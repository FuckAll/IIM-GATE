package xyz.izgnod.iim.gate.core.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.izgnod.iim.gate.core.cmd.CmdEvent;
import xyz.izgnod.iim.gate.core.cmd.handler.HandlerProxy;
import xyz.izgnod.iim.gate.core.pipeline.handler.HeadMessageHandler;
import xyz.izgnod.iim.gate.core.pipeline.handler.MessageCancelHandler;
import xyz.izgnod.iim.gate.core.pipeline.handler.MessageDownHandler;
import xyz.izgnod.iim.gate.core.pipeline.handler.MessageHandler;
import xyz.izgnod.iim.gate.core.pipeline.handler.MessageUpHandler;
import xyz.izgnod.iim.gate.core.pipeline.handler.TailMessageHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * DefaultMessagePipeline
 */
public class DefaultMessagePipeline implements MessagePipeline {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultMessagePipeline.class);
    private final DefaultMessageHandler head;
    private final DefaultMessageHandler tail;
    private final Map<String, DefaultMessageHandler> ctxMap = new HashMap<>();
    private final HandlerProxy handlerProxy;

    public DefaultMessagePipeline(HandlerProxy handlerProxy) {
        this.handlerProxy = handlerProxy;
        DefaultMessageHandler head =
                new DefaultMessageHandler(null, null, "head", new HeadMessageHandler());
        DefaultMessageHandler tail =
                new DefaultMessageHandler(null, null, "tail", new TailMessageHandler(handlerProxy));
        head.next = tail;
        tail.prev = head;
        this.head = head;
        this.tail = tail;
        ctxMap.put("head", head);
        ctxMap.put("tail", tail);
    }

    @Override
    public synchronized void addLast(String name, MessageHandler messageHandler) {
        if (ctxMap.containsKey(name)) {
            throw new IllegalArgumentException("Duplicate handler name: " + name);
        }

        DefaultMessageHandler oldPrev = tail.prev;
        DefaultMessageHandler context = new DefaultMessageHandler(oldPrev, tail, name, messageHandler);
        oldPrev.next = context;
        tail.prev = context;
        ctxMap.put(name, context);
    }

    @Override
    public void sendUp(CmdEvent e) {
        DefaultMessageHandler head = getActualUpContext(this.head);
        if (head != null) {
            sendUp(head, e);
        }
    }


    /**
     * 向上传递事件
     *
     * @param ctx
     * @param e
     */
    private void sendUp(DefaultMessageHandler ctx, CmdEvent e) {

        try {
            ((MessageUpHandler) ctx.getHandler()).handleUp(ctx, e);
        } catch (Throwable t) {
            notifyHandlerUpException(ctx, e, t);
        }
    }

    /**
     * 上行传递异常，会自动终止pipeline传递
     *
     * @param ctx
     * @param e
     * @param t
     */
    private void notifyHandlerUpException(DefaultMessageHandler ctx, CmdEvent e, Throwable t) {
        LOG.warn("pipeline notifyHandlerUpException,logId={}", e.getLogId(), t);
        ctx.endSendUp(e);
    }


    /**
     * 从尾巴开始传递的入口
     *
     * @param e
     */
    @Override
    public void sendDown(CmdEvent e) {
        //从尾开始传递
        DefaultMessageHandler tail = getActualDownContext(this.tail);
        if (tail != null) {
            sendDown(tail, e);
        }
    }

    /**
     * 向下传递事件
     *
     * @param ctx
     * @param e
     */
    private void sendDown(DefaultMessageHandler ctx, CmdEvent e) {

        try {
            ((MessageDownHandler) ctx.getHandler()).handleDown(ctx, e);
        } catch (Throwable t) {
            notifyHandlerDownException(ctx, e, t);
        }
    }

    /**
     * @param ctx
     * @param e
     * @param t
     */
    private void notifyHandlerDownException(DefaultMessageHandler ctx, CmdEvent e, Throwable t) {
        LOG.warn("pipeline notifyHandlerDownException,logId={}", e.getLogId(), t);
        ctx.endSendDown(e);
    }

    /**
     * @param e
     * @param t
     */
    private void notifyHandlerCancelException(CmdEvent e, Throwable t) {
        LOG.warn("pipeline notifyHandlerCancelException,logId={}", e.getLogId(), t);
    }

    @Override
    public synchronized MessageHandlerContext getContext(String name) {
        return ctxMap.get(name);
    }

    private DefaultMessageHandler getActualUpContext(DefaultMessageHandler ctx) {
        if (ctx == null) {
            return null;
        }
        DefaultMessageHandler realCtx = ctx;
        while (!realCtx.canHandleUp()) {
            realCtx = realCtx.next;
            if (realCtx == null) {
                return null;
            }
        }

        return realCtx;
    }

    /**
     * 向前查找一个可用的handler
     *
     * @param ctx
     * @return
     */
    private DefaultMessageHandler getActualDownContext(DefaultMessageHandler ctx) {
        if (ctx == null) {
            return null;
        }
        //查找一个可用的
        DefaultMessageHandler realCtx = ctx;
        while (!realCtx.canHandleDown()) {
            realCtx = realCtx.prev;
            if (realCtx == null) {
                return null;
            }
        }
        return realCtx;
    }

    private final class DefaultMessageHandler implements MessageHandlerContext {
        volatile DefaultMessageHandler next;
        volatile DefaultMessageHandler prev;
        private final String name;
        private final MessageHandler handler;
        private final boolean canHandleUp;
        private final boolean canHandleDown;

        DefaultMessageHandler(DefaultMessageHandler prev, DefaultMessageHandler next,
                              String name, MessageHandler handler) {

            if (name == null) {
                throw new NullPointerException("name is null");
            }

            if (handler == null) {
                throw new NullPointerException("handler is null");
            }
            canHandleUp = handler instanceof MessageUpHandler;
            canHandleDown = handler instanceof MessageDownHandler;

            if (!canHandleUp && !canHandleDown) {
                throw new IllegalArgumentException("illegal handler");
            }
            this.prev = prev;
            this.next = next;
            this.name = name;
            this.handler = handler;
        }

        @Override
        public MessagePipeline getPipeline() {
            return DefaultMessagePipeline.this;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public MessageHandler getHandler() {
            return handler;
        }

        @Override
        public boolean canHandleUp() {
            return canHandleUp;
        }

        @Override
        public boolean canHandleDown() {
            return canHandleDown;
        }

        @Override
        public void sendUp(CmdEvent e) {
            DefaultMessageHandler next = getActualUpContext(this.next);
            if (next != null) {
                DefaultMessagePipeline.this.sendUp(next, e);
            }
        }


        @Override
        public void sendDown(CmdEvent e) {
            DefaultMessageHandler prev = getActualDownContext(this.prev);
            if (prev != null) {
                DefaultMessagePipeline.this.sendDown(prev, e);
            }
        }

        @Override
        public void endSendUp(CmdEvent e) {
            DefaultMessageHandler tail = DefaultMessagePipeline.this.tail;
            MessageHandler handler = tail.getHandler();
            if (handler instanceof MessageCancelHandler) {
                try {
                    ((MessageCancelHandler) handler).handleCancel(tail, e);
                } catch (Exception e1) {
                    DefaultMessagePipeline.this.notifyHandlerCancelException(e, e1);
                }
            }

        }

        @Override
        public void endSendDown(CmdEvent e) {
            DefaultMessageHandler head = DefaultMessagePipeline.this.head;
            MessageHandler handler = head.getHandler();
            if (handler instanceof MessageCancelHandler) {
                try {
                    ((MessageCancelHandler) handler).handleCancel(head, e);
                } catch (Exception e1) {
                    DefaultMessagePipeline.this.notifyHandlerCancelException(e, e1);
                }
            }
        }
    }
}

package xyz.izgnod.iim.gate.core.pipeline.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.izgnod.iim.gate.core.cmd.CmdEvent;
import xyz.izgnod.iim.gate.core.cmd.handler.HandlerProxy;
import xyz.izgnod.iim.gate.core.pipeline.MessageHandlerContext;

public class TailMessageHandler implements MessageUpHandler, MessageCancelHandler {
    private static final Logger LOG = LoggerFactory.getLogger(TailMessageHandler.class);
    private final HandlerProxy handlerProxy;

    public TailMessageHandler(HandlerProxy handlerProxy) {
        this.handlerProxy = handlerProxy;
    }

    @Override
    public void handleUp(MessageHandlerContext ctx, CmdEvent cmdEvent) throws Exception {
        try {
            handlerProxy.handleMessage(cmdEvent);
            cmdEvent.getFuture().setSuccess();
        } catch (Throwable throwable) {
            cmdEvent.getFuture().setFailure(throwable);
        }
    }

    @Override
    public void handleCancel(MessageHandlerContext ctx, CmdEvent cmdEvent) throws Exception {
        LOG.warn("handleCancel,logId={}", cmdEvent.getLogId());
        cmdEvent.getFuture().setFailure(new RuntimeException("future cancel"));
    }
}

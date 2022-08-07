package xyz.izgnod.iim.gate.core.pipeline;

import xyz.izgnod.iim.gate.core.cmd.CmdEvent;
import xyz.izgnod.iim.gate.core.pipeline.handler.MessageHandler;

public interface MessagePipeline {

    /**
     * 在尾部追加handler
     *
     * @param name
     * @param messageHandler
     */
    void addLast(String name, MessageHandler messageHandler);

    /**
     * 向上传递消息
     *
     * @param e
     */
    void sendUp(CmdEvent e);

    /**
     * 向下传递消息
     *
     * @param e
     */
    void sendDown(CmdEvent e);

    /**
     * 获取handlerContext
     *
     * @param name
     * @return
     */
    MessageHandlerContext getContext(String name);

}

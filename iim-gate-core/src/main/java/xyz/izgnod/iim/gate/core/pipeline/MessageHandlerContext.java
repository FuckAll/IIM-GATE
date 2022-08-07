package xyz.izgnod.iim.gate.core.pipeline;


import xyz.izgnod.iim.gate.core.cmd.CmdEvent;
import xyz.izgnod.iim.gate.core.pipeline.handler.MessageHandler;

public interface MessageHandlerContext {

    /**
     * 获取关联的pipeline
     *
     * @return
     */
    MessagePipeline getPipeline();

    /**
     * 获取handler名称
     *
     * @return
     */
    String getName();

    /**
     * 获取handler
     *
     * @return
     */
    MessageHandler getHandler();

    /**
     * 是否可以向上传递
     *
     * @return
     */
    boolean canHandleUp();

    /**
     * 是否可以向下传递
     *
     * @return
     */
    boolean canHandleDown();

    /**
     * 向上传递
     *
     * @param e
     */
    void sendUp(CmdEvent e);

    /**
     * 向下传递
     *
     * @param e
     */
    void sendDown(CmdEvent e);

    /**
     * 终止向上传递
     *
     * @param e
     */
    void endSendUp(CmdEvent e);

    /**
     * 终止向下传递
     *
     * @param e
     */
    void endSendDown(CmdEvent e);

}

package xyz.izgnod.iim.gate.core.pipeline.handler;

import xyz.izgnod.iim.gate.core.cmd.CmdEvent;
import xyz.izgnod.iim.gate.core.pipeline.MessageHandlerContext;

public interface MessageUpHandler extends MessageHandler {

    /**
     * handleUP
     *
     * @param ctx
     * @param cmdEvent
     * @throws Exception
     */
    void handleUp(MessageHandlerContext ctx, CmdEvent cmdEvent) throws Exception;

}

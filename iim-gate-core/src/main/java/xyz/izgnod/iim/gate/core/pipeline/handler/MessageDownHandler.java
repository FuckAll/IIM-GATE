package xyz.izgnod.iim.gate.core.pipeline.handler;

import xyz.izgnod.iim.gate.core.cmd.CmdEvent;
import xyz.izgnod.iim.gate.core.pipeline.MessageHandlerContext;

public interface MessageDownHandler extends MessageHandler {

    /**
     * handleDown
     *
     * @param ctx
     * @param cmdEvent
     * @throws Exception
     */
    void handleDown(MessageHandlerContext ctx, CmdEvent cmdEvent) throws Exception;
}

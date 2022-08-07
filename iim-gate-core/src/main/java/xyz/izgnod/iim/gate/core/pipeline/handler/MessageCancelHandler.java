package xyz.izgnod.iim.gate.core.pipeline.handler;

import xyz.izgnod.iim.gate.core.cmd.CmdEvent;
import xyz.izgnod.iim.gate.core.pipeline.MessageHandlerContext;

public interface MessageCancelHandler extends MessageHandler {

    /**
     * Cancel
     *
     * @param ctx
     * @param cmdEvent
     * @throws Exception
     */
    void handleCancel(MessageHandlerContext ctx, CmdEvent cmdEvent) throws Exception;

}

package xyz.izgnod.iim.gate.core.pipeline.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.izgnod.iim.gate.core.cmd.CmdEvent;
import xyz.izgnod.iim.gate.core.cmd.DownCmdEvent;
import xyz.izgnod.iim.gate.core.connection.AbstractNettyConnection;
import xyz.izgnod.iim.gate.core.connection.Connection;
import xyz.izgnod.iim.gate.core.pipeline.MessageHandlerContext;

public class HeadMessageHandler implements MessageDownHandler, MessageCancelHandler {
    private static final Logger LOG = LoggerFactory.getLogger(HeadMessageHandler.class);
    @Override
    public void handleDown(MessageHandlerContext ctx, CmdEvent cmdEvent) throws Exception {
        try {
            Connection connection = cmdEvent.getConnection();
            if (connection instanceof AbstractNettyConnection) {
                AbstractNettyConnection nettyConnection = (AbstractNettyConnection) connection;
                nettyConnection.write2Client((DownCmdEvent) cmdEvent);
            }
            cmdEvent.getFuture().setSuccess();
        } catch (Throwable e) {
            LOG.error("handle down message throwable ", e);
            cmdEvent.getFuture().setFailure(e);
        }
    }

    @Override
    public void handleCancel(MessageHandlerContext ctx, CmdEvent cmdEvent) throws Exception {
        LOG.warn("handleCancel,logId={}", cmdEvent.getLogId());
        cmdEvent.getFuture().setFailure(new RuntimeException("future cancel"));
    }
}

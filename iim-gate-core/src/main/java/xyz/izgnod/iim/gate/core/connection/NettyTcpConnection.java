package xyz.izgnod.iim.gate.core.connection;


import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.izgnod.iim.gate.core.cmd.DownCmdEvent;
import xyz.izgnod.iim.gate.core.pipeline.MessagePipeline;
import xyz.izgnod.iim.gate.protocol.tcp.GateMessage;

/**
 * Netty TCP Connection
 */
public class NettyTcpConnection extends AbstractNettyConnection {

    private static final Logger LOG = LoggerFactory.getLogger(NettyTcpConnection.class);

    public NettyTcpConnection(Channel channel, String sessionId, String remoteIp, int remotePort, MessagePipeline messagePipeline) {
        super(channel, sessionId, remoteIp, remotePort, messagePipeline);
    }

    @Override
    public void write2Client(DownCmdEvent cmdEvent) {
        GateMessage gateMessage = cmdEvent.getGateMessage();
        LOG.info("getMessage = {}", gateMessage);
        if (gateMessage == null) {
            return;
        }

        LOG.debug("will write message,logId={}", cmdEvent.getLogId());
        ChannelFuture future = channel.writeAndFlush(gateMessage);

        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                LOG.info("echo handler write back success...");
            }
        });
    }
}

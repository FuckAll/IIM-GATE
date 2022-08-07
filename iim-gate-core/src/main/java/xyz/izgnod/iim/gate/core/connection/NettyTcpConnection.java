package xyz.izgnod.iim.gate.core.connection;


import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.izgnod.iim.gate.core.cmd.DownCmdEvent;
import xyz.izgnod.iim.gate.core.pipeline.MessagePipeline;

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
//        GateMessage gateMessage = cmdEvent.getGateMessage();
//        if (gateMessage == null) {
//            return;
//        }
//        LOG.debug("will write message,logId={}", cmdEvent.getLogId());
//        byte[] bytes = gateMessage.toByteArray();
//        ChannelFuture future = channel.write(ChannelBuffers.copiedBuffer(bytes));
//
//        //传递trace信息
//        String currentTraceId = LogUtil.getCurrentTraceId();
//        String currentSpanId = LogUtil.getCurrentSpanId();
//        future.addListener(new SendMessageWithTraceFutureHandler(cmdEvent.getLogId(), currentTraceId, currentSpanId));
    }


}

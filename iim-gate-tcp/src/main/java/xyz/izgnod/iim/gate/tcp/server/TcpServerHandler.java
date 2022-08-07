package xyz.izgnod.iim.gate.tcp.server;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import xyz.izgnod.iim.gate.core.component.BaseCommonSimpleChannelUpstreamHandler;
import xyz.izgnod.iim.gate.core.config.ServerProperties;
import xyz.izgnod.iim.gate.core.connection.Connection;
import xyz.izgnod.iim.gate.core.connection.NettyTcpConnection;
import xyz.izgnod.iim.gate.protocol.tcp.GateMessage;

import javax.annotation.Resource;

@Component
@ChannelHandler.Sharable
public class TcpServerHandler extends BaseCommonSimpleChannelUpstreamHandler {

    private static final Logger LOG = LoggerFactory.getLogger(TcpServerHandler.class);

    private static final AttributeKey<NettyTcpConnection> CONNECTION_ATTRIBUTE_KEY = AttributeKey.valueOf("connection");

    @Resource
    private ServerProperties serverProperties;

    //    @Resource
//    private MessagePipeline messagePipeline;
//
//    public TcpServerHandler(Connector connector, ServerProperties serverProperties) {
//        super(connector, serverProperties);
//    }
    public TcpServerHandler() {

    }

    @Override
    protected Connection createOrGetConnection(Channel channel) {
//        if (channel.hasAttr(CONNECTION_ATTRIBUTE_KEY)) {
//            return channel.attr(CONNECTION_ATTRIBUTE_KEY).get();
//        }
//        NettyTcpConnection connection = new NettyTcpConnection(channel, channel.id().asLongText(),
//                GateNetUtils.getIp(channel.remoteAddress()), GateNetUtils.getPort(channel.remoteAddress()), messagePipeline);
//        channel.attr(CONNECTION_ATTRIBUTE_KEY).set(connection);
//        LOG.info("create connection,sessionId={},remote address={}",
//                connection.getSessionId(), connection.getRemoteIp() + ":" + connection.getRemotePort());
//        return connection;
        return null;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GateMessage msg) throws Exception {
//        String logId = UUID.randomUUID().toString();
        System.out.println("msg = " + msg);
//        Connection connection = createOrGetConnection(ctx.channel());
//        LOG.debug("receive message,logId={},sessionId={}", logId, connection.getSessionId());

        //封装客户端发送的消息
//        GateMessage gateMessage = convert2GateMessage(connector, connection, msg);
//        if (gateMessage == null) {
        return;
//        }

//        if (isAuthMessage(gateMessage)) {
//            Auth.AuthRequest authMessage = getAuthMessage(gateMessage);
//            if (authMessage != null) {
//                String traceIdSalt = authMessage.getTraceIdSalt();
//                connection.setAttachment(Constant.TRACEID_SALT, traceIdSalt);
//                connection.setAttachment(Constant.USERID, authMessage.getUid());
//                connection.setAttachment(Constant.APPID, authMessage.getAppId());
//            }
//        }
//
//        UpCmdEvent upCmdEvent = new UpCmdEvent(connection, gateMessage, logId);
//        connector.process(upCmdEvent);
    }
}

package xyz.izgnod.iim.gate.core.component;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.ReadTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.izgnod.iim.gate.core.cmd.GateCmdEnum;
import xyz.izgnod.iim.gate.core.config.ServerProperties;
import xyz.izgnod.iim.gate.core.connection.Connection;
import xyz.izgnod.iim.gate.core.connection.event.ConnectionStateEvent;
import xyz.izgnod.iim.gate.core.connector.Connector;
import xyz.izgnod.iim.gate.core.util.GateNetUtils;
import xyz.izgnod.iim.gate.protocol.tcp.GateMessage;
import xyz.izgnod.iim.gate.protocol.tcp.MsgHeader;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseCommonSimpleChannelUpstreamHandler extends SimpleChannelInboundHandler<GateMessage> {

    private static final Logger LOG = LoggerFactory.getLogger(BaseCommonSimpleChannelUpstreamHandler.class);
    private final AtomicInteger count = new AtomicInteger();

    private final ConcurrentMap<String, Long> map = new ConcurrentHashMap<>();
    protected Connector connector;
    private final ServerProperties serverProperties = null;

    //
//    public BaseCommonSimpleChannelUpstreamHandler(Connector connector, ServerProperties serverProperties) {
//        this.connector = connector;
//        this.serverProperties = serverProperties;
//    }
    public BaseCommonSimpleChannelUpstreamHandler() {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if (!(evt instanceof IdleStateEvent)) {
            return;
        }

        IdleStateEvent e = (IdleStateEvent) evt;
        IdleState state = e.state();
        if (state == IdleState.READER_IDLE || state == IdleState.WRITER_IDLE) {
            Connection connection = createOrGetConnection(ctx.channel());
            connector.process(new ConnectionStateEvent(ConnectionStateEvent.State.TIMEOUT, connection));
        }
    }

    protected abstract Connection createOrGetConnection(Channel channel);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

//        Connection connection = createOrGetConnection(ctx.channel());
//        connector.process(new ConnectionStateEvent(ConnectionStateEvent.State.OPEN, connection));
//        map.put(ctx.channel().id().asLongText(), System.currentTimeMillis());
//        count.incrementAndGet();
//        LOG.debug("channelConnected remote address={},channelId={},count={}",
//                GateNetUtils.getAddress(ctx.channel().remoteAddress()), ctx.channel().id().asLongText(), count.get());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        super.channelInactive(ctx);
//        Connection connection = createOrGetConnection(ctx.channel());
//        connector.process(new ConnectionStateEvent(ConnectionStateEvent.State.CLOSE, connection));
//        map.remove(ctx.channel().id().asLongText());
//        count.decrementAndGet();
//        LOG.debug("channelDisconnected remote address={},channelId={},count={}",
//                GateNetUtils.getAddress(ctx.channel().remoteAddress()), ctx.channel().id().asLongText(), count.get());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        String address = GateNetUtils.getAddress(ctx.channel().remoteAddress());
        String channelId = ctx.channel().id().asLongText();
        if (cause.getCause() instanceof ReadTimeoutException) {
            LOG.warn("readTimeoutException remote address=" + address + "channelId=" + channelId, cause.getCause());
        } else if (cause.getCause() instanceof IOException) {
            LOG.warn("IOException remote address=" + address + "channelId=" + channelId, cause.getCause());
        } else if (cause.getCause() instanceof TooLongFrameException) {
            LOG.warn("TooLongFrameException remote address=" + address + "channelId=" + channelId, cause.getCause());
        } else {
            LOG.error("exceptionCaught remote address=" + address + "channelId=" + channelId, cause.getCause());
        }
        Connection connection = createOrGetConnection(ctx.channel());
        connector.process(new ConnectionStateEvent(ConnectionStateEvent.State.ERROR, connection));
    }

    protected boolean isAuthMessage(GateMessage gateMessage) {
        int cmdId = gateMessage.getHeader().getCmdId();
        if (GateCmdEnum.AUTH.getCmdId() == cmdId) {
            return true;
        }
        return false;
    }


//    protected Auth.AuthRequest getAuthMessage(GateMessage gateMessage) {
//        int cmdId = gateMessage.getHeader().getCmdId();
//        if (GateCmdEnum.AUTH.getCmdId() == cmdId) {
//            //解析auth请求
//            Auth.AuthRequest request = null;
//            try {
//                request = Auth.AuthRequest.parseFrom(gateMessage.getBody());
//            } catch (InvalidProtocolBufferException e) {
//                LOG.error("authRequest parseFrom error", e);
//
//                return null;
//            }
//            return request;
//        }
//        return null;
//    }


    /**
     * @return 获取连接数
     */
    public int getConnectionCount() {
        return count.intValue();
    }

    private void printTooLongFrame(byte[] bytes) {
        byte[] headerBytes = new byte[MsgHeader.HEADER_LENGTH];
        System.arraycopy(bytes, 0, headerBytes, 0, MsgHeader.HEADER_LENGTH);

        MsgHeader header = MsgHeader.newMsgHeader();
        header.decode(headerBytes);
        LOG.warn("frame length exceeds={},now={},header={}", serverProperties.getFrameMaxLength(), bytes.length, header);
    }
}

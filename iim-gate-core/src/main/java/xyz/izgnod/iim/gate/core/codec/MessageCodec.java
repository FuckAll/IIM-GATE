package xyz.izgnod.iim.gate.core.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.izgnod.iim.gate.core.connection.NettyTcpConnection;
import xyz.izgnod.iim.gate.protocol.tcp.GateMessage;

import java.util.List;

public class MessageCodec extends ByteToMessageCodec<GateMessage> {
    private static final Logger LOG = LoggerFactory.getLogger(NettyTcpConnection.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, GateMessage msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.toByteArray());
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int len = in.readableBytes();
        byte[] bytes = new byte[len];
        in.readBytes(bytes, 0, len);
        out.add(GateMessage.streamToMessage(bytes));
    }
}

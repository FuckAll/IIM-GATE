package xyz.izgnod.iim.gate.core.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import xyz.izgnod.iim.gate.protocol.tcp.GateMessage;

import java.util.List;

public class MessageCodec extends ByteToMessageCodec<GateMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, GateMessage msg, ByteBuf out) throws Exception {
        out.setBytes(0, msg.toByteArray());
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int len = in.readableBytes();
        byte[] bytes = new byte[len];
        in.readBytes(bytes, 0, len);
        out.add(GateMessage.streamToMessage(bytes));
    }
}

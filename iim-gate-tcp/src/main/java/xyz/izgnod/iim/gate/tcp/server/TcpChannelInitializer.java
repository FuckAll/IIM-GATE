package xyz.izgnod.iim.gate.tcp.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import xyz.izgnod.iim.gate.core.codec.MessageCodec;
import xyz.izgnod.iim.gate.core.config.ServerProperties;

public class TcpChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final ServerProperties serverProperties;
    private final TcpServerHandler tcpServerHandler;

    public TcpChannelInitializer(ServerProperties serverProperties, TcpServerHandler tcpServerHandler) {
        this.serverProperties = serverProperties;
        this.tcpServerHandler = tcpServerHandler;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("timeout",
                new IdleStateHandler(0, 60, 0));
        ch.pipeline().addLast("framer",
                new LengthFieldBasedFrameDecoder(serverProperties.getFrameMaxLength(), 16,
                        4, 0, 0, true));
        ch.pipeline().addLast("codec", new MessageCodec());
        ch.pipeline().addLast("handler", tcpServerHandler);
    }
}

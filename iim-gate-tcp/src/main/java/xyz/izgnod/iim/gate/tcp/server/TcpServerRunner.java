package xyz.izgnod.iim.gate.tcp.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.izgnod.iim.gate.core.codec.MessageCodec;
import xyz.izgnod.iim.gate.core.config.ServerProperties;

@Component
public class TcpServerRunner implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TcpServerRunner.class);

    private final ServerProperties serverProperties;
    private final TcpServerHandler tcpServerHandler;

    @Autowired
    public TcpServerRunner(ServerProperties serverProperties, TcpServerHandler tcpServerHandler) {
        this.serverProperties = serverProperties;
        System.out.println("serverProperties = " + serverProperties.getPort());
        this.tcpServerHandler = tcpServerHandler;
    }

    @Override
    public void run(String... args) throws Exception {
        // todo bossGroup and workGroup from config
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childOption(ChannelOption.TCP_NODELAY, true).childOption(ChannelOption.SO_SNDBUF, 65536).option(ChannelOption.SO_RCVBUF, 65536).handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("timeout", new IdleStateHandler(0, 60, 0));
                            ch.pipeline().addLast("framer", new LengthFieldBasedFrameDecoder(serverProperties.getFrameMaxLength(), 16, 4, 0, 0, true));
                            ch.pipeline().addLast("codec", new MessageCodec());
                            ch.pipeline().addLast("handler", tcpServerHandler);
                            // ratelimiter
                            // todo
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(serverProperties.getPort());
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}

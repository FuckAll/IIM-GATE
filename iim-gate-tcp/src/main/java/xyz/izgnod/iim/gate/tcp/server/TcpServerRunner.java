package xyz.izgnod.iim.gate.tcp.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.izgnod.iim.gate.core.config.ServerProperties;

@Component
public class TcpServerRunner implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TcpServerRunner.class);

    private final ServerProperties serverProperties;
    private final TcpServerHandler tcpServerHandler;

    @Autowired
    public TcpServerRunner(ServerProperties serverProperties, TcpServerHandler tcpServerHandler) {
        this.serverProperties = serverProperties;
        this.tcpServerHandler = tcpServerHandler;
    }

    @Override
    public void run(String... args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(serverProperties.getBossGroup());
        EventLoopGroup workGroup = new NioEventLoopGroup(serverProperties.getWorkGroup());
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .option(ChannelOption.SO_RCVBUF, 65536)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_SNDBUF, 65536)
                    .childHandler(new TcpChannelInitializer(serverProperties, tcpServerHandler));
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

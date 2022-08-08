package xyz.izgnod.iim.gate.tcp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.izgnod.iim.gate.core.cmd.handler.HandlerProxy;
import xyz.izgnod.iim.gate.core.component.ProxySelectorComponent;
import xyz.izgnod.iim.gate.core.config.ServerProperties;
import xyz.izgnod.iim.gate.core.connector.Connector;
import xyz.izgnod.iim.gate.core.connector.PipelineConnector;
import xyz.izgnod.iim.gate.core.pipeline.DefaultMessagePipeline;
import xyz.izgnod.iim.gate.core.pipeline.MessagePipeline;
import xyz.izgnod.iim.gate.core.session.DefaultSessionManager;
import xyz.izgnod.iim.gate.core.session.SessionManager;
import xyz.izgnod.iim.gate.core.task.GateThreadFactory;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class BeanConfig {

//    @Value("${dubbo.protocol.port}")
//    private int port;

    @Bean
    @ConfigurationProperties(prefix = "iim-gate-tcp.server")
    public ServerProperties gateServerProperties() {
        return new ServerProperties();
    }
//
//    /**
//     * 用于dubbo地址
//     *
//     * @return
//     */
//    @Bean
//    public DubboAddressComponent dubboAddressComponent() {
//        return new DubboAddressComponent(port);
//    }
//
//    /**
//     * gate元信息
//     *
//     * @param dubboAddressComponent
//     * @return
//     */
//    @Bean
//    public GateMetadataDto gateMetadataDto(DubboAddressComponent dubboAddressComponent) {
//        return new GateMetadataDto(dubboAddressComponent.getIp(), dubboAddressComponent.getPort(), GateTypeEnum.TCP.getCode());
//    }

    /**
     * session管理
     *
     * @return
     */
    @Bean
    public SessionManager sessionManager(ServerProperties serverProperties, ProxySelectorComponent proxySelectorComponent) {
        return new DefaultSessionManager(serverProperties, proxySelectorComponent);
    }
//
//    /**
//     * 配置zipkin监控上行
//     *
//     * @return
//     */
//    @Bean
//    public TraceMessageUpHandler traceMessageUpHandler(Tracing tracing) {
//        return new TraceMessageUpHandler(tracing);
//
//    }

//    /**
//     * 配置zipkin监控下行
//     *
//     * @return
//     */
//    @Bean
//    public TraceMessageDownHandler traceMessageDownHandler(Tracing tracing) {
//        return new TraceMessageDownHandler(tracing);
//
//    }

//    /**
//     * 自定义redisTemplate，添加自定义序列化方式
//     *
//     * @param redisConnectionFactory
//     * @return
//     */
//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//
//        //设置序列化方式
//        RedisSerializer keySerializer = new StringRedisSerializer();
//        RedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();
//        redisTemplate.setKeySerializer(keySerializer);
//        redisTemplate.setValueSerializer(valueSerializer);
//        redisTemplate.setHashKeySerializer(keySerializer);
//        redisTemplate.setHashValueSerializer(valueSerializer);
//        return redisTemplate;
//    }


    /**
     * 配置处理消息的pipeline
     *
     * @param handlerProxy
     * @param sessionManager
     * @return
     */
    @Bean
    public MessagePipeline messagePipeline(HandlerProxy handlerProxy, SessionManager sessionManager) {
        DefaultMessagePipeline messagePipeline = new DefaultMessagePipeline(handlerProxy);
//        messagePipeline.addLast("limit", new SentinelLimitMessageHandler());
        /**
         * 需要加密的serviceId，必须配置。
         */
//        Set<Integer> include = new HashSet<>();
//        messagePipeline.addLast("traceUp", traceMessageUpHandler);
//        messagePipeline.addLast("monitor", new MonitorPrometheusMessageHandler(registry));
//        messagePipeline.addLast("traceDown", traceMessageDownHandler);
        return messagePipeline;
    }


    /**
     * 配置处理消息事件的连接器
     *
     * @param messagePipeline
     * @param sessionManager  会话管理器
     * @return
     */
    @Bean
    public Connector connector(MessagePipeline messagePipeline, SessionManager sessionManager) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(200, 200,
                0, TimeUnit.SECONDS, new LinkedBlockingDeque<>(20000),
                new GateThreadFactory("TcpConnector", true), new ThreadPoolExecutor.CallerRunsPolicy());
        return new PipelineConnector(messagePipeline, threadPoolExecutor, sessionManager);
    }
//
//
//    /**
//     * auth处理
//     *
//     * @param proxySelectorComponent
//     * @param sessionManager
//     * @return
////     */
//    @Bean
//    public AuthHandler authHandler(ProxySelectorComponent proxySelectorComponent, SessionManager sessionManager) {
//        return new AuthHandler(proxySelectorComponent, sessionManager);
//    }
//
//    /**
//     * 心跳处理
//     *
//     * @param sessionManager
//     * @param proxySelectorComponent
//     * @return
//     */
//    @Bean
//    public KeepAliveCmdHandler keepAliveCmdHandler(SessionManager sessionManager, ProxySelectorComponent proxySelectorComponent) {
//        return new KeepAliveCmdHandler(sessionManager, proxySelectorComponent);
//    }
//
//
//    /**
//     * 用户退出
//     *
//     * @param sessionManager
//     * @param proxySelectorComponent
//     * @return
//     */
//    @Bean
//    public LogoutHandler logoutHandler(SessionManager sessionManager, ProxySelectorComponent proxySelectorComponent) {
//        return new LogoutHandler(sessionManager, proxySelectorComponent);
//    }
//
//
//    /**
//     * 默认处理
//     *
//     * @param sessionManager
//     * @param proxySelectorComponent
//     * @return
//     */
//    @Bean
//    public DefaultHandler defaultHandler(SessionManager sessionManager, ProxySelectorComponent proxySelectorComponent) {
//        return new DefaultHandler(sessionManager, proxySelectorComponent);
//    }
//
//
//    /**
//     * http代理
//     *
//     * @return
//     */
//    @Bean
//    public HttpHandler httpHandler() {
//        return new HttpHandler();
//    }
//
//
//    /**
//     * 测试速度使用
//     *
//     * @return
//     */
//    @Bean
//    public EchoHandler echoHandler() {
//        return new EchoHandler();
//    }
//
//    @Bean
//    public HeartbeatCmdHandler heartbeatCmdHandler(SessionManager sessionManager, ProxySelectorComponent proxySelectorComponent) {
//        return new HeartbeatCmdHandler(sessionManager, proxySelectorComponent);
//    }
}

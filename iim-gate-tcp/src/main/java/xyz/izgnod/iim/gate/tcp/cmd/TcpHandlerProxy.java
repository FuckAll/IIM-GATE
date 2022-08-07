package xyz.izgnod.iim.gate.tcp.cmd;


import org.springframework.stereotype.Component;
import xyz.izgnod.iim.gate.core.cmd.handler.AbstractHandlerProxy;

import javax.annotation.PostConstruct;

@Component
public class TcpHandlerProxy extends AbstractHandlerProxy {
    //
//    @Resource
//    private AuthHandler authHandler;
//    @Resource
//    private KeepAliveCmdHandler keepAliveCmdHandler;
//    @Resource
//    private LogoutHandler logoutHandler;
//    @Resource
//    private DefaultHandler defaultHandler;
//    @Resource
//    private HttpHandler httpHandler;
//    @Resource
//    private EchoHandler echoHandler;
//    @Resource
//    private HeartbeatCmdHandler heartbeatCmdHandler;

    /**
     * 注册命令处理器
     * gate层内置的命令号很少，直接写死，其他业务不可以与gate内置的一样
     */
    @PostConstruct
    void register() {
//        super.register(GateCmdEnum.AUTH.getCmdId(), authHandler);
//        super.register(GateCmdEnum.KEEP_ALIVE.getCmdId(), keepAliveCmdHandler);
//        super.register(GateCmdEnum.LOG_OUT.getCmdId(), logoutHandler);
//        super.register(GateCmdEnum.DEFAULT.getCmdId(), defaultHandler);
//        super.register(GateCmdEnum.HEARTBEAT.getCmdId(), heartbeatCmdHandler);
//        super.register(10, httpHandler);
//        super.register(11, echoHandler);
    }
}

package xyz.izgnod.iim.gate.core.logic.service;

import xyz.izgnod.iim.gate.api.dto.GateUserDto;
import xyz.izgnod.iim.gate.core.cmd.CmdEvent;
import xyz.izgnod.iim.gate.core.session.Session;

/**
 * @description 统一封装对逻辑层服务访问的代理对象。
 * gate层有多个业务提供方，可能dubbo地址都不同。
 * gate通过路由找到各个业务方的实现类，每个业务方实现类必须实现这个接口。由这个接口来统一处理业务逻辑
 */
public interface ServiceProxy {


    /**
     * 登录
     *
     * @param cmdEvent
     */
    Session auth(CmdEvent cmdEvent);

    /**
     * 心跳
     *
     * @param session
     * @param cmdEvent
     */
    void keepalive(Session session, CmdEvent cmdEvent);


    /**
     * 退出
     *
     * @param gateUserDto
     * @param logId
     */
    void logout(GateUserDto gateUserDto, String logId);

    /**
     * 发送消息
     *
     * @param session
     * @param cmdEvent
     */
    void sendMsg(Session session, CmdEvent cmdEvent);
}

package xyz.izgnod.iim.gate.api.service;

import xyz.izgnod.iim.gate.api.dto.GateMetadataDto;
import xyz.izgnod.iim.gate.api.dto.GateUserDto;
import xyz.izgnod.iim.gate.api.dto.LogicAuthResponse;
import xyz.izgnod.iim.gate.protocol.tcp.GateMessage;

/**
 * @description gate调用业务方的接口，消息上行接口
 * <p>
 * 各个业务方实现这个接口，gate层通过dubbo的group来区分调用不同的业务方。
 */
public interface Logic4GateService {


    /**
     * 登录
     *
     * @param gateUserDto     用户
     * @param gateMessage     消息
     * @param gateMetadataDto gate信息
     * @param logId           请求id
     * @return
     */
    LogicAuthResponse auth(GateUserDto gateUserDto, GateMessage gateMessage, GateMetadataDto gateMetadataDto, String logId);


    /**
     * 心跳
     *
     * @param gateUserDto     用户
     * @param gateMetadataDto gate信息
     * @param logId           请求id
     */
    void keepAlive(GateUserDto gateUserDto, GateMetadataDto gateMetadataDto, String logId);

    /**
     * 退出
     *
     * @param gateUserDto     用户
     * @param gateMetadataDto gate信息
     * @param logId           请求id
     */
    void logout(GateUserDto gateUserDto, GateMetadataDto gateMetadataDto, String logId);

    /**
     * 发送消息
     *
     * @param gateUserDto     用户
     * @param gateMessage     消息
     * @param gateMetadataDto gate信息
     * @param logId           请求id
     */
    void sendMessage(GateUserDto gateUserDto, GateMessage gateMessage, GateMetadataDto gateMetadataDto, String logId);

}

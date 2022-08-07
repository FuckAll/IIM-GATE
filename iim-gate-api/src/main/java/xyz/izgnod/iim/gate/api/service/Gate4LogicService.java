package xyz.izgnod.iim.gate.api.service;


import xyz.izgnod.iim.gate.api.dto.GateUserDto;
import xyz.izgnod.iim.gate.protocol.tcp.GateMessage;

import java.util.List;

public interface Gate4LogicService {
    /**
     * 发送消息，将消息发给一个用户
     *
     * @param gateMessage 消息
     * @param gateUserDto 接收者
     * @param logId       请求id
     */
    void pushMessage(GateMessage gateMessage, GateUserDto gateUserDto, String logId);

    /**
     * 发送消息，将消息发送给多个用户
     *
     * @param gateMessage     消息
     * @param gateUserDtoList 接收者集合
     * @param logId           请求id
     */
    void pushMessage(GateMessage gateMessage, List<GateUserDto> gateUserDtoList, String logId);

    /**
     * gate层踢人
     *
     * @param gateUserDto 需要强制下线的用户
     * @param gateMessage 下线前如果用户在线，发送给用户的消息
     * @param logId       请求id
     */
    void kickOut(GateUserDto gateUserDto, GateMessage gateMessage, String logId);

    /**
     * 获取在线的连接
     *
     * @param gateUserDtoList 所有待检测的连接
     * @param logId           请求id
     * @return 所有在线的连接
     */
    List<GateUserDto> getOnlineConnection(List<GateUserDto> gateUserDtoList, String logId);

    /**
     * 关闭连接
     *
     * @param gateUserDto
     * @param logId
     */
    void closeConnection(GateUserDto gateUserDto, String logId);
}



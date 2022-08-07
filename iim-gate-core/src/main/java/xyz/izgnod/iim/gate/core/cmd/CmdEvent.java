package xyz.izgnod.iim.gate.core.cmd;


import xyz.izgnod.iim.gate.core.connection.event.ConnectionEvent;
import xyz.izgnod.iim.gate.protocol.tcp.GateMessage;

public interface CmdEvent extends ConnectionEvent {

    /**
     * 获取消息
     *
     * @return
     */
    GateMessage getGateMessage();

    /**
     * 获取logId
     *
     * @return
     */
    String getLogId();

    /**
     * 获取开始时间
     */
    Long getStartTime();
}

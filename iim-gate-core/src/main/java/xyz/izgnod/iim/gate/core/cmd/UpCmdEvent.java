package xyz.izgnod.iim.gate.core.cmd;


import xyz.izgnod.iim.gate.core.connection.Connection;
import xyz.izgnod.iim.gate.core.connection.event.ConnectionFuture;
import xyz.izgnod.iim.gate.core.connection.event.DefaultConnectionFuture;
import xyz.izgnod.iim.gate.protocol.tcp.GateMessage;

public class UpCmdEvent implements CmdEvent {
    private final ConnectionFuture connectionFuture;
    private final Connection connection;
    private final GateMessage gateMessage;
    private final String logId;
    private Long startTime = System.currentTimeMillis();

    public UpCmdEvent(Connection connection, GateMessage gateMessage, String logId) {
        this.connectionFuture = new DefaultConnectionFuture();
        this.connection = connection;
        this.gateMessage = gateMessage;
        this.logId = logId;
    }

    @Override
    public ConnectionFuture getFuture() {
        return connectionFuture;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public GateMessage getGateMessage() {
        return gateMessage;
    }

    @Override
    public String getLogId() {
        return logId;
    }

    @Override
    public Long getStartTime() {
        return this.startTime;
    }
}

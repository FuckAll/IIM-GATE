package xyz.izgnod.iim.gate.core.connection.event;


import xyz.izgnod.iim.gate.core.connection.Connection;

public class ConnectionStateEvent implements ConnectionEvent {

    private final State state;
    private final ConnectionFuture connectionFuture;
    private final Connection connection;

    public ConnectionStateEvent(State state, Connection connection) {
        this.state = state;
        this.connectionFuture = new DefaultConnectionFuture();
        this.connection = connection;
    }

    @Override
    public ConnectionFuture getFuture() {
        return connectionFuture;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    public State getState() {
        return state;
    }

    public enum State {
        OPEN, TIMEOUT, ERROR, CLOSE;
    }
}

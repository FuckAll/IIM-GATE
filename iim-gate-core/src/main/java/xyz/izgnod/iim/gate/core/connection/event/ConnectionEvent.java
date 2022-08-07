package xyz.izgnod.iim.gate.core.connection.event;

import xyz.izgnod.iim.gate.core.connection.Connection;

public interface ConnectionEvent {

    /**
     * 获取future
     *
     * @return
     */
    ConnectionFuture getFuture();

    /**
     * 获取关联的connection
     *
     * @return
     */
    Connection getConnection();
}

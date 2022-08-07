package xyz.izgnod.iim.gate.core.connector;


import xyz.izgnod.iim.gate.core.connection.event.ConnectionEvent;

public interface Connector {

    /**
     * 处理连接上的事件
     *
     * @param connectionEvent
     */
    void process(ConnectionEvent connectionEvent);

}

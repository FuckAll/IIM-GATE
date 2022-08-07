package xyz.izgnod.iim.gate.core.connection.event;


public interface ConnectionFutureListener {

    /**
     * 操作完成后回调
     *
     * @param future
     */
    void operationComplete(ConnectionFuture future);
}

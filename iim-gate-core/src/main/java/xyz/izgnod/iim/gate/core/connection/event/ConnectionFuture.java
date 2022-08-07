package xyz.izgnod.iim.gate.core.connection.event;

/**
 * ConnectionFuture
 */
public interface ConnectionFuture {

    /**
     * 是否执行完成
     *
     * @return
     */
    boolean isDone();

    /**
     * 是否执行成功
     *
     * @return
     */
    boolean isSuccess();

    /**
     * 失败原因
     *
     * @return
     */
    Throwable getCause();

    /**
     * 设置成功
     */
    void setSuccess();

    /**
     * 设置失败
     *
     * @param cause
     */
    void setFailure(Throwable cause);

    /**
     * 添加监听者
     *
     * @param listener
     */
    void addListener(ConnectionFutureListener listener);
}

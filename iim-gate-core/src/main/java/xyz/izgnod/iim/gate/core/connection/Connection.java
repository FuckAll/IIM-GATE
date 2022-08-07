package xyz.izgnod.iim.gate.core.connection;

import xyz.izgnod.iim.gate.core.pipeline.MessagePipeline;
import xyz.izgnod.iim.gate.protocol.tcp.GateMessage;

/**
 * abstract tcp and websocket
 */
public interface Connection {
    /**
     * connection
     *
     * @return
     */
    boolean isOk();

    /**
     * Write To Connection
     * @param gateMessage
     * @param logId
     */
    void write(GateMessage gateMessage, String logId);

    /**
     * Close Connection
     */
    void close();

    /**
     * SessionId
     *
     * @return
     */
    String getSessionId();

    /**
     * RemoteIP
     *
     * @return
     */
    String getRemoteIp();

    /**
     * RemotePort
     *
     * @return
     */
    int getRemotePort();


    /**
     * 获取处理当前连接的pipeline
     *
     * @return
     */
    MessagePipeline getPipeline();

    /**
     * 获取密钥
     *
     * @return
     */
    String getSecret();

    /**
     * 设置密钥，链路加密使用，与当前连接绑定
     *
     * @param secret
     */
    void setSecret(String secret);

    /**
     * 获取自定义数据
     *
     * @param key
     * @return
     */
    String getAttachment(String key);

    /**
     * 设置自定义数据
     *
     * @param key
     * @param value
     */
    void setAttachment(String key, String value);
}
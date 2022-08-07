package xyz.izgnod.iim.gate.core.session;

import xyz.izgnod.iim.gate.core.connection.Connection;

public interface Session {

    /**
     * 获取创建时间
     *
     * @return
     */
    long getCreateTime();

    /**
     * 设置更新时间
     *
     * @param updateTime
     */
    void setUpdateTime(long updateTime);

    /**
     * 获取更新时间
     *
     * @return
     */
    long getUpdateTime();

    /**
     * 设置是否合法
     *
     * @param valid
     */
    void setValid(boolean valid);

    /**
     * 是否合法
     *
     * @return
     */
    boolean isValid();

    /**
     * 设置sessionId
     *
     * @param sessionId
     */
    void setSessionId(String sessionId);

    /**
     * 获取sessionId
     *
     * @return
     */
    String getSessionId();


    /**
     * 设置connection
     *
     * @param connection
     */
    void setConnection(Connection connection);

    /**
     * 获取connection
     *
     * @return
     */
    Connection getConnection();

    /**
     * 关闭
     * <p>
     * <p>
     * 主要情况如下:
     * 1.客户端退出，这时候关闭peer，通知logic
     * 2.服务端kickout，关闭即可
     * 3.遇到异常，关闭peer，通知logic
     *
     * @param closeReasonEnum
     * @param logId
     */
    void close(SessionCloseReasonEnum closeReasonEnum, String logId);

    /**
     * 添加监听者
     *
     * @param sessionListener
     */
    void addSessionListener(SessionListener sessionListener);

    /**
     * 获取用户信息
     *
     * @return
     */
    UserInfo getUserInfo();

    /**
     * 设置用户信息
     *
     * @param userInfo
     */
    void setUserInfo(UserInfo userInfo);

}

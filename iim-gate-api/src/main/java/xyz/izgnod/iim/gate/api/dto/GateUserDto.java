package xyz.izgnod.iim.gate.api.dto;

import java.io.Serializable;

public class GateUserDto implements Serializable {

    private static final long serialVersionUID = 7164278217995049095L;
    /**
     * appId,每个应用唯一
     */
    private String appId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户域，如果不需要就忽略，默认0
     */
    private int domain;
    /**
     * 客户端类型，如果不需要就忽略，默认0
     */
    private int clientType;
    /**
     * 这个字段，对逻辑层来说，就是gate层的连接id，代表一个唯一的连接
     */
    private String sessionId;

    public GateUserDto(String appId, String userId, String sessionId) {
        this.appId = appId;
        this.userId = userId;
        this.sessionId = sessionId;
    }

    public GateUserDto(String appId, String userId, int domain, int clientType, String sessionId) {
        this.appId = appId;
        this.userId = userId;
        this.domain = domain;
        this.clientType = clientType;
        this.sessionId = sessionId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getDomain() {
        return domain;
    }

    public void setDomain(int domain) {
        this.domain = domain;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "GateUserDto{" +
                "appId='" + appId + '\'' +
                ", userId='" + userId + '\'' +
                ", domain=" + domain +
                ", clientType=" + clientType +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}

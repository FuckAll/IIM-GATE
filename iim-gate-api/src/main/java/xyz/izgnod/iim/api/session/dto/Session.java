package xyz.izgnod.iim.api.session.dto;

import java.io.Serializable;

public class Session implements Serializable {

    private static final long serialVersionUID = 1L;
    private String appId;
    private String uid;
    private String domain;
    private String clientType;
    private String sessionId;
    private String deviceId;
    private Long lastPacketTime;

    private GateAddr gateAddr;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Long getLastPacketTime() {
        return lastPacketTime;
    }

    public void setLastPacketTime(Long lastPacketTime) {
        this.lastPacketTime = lastPacketTime;
    }

    public GateAddr getGateAddr() {
        return gateAddr;
    }

    public void setGateAddr(GateAddr gateAddr) {
        this.gateAddr = gateAddr;
    }

    public static class GateAddr {
        private String ip;
        private Integer port;
    }

    public static final class SessionBuilder {
        private String appId;
        private String uid;
        private String domain;
        private String clientType;
        private String sessionId;
        private String deviceId;
        private Long lastPacketTime;
        private GateAddr gateAddr;

        private SessionBuilder() {
        }

        public static SessionBuilder aSession() {
            return new SessionBuilder();
        }

        public SessionBuilder appId(String appId) {
            this.appId = appId;
            return this;
        }

        public SessionBuilder uid(String uid) {
            this.uid = uid;
            return this;
        }

        public SessionBuilder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public SessionBuilder clientType(String clientType) {
            this.clientType = clientType;
            return this;
        }

        public SessionBuilder sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public SessionBuilder deviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public SessionBuilder lastPacketTime(Long lastPacketTime) {
            this.lastPacketTime = lastPacketTime;
            return this;
        }

        public SessionBuilder gateAddr(GateAddr gateAddr) {
            this.gateAddr = gateAddr;
            return this;
        }

        public Session build() {
            Session session = new Session();
            session.setAppId(appId);
            session.setUid(uid);
            session.setDomain(domain);
            session.setClientType(clientType);
            session.setSessionId(sessionId);
            session.setDeviceId(deviceId);
            session.setLastPacketTime(lastPacketTime);
            session.setGateAddr(gateAddr);
            return session;
        }
    }
}

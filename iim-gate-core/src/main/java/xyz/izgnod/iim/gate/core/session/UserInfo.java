package xyz.izgnod.iim.gate.core.session;

public class UserInfo {

    /**
     * appId
     */
    private String appId;

    /**
     * user id
     */
    private String userId;

    /**
     * domain
     */
    private int domain;

    /**
     * client type
     */
    private int clientType;
    /**
     * client version
     */
    private int clientVersion;

    public String getAppId() {
        return appId;
    }

    public String getUserId() {
        return userId;
    }

    public int getDomain() {
        return domain;
    }

    public int getClientType() {
        return clientType;
    }

    public int getClientVersion() {
        return clientVersion;
    }

    private UserInfo(Builder builder) {
        appId = builder.appId;
        userId = builder.userId;
        domain = builder.domain;
        clientType = builder.clientType;
        clientVersion = builder.clientVersion;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(UserInfo copy) {
        Builder builder = new Builder();
        builder.appId = copy.getAppId();
        builder.userId = copy.getUserId();
        builder.domain = copy.getDomain();
        builder.clientType = copy.getClientType();
        builder.clientVersion = copy.getClientVersion();
        return builder;
    }


    public static final class Builder {
        private String appId;
        private String userId;
        private int domain;
        private int clientType;
        private int clientVersion;

        private Builder() {
        }

        public Builder appId(String val) {
            appId = val;
            return this;
        }

        public Builder userId(String val) {
            userId = val;
            return this;
        }

        public Builder domain(int val) {
            domain = val;
            return this;
        }

        public Builder clientType(int val) {
            clientType = val;
            return this;
        }

        public Builder clientVersion(int val) {
            clientVersion = val;
            return this;
        }

        public UserInfo build() {
            return new UserInfo(this);
        }
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "appId='" + appId + '\'' +
                ", userId='" + userId + '\'' +
                ", domain=" + domain +
                ", clientType=" + clientType +
                ", clientVersion=" + clientVersion +
                '}';
    }
}

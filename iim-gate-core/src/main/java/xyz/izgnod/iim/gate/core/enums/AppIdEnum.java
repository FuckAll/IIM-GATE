package xyz.izgnod.iim.gate.core.enums;


public enum AppIdEnum {

    /**
     * APPID
     */
    UNKNOWN("0000"),

    IMKF("1001"),
    ;

    /**
     * appId
     */
    private String appId;

    AppIdEnum(String appId) {
        this.appId = appId;
    }

    public String getAppId() {
        return appId;
    }
}
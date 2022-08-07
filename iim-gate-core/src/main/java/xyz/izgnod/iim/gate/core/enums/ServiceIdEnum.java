package xyz.izgnod.iim.gate.core.enums;

/**
 * @description 对依赖gate的所有服务端的编码
 */
public enum ServiceIdEnum {
    IMKF(4, "在线客服"),
    MSG_BOX(5, "消息盒子"),
    VIDEO_LIVE(6, "视频直播");

    private int serviceCode;
    private String description;

    ServiceIdEnum(int serviceCode, String description) {
        this.serviceCode = serviceCode;
        this.description = description;
    }

    public int getServiceCode() {
        return serviceCode;
    }

    public String getDescription() {
        return description;
    }
}

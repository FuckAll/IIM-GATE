package xyz.izgnod.iim.gate.core.enums;

/**
 * @description 定义gate的类型
 */
public enum GateTypeEnum {

    TCP(0),
    WEBSOCKET(1);

    private int code;

    GateTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}

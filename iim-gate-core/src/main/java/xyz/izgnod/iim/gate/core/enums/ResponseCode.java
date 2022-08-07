package xyz.izgnod.iim.gate.core.enums;

/**
 * @description 返回给客户端的code编码
 * 这个编码是gate特有的，其他业务线不可以与这重复
 */
public enum ResponseCode {
    AUTH_INVALID_PROTOCOLBUFFER(2010001, "Invalid ProtocolBuffer"),
    AUTH_CALLLOGIC_ERR(2010002, "Auth Call Logic err"),
    MESSAGE_DECRYPT_ERR(1010006, "Message解密失败"),
    MESSAGE_DECRYPT_ERR_KICKOUT(1010007, "解密失败"),
    SESSION_NOT_FOUND(1001, "session不存在，请重新认证");


    private int code;
    private String desp;

    ResponseCode(int code, String desp) {
        this.code = code;
        this.desp = desp;
    }

    public int getCode() {
        return this.code;
    }

    public String getDesp() {
        return this.desp;
    }


}

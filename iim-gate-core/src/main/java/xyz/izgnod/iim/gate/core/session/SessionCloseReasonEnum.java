package xyz.izgnod.iim.gate.core.session;

public enum SessionCloseReasonEnum {

    ERROR(1, "出现异常，错误"),
    CHANNEL_CLOSE(2, "连接已经断开"),
    TIMEOUT(3, "心跳超时"),
    LOG_OUT(4, "客户端主动退出"),
    KICK_OUT(5, "被踢出，一般是logic层发出踢人请求");

    private int code;
    private String desc;

    SessionCloseReasonEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}

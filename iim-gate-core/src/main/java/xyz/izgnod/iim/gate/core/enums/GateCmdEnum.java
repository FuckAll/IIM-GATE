package xyz.izgnod.iim.gate.core.enums;

/**
 * @description 定义gate层的cmd命令号
 */
public enum GateCmdEnum {

    DEFAULT(0, "默认"),
    AUTH(1001, "登录"),
    LOG_OUT(1002, "退出"),
    KEEP_ALIVE(6, "心跳处理"),
    KICK_OUT(1003, "gate踢人"),
    HEARTBEAT(7, "心跳");

    private int cmdId;
    private String desc;

    GateCmdEnum(int cmdId, String desc) {
        this.cmdId = cmdId;
        this.desc = desc;
    }

    public int getCmdId() {
        return cmdId;
    }

    public String getDesc() {
        return desc;
    }


    /**
     * 分组
     *
     * @param cmdId
     * @return
     */
    public static int group(int cmdId) {
        if (cmdId == AUTH.getCmdId()) {
            return AUTH.getCmdId();
        } else if (cmdId == LOG_OUT.getCmdId()) {
            return LOG_OUT.getCmdId();
        } else if (cmdId == KEEP_ALIVE.getCmdId()) {
            return KEEP_ALIVE.getCmdId();
        } else {
            return DEFAULT.getCmdId();
        }
    }


}

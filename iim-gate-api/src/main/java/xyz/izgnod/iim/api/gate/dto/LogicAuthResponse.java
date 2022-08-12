package xyz.izgnod.iim.api.gate.dto;

import java.io.Serializable;

public class LogicAuthResponse implements Serializable {

    private static final long serialVersionUID = 7915929894728544411L;

    /**
     * 定义成功或失败
     */
    private Status status;
    /**
     * 失败时的错误编码
     */
    private int code;
    /**
     * 状态描述
     */
    private String msg;
    /**
     * 额外信息。透传给客户端。
     * 业务方将业务数据，返回给客户端
     */
    private String extra;

    public LogicAuthResponse() {

    }

    private LogicAuthResponse(Builder builder) {
        setStatus(builder.status);
        setCode(builder.code);
        setMsg(builder.msg);
        setExtra(builder.extra);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public boolean isOK() {
        return this.status == Status.OK;
    }

    public enum Status {
        OK(0),
        ERR(-1);

        private int value;

        Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    public static final class Builder {
        private Status status;
        private int code;
        private String msg;
        private String extra;

        private Builder() {
        }

        public Builder status(Status val) {
            status = val;
            return this;
        }

        public Builder code(int val) {
            code = val;
            return this;
        }

        public Builder msg(String val) {
            msg = val;
            return this;
        }

        public Builder extra(String val) {
            extra = val;
            return this;
        }

        public LogicAuthResponse build() {
            return new LogicAuthResponse(this);
        }
    }
}

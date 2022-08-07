package xyz.izgnod.iim.gate.protocol.tcp;

import java.io.Serializable;


/**
  +-----------------------+------------------------+----------------+--------------+----------------------+---------+
  | HeaderLength(4 bytes) | clientVersion(4 bytes) | cmdId(4 bytes) | seq(4 bytes) | bodyLength (4 bytes) |   body  |
  | 20                    | 10                     |   10           |  1000010001  |     11               | "a,b,c" |
  +-----------------------+------------------------+----------------+--------------+----------------------+---------+
*/
public class MsgHeader implements Serializable {

    private static final long serialVersionUID = -216530754674698610L;

    public static final int HEADER_LENGTH = 20;

    private int headLength;
    private int clientVersion;
    private int cmdId;
    private int seq;
    private int bodyLength;

    private MsgHeader() {
    }

    private MsgHeader(Builder builder) {
        setHeadLength(builder.headLength);
        setClientVersion(builder.clientVersion);
        setCmdId(builder.cmdId);
        setSeq(builder.seq);
        setBodyLength(builder.bodyLength);
    }

    /**
     * 构造函数
     *
     * @return
     */
    public static MsgHeader newMsgHeader() {
        return new MsgHeader();
    }

    public static MsgHeader newMsgHeader(MsgHeader copy) {
        MsgHeader header = new MsgHeader();
        header.setHeadLength(copy.getHeadLength());
        header.setClientVersion(copy.getClientVersion());
        header.setCmdId(copy.getCmdId());
        header.setSeq(copy.getSeq());
        header.setBodyLength(copy.getBodyLength());
        return header;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(MsgHeader copy) {
        Builder builder = new Builder();
        builder.headLength = copy.getHeadLength();
        builder.clientVersion = copy.getClientVersion();
        builder.cmdId = copy.getCmdId();
        builder.seq = copy.getSeq();
        builder.bodyLength = copy.getBodyLength();
        return builder;
    }

    /**
     * 解析消息流，得到消息头内容
     *
     * @param msg
     * @return
     */
    public boolean decode(byte[] msg) {
        if (msg == null || msg.length <= 0) {
            throw new IllegalArgumentException("msg byte array is null");
        }

        if (msg.length < HEADER_LENGTH) {
            throw new IllegalArgumentException("msg length is less than header length : " + msg.length);
        }

        int offset = 0;
        this.headLength = BitConverter.byteArrayToInt(msg, offset);

        offset += 4; // Integer.SIZE / Byte.SIZE;
        this.clientVersion = BitConverter.byteArrayToInt(msg, offset);

        offset += 4; // Integer.SIZE / Byte.SIZE;
        this.cmdId = BitConverter.byteArrayToInt(msg, offset);

        offset += 4; // Integer.SIZE / Byte.SIZE;
        this.seq = BitConverter.byteArrayToInt(msg, offset);

        offset += 4; // Integer.SIZE / Byte.SIZE;
        this.bodyLength = BitConverter.byteArrayToInt(msg, offset);

        return true;
    }

    public byte[] encode() {
        byte[] headLengthBytes = BitConverter.intToByteArray(this.headLength);
        byte[] clientVersionBytes = BitConverter.intToByteArray(this.clientVersion);
        byte[] cmdIdBytes = BitConverter.intToByteArray(this.cmdId);
        byte[] seqBytes = BitConverter.intToByteArray(this.seq);
        byte[] bodyLengthBytes = BitConverter.intToByteArray(this.bodyLength);

        return BitConverter.concat(headLengthBytes, clientVersionBytes, cmdIdBytes, seqBytes, bodyLengthBytes);
    }

    public int getHeadLength() {
        return headLength;
    }

    public void setHeadLength(int headLength) {
        this.headLength = headLength;
    }

    public int getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(int clientVersion) {
        this.clientVersion = clientVersion;
    }

    public int getCmdId() {
        return cmdId;
    }

    public void setCmdId(int cmdId) {
        this.cmdId = cmdId;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(int bodyLength) {
        this.bodyLength = bodyLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MsgHeader that = (MsgHeader) o;
        if (this.headLength == that.headLength && this.clientVersion == that.clientVersion && this.seq == that.seq
                && this.cmdId == that.cmdId && this.bodyLength == that.bodyLength) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MsgHeader[headLength=").append(this.headLength).append(",clientVersion=").append(this.clientVersion);
        sb.append(",cmdId=").append(this.cmdId).append(",seq=").append(this.seq).append(",bodyLength=").append(this.bodyLength).append("]");

        return sb.toString();
    }


    public static final class Builder {
        private int headLength;
        private int clientVersion;
        private int cmdId;
        private int seq;
        private int bodyLength;

        private Builder() {
        }

        public Builder headLength(int val) {
            headLength = val;
            return this;
        }

        public Builder clientVersion(int val) {
            clientVersion = val;
            return this;
        }

        public Builder cmdId(int val) {
            cmdId = val;
            return this;
        }

        public Builder seq(int val) {
            seq = val;
            return this;
        }

        public Builder bodyLength(int val) {
            bodyLength = val;
            return this;
        }

        public MsgHeader build() {
            return new MsgHeader(this);
        }
    }
}

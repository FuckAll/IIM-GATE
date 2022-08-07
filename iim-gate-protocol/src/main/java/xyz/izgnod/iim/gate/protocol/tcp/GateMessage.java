package xyz.izgnod.iim.gate.protocol.tcp;

import java.io.Serializable;

/**
 * GateMessage
 */
public class GateMessage implements Serializable {
    private static final long serialVersionUID = 1380398756418802143L;
    /**
     * MsgHeader
     */
    private MsgHeader header;

    /**
     * body
     */
    private byte[] body;

    public static GateMessage create(MsgHeader header, byte[] body) {
        return new GateMessage(header, body);
    }

    private GateMessage() {
    }

    private GateMessage(MsgHeader header, byte[] body) {
        this.header = header;
        this.body = body;

        if (body != null) {
            this.header.setBodyLength(body.length);
        } else {
            this.header.setBodyLength(0);
        }
    }

    public static GateMessage streamToMessage(byte[] msg) {
        if (msg == null || msg.length <= 0) {
            throw new IllegalArgumentException("msg byte array is null");
        }

        MsgHeader header = MsgHeader.newMsgHeader();
        header.decode(msg);

        if (header.getBodyLength() + MsgHeader.HEADER_LENGTH != msg.length) {
            throw new IllegalArgumentException("wrong body length");
        }

        byte[] body = new byte[header.getBodyLength()];

        System.arraycopy(msg, MsgHeader.HEADER_LENGTH, body, 0, header.getBodyLength());

        return new GateMessage(header, body);
    }

    public byte[] toByteArray() {
        return BitConverter.concat(header.encode(), this.body);
    }

    public MsgHeader getHeader() {
        return header;
    }

    public void setHeader(MsgHeader header) {
        this.header = header;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
        if (body != null) {
            this.header.setBodyLength(body.length);
        } else {
            this.header.setBodyLength(0);
        }
    }

    @Override
    public String toString() {
        return "GateMessage{" +
                "header=" + header +
                '}';
    }
}

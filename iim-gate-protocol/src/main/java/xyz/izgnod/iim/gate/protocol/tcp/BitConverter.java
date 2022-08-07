package xyz.izgnod.iim.gate.protocol.tcp;

public class BitConverter {
    // | 表示按位或

    public static int byteArrayToInt(byte[] bytes, int offset) {
        if (bytes == null || bytes.length <= 0) {
            throw new IllegalArgumentException("bytes array is null");
        }
        return fromBytes(bytes[offset], bytes[1 + offset], bytes[2 + offset], bytes[3 + offset]);
    }

    public static int fromBytes(byte b1, byte b2, byte b3, byte b4) {
        return b1 << 24 | (b2 & 0xFF) << 16 | (b3 & 0xFF) << 8 | (b4 & 0xFF);
    }

    public static byte[] intToByteArray(int i) {
        return new byte[]{(byte) (i >> 24), (byte) (i >> 16), (byte) (i >> 8), (byte) i};
    }

    public static byte[] concat(byte[]... arrays) {
        int length = 0;
        for (byte[] array : arrays) {
            if (null == array) {
                continue;
            }
            length += array.length;
        }
        byte[] result = new byte[length];
        int pos = 0;
        for (byte[] array : arrays) {
            if (null == array) {
                continue;
            }
            System.arraycopy(array, 0, result, pos, array.length);
            pos += array.length;
        }
        return result;
    }
}

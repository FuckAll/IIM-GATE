package xyz.izgnod.iim.gate.core.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class GateNetUtils {

    private final static Logger LOG = LoggerFactory.getLogger(GateNetUtils.class);

    /**
     * 获取ip
     *
     * @param socketAddress
     * @return ip
     */
    public static String getIp(SocketAddress socketAddress) {

        String ip = "";
        if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
            InetAddress address = inetSocketAddress.getAddress();
            ip = (address == null ? inetSocketAddress.getHostName() : address.getHostAddress());
        }
        return ip;
    }

    /**
     * 获取端口
     *
     * @param socketAddress
     * @return port
     */
    public static int getPort(SocketAddress socketAddress) {
        int port = 0;

        if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
            port = inetSocketAddress.getPort();
        }
        return port;
    }

    /**
     * 获取地址，格式ip:port
     *
     * @param socketAddress
     * @return ip:port
     */
    public static String getAddress(SocketAddress socketAddress) {

        if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
            return getIp(inetSocketAddress) + ":" + getPort(inetSocketAddress);
        }
        return "";
    }

}
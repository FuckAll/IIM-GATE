package xyz.izgnod.iim.gate.core.connection;

import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.izgnod.iim.gate.core.cmd.DownCmdEvent;
import xyz.izgnod.iim.gate.core.pipeline.MessagePipeline;
import xyz.izgnod.iim.gate.protocol.tcp.GateMessage;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractNettyConnection implements Connection {
    public static final Logger LOG = LoggerFactory.getLogger(AbstractNettyConnection.class);
    protected final Channel channel;
    private final String sessionId;
    private final String remoteIp;
    private final int remotePort;
    private final MessagePipeline messagePipeline;
    private String secret;
    private final AtomicBoolean close = new AtomicBoolean();
    private final ConcurrentMap<String, String> map = new ConcurrentHashMap<>();

    public AbstractNettyConnection(Channel channel, String sessionId, String remoteIp, int remotePort, MessagePipeline messagePipeline) {
        this.channel = channel;
        this.sessionId = sessionId;
        this.remoteIp = remoteIp;
        this.remotePort = remotePort;
        this.messagePipeline = messagePipeline;
    }

    @Override
    public void write(GateMessage gateMessage, String logId) {
        if (!isOk()) {
            LOG.warn("connection is not ok,logId={}", logId);
            return;
        }
        if (gateMessage == null) {
            LOG.warn("gateMessage is null,logId={}", logId);
            return;
        }
        DownCmdEvent cmdEvent = new DownCmdEvent(this, gateMessage, logId);
        getPipeline().sendDown(cmdEvent);
    }

    @Override
    public boolean isOk() {
        return !close.get() && channel.isActive();
    }

    @Override
    public void close() {
        if (close.compareAndSet(false, true)) {
            channel.close();
            LOG.debug("connection close,connection={}", toString());
        }
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public String getRemoteIp() {
        return remoteIp;
    }

    @Override
    public int getRemotePort() {
        return remotePort;
    }

    @Override
    public MessagePipeline getPipeline() {
        return messagePipeline;
    }

    @Override
    public String getSecret() {
        return secret;
    }

    @Override
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String getAttachment(String key) {
        return map.get(key);
    }

    @Override
    public void setAttachment(String key, String value) {
        map.put(key, value);
    }

    /**
     *
     * @param downCmdEvent
     */
    public abstract void write2Client(DownCmdEvent downCmdEvent);

    @Override
    public String toString() {
        return "AbstractNettyConnection{" +
                "channel=" + channel +
                ", sessionId='" + sessionId + '\'' +
                ", remoteIp='" + remoteIp + '\'' +
                ", remotePort=" + remotePort +
                ", secret='" + secret + '\'' +
                ", close=" + close +
                ", map=" + map +
                '}';
    }
}

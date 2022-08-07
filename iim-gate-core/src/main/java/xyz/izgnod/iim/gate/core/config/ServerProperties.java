package xyz.izgnod.iim.gate.core.config;

public class ServerProperties {
    private int port;
    private int frameMaxLength;
    private int readTimeoutSeconds;
    private int expireSeconds;
    private boolean benchmark;
    private int rateLimitPerSecond;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getFrameMaxLength() {
        return frameMaxLength;
    }

    public void setFrameMaxLength(int frameMaxLength) {
        this.frameMaxLength = frameMaxLength;
    }

    /**
     * 最大帧预警值，小于这个值的，会打印出head，来当做提醒
     * 大于这个值的，直接抛弃
     */
    public int getFrameMaxLengthAlert() {
        return frameMaxLength * 10;
    }

    public int getReadTimeoutSeconds() {
        return readTimeoutSeconds;
    }

    public void setReadTimeoutSeconds(int readTimeoutSeconds) {
        this.readTimeoutSeconds = readTimeoutSeconds;
    }

    public int getRateLimitPerSecond() {
        return rateLimitPerSecond;
    }

    public void setRateLimitPerSecond(int rateLimitPerSecond) {
        this.rateLimitPerSecond = rateLimitPerSecond;
    }

    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public boolean isBenchmark() {
        return benchmark;
    }

    public void setBenchmark(boolean benchmark) {
        this.benchmark = benchmark;
    }
}

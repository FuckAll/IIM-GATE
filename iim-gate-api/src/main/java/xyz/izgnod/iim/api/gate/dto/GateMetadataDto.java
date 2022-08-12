package xyz.izgnod.iim.api.gate.dto;

import java.io.Serializable;

public class GateMetadataDto implements Serializable {

    private static final long serialVersionUID = 8442579927565465153L;

    private String dubboIp;
    private int dubboPort;
    private int gateType;

    public GateMetadataDto() {
    }

    public GateMetadataDto(String dubboIp, int dubboPort, int gateType) {
        this.dubboIp = dubboIp;
        this.dubboPort = dubboPort;
        this.gateType = gateType;
    }

    public String getDubboIp() {
        return dubboIp;
    }

    public void setDubboIp(String dubboIp) {
        this.dubboIp = dubboIp;
    }

    public int getDubboPort() {
        return dubboPort;
    }

    public void setDubboPort(int dubboPort) {
        this.dubboPort = dubboPort;
    }

    public int getGateType() {
        return gateType;
    }

    public void setGateType(int gateType) {
        this.gateType = gateType;
    }

    @Override
    public String toString() {
        return "GateMetadataDto{" +
                "dubboIp='" + dubboIp + '\'' +
                ", dubboPort=" + dubboPort +
                ", gateType=" + gateType +
                '}';
    }
}

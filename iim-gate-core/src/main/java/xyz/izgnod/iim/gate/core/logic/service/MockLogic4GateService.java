package xyz.izgnod.iim.gate.core.logic.service;

import xyz.izgnod.iim.gate.api.dto.GateMetadataDto;
import xyz.izgnod.iim.gate.api.dto.GateUserDto;
import xyz.izgnod.iim.gate.api.dto.LogicAuthResponse;
import xyz.izgnod.iim.gate.api.service.Logic4GateService;
import xyz.izgnod.iim.gate.protocol.tcp.GateMessage;

public class MockLogic4GateService implements Logic4GateService {
    @Override
    public LogicAuthResponse auth(GateUserDto gateUserDto, GateMessage gateMessage, GateMetadataDto gateMetadataDto, String logId) {
        return null;
    }

    @Override
    public void keepAlive(GateUserDto gateUserDto, GateMetadataDto gateMetadataDto, String logId) {

    }

    @Override
    public void logout(GateUserDto gateUserDto, GateMetadataDto gateMetadataDto, String logId) {

    }

    @Override
    public void sendMessage(GateUserDto gateUserDto, GateMessage gateMessage, GateMetadataDto gateMetadataDto, String logId) {

    }
}

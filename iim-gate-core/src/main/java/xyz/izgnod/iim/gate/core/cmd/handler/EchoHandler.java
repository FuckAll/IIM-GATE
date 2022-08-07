package xyz.izgnod.iim.gate.core.cmd.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.izgnod.iim.gate.core.cmd.CmdEvent;
import xyz.izgnod.iim.gate.protocol.tcp.GateMessage;


public class EchoHandler implements CmdHandler {

    private static final Logger LOG = LoggerFactory.getLogger(EchoHandler.class);

    @Override
    public void handleMessage(CmdEvent cmdEvent) throws Exception {

        LOG.debug("echo dispatch");

        GateMessage gateMessage = cmdEvent.getGateMessage();
        byte[] body = gateMessage.getBody();
        LOG.debug("body size={}", body.length);

        //返回响应结果
        cmdEvent.getConnection().write(gateMessage, cmdEvent.getLogId());
    }

}

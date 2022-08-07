package xyz.izgnod.iim.gate.core.cmd.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.izgnod.iim.gate.core.cmd.CmdEvent;
import xyz.izgnod.iim.gate.core.cmd.GateCmdEnum;
import xyz.izgnod.iim.gate.core.cmd.UpCmdEvent;
import xyz.izgnod.iim.gate.protocol.tcp.GateMessage;
import xyz.izgnod.iim.gate.protocol.tcp.MsgHeader;

import java.util.HashMap;
import java.util.Map;

public class AbstractHandlerProxy implements HandlerProxy {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractHandlerProxy.class);

    private final Map<Integer, CmdHandler> handlerMap = new HashMap<>();

    /**
     * 注册handler
     *
     * @param cmdId
     * @param cmdHandler
     */
    protected void register(Integer cmdId, CmdHandler cmdHandler) {
        handlerMap.put(cmdId, cmdHandler);
    }

    @Override
    public void handleMessage(CmdEvent cmdEvent) throws Exception {

        try {
            if (cmdEvent instanceof UpCmdEvent) {
                GateMessage gateMessage = cmdEvent.getGateMessage();
                MsgHeader header = gateMessage.getHeader();

                LOG.info("handle message,logId={},threadPool delay {}ms", cmdEvent.getLogId(), System.currentTimeMillis() - cmdEvent.getStartTime());
                CmdHandler cmdHandler = handlerMap.get(header.getCmdId());
                if (cmdHandler != null) {
                    //自己处理
                    cmdHandler.handleMessage(cmdEvent);
                } else {
                    // 不需要gate处理的命令，透传给logic
                    handlerMap.get(GateCmdEnum.DEFAULT.getCmdId()).handleMessage(cmdEvent);
                }
            }
        } catch (Exception e) {
            LOG.error("", e);
            //监控使用
            throw e;
        }
    }
}

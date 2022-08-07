package xyz.izgnod.iim.gate.core.cmd.handler;

import xyz.izgnod.iim.gate.core.cmd.CmdEvent;


public interface HandlerProxy {

    /**
     * 处理业务逻辑
     *
     * @param cmdEvent
     * @throws Exception
     */
    void handleMessage(CmdEvent cmdEvent) throws Exception;
}

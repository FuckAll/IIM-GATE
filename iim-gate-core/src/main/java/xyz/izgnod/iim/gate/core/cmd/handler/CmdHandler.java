package xyz.izgnod.iim.gate.core.cmd.handler;


import xyz.izgnod.iim.gate.core.cmd.CmdEvent;


public interface CmdHandler {
    void handleMessage(CmdEvent cmdEvent) throws Exception;
}

package xyz.izgnod.iim.logic.store.service;

import xyz.izgnod.iim.api.gate.service.LogicStoreService;

public class LogicStoreServiceImpl implements LogicStoreService {
    @Override
    public Long getMaxServerSeq(String chatId) {
        return 10L;
    }
}

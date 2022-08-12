package xyz.izgnod.iim.api.gate.service;

/**
 * Logic存储层
 */
public interface LogicStoreService {
    /**
     * get max server seq
     *
     * @param chatId
     * @return
     */
    Long getMaxServerSeq(String chatId);
}

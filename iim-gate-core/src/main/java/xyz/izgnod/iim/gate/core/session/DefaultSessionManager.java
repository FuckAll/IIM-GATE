package xyz.izgnod.iim.gate.core.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.izgnod.iim.gate.core.component.ProxySelectorComponent;
import xyz.izgnod.iim.gate.core.config.ServerProperties;
import xyz.izgnod.iim.gate.core.connection.Connection;
import xyz.izgnod.iim.gate.core.connection.event.ConnectionStateEvent;
import xyz.izgnod.iim.gate.core.task.GateThreadFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DefaultSessionManager implements SessionManager {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultSessionManager.class);

    private Map<String, Session> sessionMap = new ConcurrentHashMap<>();
    private ServerProperties serverProperties;
    private ProxySelectorComponent proxySelectorComponent;
    private static final ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(1,
            new GateThreadFactory("SessionManager-check", true));

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5,
            10, TimeUnit.MINUTES, new LinkedBlockingDeque<>(),
            new GateThreadFactory("SessionManager-biz", true));

    public DefaultSessionManager(ServerProperties serverProperties, ProxySelectorComponent proxySelectorComponent) {
        this.serverProperties = serverProperties;
        this.proxySelectorComponent = proxySelectorComponent;
        /**
         * 定时检测
         */
        scheduled.scheduleWithFixedDelay(new CheckSessionTask(), 10, 10, TimeUnit.SECONDS);
    }

    @Override
    public void auth(Session session) {
        Session oldSession = getSession(session.getSessionId());
        if (oldSession != null) {
            handelOldSession(session);

        }
        sessionMap.put(session.getSessionId(), session);
        LOG.info("session save success,session={}", session.toString());
    }

    public void handelOldSession(Session session) {
        // TODO how to handle ?
    }

    @Override
    public void keepAlive(String sessionId) {
        Session session = sessionMap.get(sessionId);
        if (session == null) {
            return;
        }
        session.setUpdateTime(System.currentTimeMillis());
    }

    @Override
    public void logOut(String sessionId) {
        // async close session
        closeSession(sessionId, SessionCloseReasonEnum.LOG_OUT, null);
    }

    private void closeSession(String sessionId, SessionCloseReasonEnum reasonEnum, Connection connection) {

        //session已经建立了，直接移除
        Session session = sessionMap.remove(sessionId);
        if (session != null) {

            LOG.info("closeSession session={},reason={}", session.toString(), reasonEnum.name());
            //关闭时统计
//            recordClose(reasonEnum, session);
            //异步执行关闭方法
            asyncSessionClose(session, reasonEnum);

        } else {
            //确保关闭连接
            if (connection != null) {
                connection.close();
            }
        }
    }

    private void asyncSessionClose(Session session, SessionCloseReasonEnum reasonEnum) {
        // TODO 关闭
//        executor.execute(new TraceRunnable(tracing, new CloseTask(session, reasonEnum), "CloseTask"));
    }

    @Override
    public Session getSession(String sessionId) {
        return null;
    }

    @Override
    public void stateChange(ConnectionStateEvent stateEvent) {

    }

    @Override
    public void kickOut(String sessionId) {

    }

    /**
     * 检测session可用性
     * 1.判断连接是否可用
     */
    private class CheckSessionTask implements Runnable {
        @Override
        public void run() {
            try {
                Map<String, Session> map = new HashMap<>(sessionMap);
                Map<String, Integer> countMap = new HashMap<>();
                LOG.info("session size={}", map.size());
                for (Map.Entry<String, Session> entry : map.entrySet()) {
                    Session session = entry.getValue();
                    if (!session.getConnection().isOk()) {
                        //连接已经关闭
                        closeSession(session.getSessionId(), SessionCloseReasonEnum.CHANNEL_CLOSE, session.getConnection());
                        continue;
                    }

                    Integer count = countMap.computeIfAbsent(session.getUserInfo().getAppId(), k -> 0);
                    count++;
                    countMap.put(session.getUserInfo().getAppId(), count);
                }
                // TODO 统计在线用户数
//
//                //更新在线用户
//                for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
//                    recordOnlineUser(entry.getKey(), entry.getValue());
//                }

            } catch (Exception e) {
                LOG.error("", e);
            }
        }
    }
}

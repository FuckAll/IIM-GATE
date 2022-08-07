package xyz.izgnod.iim.gate.core.session;


import xyz.izgnod.iim.gate.core.connection.event.ConnectionStateEvent;

public interface SessionManager {

    /**
     * auth
     *
     * @param session
     */
    void auth(Session session);

    /**
     * keep-alive
     *
     * @param sessionId
     */
    void keepAlive(String sessionId);

    /**
     * logout
     *
     * @param sessionId
     */
    void logOut(String sessionId);

    /**
     * if valid is false, return null;
     * connection valid return null;
     * keep-alive timeout return null;
     * netty closed return null;
     *
     * @param sessionId
     * @return valid session
     */
    Session getSession(String sessionId);


    /**
     * connection state change
     *
     * @param stateEvent
     */
    void stateChange(ConnectionStateEvent stateEvent);


    /**
     * kick out
     *
     * @param sessionId
     */
    void kickOut(String sessionId);
}

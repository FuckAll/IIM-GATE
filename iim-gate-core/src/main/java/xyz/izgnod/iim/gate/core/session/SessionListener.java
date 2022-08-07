package xyz.izgnod.iim.gate.core.session;

import java.util.EventListener;

public interface SessionListener extends EventListener {

    /**
     * session失效时，触发
     *
     * @param sessionCloseEvent
     */
    default void sessionDestroyed(SessionCloseEvent sessionCloseEvent) {
    }

    ;
}

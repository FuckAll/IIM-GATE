package xyz.izgnod.iim.gate.core.session;

import java.util.EventObject;

public class SessionCloseEvent extends EventObject {


    private static final long serialVersionUID = -7846245966979031307L;

    private SessionCloseReasonEnum sessionCloseReasonEnum;
    private String logId;

    public SessionCloseEvent(Object source, SessionCloseReasonEnum sessionCloseReasonEnum, String logId) {
        super(source);
        this.sessionCloseReasonEnum = sessionCloseReasonEnum;
        this.logId = logId;
    }

    public Session getSession() {
        return (Session) super.getSource();
    }

    public SessionCloseReasonEnum getSessionCloseReasonEnum() {
        return sessionCloseReasonEnum;
    }

    public String getLogId() {
        return logId;
    }
}

package xyz.izgnod.iim.gate.core.connection.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DefaultConnectionFuture implements ConnectionFuture {

    public static final Logger LOG = LoggerFactory.getLogger(DefaultConnectionFuture.class);

    /**
     * 通过synchronized来保证线程安全
     */
    private boolean done;
    /**
     * 失败原因
     */
    private Throwable cause;
    /**
     * 监听者，每个监听者只会执行一次
     */
    private List<ConnectionFutureListener> listenerList = null;

    @Override
    public synchronized boolean isDone() {
        return done;
    }

    @Override
    public synchronized boolean isSuccess() {
        return done && cause == null;
    }

    @Override
    public synchronized Throwable getCause() {
        return cause;
    }

    @Override
    public void setSuccess() {

        synchronized (this) {
            //确保只执行一次
            if (done) {
                return;
            }
            done = true;
        }

        notifyListeners();
    }


    @Override
    public void setFailure(Throwable cause) {
        synchronized (this) {
            //确保只执行一次
            if (done) {
                return;
            }
            this.cause = cause;
            done = true;
        }

        notifyListeners();
    }


    @Override
    public void addListener(ConnectionFutureListener listener) {
        if (listener == null) {
            throw new NullPointerException("listener");
        }

        boolean notifyNow = false;
        synchronized (this) {
            if (done) {
                notifyNow = true;
            } else {
                if (listenerList == null) {
                    listenerList = new ArrayList<>();
                }
                listenerList.add(listener);
            }
        }

        if (notifyNow) {
            notifyListener(listener);
        }
    }


    /**
     * 通知listener
     * 这个方法不需要加锁。
     * 都是在sync后调用，并且Arraylist在done之后，不会有任何更新
     * 实际所有的listener，都执行一次
     */
    private void notifyListeners() {
        if (listenerList != null) {
            for (ConnectionFutureListener listener : listenerList) {
                notifyListener(listener);
            }
        }
    }

    private void notifyListener(ConnectionFutureListener listener) {
        try {
            listener.operationComplete(this);
        } catch (Exception e) {
            LOG.error("", e);
        }

    }

}

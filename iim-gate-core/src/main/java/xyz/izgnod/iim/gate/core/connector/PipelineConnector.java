package xyz.izgnod.iim.gate.core.connector;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.izgnod.iim.gate.core.cmd.CmdEvent;
import xyz.izgnod.iim.gate.core.connection.event.ConnectionEvent;
import xyz.izgnod.iim.gate.core.connection.event.ConnectionStateEvent;
import xyz.izgnod.iim.gate.core.pipeline.MessagePipeline;
import xyz.izgnod.iim.gate.core.session.SessionManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PipelineConnector implements Connector {

    private static final Logger LOG = LoggerFactory.getLogger(PipelineConnector.class);

    private final MessagePipeline messagePipeline;
    private final ExecutorService executorService;
    private final SessionManager sessionManager;

    public PipelineConnector(MessagePipeline messagePipeline, ExecutorService executorService, SessionManager sessionManager) {
        this.messagePipeline = messagePipeline;
        this.executorService = executorService;
        this.sessionManager = sessionManager;

        //每5分钟打印一次线程池信息
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                LOG.info("executorService-info," + executorService.toString());
            }
        }, 5, 5, TimeUnit.MINUTES);
    }

    @Override
    public void process(ConnectionEvent connectionEvent) {

        if (connectionEvent instanceof ConnectionStateEvent) {

            sessionManager.stateChange((ConnectionStateEvent) connectionEvent);
        } else if (connectionEvent instanceof CmdEvent) {
            //提交线程池处理
            executorService.execute(new Runnable() {
                @Override
                public void run() {

                    try {
                        messagePipeline.sendUp((CmdEvent) connectionEvent);
                    } catch (Exception e) {
                        LOG.error("", e);
                    }
                }
            });
        }
    }

}

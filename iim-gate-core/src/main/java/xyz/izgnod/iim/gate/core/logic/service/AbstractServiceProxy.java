package xyz.izgnod.iim.gate.core.logic.service;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.izgnod.iim.api.gate.dto.GateMetadataDto;
import xyz.izgnod.iim.api.gate.dto.GateUserDto;
import xyz.izgnod.iim.api.gate.dto.LogicAuthResponse;
import xyz.izgnod.iim.api.gate.service.Logic4GateService;
import xyz.izgnod.iim.gate.core.cmd.CmdEvent;
import xyz.izgnod.iim.gate.core.config.ServerProperties;
import xyz.izgnod.iim.gate.core.connection.Connection;
import xyz.izgnod.iim.gate.core.enums.ResponseCode;
import xyz.izgnod.iim.gate.core.session.Session;
import xyz.izgnod.iim.gate.core.session.SessionCloseEvent;
import xyz.izgnod.iim.gate.core.session.SessionCloseReasonEnum;
import xyz.izgnod.iim.gate.protocol.protobuf.Auth;
import xyz.izgnod.iim.gate.protocol.tcp.GateMessage;
import xyz.izgnod.iim.gate.protocol.tcp.MsgHeader;

public abstract class AbstractServiceProxy implements ServiceProxy {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractServiceProxy.class);

    protected Logic4GateService logic4GateService;
    protected ServerProperties serverProperties;
    protected final GateMetadataDto gateMetadataDto;

    public AbstractServiceProxy(Logic4GateService logic4GateService,
                                ServerProperties serverProperties,
                                GateMetadataDto gateMetadataDto) {
        this.logic4GateService = logic4GateService;
        this.serverProperties = serverProperties;
        this.gateMetadataDto = gateMetadataDto;
    }

    @Override
    public Session auth(CmdEvent cmdEvent) {
        GateMessage gateMessage = cmdEvent.getGateMessage();
        Connection connection = cmdEvent.getConnection();
        Auth.AuthRequest request = parseAuthRequest(cmdEvent);
        if (request == null) {
            return null;
        }

        LogicAuthResponse response = null;
        try {
            GateUserDto userDto = new GateUserDto(request.getAppId(), request.getUid(), request.getDomain(),
                    request.getClientType(), connection.getSessionId());
            response = logic4GateService.auth(userDto, gateMessage, gateMetadataDto, cmdEvent.getLogId());
        } catch (Exception e) {
            LOG.error("auth error", e);
            GateMessage responseMessage = generateErrorResponse(gateMessage.getHeader(), ResponseCode.AUTH_CALLLOGIC_ERR.getCode(), ResponseCode.AUTH_CALLLOGIC_ERR.getDesp());
            connection.write(responseMessage, cmdEvent.getLogId());
            return null;
        }
        //处理auth结果
        return resolveCommonAuthResponse(request, response, cmdEvent);
    }

    private void logout(SessionCloseEvent sessionEvent) {

        if (checkNeedNotify(sessionEvent)) {
            Session session = sessionEvent.getSession();
            GateUserDto gateUserDto = new GateUserDto(session.getUserInfo().getAppId(), session.getUserInfo().getUserId(), session.getUserInfo().getDomain(), session.getUserInfo().getClientType(), session.getSessionId());
            logout(gateUserDto, sessionEvent.getLogId());
        }
    }


    @Override
    public void keepalive(Session session, CmdEvent cmdEvent) {
        keepalive(session, cmdEvent.getLogId());
    }

    private void keepalive(Session session, String logId) {
        logic4GateService.keepAlive(new GateUserDto(session.getUserInfo().getAppId(), session.getUserInfo().getUserId(), session.getUserInfo().getDomain(), session.getUserInfo().getClientType(), session.getSessionId()),
                gateMetadataDto, logId);
    }


    @Override
    public void logout(GateUserDto gateUserDto, String logId) {
        logic4GateService.logout(gateUserDto, gateMetadataDto, logId);
    }

    @Override
    public void sendMsg(Session session, CmdEvent cmdEvent) {
        GateMessage message = cmdEvent.getGateMessage();

        logic4GateService.sendMessage(new GateUserDto(session.getUserInfo().getAppId(), session.getUserInfo().getUserId(), session.getUserInfo().getDomain(), session.getUserInfo().getClientType(), session.getSessionId()), message,
                gateMetadataDto, cmdEvent.getLogId());
    }


    /**
     * 统一的auth返回结果处理
     *
     * @param request  auth请求
     * @param response auth结果
     * @param cmdEvent
     */
    public Session resolveCommonAuthResponse(Auth.AuthRequest request, LogicAuthResponse response, CmdEvent cmdEvent) {
//
//        ExchangeSession session = null;
//        Connection connection = cmdEvent.getConnection();
//
//        if (response.isOK()) {
//            LOG.info("auth success");
//
//            //用户信息
//            UserInfo userInfo = UserInfo.newBuilder().appId(request.getAppId()).userId(request.getUid()).domain(request.getDomain()).clientType(request.getClientType()).clientVersion(cmdEvent.getGateMessage().getHeader().getClientVersion()).build();
//            //构建session
//            session = ExchangeSession.newBuilder().valid(true)
//                    .connection(connection).sessionId(connection.getSessionId()).userInfo(userInfo).build();
//            //添加监听者
//            session.addSessionListener(new SessionListener() {
//                @Override
//                public void sessionDestroyed(SessionCloseEvent sessionCloseEvent) {
//                    logout(sessionCloseEvent);
//                }
//            });
//
//        } else {
//            //登录失败
//            LOG.warn("auth fail");
//        }
//
//        //将登录结果返回客户端
//        Auth.AuthResponse.Builder builder = Auth.AuthResponse.newBuilder().setStatus(response.getStatus().getValue()).setCode(response.getCode()).setMsg(response.getMsg()).setTimestamp(System.currentTimeMillis())
//                .setSessionId(connection.getSessionId());
//        if (response.getExtra() != null) {
//            builder.setExtra(response.getExtra());
//        }
//        Auth.AuthResponse authResponse = builder.build();
//        GateMessage respMessage = GateMessage.create(cmdEvent.getGateMessage().getHeader(), authResponse.toByteArray());
//        connection.write(respMessage, cmdEvent.getLogId());
//        return session;
        return null;
    }

    /**
     * 统一的解析请求request
     *
     * @param cmdEvent
     * @return
     */
    public Auth.AuthRequest parseAuthRequest(CmdEvent cmdEvent) {

        GateMessage gateMessage = cmdEvent.getGateMessage();
        Connection connection = cmdEvent.getConnection();
        Auth.AuthRequest request = null;
        try {
            request = Auth.AuthRequest.parseFrom(gateMessage.getBody());
        } catch (InvalidProtocolBufferException e) {
            LOG.error("authRequest parseFrom error", e);
            // 封装一个返回包给Client
            GateMessage responseMessage = generateErrorResponse(gateMessage.getHeader(), ResponseCode.AUTH_INVALID_PROTOCOLBUFFER.getCode(), ResponseCode.AUTH_INVALID_PROTOCOLBUFFER.getDesp());
            connection.write(responseMessage, cmdEvent.getLogId());

        }
        return request;
    }

    /**
     * 构造登录失败的返回包
     *
     * @return
     */
    public GateMessage generateErrorResponse(MsgHeader msgHeader, int code, String msg) {

        Auth.AuthResponse response = Auth.AuthResponse.newBuilder().setStatus(Auth.AuthResponse.Status.ERR_VALUE).
                setCode(code).setMsg(msg).setTimestamp(System.currentTimeMillis())
                .build();

        return GateMessage.create(msgHeader, response.toByteArray());
    }


    /**
     * 检测是否需要通知logic
     *
     * @param sessionEvent
     * @return
     */
    public boolean checkNeedNotify(SessionCloseEvent sessionEvent) {

        SessionCloseReasonEnum reasonEnum = sessionEvent.getSessionCloseReasonEnum();
        if (reasonEnum != SessionCloseReasonEnum.KICK_OUT && reasonEnum != SessionCloseReasonEnum.LOG_OUT) {
            return true;
        }
        return false;
    }
}



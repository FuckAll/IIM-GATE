package xyz.izgnod.iim.gate.tcp.component;

import org.springframework.stereotype.Component;
import xyz.izgnod.iim.gate.core.component.ProxySelectorComponent;

import javax.annotation.PostConstruct;

@Component
public class TcpProxySelectorComponent extends ProxySelectorComponent {
    /**
     * 客服
     */
//    @Resource
//    private ImkfServiceProxy imkfServiceProxy;

    /**
     * 注册路由表，根据serviceCode来选择调用哪个服务
     */
    @PostConstruct
    void register() {
//        super.register(ServiceIdEnum.IMKF.getServiceCode(), imkfServiceProxy);
//        super.register(ServiceIdEnum.MSG_BOX.getServiceCode(), messageBoxServiceProxy);
//        super.register(ServiceIdEnum.VIDEO_LIVE.getServiceCode(), videoLiveServiceProxy);
    }

}

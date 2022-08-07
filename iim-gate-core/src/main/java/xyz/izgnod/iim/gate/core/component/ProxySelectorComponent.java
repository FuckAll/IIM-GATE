package xyz.izgnod.iim.gate.core.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.izgnod.iim.gate.core.enums.ServiceIdEnum;
import xyz.izgnod.iim.gate.core.logic.service.ServiceProxy;

import java.util.HashMap;
import java.util.Map;

public class ProxySelectorComponent {

    private static final Logger LOG = LoggerFactory.getLogger(ProxySelectorComponent.class);

    private final Map<Integer, ServiceProxy> serviceProxyMap = new HashMap<>();

    /**
     * 注册服务代理
     *
     * @param serviceId
     * @param serviceProxy
     */
    protected void register(Integer serviceId, ServiceProxy serviceProxy) {
        serviceProxyMap.put(serviceId, serviceProxy);
    }

    /**
     * 服务代理选择
     *
     * @param appId
     * @param cmdId
     * @return
     */
    public ServiceProxy selectProxy(String appId, int cmdId) {
        // TODO 判断服务类型
        ServiceIdEnum serviceIdEnum = ServiceIdEnum.IMKF;
        return serviceProxyMap.get(serviceIdEnum.getServiceCode());
    }
}

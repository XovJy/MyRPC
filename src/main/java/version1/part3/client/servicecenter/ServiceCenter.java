package version1.part3.client.servicecenter;

import java.net.InetSocketAddress;

/**
 * @author xuehao
 */
public interface ServiceCenter {
    // 查询：根据服务名查找地址

    /**
     * 查询：根据服务名查找地址
     * @param serviceName 服务名
     * @return InetSocketAddress
     */
    InetSocketAddress serviceDiscovery(String serviceName);
}

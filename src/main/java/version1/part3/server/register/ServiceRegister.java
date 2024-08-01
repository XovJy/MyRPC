package version1.part3.server.register;


import java.net.InetSocketAddress;

/**
 * @author xuehao
 */
public interface ServiceRegister {
    /**
     * 注册：保存服务与地址
     * @param serviceName 服务名
     * @param serviceAddress 服务地址
     */
    void register(String serviceName, InetSocketAddress serviceAddress);
}

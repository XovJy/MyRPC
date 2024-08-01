package version1.part3.server.provider;

import version1.part3.server.register.ServiceRegister;
import version1.part3.server.register.impl.ZkServiceRegister;

import java.io.StringReader;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuehao
 */

public class ServiceProvider {

    private Map<String, Object> interfaceProvider;

    private int port;
    private String host;

    private ServiceRegister serviceRegister;

    public ServiceProvider(String host, int port){
        this.host = host;
        this.port = port;

        this.interfaceProvider=new HashMap<>();
        this.serviceRegister = new ZkServiceRegister();
    }

    public void provideServiceInterface(Object service) {
        String serviceName = service.getClass().getName();
        Class<?>[] interfaceName = service.getClass().getInterfaces();

        for (Class<?> clazz:interfaceName) {
            interfaceProvider.put(clazz.getName(), service);
            serviceRegister.register(clazz.getName(), new InetSocketAddress(host, port));
        }
    }

    public Object getService(String interfaceName) {
        return interfaceProvider.get(interfaceName);
    }
}

package version2.part1.server;

import version2.part1.common.service.UserService;
import version2.part1.common.service.UserServiceImpl;
import version2.part1.server.handler.RpcServer;
import version2.part1.server.handler.impl.SimpleRpcServer;
import version2.part1.server.provider.ServiceProvider;

/**
 * @author xuehao
 */
public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);

        RpcServer rpcServer = new SimpleRpcServer(serviceProvider);
        rpcServer.start(9999);
    }
}

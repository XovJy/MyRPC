package part1.server;

import part1.server.handler.RpcServer;
import part1.common.service.UserServiceImpl;
import part1.common.service.UserService;
import part1.server.handler.impl.SimpleRpcServer;
import part1.server.handler.impl.ThreadPoolRpcServer;
import part1.server.provider.ServiceProvider;

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

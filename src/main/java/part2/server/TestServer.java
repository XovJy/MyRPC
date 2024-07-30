package part2.server;

import part2.common.service.UserService;
import part2.common.service.UserServiceImpl;
import part2.server.handler.RpcServer;
import part2.server.handler.impl.NettyRpcServer;
import part2.server.handler.impl.SimpleRpcServer;
import part2.server.provider.ServiceProvider;

/**
 * @author xuehao
 */
public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);

        RpcServer rpcServer = new NettyRpcServer(serviceProvider);
        rpcServer.start(9999);
    }
}

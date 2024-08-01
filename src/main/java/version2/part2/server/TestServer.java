package version2.part2.server;

import version2.part2.common.service.UserService;
import version2.part2.common.service.UserServiceImpl;
import version2.part2.server.handler.RpcServer;
import version2.part2.server.handler.impl.NettyRpcServer;
import version2.part2.server.provider.ServiceProvider;

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

package part3.server;

import part3.common.service.UserService;
import part3.common.service.UserServiceImpl;
import part3.server.handler.RpcServer;
import part3.server.handler.impl.NettyRpcServer;
import part3.server.provider.ServiceProvider;

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

package version2.part2.server.handler.impl;

import version2.part2.server.handler.RpcServer;
import version2.part2.server.handler.work.WorkThread;
import version2.part2.server.provider.ServiceProvider;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author xuehao
 */

public class ThreadPoolRpcServer implements RpcServer {
    private final ThreadPoolExecutor threadPool;
    private ServiceProvider serviceProvider;

    public ThreadPoolRpcServer(ServiceProvider serviceProvider) {
        threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                1000,
                60, TimeUnit.SECONDS,new ArrayBlockingQueue<>(100));
        this.serviceProvider = serviceProvider;
    }

    public ThreadPoolRpcServer(ServiceProvider serviceProvider, int corePoolSize,
                               int maxPoolSize,
                               long keepAliveTime,
                               TimeUnit unit,
                               BlockingDeque<Runnable> workQueue) {
        threadPool = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, unit, workQueue);
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void start(int port) {
        System.out.println("服务启动了");
        try {
            ServerSocket serverSocket = new ServerSocket();
            while (true) {
                Socket socket = serverSocket.accept();
                threadPool.submit(new WorkThread(socket, serviceProvider));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }
}

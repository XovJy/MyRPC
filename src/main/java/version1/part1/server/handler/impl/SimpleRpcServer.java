package version1.part1.server.handler.impl;

import lombok.AllArgsConstructor;

import version1.part1.server.handler.RpcServer;
import version1.part1.server.handler.work.WorkThread;
import version1.part1.server.provider.ServiceProvider;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xuehao
 */

@AllArgsConstructor
public class SimpleRpcServer implements RpcServer {
    private ServiceProvider serviceProvider;
    @Override
    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port);){
            System.out.println("服务启动");
            while (true) {
                // 如果没有连接，堵塞在这。
                Socket socket = serverSocket.accept();
                // 有连接，创建一个新的线程执行任务
                new Thread(new WorkThread(socket, serviceProvider)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }
}

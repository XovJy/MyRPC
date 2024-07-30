package part3.server.handler;





/**
 * @author xuehao
 */

public interface RpcServer {

    /**
     * 开始监听
     *
     * @param port 监听的端口ß
     */
    void start(int port);

    /**
     * 停止监听
     */
    void stop();
}

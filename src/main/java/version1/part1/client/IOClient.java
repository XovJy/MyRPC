package version1.part1.client;

import version1.part1.common.message.RpcRequest;
import version1.part1.common.message.RpcResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author xuehao
 */
public class IOClient {
    // 负责底层与服务端端通信，发送request，返回response

    public static RpcResponse sendRequest(String host, int port, RpcRequest request) {
        try ( Socket socket = new Socket(host, port);){
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            oos.writeObject(request);
            oos.flush();

            return (RpcResponse) ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

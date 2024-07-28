package part1.server.handler.work;


import lombok.AllArgsConstructor;
import part1.server.provider.ServiceProvider;
import part1.common.message.RpcRequest;
import part1.common.message.RpcResponse;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author xuehao
 */

@AllArgsConstructor
public class WorkThread implements Runnable{
    private Socket socket;
    private ServiceProvider serviceProvider;

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            RpcRequest rpcRequest = (RpcRequest) ois.readObject();
            RpcResponse rpcResponse = getResponse(rpcRequest);

            oos.writeObject(rpcResponse);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private RpcResponse getResponse(RpcRequest rpcRequest) {

        String interfaceName = rpcRequest.getInterfaceName();
        Object service = serviceProvider.getService(interfaceName);

        Method method = null;
        try {
            method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamsType());
            Object invoke = method.invoke(service, rpcRequest.getParams());
            return RpcResponse.success(invoke);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("方法执行错误");
            return RpcResponse.fail();
        }
    }
}

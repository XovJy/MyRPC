package part3.client.proxy;

import lombok.AllArgsConstructor;
import part3.client.rpcclient.RpcClient;
import part3.client.rpcclient.impl.NettyRpcClient;
import part3.client.rpcclient.impl.SimpleSocketRpcClient;
import part3.common.message.RpcRequest;
import part3.common.message.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xuehao
 */
@AllArgsConstructor
public class ClientProxy implements InvocationHandler{
    // 传入参数service接口的class对象，反射包装为一个request

    private RpcClient rpcClient;

    public ClientProxy() {
        rpcClient = new NettyRpcClient();

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 构建request
        RpcRequest request = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass()
                .getName())
                .methodName(method.getName())
                .params(args)
                .paramsType(method.getParameterTypes())
                .build();
        RpcResponse response = rpcClient.sendRequest(request);
        assert response != null;
        return response.getData();
    }

    public <T>T getProxy(Class<T> clazz) {
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T)o;
    }
}

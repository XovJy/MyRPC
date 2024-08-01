package version2.part3.client.rpcclient;

import version2.part3.common.message.RpcRequest;
import version2.part3.common.message.RpcResponse;

/**
 * @author xuehao
 */
public interface RpcClient {
    /**
     * 发送request 得到返回的response
     * @param request 向服务方发送的请求
     * @return 得到服务方的响应结果
     */
    RpcResponse sendRequest(RpcRequest request);
}

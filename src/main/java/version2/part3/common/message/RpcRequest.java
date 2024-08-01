package version2.part3.common.message;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
/**
 * @author xuehao
 * 定义发送消息格式
 */
@Data
@Builder
public class RpcRequest implements Serializable{

    // 服务类名、客户端只知道接口

    private String interfaceName;
    // 调用方法名

    private String methodName;
    // 参数列表

    private Object[] params;
    // 参数类型

    private Class<?>[] paramsType;


}

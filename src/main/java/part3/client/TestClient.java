package part3.client;

import part3.client.proxy.ClientProxy;
import part3.common.pojo.User;
import part3.common.service.UserService;

/**
 * @author xuehao
 */

public class TestClient {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy();
        UserService proxy = clientProxy.getProxy(UserService.class);

        User user = proxy.getUserByUserId(1);
        System.out.println("从服务端得到user="+user.toString());

        User u = User.builder()
                .id(100)
                .userName("xuehao")
                .sex(true)
                .build();

        Integer id = proxy.insertUserId(u);
        System.out.println("向服务端插入user的id"+id);
    }
}

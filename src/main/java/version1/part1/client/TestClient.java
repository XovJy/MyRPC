package version1.part1.client;

import version1.part1.client.proxy.ClientProxy;
import version1.part1.common.service.UserService;
import version1.part1.common.pojo.User;

/**
 * @author xuehao
 */

public class TestClient {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 9999);
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

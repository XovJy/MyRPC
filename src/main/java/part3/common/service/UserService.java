package part3.common.service;

import part3.common.pojo.User;

/**
 * @author xuehao
 */
public interface UserService {

    /**
     * 根据用户 ID 获取用户信息。
     *
     * @param id 用户的唯一标识符
     * @return 对应的用户对象，如果用户不存在则返回 null
     */
    User getUserByUserId(Integer id);

    /**
     * 添加UserId
     *
     * @param user 要插入的用户对象
     * @return 返回插入对象成功后的id值
     */
    Integer insertUserId(User user);
}

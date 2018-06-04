package org.scattered_clients.dao;

import org.scattered_clients.entity.User;

public interface UserDao {
    /**
     * 功能描述：根据账号来获取用户信息
     * @param login
     * @return
     */
    User findByLogin(String login);
}

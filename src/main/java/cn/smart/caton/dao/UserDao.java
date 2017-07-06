package cn.smart.caton.dao;

import cn.smart.caton.model.User;

import java.util.List;

/**
 * Created by user on 2017/7/6.
 */
public interface UserDao{
    List<User> findList();
    User findById(String id);
    void insert(User user);
    void update(User user);
    void delete(String id);
}

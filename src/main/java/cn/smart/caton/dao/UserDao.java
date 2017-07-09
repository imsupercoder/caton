package cn.smart.caton.dao;

import cn.smart.caton.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/7/6.
 */
public interface UserDao{
    List<User> findList(Map<String, String> params);
    List<User> findAll();
    User findById(String id);
    int insertOrUpdate(User user);
    int delete(String id);
}

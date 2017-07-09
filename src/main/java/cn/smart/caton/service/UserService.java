package cn.smart.caton.service;

import cn.smart.caton.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/7/6.
 */
public interface UserService {
    List<User> findList(Map<String,String> params);
    int insertOrUpdate(User user);
    int delete(String id);
}

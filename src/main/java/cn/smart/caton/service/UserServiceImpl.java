package cn.smart.caton.service;

import cn.smart.caton.dao.UserDao;
import cn.smart.caton.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/7/6.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findList(Map<String, String> params) {
        return userDao.findList(params);
    }

    @Override
    public int insertOrUpdate(User user) {
        return userDao.insertOrUpdate(user);
    }

    @Override
    public int delete(String id) {
        return userDao.delete(id);
    }
}

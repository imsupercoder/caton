package cn.smart.caton.service;

import cn.smart.caton.dao.UserDao;
import cn.smart.caton.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/7/6.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    public List<User> findList() {
        return userDao.findList();
    }
}

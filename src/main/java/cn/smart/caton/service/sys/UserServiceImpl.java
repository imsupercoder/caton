package cn.smart.caton.service.sys;

import cn.smart.caton.dao.sys.UserDao;
import cn.smart.caton.model.sys.User;
import cn.smart.caton.util.EncryptUtil;
import cn.smart.caton.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by user on 2017/7/6.
 */
@Service
@Transactional
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
        if(user!=null&&user.getPassword()!=null&& StringUtil.isBlank(user.getId())){
            user.setPassword(EncryptUtil.hexMD5(user.getPassword()));
        }
        userDao.saveUserRole(user);
        return userDao.insertOrUpdate(user);
    }

    @Override
    public int delete(String id) {
        return userDao.delete(id);
    }

    @Override
    public Set<String> getRoles(String id) {
        return userDao.getRoles(id);
    }

    @Override
    public Set<String> getFunctions(Set<String> roles) {
        return userDao.getFunctions(roles);
    }

    @Override
    public User findByCode(String code) {
        return userDao.findByCode(code);
    }
}

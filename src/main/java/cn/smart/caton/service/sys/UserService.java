package cn.smart.caton.service.sys;

import cn.smart.caton.model.sys.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by user on 2017/7/6.
 */
public interface UserService {
    List<User> findList(Map<String,String> params);
    int insertOrUpdate(User user);
    int delete(String id);
    Set<String> getRoles(String id);
    Set<String> getFunctions(Set<String> roles);
    User findByCode(String code);
}

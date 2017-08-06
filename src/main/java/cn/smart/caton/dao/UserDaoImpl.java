package cn.smart.caton.dao;

import cn.smart.caton.model.User;
import cn.smart.caton.util.SQLUtil;
import cn.smart.caton.util.StringUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by user on 2017/7/6.
 */
@Repository
public class UserDaoImpl extends SmartDaoSupport<User> implements UserDao {

    @Override
    public List<User> findList(Map<String, String> params) {
        String sql = SQLUtil.queryAllSql(User.class);
        List<String> values = new LinkedList<>();
        if(StringUtil.isNotEmpty(params.get("userName"))) {
            sql += " and USERNAME like ?";
            values.add("%"+params.get("userName")+"%");
        }
        if(StringUtil.isNotEmpty(params.get("gender"))) {
            sql += " and GENDER =?";
            values.add(params.get("gender"));
        }
        sql = sql.replaceFirst("and","where");
        return getJdbcTemplate().query(sql,BeanPropertyRowMapper.newInstance(User.class),values.toArray(new String[0]));
    }

    @Override
    public User findByCode(String code) {
        String sql = "select * from USER where usercode =?";
        return queryForObject(sql,BeanPropertyRowMapper.newInstance(User.class),code);
    }

    @Override
    public Set<String> getRoles(String username) {
        Set<String> roles = new HashSet<>();
        String sql = "select roleId from UserRole ur,User u where u.id=ur.userid and u.usercode = ?";
        List<String> list = getJdbcTemplate().queryForList(sql,String.class,username);
        roles.addAll(list);
        return roles;
    }

    @Override
    public Set<String> getFunctions(Set<String> roles) {
        Set<String> functions = new HashSet<>();
        String sql = "select  code from Function f,RoleFunction rf where f.id=rf.functionId and rf.roleId in (:roles)";
        List<String> params = new ArrayList<>();
        params.addAll(roles);
        Map<String,Object> map = new HashMap<>();
        map.put("roles",params);
        List<String> list = getNamedParameterJdbcTemplate().queryForList(sql,map,String.class);
        functions.addAll(list);
        return functions;
    }
}

package cn.smart.caton.dao.sys;

import cn.smart.caton.dao.SmartDaoSupport;
import cn.smart.caton.model.sys.User;
import cn.smart.caton.util.StringUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by user on 2017/7/6.
 */
@Repository
public class UserDaoImpl extends SmartDaoSupport<User> implements UserDao {

    @Override
    public List<User> findList(Map<String, String> params) {
        String sql = "select u.*,r.name as roleName,r.id as roleId,d.name as deptName from USER u,ROLE r,UserRole ur,Dept d where d.id=u.deptId and ur.UserId = u.id and r.id=ur.roleId ";//SQLUtil.queryAllSql(User.class);
        List<String> values = new LinkedList<>();
        if(StringUtil.isNotEmpty(params.get("userName"))) {
            sql += " and u.USERNAME like ?";
            values.add("%"+params.get("userName")+"%");
        }
        if(StringUtil.isNotEmpty(params.get("gender"))) {
            sql += " and u.GENDER =?";
            values.add(params.get("gender"));
        }
        if(StringUtil.isNoneEmpty(params.get("roleId"))){
            sql+= " and r.id =?";
            values.add(params.get("roleId"));
        }
        //sql = sql.replaceFirst("and","where");
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

    @Override
    public void saveUserRole(User user) {
        getJdbcTemplate().update("delete from UserRole where userId = ?",user.getId());
        getJdbcTemplate().update("insert into UserRole (userId,roleId)values(?,?)",user.getId(),user.getRoleId());
    }
}

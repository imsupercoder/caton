package cn.smart.caton.dao;

import cn.smart.caton.model.User;
import cn.smart.caton.util.SQLUtil;
import cn.smart.caton.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
}

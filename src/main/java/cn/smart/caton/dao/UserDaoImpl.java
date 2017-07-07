package cn.smart.caton.dao;

import cn.smart.caton.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 2017/7/6.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findList() {
        String sql = "select * from user";

        return jdbcTemplate.query(sql,new UserRowMapper());
    }

    public User findById(String id) {
        String sql = "select * from user where id=?";
        return jdbcTemplate.queryForObject(sql,new UserRowMapper(),id);
    }

    public void insert(User user) {
        
    }

    public void update(User user) {

    }

    public void delete(String id) {

    }

    class UserRowMapper implements RowMapper<User>{

        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getString("ID"));
            user.setUsername(resultSet.getString("USERNAME"));
            user.setPassword(resultSet.getString("PASSWORD"));
            return user;
        }
    }
}

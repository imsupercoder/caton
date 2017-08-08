/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */

package cn.smart.caton.dao.sys;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cn.smart.caton.dao.SmartDaoSupport;
import cn.smart.caton.model.sys.Function;
import cn.smart.caton.model.sys.Role;
import cn.smart.caton.util.SQLUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;


/**
 *
 * @author wanglei email:840996551@qq.com
 * @version 1.0
 * @since 1.0
 * */
@Repository
public class RoleDaoImpl extends SmartDaoSupport<Role> implements RoleDao{
	
	@Override
    public List<Role> findList(Map<String, String> params) {
        String sql = SQLUtil.queryAllSql(Role.class);
        List<String> values = new LinkedList<>();
		/*
        if(StringUtil.isNotEmpty(params.get("userName"))) {
            sql += " and USERNAME like ?";
            values.add("%"+params.get("userName")+"%");
        }
        if(StringUtil.isNotEmpty(params.get("gender"))) {
            sql += " and GENDER =?";
            values.add(params.get("gender"));
        }*/
		//TODO. 按照示例填充SQL和请求参数
        sql = sql.replaceFirst("and","where");
        return getJdbcTemplate().query(sql,BeanPropertyRowMapper.newInstance(Role.class),values.toArray(new String[0]));
    }

    @Override
    public int saveRoleFunction(Role role) {
	    getJdbcTemplate().update("delete from RoleFunction where roleId=?",role.getId());
	    List<Function> functions = role.getFunctions();
	    String sql = "insert into RoleFunction (RoleId,FunctionId) values(?,?)";
	    for(Function function : functions){
	        getJdbcTemplate().update(sql,role.getId(),function.getId());
        }
        return functions==null?0:functions.size();
    }

}

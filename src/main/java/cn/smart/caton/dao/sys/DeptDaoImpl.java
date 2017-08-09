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
import cn.smart.caton.model.sys.Dept;
import cn.smart.caton.util.SQLUtil;
import cn.smart.caton.util.StringUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;


/**
 *
 * @author wanglei email:840996551@qq.com
 * @version 1.0
 * @since 1.0
 * */
@Repository
public class DeptDaoImpl extends SmartDaoSupport<Dept> implements DeptDao{
	
	@Override
    public List<Dept> findList(Map<String, String> params) {
        String sql = "select d.*,d2.name as parentName from Dept d left join Dept d2 on d.parentId = d2.id ";//SQLUtil.queryAllSql(Dept.class);
        List<String> values = new LinkedList<>();

        if(StringUtil.isNotEmpty(params.get("name"))) {
            sql += " and d.name like ?";
            values.add("%"+params.get("name")+"%");
        }
        /*
        if(StringUtil.isNotEmpty(params.get("gender"))) {
            sql += " and GENDER =?";
            values.add(params.get("gender"));
        }*/
		//TODO. 按照示例填充SQL和请求参数
        sql = sql.replaceFirst("and","where");
        return getJdbcTemplate().query(sql,BeanPropertyRowMapper.newInstance(Dept.class),values.toArray(new String[0]));
    }

}

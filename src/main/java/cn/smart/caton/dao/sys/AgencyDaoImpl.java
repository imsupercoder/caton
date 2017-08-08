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
import cn.smart.caton.model.sys.Agency;
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
public class AgencyDaoImpl extends SmartDaoSupport<Agency> implements AgencyDao{
	
	@Override
    public List<Agency> findList(Map<String, String> params) {
        String sql = SQLUtil.queryAllSql(Agency.class);
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
        return getJdbcTemplate().query(sql,BeanPropertyRowMapper.newInstance(Agency.class),values.toArray(new String[0]));
    }

}

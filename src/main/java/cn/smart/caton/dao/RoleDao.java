/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */

package cn.smart.caton.dao;

import java.util.List;
import java.util.Map;

import cn.smart.caton.model.Role;


/**
 *
 * @author wanglei email:840996551@qq.com
 * @version 1.0
 * @since 1.0
 * */
public interface RoleDao{
	
	List<Role> findList(Map<String, String> params);
    List<Role> findAll();
    Role findById(String id);
    int insertOrUpdate(Role entity);
    int delete(String id);

}

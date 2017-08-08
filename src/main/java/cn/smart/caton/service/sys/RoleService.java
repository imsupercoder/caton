/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */

package cn.smart.caton.service.sys;

import java.util.List;
import java.util.Map;

import cn.smart.caton.model.sys.Role;


/**
 *
 * @author wanglei email:840996551@qq.com
 * @version 1.0
 * @since 1.0
 * */
public interface RoleService {

	/** 
	 * 创建/更新Role
	 **/
	int insertOrUpdate(Role role);
    
	/** 
	 * 删除Role
	 **/
    int delete(String id);
    
	/** 
	 * 根据ID得到Role
	 **/    
    Role findById(String id);
    
	/** 
	 * 查询: Role
	 **/      
	List<Role> findList(Map<String, String> params);
    
}

/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */

package cn.smart.caton.service;

import java.util.List;
import java.util.Map;

import cn.smart.caton.dao.RoleDao;
import cn.smart.caton.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 *
 * @author wanglei email:840996551@qq.com
 * @version 1.0
 * @since 1.0
 * */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	/** 
	 * 创建/更新Role
	 **/
	public int insertOrUpdate(Role role) {
	    return this.roleDao.insertOrUpdate(role);
	}	
    
	/** 
	 * 删除Role
	 **/
    public int delete(String id) {
        return  this.roleDao.delete(id);
    }
    
	/** 
	 * 根据ID得到Role
	 **/    
    public Role findById(String id) {
        return this.roleDao.findById(id);
    }
    
	/** 
	 * 查询: Role
	 **/      
	@Transactional(readOnly=true)
	public List<Role> findList(Map<String,String> params) {
		return roleDao.findList(params);
	}
	
}

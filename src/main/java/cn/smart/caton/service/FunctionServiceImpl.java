/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */

package cn.smart.caton.service;

import java.util.List;
import java.util.Map;

import cn.smart.caton.dao.FunctionDao;
import cn.smart.caton.model.Function;
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
public class FunctionServiceImpl implements FunctionService {

	@Autowired
	private FunctionDao functionDao;
	
	/** 
	 * 创建/更新Function
	 **/
	public int insertOrUpdate(Function function) {
	    return this.functionDao.insertOrUpdate(function);
	}	
    
	/** 
	 * 删除Function
	 **/
    public int delete(String id) {
        return this.functionDao.delete(id);
    }
    
	/** 
	 * 根据ID得到Function
	 **/    
    public Function findById(String id) {
        return this.functionDao.findById(id);
    }
    
	/** 
	 * 查询: Function
	 **/      
	@Transactional(readOnly=true)
	public List<Function> findList(Map<String,String> params) {
		return functionDao.findList(params);
	}

	@Override
	public List<Function> getFunctionsByRoleId(String roleId) {
		return functionDao.getFunctionsByRoleId(roleId);
	}

}

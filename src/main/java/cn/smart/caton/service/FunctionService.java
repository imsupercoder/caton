/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */

package cn.smart.caton.service;

import java.util.List;
import java.util.Map;

import cn.smart.caton.model.Function;


/**
 *
 * @author wanglei email:840996551@qq.com
 * @version 1.0
 * @since 1.0
 * */
public interface FunctionService {

	/** 
	 * 创建/更新Function
	 **/
	int insertOrUpdate(Function function);
    
	/** 
	 * 删除Function
	 **/
    int delete(String id);
    
	/** 
	 * 根据ID得到Function
	 **/    
    Function findById(String id);
    
	/** 
	 * 查询: Function
	 **/      
	List<Function> findList(Map<String, String> params);
    
}

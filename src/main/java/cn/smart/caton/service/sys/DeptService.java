/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */

package cn.smart.caton.service.sys;

import java.util.List;
import java.util.Map;

import cn.smart.caton.model.sys.Dept;


/**
 *
 * @author wanglei email:840996551@qq.com
 * @version 1.0
 * @since 1.0
 * */
public interface DeptService {

	/** 
	 * 创建/更新Dept
	 **/
	int insertOrUpdate(Dept dept);
    
	/** 
	 * 删除Dept
	 **/
    int delete(String id);
    
	/** 
	 * 根据ID得到Dept
	 **/    
    Dept findById(String id);
    
	/** 
	 * 查询: Dept
	 **/      
	List<Dept> findList(Map<String, String> params);
    
}

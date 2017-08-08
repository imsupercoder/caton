/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */

package cn.smart.caton.service.sys;

import java.util.List;
import java.util.Map;

import cn.smart.caton.model.sys.Agency;


/**
 *
 * @author wanglei email:840996551@qq.com
 * @version 1.0
 * @since 1.0
 * */
public interface AgencyService {

	/** 
	 * 创建/更新Agency
	 **/
	int insertOrUpdate(Agency agency);
    
	/** 
	 * 删除Agency
	 **/
    int delete(String id);
    
	/** 
	 * 根据ID得到Agency
	 **/    
    Agency findById(String id);
    
	/** 
	 * 查询: Agency
	 **/      
	List<Agency> findList(Map<String, String> params);
    
}

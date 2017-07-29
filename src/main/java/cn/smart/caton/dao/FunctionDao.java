/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */

package cn.smart.caton.dao;

import java.util.List;
import java.util.Map;

import cn.smart.caton.model.Function;


/**
 *
 * @author wanglei email:840996551@qq.com
 * @version 1.0
 * @since 1.0
 * */
public interface FunctionDao{
	
	List<Function> findList(Map<String, String> params);
    List<Function> findAll();
    Function findById(String id);
    int insertOrUpdate(Function entity);
    int delete(String id);

}

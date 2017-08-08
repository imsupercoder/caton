/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */

package cn.smart.caton.dao.sys;

import java.util.List;
import java.util.Map;

import cn.smart.caton.model.sys.Dept;


/**
 *
 * @author wanglei email:840996551@qq.com
 * @version 1.0
 * @since 1.0
 * */
public interface DeptDao{
	
	List<Dept> findList(Map<String, String> params);
    List<Dept> findAll();
    Dept findById(String id);
    int insertOrUpdate(Dept entity);
    int delete(String id);

}

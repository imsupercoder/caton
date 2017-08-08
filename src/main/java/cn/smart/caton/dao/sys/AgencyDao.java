/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */

package cn.smart.caton.dao.sys;

import java.util.List;
import java.util.Map;

import cn.smart.caton.model.sys.Agency;


/**
 *
 * @author wanglei email:840996551@qq.com
 * @version 1.0
 * @since 1.0
 * */
public interface AgencyDao{
	
	List<Agency> findList(Map<String, String> params);
    List<Agency> findAll();
    Agency findById(String id);
    int insertOrUpdate(Agency entity);
    int delete(String id);

}

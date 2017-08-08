/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */

package cn.smart.caton.service.sys;

import java.util.List;
import java.util.Map;

import cn.smart.caton.dao.sys.AgencyDao;
import cn.smart.caton.model.sys.Agency;
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
public class AgencyServiceImpl implements AgencyService {

	@Autowired
	private AgencyDao agencyDao;
	
	/** 
	 * 创建/更新Agency
	 **/
	public int insertOrUpdate(Agency agency) {
	    return this.agencyDao.insertOrUpdate(agency);
	}	
    
	/** 
	 * 删除Agency
	 **/
    public int delete(String id) {
        return this.agencyDao.delete(id);
    }
    
	/** 
	 * 根据ID得到Agency
	 **/    
    public Agency findById(String id) {
        return this.agencyDao.findById(id);
    }
    
	/** 
	 * 查询: Agency
	 **/      
	@Transactional(readOnly=true)
	public List<Agency> findList(Map<String,String> params) {
		return agencyDao.findList(params);
	}
	
}

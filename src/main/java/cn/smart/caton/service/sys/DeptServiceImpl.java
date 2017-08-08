/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */

package cn.smart.caton.service.sys;

import java.util.List;
import java.util.Map;

import cn.smart.caton.dao.sys.DeptDao;
import cn.smart.caton.model.sys.Dept;
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
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptDao deptDao;
	
	/** 
	 * 创建/更新Dept
	 **/
	public int insertOrUpdate(Dept dept) {
	    return this.deptDao.insertOrUpdate(dept);
	}	
    
	/** 
	 * 删除Dept
	 **/
    public int delete(String id) {
        return this.deptDao.delete(id);
    }
    
	/** 
	 * 根据ID得到Dept
	 **/    
    public Dept findById(String id) {
        return this.deptDao.findById(id);
    }
    
	/** 
	 * 查询: Dept
	 **/      
	@Transactional(readOnly=true)
	public List<Dept> findList(Map<String,String> params) {
		return deptDao.findList(params);
	}
	
}

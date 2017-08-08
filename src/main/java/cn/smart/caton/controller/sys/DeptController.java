/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */


package cn.smart.caton.controller.sys;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.smart.caton.controller.BaseController;
import cn.smart.caton.model.sys.Dept;
import cn.smart.caton.service.sys.DeptService;
import cn.smart.caton.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author wanglei email:840996551@qq.com
 * @version 1.0
 * @since 1.0
 * */
@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController {

    @Autowired
	private DeptService deptService;

	@RequestMapping(value = "/depts",method = RequestMethod.GET)
    public List<Dept> list(HttpServletRequest request){
        return deptService.findList(RequestUtil.getParamMap(request));
    }
    @RequestMapping(value ="/delete",method = RequestMethod.POST)
    public Map<String,Object> delete(@RequestParam("id")String id){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("flag",deptService.delete(id)==1?true:false);
        return returnMap;
    }
    @RequestMapping(value ="/dept",method = RequestMethod.POST)
    public Map<String,Object> insertOrUpdate(@RequestBody Dept dept){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("flag",deptService.insertOrUpdate(dept)==1?true:false);
        return returnMap;
    }
	
}


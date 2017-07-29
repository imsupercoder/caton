/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */


package cn.smart.caton.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.smart.caton.model.Role;
import cn.smart.caton.service.RoleService;
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
@RequestMapping("/role")
public class RoleController extends BaseController{
	
	private RoleService roleService;

	@RequestMapping(value = "/roles.do",method = RequestMethod.POST)
    public List<Role> list(HttpServletRequest request){
        return roleService.findList(RequestUtil.getParamMap(request));
    }
    @RequestMapping(value ="/delete.do",method = RequestMethod.POST)
    public Map<String,Object> delete(@RequestParam("id")String id){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("flag",roleService.delete(id)==1?true:false);
        return returnMap;
    }
    @RequestMapping(value ="/role.do",method = RequestMethod.POST)
    public Map<String,Object> insertOrUpdate(@RequestBody Role role){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("flag",roleService.insertOrUpdate(role)==1?true:false);
        return returnMap;
    }
	
}


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
import java.util.Set;

import cn.smart.caton.model.Function;
import cn.smart.caton.model.Role;
import cn.smart.caton.service.RoleService;
import cn.smart.caton.util.RequestUtil;
import cn.smart.caton.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

    @Autowired
	private RoleService roleService;

    @RequiresPermissions("caton.sys.admin")
	@RequestMapping(value = "/roles",method = RequestMethod.GET)
    public List<Role> list(HttpServletRequest request){
        return roleService.findList(RequestUtil.getParamMap(request));
    }
    @RequiresPermissions("caton.sys.admin")
    @RequestMapping(value ="/delete.do",method = RequestMethod.POST)
    public Map<String,Object> delete(@RequestParam("id")String id){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("flag",roleService.delete(id)==1?true:false);
        return returnMap;
    }
    @RequiresPermissions("caton.sys.admin")
    @RequestMapping(value ="/role",method = RequestMethod.POST)
    public Map<String,Object> insertOrUpdate(@RequestBody Role role){
        if(StringUtil.isBlank(role.getId())){
            role.setAddBy(getCurrRealName());
        } else {
            role.setUpdateBy(getCurrRealName());
        }
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("flag",roleService.insertOrUpdate(role)==1?true:false);
        return returnMap;
    }


	
}


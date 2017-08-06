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

import cn.smart.caton.model.Function;
import cn.smart.caton.service.FunctionService;
import cn.smart.caton.util.RequestUtil;
import cn.smart.caton.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
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
@RequestMapping("/function")
public class FunctionController extends BaseController{

    @Autowired
	private FunctionService functionService;

    @RequiresAuthentication
    @RequiresPermissions("caton.sys.admin")
	@RequestMapping(value = "/functions",method = RequestMethod.GET)
    public List<Function> list(HttpServletRequest request){
        return functionService.findList(RequestUtil.getParamMap(request));
    }
    @RequiresPermissions("caton.sys.admin")
    @RequestMapping(value ="/delete",method = RequestMethod.POST)
    public Map<String,Object> delete(@RequestParam("id")String id){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("flag",functionService.delete(id)==1?true:false);
        return returnMap;
    }
    @RequiresPermissions("caton.sys.admin")
    @RequestMapping(value ="/function",method = RequestMethod.POST)
    public Map<String,Object> insertOrUpdate(@RequestBody Function function){
        if(StringUtil.isBlank(function.getId())){
            function.setAddBy(getCurrRealName());
        } else {
            function.setUpdateBy(getCurrRealName());
        }

        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("flag",functionService.insertOrUpdate(function)==1?true:false);
        return returnMap;
    }

    @RequiresPermissions("caton.sys.admin")
    @RequestMapping("/functionsByRoleId")
    public List<Function> getFunctionsById(@RequestParam("roleId") String roleId){
        return functionService.getFunctionsByRoleId(roleId);
    }
	
}


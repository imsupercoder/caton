package cn.smart.caton.controller.sys;

import cn.smart.caton.controller.BaseController;
import cn.smart.caton.model.sys.User;
import cn.smart.caton.service.sys.UserService;
import cn.smart.caton.util.RequestUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by user on 2017/7/6.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequiresPermissions("caton.sys.admin")
    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public List<User> list(HttpServletRequest request){
        return userService.findList(RequestUtil.getParamMap(request));
    }

    @RequiresPermissions("caton.sys.admin")
    @RequestMapping(value ="/delete",method = RequestMethod.POST)
    public Map<String,Object> delete(@RequestParam("id")String id){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("flag",userService.delete(id)==1?true:false);
        return returnMap;
    }

    @RequiresPermissions("caton.sys.admin")
    @RequestMapping(value ="/user",method = RequestMethod.POST)
    public Map<String,Object> insertOrUpdate(@RequestBody User user){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("flag",userService.insertOrUpdate(user)==1?true:false);
        return returnMap;
    }

    @RequiresAuthentication
    @RequestMapping("/current/permissions")
    public Set<String> getPermissions(){
        Set<String> roles = userService.getRoles(getCurrentUserName());
        Set<String> functions = userService.getFunctions(roles);
        return functions;
    }
}

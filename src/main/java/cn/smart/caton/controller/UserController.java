package cn.smart.caton.controller;

import cn.smart.caton.model.User;
import cn.smart.caton.service.UserService;
import cn.smart.caton.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/7/6.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/users.do",method = RequestMethod.POST)
    public List<User> list(HttpServletRequest request){
        return userService.findList(RequestUtil.getParamMap(request));
    }
    @RequestMapping(value ="/delete.do",method = RequestMethod.POST)
    public Map<String,Object> delete(@RequestParam("id")String id){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("flag",userService.delete(id)==1?true:false);
        return returnMap;
    }
    @RequestMapping(value ="/user.do",method = RequestMethod.POST)
    public Map<String,Object> insertOrUpdate(@RequestBody User user){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("flag",userService.insertOrUpdate(user)==1?true:false);
        return returnMap;
    }
}

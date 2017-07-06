package cn.smart.caton.controller;

import cn.smart.caton.model.User;
import cn.smart.caton.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by user on 2017/7/6.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/list.do")
    public List<User> list(){
        return userService.findList();
    }
}

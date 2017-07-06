package cn.smart.caton.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by wl on 2017/7/6.
 */
@RestController
public class HelloController {

    @RequestMapping("/hello.do")
    @ResponseBody
    public String method(@RequestBody Map<String,Object> map){
        return "hello";
    }
}

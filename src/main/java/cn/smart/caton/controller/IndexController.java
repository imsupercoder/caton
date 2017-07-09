package cn.smart.caton.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 2017/7/9.
 */
@Controller
public class IndexController extends BaseController {
    @RequestMapping("/index.do")
    public String index(HttpServletRequest request){
        return "/index";
    }
}

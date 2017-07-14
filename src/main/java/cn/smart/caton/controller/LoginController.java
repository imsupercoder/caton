package cn.smart.caton.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 2017/7/10.
 */
@Controller
public class LoginController extends BaseController {
    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("error","");
        return "/login";
    }
}

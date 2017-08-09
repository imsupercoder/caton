package cn.smart.caton.controller.sys;

import cn.smart.caton.controller.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 2017/7/10.
 */
@Controller
public class LoginController extends BaseController {
    @RequestMapping("/login")
    public String login(HttpServletRequest req,Model model){
        Subject subject = SecurityUtils.getSubject();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username!=null&&password!=null){
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            try{
                subject.login(token);
            }
            catch (Exception e){
                String error ;
                if(e instanceof UnknownAccountException){
                    error = "用户名/密码错误";
                } else if(e instanceof IncorrectCredentialsException){
                    error = "用户名/密码错误";
                } else if(e instanceof LockedAccountException){
                    error = "账号被锁定";
                } else if(e instanceof ExcessiveAttemptsException){
                    error = "尝试次数过多，账号被锁定一小时";
                } else if(e instanceof AuthenticationException){
                    error = "用户名/密码错误";
                } else {
                    error = "未知错误";
                }
                model.addAttribute("error", error);
            }
        }
        if(subject.isAuthenticated()) {
            SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(req);
            if (savedRequest != null && savedRequest.getMethod().equalsIgnoreCase(AccessControlFilter.GET_METHOD)) {
                return "redirect:" + savedRequest.getRequestUrl();
            }
            return "redirect:/";
        }
        return "login";
    }
}

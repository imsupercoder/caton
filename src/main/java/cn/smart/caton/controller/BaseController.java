package cn.smart.caton.controller;

import cn.smart.caton.model.sys.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.WebContentGenerator;


/**
 * Created by user on 2017/7/8.
 */
public class BaseController extends WebContentGenerator {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    StatusMessage lastDefence(Exception ex) {
        log.error("捕获异常："+ex.getMessage(),ex);
        return new StatusMessage("未知错误");
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public @ResponseBody StatusMessage noRight(UnauthorizedException ex) {
        log.error("捕获异常："+ex.getMessage(),ex);
        return new StatusMessage("您无权限访问此功能！");
    }

    @ExceptionHandler(value = { DuplicateKeyException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody StatusMessage duplicatKey(DuplicateKeyException ex) {
        log.error("捕获异常："+ex.getMessage(),ex);
        return new StatusMessage("操作失败，违反数据库完整约束条件，可能是数据库已经存在相应记录！");
    }

    public User getCurrentUser(){
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getSession().getAttribute("currentUser");
        return currentUser;
    }

    public String getCurrentUserName(){
        Subject subject = SecurityUtils.getSubject();
        return (String)subject.getPrincipal();
    }

    public String getCurrRealName(){
        return getCurrentUser().getUserName();
    }
}

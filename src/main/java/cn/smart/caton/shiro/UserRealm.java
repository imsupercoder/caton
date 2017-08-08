package cn.smart.caton.shiro;

import cn.smart.caton.model.sys.User;
import cn.smart.caton.service.sys.UserService;
import cn.smart.caton.util.EncryptUtil;
import cn.smart.caton.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created by user on 2017/7/10.
 */
public class UserRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = principalCollection.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = userService.getRoles(username);
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(userService.getFunctions(roles));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
        String username = authcToken.getUsername();
        String password = String.valueOf(authcToken.getPassword());
        if (StringUtil.isBlank(username)||StringUtil.isBlank(password)) {
            throw new AccountException("用户名或密码不能为空！");// 没找到帐号
        }
        User user = userService.findByCode(username);
        if(user==null||!user.getPassword().equals(EncryptUtil.hexMD5(password))){
            throw new AuthenticationException("用户名或密码错误！");
        }
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.setAttribute("currentUser",user);
        SimpleAccount account = new SimpleAccount(username, password, getName());
        return account;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }
}

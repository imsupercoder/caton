package cn.smart.caton.shiro;

import cn.smart.caton.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.cache.CacheManager;

/**
 * Created by user on 2017/7/10.
 */
public class UserRealm extends AuthorizingRealm{
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
        String username = authcToken.getUsername();
        String password = String.valueOf(authcToken.getPassword());
        if (StringUtil.isBlank(username)) {
            throw new AccountException("用户名不能为空！");// 没找到帐号
        }

        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        SimpleAccount account = new SimpleAccount(username, password, getName());
        return account;
    }
}

package net.sppan.base.config.shiro;


import net.sppan.base.Application;
import net.sppan.base.common.utils.MD5Utils;
import net.sppan.base.entity.*;
import net.sppan.base.service.IDriverService;
import net.sppan.base.service.INurseService;
import net.sppan.base.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
public class UserRealm extends AuthorizingRealm{
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public UserRealm(){

        super(new AllowAllCredentialsMatcher());
        setAuthenticationTokenClass(UsernamePasswordToken.class);

        //FIXME: 暂时禁用Cache
        setCachingEnabled(false);
    }
    @Autowired
    private IUserService userService;
    @Autowired
    IDriverService driverService;
    @Autowired
    INurseService nurseService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User dbUser = userService.findByUserName(user.getUserName());
        Set<String> shiroPermissions = new HashSet<>();
        Set<String> roleSet = new HashSet<String>();
        Set<Role> roles = dbUser.getRoles();
        for (Role role : roles) {
            Set<Resource> resources = role.getResources();
            for (Resource resource : resources) {
                shiroPermissions.add(resource.getSourceKey());
            }
            roleSet.add(role.getRoleKey());
        }
        Iterator iterator=shiroPermissions.iterator();
        if (iterator.hasNext())
            authorizationInfo.setRoles(roleSet);
        authorizationInfo.setStringPermissions(shiroPermissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        Driver driver=driverService.findDriverByUserName(username);
        Nurse nurse=nurseService.findNurseByUserName(username);
        if (driver==null&&nurse==null){
            throw  new UnknownAccountException("账号或密码不正确");

        } Object credentials = authenticationToken.getCredentials();
        if (credentials == null) {
            logger.debug("ddasdsddcredentials  nulll"+username);
            throw new UnknownAccountException("账号或密码不正确");
        }
        String password = new String((char[]) credentials);
        // 密码错误
        if ((driver!=null&&!password.equals(driver.getPassWord()))||(nurse!=null&&!password.equals(nurse.getPassWord()))) {
            logger.debug("  password null"+password+"driver .getpassword"+driver.getPassWord());
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        SimpleAuthenticationInfo info=null;
        if (driver!=null) {
             info= new SimpleAuthenticationInfo(driver, password, getName());
        }
        else if (nurse!=null){
            info= new SimpleAuthenticationInfo(nurse, password, getName());

        }

        return info;


    }
}

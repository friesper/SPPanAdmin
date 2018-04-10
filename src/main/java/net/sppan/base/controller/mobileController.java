package net.sppan.base.controller;


import net.sppan.base.Application;
import net.sppan.base.common.JsonResult;
import net.sppan.base.config.shiro.CustomizedToken;
import net.sppan.base.entity.Driver;
import net.sppan.base.entity.LoginType;
import net.sppan.base.entity.ReqUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/mobile")
public class mobileController extends BaseController {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);
    private static final String USER_LOGIN_TYPE = LoginType.USER.toString();
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(@RequestBody ReqUser reqUser) {
        Subject subject;
        try {
            String username=reqUser.getUserName();
            String password=reqUser.getPassWord();
             subject = SecurityUtils.getSubject();
            CustomizedToken customizedToken = new CustomizedToken(username, password, USER_LOGIN_TYPE);
            logger.debug("      name"+username+"            passwd"+password);

           /* CustomizedToken customizedToken = new CustomizedToken(driver.getUserName(), driver.getPassWord(), USER_LOGIN_TYPE);
            logger.debug("      name"+driver.getUserName()+"            passwd"+driver.getPassWord());*/
            subject.login(customizedToken);
            subject.getSession().setTimeout(60480000);
        } catch (AuthenticationException e) {
            response.setStatus(404);
            logger.debug("error exception+  "+e.getMessage());
            return JsonResult.failure(404,e.getMessage());
        }
        logger.debug("success                           "+subject.getPrincipals().getPrimaryPrincipal().toString());
        response.setStatus(200);
        return JsonResult.success("",subject.getPrincipal().toString());


    }



}

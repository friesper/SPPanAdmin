package net.sppan.base.controller;

import net.sppan.base.common.JsonResult;
import net.sppan.base.config.shiro.CustomizedToken;
import net.sppan.base.entity.LoginType;
import net.sppan.base.entity.ReqUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserLoginController extends BaseController {

    private static final String USER_LOGIN_TYPE = LoginType.USER.toString();
    @RequestMapping(value = {"login"}, method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        ModelMap model) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            CustomizedToken customizedToken = new CustomizedToken(username, password, USER_LOGIN_TYPE);
            customizedToken.setRememberMe(true);
            customizedToken.setLoginType(USER_LOGIN_TYPE);
            subject.login(customizedToken);
            return "user/index";
        } catch (AuthenticationException e) {
            model.put("message", e.getMessage());
        }
        return "user/login";
    }


}

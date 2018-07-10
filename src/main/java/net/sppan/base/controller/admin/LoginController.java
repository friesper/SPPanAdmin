package net.sppan.base.controller.admin;

import net.sppan.base.common.utils.AESUtils;
import net.sppan.base.config.shiro.CustomizedToken;
import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.LoginType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController extends BaseController {
    private static final String ADMIN_LOGIN_TYPE = LoginType.ADMIN.toString();
    @RequestMapping(value = {"/admin/login"}, method = RequestMethod.GET)
    public String login() {
        return "admin/login";
    }
    @RequestMapping(value = {"/admin/login"}, method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        ModelMap model) {
        try {
            username=AESUtils.decrypt("huitong",username);
            password=AESUtils.decrypt("huitong",password);
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            CustomizedToken customizedToken = new CustomizedToken(username, password, ADMIN_LOGIN_TYPE);
            customizedToken.setRememberMe(true);
            customizedToken.setLoginType(ADMIN_LOGIN_TYPE);
            subject.login(customizedToken);
            return redirect("/admin/index");
        } catch (AuthenticationException e) {
            model.put("message", e.getMessage());
        }
        return "admin/login";
    }

    @RequestMapping(value = {"/admin/logout"}, method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return redirect("admin/login");
    }

}

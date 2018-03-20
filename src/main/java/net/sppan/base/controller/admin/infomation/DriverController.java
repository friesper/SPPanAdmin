package net.sppan.base.controller.admin.infomation;

import net.sppan.base.Application;
import net.sppan.base.common.JsonResult;
import net.sppan.base.config.shiro.ShiroConfig;
import net.sppan.base.config.shiro.ShiroManager;
import net.sppan.base.controller.BaseController;
import net.sppan.base.dao.IDriverDao;
import net.sppan.base.entity.Driver;
import net.sppan.base.entity.RlationOFSD;
import net.sppan.base.entity.Role;
import net.sppan.base.entity.User;
import net.sppan.base.service.IDriverService;
import net.sppan.base.service.IRlationOFSDService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.omg.CORBA.Request;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
@RequestMapping("/admin/driver")
public class DriverController extends BaseController {
    @Autowired
    private IDriverService iDriverService;
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);
    @Autowired
    IRlationOFSDService iRlationOFSDService;
    @RequestMapping(value = "/index")
    public String index(ModelMap modelMap) {
        User user;
        int schoolId=0,roleId=0;
        Page<Driver>  page;
       Cookie[] cookies=request.getCookies();
       for (Cookie cookie:cookies) {
           if (cookie.getName().equals("JSESSIONID"))
           try{
               SessionKey key = new WebSessionKey(cookie.getValue(),request,response);
               Session se = SecurityUtils.getSecurityManager().getSession(key);
               Object obj = se.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
               SimplePrincipalCollection coll = (SimplePrincipalCollection) obj;
                user=(User)coll.getPrimaryPrincipal();
               Set<Role> role=user.getRoles();
               for (Role role1:role){
                  roleId= role1.getId();
                  schoolId=role1.getSchoolId();
               }

           }catch(Exception e){
               e.printStackTrace();
           }finally{
           }
       }
        if (roleId==1){
           page=iDriverService.findAll(getPageRequest());
        }
        else {
            List<RlationOFSD> list = iRlationOFSDService.findByDriverId(schoolId);
            HashSet<Integer> ids = new HashSet<Integer>();
            for (int i=0;i<list.size();i++) {
                ids.add(list.get(i).getDriverId());
            }

            page = iDriverService.findById(ids, getPageRequest());

        }
        modelMap.put("pageInfo", page);
        return "admin/driver/index";
    }
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(ModelMap modelMap){
        return "admin/driver/form";
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {

        Driver driver = iDriverService.find(id);
        map.put("driver", driver);
        return "admin/driver/form";
    }
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(Driver driver,ModelMap map){
        try {
            logger.info("driver                 post++++++++++++"+driver.toString());
            iDriverService.saveOrUpdate(driver);
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id){
        try {
            iDriverService.delete(id);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

}

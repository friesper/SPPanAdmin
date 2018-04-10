package net.sppan.base.controller.admin.infomation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.sppan.base.Application;
import net.sppan.base.common.JsonResult;
import net.sppan.base.config.shiro.ShiroConfig;
import net.sppan.base.config.shiro.ShiroManager;
import net.sppan.base.controller.BaseController;
import net.sppan.base.dao.IDriverDao;
import net.sppan.base.entity.*;
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
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;


@Controller
@RequestMapping("/admin/driver")
public class DriverController extends BaseController {

    @Autowired
    private Environment env;
    @Autowired
    private IDriverService iDriverService;
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);
    @Autowired
    IRlationOFSDService iRlationOFSDService;
    @RequestMapping(value = "/index")
    public String index(ModelMap modelMap) {
       int schoolId=0,roleId=0;
       Page<Driver>  page;
      User user=getUser();
        if (user.getRoles().iterator().next().getId()==1){
           page=iDriverService.findAll(getPageRequest());
        }
        else {
            HashSet<Integer> ids = new HashSet<Integer>();
            Set<Role> roles=user.getRoles();
            logger.debug("dadwa             "+roles.iterator().next().getId());
            for (Role role:roles){
                schoolId= role.getSchoolId();
                List<RlationOFSD> list = iRlationOFSDService.findBySchoolId(schoolId);
                logger.debug("dadwssd           a"+list.size());
                for (int i=0;i<list.size();i++) {
                    ids.add(list.get(i).getDriverId());
                        logger.debug("dadwa"+list.get(i).toString());
                }
            }

            page = iDriverService.findById(ids, getPageRequest());

        }
        modelMap.put("pageInfo", page);
        return "admin/driver/index";
    }
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(ModelMap modelMap){
        User user=getUser();
        Role role=user.getRoles().iterator().next();

        if (role.getSchoolId()!=null){
            modelMap.put("schoolId",role.getSchoolId());

        }
        else {
            modelMap.put("schoolId","");
            modelMap.put("roleId",role.getId());
        }
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
    public JsonResult edit( @RequestParam("driverImageFile") MultipartFile multipartFile, Driver driver, ModelMap map){
        try {
            if (!multipartFile.isEmpty()) {
                logger.debug("dsad"+multipartFile.getOriginalFilename());
                String rootPathDir = env.getProperty("upload.path");
                String fullPathDir = rootPathDir;
                /**根据本地路径创建目录**/
                File fullPathFile = new File(fullPathDir);
                if (!fullPathFile.exists())
                    fullPathFile.mkdirs();
                /** 获取文件的后缀* */
                String suffix = multipartFile.getOriginalFilename().substring(
                        multipartFile.getOriginalFilename().lastIndexOf("."));
                String fileName = driver.getName() + driver.getId() + suffix;
                String filePath = rootPathDir + fileName;
                if (driver.getDriverImage()!=null){
                    File file=new File(rootPathDir+driver.getDriverImage());
                    if (file.exists()){
                        file.delete();
                    }
                }
                /** 文件输出流* */
                File file = new File(filePath);
                try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                     BufferedOutputStream stream = new BufferedOutputStream(fileOutputStream)) {
                    stream.write(multipartFile.getBytes());
                    stream.flush();
                    driver.setDriverImage(fileName);
                } catch (Exception e) {
                    return JsonResult.failure(e.getMessage());
                }
            }
            else {
            }
            iDriverService.saveOrUpdate(driver);

        } catch (Exception e) {
               throw e;
        }
        return JsonResult.success();
    }
   /* @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public JsonResult edit(@RequestBody Map<String,Object> map ){
        try {
            File  file=(File)map.get("driverImage");
            logger.debug("sdasd         ++"+file.getName());
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }*/
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id){
        try {
            iDriverService.delete(id);
            iRlationOFSDService.deleteAllByDriverId(id);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
    @RequestMapping(value = "/driverList")
    @ResponseBody
    public  JsonResult driverList(){
        User user=getUser();
        List<Driver> list;
        JSONArray jsonArray=new JSONArray();

        Role role=user.getRoles().iterator().next();
        if (role.getId()==1){
           list=iDriverService.findAll();
            JSONObject jsonObject=new JSONObject();
            for (int i = 0; i <list.size() ; i++) {
                jsonArray.add(i,list.get(i));
            }
        }
        else{
         List<RlationOFSD> list1= iRlationOFSDService.findBySchoolId(role.getSchoolId());
               Driver driver;
                for (RlationOFSD rlationOFSD:list1){
                    driver=iDriverService.find(rlationOFSD.getDriverId());
                    jsonArray.add(driver);
                }
        }
        return JsonResult.success("",jsonArray.toString());
    }

}

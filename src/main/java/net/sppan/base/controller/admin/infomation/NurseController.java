package net.sppan.base.controller.admin.infomation;

import com.alibaba.fastjson.JSONArray;
import net.sppan.base.Application;
import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.*;
import net.sppan.base.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin/nurse")
public class NurseController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private Environment env;
    @Autowired
    private INurseService nurseService;
    @Autowired
    IRelationOfSchoolAndNurseService relationOfSchoolAndNurseService;
    @Autowired
    IBusService busService;
    @Autowired
    IDriverService driverService;
    @Autowired
    IRelationOfSAService relationOfSAService;
    @RequestMapping("/index")
    public String index( ModelMap modelMap){
        Page<Nurse > page;
        User user=getUser();
        Role role=user.getRoles().iterator().next();
        if (role.getId()!=1){
            HashSet<Integer> ids = new HashSet<Integer>();
                List<RelationOfSA> list = relationOfSAService.findNurseBySchoolId(role.getSchoolId());
                for (int i=0;i<list.size();i++) {
                    ids.add(list.get(i).getNurseId());
                    logger.debug("asdwahdwa nurseId"+list.get(i).getNurseId());
                }
                logger.debug("asdwahdwa schoolId"+role.getSchoolId()+"nurse+++");
            page = nurseService.findById(ids, getPageRequest());
            }

        else {
            page=nurseService.findAll(getPageRequest());

        }
        modelMap.put("pageInfo", page);
        return  "/admin/nurse/index";
    }
    @RequestMapping("/mobile/relationInfo/{id}")
    @ResponseBody
    public JsonResult mobileList(@PathVariable Integer id){
        List<RelationOfSchoolAndNurse> list=relationOfSchoolAndNurseService.findByNurseId(id);
        logger.debug("dsadasdas+            "+list.size());
        JSONArray jsonArray=new JSONArray();
        if (list.size()>0) {
            RelationOfSchoolAndNurse relationOfSchoolAndNurse=list.get(0);
            logger.debug(relationOfSchoolAndNurse.toString());
            Driver driver=driverService.find(relationOfSchoolAndNurse.getDriverId());
            if (driver!=null){
                jsonArray.add(driver);
            }
        }
        return JsonResult.success("",jsonArray.toString());
    }
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(ModelMap modelMap){
        User user=getUser();
        Role role=user.getRoles().iterator().next();

        if (role.getId()!=1){
            modelMap.put("schoolId",role.getSchoolId());
            return "admin/nurse/form";
        }
        else {
            modelMap.put("schoolId","");
            modelMap.put("roleId",role.getId());
            return "admin/nurse/adminform";
        }

    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {

        Nurse nurse = nurseService.find(id);
        map.put("nurse", nurse);
        return "admin/nurse/form";
    }
    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit( Nurse nurse, ModelMap map) {
        try {
            nurseService.saveOrUpdate(nurse);
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id){
        try {
            nurseService.delete(id);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
    @RequestMapping(value = "nurseList",method = RequestMethod.GET)
    @ResponseBody
    public  JsonResult nurseList(){
        User user=getUser();
        List<Nurse> list;
        Role role=user.getRoles().iterator().next();
        JSONArray jsonArray=new JSONArray();
        if(role.getId()==1){

            list=nurseService.findAll();
        }
        else {
            list=nurseService.findBySchoolId(role.getSchoolId());
        }
        for (Nurse nurse :list){
            jsonArray.add(nurse);
        }

        return JsonResult.success("",jsonArray.toString());
    }
    @RequestMapping(value = "nurseList/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  JsonResult nurseListBySchoolId(@PathVariable Integer id){
        List<Nurse> list;
        JSONArray jsonArray=new JSONArray();

            list=nurseService.findBySchoolId(id);
        for (Nurse nurse :list){
            jsonArray.add(nurse);
        }

        return JsonResult.success("",jsonArray.toString());
    }
}

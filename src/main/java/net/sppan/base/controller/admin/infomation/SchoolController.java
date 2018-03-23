package net.sppan.base.controller.admin.infomation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.sppan.base.Application;
import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.dao.ISchoolDao;
import net.sppan.base.entity.*;
import net.sppan.base.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin/school")
public class SchoolController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    ISchoolService schoolService;
    @Autowired
    ISchoolDao schoolDao;
    @Autowired
    IDriverService driverService;
    @Autowired
    IBusService iBusService;
    @Autowired
    INurseService nurseService;
    @Autowired
    IRelationOfSchoolAndNurseService schoolAndNurseService;
    @RequestMapping("/index")
    public String index(ModelMap modelMap){

        Page<School> page = schoolService.findAll(getPageRequest());
        modelMap.put("pageInfo", page);
        return "admin/school/index";
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap modelMap) {
        return "admin/school/form";
    }
    @RequestMapping(value = "/grant/{id}")
    public String grant(@PathVariable Integer id,ModelMap modelMap) {
        Nurse nurse;
        Driver driver;
        Bus bus;
        List<RelationOfSchoolAndNurse>  list;
        List<SchoolGrantInfo> schoolGrantInfos=new ArrayList<>();
        SchoolGrantInfo schoolGrantInfo;
          list=  schoolAndNurseService.findBySchoolId(id);

            for (RelationOfSchoolAndNurse relationOfSchoolAndNurse:list){
                schoolGrantInfo=new SchoolGrantInfo(relationOfSchoolAndNurse);
                bus= iBusService.find(relationOfSchoolAndNurse.getBusId());
                if (bus!=null) {
                    schoolGrantInfo.setBusNumber(bus.getNumber());
                }
                driver=driverService.find(relationOfSchoolAndNurse.getDriverId());

                if (driver!=null){ schoolGrantInfo.setDriverName(driver.getName());}
                nurse=nurseService.find(relationOfSchoolAndNurse.getNurseId());
                if (nurse!=null) {
                    schoolGrantInfo.setNurseName(nurse.getName());
                }
                schoolGrantInfos.add(schoolGrantInfo);
            }
            Page<SchoolGrantInfo> pageInfo=new PageImpl<>(schoolGrantInfos,getPageRequest(),schoolGrantInfos.size());
            modelMap.put("pageInfo",pageInfo);
            return "admin/school/grant";

    }

    @RequestMapping(value = "/grant/edit/{id}")
    public  String grantEdit(@PathVariable Integer id,ModelMap map){
        RelationOfSchoolAndNurse relationOfSchoolAndNurse=  schoolAndNurseService.find(id);
        SchoolGrantInfo schoolGrantInfo=new SchoolGrantInfo(relationOfSchoolAndNurse);
        Bus bus=iBusService.find(schoolGrantInfo.getBusId());
        if (bus!=null){
            schoolGrantInfo.setBusNumber(bus.getNumber());

        }
        Nurse nurse=nurseService.find(schoolGrantInfo.getNurseId());
        if (nurse!=null){
            schoolGrantInfo.setNurseName(nurse.getName());
        }

        Driver driver=driverService.find(schoolGrantInfo.getDriverId());
    if (driver!=null){
        schoolGrantInfo.setDriverName(driver.getName());
        }
        map.put("pageInfo",schoolGrantInfo);
            logger.debug("      dsadas++"+schoolGrantInfo.toString());
        return  "admin/school/grant_form";
    }
    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit( School School, ModelMap map) {
        try {
            schoolService.saveOrUpdate(School);
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {

        School school = schoolService.find(id);
        map.put("school", school);
        return "admin/school/form";
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id){
        try {
            schoolService.delete(id);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @RequestMapping(value = "/grant/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public  JsonResult grantDelete(@PathVariable Integer id){
        try {
            schoolAndNurseService.delete(id);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
    @RequestMapping(value = "/schoolList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult schoolList(){

        List<School> list= schoolService.findAll();
        JSONObject jsonObject=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        for (int i = 0; i <list.size() ; i++) {
            jsonArray.add(i,list.get(i));

        }
        return JsonResult.success("",jsonArray.toString());
    }
    @RequestMapping(value = "/grant/add",method = RequestMethod.GET)
    public  String  grant_add(){

        return "admin/school/grant_form";
    }
    @RequestMapping(value = {"/grant/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult grantEdit( RelationOfSchoolAndNurse relationOfSchoolAndNurse, ModelMap map) {
        try {
            logger.debug("dasdsa+++"+relationOfSchoolAndNurse.toString());
            schoolAndNurseService.saveOrUpdate(relationOfSchoolAndNurse);
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
}

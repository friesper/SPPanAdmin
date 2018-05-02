package net.sppan.base.controller.admin.infomation;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.sppan.base.Application;
import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.dao.IRelationAndBusDao;
import net.sppan.base.entity.*;
import net.sppan.base.service.IRelationAndBusService;
import net.sppan.base.service.IRelationOfSchoolAndNurseService;
import net.sppan.base.service.impl.BusServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/bus")
public class BusController extends BaseController {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    BusServiceImpl busService;
    @Autowired
    IRelationAndBusDao iRelationAndBusDao;
    @Autowired
    IRelationAndBusService relationAndBusService;
    @Autowired
    IRelationOfSchoolAndNurseService relationOfSchoolAndNurseService;

    @RequestMapping(value = "/index")
    public String index(ModelMap modelMap){
        User user=getUser();
        Page<Bus> page;
        Role role=user.getRoles().iterator().next();
        List<Integer> list=new ArrayList<>();
        if (role.getId()!=1){
          List<RelationOfSchoolAndBus> list1= relationAndBusService.findBySchoolId(role.getSchoolId());
            for (RelationOfSchoolAndBus relationOfSchoolAndBus:list1){
                list.add(relationOfSchoolAndBus.getBusId());
            }
            page=busService.findById(list,getPageRequest());
        }else {
            page=busService.findAll(getPageRequest());
        }
        modelMap.put("pageInfo",page);
        return "admin/bus/index";

    }
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(){
        if (getUser().getRoles().iterator().next().getId()==1){
            return  "admin/bus/adminform";
        }
        else {

        return "admin/bus/form";
        }
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id){
        try {
            busService.delete(id);
            relationAndBusService.deleteAllByBusid(id);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable Integer id,ModelMap modelMap) {
        Bus bus = busService.find(id);
        modelMap.put("bus", bus);
        RelationOfSchoolAndBus relationOfSchoolAndBus=relationAndBusService.findByBusId(id);
        int schoolId=relationOfSchoolAndBus.getSchoolId();
        modelMap.put("schoolId",schoolId);
        if (getUser().getRoles().iterator().next().getId() == 1) {
            return "admin/bus/adminform";
        } else {
            return "admin/bus/form";
        }
    }
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(ReqBus reqBus){

        try {
            Bus bus=new Bus();
            bus.setNumber(reqBus.getNumber());
            bus.setId(reqBus.getId());
            if (reqBus.getSchoolId()==null){
                reqBus.setSchoolId(getUser().getRoles().iterator().next().getSchoolId());
            }
            busService.saveOrUpdate(bus,reqBus.getSchoolId());
            logger.debug(reqBus.toString());

        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }


    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult findAll(){
        List<Bus> list=busService.findAll();
        JSONArray jsonArray=new JSONArray();
        for (int i = 0; i <list.size() ; i++) {
            jsonArray.add(i,list.get(i));

        }
        return JsonResult.success("",jsonArray.toString());

    }
    @RequestMapping(value = "/findById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  JsonResult findByid(@PathVariable Integer id){
        Bus bus=busService.find(id);
        JSONArray jsonArray=new JSONArray();
        jsonArray.add(bus);
        return JsonResult.success("",jsonArray.toString());
    }

    @RequestMapping(value = "/busList",method = RequestMethod.GET)
    @ResponseBody
    public  JsonResult busList(){
        User user=getUser();
        List<Bus> list;
        JSONArray jsonArray=new JSONArray();
        Role role=user.getRoles().iterator().next();
        if (role.getId()==1){
            list=busService.findAll();
        }
        else {
         List<RelationOfSchoolAndBus>  list1=  iRelationAndBusDao.findAllBySchoolId(role.getSchoolId());
         list=new ArrayList<Bus>();
         for (RelationOfSchoolAndBus relationOfSchoolAndBus:list1){
                list.add(busService.find(relationOfSchoolAndBus.getBusId()));
            }
        }
        for (int i=0;i<list.size();i++){
            jsonArray.add(list.get(i));
        }
        return JsonResult.success("",jsonArray.toString());
    }
    @RequestMapping(value = "/busList/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  JsonResult busListBySchool(@PathVariable Integer id){
        List<Bus> list;
        JSONArray jsonArray=new JSONArray();
            List<RelationOfSchoolAndBus>  list1=  iRelationAndBusDao.findAllBySchoolId(id);
            list=new ArrayList<Bus>();
            for (RelationOfSchoolAndBus relationOfSchoolAndBus:list1){
                list.add(busService.find(relationOfSchoolAndBus.getBusId()));
            }
        for (int i=0;i<list.size();i++){
            jsonArray.add(list.get(i));
        }
        return JsonResult.success("",jsonArray.toString());
    }



}

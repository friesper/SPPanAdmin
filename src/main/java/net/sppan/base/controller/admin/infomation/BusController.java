package net.sppan.base.controller.admin.infomation;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.dao.IRelationAndBusDao;
import net.sppan.base.entity.*;
import net.sppan.base.service.IRelationAndBusService;
import net.sppan.base.service.impl.BusServiceImpl;
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

    @Autowired
    BusServiceImpl busService;
    @Autowired
    IRelationAndBusDao iRelationAndBusDao;
    @Autowired
    IRelationAndBusService relationAndBusService;

    @RequestMapping(value = "/index")
    public String index(ModelMap modelMap){
        User user=getUser();
        Role role=user.getRoles().iterator().next();
        List<Bus> list=new ArrayList<>();
        if (role.getId()!=1){
          List<RelationOfSchoolAndBus> list1= relationAndBusService.findBySchoolId(role.getSchoolId());
            for (RelationOfSchoolAndBus relationOfSchoolAndBus:list1){
                list.add(busService.find(relationOfSchoolAndBus.getBusId()));
            }

        }else {
            list=busService.findAll();
        }
        Page<Bus> page=new PageImpl<>(list,getPageRequest(),list.size());
        modelMap.put("pageInfo",page);
        return "admin/bus/index";

    }
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(){


        return "admin/bus/form";
    }
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable Integer id,ModelMap modelMap){
        Bus bus=busService.find(id);
        modelMap.put("bus",bus);
        return "admin/bus/form";

    }
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(Bus bus){

        try {
            Integer schoolId=0;
            schoolId=getUser().getRoles().iterator().next().getSchoolId();
            busService.saveOrUpdate(bus,schoolId);
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
         List<RelationOfSchoolAndBus>  list1=  iRelationAndBusDao.findBySchoolId(role.getSchoolId());
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



}

package net.sppan.base.controller.admin.infomation;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.Bus;
import net.sppan.base.service.impl.BusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/info/bus")
public class BusController extends BaseController {

    @Autowired
    BusServiceImpl busService;
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



}

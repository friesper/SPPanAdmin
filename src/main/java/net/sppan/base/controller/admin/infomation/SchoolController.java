package net.sppan.base.controller.admin.infomation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.sppan.base.Application;
import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.dao.ISchoolDao;
import net.sppan.base.entity.Driver;
import net.sppan.base.entity.School;
import net.sppan.base.service.ISchoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @RequestMapping("/index/")
    public String index(ModelMap modelMap){

        Page<School> page = schoolService.findAll(getPageRequest());
        modelMap.put("pageInfo", page);
        return "admin/school/index";
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap modelMap) {
        return "admin/school/form";
    }
    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(School school, ModelMap modelMap) {
        try {
            schoolService.saveOrUpdate(school);
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id, ModelMap map) {
        try {
            schoolService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
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
    @RequestMapping(value = "/grant",method = RequestMethod.POST)
    @ResponseBody
    public String grant(@PathVariable Integer id, ModelMap map){

        School school = schoolService.find(id);
        map.put("role", school);
        return "admin/role/grant";
    }


}

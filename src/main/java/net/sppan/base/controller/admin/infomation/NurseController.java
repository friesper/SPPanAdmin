package net.sppan.base.controller.admin.infomation;

import com.alibaba.fastjson.JSONArray;
import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.Nurse;
import net.sppan.base.entity.RelationOfSchoolAndNurse;
import net.sppan.base.entity.Role;
import net.sppan.base.entity.User;
import net.sppan.base.service.IDriverService;
import net.sppan.base.service.INurseService;
import net.sppan.base.service.IRelationOfSchoolAndNurseService;
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
    @Autowired
    private Environment env;
    @Autowired
    private INurseService nurseService;
    @Autowired
    IRelationOfSchoolAndNurseService relationOfSchoolAndNurseService;
    @RequestMapping("/index")
    public String index( ModelMap modelMap){
        int schoolId=0,roleId;
        Page<Nurse > page;
        User user=getUser();
        roleId=user.getRoles().iterator().next().getId();
        if (roleId==1){
            page=nurseService.findAll(getPageRequest());
        }
        else {
            HashSet<Integer> ids = new HashSet<Integer>();
            Set<Role> roles=user.getRoles();
            for (Role role:roles){
                schoolId= role.getSchoolId();
                List<RelationOfSchoolAndNurse> list = relationOfSchoolAndNurseService.findNurseBySchoolId(schoolId);
                for (int i=0;i<list.size();i++) {
                    ids.add(list.get(i).getNurseId());
                }
            }
            page = nurseService.findById(ids, getPageRequest());
        }
        modelMap.put("pageInfo", page);
        return  "/admin/nurse/index";
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
        return "admin/nurse/form";
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
}

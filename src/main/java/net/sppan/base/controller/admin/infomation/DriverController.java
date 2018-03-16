package net.sppan.base.controller.admin.infomation;

import net.sppan.base.Application;
import net.sppan.base.controller.BaseController;
import net.sppan.base.dao.IDriverDao;
import net.sppan.base.entity.Driver;
import net.sppan.base.service.IDriverService;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
@RequestMapping("/admin/driver")
public class DriverController extends BaseController {
    @Autowired
    private IDriverDao iDriverDao;
    @Autowired
    private IDriverService iDriverService;
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);

    @RequestMapping(value = {"/index"})
    public String index(ModelMap modelMap) {
       Page<Driver> page = iDriverService.findAll(getPageRequest());
        modelMap.put("pageInfo", page);
        return "admin/driver/index";
    }
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(ModelMap modelMap){
        List<Driver>  drivers=iDriverService.findAll();
        modelMap.put("list",drivers);
        return "admin/resource/form";
    }

}

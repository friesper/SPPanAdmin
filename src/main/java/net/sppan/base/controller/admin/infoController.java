package net.sppan.base.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.sppan.base.Application;
import net.sppan.base.common.JsonResult;
import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.BusInfo;
import net.sppan.base.entity.ReqBusInfo;
import net.sppan.base.entity.ReqStatusInfo;
import net.sppan.base.entity.StudentStatus;
import net.sppan.base.service.IBusInfoService;
import net.sppan.base.service.IBusService;
import net.sppan.base.service.IStudentStatusService;
import org.joda.time.DateTime;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/info")
public class infoController extends BaseController {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    IBusInfoService busInfoService;
    @Autowired
    IStudentStatusService studentStatusService;
    @Autowired
    IBusService busService;
    @RequestMapping("/index")
    public String  index(){
    return "admin/info/index";
    }
    @RequestMapping("/bus/index")
    public String  busindex(){
        return "admin/info/bus/index";
    }
    @RequestMapping("/student/find/{id}/{date}")
    public  String find(@PathVariable Integer id, @PathVariable Date date, ModelMap modelMap){
       List<StudentStatus> list= studentStatusService.findAllByBusIdAndTakeTime(id,date);
        Page<StudentStatus> page=new PageImpl<StudentStatus>(list,getPageRequest(),list.size());
        modelMap.put("pageInfo",page);
        modelMap.put("urls",request.getRequestURI());
        logger.debug("dasd"+request.getRequestURI());
        /*modelMap.put("request",request);*/
        return "admin/info/studentForm";
    }
    @RequestMapping("/mobile/student/find/{id}/{date}")
    @ResponseBody
    public  JsonResult findmobile(@PathVariable Integer id, @PathVariable Date date, ModelMap modelMap){
        List<StudentStatus> list= studentStatusService.findAllByBusIdAndTakeTime(id,date);
        JSONArray jsonArray=new JSONArray();
        for (int i = 0; i <list.size() ; i++) {
            jsonArray.add(i,list.get(i));
        }
        return JsonResult.success("",jsonArray.toString());
    }
    @RequestMapping("/student/delete/{id}")
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id){

        try {
            studentStatusService.delete(id);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();

    }
    @RequestMapping("/bus/delete/{id}")
    @ResponseBody
    public JsonResult busdelete(@PathVariable Integer id){

        try {
            busInfoService.delete(id);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();

    }
    @RequestMapping("/bus/find/{id}/{date}")
    public  String busfind(@PathVariable Integer id, @PathVariable Date date, ModelMap modelMap){

        List<BusInfo> list= busInfoService.findAllByBusIdAndCreateTime(id,date);
        Page<BusInfo> page=new PageImpl<>(list,getPageRequest(),list.size());
        modelMap.put("pageInfo",page);
        return "admin/info/bus/busForm";
    }
    @RequestMapping("/bus/info/find/{id}/{date}")
    @ResponseBody
    public JsonResult busInfo(@PathVariable Integer id,@PathVariable Date date){
        List<BusInfo> list= busInfoService.findAllByBusIdAndCreateTime(id,date);
        JSONArray jsonArray=new JSONArray();
        for (int i = 0; i <list.size() ; i++) {
            jsonArray.add(i,list.get(i));
        }
        return JsonResult.success("",jsonArray.toString());

    }
    @RequestMapping(value = "/bus/info/upload" ,method = RequestMethod.POST)
    @ResponseBody
    public JsonResult busInfoUpload(@RequestBody ReqBusInfo reqbusInfo){

        logger.debug("dasdwad               "+reqbusInfo.getAirFilter());
        BusInfo busInfo=new BusInfo();
        busInfo.setAirFilter(reqbusInfo.getAirFilter());
        busInfo.setAmountOfAntifreeze(reqbusInfo.getAmountOfAntifreeze());
        busInfo.setBakeFluid(reqbusInfo.getBakeFluid());
        busInfo.setBatteryHealth(reqbusInfo.getBatteryHealth());
        busInfo.setBeltTightness(reqbusInfo.getBeltTightness());
        busInfo.setBrake(reqbusInfo.getBrake());
        busInfo.setClutch(reqbusInfo.getClutch());
        busInfo.setCreateTime(reqbusInfo.getCreateTime());
        busInfo.setEngineHygiene(reqbusInfo.getEngineHygiene());
        busInfo.setEscapeDoor(reqbusInfo.getEscapeDoor());
        busInfo.setFireExtinguisher(reqbusInfo.getFireExtinguisher());
        busInfo.setGpsMonitoring(reqbusInfo.getGpsMonitoring());
        busInfo.setGuideBoard(reqbusInfo.getGuideBoard());
        busInfo.setInstrumentPanel(reqbusInfo.getInstrumentPanel());
        busInfo.setLights(reqbusInfo.getLights());
        busInfo.setMedicineBox(reqbusInfo.getMedicineBox());
        busInfo.setOillOilLevel(reqbusInfo.getOillOilLevel());
        busInfo.setSafetyHammer(reqbusInfo.getSafetyHammer());
        busInfo.setSteeringWheel(reqbusInfo.getSteeringWheel());
        busInfo.setTirePressureScrews(reqbusInfo.getTirePressureScrews());
        busInfo.setBusId(reqbusInfo.getBusId());
        busInfo.setBusNumber(reqbusInfo.getBusNumber());
        busInfo.setDriverName(reqbusInfo.getDriverName());
        busInfoService.save(busInfo);
        return JsonResult.success("");

    }
    @RequestMapping(value = "/status/info/upload" ,method = RequestMethod.POST)
    @ResponseBody
    public JsonResult busInfoUpload(@RequestBody List<ReqStatusInfo> reqStatusInfos){
        StudentStatus studentStatus;
        ReqStatusInfo reqStatusInfo;
        try {

            logger.debug("sdasdasd                    reqstatusinfo      " + reqStatusInfos.size());
            for (int i=0;i<reqStatusInfos.size();i++){
                logger.debug("sadsadad"+reqStatusInfos.get(i).toString());
                reqStatusInfo=reqStatusInfos.get(i);
              studentStatus=new StudentStatus();
              studentStatus.setTakeTime(reqStatusInfo.getTakeTime());
              studentStatus.setBusId(reqStatusInfo.getBusId());
              studentStatus.setBusNumber(reqStatusInfo.getBusNumber());
              studentStatus.setDriverId(reqStatusInfo.getDriverId());
              studentStatus.setDriverName(reqStatusInfo.getDriverName());
              studentStatus.setNurseId(reqStatusInfo.getNurseId());
              studentStatus.setNurseName(reqStatusInfo.getNurseName());
              studentStatus.setStatus(reqStatusInfo.getStatus());
              studentStatus.setStudentName(reqStatusInfo.getStudentName());
              studentStatus.setStudentPhone(reqStatusInfo.getStudentPhone());
              studentStatus.setTimeQuantum(reqStatusInfo.getTimeQuantum());
              studentStatusService.saveOrUpdate(studentStatus);
            }

        }
        catch (Exception e){
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success("success","");

    }

}

package net.sppan.base.controller.admin;

import com.alibaba.fastjson.JSONArray;
import net.sppan.base.Application;
import net.sppan.base.common.JsonResult;
import net.sppan.base.common.utils.ExportBusExcel;
import net.sppan.base.common.utils.ExportExcel;
import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.*;
import net.sppan.base.service.IBusInfoService;
import net.sppan.base.service.IBusService;
import net.sppan.base.service.IRelationAndBusService;
import net.sppan.base.service.IStudentStatusService;
import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/admin/info")
public class infoController extends BaseController {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    IBusInfoService busInfoService;
    @Autowired
    private Environment env;
    @Autowired
    IStudentStatusService studentStatusService;
    @Autowired
    IRelationAndBusService relationAndBusService;
    @Autowired
    IBusService busService;
    public  infoController(){

    }
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
        Page<StudentStatus> page= studentStatusService.findAllByBusIdAndTakeTime(id,date,getPageRequest());
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
    @RequestMapping(value = "/status/info/getExcel" ,method = RequestMethod.GET)
    @ResponseBody
    public void getExcele( HttpServletRequest request, HttpServletResponse response) throws IOException {
        ExportExcel exportExcel;
       int roleId=getUser().getRoles().iterator().next().getId();
        ArrayList<String> createFilesPath=new ArrayList<String>();
           response.setCharacterEncoding(request.getCharacterEncoding());
           response.setContentType("application/octet-stream");
           Date date = new Date();
           Calendar cal = Calendar.getInstance();
           cal.setTime(date);
           int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
           cal.add(Calendar.DATE, -w);
           Date startdate = cal.getTime();
           List<StudentStatus> list;
        if (roleId!=1) {
            List<RelationOfSchoolAndBus> relationOfSchoolAndBuses = relationAndBusService.findBySchoolId(getUser().getRoles().iterator().next().getSchoolId());
            for (RelationOfSchoolAndBus r : relationOfSchoolAndBuses) {
                exportExcel=new ExportExcel();
                list = studentStatusService.findByBusIdBetweenDate(r.getBusId(), startdate, date);
                createFilesPath.add(exportExcel.createStudentstatusExcel(list));

            }
        }
        else {
            List<Bus> list1=busService.findAll();
            for (Bus b:list1){
                exportExcel=new ExportExcel();
                list = studentStatusService.findByBusIdBetweenDate(b.getId(), startdate, date);
                createFilesPath.add(exportExcel.createStudentstatusExcel(list));
            }

        }
        exportZip("校车接送记录.zip",response, createFilesPath);
           // FileSystemResource file = new FileSystemResource(filePath);


        /*HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        Resource resource = new InputStreamResource(file.getInputStream());
        return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/x-msdownload")).body(resource);
*/


    }

    private void exportZip(String name,HttpServletResponse response, ArrayList<String> createFilesPath) {
        byte[] buffer = new byte[1024];
        String zipPath=env.getProperty("zipPath");
        String zipName=name;
        String strZipPath=zipPath+"/"+zipName;
        try {
            File tmpZip=new File(zipPath);
            if (!tmpZip.exists())
                tmpZip.mkdirs();
            File tmpZipFile = new File(strZipPath);
            if (!tmpZipFile.exists())
                tmpZipFile.createNewFile();

            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipPath));
            // 需要同时下载的两个文件result.txt ，source.txt

            File[] file1 =new File[createFilesPath.size()] ;

            for(int i=0;i<createFilesPath.size();i++){
                file1[i]=new File(createFilesPath.get(i));
            }
            for (int i = 0; i < file1.length; i++) {
                FileInputStream fiss = new FileInputStream(file1[i]);
                out.putNextEntry(new ZipEntry(file1[i].getName()));
                //设置压缩文件内的字符编码，不然会变成乱码
                int len;
                // 读入需要下载的文件的内容，打包到zip文件
                while ((len = fiss.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                out.closeEntry();
                fiss.close();
            }
            out.close();
            this.downloadFile(zipPath,zipName,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/status/info/getBusInfoExcel" ,method = RequestMethod.GET)
    @ResponseBody
    public void getbusExcele( HttpServletRequest request, HttpServletResponse response) throws IOException {
        ExportBusExcel exportExcel;
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        ArrayList<String> createFilesPath=new ArrayList<String>();
        String  filename;
        Date date=new Date();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH,1);
        Date startdate=cal.getTime();
        Bus bus;
        int id=getUser().getRoles().iterator().next().getId();
        List<BusInfo> list;
        if (id==1){
            List<Bus> list1=busService.findAll();
            for (Bus bus1:list1){
                exportExcel=new ExportBusExcel();
                list=busInfoService.findAllByBusIdAndCreateTimeBetween(bus1.getId(),startdate,date);
                createFilesPath.add(exportExcel.createBusInfoExcel(list));
            }
        }
        else {
            List<RelationOfSchoolAndBus> relationOfSchoolAndBuses=relationAndBusService.findBySchoolId(getUser().getRoles().iterator().next().getSchoolId());
            for (RelationOfSchoolAndBus r:relationOfSchoolAndBuses){
                exportExcel=new ExportBusExcel();
                createFilesPath.add(exportExcel.createBusInfoExcel(busInfoService.findAllByBusIdAndCreateTimeBetween(r.getBusId(),startdate,date)));
            }

        }
        exportZip("校车例检.zip",response, createFilesPath);

        /*List<RelationOfSchoolAndBus> relationOfSchoolAndBuses=relationAndBusService.findBySchoolId(getUser().getRoles().iterator().next().getSchoolId());
        for ( RelationOfSchoolAndBus  a:relationOfSchoolAndBuses){
             bus=busService.find(a.getBusId());
            List<BusInfo> list=busInfoService.findAllByBusIdAndCreateTimeBetween(bus.getId(),startdate,date);
        }*/


    }
    public void downloadFile(String filePath,String fileName,HttpServletResponse response){

        response.setCharacterEncoding("utf-8");
        // response.setContentType("application/octet-stream");

        try {
            File file=new File(filePath,fileName);
            // 以流的形式下载文件。
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            String agent = request.getHeader("USER-AGENT");
            if(agent != null && agent.indexOf("MSIE") == -1) {// FF
                String enableFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
                response.setHeader("Content-Disposition", "attachment; filename=" + enableFileName);
            } else { // IE
                String enableFileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
                response.setHeader("Content-Disposition", "attachment; filename=" + enableFileName);
            }
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

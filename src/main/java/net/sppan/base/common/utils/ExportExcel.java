package net.sppan.base.common.utils;

import net.sppan.base.Application;
import net.sppan.base.entity.BusInfo;
import net.sppan.base.entity.StudentStatus;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExportExcel {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);
    HSSFCellStyle a_cellStyle;
    Workbook workbook;
    public String createStudentstatusExcel(List<StudentStatus> arrayList) throws IOException{
        /**
         * 插入数据从第六行开始  index=5;
         *
         * */

         workbook = initFile();
        insertInfo(arrayList,workbook.getSheet("info"));
        String filename="校车接送记录"+arrayList.get(0).getBusNumber()+".xls";
        String rootpath="D:/OTA/download/";
        File fluteFile=new File(rootpath);
        if (!fluteFile.exists()) {
            fluteFile.mkdirs();
        }
        File file=new File(rootpath+filename);
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        logger.debug("write successful");
        return filename;
    }
    public String createBusInfoExcel() throws IOException{
        initBushFile();

        String filename="校车例检表.xls";
        String rootpath="D:/OTA/download/";
        File fluteFile=new File(rootpath);
        if (!fluteFile.exists()) {
            fluteFile.mkdirs();
        }
        File file=new File(rootpath+filename);
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        logger.debug("write successful");
        return filename;
    }
    public  ExportExcel(){
         workbook=new HSSFWorkbook();
        a_cellStyle= (HSSFCellStyle) workbook.createCellStyle();
        a_cellStyle.setBorderBottom(BorderStyle.THIN);
        a_cellStyle.setBorderLeft(BorderStyle.THIN);
        a_cellStyle.setAlignment(HorizontalAlignment.CENTER);
        a_cellStyle.setBorderRight(BorderStyle.THIN);
        a_cellStyle.setBorderTop(BorderStyle.THIN);
    }

    private Workbook initBushFile() {
        CellRangeAddress cellRangeAddress;
        /* 设置字体大小*/
        Font fontStyle = workbook.createFont();
        fontStyle.setBold(true);
        fontStyle.setFontName("黑体");
        Sheet sheet=workbook.createSheet("info");
        Row row=sheet.createRow(0);
        Cell cell=row.createCell(0);
        cell.setCellValue("校车例检表");
        cell.setCellStyle(a_cellStyle);
        cellRangeAddress=new CellRangeAddress(0,0,0,22);
        sheet.addMergedRegion(cellRangeAddress);
        setBorderBottoms(cellRangeAddress,sheet);
        row=sheet.createRow(1);
        cell=row.createCell(0);
        cell.setCellValue("车号");
        cell.setCellStyle(a_cellStyle);
        cellRangeAddress=new CellRangeAddress(1,1,1,8);
        sheet.addMergedRegion(cellRangeAddress);
        setBorderBottoms(cellRangeAddress,sheet);
        cell=row.getCell(9);
        cell.setCellValue("服务学校");
        cell.setCellStyle(a_cellStyle);
        cellRangeAddress=new CellRangeAddress(1,1,10,21);
        sheet.addMergedRegion(cellRangeAddress);
        setBorderBottoms(cellRangeAddress,sheet);
        row=sheet.createRow(2);
        initBusInfoRow( row);
        return  workbook;

    }
    private void initBusInfoRow(Row row){
        String[] strings={"发动机卫生","空滤卫生","电瓶卫生","药品箱","gps和监控","灭火器","应急门","安全锤","机油量","防冻液量","制动液","皮带松紧度","轮胎气压及螺丝","灯光","路牌","离合",
                "制动器","方向盘","仪表盘"};
        Cell cell;
        for (int i=0;i<strings.length;i++){
            cell=row.createCell(i+1);
            cell.setCellValue(strings[i]);
            cell.setCellStyle(a_cellStyle);
        }
    }
    private Workbook initFile() {
        Workbook workbook=new HSSFWorkbook();
        HSSFCellStyle cellStyle= (HSSFCellStyle) workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
         a_cellStyle= (HSSFCellStyle) workbook.createCellStyle();
        HSSFCellStyle b_cellStyle= (HSSFCellStyle) workbook.createCellStyle();
        CellRangeAddress cellRangeAddress;
        a_cellStyle.setBorderBottom(BorderStyle.THIN);
        a_cellStyle.setBorderLeft(BorderStyle.THIN);
        a_cellStyle.setAlignment(HorizontalAlignment.CENTER);
        a_cellStyle.setBorderRight(BorderStyle.THIN);
        a_cellStyle.setBorderTop(BorderStyle.THIN);

        /* 设置字体大小*/
        Font fontStyle = workbook.createFont();
        fontStyle.setBold(true);
        fontStyle.setFontName("黑体");
        fontStyle.setFontHeightInPoints((short) 24);
        Sheet sheet=workbook.createSheet("info");
        Row row=sheet.createRow(0);
        Cell cell=row.createCell(0);
        cell.setCellValue("校车接送记录");
        cellStyle.setFont(fontStyle);
        cell.setCellStyle(cellStyle);
        cellRangeAddress=new CellRangeAddress(0,0,0,11);
        sheet.addMergedRegion(cellRangeAddress);
        row=sheet.createRow(1);
        cell=row.createCell(0);
        Font a_fontStyle = workbook.createFont();
        a_cellStyle.setFont(a_fontStyle);
        cell.setCellValue("学校名称:");
        cellRangeAddress=new CellRangeAddress(1,1,1,3);
        sheet.addMergedRegion(cellRangeAddress);
        cell=row.createCell(4);
        cell.setCellValue("车牌号:");
        cellRangeAddress=new CellRangeAddress(1,1,5,8);
        sheet.addMergedRegion(cellRangeAddress);
        cell=row.createCell(5);
        cell=row.createCell(9);
        cell.setCellValue("日期:");
        cellRangeAddress=new CellRangeAddress(1,1,10,11);
        sheet.addMergedRegion(cellRangeAddress);
        for (int i=2;i<5;i++){
            row=sheet.createRow(i);
            for (int m=0;m<12;m++){
                cell=row.createCell(m);
                cell.setCellStyle(a_cellStyle);
            }
        }
        row=sheet.createRow(2);
        cell=row.createCell(0);
        cell.setCellValue("学生姓名:");
        cellRangeAddress=new CellRangeAddress(2,4,0,0);
        setBorderBottoms(cellRangeAddress,sheet);
        sheet.addMergedRegion(cellRangeAddress);
        cell=row.createCell(1);
        cell.setCellValue("联系电话:");
        cellRangeAddress=new CellRangeAddress(2,4,1,1);
        sheet.addMergedRegion(cellRangeAddress);
        setBorderBottoms(cellRangeAddress,sheet);
        cell=row.createCell(2);
        cell.setCellValue("第   周");
        cellRangeAddress=new CellRangeAddress(2,2,2,11);
        sheet.addMergedRegion(cellRangeAddress);
        setBorderBottoms(cellRangeAddress,sheet);
        row=sheet.getRow(3);
        cell=row.createCell(2);
        cell.setCellValue("星期一");
        cellRangeAddress=new CellRangeAddress(3,3,2,3);
        sheet.addMergedRegion(cellRangeAddress);
        setBorderBottoms(cellRangeAddress,sheet);
        cell.setCellStyle(a_cellStyle);
        cell=row.createCell(4);
        cell.setCellValue("星期二");
        cellRangeAddress=new CellRangeAddress(3,3,4,5);
        sheet.addMergedRegion(cellRangeAddress);
        setBorderBottoms(cellRangeAddress,sheet);
        cell.setCellStyle(a_cellStyle);
        cell=row.createCell(6);
        cell.setCellValue("星期三");
        cellRangeAddress=new CellRangeAddress(3,3,6,7);
        sheet.addMergedRegion(cellRangeAddress);
        setBorderBottoms(cellRangeAddress,sheet);
        cell.setCellStyle(a_cellStyle);
        cell=row.createCell(8);
        cell.setCellValue("星期四");
        cellRangeAddress=new CellRangeAddress(3,3,8,9);
        sheet.addMergedRegion(cellRangeAddress);
        setBorderBottoms(cellRangeAddress,sheet);
        cell.setCellStyle(a_cellStyle);
        cell=row.createCell(10);
        cell.setCellValue("星期五");
        cellRangeAddress=new CellRangeAddress(3,3,10,11);
        sheet.addMergedRegion(cellRangeAddress);
        setBorderBottoms(cellRangeAddress,sheet);
        cell.setCellStyle(a_cellStyle);
        row=sheet.getRow(4);
        for (int i=2;i<11;i++){
            if (i%2==0) {
                row.getCell(i).setCellValue("上午");
            }
            else {
                row.getCell(i).setCellValue("下午");
            }

        }
        return workbook;
    }

    public static void setBorderBottoms(CellRangeAddress cellRangeAddress,Sheet sheet){
        RegionUtil.setBorderBottom(BorderStyle.THIN,cellRangeAddress,sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, cellRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, cellRangeAddress, sheet);
        RegionUtil.setBorderTop(BorderStyle.THIN, cellRangeAddress, sheet);
    }
    public  void  insertInfo(List<StudentStatus> arrayList, Sheet sheet){
        HashMap<String,ArrayList<Integer>> hashMap=new HashMap<String, ArrayList<Integer>>();
        for (int i=0;i<arrayList.size();i++) {
            StudentStatus studentStatus=arrayList.get(i);

            if (hashMap.keySet().contains(studentStatus.getStudentName())){
                ArrayList<Integer>  excelArrayList=hashMap.get(studentStatus.getStudentName());
                int  weekdate=getWeekOfDate(studentStatus.getTakeTime());
                int timeqyuuer=studentStatus.getTimeQuantum();
                excelArrayList.add((weekdate-1)*2+timeqyuuer,studentStatus.getStatus());
            }
            else {
                ArrayList<Integer> excelArrayList;
                excelArrayList=new ArrayList<Integer>(11);
                for (int z=0;z<10;z++){
                    excelArrayList.add(0);
                }
                int  weekdate=getWeekOfDate(studentStatus.getTakeTime());
                int timeqyuuer=studentStatus.getTimeQuantum();
                excelArrayList.set((weekdate-1)*2+timeqyuuer,studentStatus.getStatus());

                hashMap.put(studentStatus.getStudentName(),excelArrayList);
            }
        }
        int  index=5;
        Iterator iterator=hashMap.keySet().iterator();
        while (iterator.hasNext()){
            ArrayList<Integer> arrayList1=hashMap.get(iterator.next());
            Row row=sheet.createRow(index);
            Cell cell;
            for (int i=0;i<arrayList1.size();i++){
                cell=row.createCell(i+2);
                if (arrayList1.get(i).equals(1)) {
                    cell.setCellValue("✓");
                    logger.debug("arrlist"+arrayList1.get(i)+"cell+"+cell.getAddress());
                }
                else {
                    cell.setCellValue("✗");
                }
                cell.setCellStyle(a_cellStyle);
            }
        }

    }
    public static int getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return w;
    }
}

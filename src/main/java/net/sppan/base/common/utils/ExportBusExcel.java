package net.sppan.base.common.utils;

import net.sppan.base.Application;
import net.sppan.base.entity.BusInfo;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static net.sppan.base.common.utils.ExportExcel.setBorderBottoms;

public class ExportBusExcel {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);

    HSSFCellStyle a_cellStyle;
    Workbook workbook;
    Calendar calendar;
    Sheet sheet;
    public String createBusInfoExcel(List<BusInfo> list) throws IOException {
        String filename;
        if (list.size()>0){
            filename=list.get(0).getBusNumber()+"校车例检表.xls";
        }else {
            filename = "校车例检表.xls";
        }
        String rootpath="D:/OTA/busInfo/";
        File fluteFile=new File(rootpath);
        if (!fluteFile.exists()) {
            fluteFile.mkdirs();
        }
        initBushFile(sheet);
        insertBusInfo(list,sheet);
        File file=new File(rootpath+filename);
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
        logger.debug("write successful");
        return file.getAbsolutePath();
    }
    private void insertBusInfo(List<BusInfo> list,Sheet sheet) {
        Row row;Cell cell;
        int i=0;
        for (BusInfo busInfo:list){
            calendar.setTime(busInfo.getCreateTime());
            row=sheet.createRow(i+3);
            cell=row.createCell(0);
            cell.setCellValue(calendar.get(Calendar.DAY_OF_MONTH)+"日");cell.setCellStyle(a_cellStyle);
            cell=row.createCell(1);cell.setCellValue(busInfo.getEngineHygiene());cell.setCellStyle(a_cellStyle);
            cell=row.createCell(2);cell.setCellValue(busInfo.getAirFilter());cell.setCellStyle(a_cellStyle);
            cell=row.createCell(3);cell.setCellValue(busInfo.getBatteryHealth());cell.setCellStyle(a_cellStyle);
            cell=row.createCell(4);cell.setCellValue(busInfo.getMedicineBox());cell.setCellStyle(a_cellStyle);
            cell=row.createCell(5);cell.setCellValue(busInfo.getGpsMonitoring());cell.setCellStyle(a_cellStyle);
            cell=row.createCell(6);cell.setCellValue(busInfo.getFireExtinguisher());cell.setCellStyle(a_cellStyle);
            cell=row.createCell(7);cell.setCellValue(busInfo.getEscapeDoor());cell.setCellStyle(a_cellStyle);
            cell=row.createCell(8);cell.setCellValue(busInfo.getSafetyHammer());cell.setCellStyle(a_cellStyle);
            cell=row.createCell(9);cell.setCellValue(busInfo.getOillOilLevel());cell.setCellStyle(a_cellStyle);
            cell=row.createCell(10);cell.setCellValue(busInfo.getAmountOfAntifreeze());cell.setCellStyle(a_cellStyle);
            cell=row.createCell(11);cell.setCellValue(busInfo.getBakeFluid());cell.setCellStyle(a_cellStyle);
            cell=row.createCell(12);cell.setCellValue(busInfo.getBeltTightness());cell.setCellStyle(a_cellStyle);
            cell=row.createCell(13);cell.setCellValue(busInfo.getTirePressureScrews());cell.setCellStyle(a_cellStyle);
            cell=row.createCell(14);cell.setCellValue(busInfo.getLights());cell.setCellStyle(a_cellStyle);
            cell=row.createCell(15);cell.setCellValue(busInfo.getGuideBoard());cell.setCellStyle(a_cellStyle);
            cell=row.createCell(16);cell.setCellValue(busInfo.getClutch());cell.setCellStyle(a_cellStyle);
            cell=row.createCell(17);cell.setCellValue(busInfo.getBrake());cell.setCellStyle(a_cellStyle);
            cell=row.createCell(18);cell.setCellValue(busInfo.getSteeringWheel());cell.setCellStyle(a_cellStyle);
            cell=row.createCell(19);cell.setCellValue(busInfo.getInstrumentPanel());cell.setCellStyle(a_cellStyle);
            i++;
        }

    }
    public  ExportBusExcel(){
        workbook=new HSSFWorkbook();
        calendar=Calendar.getInstance();
        sheet= workbook.createSheet("info");

        a_cellStyle= (HSSFCellStyle) workbook.createCellStyle();
        a_cellStyle.setBorderBottom(BorderStyle.THIN);
        a_cellStyle.setBorderLeft(BorderStyle.THIN);
        a_cellStyle.setAlignment(HorizontalAlignment.CENTER);
        a_cellStyle.setBorderRight(BorderStyle.THIN);
        a_cellStyle.setBorderTop(BorderStyle.THIN);
    }
    private Workbook initBushFile(Sheet sheet) {
        CellRangeAddress cellRangeAddress;
        /* 设置字体大小*/
        Font fontStyle = workbook.createFont();
        fontStyle.setBold(true);
        fontStyle.setFontName("黑体");
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
        cell=row.createCell(9);
        cell.setCellValue("服务学校");
        cell.setCellStyle(a_cellStyle);
        cellRangeAddress=new CellRangeAddress(1,1,10,21);
        sheet.addMergedRegion(cellRangeAddress);
        setBorderBottoms(cellRangeAddress,sheet);
        row=sheet.createRow(2);
        initBusInfoRow( row);
        return  workbook;

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
    private void initBusInfoRow(Row row){
        String[] strings={"发动机卫生","空滤卫生","电瓶卫生","药品箱","gps和监控","灭火器","应急门","安全锤","机油量","防冻液量","制动液","皮带松紧度","轮胎气压及螺丝","灯光","路牌","离合",
                "制动器","方向盘","仪表盘"};
        Cell cell;
        cell=row.createCell(0);cell.setCellValue("");cell.setCellStyle(a_cellStyle);
        for (int i=0;i<strings.length;i++){
            cell=row.createCell(i+1);
            cell.setCellValue(strings[i]);
            cell.setCellStyle(a_cellStyle);
        }
        cell=row.createCell(20);
        cell.setCellValue("驾驶员签字");
        cell.setCellStyle(a_cellStyle);
        cell=row.createCell(21);
        cell.setCellValue("备注");
        cell.setCellStyle(a_cellStyle);
    }

}

package com.chenxi.test.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ChoseMobileUtils {

    public static void createDianxinExcel(List<String>list){
        int rowNo = 0;
        int cellNo = 0;
        XSSFCell cell;
        XSSFRow row;
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        if (list != null && list.size() > 0) {
            for(int i = rowNo; i< list.size(); i ++) {
                row = sheet.createRow(i);//获得第i行
                cell = row.createCell(cellNo);//第i行第一个单元格
                cell.setCellValue(list.get(i));
            }
        }
        try {
            FileOutputStream fout = new FileOutputStream("C:\\Users\\dell\\Desktop\\电信MobileNo"+ new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xlsx");
            wb.write(fout);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void createLiantongExcel(List<String>list){
        int rowNo = 0;
        int cellNo = 0;
        XSSFCell cell;
        XSSFRow row;
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        if (list != null && list.size() > 0) {
            for(int i = rowNo; i< list.size(); i ++) {
                row = sheet.createRow(i);//获得第i行
                cell = row.createCell(cellNo);//第i行第一个单元格
                cell.setCellValue(list.get(i));
            }
        }
        try {
            FileOutputStream fout = new FileOutputStream("C:\\Users\\dell\\Desktop\\联通MobileNo"+ new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xlsx");
            wb.write(fout);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void createYidongExcel(List<String>list){
        int rowNo = 0;
        int cellNo = 0;
        XSSFCell cell;
        XSSFRow row;
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        if (list != null && list.size() > 0) {
            for(int i = rowNo; i< list.size(); i ++) {
                row = sheet.createRow(i);//获得第i行
                cell = row.createCell(cellNo);//第i行第一个单元格
                cell.setCellValue(list.get(i));
            }
        }
        try {
            FileOutputStream fout = new FileOutputStream("C:\\Users\\dell\\Desktop\\移动MobileNo"+ new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xlsx");
            wb.write(fout);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void createLiantongMD5MobileExcel(Map<String, String>map){
        int rowNo = 0;
        XSSFCell cell;
        XSSFRow row;
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
                row = sheet.createRow(rowNo);//获得第i行
                cell = row.createCell(0);//第i行第一个单元格
                cell.setCellValue(stringStringEntry.getKey());

                cell = row.createCell(1);//第i行第二个单元格
                cell.setCellValue(stringStringEntry.getValue());
                rowNo ++;
            }
        }
        try {
            FileOutputStream fout = new FileOutputStream("C:\\Users\\dell\\Desktop\\联通MobileNo"+ new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xlsx");
            wb.write(fout);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void createYidongMD5MobileExcel(Map<String, String>map){
        int rowNo = 0;
        XSSFCell cell;
        XSSFRow row;
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
                row = sheet.createRow(rowNo);//获得第i行
                cell = row.createCell(0);//第i行第一个单元格
                cell.setCellValue(stringStringEntry.getKey());

                cell = row.createCell(1);//第i行第二个单元格
                cell.setCellValue(stringStringEntry.getValue());
                rowNo ++;
            }
        }
        try {
            FileOutputStream fout = new FileOutputStream("C:\\Users\\dell\\Desktop\\移动MobileNo"+ new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xlsx");
            wb.write(fout);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void createDianxinMD5MobileExcel(Map<String, String>map){
        int rowNo = 0;
        XSSFCell cell;
        XSSFRow row;
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
                row = sheet.createRow(rowNo);//获得第i行
                cell = row.createCell(0);//第i行第一个单元格
                cell.setCellValue(stringStringEntry.getKey());

                cell = row.createCell(1);//第i行第二个单元格
                cell.setCellValue(stringStringEntry.getValue());
                rowNo ++;
            }
        }
        try {
            FileOutputStream fout = new FileOutputStream("C:\\Users\\dell\\Desktop\\电信MobileNo"+ new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xlsx");
            wb.write(fout);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

package com.chenxi.test.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PoiSingleReadUtils {

   /* public static void main(String[] args) throws Exception {
        List<User> list = readExcel("C:\\Users\\dell\\Desktop\\1111.xlsx");
        for (User user : list) {
            System.out.println(user.getRealName());
            System.out.println(user.getMobile());
            System.out.println(user.getIdNo());
            System.out.println("@@@@@@@@@@@@@@@@@@@");
        }
        System.out.println(list.size());
    }*/


    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    /**
     * 判断Excel的版本,获取Workbook
     * @param in
     * @param
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(InputStream in,File file) throws IOException{
        Workbook wb = null;
        if(file.getName().endsWith(EXCEL_XLS)){  //Excel 2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){  // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    /**
     * 判断文件是否是excel
     * @throws Exception
     */
    public static void checkExcelVaild(File file) throws Exception{
        if(!file.exists()){
            throw new Exception("文件不存在");
        }
        if(!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))){
            throw new Exception("文件不是Excel");
        }
    }

    /**
     * 读取Excel测试，兼容 Excel 2003/2007/2010
     * @throws Exception
     */
    public static List<String> readExcel(String fileName) throws Exception {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        List<String>list = new ArrayList<>();

        try {
            // 同时支持Excel 2003、2007
            File excelFile = new File(fileName); // 创建文件对象
            FileInputStream is = new FileInputStream(excelFile); // 文件流
            checkExcelVaild(excelFile);
            Workbook workbook = getWorkbok(is,excelFile);
            //Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel2003/2007/2010都是可以处理的
            int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
            /**
             * 设置当前excel中sheet的下标：0开始
             */
            Sheet sheet = workbook.getSheetAt(0);   // 遍历第一个Sheet
            // 为跳过第一行目录设置count
//            int count = 0;
//            Row row1 = sheet.getRow(1);
//            Cell cell1 = row1.getCell((short) 1);
            int rowNum=sheet.getLastRowNum();
            for (int i = 0; i <= rowNum; i++){
                Row row = sheet.getRow(i);
                Cell cell = row.getCell(0);
                //无数据跳出
                if(cell.toString().equals("")){
                    break;
                }

                cell.setCellType(Cell.CELL_TYPE_STRING);
                String stringCellValue = cell.getStringCellValue();
                list.add(stringCellValue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }



}

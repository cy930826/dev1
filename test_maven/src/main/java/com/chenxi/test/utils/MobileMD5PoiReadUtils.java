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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取手机号MD5
 */
public class MobileMD5PoiReadUtils {

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
    public static Map<String, String> readExcel(String fileName) throws Exception {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        List<Car>list = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
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
            for (Row row : sheet) {
                // 跳过第一行的目录
//                if(count == 0){
//                    count++;
//                    continue;
//                }
                // 如果当前行没有数据，跳出循环
                if(row.getCell(0).toString().equals("")){
                    break;
                }
                Car car = new Car();
                for (Cell cell : row) {
                    if(cell.toString() == null){
                        continue;
                    }
                    String cellValue = "";
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    int cellType = cell.getCellType();
                    switch (cellType) {
                        case Cell.CELL_TYPE_STRING:     // 文本
                            cellValue = cell.getRichStringCellValue().getString();
                            if (cellValue.length() >= 14){
                                car.setImei(cellValue);

                            }else if (cellValue.length() == 11){
                                car.setMobile(cellValue);
                            }
                            break;
                        case Cell.CELL_TYPE_NUMERIC:    // 数字、日期
                            if (DateUtil.isCellDateFormatted(cell)) {
                                cellValue = fmt.format(cell.getDateCellValue());
                            } else {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                cellValue = String.valueOf(cell.getRichStringCellValue().getString());
                            }
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:    // 布尔型
                            cellValue = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case Cell.CELL_TYPE_BLANK: // 空白
                            cellValue = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_ERROR: // 错误
                            cellValue = "错误";
                            break;
                        case Cell.CELL_TYPE_FORMULA:    // 公式
                            // 得到对应单元格的公式
                            //cellValue = cell.getCellFormula() + "#";
                            // 得到对应单元格的字符串
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            cellValue = String.valueOf(cell.getRichStringCellValue().getString()) ;
                            break;
                        default:
                            cellValue = "#";
                    }
//                   user.setImei(cellValue);
                }
                map.put(car.getImei(), car.getMobile());
//                list.add(car);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }



}

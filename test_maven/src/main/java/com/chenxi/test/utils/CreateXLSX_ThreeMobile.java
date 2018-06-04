package com.chenxi.test.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Map;

public class CreateXLSX_ThreeMobile {

    public static void createXLSX(Map<String,Object>map, File fi, File of) throws IOException {
        Workbook tempWorkBook = null;
        Sheet tempSheet = null;
        int rowIndex = 0;
        Row tempRow = null;
        Cell tempCell = null;
        InputStream in = null;
        OutputStream os = null;
        try {
            //excel模板路径
            os = new FileOutputStream(of);
            in = new FileInputStream(fi);
            //读取excel模板
            tempWorkBook = new XSSFWorkbook(in);
            //读取了模板内所有sheet内容
            tempSheet = tempWorkBook.getSheetAt(0);
            int count = 0;

            for (int i = rowIndex; i < map.size(); i++) {
                // 获取指定行
                tempRow = tempSheet.getRow(i);
                // 获取指定行的单元格
                //获得某行第一列的单元格
                Cell cell = tempRow.getCell(0);
                //货的单元格的内容
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String stringCellValue = cell.getStringCellValue();
                String s = (String) map.get(stringCellValue);
                if (s != null && s != "") {
                    int cellNo = 1;
                    tempCell = tempRow.createCell(cellNo);
                    tempCell.setCellValue(s);
                }else{
                    int cellNo = 1;
                    tempCell = tempRow.createCell(cellNo);
                    tempCell.setCellValue("");
                }
                count ++;
                System.out.println(count);
            }
            tempWorkBook.write(os);

        }catch(Exception e){
            e.printStackTrace();
        } finally {
            os.close();
        }
    }
}

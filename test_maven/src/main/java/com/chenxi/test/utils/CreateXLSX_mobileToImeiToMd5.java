package com.chenxi.test.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;
import java.util.Map;

public class CreateXLSX_mobileToImeiToMd5 {

    public static void createXLSX(Map<String,String> map, File fi, File of, int size, int loop) throws IOException {

        Workbook tempWorkBook = null;
        Sheet tempSheet = null;
        int rowIndex = 0;
        int cellNo = 0;
        Row tempRow = null;
        Cell tempCell = null;
        InputStream inputstream = null;
        OutputStream os = null;
        try {
            String mobile = null;
            String lm = null;
            //excel模板路径

            os = new FileOutputStream(of);
            InputStream in = new FileInputStream(fi);
            //读取excel模板
            tempWorkBook = new XSSFWorkbook(in);
            //读取了模板内所有sheet内容
            tempSheet = tempWorkBook.getSheetAt(0);
            for (int j = 0; j < size; j ++) {
                // 获取指定行
                tempRow = tempSheet.getRow(j);
                // 获取指定行的单元格
                //获得某行第一列的单元格
                if (tempRow != null) {
                    Cell cell = tempRow.getCell(0);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    //获取单元格的内容
                    String stringCellValue = cell.getStringCellValue();
                    if (stringCellValue != null) {
                        mobile = map.get(stringCellValue);
                        if (mobile != null && mobile != "") {
                            cellNo = 3;
                            tempCell = tempRow.createCell(cellNo);
                            tempCell.setCellValue(mobile);
                        }else {
                            cellNo = 3;
                            tempCell = tempRow.createCell(cellNo);
                            tempCell.setCellValue("");
                        }
                    }
                }
                System.out.println(j);
            }
            tempWorkBook.write(os);

        }catch(Exception e){
            e.printStackTrace();
        } finally {
            os.close();
        }
    }
}

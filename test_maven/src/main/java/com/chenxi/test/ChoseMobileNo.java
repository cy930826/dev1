package com.chenxi.test;

import com.chenxi.test.utils.ChoseMobileUtils;
import com.chenxi.test.utils.CreateXLSX_ThreeMobile;
import com.chenxi.test.utils.OperatorList;
import com.chenxi.test.utils.PoiReadUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChoseMobileNo {
    public static void choosedianxinMobile() throws Exception {
        List<String> list = PoiReadUtils.readExcel("C:\\Users\\dell\\Desktop\\1.xlsx");
        List<String> dxList = new ArrayList<>();
        if (list != null && list.size() > 0){
            for (String s : list) {
                if (s != null && s.length() > 0){
                    String s1 = s.replace("\t", "");
                    if(3 == OperatorList.isOperator(s1)){
                        dxList.add(s);
                    }
                }
            }
        }
        if (dxList != null && dxList.size() > 0) {
            ChoseMobileUtils.createDianxinExcel(dxList);
        }
    }

    public static void chooseLiantongMobile() throws Exception {
        List<String> list = PoiReadUtils.readExcel("C:\\Users\\dell\\Desktop\\1.xlsx");
        List<String> dxList = new ArrayList<>();
        if (list != null && list.size() > 0){
            for (String s : list) {
                if (s != null && s.length() > 0){
                    String s1 = s.replace("\t", "");
                    if(2 == OperatorList.isOperator(s1)){
                        dxList.add(s);
                    }
                }
            }
        }
        if (dxList != null && dxList.size() > 0) {
            ChoseMobileUtils.createLiantongExcel(dxList);
        }
    }

    public static void chooseYiDongMobile() throws Exception {
        List<String> list = PoiReadUtils.readExcel("C:\\Users\\dell\\Desktop\\1.xlsx");
        List<String> dxList = new ArrayList<>();
        if (list != null && list.size() > 0){
            for (String s : list) {
                if (s != null && s.length() > 0){
                    String s1 = s.replace("\t", "");
                    if(1 == OperatorList.isOperator(s1)){
                        dxList.add(s);
                    }
                }
            }
        }
        if (dxList != null && dxList.size() > 0) {
            ChoseMobileUtils.createYidongExcel(dxList);
        }
    }

    public static void chooseThrMobile() throws Exception {
        Map<String, Object>map = new HashMap<>();
        List<String> list = PoiReadUtils.readExcel("C:\\Users\\dell\\Desktop\\1.xlsx");
        File fi = new File("C:\\Users\\dell\\Desktop\\1.xlsx");
        File fo = new File("C:\\Users\\dell\\Desktop\\运营商打标.xlsx");
//        List<String> dxList = new ArrayList<>();
        if (list != null && list.size() > 0){
            for (String s : list) {
                if (s != null && s.length() > 0){
                    String s1 = s.replace("\t", "");
                    if(1 == OperatorList.isOperator(s1)){
                        map.put(s1, "移动");
                    }
                    if(2 == OperatorList.isOperator(s1)){
                        map.put(s1, "联通");
                    }
                    if(3 == OperatorList.isOperator(s1)){
                        map.put(s1, "电信");
                    }
                }
            }
        }
        if (map != null && map.size() > 0) {
            CreateXLSX_ThreeMobile.createXLSX(map, fi, fo);
        }
    }

    public static void main(String[] args) {
        try {
            choosedianxinMobile();
            chooseLiantongMobile();
            chooseYiDongMobile();
//            chooseThrMobile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

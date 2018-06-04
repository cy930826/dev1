package com.chenxi.test;

import com.chenxi.test.utils.ChoseMobileUtils;
import com.chenxi.test.utils.MobileMD5PoiReadUtils;
import com.chenxi.test.utils.OperatorList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChoseMD5MobileNo {

    public static void chooseYidongMD5Mobile() throws Exception {
        Map<String, String> map = MobileMD5PoiReadUtils.readExcel("C:\\Users\\chenxi\\Desktop\\01.xlsx");
        Map<String, String>mobileMap = new HashMap<>();
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            String mobile = stringStringEntry.getValue();
            if(mobile != null){
                if(1 == OperatorList.isOperator(mobile)){
                    mobileMap.put(stringStringEntry.getKey(), mobile);
                }}
        }

        if (mobileMap != null && mobileMap.size() > 0) {
            ChoseMobileUtils.createYidongMD5MobileExcel(mobileMap);
        }
    }

    public static void chooseLiantongMD5Mobile() throws Exception {
        Map<String, String> map = MobileMD5PoiReadUtils.readExcel("C:\\Users\\chenxi\\Desktop\\222.xlsx");
        Map<String, String>mobileMap = new HashMap<>();
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            String mobile = stringStringEntry.getValue();
            if(mobile != null){
            if(2 == OperatorList.isOperator(mobile)){
                mobileMap.put(stringStringEntry.getKey(), mobile);
            }}
        }

        if (mobileMap != null && mobileMap.size() > 0) {
            ChoseMobileUtils.createLiantongMD5MobileExcel(mobileMap);
        }
    }

    public static void chooseDianxinMD5Mobile() throws Exception {
        Map<String, String> map = MobileMD5PoiReadUtils.readExcel("C:\\Users\\chenxi\\Desktop\\2.xlsx");
        Map<String, String>mobileMap = new HashMap<>();
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            String mobile = stringStringEntry.getValue();
            if(mobile != null){
                if(3 == OperatorList.isOperator(mobile)){
                    mobileMap.put(stringStringEntry.getKey(), mobile);
                }}
        }

        if (mobileMap != null && mobileMap.size() > 0) {
            ChoseMobileUtils.createDianxinMD5MobileExcel(mobileMap);
        }
    }

    public static void main(String[] args) {
        try {
            //chooseDianxinMD5Mobile();
//            chooseLiantongMD5Mobile();
            chooseYidongMD5Mobile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

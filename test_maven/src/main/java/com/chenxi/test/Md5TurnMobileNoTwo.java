package com.chenxi.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chenxi.test.utils.*;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Md5TurnMobileNoTwo {

    public static void main(String[] args) throws Exception {
        List<String> list = PoiSingleReadUtils.readExcel("C:\\Users\\chenxi\\Desktop\\333.xlsx");
        Map<String,String> map = new HashMap<>();
        File fi = new File("C:\\Users\\chenxi\\Desktop\\333.xlsx");
        File of = new File("C:\\Users\\chenxi\\Desktop\\手机号md5解密333结果.xlsx");
        int count = 1;
        double size = list.size();
        double i1 = size / 100;
        int loop = (int)Math.ceil(i1);
        if (list != null || list.size() > 0) {
//            String[] array = list.toArray(new String[list.size()]);
//            int length = array.length;
//            double rool = Math.ceil(length/50);
//            double ceil = Math.ceil(0.02);
            List<String>list1 = new ArrayList<>();
            for (int i = 1; i <= loop; i++){
                if (i == loop){
                    list1.addAll(list.subList((loop - 1)*100, (int)size));
                }else {
                    list1.addAll(list.subList((i - 1) * 100, (i) * 100));
                }
                String[] array = list1.toArray(new String[list1.size()]);
                if (array != null && array.length > 0) {

                    Thread.currentThread().sleep(200);
                    long l = System.currentTimeMillis();
                    String s = Md5TurnMobileNo.identity(array);
                    long l1 = System.currentTimeMillis();
                    System.out.println(l1 - l);
                    System.out.println(s);
                    if (StringUtils.isNotBlank(s) && s.replace(" ", "").length() != 2 && !s.equalsIgnoreCase("null")) {
                        try {
                            //非json格式字符串会报错
                            JSONObject jsonObject = JSONObject.parseObject(s);
                            Result result = JSON.parseObject(s, Result.class);
                            String code = result.getCode();
                            String matches = matches(code);
                            if ("请求失败，请稍后重试".equals(matches)) {

                            }
                            String result1 = result.getResult();
                            Items items = JSON.parseObject(result1, Items.class);
                            System.out.println(items);

                            if (items != null || "0".equals(code)) {
                                if (items.getItems() != null && items.getItems().length > 0){
                                    String[] items1 = items.getItems();
                                    for (String s1 : items1) {
                                        Md5 md5 = JSON.parseObject(s1, Md5.class);
                                        String mobile = md5.getNo();
                                        String rmd5 = md5.getMd5();
                                        map.put(rmd5,mobile);

                                    }
                                }
                            }
                        } catch (Exception e) {
                            map.put("error", s);
                        }
                        System.out.println("处理" + count + "批");
                        count++;
                    }
                }
                list1.clear();
            }
        }
        //手机号转imeiMD5解密   两列
        CreateXLSX_mobileToImeiToMd5.createXLSX(map, fi, of, (int)size, loop);
        //imei转手机号MD5解密
//        CreateXLSX_iMEIToMobileToMd5.createXLSX(map, fi, of, (int)size, loop);
        //md5转手机号
        //CreateXLSX_1Md5.createXLSX(map, fi, of, (int)size);
    }


    public static String matches(String code) throws Exception {
        for (int i = 0; i < 3 ; i++) {
            if (!CodeMessageList.isTry(code)){
                return CodeMessageList.message(code);
            }
        }
        return "请求失败，请稍后重试";
    }
}

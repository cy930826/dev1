package com.chenxi.test.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MobileThreeElements {

    public static class Req {
        public String authId;
        public long ts;
        public String reqId;
        public String sign;
        public Integer l;

        public String mobile;
        public String realName;
        public String idNo;
        public String o ;

        public boolean omitLocal;
        public int aid;
        public boolean skipSaveLd;
    }

    private static String authId = "qydata03";
    private static String authPass = "a54cc70444ea4618ad8d586194ba1572";

    public static String identityThreeEle(String realName, String idNo, String mobile) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost request = new HttpPost("https://api.qydata.org:9000/mobile/verify/3f-cmcc-p2");//更详版
//        HttpPost request = new HttpPost("https://api.qydata.org:9000/mobile/verify/3f-cmcc-p");//更详版
        HttpPost request = new HttpPost("https://api.qydata.org:9000/mobile/verify/3f");//简版
//        HttpPost request = new HttpPost("https://api.qydata.org:9000/mobile/verify/3f/cmcc/111");//中版
//        HttpPost request = new HttpPost("http://api02.qydata.org:10000/mobile/verify/3f");//测试
//        HttpPost request = new HttpPost("http://api02.qydata.org:10000/mobile/verify/3f/cmcc/111");//测试
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
        Req req = new Req();
        req.authId = authId;
        req.reqId = Long.toString(System.currentTimeMillis()).substring(1);
        req.ts = System.currentTimeMillis();

//        req.mobile = DigestUtils.md5Hex(mobile);
//        req.idNo = DigestUtils.md5Hex(idNo);
//        req.realName = DigestUtils.md5Hex(realName);

        req.mobile = mobile;
        req.idNo = idNo;
        req.realName = realName;
        //0为低质量 默认高质量
        req.l = 1;
        //md5加密通道
//        req.o = "2";
        //供应商id
//        int i = 0;
//        if (mobile != null && mobile.length() == 11) {
//            i = OperatorList.isOperator(mobile);
//        }
//        if (i == 1) {
//            req.aid = 220;//仟穗
//            req.aid = 247;//仟穗
//            req.aid = 228;//小视
//            req.aid = 248;//小视
//            req.aid = 189;//本地
//            req.aid = 292;//本地
//            req.aid = 256;//天翼
//            int l = 0;
//            req.l = 1;
//        }
//        if (i ==2){
//            req.aid = 201;//联通详版
//            req.aid = 222;//联通详版
//            req.aid = 243;//联通更详版
//            req.aid = 131;//骏博简版
//        }
//        if (i == 3){
//            req.aid = 214;//大有详版
//            req.aid = 155;//骏博简版
//        }
        //true不调缓存
//        req.omitLocal = true;
//        true不存缓存
//        req.skipSaveLd = true;

        req.sign = DigestUtils.md5Hex(req.authId + authPass + req.reqId + Long.toString(req.ts)).toUpperCase();
        request.setEntity(new StringEntity(new Gson().toJson(req), Charsets.UTF_8));
        CloseableHttpResponse execute = httpClient.execute(request);
        int statusCode = execute.getStatusLine().getStatusCode();
        String s = EntityUtils.toString(execute.getEntity());

        if (StringUtils.isNotBlank(s) && s.replace(" ", "").length() != 2 && !s.equalsIgnoreCase("null")) {

            try {
                //非json格式字符串会报错
                JSONObject jsonObject = JSONObject.parseObject(s);
            } catch (Exception e) {
                return null;
            }
            return s;
        }else{
            return s;
        }
    }

    public static void main(String[] args) throws IOException {
        String realName = "马倩怡";
        String idNo = "69e93d89a53814b777daaea633a8a946";
        String mobile = "ca9c545f80aabc96b07362140c52d3c3";
        String s = identityThreeEle(realName, idNo, mobile);
        System.out.println("结果"+s);
    }
}
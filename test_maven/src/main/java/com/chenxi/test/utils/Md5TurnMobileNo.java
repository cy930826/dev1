package com.chenxi.test.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Md5TurnMobileNo {

    public static class Req {
        public String authId;
        public long ts;
        public String reqId;
        public String sign;

        public String[] q;
        public String sid;
//        public String realName;
//        public String idNo;
        //        客户传入数据为md5加密后制定运营商的字段 123 三大运营商
        //        public String o ;
        //是否掉缓存 true为不调
        public boolean omitLocal;
        //指定通道 例如手机三大运营商
        public int aid;
        //是否存缓存 true为不存
        public boolean skipSaveLd;
    }

    private static String authId = "qydata04";
    private static String authPass = "ff1625575cd54c3faad0763d98513765";
//    private static String authId = "qydata04_test";
//    private static String authPass = "3de2141cb8f0479b9708e92c025a6016";

    public static String identity(String[] imei) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("https://msisdn01.qydata.org:8000/mobile/query/md5-no");
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
        Req req = new Req();
        req.authId = authId;
        req.reqId = Long.toString(System.currentTimeMillis()).substring(1);
        req.ts = System.currentTimeMillis();
        req.q = imei;
        req.sid = System.currentTimeMillis() + "";

//        req.o = "2";
        req.aid = 97;
        req.omitLocal = true;
        req.skipSaveLd = true;
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
            return null;
        }

    }

    public static void main(String[] args) throws IOException {
        String[] imei = {
                "3a62c30a38e1d1002504ad6c6173c64d"
        };
        String s = identity(imei);
        System.out.println(s);
    }

}

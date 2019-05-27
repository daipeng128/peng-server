package com.peng.test.sms;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 *??API???????? ? ????
 *???????http://www.juhe.cn/docs/54
 **/

public class SmsUtil {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    //??????KEY
    public static final String APPKEY ="?????Appkey";


    //1.????
    public static JSONObject sendMsg(String mobile,Integer tplId,String tplValue){
        String result =null;
        //??????
        String url ="http://v.juhe.cn/sms/send";
        //????
        Map params = new HashMap();
        //?????????
        params.put("mobile",mobile);
        //????ID??????????????
        params.put("tpl_id",tplId);
        //????????????????????????#&=?????????????????urlencode???????<a href="http://www.juhe.cn/news/index/id/50" target="_blank">????></a>
        params.put("tpl_value",tplValue);
        //??APPKEY(???????)
        params.put("key",APPKEY);
        //???????,xml?json???json
        params.put("dtype","json");
        JSONObject object = null;
        try {
            result =net(url, params, "GET");
            object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    //2.??????
    public static int checkBlack(String msg){

        if(msg == null || "".equals(msg)){
            return 0;
        }
        String result =null;
        //??????
        String url ="http://v.juhe.cn/sms/black";
        //????
        Map params = new HashMap();
        //????????????UTF8 URLENCODE
        params.put("word",msg);
        //??APPKEY(???????)
        params.put("key",APPKEY);

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
                return 1;

            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     *
     * @param strUrl ????
     * @param params ????
     * @param method ????
     * @return  ???????
     * @throws Exception
     */
    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    //?map????????
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
package com.example.datebasebigwork.Mapper;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BaiDuOCR {

    public static String getAuth() {
        // 官网获取的 API Key
        String clientId = "qfy7KmETi54l2Xsx1ZDUao04";
        // 官网获取的 Secret Key
        String clientSecret = "DGHM2ip2zXLEySuT0AIyA8mdqClL0vMa";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取token
     * @param ak
     * @param sk
     * @return
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            //百度推荐使用POST请求
            connection.setRequestMethod("POST");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            //System.out.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }


    /**
     * 调用OCR
     * @param httpUrl
     * @param httpArg
     * @return
     */
    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        try {
            //用java JDK自带的URL去请求
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            //设置该请求的消息头
            //设置HTTP方法：POST
            connection.setRequestMethod("POST");
            //设置其Header的Content-Type参数为application/x-www-form-urlencoded
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey", "uml8HFzu2hFd8iEG2LkQGMxm");
            //将第二步获取到的token填入到HTTP header
            connection.setRequestProperty("access_token", BaiDuOCR.getAuth());
            connection.setDoOutput(true);
            connection.getOutputStream().write(httpArg.getBytes("UTF-8"));
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *身份证参数转换
     * @param jsonResult
     * @return
     */
    public static HashMap<String, String> getHashMapByJson(String jsonResult) {
        HashMap map = new HashMap<String, String>();
        try {
            JSONObject jsonObject = new JSONObject(jsonResult);
            JSONObject words_result = jsonObject.getJSONObject("words_result");
            Iterator<String> it = words_result.keys();
            while (it.hasNext()) {
                String key = it.next();
                JSONObject result = words_result.getJSONObject(key);
                String value = result.getString("words");
                switch (key) {
                    case "姓名":
                        map.put("姓名", value);
                        break;
                    case "民族":
                        map.put("民族", value);
                        break;
                    case "住址":
                        map.put("住址", value);
                        break;
                    case "公民身份号码":
                        map.put("公民身份号码", value);
                        break;
                    case "出生":
                        map.put("出生日期", value);
                        break;
                    case "性别":
                        map.put("性别", value);
                        break;
                    case "失效日期":
                        map.put("失效日期", value);
                        break;
                    case "签发日期":
                        map.put("签发日期", value);
                        break;
                    case "签发机关":
                        map.put("签发机关", value);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}

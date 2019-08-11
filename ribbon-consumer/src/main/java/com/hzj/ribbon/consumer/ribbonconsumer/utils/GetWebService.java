package com.hzj.ribbon.consumer.ribbonconsumer.utils;/*
 * @author <a href="mailto:huangzijian@gtmap.cn">huangzijian</a>
 * @version 1.0, 2018/3/8
 * @description  webservice地址以及传入参数 返回值
 */

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;


public class GetWebService {
    static int socketTimeout = 30000;// 请求超时时间
    static int connectTimeout = 30000;// 传输超时时间
    private static Logger logger = Logger.getLogger(GetWebService.class);
    public static String getWebServiceResult(String url, String functionName, String param) throws Exception {
        //hzj  cxf调用框架 需要1.7及以上的jdk版本
//        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();    // 策略
//        httpClientPolicy.setConnectionTimeout(10000);    //连接超时
//        httpClientPolicy.setAllowChunking( false );
//        httpClientPolicy.setReceiveTimeout(100000);       //接收超时
//        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//        Client client = dcf.createClient(url);
//        HTTPConduit http = (HTTPConduit) client.getConduit();
//        http.setClient(httpClientPolicy);
//        Object[] obj = client.invoke(functionName,param);
//        return JsonUtil.toJson(obj);

        //hzj普通http调用 为了适应1.6的jdk版本
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(url);
        //  设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
//        httpPost.setHeader("SOAPAction", "http://www.nankaistar.com");
        httpPost.setHeader("operationName", functionName);
        StringEntity data = new StringEntity(param, Charset.forName("UTF-8"));
        httpPost.setEntity(data);
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        HttpEntity httpEntity = response.getEntity();
        if (httpEntity != null) {
            // 打印响应内容
            retStr = EntityUtils.toString(httpEntity, "UTF-8");
        }
        // 释放资源
        closeableHttpClient.close();
        return retStr;
    }

    public static String getHfWebService(String url, String functionName) throws Exception {
        //hzj普通http调用 为了适应1.6的jdk版本
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpGet httpGet = new HttpGet(url);
        //  设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("Content-Type","application/json; charset=UTF-8");
        httpGet.setHeader("timestamp", String.valueOf(System.currentTimeMillis()/ 1000));
        httpGet.setHeader("unitCode", "LandResourcesBureau");
        httpGet.setHeader("appKey", functionName);
//        StringEntity data = new StringEntity(param, Charset.forName("UTF-8"));
//        httpGet.setEntity(data);
        CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
        HttpEntity httpEntity = response.getEntity();
        if (httpEntity != null) {
            // 打印响应内容
            retStr = EntityUtils.toString(httpEntity, "UTF-8");
        }
        // 释放资源
        closeableHttpClient.close();
        return retStr;
    }
// 南开调用
    public static String getSoapInputStream(String urlStr, String fcname, String xml) throws Exception {
        try {
            URL u = new URL(urlStr);
            URLConnection uc = u.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
//            uc.setRequestProperty("SOAPAction","http://www.nankaistar.com");
            uc.setRequestProperty("operationName",fcname);
            uc.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(uc.getOutputStream(), "UTF-8"));
            pw.println(xml);
            pw.flush();
            pw.close();
            InputStream inputStream = uc.getInputStream();
            String resXml = readStrByCode(inputStream, "UTF-8");
            logger.info(resXml);
            return inputStream.toString();
        } catch (Exception e) {
            logger.error("Unexpected error in getSoapInputStream function", e);
            return null;
        }
    }

    public static String readStrByCode(InputStream is, String code){
        StringBuilder builder = new StringBuilder();
        BufferedReader reader  = null;
        String line = "";
        try{
            reader = new BufferedReader(new InputStreamReader(is,code));
            while ((line = reader.readLine()) != null){
                builder.append(line);
            }
        }catch (Exception e){
            logger.error("Unexpected error in readStrByCode function", e);
            try{
                if(reader != null){
                    reader.close();
                }
            }catch (IOException ex){
                logger.error("Unexpected error in readStrByCode function", ex);
            }
        }finally {
            try{
                if(reader != null){
                    reader.close();
                }
            }catch (IOException e){
                logger.error("Unexpected error in readStrByCode function", e);
            }
        }
        return builder.toString();
    }
}

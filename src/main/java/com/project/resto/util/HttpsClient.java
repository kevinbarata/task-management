package com.project.resto.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @Class Name: HttpsClient
 * @Description: http/https请求工具类
 * @Sample（可选）: 
 * @Author: likai
 * @date 2017-08-24 下午17:04:33
 */
@Service
public class HttpsClient {
	
    private static final Logger logger = LoggerFactory.getLogger(HttpsClient.class);

    private static ConnectionSocketFactory plainsf = null;
    private static LayeredConnectionSocketFactory sslsf = null;
    private static Registry<ConnectionSocketFactory> registry = null;
    private static PoolingHttpClientConnectionManager cm = null;
    private static HttpRequestRetryHandler httpRequestRetryHandler = null;
    private static RequestConfig requestConfig = null;
	public static CloseableHttpClient httpClient = null;

    static {
        plainsf = PlainConnectionSocketFactory.getSocketFactory();
        sslsf = SSLConnectionSocketFactory.getSocketFactory();
        registry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", plainsf).register("https", sslsf).build();

        cm = new PoolingHttpClientConnectionManager(registry);
        // Maximum number of connections
        cm.setMaxTotal(30);
        // Connections per route basis
        cm.setDefaultMaxPerRoute(10);
        // Request retry processing
        httpRequestRetryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= 5) {// 如果已经重试了5次，就放弃
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                    return false;
                }
                if (exception instanceof InterruptedIOException) {// 超时
                    return false;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                    return false;
                }
                if (exception instanceof SSLException) {// ssl握手异常
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };

        requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000).setSocketTimeout(25000).build();

        httpClient = HttpClients.custom().setConnectionManager(cm).setRetryHandler(httpRequestRetryHandler).build();
    }

	   /**
		 * HTTP Get 请求
		 * @author likai
		 * @date  2017-08-24 下午17:04:33
	     * @param url  请求的url地址
		 * @return 返回结果
		 */
	    public static String doGet(String url ,String authorization) throws IOException {
	    	logger.info("enter HttpClient doGet, url:{}", url);

	        String result = null;
	        
	        if(StringUtils.isBlank(url)){
	            return null;
	        }
	        CloseableHttpResponse response = null ;
	        HttpGet httpGet = null;
	        try {
	        	//Content-Length必须设置成消息体的长度

	        	httpGet = new HttpGet(url);
	        	httpGet.addHeader("Authorization",authorization);
	            response = httpClient.execute(httpGet);
	        	logger.info("response.getStatusLine():{}", response.getStatusLine());
	            HttpEntity entity = response.getEntity();
	            if (entity != null){
	                result = EntityUtils.toString(entity, "utf-8");
	            }
	            EntityUtils.consume(entity);//释放资源

	            logger.info("exit HttpClient doGet");
	            return result;
	        } catch (Exception e) {
	            logger.info("HttpClient doPostXML error, e.getMessage()", e.getMessage());
	            throw e;
	        }finally{
	            if(response!=null){
	                response.close();
	            }
	            if(httpGet!=null){
	            	httpGet.releaseConnection();
	            }
	        }
	    }

    /**
	 * HTTP Post 请求
	 * @author likai
	 * @date  2017-08-24 下午17:04:33
     * @param url  请求的url地址
     * @return 返回结果
	 */
    public static String doPost(String url, String paramsJson, String authorization) throws IOException {
        logger.info("--->HttpClient#doPost: url={}, param={}, auth={}", url, paramsJson, authorization);
        String result = null;
        CloseableHttpResponse response = null;

        if(StringUtils.isBlank(url)){
            return null;
        }
        HttpPost httpPost = new HttpPost(url);
        try {
            StringEntity entity = new StringEntity(paramsJson, "UTF-8");
            httpPost.setEntity(entity);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", authorization);

            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
            logger.info("doPostJson result = {}", result);
        } catch (Exception e) {
            logger.error("HttpClient doPostJson error", e);
        } finally {
            if(response!=null){
                response.close();
            }
            if(httpPost!=null){
                httpPost.releaseConnection();
            }
        }
        return result;
    }
    
    /**
	 * HTTP Post 请求
	 * @author likai
	 * @date  2017-08-24 下午17:04:33
     * @param url  请求的url地址
     * @return 返回结果
	 */
    public static String doVerifyIdInfoPost(String url,Map<String,String> map) throws Exception {
    	logger.info("enter HttpClient doPost, url:{}", url);

        String result = null; 
        
        if(StringUtils.isBlank(url)){
            return null;
        }
        CloseableHttpResponse response = null ;
        HttpPost httpPost = null;
        try {
        	//Content-Length必须设置成消息体的长度
        	//Content-Type必须设置下面的值：text/plain; charset=utf-8
            httpPost = new HttpPost(url);
            
            httpPost.addHeader("accept", "*/*");  
            httpPost.addHeader("connection", "Keep-Alive");  
            httpPost.addHeader("cache-control","no-cache");
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");  
            httpPost.addHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)"); 
          
            //装填参数  
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            if(map!=null){  
                for (Entry<String, String> entry : map.entrySet()) {  
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }  
            }  
            //设置参数到请求对象中  
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            
            
            response = httpClient.execute(httpPost);
        	logger.info("response.getStatusLine():{}", response.getStatusLine());
            HttpEntity entity = response.getEntity();
            if (entity != null){
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);//释放资源

            logger.info("exit HttpClient doVerifyIdInfoPost,result="+result);
            return result;
        } catch (Exception e) {
            logger.info("HttpClient doVerifyIdInfoPost error, e.getMessage()", e.getMessage());
            throw e;
        }finally{
            if(response!=null){
                response.close();
            }
            if(httpPost!=null){
                httpPost.releaseConnection();
            }
        }
    }

}

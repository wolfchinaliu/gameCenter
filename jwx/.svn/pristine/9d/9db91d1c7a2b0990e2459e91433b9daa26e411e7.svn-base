package weixin.shop.util;

import java.net.URL;
import java.util.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;



import weixin.guanjia.core.util.MyX509TrustManager;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.List;

import net.sf.json.JSONObject;

import org.jeecgframework.web.system.service.SystemService;

import weixin.guanjia.core.entity.common.AccessToken;
import weixin.guanjia.core.entity.common.WeixinMedia;
import weixin.guanjia.core.entity.model.AccessTokenYw;


public class PayCommonUtil {

	 public static String createSign(String characterEncoding,SortedMap<Object,Object> parameters, String key){
	        StringBuffer sb = new StringBuffer();
	        Set es = parameters.entrySet();
	        Iterator it = es.iterator();
	        while(it.hasNext()) {
	            Map.Entry entry = (Map.Entry)it.next();
	            String k = (String)entry.getKey();
	            Object v = entry.getValue();
	            if(null != v && !"".equals(v) 
	                    && !"sign".equals(k) && !"key".equals(k)) {
	                sb.append(k + "=" + v + "&");
	            }
	        }
	        sb.append("key=" + key);
	        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
	        return sign;
	    }

	 public static String getRequestXml(SortedMap<Object,Object> parameters){
	        StringBuffer sb = new StringBuffer();
	        sb.append("<xml>");
	        Set es = parameters.entrySet();
	        Iterator it = es.iterator();
	        while(it.hasNext()) {
	            Map.Entry entry = (Map.Entry)it.next();
	            String k = (String)entry.getKey();
	            String v = (String)entry.getValue();
	            if ("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)||"sign".equalsIgnoreCase(k)) {
	                sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
	            }else {
	                sb.append("<"+k+">"+v+"</"+k+">");
	            }
	        }
	        sb.append("</xml>");
	        return sb.toString();
	    }
	 
	 public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
	        try {
	            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
	            TrustManager[] tm = { new MyX509TrustManager() };
	            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	            sslContext.init(null, tm, new java.security.SecureRandom());
	            // 从上述SSLContext对象中得到SSLSocketFactory对象
	            SSLSocketFactory ssf = sslContext.getSocketFactory();
	            URL url = new URL(requestUrl);
	            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	            conn.setSSLSocketFactory(ssf);
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setUseCaches(false);
	            // 设置请求方式（GET/POST）
	            conn.setRequestMethod(requestMethod);
	            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
	            // 当outputStr不为null时向输出流写数据
	            if (null != outputStr) {
	                OutputStream outputStream = conn.getOutputStream();
	                // 注意编码格式
	                outputStream.write(outputStr.getBytes("UTF-8"));
	                outputStream.close();
	            }
	            // 从输入流读取返回内容
	            InputStream inputStream = conn.getInputStream();
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            String str = null;
	            StringBuffer buffer = new StringBuffer();
	            while ((str = bufferedReader.readLine()) != null) {
	                buffer.append(str);
	            }
	            // 释放资源
	            bufferedReader.close();
	            inputStreamReader.close();
	            inputStream.close();
	            inputStream = null;
	            conn.disconnect();
	            return buffer.toString();
	        } catch (ConnectException ce) {
	           // log.error("连接超时：{}", ce);
	        } catch (Exception e) {
	          //  log.error("https请求异常：{}", e);
	        }
	        return null;
	    }

}

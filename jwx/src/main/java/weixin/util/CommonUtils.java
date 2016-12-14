package weixin.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by GuoLiang on 2016/6/30 10:27 11:07.
 */
public class CommonUtils {
    public static final transient Logger SERVICE_LOGGER = Logger.getLogger("com.shiliu.response.ServiceLogger");
    public static final transient Logger SQL_LOGGER = Logger.getLogger("com.shiliu.response.SqlLogger");
    private static final transient Logger LOGGER = Logger.getLogger(CommonUtils.class);

    public static String containsAny(Set<String> one, Set<String> another) {
        if (CollectionUtils.isEmpty(one)) return null;
        if (CollectionUtils.isEmpty(another)) return null;
        for (String str1 : one) {
            for (String str2 : another) {
                if (StringUtils.contains(str1, str2)) {
                    return str1;
                }
            }
        }
        return null;
    }

    public static String encodeURL(String url) {
        try {
            return URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void printRequestParam(String tag, HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            builder.append(entry.getKey()).append(":").append(Arrays.asList(entry.getValue())).append(",");
        }
        LOGGER.info(tag + builder);
        Enumeration<String> headerNames = request.getHeaderNames();
        builder.setLength(0);
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            Enumeration<String> values = request.getHeaders(key);
            String valueResult = "";
            while (values.hasMoreElements()) {
                valueResult += values.nextElement() + ", ";
            }
            builder.append(key).append(":").append(valueResult).append(",");
        }
        LOGGER.info(tag + " - headers: " + builder);
    }

    public static String encode(String something, int times) {
        if (times < 0) times = 1;
        String result = something;
        for (int i = 0; i < times; i++) {
            try {
                result = Base64.encodeBase64URLSafeString(something.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String decode(String something, int times) {
        if (times < 0) times = 1;
        String result = something;
        for (int i = 0; i < times; i++) {
            try {
                result = new String(Base64.decodeBase64(something), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static boolean isAnyBlank(String... params) {
        if (null == params) return true;
        for (String param : params) {
            if (StringUtils.isBlank(param)) {
                return true;
            }
        }
        return false;
    }
    
    /***
	 * 随机获取des密钥
	 * @return
	 */
	public static String randomDesKey(){
		String keySource = "0123456789!@#$%^&*QWERTYUIOP|ASDFGHJKL:ZXCVBNM?qwertyuiopasdfghjklzxcvbnm,.;";
		char[] keyArray = new char[8];
		for(int i = 0;i < 8;i++){
			int a = (int)((Math.random()) * keySource.length());
			keyArray[i] = keySource.charAt(a);
		}
		return new String(keyArray);
	}

	public static String listToSqlString(List<String> list) {
		if (CollectionUtils.isEmpty(list)) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		for (String elem : list) {
			buffer.append("'").append(elem).append("'").append(",");
		}
		buffer.deleteCharAt(buffer.length() - 1);
		return buffer.toString();
	}
	public static String getStringUnicode(String str){
	 String result="";  
    for (int i = 0; i < str.length(); i++){  
        int chr1 = (char) str.charAt(i);  
        if(chr1>=19968&&chr1<=171941){//汉字范围 \u4e00-\u9fa5 (中文)  
            result+="\\u" + Integer.toHexString(chr1);  
        }else{  
            result+=str.charAt(i);  
        }  
    }  
    return result;
	}
}

package sdk.weishenghuo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jeecgframework.core.util.JsonUtil;

import com.alibaba.fastjson.JSONObject;

import weixin.shop.util.MD5Util;

/**
 * @Auth popl
 * @Date 2016年10月18日 下午2:04:19
 * @authEmail popl_lu@sian.cn
 * @CalssName sdk.weishenghuo.RequestEncapsulation
 * @dec 请求的封装
 */
public class RequestEncapsulation {

	public static String MAIN_URL = "https://api.acewill.net";
	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	/**
	 * 
	 * @param paraMap
	 *            入参
	 * @return 参数排序拼接串
	 */
	public static String getParamsString(Map<String, String> paraMap) {
		List<String> keyList = new ArrayList<String>();
		StringBuffer queryString = new StringBuffer();
		Set<String> sets = paraMap.keySet();
		for (String key : sets) {
			keyList.add(key);
		}
		Collections.sort(keyList, new Comparator<String>() {
			@Override
			public int compare(String key, String key2) {
				return key.compareToIgnoreCase(key2);// 比较，忽略大小写
			}
		});
		if (keyList.size() > 0) {
			for (String key : keyList) {
				queryString
						.append(String.format("%s=%s&", URLEncoder.encode(key), URLEncoder.encode(paraMap.get(key))));
			}
			return queryString.toString();
		}
		return "";
	}

	/**
	 * https请求
	 * 
	 * @param url
	 * @param paramString
	 * @return
	 */
	public static JSONObject httpsDoPost(String url, String paramString) {
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
			URL aURL = new URL(url);
			HttpsURLConnection connection = (HttpsURLConnection) aURL.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
			connection.setSSLSocketFactory(sc.getSocketFactory());
			connection.setHostnameVerifier(new TrustAnyHostnameVerifier());
			connection.setDoOutput(true);
			connection.setDoInput(true);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
			writer.write(paramString);
			writer.flush();
			writer.close();
			connection.connect();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			StringBuffer resString = new StringBuffer("");
			String temp = null;
			while ((temp = buffer.readLine()) != null) {
				resString.append(temp);
			}
			return JSONObject.parseObject(resString.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return JSONObject.parseObject("{'errorcode':500,'errormsg':'请求失败'}");
		}
	}

	public static JSONObject weishenhuoRequest(String url, String appId, String appKey, Map<String, String> paMap) {
		String paOrderString = getParamsString(paMap); // 接口参数
		String ts = System.currentTimeMillis() + ""; // 时间戳
		String sig = MD5Util.MD5Encode(paOrderString + "appid=" + appId + "&appkey=" + appKey + "&v=2.0&ts=" + ts, "UTF-8");
		String paJsonString = JsonUtil.toJSONString(paMap);
		return httpsDoPost(url, "req="+ paJsonString+ "&appid=" + appId + "&v=2.0&ts=" + ts + "&sig=" + sig + "&fmt=JSON");
	}

	public static String geOPenid(String cno, String accontId,String appId, String appKey) {
		String url = MAIN_URL+"/user/account";
//		String appId = "dp0mrazel7Gfii1XHYbE7";
//		String appKey = "34211233c019108e75fba9575b69bf27";
		Map<String, String> map = new HashMap<String, String>();
		map.put("cno", cno);
		System.out.println(weishenhuoRequest(url, appId, appKey, map));
		return "";

	}
	public static JSONObject getUserFromOPenid(String openid,String appId,String appKey){
		String url = MAIN_URL + "/user/getinfo";
		Map<String, String> map = new HashMap<String, String>();
		openid = "ohph4t5VCi7BttFliU7E_Ov-MKBE";//
		map.put("openid", openid);
		return weishenhuoRequest(url, appId, appKey, map);
	}
	public static JSONObject sendTicket(String cno,String templateId,int amount,String appId,String appKey){
		String url = MAIN_URL + "/coupon/send";
		Map<String, String> map = new HashMap<String, String>();
		map.put("cno", cno);
		map.put("template_id", templateId);
		map.put("amount", amount+"");
		map.put("biz_id", LongIdWorker.getWeishenhuoId()+"");
		 appId = "dp0mrazel7Gfii1XHYbE7";
		 appKey = "34211233c019108e75fba9575b69bf27";
		return weishenhuoRequest(url, appId, appKey, map);
	}
	public static JSONObject getAllCoupan(String appId,String appKey){
		String url = MAIN_URL + "/coupon/list";
		//String appId = "dp0mrazel7Gfii1XHYbE7";
		//String appKey = "34211233c019108e75fba9575b69bf27";
		Map<String, String> map = new HashMap<String, String>();
		map.put("page", "1");
		//JSONObject res = weishenhuoRequest(url, appId, appKey, map);
		return weishenhuoRequest(url, appId, appKey, map);
	}
	public static void main(String[] args) {
		
		//geOPenid("1117092", "123123");
		//sendTicket(1117092,2003702,2);
		String openid = "ohph4t5VCi7BttFliU7E_Ov-MKBE";
		//getUserFromOPenid(openid, "123123");
		//System.out.println(Md5.Md5("coupons%5B0%5D=12345&coupons%5B1%5D=67890&grade=1&limit=20&offset=0&orderby%5Bbalance%5D=desc&appid=ct_ZjQ4NmU2OTE0OGY1MTJ&appkey=202cb962ac59075b964b07152d234b70&v=2.0&ts=1426773603"));
		System.out.println(getAllCoupan("dp0mrazel7Gfii1XHYbE7","34211233c019108e75fba9575b69bf27"));
	}
}

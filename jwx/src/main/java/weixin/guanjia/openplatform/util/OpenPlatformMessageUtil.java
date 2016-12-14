package weixin.guanjia.openplatform.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;

import net.sf.json.JSONObject;

public class OpenPlatformMessageUtil {
	
	public static Map<String, String> parseXml(HttpServletRequest request, String appId, String token, String encodingAesKey) throws Exception {
		String msgSignature = request.getParameter("msg_signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");

		InputStream is = request.getInputStream();
		String postData = IOUtils.toString(is);
		WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
		String decrpyMsg = pc.decryptMsg(msgSignature, timestamp, nonce, postData);
		
		Map<String, String> ret = new HashMap<String, String>();
		Document document = DocumentHelper.parseText(decrpyMsg);
		Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for (Element e : elementList) {
        	ret.put(e.getName(), e.getText());
        }
                
		return ret;
	}
	
	public static String encryptMsg(String srcMsg, String timestamp, String nonce, String token, String encodingAesKey, String componentAppId) throws Exception {
		WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, componentAppId);
		return pc.encryptMsg(srcMsg, timestamp, nonce);
	}
	
	public static String map2String(Map<String, String> map) {
		JSONObject json = new JSONObject();
		for (String key: map.keySet()) {
			json.put(key, map.get(key));
		}
		
		return json.toString();
	}
}

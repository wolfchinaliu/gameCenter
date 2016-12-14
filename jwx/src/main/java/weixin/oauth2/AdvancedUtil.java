package weixin.oauth2;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.lottery.controller.LotteryController;

public class AdvancedUtil {         
	private static final Logger logger = Logger
			.getLogger(AdvancedUtil.class);
	public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
		WeixinOauth2Token wat = null;
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
		logger.info(requestUrl);
		logger.info(jsonObject);
		if (null != jsonObject && jsonObject.containsKey("access_token")) {
            wat = new WeixinOauth2Token();
            wat.setAccessToken(jsonObject.getString("access_token"));
            wat.setExpiresIn(jsonObject.getInt("expires_in"));
            wat.setRefreshToken(jsonObject.getString("refresh_token"));
            wat.setOpenId(jsonObject.getString("openid"));
            wat.setScope(jsonObject.getString("scope"));
		}
		return wat;
	}
	
	public static WeixinOauth2Token getOauth2AccessToken(String appId, String code, String componentAppId, String componentAccessToken) {
		WeixinOauth2Token wat = null;
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/component/access_token?appid=%s&code=%s&grant_type=authorization_code&component_appid=%s&component_access_token=%s";
		requestUrl = String.format(requestUrl, appId, code, componentAppId, componentAccessToken);
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
		logger.info(requestUrl);
		logger.info(jsonObject);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				logger.error(jsonObject.toString());
			}
		}
		return wat;
	}
	
	

	public static WeixinOauth2Token refreshOauth2AccessToken(String appId,
			String refreshToken) {
		WeixinOauth2Token wat = null;
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scpde"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
			}
		}
		return wat;
	}

	public static SNSUserInfo getSnsUserInfo(String accressToken, String openId) {
		SNSUserInfo snsUserInfo = null;
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accressToken);
		requestUrl = requestUrl.replace("OPENID", openId);
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				snsUserInfo = new SNSUserInfo();
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				snsUserInfo.setSex(jsonObject.getString("sex"));
				snsUserInfo.setNickName(jsonObject.getString("nickname"));
			} catch (Exception e) {
				snsUserInfo = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
			}
		}
		return snsUserInfo;
	}
}

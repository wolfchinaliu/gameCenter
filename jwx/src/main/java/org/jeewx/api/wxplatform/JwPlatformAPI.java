package org.jeewx.api.wxplatform;

import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.core.req.WeiXinReqService;
import org.jeewx.api.core.req.model.platform.GetAuthorizerInfo;
import org.jeewx.api.wxplatform.model.WxAccuont;
import com.google.gson.Gson;
import net.sf.json.JSONObject;
/**
 * 获取公众号信息
 * @author PC
 *
 */
public class JwPlatformAPI {
	/**
     * 获取公众号信息
     *
     * @return
     * @throws WexinReqException
     */
    public static WxAccuont getWeixinAccount(String authorization_code, String component_access_token,String component_appid) throws WexinReqException {
        if (component_access_token != null) {
        	GetAuthorizerInfo getAuthorizerInfo = new GetAuthorizerInfo();
		     getAuthorizerInfo.setAuthorization_code(authorization_code);
		     getAuthorizerInfo.setComponent_access_token(component_access_token);
		     getAuthorizerInfo.setComponent_appid(component_appid);
            JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(getAuthorizerInfo);
            WxAccuont wxAccuont = null;
            Object error = result.get("errcode");
            if (error == null) {
            	wxAccuont = new Gson().fromJson(result.toString(), WxAccuont.class);
            }
            System.out.println(wxAccuont.getAuthorization_info());
            return wxAccuont;
        }
        return null;
    }

}

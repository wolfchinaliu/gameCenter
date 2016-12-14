package weixin.guanjia.core.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.cgform.engine.FreemarkerHelper;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weixin.business.entity.WeixinCardEntity;
import weixin.business.entity.WeixinLocationEntity;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.entity.WeixinOpenPlatformEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.account.service.WeixinOpenPlatformServiceI;
import weixin.guanjia.base.entity.Subscribe;
import weixin.guanjia.base.entity.WeixinExpandconfigEntity;
import weixin.guanjia.base.service.SubscribeServiceI;
import weixin.guanjia.base.service.WeixinExpandconfigServiceI;
import weixin.guanjia.core.entity.message.resp.*;
import weixin.guanjia.core.util.MessageUtil;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.menu.entity.MenuEntity;
import weixin.guanjia.menu.service.WeixinMenuServiceI;
import weixin.guanjia.message.dao.TextTemplateDao;
import weixin.guanjia.message.entity.AutoResponse;
import weixin.guanjia.message.entity.AutoResponseDefault;
import weixin.guanjia.message.entity.NewsItem;
import weixin.guanjia.message.entity.NewsTemplate;
import weixin.guanjia.message.entity.ReceiveText;
import weixin.guanjia.message.entity.TextTemplate;
import weixin.guanjia.message.service.AutoResponseDefaultServiceI;
import weixin.guanjia.message.service.AutoResponseServiceI;
import weixin.guanjia.message.service.NewsItemServiceI;
import weixin.guanjia.message.service.NewsTemplateServiceI;
import weixin.guanjia.message.service.ReceiveTextServiceI;
import weixin.guanjia.message.service.TextTemplateServiceI;
import weixin.guanjia.openplatform.util.OpenPlatformMessageUtil;
import weixin.idea.extend.function.KeyServiceI;
import weixin.liuliangbao.util.HttpUtil;
import weixin.liuliangbao.util.RedisConnectionPoolFactory;
import weixin.member.entity.WeixinGroupEntity;
import weixin.member.entity.WeixinMemberCardEntity;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinGroupServiceI;
import weixin.member.service.WeixinMemberServiceI;
import weixin.member.util.MemberUtil;
import weixin.payment.entity.WeixinUsergetcardEntity;
import weixin.source.entity.WeixinSourceEntity;
import weixin.source.service.WeixinSourceServiceI;
import weixin.util.DateUtils;

@Service("openPlatformPushMsgService")
public class OpenPlatformPushMsgService {
    private static final Logger LOGGER = Logger.getLogger(OpenPlatformPushMsgService.class);

    @Autowired
    private WeixinAccountServiceI weixinAccountService;
    @Autowired
    WeixinGroupServiceI weixinGroupService;
	@Autowired
	WeixinSourceServiceI weixinSourceService;
    @Autowired
    private WeixinOpenPlatformServiceI weixinOpenPlatformService;
    @Autowired
    private WechatService wechatService;

    /**
     * 事件处理核心控制方法
     *
     * @param request
     * @return
     */
    public String coreService(HttpServletRequest request,final String componentAppId, String token, String encodingAesKey) {
    	Map<String, String> requestMap = null;
    	try {
    		// xml请求解析
    		requestMap = OpenPlatformMessageUtil.parseXml(request, componentAppId, token, encodingAesKey);
    	} catch (Exception ex) {
    		LogUtil.error("读取request失败" + ex.getMessage(), ex);
    		return "";
    	}
        // 发送方帐号（open_id）
        final String fromUserName = requestMap.get("FromUserName");
        // 公众帐号
        String toUserName = requestMap.get("ToUserName");
        // 消息类型
        String msgType = requestMap.get("MsgType");
        
        // 全网发布测试公众号  appid: wx570bc396a51b8ff8  username:gh_3c884a361561
        if (toUserName.equalsIgnoreCase("gh_3c884a361561")) {
        	TextMessageResp testTextMessage = new TextMessageResp();
        	testTextMessage.setToUserName(fromUserName);
        	testTextMessage.setFromUserName(toUserName);
        	testTextMessage.setCreateTime(new Date().getTime());
        	testTextMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        	String requestContent = requestMap.get("Content");
        	if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
        		LogUtil.info("收到事件消息到测试公众号");
        		String eventType = requestMap.get("Event");
        		testTextMessage.setContent(eventType + "from_callback"); 
        	} else if (msgType.equalsIgnoreCase(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
        		LogUtil.info("收到文本消息:" + requestContent + "到测试公众号");
        		if (requestContent.equalsIgnoreCase("TESTCOMPONENT_MSG_TYPE_TEXT")) {
        			testTextMessage.setContent("TESTCOMPONENT_MSG_TYPE_TEXT_callback");
        		} else if (requestContent.startsWith("QUERY_AUTH_CODE:")) {
        			final String preAuthCode = requestContent.substring(16);
        			
        			Jedis jedis = RedisConnectionPoolFactory.getResource();
        			
					String apiQueryAuthUrl = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=COMPONENT_ACCESS_TOKEN";
        			WeixinOpenPlatformEntity platform = weixinOpenPlatformService.findByAppId(componentAppId);
        			String componentAccessToken = weixinOpenPlatformService.getComponentAccessToken(platform.getId());
        			apiQueryAuthUrl = apiQueryAuthUrl.replace("COMPONENT_ACCESS_TOKEN", componentAccessToken);
        			JSONObject postData = new JSONObject();
        	        postData.put("component_appid", componentAppId);
        	        postData.put("authorization_code", jedis.get("internet_publish_authorization_code"));
        	        JSONObject obj = WeixinUtil.httpRequest(apiQueryAuthUrl, "POST", postData.toString());
        	        if (obj.containsKey("authorization_info")) {
        	        	JSONObject authInfo = obj.getJSONObject("authorization_info");
        	        	String authorizerAccessToken = authInfo.getString("authorizer_access_token");
        	        	String customSendUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + authorizerAccessToken;
        	        	postData.clear();
        	        	postData.accumulate("touser", fromUserName).accumulate("msgtype", "text")
        	        	.accumulate("text", new JSONObject().accumulate("content", preAuthCode + "_from_api"));
        	        	WeixinUtil.httpRequest(customSendUrl, "POST", postData.toString());
        	        	LogUtil.info("全网发布发布客服消息:" + postData.toString());
        	        } else {
        	        	LogUtil.info(String.format("授权码换取公众号授权信息失败：%s", obj));
        	        }
        			
        			return "";
        		}
        	}
        	
        	//return OpenPlatformMessageUtil.encryptMsg(MessageUtil.textMessageToXml(testTextMessage), request.getParameter("timestamp"), request.getParameter("nonce"), token, encodingAesKey, componentAppId);
            String testRespMsg = MessageUtil.textMessageToXml(testTextMessage);
            LogUtil.info("全网发布返回消息" + testRespMsg);
        	return testRespMsg;
        }
        
        // 根据微信ID,获取配置的全局的数据权限ID
        WeixinAccountEntity paramEntit = new WeixinAccountEntity();
        paramEntit.setWeixin_accountid(toUserName);
        WeixinAccountEntity accountEntity = weixinAccountService.findByEntity(paramEntit);

        if (accountEntity == null) {
            return "";
        }
    	
    	return wechatService.coreService(request, requestMap, accountEntity);
    }
}

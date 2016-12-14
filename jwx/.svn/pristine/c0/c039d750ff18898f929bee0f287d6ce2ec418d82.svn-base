package weixin.idea.extend.function.impl;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.core.req.model.kfaccount.KfcustomSend;
import org.jeewx.api.core.req.model.kfaccount.MsgText;
import org.jeewx.api.wxsendmsg.JwKfaccountAPI;
import org.jeewx.api.wxsendmsg.model.WxKfaccount;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.entity.message.resp.TextMessageResp;
import weixin.guanjia.core.util.MessageUtil;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.message.entity.TextTemplate;
import weixin.guanjia.message.model.TextItem;
import weixin.guanjia.message.model.TextMessageKf;
import weixin.idea.extend.function.KeyServiceI;

/**
 * 微网站
 * 
 * @author zhangdaihao
 *
 */
public class WeixinDKFService implements KeyServiceI {

	public String excute(String content, TextMessageResp defaultMessage, HttpServletRequest request) {
		String parimaryKey = request.getParameter("t");
		WeixinAccountServiceI weixinAccountService = (WeixinAccountServiceI) ApplicationContextUtil.getContext()
				.getBean("weixinAccountService");
		// 此userid后期需要通过高级接口获取到微信帐号，此处先以加密后的ID为参数进行传递
		String token = weixinAccountService.getAccessTokenByPrimaryKey(parimaryKey);
		/*List<WxKfaccount> wkfList = null;
		try {
			wkfList = JwKfaccountAPI.getAllOnlineKfaccount(token);
		} catch (WexinReqException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (wkfList != null && wkfList.size() > 0) {*/
			TextMessageResp textMessage = new TextMessageResp();
			textMessage.setToUserName(defaultMessage.getToUserName());
			textMessage.setFromUserName(defaultMessage.getFromUserName());
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
			StringBuffer url = new StringBuffer();
			url.append("点击<a href='");
			url.append(ResourceUtil.getDomain()).append("/").append("weixinCustomerMsgController.do?talk");
			url.append("&t=").append(parimaryKey);
			url.append("&AId=").append(defaultMessage.getToUserName());
			url.append("'");
			url.append(">这里</a>进入与客服交流");
			textMessage.setContent(url.toString());
			
			
			return MessageUtil.textMessageToXml(textMessage);
	/*	}*/


	}

	@Override
	public String getKey() {

		return "帮助";
	}

	public String sendMessage(String json, String accessTocken) {
		String send_message_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
		String url = send_message_url.replace("ACCESS_TOKEN", accessTocken);
		JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", json);
		return jsonObject.toString();
	}
}

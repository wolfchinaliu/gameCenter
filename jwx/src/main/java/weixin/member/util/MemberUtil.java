package weixin.member.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.message.controller.EmojiFilter;
import weixin.member.entity.WeixinMemberEntity;

public class MemberUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = Logger.getLogger(MemberUtil.class);
	public static WeixinMemberEntity loadMemberInfo(String accessToken, String openId,
			String accountId,WeixinMemberEntity weixinMemberEntity) {
		LOGGER.info("-----accessToken:"+accessToken+"------openId:"+openId);

		// 接口参数
		String url = WeixinUtil.download_member_info_url.replace(
				"ACCESS_TOKEN", accessToken).replace("OPENID", openId);

		JSONObject jsonO = new JSONObject();
		jsonO.put("openid", openId);// 普通用户的标识，对当前公众号唯一
		jsonO.put("lang", "zh_CN");
		// 获取接口返回结果
		JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET", null);
		LOGGER.info(jsonObject);
		if (jsonObject != null) {
			if (null == jsonObject.get("errcode")) {
				if ("1".equals(jsonObject.getString("subscribe"))) {
					weixinMemberEntity.setSubscribe(jsonObject.getString("subscribe"));
					weixinMemberEntity.setOpenId(jsonObject.getString("openid"));
					weixinMemberEntity.setNickName(EmojiFilter.filterEmoji(jsonObject.getString("nickname")));
					weixinMemberEntity.setSex(jsonObject.getString("sex"));
					weixinMemberEntity.setCity(jsonObject.getString("city"));
					weixinMemberEntity.setCountry(jsonObject.getString("country"));
					weixinMemberEntity.setProvince(jsonObject.getString("province"));
					weixinMemberEntity.setLanguage(jsonObject.getString("language"));
					weixinMemberEntity.setHeadImgUrl(jsonObject.getString("headimgurl"));
					weixinMemberEntity.setAccountId(accountId);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date;
					try {
						int aa = jsonObject.getInt("subscribe_time");
						String sd = sdf.format(aa * 1000L);
						date = sdf.parse(sd);
						weixinMemberEntity.setSubscribeTime(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			// {"errcode":48001,"errmsg":"api unauthorized hint: [R_bjQa0157vr18]"}
			} else {
				weixinMemberEntity.setSubscribe("1");
				weixinMemberEntity.setOpenId(openId);
				weixinMemberEntity.setSubscribeTime(new Date());
                weixinMemberEntity.setAccountId(accountId);
            }
		}
		return weixinMemberEntity;
	}

	/**
	 * 查询用户所在分组
	 * 
	 * @param accessToken
     * @param openid
     * @return
	 */
	public static int getGroupIdByMember(String accessToken, String openid) {
		String url = WeixinUtil.download_member_groupid_info_url.replace(
				"ACCESS_TOKEN", accessToken);
		JSONObject jsonO = new JSONObject();
		jsonO.put("openid", openid);

		// 获取接口返回结果
		JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET", jsonO.toString());
		if (jsonObject != null && null == jsonObject.get("errcode")) {
            return jsonObject.getInt("groupid");
		}
		return 0;
	}
}

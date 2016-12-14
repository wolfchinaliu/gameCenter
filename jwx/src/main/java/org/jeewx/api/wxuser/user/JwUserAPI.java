package org.jeewx.api.wxuser.user;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.core.req.WeiXinReqService;
import org.jeewx.api.core.req.model.user.UserBaseInfoGet;
import org.jeewx.api.core.req.model.user.UserInfoBatchGet;
import org.jeewx.api.core.req.model.user.UserInfoListGet;
import org.jeewx.api.wxuser.thread.LoadMemberInfoTask;
import org.jeewx.api.wxuser.user.model.WxUserList;
import org.jeewx.api.wxuser.user.model.Wxuser;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.RedisUtil;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 微信--用户
 *
 * @author lizr
 */
public class JwUserAPI {
    public static final transient Logger LOGGER = Logger.getLogger(JwUserAPI.class);

    /**
     * 根据user_openid 获取关注用户的基本信息
     *
     * @param shelf_id
     * @return
     * @throws WexinReqException
     */
    public static Wxuser getWxuser(String accesstoken, String user_openid) throws WexinReqException {
        if (accesstoken != null) {
            UserBaseInfoGet userBaseInfoGet = new UserBaseInfoGet();
            userBaseInfoGet.setAccess_token(accesstoken);
            userBaseInfoGet.setOpenid(user_openid);
            JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(userBaseInfoGet);
            // 正常返回
            Wxuser wxuser = null;
            Object error = result.get("errcode");
            if (error == null) {
                wxuser = new Gson().fromJson(result.toString(), Wxuser.class);
            }
            return wxuser;
        }
        return null;
    }

    /**
     * 根据user_openid 获取关注用户的基本信息
     *
     * @param accesstoken
     * @param userOpenIds
     * @return
     * @throws WexinReqException
     */
    public static WxUserList getWxUsers(String accesstoken, List<String> userOpenIds) throws WexinReqException {
        if (accesstoken != null) {
            UserInfoBatchGet userInfoBatchGet = new UserInfoBatchGet();
            userInfoBatchGet.setAccess_token(accesstoken);
            userInfoBatchGet.addUserList(userOpenIds, "zh_CN");
            JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(userInfoBatchGet);
            // 正常返回
            WxUserList wxUserList = null;
            Object error = result.get("errcode");
            if (error == null) {
                wxUserList = new Gson().fromJson(result.toString(), WxUserList.class);
            } else {
                LOGGER.warn(result);
            }
            return wxUserList;
        }
        return null;
    }

    /**
     * 获取所有关注用户信息信息
     *
     * @return
     * @throws WexinReqException
     */
    public static List<Wxuser> getAllWxuser(String accesstoken, String next_openid) throws WexinReqException {
        if (accesstoken != null) {
            UserInfoListGet userInfoListGet = new UserInfoListGet();
            userInfoListGet.setAccess_token(accesstoken);
            userInfoListGet.setNext_openid(next_openid);
            JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(userInfoListGet);
            Object error = result.get("errcode");
            List<Wxuser> lstUser = null;
            Wxuser mxuser = null;
            if (error == null) {
                int total = result.getInt("total");
                int count = result.getInt("count");
                String strNextOpenId = result.getString("next_openid");
                JSONObject data = result.getJSONObject("data");
                lstUser = new ArrayList<Wxuser>(total);
                if (count > 0) {
                    JSONArray lstOpenid = data.getJSONArray("openid");
                    int iSize = lstOpenid.size();
                    for (int i = 0; i < iSize; i++) {
                        String openId = lstOpenid.getString(i);
                        mxuser = getWxuser(accesstoken, openId);
                        lstUser.add(mxuser);
                    }
                    if (strNextOpenId != null) {
                        lstUser.addAll(getAllWxuser(accesstoken, strNextOpenId));
                    }
                }
            }
            return lstUser;
        }
        return null;
    }

    public static void getAllWxuser1(Logger logger, WeixinAccountServiceI weixinAccountService, String accountId, WeixinMemberServiceI weixinMemberService, String next_openid) throws WexinReqException {
        String accessToken = weixinAccountService.getAccessToken(accountId);
        if (accessToken == null) {
            return;
        }

        UserInfoListGet userInfoListGet = new UserInfoListGet();
        userInfoListGet.setAccess_token(accessToken);
        userInfoListGet.setNext_openid(next_openid);
        JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(userInfoListGet);
        String error = null;
        if (result.isNullObject() || StringUtils.isNotBlank(error = result.optString("errcode"))) {// 有错!
            if ("48001".equals(result.optString("errcode"))) {
                logger.warn("同步粉丝列表：当前商家的公众号不支持同步粉丝列表，当前商家的公众号为未认证的订阅号或者未认证的服务号");
                throw new BusinessException("您的公众号不支持同步粉丝功能");
            } else {
                logger.warn("同步粉丝列表：微信平台返回错误码：" + result);
                throw new BusinessException("同步粉丝列表失败，错误码：" + error);
            }
        }

        // int total = result.getInt("total");
        int count = result.getInt("count");
        String strNextOpenId = result.getString("next_openid");
        JSONObject data = result.getJSONObject("data");
        if (count > 0) {
            JSONArray allOpenIdList = data.getJSONArray("openid");
            Set<String> allOpenIdSet = new HashSet<>();
            for (int i = 0; i < allOpenIdList.size(); i++) {
                allOpenIdSet.add(allOpenIdList.optString(i));
            }
            List<WeixinMemberEntity> existsMemberEntities = weixinMemberService.getWeixinMemberByOpenIds(allOpenIdSet, accountId);
            List<WeixinMemberEntity> newMemberEntities = new ArrayList<>();

            for (WeixinMemberEntity weixinMemberEntity : existsMemberEntities) {
                weixinMemberEntity.setSubscribe("1");
                allOpenIdSet.remove(weixinMemberEntity.getOpenId());
            }

            for (String openId : allOpenIdSet) {
                WeixinMemberEntity memberEntity = new WeixinMemberEntity();
                memberEntity.setAccountId(accountId);
                memberEntity.setOpenId(openId);
                memberEntity.setSubscribe("1");
                memberEntity.setIsRealOpenid((short)1);
                newMemberEntities.add(memberEntity);
            }

            if (!newMemberEntities.isEmpty()) {
                weixinMemberService.batchSave(newMemberEntities);
            }
            if (!existsMemberEntities.isEmpty()) {
                weixinMemberService.batchUpdate(existsMemberEntities);
            }

            String message = "商家[{0}]已更新粉丝{1}个,其中已有粉丝:{2}，新增粉丝：{3}";
            logger.info(MessageFormat.format(message, accountId, allOpenIdList.size(), existsMemberEntities.size(), newMemberEntities.size()));

            if (strNextOpenId != null) {
                RedisUtil.setRedis(accountId + ".member.next.openId", strNextOpenId);
                getAllWxuser1(logger, weixinAccountService, accountId, weixinMemberService, strNextOpenId);
            }
        } else {
            new LoadMemberInfoTask(accountId, weixinMemberService, weixinAccountService).run();
        }
    }

}

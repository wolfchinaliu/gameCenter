package sdk.jfinal;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.ResourceUtil;
import weixin.liuliangbao.jsonbean.MerchantInfoBean;
import weixin.liuliangbao.jsonbean.Update;
import weixin.liuliangbao.jsonbean.ViewBean.UserFlowGiveBean;
import weixin.liuliangbao.util.HttpUtil;
import weixin.util.CommonUtils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by GuoLiang on 2016/7/7 17:06.
 */
public class JFinalUtils {
    public static final transient Logger LOGGER = Logger.getLogger(JFinalUtils.class);
    // jfinal地址的公众前缀
    private static String JFINAL_BASE_PATH = ResourceUtil.getConfigByName("jfinalUrl-config");
    //
    public static final String QUERY_FLOW_RULE_URL = JFINAL_BASE_PATH + "userGetFlow/QueryFlowRule";
    public static final String SHARE_URL = JFINAL_BASE_PATH + "userGetFlow/share";
    public static final String GETCOVERANDLOCATION_URL = JFINAL_BASE_PATH + "userGetFlow/getCoverAndLocation";

    /**
     * 查询商户的流量赠送规则
     *
     * @param accountid      商家公众号Id
     * @param operateType    操作类型
     * @return
     */
    public static MerchantInfoBean getMerchantFlowRule(String accountid, String operateType) {
        Gson gson = new Gson();
        JSONObject params = new JSONObject();
        params.element("id", accountid);
        params.element("opreateType", operateType);
        JSONObject resultObj = HttpUtil.httpPost(QUERY_FLOW_RULE_URL, params, false);
        String resultStr = gson.toJson(resultObj);
        Type typeBinding = new TypeToken<MerchantInfoBean>() {}.getType();
        return gson.fromJson(resultStr, typeBinding);
    }

    /**
     * 分享
     *
     * @param openId             微信用户id
     * @param shiliuOpenId       主公众号的微信id
     * @param shiliuAccountId    注公众号的商家Id
     * @param accountid          商家的Id
     * @param shareId            分享资源(文章)的id
     * @param nickname           昵称
     * @param shareWay           分享方式
     * @return  分享后的状态
     */
    public static JSONObject share(String openId, String shiliuOpenId, String shiliuAccountId, String accountid, String shareId, String nickname, String shareWay) {
        LOGGER.info("JFinalUtils.share(openId = [" + openId + "], shiliuOpenId = [" + shiliuOpenId + "], accountid = [" + accountid + "], shareId = [" + shareId + "], nickname = [" + nickname + "], shareWay = [" + shareWay + "])");
        if (CommonUtils.isAnyBlank(shiliuOpenId, accountid, shareId, shareWay, shiliuAccountId)) {
            throw new BusinessException("非法请求,请重试");
        }

        JSONObject params = new JSONObject();
        params.element("openId", openId).element("shiliuOpenId", shiliuOpenId).element("shiliuAccountId", shiliuAccountId).element("accountid", accountid).element("shareId", shareId).element("nickname", nickname).element("shareWay", shareWay);
        JSONObject jsonObject = HttpUtil.httpPost(SHARE_URL, params, false);
        System.out.println("分享送流量执行结果:" + jsonObject);
        return jsonObject;
    }

    /**
     * 获取流量账户信息
     *
     * @param openId         微信用户Id
     * @param phoneNumber    用户手机号码
     * @return
     */
    public static MerchantInfoBean getUserFlowAccount(String openId, String phoneNumber) {
        String urlFlowAccount = JFINAL_BASE_PATH + "userGetFlow/QueryFlowAccount";
        JSONObject myJsonAccount = new JSONObject();
        myJsonAccount.accumulate("phoneNumber", phoneNumber);
        // 如果允许没有绑定手机号就可以参数活动,查询用户手机流量账户的时候要带上openId, 根据openId也是可以查询到流量账户的
        if (StringUtils.isNotBlank(openId)) {
            myJsonAccount.accumulate("openId", openId);
        }
        JSONObject contentFlowAccount = HttpUtil.httpPost(urlFlowAccount, myJsonAccount, false);

        Gson gson = new Gson();
        String reStrFlowAccount = gson.toJson(contentFlowAccount);
        Type typeFlowAccount = new TypeToken<MerchantInfoBean>() {}.getType();
        return gson.fromJson(reStrFlowAccount, typeFlowAccount);
    }

    /**
     * 签到
     *
     * @param merchantId     商家Id
     * @param phoneNumber    手机号码
     * @param openId         微信用户Id
     * @return
     */
    public static Update sign(String merchantId, String phoneNumber, String openId) {
        String url = JFINAL_BASE_PATH + "sign/sign";
        JSONObject myJsonObject = new JSONObject();
        myJsonObject.element("merchantID", merchantId);
        myJsonObject.element("phoneNumber", phoneNumber);
        myJsonObject.element("openId", openId);
        myJsonObject.element("operateType", "签到");
        JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);

        Gson gson = new Gson();
        String reStr = gson.toJson(content);
        Type type = new TypeToken<Update>() { }.getType();
        return gson.fromJson(reStr, type);
    }

    /**
     * 绑定手机号
     *
     * @param openId             用户openId
     * @param shiliuOpenId       用户的主公众号的openId
     * @param operateType        操作类型
     * @param phoneNumber        用户手机号
     * @param userName           用户的昵称
     * @param merchantID         商家的公众号Id
     * @param shiliuAccountId    主公众号Id
     * @return
     */
    public static Map<String, Object> bind(String openId, String shiliuOpenId, String operateType, String phoneNumber, String userName, String merchantID, String shiliuAccountId) {
        JSONObject myJsonObject = new JSONObject();
        myJsonObject.element("openId", openId);
        myJsonObject.element("operateType", operateType);
        myJsonObject.element("phoneNumber", phoneNumber);
        myJsonObject.element("userName", userName);
        myJsonObject.element("merchantID", merchantID);
        myJsonObject.element("shiliuOpenId", shiliuOpenId);
        myJsonObject.element("shiliuAccountId", shiliuAccountId);
        JSONObject content = HttpUtil.httpPost(JFINAL_BASE_PATH + "bind/bind", myJsonObject, false);

        Gson gson = new Gson();
        String reStr = gson.toJson(content);
        Type type = new TypeToken<Update>() { }.getType();
        Update update = gson.fromJson(reStr, type);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", !"false".equals(update.getCode()));
        map.put("msg", update.getMessage());
        return map;
    }

    /**
     * app绑定手机开账户
     * @param openId             用户openId
     * @param phoneNumber        用户手机号
     * @param merchantID         商家的公众号Id
     * @return
     */
    public static Map<String, Object> appBind(String openId,String phoneNumber,  String merchantID) {
        JSONObject myJsonObject = new JSONObject();
        myJsonObject.element("openId", openId);
        myJsonObject.element("phoneNumber", phoneNumber);
        myJsonObject.element("merchantID", merchantID);
        JSONObject content = HttpUtil.httpPost(JFINAL_BASE_PATH + "bind/appBind", myJsonObject, false);
        Gson gson = new Gson();
        String reStr = gson.toJson(content);
        Type type = new TypeToken<Update>() { }.getType();
        Update update = gson.fromJson(reStr, type);
        System.out.println(update.getCode());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", !"false".equals(update.getCode()));
        map.put("msg", update.getMessage());
        return map;
    }

    /**
     * 调用接口查询用户流量获取记录
     *
     * @param accountid      商家公众号Id
     * @param openid         openId
     * @param phoneNumber    用户手机卡号
     * @return
     */
    public static UserFlowGiveBean getUserFlowGiveRecord(String accountid, String openid, String phoneNumber) {
        Gson gson = new Gson();
        Type type = new TypeToken<UserFlowGiveBean>() {
        }.getType();

        String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "flowGetRecords/queryUserFlowGetRecord";
        JSONObject myJsonObject = new JSONObject();
        myJsonObject.accumulate("phoneNumber", phoneNumber);
        myJsonObject.accumulate("accountid", accountid);
        myJsonObject.accumulate("openid", openid);


        JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
        String strcontent = gson.toJson(content);
        return gson.fromJson(strcontent, type);
    }
    /**
     * 判断手机是否在覆盖区域
     * @param phoneNumber
     * @param accountId
     * @return
     */
    public static boolean CKCoverandLocation(String phoneNumber,String accountId){
        Gson gson = new Gson();
        Type type = new TypeToken<UserFlowGiveBean>() {
        }.getType();

        String url = JFinalUtils.GETCOVERANDLOCATION_URL;
        JSONObject myJsonObject = new JSONObject();
        myJsonObject.accumulate("phoneNumber", phoneNumber);
        myJsonObject.accumulate("id", accountId);
        
        JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
        String strcontent = gson.toJson(content);
        UserFlowGiveBean  ufgb= gson.fromJson(strcontent, type);     
        if("200".equals(ufgb.getCode())){ //在覆盖区域
            return true;
        }else{
            return false;
        }
    }
    /**
     * 大转盘抽奖
     *
     * @param hdId      活动Id
     * @param openId    openId
     * @return
     */
    public static Update luckyTurnable(String hdId, String openId) {
        String url = JFINAL_BASE_PATH + "lottery/luckyTurntable";
        JSONObject params = new JSONObject();
        params.element("id", hdId);
        params.element("openId", openId);
        JSONObject content = HttpUtil.httpPost(url, params, false);

        Gson gson = new Gson();
        String reStr = gson.toJson(content);
        Type type = new TypeToken<Update>() { }.getType();
        return gson.fromJson(reStr, type);
    }

    /**
     * 获取活动剩余次数
     *
     * @param hdid      活动Id
     * @param openId    openId
     * @return
     */
    public static Update getLeftTimes(String hdid, String openId) {
        String url = JFINAL_BASE_PATH + "lottery/getLeftTimes";
        JSONObject params = new JSONObject();
        params.element("id", hdid);
        params.element("openId", openId);
        JSONObject content = HttpUtil.httpPost(url, params, false);

        Gson gson = new Gson();
        String reStr = gson.toJson(content);
        Type type = new TypeToken<Update>() { }.getType();
        Update response = gson.fromJson(reStr, type);
        LOGGER.info("JFinalUtils.getLeftTimes:" + response);
        if (null == response) {
            response = new Update("400", "服务器开小差了,请刷新后再试!");
            response.getAttributes().put("count", "0");
        }
        return response ;
    }

    /**
     * 刮刮卡抽奖
     *
     * @param hdId      活动Id
     * @param openId    用户openId
     * @return
     */
    public static Update scratchCard(String hdId, String openId) {
        String url = JFINAL_BASE_PATH + "lottery/scratchCard";
        JSONObject params = new JSONObject();
        params.element("id", hdId);
        params.element("openId", openId);
        JSONObject content = HttpUtil.httpPost(url, params, false);

        Gson gson = new Gson();
        String reStr = gson.toJson(content);
        Type type = new TypeToken<Update>() { }.getType();
        return gson.fromJson(reStr, type);
    }

    /**
     * 保存刮刮卡的中奖信息
     *
     * @param scratchInfo    刮刮卡中奖信息
     * @return
     */
    public static Update saveScratchRecord(Update scratchInfo) {
        String url = JFINAL_BASE_PATH + "lottery/saveScratchWinningRecord";
        Gson gson = new Gson();
        JSONObject content = HttpUtil.httpPost(url, gson.toJson(scratchInfo), false);
        String result = gson.toJson(content);
        return gson.fromJson(result, Update.class);
    }

    /**
     * 拆红包
     *
     * @param hdId      活动Id
     * @param openId    openId
     * @return
     */
    public static Update openRedpacket(String hdId, String openId) {
        String url = JFINAL_BASE_PATH + "lottery/openRedPacket";
        JSONObject params = new JSONObject();
        params.element("id", hdId);
        params.element("openId", openId);
        JSONObject content = HttpUtil.httpPost(url, params, false);

        Gson gson = new Gson();
        String reStr = gson.toJson(content);
        Type type = new TypeToken<Update>() { }.getType();
        return gson.fromJson(reStr, type);
    }

    /**
     * 摇一摇
     *
     * @param hdId      活动Id
     * @param openId    openId
     * @return
     */
    public static Update shakeHand(String hdId, String openId) {
        String url = JFINAL_BASE_PATH + "lottery/shakeHand";
        JSONObject params = new JSONObject();
        params.element("id", hdId);
        params.element("openId", openId);
        JSONObject content = HttpUtil.httpPost(url, params, false);

        Gson gson = new Gson();
        String reStr = gson.toJson(content);
        Type type = new TypeToken<Update>() { }.getType();
        Update result = gson.fromJson(reStr, type);
        LOGGER.info("摇一摇返回结果:" + result);
        return result;
    }
}

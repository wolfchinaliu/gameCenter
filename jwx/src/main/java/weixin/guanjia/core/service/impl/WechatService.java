package weixin.guanjia.core.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.Now;
import org.hibernate.mapping.IdGenerator;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.cgform.engine.FreemarkerHelper;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.core.ReferenceByIdMarshaller.IDGenerator;

import weixin.business.entity.WeixinCardEntity;
import weixin.business.entity.WeixinLocationEntity;
import weixin.customer.entity.WeixinCustomerLxcRespEntity;
import weixin.customer.service.WeixinCustomerLxcRespServiceI;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.base.entity.Subscribe;
import weixin.guanjia.base.entity.WeixinExpandconfigEntity;
import weixin.guanjia.base.service.SubscribeServiceI;
import weixin.guanjia.base.service.WeixinExpandconfigServiceI;
import weixin.guanjia.core.entity.message.resp.*;
import weixin.guanjia.core.util.MessageUtil;
import weixin.guanjia.menu.entity.MenuEntity;
import weixin.guanjia.message.dao.TextTemplateDao;
import weixin.guanjia.message.entity.*;
import weixin.guanjia.message.service.*;
import weixin.idea.extend.function.KeyServiceI;
import weixin.member.entity.WeixinGroupEntity;
import weixin.member.entity.WeixinMemberCardEntity;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinGroupServiceI;
import weixin.member.service.WeixinMemberServiceI;
import weixin.member.util.MemberUtil;
import weixin.memberStructure.entity.LogMemberDatailedEntity;
import weixin.memberStructure.service.LogMemberDatailedService;
import weixin.payment.entity.WeixinUsergetcardEntity;
import weixin.report.service.UserGiveFlowServiceI;
import weixin.source.entity.WeixinSourceEntity;
import weixin.source.service.WeixinSourceServiceI;
import weixin.util.DateUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 接收微信推送事件处理
 *
 * @author Administrator
 */
@Service("wechatService")
public class WechatService {
    private static final Logger LOGGER = Logger.getLogger(WechatService.class);
    @Autowired
    private TextTemplateDao textTemplateDao;
    @Autowired
    private AutoResponseServiceI autoResponseService;
    @Autowired
    private TextTemplateServiceI textTemplateService;
    @Autowired
    private NewsTemplateServiceI newsTemplateService;
    @Autowired
    private ReceiveTextServiceI receiveTextService;
    @Autowired
    private NewsItemServiceI newsItemService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private SubscribeServiceI subscribeService;
    @Autowired
    private WeixinExpandconfigServiceI weixinExpandconfigService;
    @Autowired
    private WeixinAccountServiceI weixinAccountService;
    @Autowired
    WeixinGroupServiceI weixinGroupService;
    @Autowired
    WeixinSourceServiceI weixinSourceService;
    @Autowired
    private WeixinMemberServiceI weixinMemberService;
    @Autowired
    private WeixinCustomerLxcRespServiceI weixinCustomerLxcRespService;
    @Autowired
    private UserGiveFlowServiceI userGiveFlowService;
    @Autowired
    private LogMemberDatailedService logMemberDatailedService;



    /**
     * 事件处理核心控制方法
     *
     * @param request
     * @return
     */
    public String coreService(HttpServletRequest request) {

        // 默认返回的文本消息内容,主键+
        String sys_accountId = request.getParameter("t");

        // xml请求解析
        Map<String, String> requestMap;
        try {
            requestMap = MessageUtil.parseXml(request);
        } catch (Exception e1) {
            LogUtil.error("读取request失败" + e1.getMessage(), e1);
            return "";
        }


        // 根据微信ID,获取配置的全局的数据权限ID
        WeixinAccountEntity paramEntit = new WeixinAccountEntity();
        paramEntit.setId(sys_accountId);
        WeixinAccountEntity accountEntity = weixinAccountService.findByEntity(paramEntit);

        if (accountEntity == null) {
            return "";
        }


        return coreService(request, requestMap, accountEntity);
    }

    public String coreService(HttpServletRequest request, Map<String, String> requestMap,
                              WeixinAccountEntity accountEntity) {
        String respMessage = null;
        String fromUserName = "";
        try {


            // 发送方帐号（open_id）
            fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            //关注时间
            String subscribeTime = requestMap.get("CreateTime");

            String respContent = "请求处理异常，请稍候尝试！";
            // 事件类型
            String eventType = requestMap.get("Event");
            LogUtil.info(MessageFormat.format("收到微信请求:openId={0},收到消息的公众号Id:{1},消息类型:{2},事件类型为:{3}", fromUserName, toUserName, msgType, eventType));

            String nickName = "";
            WeixinMemberEntity weixinMemberEntity = weixinMemberService.getWeixinMemberEntity(fromUserName, accountEntity.getId());
            if (null != weixinMemberEntity) {
                nickName = weixinMemberEntity.getNickName();
            }

            ResourceBundle bundler = null;
            // 默认回复此文本消息
            TextMessageResp textMessage = new TextMessageResp();
            // 将文本消息对象转换成xml字符串
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            // 【微信触发类型】文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {

                String msgId = requestMap.get("MsgId");
                // 消息内容
                String content = requestMap.get("Content");
                LogUtil.info("微信客户端发送请求:消息类型为文本消息---");
                respMessage = doTextResponse(content, toUserName, textMessage, bundler, accountEntity.getId(), respMessage, fromUserName, request, msgId, msgType, nickName);
            }
            // 【微信触发类型】图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
            }
            // 【微信触发类型】地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 【微信触发类型】链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 【微信触发类型】音频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是音频消息！";
            }
            // 【微信触发类型】事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                //TODO:修改后再改回来

                //门店审核事件推送
                if (eventType.equals("poi_check_notify")) {

                    String sid = requestMap.get("UniqId");//商户自己内部的id
                    String poiId = requestMap.get("PoiId");//微信门店ID
                    String result = requestMap.get("Result");//审核结果 成功：succ, 失败:fail
                    String msg = requestMap.get("Msg");//成功信息，或者失败细心耐心
                    WeixinLocationEntity weixinLocation = systemService.get(WeixinLocationEntity.class, sid);
                    if (null != weixinLocation) {

                        if ("0".equals(weixinLocation.getStatus())) {

                            weixinLocation.setPoiId(poiId);
                            if ("succ".equals(result))
                                weixinLocation.setStatus("1");
                            if ("fail".equals(result))
                                weixinLocation.setStatus("3");
                            weixinLocation.setMsg(msg);
                            systemService.saveOrUpdate(weixinLocation);
                        }
                    }
                }

                //卡券审核事件推送
                if (eventType.equals("card_pass_check")) {

                    String cardid = requestMap.get("CardId");

                    //查询优惠券
                    WeixinCardEntity weixinCard = systemService.findUniqueByProperty(WeixinCardEntity.class, "cardId", cardid);

                    if (null != weixinCard && "0".equals(weixinCard.getStatus())) {

                        weixinCard.setStatus("1");
                        systemService.saveOrUpdate(weixinCard);
                    }

                    if (null == weixinCard) {

                        //会员卡
                        WeixinMemberCardEntity weixinMemberCard = systemService.findUniqueByProperty(WeixinMemberCardEntity.class, "cardId", cardid);

                        if (null != weixinMemberCard && "0".equals(weixinMemberCard.getStatus())) {

                            weixinMemberCard.setStatus("1");
                            systemService.saveOrUpdate(weixinMemberCard);
                        }
                    }
                }

                //卡券领取事件推送
                if (eventType.equals("user_get_card")) {

                    String cardid = requestMap.get("CardId");
                    String isGiveByFriend = requestMap.get("IsGiveByFriend");
                    String friendUser = requestMap.get("FriendUserName");
                    String userCardCode = requestMap.get("UserCardCode");//code序列号。自定义code及非自定义code的卡券被领取后都支持事件推送
                    String outerId = requestMap.get("OuterId");

                    WeixinUsergetcardEntity weixinUsergetcard = systemService.findUniqueByProperty(WeixinUsergetcardEntity.class, "userCardCode", userCardCode);
                    if (null == weixinUsergetcard) {

                        weixinUsergetcard = new WeixinUsergetcardEntity();
                        weixinUsergetcard.setAccountid(accountEntity.getId());
                        weixinUsergetcard.setCardId(cardid);
                        weixinUsergetcard.setOpenId(fromUserName);//领取方
                        weixinUsergetcard.setByFriend(isGiveByFriend);
                        weixinUsergetcard.setFriendUser(friendUser);
                        weixinUsergetcard.setOuterId(outerId);
                        weixinUsergetcard.setUserCardCode(userCardCode);
                        weixinUsergetcard.setStatus("0");
                        weixinUsergetcard.setCreateDate(new Date());

                        //所属卡券ID
                        WeixinCardEntity weixinCardEntity = systemService.findUniqueByProperty(WeixinCardEntity.class, "cardId", cardid);
                        weixinUsergetcard.setWeixinCardEntity(weixinCardEntity);

                        //领券用户
                        weixinUsergetcard.setWeixinMemberEntity(weixinMemberEntity);

                        systemService.save(weixinUsergetcard);
                    }
                }

                //卡券核销事件推送
                if (eventType.equals("user_consume_card")) {

                    String cardid = requestMap.get("CardId");
                    String userCardCode = requestMap.get("UserCardCode");//code序列号
                    String consumeSource = requestMap.get("ConsumeSource");

                    WeixinUsergetcardEntity weixinUsergetcard = systemService.findUniqueByProperty(WeixinUsergetcardEntity.class, "userCardCode", userCardCode);
                    if (null != weixinUsergetcard && "0".equals(weixinUsergetcard.getStatus())) {

                        weixinUsergetcard.setStatus("1");
                        weixinUsergetcard.setConsumeSource(consumeSource);

                        systemService.saveOrUpdate(weixinUsergetcard);
                    }
                    if (null == weixinUsergetcard) {

                        weixinUsergetcard = new WeixinUsergetcardEntity();
                        weixinUsergetcard.setAccountid(accountEntity.getId());
                        weixinUsergetcard.setCardId(cardid);
                        weixinUsergetcard.setOpenId(fromUserName);//领取方
                        weixinUsergetcard.setUserCardCode(userCardCode);
                        weixinUsergetcard.setCreateDate(new Date());
                        weixinUsergetcard.setStatus("1");
                        weixinUsergetcard.setConsumeSource(consumeSource);

                        systemService.save(weixinUsergetcard);
                    }
                }

                //卡券删除事件推送
                if (eventType.equals("user_del_card")) {

                    String userCardCode = requestMap.get("UserCardCode");//code序列号
                    WeixinUsergetcardEntity weixinUsergetcard = systemService.findUniqueByProperty(WeixinUsergetcardEntity.class, "userCardCode", userCardCode);
                    if (null != weixinUsergetcard) {

                        weixinUsergetcard.setStatus("3");
                        systemService.saveOrUpdate(weixinUsergetcard);
                    }
                }

                // 订阅
                //TODO:修改再改回去
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {

//				if (true) {

                    String accessToken = weixinAccountService.getAccessTokenByPrimaryKey(accountEntity.getId());

                    // 关注时,是否需要赠送流量?
                    // 赠送的条件:
                    // 1. 用户尚未关注公众号;
                    // 2.weixinMember中保存的注册类型为"2", 代表此weixinMember信息是因为用户关注其他商家时,自动在石榴科技的公众号中自动新增的粉丝记录;
                    // 3. 符合石榴科技的流量币赠送的规则(覆盖区域和运营商类型).

                    boolean needGiveUserAttensionFlow = false;
                    if (weixinMemberEntity == null) {
                        weixinMemberEntity = new WeixinMemberEntity();
                    } else if ("2".equals(weixinMemberEntity.getSubscribe()) && StringUtils.isNotBlank(weixinMemberEntity.getPhoneNumber())) {
                        needGiveUserAttensionFlow = true;
                        // Subscribe的值,会在loadMemberInfo中进行更新
                        weixinMemberEntity.setSubscribe("0");
                    }
                    String msgId = fromUserName+subscribeTime;  
                    if(!msgId.equals(weixinMemberEntity.getMsgId())){
                    weixinMemberEntity.setMsgId(msgId);
                    this.weixinMemberService.saveOrUpdate(weixinMemberEntity);
                    //TODO:之前jwx项目中的保存关注用户信息
                    weixinMemberEntity = MemberUtil.loadMemberInfo(accessToken, fromUserName, accountEntity.getId(), weixinMemberEntity);
                    int groupId = MemberUtil.getGroupIdByMember(accessToken, fromUserName);

                    WeixinGroupEntity weixinGroupEntity = new WeixinGroupEntity();
                    weixinGroupEntity.setGroupId(groupId);
                    weixinGroupEntity.setAccountid(accountEntity.getId());
                    weixinGroupEntity = weixinGroupService.getGroupEntityByParam(weixinGroupEntity);
                    weixinMemberEntity.setWeixinGroupEntity(weixinGroupEntity);
                    this.weixinMemberService.saveOrUpdate(weixinMemberEntity);
                    //保存关注记录
                    WeixinAccountEntity accountEntity2 = this.systemService.findUniqueByProperty(WeixinAccountEntity.class, "id", weixinMemberEntity.getAccountId());
                    LogMemberDatailedEntity logMemberDatailedEntity = new LogMemberDatailedEntity();
                 //   logMemberDatailedEntity.setId(UUIDGenerator.generate());
                    logMemberDatailedEntity.setOpenId(weixinMemberEntity.getOpenId());
                    logMemberDatailedEntity.setNickName(weixinMemberEntity.getNickName());
                    logMemberDatailedEntity.setSex(weixinMemberEntity.getSex());
                    logMemberDatailedEntity.setCity(weixinMemberEntity.getCity());
                    logMemberDatailedEntity.setProvince(weixinMemberEntity.getProvince());
                    logMemberDatailedEntity.setCountry(weixinMemberEntity.getCountry());
                    logMemberDatailedEntity.setTime(weixinMemberEntity.getSubscribeTime());
                    logMemberDatailedEntity.setSubscribe("1");
                    logMemberDatailedEntity.setAccountId(weixinMemberEntity.getAccountId());
                    logMemberDatailedEntity.setAcctId(accountEntity2.getAcctId());
                    if(weixinMemberEntity.getPhoneNumber() == null||weixinMemberEntity.getPhoneNumber() == ""){
                       logMemberDatailedEntity.setPhoneNumber(null);
                    }else{
                    logMemberDatailedEntity.setPhoneNumber(weixinMemberEntity.getPhoneNumber());
                    }
                    logMemberDatailedEntity.setCreated(new Date());
                    this.logMemberDatailedService.saveOrUpdate(logMemberDatailedEntity);
                    

                    
                    String content;
                    if (needGiveUserAttensionFlow
                            && StringUtils.isNotBlank(content = this.userGiveFlowService.giveUserFlow(fromUserName, weixinMemberEntity.getPhoneNumber(), weixinMemberEntity.getAccountId(), "关注"))) {
                        textMessage.setContent(content);
                        respMessage = MessageUtil.textMessageToXml(textMessage);
                    } else {
                        respMessage = doDingYueEventResponse(requestMap,
                                textMessage, bundler, respMessage, toUserName,
                                fromUserName, respContent, accountEntity.getId());
                    }
                    }
                    LOGGER.info("订阅事件: " + respMessage);
                    LOGGER.info(MessageFormat.format("用户[{0}]关注了商家:[{1}]", fromUserName, accountEntity.getId()));
                }
                // 取消订阅
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                    if (weixinMemberEntity != null) {
                    		Date date = new Date();
                    		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    		String countermandtime = simpleDateFormat.format(date);
                    		Date unsubscribeTime = simpleDateFormat.parse(countermandtime);
                    	weixinMemberEntity.setUnsubscribeTime(unsubscribeTime);
                        weixinMemberEntity.setSubscribe("0");
                        weixinMemberEntity.setWeixinGroupEntity(null);
                        this.systemService.saveOrUpdate(weixinMemberEntity);	
                        //保存取关记录
                        WeixinAccountEntity accountEntity2 = this.systemService.findUniqueByProperty(WeixinAccountEntity.class, "id", weixinMemberEntity.getAccountId());
                        LogMemberDatailedEntity logMemberDatailedEntity = new LogMemberDatailedEntity();
                      //  logMemberDatailedEntity.setId(UUIDGenerator.generate());
                        logMemberDatailedEntity.setOpenId(weixinMemberEntity.getOpenId());
                        logMemberDatailedEntity.setNickName(weixinMemberEntity.getNickName());
                        logMemberDatailedEntity.setSex(weixinMemberEntity.getSex());
                        logMemberDatailedEntity.setCity(weixinMemberEntity.getCity());
                        logMemberDatailedEntity.setProvince(weixinMemberEntity.getProvince());
                        logMemberDatailedEntity.setCountry(weixinMemberEntity.getCountry());
                        logMemberDatailedEntity.setTime(weixinMemberEntity.getUnsubscribeTime());
                        logMemberDatailedEntity.setSubscribe("0");
                        logMemberDatailedEntity.setAccountId(weixinMemberEntity.getAccountId());
                        logMemberDatailedEntity.setAcctId(accountEntity2.getAcctId());
                        if(weixinMemberEntity.getPhoneNumber() == null||weixinMemberEntity.getPhoneNumber() == ""){
                           logMemberDatailedEntity.setPhoneNumber(null);
                        }else{
                        logMemberDatailedEntity.setPhoneNumber(weixinMemberEntity.getPhoneNumber());
                        }
                        logMemberDatailedEntity.setCreated(new Date());
                        this.logMemberDatailedService.saveOrUpdate(logMemberDatailedEntity);
                        LogUtil.info(MessageFormat.format("用户:[{0}]取消关注了商家:[{1}]", weixinMemberEntity.getOpenId(), weixinMemberEntity.getAccountId()));
                    }
                }
                // 自定义菜单点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    respMessage = doMyMenuEvent(requestMap, textMessage,
                            bundler, respMessage, toUserName, fromUserName,
                            respContent, accountEntity.getId(), request);
                    LogUtil.info("用户[" + fromUserName + "]触发了类型为[" + requestMap.get("Event") + "]的菜单:[" + requestMap.get("EventKey") + "]");
                }
            }
            if(respMessage==null){
                respMessage = doDefaultEventResponse(requestMap, textMessage,
                        bundler, respMessage, toUserName, fromUserName,
                        respContent, accountEntity.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }

    /**
     * Q译通使用指南
     *
     * @return
     */
    public static String getTranslateUsage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("微译使用指南").append("\n\n");
        buffer.append("微译为用户提供专业的多语言翻译服务，目前支持以下翻译方向：").append("\n");
        buffer.append("    中 -> 英").append("\n");
        buffer.append("    英 -> 中").append("\n");
        buffer.append("    日 -> 中").append("\n\n");
        buffer.append("使用示例：").append("\n");
        buffer.append("    翻译我是中国人").append("\n");
        buffer.append("    翻译dream").append("\n");
        buffer.append("    翻译さようなら").append("\n\n");
        buffer.append("回复“?”显示主菜单");
        return buffer.toString();
    }

    /**
     * 遍历关键字管理中是否存在用户输入的关键字信息
     *
     * @param content
     * @return
     */
    private AutoResponse findKey(String content, String toUsername) {
        LogUtil.info("---------sys_accountId--------" + toUsername + "|");
        // 获取全局的数据权限ID
        String sys_accountId = weixinAccountService
                .findByToUsername(toUsername).getId();
        LogUtil.info("---------sys_accountId--------" + sys_accountId);
        // 获取关键字管理的列表，匹配后返回信息
        List<AutoResponse> autoResponses = autoResponseService.findByProperty(
                AutoResponse.class, "accountId", sys_accountId);
        LogUtil.info("---------sys_accountId----关键字查询结果条数：----" + autoResponses != null ? autoResponses
                .size() : 0);
        for (AutoResponse r : autoResponses) {
            // 如果包含关键字
            String kw = r.getKeyWord();
            String[] allkw = kw.split(",");
            for (String k : allkw) {
                if (k.equals(content)) {
                    LogUtil.info("---------sys_accountId----查询结果----" + r);
                    return r;
                }
            }
        }
        return null;
    }

    private AutoResponse findKeyByPrimaryKey(String sys_accountId, String content) {
        List<AutoResponse> autoResponses = autoResponseService.findByProperty(
                AutoResponse.class, "accountId", sys_accountId);
        LogUtil.info("---------sys_accountId----关键字查询结果条数：----" + autoResponses != null ? autoResponses
                .size() : 0);
        for (AutoResponse r : autoResponses) {
            // 如果包含关键字
            String kw = r.getKeyWord();
            String[] allkw = kw.split(",");
            for (String k : allkw) {
                if (k.equals(content)) {
                    LogUtil.info("---------sys_accountId----查询结果----" + r);
                    return r;
                }
            }
        }
        return null;
    }

    /**
     * 查询关键字是否是客服关键字-刘晓春-2015年12月7日
     *
     * @param accountId
     * @param content
     * @return
     */
    private WeixinCustomerLxcRespEntity findCustomerKey(String accountId, String content) {
        String hql = "from WeixinCustomerLxcRespEntity t where t.accountId='" + accountId + "' and t.keyWord='" + content + "'";
        List<WeixinCustomerLxcRespEntity> weixinCustomerKeys = weixinCustomerLxcRespService.findHql(hql, null);
        LogUtil.info("---------findCustomerKey----关键字查询结果条数：----" + (weixinCustomerKeys != null ? weixinCustomerKeys.size() : 0));

        if (!weixinCustomerKeys.isEmpty()) {
            return weixinCustomerKeys.get(0);
        }
        return null;
    }


    /**
     * 针对文本消息
     *
     * @param content
     * @param toUserName
     * @param textMessage
     * @param bundler
     * @param sys_accountId
     * @param respMessage
     * @param fromUserName
     * @param request
     * @param msgId
     * @param msgType
     * @param nickName
     * @return
     * @throws Exception
     */
    String doTextResponse(String content, String toUserName,
                          TextMessageResp textMessage, ResourceBundle bundler,
                          String sys_accountId, String respMessage, String fromUserName,
                          HttpServletRequest request, String msgId, String msgType,
                          String nickName) throws Exception {
        // =================================================================================================================
        // 保存接收到的信息
        ReceiveText receiveText = new ReceiveText();
        receiveText.setContent(content);
        Timestamp temp = Timestamp.valueOf(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
        receiveText.setCreateTime(temp);
        receiveText.setFromUserName(fromUserName);
        receiveText.setToUserName(toUserName);
        receiveText.setMsgId(msgId);
        receiveText.setMsgType(msgType);
        receiveText.setResponse("0");
        receiveText.setAccountId(toUserName);
        receiveText.setNickName(nickName);
        this.receiveTextService.save(receiveText);


        // =================================================================================================================
        // Step.1 判断关键字信息中是否管理该文本内容。有的话优先采用数据库中的回复
        LogUtil.info("------------微信客户端发送请求--------------Step.1 判断关键字信息中是否管理该文本内容。有的话优先采用数据库中的回复---");

        AutoResponse autoResponse = findKeyByPrimaryKey(sys_accountId, content);
        // 根据系统配置的关键字信息，返回对应的消息

        if (autoResponse != null) {
            String resMsgType = autoResponse.getMsgType();
            if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(resMsgType)) {
                // 根据返回消息key，获取对应的文本消息返回给微信客户端
                LogUtil.info("--------文本消息处理-------");
                TextTemplate textTemplate = textTemplateDao.getTextTemplate(sys_accountId, autoResponse.getTemplateName());
                LogUtil.info("---mubanneirong----" + textTemplate.getContent() + "------");
                textMessage.setToUserName(fromUserName);
                textMessage.setFromUserName(toUserName);
                textMessage.setCreateTime(new Date().getTime());
                textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                textMessage.setContent(textTemplate.getContent());
                respMessage = MessageUtil.textMessageToXml(textMessage);


            } else if (MessageUtil.RESP_MESSAGE_TYPE_NEWS.equals(resMsgType)) {
                // List<NewsItem> newsList = this.newsItemService.findByProperty(NewsItem.class, "newsTemplate.id", autoResponse.getResContent());
                List<NewsItem> newsList = this.newsItemService.findHql("from NewsItem a where a.newsTemplate.id=? order by orders asc", autoResponse.getResContent());
                NewsTemplate newsTemplate = newsTemplateService.getEntity(NewsTemplate.class, autoResponse.getResContent());
                List<Article> articleList = new ArrayList<Article>();
                for (NewsItem news : newsList) {
                    Article article = new Article();
                    article.setTitle(news.getTitle());
                    article.setPicUrl(ResourceUtil.getMediaUrlPrefix() + "/"
                            + news.getImagePath());
                    String url = "";
                    if (news.getUrlType().equals("default") || news.getUrlType().equals("默认页面")) {
                        url = ResourceUtil.getDomain() + "/newsItemController.do?newscontent&id=" + news.getId();
                    } else {
                        url = news.getUrl();
                    }
                    //todo:暂时将此处进行注释掉，以便以后更好滴进行改进
//                    if (news.getUrlType()!="outurl" || news.getUrlType()!="外部页面") {
                    article.setUrl(url + "&openId=" + fromUserName);
//                    }
                    article.setDescription(news.getDescription());
                    articleList.add(article);
                }
                NewsMessageResp newsResp = new NewsMessageResp();
                newsResp.setCreateTime(new Date().getTime());
                newsResp.setFromUserName(toUserName);
                newsResp.setToUserName(fromUserName);
                newsResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                newsResp.setArticleCount(newsList.size());
                newsResp.setArticles(articleList);
                respMessage = MessageUtil.newsMessageToXml(newsResp);
            }
        } else {
            // Step.2 通过微信扩展接口（支持二次开发，例如：翻译，天气）
            LogUtil.info("------------微信客户端发送请求--------------Step.2  通过微信扩展接口（支持二次开发，例如：翻译，天气）---");
            List<WeixinExpandconfigEntity> weixinExpandconfigEntityLst = weixinExpandconfigService
                    .findByQueryString("FROM WeixinExpandconfigEntity");
            if (weixinExpandconfigEntityLst.size() != 0) {
                for (WeixinExpandconfigEntity wec : weixinExpandconfigEntityLst) {
                    boolean findflag = false;// 是否找到关键字信息
                    // 如果已经找到关键字并处理业务，结束循环。
                    if (findflag) {
                        break;// 如果找到结束循环
                    }
                    String[] keys = wec.getKeyword().split(",");
                    for (String k : keys) {
                        if (content.contains(k)) {
                            String className = wec.getClassname();
                            KeyServiceI keyService = (KeyServiceI) Class
                                    .forName(className).newInstance();
                            respMessage = keyService.excute(content,
                                    textMessage, request);
                            findflag = true;// 改变标识，已经找到关键字并处理业务，结束循环。
                            break;// 当前关键字信息处理完毕，结束当前循环
                        }
                    }
                }
            }

        }
        WeixinCustomerLxcRespEntity weixinCustomerLxcResp = findCustomerKey(sys_accountId, content);
        if (weixinCustomerLxcResp != null) {
            CustomerMessageResp customerMeesage = new CustomerMessageResp();
            customerMeesage.setToUserName(fromUserName);
            customerMeesage.setFromUserName(toUserName);
            customerMeesage.setCreateTime(new Date().getTime());
            customerMeesage.setMsgType("transfer_customer_service");
            respMessage = MessageUtil.customerMessageToXml(customerMeesage);
        }
        LogUtil.info("--------最后的返回结果----" + respMessage + "---");
        return respMessage;
    }

    String doCustomerResponse(String content, String toUserName,
                              TextMessageResp textMessage, ResourceBundle bundler,
                              String sys_accountId, String respMessage, String fromUserName,
                              HttpServletRequest request, String msgId, String msgType,
                              String nickName) throws Exception {
        WeixinCustomerLxcRespEntity weixinCustomerLxcResp = findCustomerKey(sys_accountId, content);

        textMessage.setCreateTime(new Date().getTime());
        textMessage.setFromUserName(toUserName);
        textMessage.setToUserName(fromUserName);
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        textMessage.setContent(weixinCustomerLxcResp.getContent());
        respMessage = MessageUtil.textMessageToXml(textMessage);
        return respMessage;
    }

    /**
     * 针对事件消息
     *
     * @param requestMap
     * @param textMessage
     * @param bundler
     * @param respMessage
     * @param toUserName
     * @param fromUserName
     * @param respContent
     * @param sys_accountId
     * @return
     */
    String doDingYueEventResponse(Map<String, String> requestMap,
                                  TextMessageResp textMessage, ResourceBundle bundler,
                                  String respMessage, String toUserName, String fromUserName,
                                  String respContent, String sys_accountId) {
        respContent = "谢谢您的关注！回复\"?\"进入主菜单。";
        // 保存关注用户的信息
        List<Subscribe> lst = subscribeService.findByProperty(Subscribe.class,
                "accountid", sys_accountId);
        if (lst.size() != 0) {
            Subscribe subscribe = lst.get(0);
            String type = subscribe.getMsgType();
            if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(type)) {
                TextTemplate textTemplate = this.textTemplateService.getEntity(TextTemplate.class, subscribe.getTemplateId());
                String content = textTemplate.getContent();
                textMessage.setContent(content);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            } else if (MessageUtil.RESP_MESSAGE_TYPE_NEWS.equals(type)) {
//                List<NewsItem> newsList = this.newsItemService.findByProperty(NewsItem.class, "newsTemplate.id", subscribe.getTemplateId());
                List<NewsItem> newsList = this.newsItemService.findHql("from NewsItem a where a.newsTemplate.id=? order by orders asc", subscribe.getTemplateId());
                List<Article> articleList = new ArrayList<Article>();
                // NewsTemplate newsTemplate = newsTemplateService.getEntity(NewsTemplate.class, subscribe.getTemplateId());
                for (NewsItem news : newsList) {
                    Article article = new Article();
                    article.setTitle(news.getTitle());
                    article.setPicUrl(ResourceUtil.getMediaUrlPrefix() + "/" + news.getImagePath());
                    String url = "";
                    if (news.getUrlType().equals("default") || news.getUrlType().equals("默认页面")) {
                        url = ResourceUtil.getDomain() + "/newsItemController.do?newscontent&id=" + news.getId();
                    } else {
                        url = news.getUrl();
                    }
                    //todo:暂时将此处进行注释掉，以便以后更好滴进行改进
//                    if (news.getUrlType()!="outurl" || news.getUrlType()!="外部页面") {
                    article.setUrl(url + "&openId=" + fromUserName);
//                    }
                    article.setDescription(news.getDescription());
                    articleList.add(article);
                }
                NewsMessageResp newsResp = new NewsMessageResp();
                newsResp.setCreateTime(new Date().getTime());
                newsResp.setFromUserName(toUserName);
                newsResp.setToUserName(fromUserName);
                newsResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                newsResp.setArticleCount(newsList.size());
                newsResp.setArticles(articleList);
                respMessage = MessageUtil.newsMessageToXml(newsResp);
            } else if (MessageUtil.RESP_MESSAGE_TYPE_IMAGE.equals(type)) {
                WeixinSourceEntity weixinSourceEntity = weixinSourceService.getEntity(
                        WeixinSourceEntity.class, subscribe.getTemplateId());
                Image image = new Image();
                image.setMediaId(weixinSourceEntity.getMediaId());
                ImageMessageResp imageMessageResp = new ImageMessageResp();
                imageMessageResp.setImage(image);
                imageMessageResp.setCreateTime(new Date().getTime());
                imageMessageResp.setFromUserName(toUserName);
                imageMessageResp.setToUserName(fromUserName);
                imageMessageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
                respMessage = MessageUtil.imageMessageToXml(imageMessageResp);
            }

        }
        return respMessage;
    }

    /**
     * 点击自定义菜单事件
     *
     * @param requestMap
     * @param textMessage
     * @param bundler
     * @param respMessage
     * @param toUserName
     * @param fromUserName
     * @param respContent
     * @param sys_accountId
     * @param request
     * @return
     * @throws Exception
     */
    String doMyMenuEvent(Map<String, String> requestMap,
                         TextMessageResp textMessage, ResourceBundle bundler,
                         String respMessage, String toUserName, String fromUserName,
                         String respContent, String sys_accountId, HttpServletRequest request)
            throws Exception {
        String key = requestMap.get("EventKey");// 相当于menuEntity的主键
        // 自定义菜单CLICK类型
        // MenuEntity menuEntity =
        // this.systemService.findUniqueByProperty(MenuEntity.class,
        // "menuKey",key);
        MenuEntity menuEntity = this.systemService.getEntity(MenuEntity.class,
                key);
        if (menuEntity != null
                && oConvertUtils.isNotEmpty(menuEntity.getTemplateId())) {
            String type = menuEntity.getMsgType();
            if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(type)) {
                TextTemplate textTemplate = this.textTemplateService.getEntity(
                        TextTemplate.class, menuEntity.getTemplateId());
                String content = textTemplate.getContent();
                textMessage.setContent(content);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            } else if (MessageUtil.RESP_MESSAGE_TYPE_NEWS.equals(type)) {
                List<NewsItem> newsList = this.newsItemService.findHql("from NewsItem a where a.newsTemplate.id=? order by orders asc", menuEntity.getTemplateId());
                List<Article> articleList = new ArrayList<Article>();
                NewsTemplate newsTemplate = newsTemplateService.getEntity(
                        NewsTemplate.class, menuEntity.getTemplateId());
                for (NewsItem news : newsList) {
                    Article article = new Article();
                    article.setTitle(news.getTitle());
                    article.setPicUrl(ResourceUtil.getMediaUrlPrefix() + "/"
                            + news.getImagePath());
                    String url = "";
                    if (news.getUrlType().equals("default") || news.getUrlType().equals("默认页面")) {
                        url = ResourceUtil.getDomain()
                                + "/newsItemController.do?newscontent&id="
                                + news.getId();
                    } else {
                        url = news.getUrl();
                    }
                    //todo:暂时将此处进行注释掉，以便以后更好滴进行改进
//                    if (news.getUrlType()!="outurl" || news.getUrlType()!="外部页面") {
                    article.setUrl(url + "&openId=" + fromUserName);
//                    }
                    article.setDescription(news.getDescription());
                    articleList.add(article);
                }
                NewsMessageResp newsResp = new NewsMessageResp();
                newsResp.setCreateTime(new Date().getTime());
                newsResp.setFromUserName(toUserName);
                newsResp.setToUserName(fromUserName);
                newsResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                newsResp.setArticleCount(newsList.size());
                newsResp.setArticles(articleList);
                respMessage = MessageUtil.newsMessageToXml(newsResp);
            } else if ("expand".equals(type)) {
                WeixinExpandconfigEntity expandconfigEntity = weixinExpandconfigService
                        .getEntity(WeixinExpandconfigEntity.class,
                                menuEntity.getTemplateId());
                String className = expandconfigEntity.getClassname();
                KeyServiceI keyService = (KeyServiceI) Class.forName(className)
                        .newInstance();
                respMessage = keyService.excute("", textMessage, request);

            }
        }
        return respMessage;
    }

    /**
     * 欢迎语
     *
     * @return
     */
    public static String getMainMenu() {
        // 复杂字符串文本读取，采用文件方式存储
        String html = new FreemarkerHelper().parseTemplate(
                "/weixin/welcome.ftl", null);
        return html;
    }

    /**
     * 默认回复
     *
     * @param requestMap
     * @param textMessage
     * @param bundler
     * @param respMessage
     * @param toUserName
     * @param fromUserName
     * @param respContent
     * @param sys_accountId
     * @return
     */
    public String doDefaultEventResponse(Map<String, String> requestMap,
                                         TextMessageResp textMessage, ResourceBundle bundler,
                                         String respMessage, String toUserName, String fromUserName,
                                         String respContent, String sys_accountId) {
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        textMessage.setContent("");
        List<AutoResponseDefault> lst = subscribeService.findByProperty(
                AutoResponseDefault.class, "accountid", sys_accountId);
        if (lst.size() != 0) {
            AutoResponseDefault autoResponseDefault = lst.get(0);
            String type = autoResponseDefault.getMsgType();
            if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(type)) {
                TextTemplate textTemplate = this.textTemplateService.getEntity(TextTemplate.class, autoResponseDefault.getTemplateId());
                if (null == textTemplate) {
                    LOGGER.error("请重新配置默认回复:默认回复的模板Id:" + autoResponseDefault.getTemplateId() + ", sys_accountId=" + sys_accountId);
                    textMessage.setContent("");
                } else {
                    String content = textTemplate.getContent();
                    textMessage.setContent(content);
                }
                respMessage = MessageUtil.textMessageToXml(textMessage);
            } else if (MessageUtil.RESP_MESSAGE_TYPE_NEWS.equals(type)) {
                List<NewsItem> newsList = this.newsItemService.findHql("from NewsItem a where a.newsTemplate.id=? order by orders asc", autoResponseDefault.getTemplateId());
                List<Article> articleList = new ArrayList<Article>();
                NewsTemplate newsTemplate = newsTemplateService
                        .getEntity(NewsTemplate.class,
                                autoResponseDefault.getTemplateId());
                for (NewsItem news : newsList) {
                    Article article = new Article();
                    article.setTitle(news.getTitle());
                    article.setPicUrl(ResourceUtil.getMediaUrlPrefix() + "/"
                            + news.getImagePath());
                    String url = "";
                    if (news.getUrlType().equals("default") || news.getUrlType().equals("默认页面")) {
                        url = ResourceUtil.getDomain()
                                + "/newsItemController.do?newscontent&id="
                                + news.getId();
                    } else {
                        url = news.getUrl();
                    }
                    //todo:暂时将此处进行注释掉，以便以后更好滴进行改进
//                    if (news.getUrlType()!="outurl" || news.getUrlType()!="外部页面") {
                    article.setUrl(url + "&openId=" + fromUserName);
//                    }
                    article.setDescription(news.getDescription());
                    articleList.add(article);
                }
                NewsMessageResp newsResp = new NewsMessageResp();
                newsResp.setCreateTime(new Date().getTime());
                newsResp.setFromUserName(toUserName);
                newsResp.setToUserName(fromUserName);
                newsResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                newsResp.setArticleCount(newsList.size());
                newsResp.setArticles(articleList);
                respMessage = MessageUtil.newsMessageToXml(newsResp);
            }
        }
        return respMessage;
    }

}

package weixin.liuliangbao.flowcard.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.PageAuthCallback;
import weixin.guanjia.account.service.PageAuthHandler;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.account.util.CheckPic;
import weixin.guanjia.core.util.SignUtil;
import weixin.liuliangbao.flowcard.Entity.FlowCardBatchEntity;
import weixin.liuliangbao.flowcard.Entity.FlowCardInfoEntity;
import weixin.liuliangbao.flowcard.Service.FlowCardInfoServiceI;
import weixin.liuliangbao.jsonbean.MerchantInfoBean;
import weixin.liuliangbao.jsonbean.Update;
import weixin.liuliangbao.util.HttpUtil;
import weixin.member.entity.WeixinMemberEntity;
import weixin.oauth2.AdvancedUtil;
import weixin.oauth2.WeixinOauth2Token;
import weixin.tenant.entity.WeixinMerchantCoverAreaEntity;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;
import weixin.util.CommonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aa on 2015/12/19.
 */
@Scope("prototype")
@Controller
@RequestMapping("/flowCardController")
public class FlowCardController extends BaseController implements PageAuthHandler {
    private static final Logger LOGGER = Logger.getLogger(FlowCardController.class);
    @Autowired
    private WeixinAccountServiceI weixinAccountService;

    private String path = ResourceUtil.getConfigByName("jfinalUrl-config");
    @Autowired
    private SystemService systemService;
    @Autowired
    private FlowCardInfoServiceI flowCardInfoService;


    // 扫描流量卡二维码的时候的流程
    @RequestMapping(params = "startLoad")
    public ModelAndView startLoad(HttpServletRequest request, HttpServletResponse response) {
        Long start = System.currentTimeMillis();  //方法开始时间
        StringBuffer sb = new StringBuffer();
        sb.append("flowCardController_startLottery");
        String extractionCode = request.getParameter("extractionCode");
        String batchNo = request.getParameter("batchNo");
        String openId = StringUtils.defaultIfBlank(request.getParameter("openId"), "");
        String url = null;
        String accountid = null;
        try {
            String hql = " from FlowCardInfoEntity where extractionCode=? and batchNo=?";
            List<FlowCardInfoEntity> flowCardInfoEntityList = this.systemService.findHql(hql, extractionCode, batchNo);
            if (CollectionUtils.isEmpty(flowCardInfoEntityList)) {
                request.setAttribute("msg", "您所扫描的流量卡不存在！");
                return new ModelAndView("liuliangbao/flowmanager/failFlowCard");
            }

            Map<String, String> properties = new HashMap<String, String>();
            properties.put("extractionCode", extractionCode);  //提取码，传给后面用
            properties.put("batchNo", batchNo);  //批次号，传给后面用
            url = "";
            FlowCardInfoEntity flowCardInfoEntity = flowCardInfoEntityList.get(0);
            accountid = flowCardInfoEntity.getAcctId();
            properties.put("accountid", accountid);  //商户id，传给后面用
            WeixinAccountEntity weixinAccountEntity = this.weixinAccountService.get(WeixinAccountEntity.class, accountid);
            if (StringUtils.isBlank(openId) && !"1".equals(weixinAccountEntity.getAccounttype())) {
                // 如果不是已认证的服务号, 那么不能网页授权获取openId, 直接跳转到扫描二维码页面
                return redirectToSubscribePage(request, weixinAccountEntity);
            }
            properties.put("type", "flowcard");

            if (StringUtils.isBlank(openId)) {
                url = weixinAccountService.pageAuth(accountid, properties, this);   //调用授权封装:商户ID，
            } else {
                url = weixinAccountService.pageAuth(accountid, properties, this, openId);
            }
            sb.append("_url:" + url);
            long endTime = System.currentTimeMillis();//获取结束的当前时间
            LOGGER.info("绑定页的Load方法运行时间：----" + (endTime - start) + "ms");
            sb.append("bindingController_load_end_time:" + (endTime - start) + "ms");
            LOGGER.info(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:" + url);
    }

    @RequestMapping(params = "showAddress")
    public ModelAndView showAddress(HttpServletRequest request) {
        request.setAttribute("accountid", ResourceUtil.getWeiXinAccountId());
        return new ModelAndView("liuliangbao/flowcard/weixinFlowCardAddress");
    }

    /**
     * 进入二维码拍照页面
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "scanFlowCard")
    public ModelAndView scanFlowCard(HttpServletRequest request) {
        CommonUtils.printRequestParam("scanFlowCard", request);
        ModelMap model = new ModelMap();
        model.addAttribute("code", "200");
        if (null == request.getParameter("openId")) {
            // throw new BusinessException("请从公众号主页面中进入扫描二维码页面。");
            model.addAttribute("code", "400");
            model.addAttribute("message", "请从公众号主页面中进入扫描二维码页面。");
        }
        String accountId = request.getParameter("accountid");
        if (null == accountId) {
            // throw new BusinessException("未设置商家的信息");
            model.addAttribute("code", "400");
            model.addAttribute("message", "未设置商家的信息。");
        }

        if ("200".equals(model.get("code"))) {
            String openId = request.getParameter("openId");
            String ticket = weixinAccountService.getSignature(accountId);
            String url = request.getRequestURL().toString() + "?" + request.getQueryString();
            Map<String, String> map = SignUtil.sign(ticket, url);
            WeixinAccountEntity weixinAccountEntity = this.weixinAccountService.get(WeixinAccountEntity.class, accountId);
            map.put("appId", weixinAccountEntity.getAccountappid());
            model.addAttribute("map", map);
            model.addAttribute("accountId", accountId);
            model.addAttribute("openId", openId);
        }
        return new ModelAndView("liuliangbao/flowmanager/scanFlowCard", model);
    }

    private ModelAndView redirectToSubscribePage(HttpServletRequest request, WeixinAccountEntity account) {
        String accountId = account.getId();
        String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/QueryFlowRule";
        JSONObject myJson = new JSONObject().accumulate("id", account.getId()).accumulate("opreateType", "关注");

        Gson gson = new Gson();
        Type typeBinding = new TypeToken<MerchantInfoBean>() {}.getType();
        String reStrBinding = gson.toJson(HttpUtil.httpPost(url, myJson, false));
        MerchantInfoBean merchantInfoBean = gson.fromJson(reStrBinding, typeBinding);
        request.getSession().setAttribute("accountid", accountId);
        request.setAttribute("merchantInfoBean", merchantInfoBean);
        String qrcode = account.getQRcode();
        if(CheckPic.checkImg(qrcode)){
        	qrcode = ResourceUtil.getConfigByName("media.url.prefix") + "/" + qrcode;
        }
        String accountLogo = account.getLogoAccount();
        if(CheckPic.checkImg(accountLogo)){
        	accountLogo = ResourceUtil.getConfigByName("media.url.prefix") + "/" +accountLogo;
        }
        if(CheckPic.checkImg(reStrBinding)){}
        request.setAttribute("url", qrcode);
        request.setAttribute("urllogo", accountLogo);
        request.setAttribute("accountName", account.getAccountname());
        return new ModelAndView("weixin/member/NoattentionPublicNumCard");
    }


    @Override
    public ModelAndView followAndBind(PageAuthCallback msg, HttpServletRequest request) {
        /**
         * 1、查询是否关注
         * 2、查询商户归属地
         * 3、查询扫描的流量卡的信息
         * 4、如果不符合要求则进入相应的页面，如果符合则进行是否进行手机号的判断的页面，查询是否有手机号
         */
        Long start = System.currentTimeMillis();  //方法开始时间
        StringBuffer sb = new StringBuffer();
        sb.append("FlowCardController_followAndBind");

        String businessArea = "";
        String areaMerchant = "";
        String flUrl = "";
        try {
            String extractionCode = msg.getProperties().get("extractionCode");
            String batchNo = msg.getProperties().get("batchNo");
            String accountid = msg.getProperties().get("accountid");

            String phoneNumber = (String) request.getSession().getAttribute("phoneNumber");

            //获取openID，获取到的点击者的openid
            String openId = msg.getOpenId();
            request.setAttribute("openId", openId);

            if (StringUtils.isBlank(phoneNumber)) {
                String redirectURL = "bindingController.do?redirectBinding&merchantId={0}&openId={1}&operateType={2}&redirectURL={3}";
                String flowCardRedirectURL = request.getRequestURL() + "?" + request.getQueryString();
                flowCardRedirectURL = URLEncoder.encode(flowCardRedirectURL, "UTF-8");
                return new ModelAndView("redirect:" + MessageFormat.format(redirectURL, accountid, openId, "流量卡", flowCardRedirectURL));
            }

            // 根据商户id查询商户的信息
            WeixinAccountEntity weixinAccountEntity = this.systemService.get(WeixinAccountEntity.class, accountid);
            String accountname = weixinAccountEntity.getAccountname();  //商户名称
            request.setAttribute("accountName", accountname); //页面显示的商户的名称
            // 根据粉丝openId查询粉丝的信息，获取粉丝的基本信息
            String hqlfs = "from WeixinMemberEntity m where m.openId=? and m.accountId=?";
//            String hqlfs = "from WeixinMemberEntity m where m.openId='" + openId + "' and m.accountId='" + accountid + "'";
            List<WeixinMemberEntity> lismember = this.systemService.findHql(hqlfs, openId, accountid);
            String nickName = "";
            if (lismember.size() < 0) { //未关注页面
                request.setAttribute("msg", "当前用户尚未关注本商户！");
                return new ModelAndView("liuliangbao/flowmanager/failFlowCard");   //进入关注的二维码的页面
            } else {
                nickName = lismember.get(0).getNickName();
                request.setAttribute("openId", openId);
                request.setAttribute("accountid", accountid);
                request.setAttribute("nickname", lismember.get(0).getNickName());
                request.setAttribute("url", lismember.get(0).getHeadImgUrl());
            }
            /**
             * 获取到商户的所在的覆盖区域
             */
            WeixinMerchantCoverAreaEntity weixinMerchantCoverAreaEntity = null;
            request.setAttribute("accountName", weixinAccountEntity.getAccountname());
            // 查询商户的归属地
            weixinMerchantCoverAreaEntity = this.systemService.findUniqueByProperty(WeixinMerchantCoverAreaEntity.class, "accountID", weixinAccountEntity.getAcctId());
            if (weixinMerchantCoverAreaEntity == null) {
                weixinMerchantCoverAreaEntity = new WeixinMerchantCoverAreaEntity();
            }

            /**
             *  判断商户的覆盖区域中城市是否为空，为空的时候显示的时候只是显示到省，否则的话显示到市，此外还要增加到运营商的判断
             *  如果是所有的，那么直接显示三网通，否则的显示的是移动联通和电信
             */
            businessArea = weixinMerchantCoverAreaEntity.getBusinessArea();

            if (StringUtils.isBlank(businessArea) || "三网通".equals(businessArea)) {
                businessArea = "所有运营商";
            }
            if (StringUtils.isBlank(weixinMerchantCoverAreaEntity.getCityname())) {
                request.setAttribute("areaMerchant", weixinMerchantCoverAreaEntity.getProvincename() + businessArea);
                areaMerchant = weixinMerchantCoverAreaEntity.getProvincename() + businessArea;
            } else {
                request.setAttribute("areaMerchant", weixinMerchantCoverAreaEntity.getProvincename() + weixinMerchantCoverAreaEntity.getCityname() + businessArea);
                areaMerchant = weixinMerchantCoverAreaEntity.getProvincename() + weixinMerchantCoverAreaEntity.getCityname() + businessArea;
            }
            /**
             *如果不符合要求则进入相应的页面，如果符合则进行是否进行手机号的判断的页面，查询是否有手机号
             *
             * 查询的是流量卡的信息
             */
            String hql = "from FlowCardInfoEntity where extractionCode=? and batchNo=?";
            List<FlowCardInfoEntity> flowCardInfoEntityList = this.systemService.findHql(hql, extractionCode, batchNo);
            FlowCardInfoEntity flowCardInfoEntity = flowCardInfoEntityList.get(0);

            //根据批次号查询当前批次的信息
            FlowCardBatchEntity flowCardBatchEntity = this.systemService.findUniqueByProperty(FlowCardBatchEntity.class, "batchNo", batchNo);

            if (flowCardBatchEntity == null) {
                flowCardBatchEntity = new FlowCardBatchEntity();
            }

            Date beginDate = flowCardInfoEntity.getBeginDate();
            Date endDate = flowCardInfoEntity.getEndDate();
            Date today = new Date();

            /**
             * 判断的是流量卡的各种状态
             * 1、流量卡过期
             * 2、已经被使用
             * 3、尚未激活
             * 4、正常情况
             */
            if (flowCardInfoEntity.getBeginDate().getTime() > today.getTime()) {
                //todo:流量卡被使用，流量卡过期，流量卡状态被锁定
                request.setAttribute("msg", "您的流量卡尚不能使用！");
                return new ModelAndView("liuliangbao/flowmanager/failFlowCard");
            }

            if (flowCardInfoEntity.getIsValid().equals("否") ||
                    (flowCardInfoEntity.getEndDate().getTime() < today.getTime()) ||
                    (flowCardBatchEntity.getDisabledDate().getTime() < today.getTime())) {
                request.setAttribute("msg", "您的流量卡已经过期！");
                return new ModelAndView("liuliangbao/flowmanager/failFlowCard");
            }

            if (flowCardInfoEntity.getIsUse().equals("是")) {
                request.setAttribute("msg", "您的流量卡已经被使用！");
                return new ModelAndView("liuliangbao/flowmanager/failFlowCard");
            }

            if (flowCardInfoEntity.getStatusLock().equals("锁定")) {
                request.setAttribute("msg", "您的流量卡未激活！");
                return new ModelAndView("liuliangbao/flowmanager/failFlowCard");
            }

            String flowType = flowCardInfoEntity.getFlowType();//流量币类型
            Double flowValue = flowCardInfoEntity.getFlowValue();  //获取的流量值

            /**
             * 1、填充一条流量领取记录（判断是否存在手机号码，如果存在那么更新的时候需要同时更新的是领取记录和手机账户的流量账户变更，否则的话只是需要添加一条领取记录即可）
             * 2、填充手机号码后更新用户的流量账户
             * 3、流量卡到期后会自动回收（需要一个线程----需要回收的是没有被使用过的，是需要进行回收的）
             */
            String accountName = weixinAccountEntity.getAccountname();//获取公众号的名称
            request.setAttribute("accountName", accountName);
            request.setAttribute("flowType", flowType);
            request.setAttribute("flowValue", flowValue);//保存流量卡的流量币值
            request.setAttribute("accountid", accountid);
            request.setAttribute("merchantId", accountid);
            request.setAttribute("batchNo", batchNo);
            request.setAttribute("extractionCode", extractionCode);
            request.setAttribute("operateType", "扫码流量卡");
            request.setAttribute("nickName", nickName);

            // logo的地址
            String urlprefix = ResourceUtil.getConfigByName("media.url.prefix");
            String impUrl = weixinAccountEntity.getLogoAccount();
            String urll = urlprefix + "/" + impUrl;
            request.setAttribute("url", urll);

            /** 更新流量账户 */
            String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/useFlowCard";
            Gson gson = new Gson();
            JSONObject myJson = new JSONObject();
            myJson.element("phoneNumber", phoneNumber);
            myJson.element("flowValue", flowValue);
            myJson.element("accountid", accountid);
            myJson.element("opreateType", "流量卡");
            myJson.element("flowType", flowType);
            myJson.element("nickName", nickName);
            myJson.element("openid", openId);
            myJson.element("flowCardId", flowCardInfoEntity.getId());
            System.out.println(myJson.toString());
            JSONObject contentFlow = HttpUtil.httpPost(url, myJson, false);
            String strFlow = gson.toJson(contentFlow);
            Type type = new TypeToken<Update>() {
            }.getType();
            Update update = gson.fromJson(strFlow, type);
            /** 验证是否保存成功 */
            if (!update.getCode().equals("200")) {
                LOGGER.info("保存未成功");
                return new ModelAndView("common/404");
            }

            String urlPattern = "flowCardController.do?showCardInfo&accountid={0}&flowValue={1}&phoneNumber={2}&businessArea={3}&openId={4}&batchNo={5}&extractionCode={6}";
            flUrl = MessageFormat.format(urlPattern, accountid, flowValue, phoneNumber, URLEncoder.encode(businessArea, "UTF-8"), openId, batchNo, extractionCode);
            sb.append("_hdUrl:" + flUrl);
            request.setAttribute("operateType", "流量卡");   //通过什么方式进行的绑定
            return new ModelAndView("redirect:" + flUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("common/404");
        }
    }

    @Override
    public ModelAndView follow(PageAuthCallback msg, HttpServletRequest request) throws Exception {
        return this.followAndBind(msg, request);
    }

    /**
     * 流量卡页面的跳转和处理
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "showCardInfo")
    public ModelAndView showCardInfo(HttpServletRequest request) {
        String accountid = request.getParameter("accountid");
        // String flowValue=request.getParameter("flowValue");
        String phoneNumber = request.getParameter("phoneNumber");
        String businessArea = request.getParameter("businessArea");
        String extractionCode = request.getParameter("extractionCode");
        String batchNo = request.getParameter("batchNo");

        String openId = request.getParameter("openId");

        request.setAttribute("businessArea", businessArea);
        // request.setAttribute("flowValue",flowValue);
        request.setAttribute("accountid", accountid);
        request.setAttribute("openId", openId);
        request.setAttribute("operateType", "流量卡");
        WeixinAccountEntity weixinAccountEntity = this.systemService.getEntity(WeixinAccountEntity.class, accountid);
        if (weixinAccountEntity == null) {
            weixinAccountEntity = new WeixinAccountEntity();
        }
        request.setAttribute("accountName", weixinAccountEntity.getAccountname());

        /**
         * logo的地址
         */
        String urlprefix = ResourceUtil.getConfigByName("media.url.prefix");
        String impUrl = weixinAccountEntity.getLogoAccount();
        String urll = urlprefix + "/" + impUrl;
        request.setAttribute("url", urll);
        /**
         * 查询粉丝的信息
         */
        /**
         * 根据粉丝openId查询粉丝的信息，获取粉丝的基本信息
         */
        String hqlfs = "from WeixinMemberEntity m where m.openId=? and m.accountId=?";//+openId+"' and m.accountId='"+accountid+"'";
        List<WeixinMemberEntity> lismember = this.systemService.findHql(hqlfs, openId, accountid);
        String nickName = "";
        if (lismember.size() < 0) { //未关注页面
            request.setAttribute("msg", "当前用户尚未关注本商户！");
            return new ModelAndView("liuliangbao/flowmanager/failFlowCard");   //进入关注的二维码的页面
        } else {
            nickName = lismember.get(0).getNickName();
            request.setAttribute("openId", openId);
            request.setAttribute("accountid", accountid);
            request.setAttribute("nickname", lismember.get(0).getNickName());
            request.setAttribute("head", lismember.get(0).getHeadImgUrl());
        }
        /**
         * 流量规则的设置
         */
        Gson gson = new Gson();
        String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/QueryFlowRule";
        JSONObject myJson = new JSONObject();
        myJson.accumulate("id", accountid);
        myJson.accumulate("opreateType", StringUtils.defaultString(request.getParameter("operateType"), "关注"));
        JSONObject contentBinding = HttpUtil.httpPost(url, myJson, false);
        String reStrBinding = gson.toJson(contentBinding);
        Type typeBinding = new TypeToken<MerchantInfoBean>() {
        }.getType();
        MerchantInfoBean merchantInfoBean = gson.fromJson(reStrBinding, typeBinding);
        request.setAttribute("merchantInfoBean", merchantInfoBean);

        /**
         *如果不符合要求则进入相应的页面，如果符合则进行是否进行手机号的判断的页面，查询是否有手机号
         *
         * 查询的是流量卡的信息
         */
        String hql = "from FlowCardInfoEntity where extractionCode=?  and batchNo=?";// + extractionCode + "' and batchNo='" + batchNo + "'";
        List<FlowCardInfoEntity> flowCardInfoEntityList = this.systemService.findHql(hql, extractionCode, batchNo);
        FlowCardInfoEntity flowCardInfoEntity = flowCardInfoEntityList.get(0);

        request.setAttribute("flowValue", flowCardInfoEntity.getFlowValue());
        String flUrl = "";
        if (StringUtils.isBlank(phoneNumber)) {
            flUrl = "liuliangbao/flowmanager/binding";
        } else { //如果存在手机号的话，直接将流量充值在账户上即可
            flUrl = "liuliangbao/flowmanager/flowCardSuccess";
        }
        return new ModelAndView(flUrl);
    }

    /**
     * 新宇的------没有使用--方法名称goLoad（）
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "goLoad")
    public ModelAndView goLoad(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();//获取开始当前的时间

        String accountid = request.getParameter("state");
        if (accountid == null || "".equals(accountid)) {
            return new ModelAndView("liuliangbao/flowmanager/noMain");
        }
        String hdNotUrl = "liuliangbao/flowmanager/noMain";
        String code = request.getParameter("code");
        if (!"authdeny".equals(code)) {
//            FlowMainEntity flowEntity = this.systemService.get(FlowMainEntity.class, hdid);
//            String accountid = flowEntity.getAccountid();
            WeixinAccountEntity account = this.systemService.get(WeixinAccountEntity.class, accountid);
            // 获取网页授权access_token
            WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(account.getAccountappid(),
                    account.getAccountappsecret(), code);
            // 网页授权接口访问凭证
            String accessToken = weixinOauth2Token.getAccessToken();
            // 用户标识
            String openId = weixinOauth2Token.getOpenId();
            //领取流量卡人的信息
            WeixinMemberEntity memberEntity = this.systemService.findUniqueByProperty(WeixinMemberEntity.class, "openId", openId);

            if (memberEntity == null || memberEntity.getSubscribe().equals("0")) {
                //没有关注，跳到需要点击按钮弹出二维码的页面

                request.setAttribute("accountName", account.getAccountname());
                Gson gson = new Gson();
                String urll = path + "userGetFlow/QueryFlowRule";
                JSONObject myJson = new JSONObject();
                myJson.accumulate("id", accountid);
                myJson.accumulate("opreateType", "关注");
                JSONObject contentBinding = HttpUtil.httpPost(urll, myJson, false);
                String reStrBinding = gson.toJson(contentBinding);
                Type typeBinding = new TypeToken<MerchantInfoBean>() {
                }.getType();
                MerchantInfoBean merchantInfoBean = gson.fromJson(reStrBinding, typeBinding);
                //LOGGER.info(gson.toJson(contentBinding));
                // LOGGER.info("------------------------"+content.getData().get(0).getId()+"----------------------------");
                request.getSession().setAttribute("flowValue", merchantInfoBean.getData().get(0).getCountryFlowValue());

                //二维码的地址
                String urlprefix = ResourceUtil.getConfigByName("media.url.prefix");
                String impUrl = account.getQRcode();
                if(CheckPic.checkImg(impUrl)){
                	impUrl = urlprefix + "/" + impUrl;
                }
                request.setAttribute("url", impUrl);
                if(!account.getQRcode().equals(null)){
                    String logoAccount = account.getLogoAccount();
                    if(CheckPic.checkImg(logoAccount)){
                    	logoAccount = urlprefix + "/" + logoAccount;
                    }
                    request.setAttribute("logo", logoAccount);
                } else {
                    request.setAttribute("logo", "plug-in/liuliangbao/css/0422/images/logo-1.png");
                }
                long endTime = System.currentTimeMillis();//获取结束的当前时间
                LOGGER.info("流量卡页的goLoad：----" + (endTime - startTime) + "ms");
//                return new ModelAndView("weixin/member/shareFlow/noSubQRCode");
                return new ModelAndView("weixin/member/NoattentionPublicNum");

                // return new ModelAndView("/liuliangbao/flowmanager/flowCard");
            } else {
                //关注了
                if (memberEntity.getPhoneNumber().equals("")) {
                    request.getSession().setAttribute("nickname", memberEntity.getNickName());
                    //没有绑定，跳到点击按钮弹出绑定的页面

                    Gson gson = new Gson();
                    String urll = path + "userGetFlow/QueryFlowRule";
                    JSONObject myJson = new JSONObject();
                    myJson.accumulate("id", accountid);
                    myJson.accumulate("opreateType", "关注");
                    JSONObject contentBinding = HttpUtil.httpPost(urll, myJson, false);
                    String reStrBinding = gson.toJson(contentBinding);
                    Type typeBinding = new TypeToken<MerchantInfoBean>() {
                    }.getType();
                    MerchantInfoBean merchantInfoBean = gson.fromJson(reStrBinding, typeBinding);
                    //LOGGER.info(gson.toJson(contentBinding));
                    // LOGGER.info("------------------------"+content.getData().get(0).getId()+"----------------------------");
                    request.getSession().setAttribute("merchantInfoBean", merchantInfoBean);

                    long endTime = System.currentTimeMillis();//获取结束的当前时间
                    LOGGER.info("流量卡页的goLoad：----" + (endTime - startTime) + "ms");
                    return new ModelAndView("liuliangbao/flowmanager/binding");
                    //return new ModelAndView("/liuliangbao/flowmanager/flowCard1");
                } else {
                    String phoneNumber = memberEntity.getPhoneNumber();//获取手机号
                    request.getSession().setAttribute("phoneNumber", phoneNumber);

                    //绑定了，跳到只有一个按钮的页面，允许领取流量
                    long endTime = System.currentTimeMillis();//获取结束的当前时间
                    LOGGER.info("流量卡页的goLoad：----" + (endTime - startTime) + "ms");
                    return new ModelAndView("/liuliangbao/flowmanager/flowCard");
                }
            }

        } else {
            long endTime = System.currentTimeMillis();//获取结束的当前时间
            LOGGER.info("流量卡页的goLoad：----" + (endTime - startTime) + "ms");
            return new ModelAndView(hdNotUrl);
        }
    }

    /**
     * 新宇的------没有使用--方法名称goGetFlow（）
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "goGetFlow")
    public void goGetFlow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();

        String extractionCode = request.getSession().getAttribute("extractionCode").toString();
        String batchNo = request.getSession().getAttribute("batchNo").toString();
        String phoneNumber = request.getParameter("phoneNumber");

//        FlowCardInfoEntity flowCardInfoEntity=this.systemService.findUniqueByProperty(FlowCardInfoEntity.class,"extractionCode",extractionCode);

        String hql = "from FlowCardInfoEntity where extractionCode='" + extractionCode + "' and batchNo='" + batchNo + "'";
        List<FlowCardInfoEntity> flowCardInfoEntityList = this.systemService.findHql(hql, null);
        FlowCardInfoEntity flowCardInfoEntity = flowCardInfoEntityList.get(0);
        String accountid = flowCardInfoEntity.getBelongAcctId().toString();

        String flowType = flowCardInfoEntity.getFlowType();
        double flowValue = flowCardInfoEntity.getFlowValue();
        Gson gson = new Gson();
        String urll = path + "flowCard/UpdateAccount";
        JSONObject myJson = new JSONObject();
        myJson.accumulate("id", accountid);
        myJson.accumulate("phoneNumber", phoneNumber);
        myJson.accumulate("flowType", flowType);
        myJson.accumulate("flowValue", flowValue);
        JSONObject contentUpdate = HttpUtil.httpPost(urll, myJson, false);
        String reStrUpdate = gson.toJson(contentUpdate);
        Type typeUpdate = new TypeToken<Update>() {
        }.getType();
        Update update = gson.fromJson(reStrUpdate, typeUpdate);
        if (update.getCode().equals("200")) {
            flowCardInfoEntity.setIsUse("是");//设置为已经使用
            flowCardInfoEntity.setPhoneNumber(phoneNumber);//将使用者的手机号添加、
            this.systemService.saveOrUpdate(flowCardInfoEntity);

            map.put("flag", true);
            map.put("msg", "成功获取" + flowValue + "M流量！");
        } else {
            if (update.getCode().equals("201")) {
                map.put("flag", true);
                map.put("msg", "手机用户没有在商户的覆盖区域内,不能领取，请谅解！");
            } else {
                map.put("flag", false);
                map.put("msg", "流量币领取失败");
            }

        }
        String json = objectMapper.writeValueAsString(map);
        PrintWriter out = response.getWriter();
        out.write(json);
    }

    /**
     * 新宇的------没有使用--方法名称success（）
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "success")
    public ModelAndView success(HttpServletRequest request) {
        String accountName = request.getParameter("accountName");
        String flowValue = request.getParameter("flowValue");
        request.setAttribute("accountName", accountName);
        request.setAttribute("flowValue", flowValue);
        return new ModelAndView("liuliangbao/flowmanager/successFlowCard");
    }


    /**
     * 新宇的------没有使用--方法名称load（）
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "load")
    public ModelAndView load(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();//获取开始当前的时间

        //todo:这里需要先根据给的提取码来获取公众号id
        String extractionCode = request.getParameter("extractionCode");
        String batchNo = request.getParameter("batchNo");
        String hql = "from FlowCardInfoEntity where extractionCode='" + extractionCode + "' and batchNo='" + batchNo + "'";
        List<FlowCardInfoEntity> flowCardInfoEntityList = this.systemService.findHql(hql, null);
//        FlowCardInfoEntity flowCardInfoEntity=this.systemService.findByQueryString("from ");
        if (flowCardInfoEntityList.size() < 0) {
            //todo:流量卡信息不存在
            request.getSession().setAttribute("msg", "您所扫描的流量卡不存在！");
            return new ModelAndView("liuliangbao/flowmanager/failFlowCard");
        } else {
            FlowCardInfoEntity flowCardInfoEntity = flowCardInfoEntityList.get(0);
            Date beginDate = flowCardInfoEntity.getBeginDate();
            Date endDate = flowCardInfoEntity.getEndDate();
            Date today = new Date();
            if (beginDate.getTime() > today.getTime()) {

                //todo:流量卡被使用，流量卡过期，流量卡状态被锁定
                request.getSession().setAttribute("msg", "您的流量卡尚不能使用！");
                return new ModelAndView("liuliangbao/flowmanager/failFlowCard");
            } else {
//                if(today.getTime()-86400000>endDate.getTime()){
                if (flowCardInfoEntity.getIsValid().equals("否")) {
                    request.getSession().setAttribute("msg", "您的流量卡已经过期！");
                    return new ModelAndView("liuliangbao/flowmanager/failFlowCard");
                } else {
                    if (flowCardInfoEntity.getIsUse().equals("是")) {
                        request.getSession().setAttribute("msg", "您的流量卡已经被使用！");
                        return new ModelAndView("liuliangbao/flowmanager/failFlowCard");
                    } else {
                        if (flowCardInfoEntity.getStatusLock().equals("否")) {
                            request.getSession().setAttribute("msg", "您的流量卡未激活！");
                            return new ModelAndView("liuliangbao/flowmanager/failFlowCard");
                        } else {
                            String accountid = flowCardInfoEntity.getBelongAcctId();//获取流量卡所属公众号的id
                            String flowType = flowCardInfoEntity.getFlowType();//流量币类型
                            WeixinAccountEntity account = this.systemService.get(WeixinAccountEntity.class, accountid);
                            String accountName = account.getAccountname();//获取公众号的名称
                            request.getSession().setAttribute("accountName", accountName);
                            request.getSession().setAttribute("flowType", flowType);
                            request.getSession().setAttribute("flowValue", flowCardInfoEntity.getFlowValue());//保存流量卡的流量币值
                            request.getSession().setAttribute("accouontid", accountid);
                            request.getSession().setAttribute("batchNo", batchNo);
                            request.getSession().setAttribute("extractionCode", extractionCode);
                            return new ModelAndView("/liuliangbao/flowmanager/flowCard");
                        }
                    }
                }


//                String rdUrl = "flowCardController.do?goLoad";
//                String accountid = flowCardInfoEntity.getBelongAcctId();//获取流量卡所属公众号的id
//                String flowType=flowCardInfoEntity.getFlowType();
//                WeixinAccountEntity account = this.systemService.get(WeixinAccountEntity.class, accountid);
//                String requestUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
//                requestUrl = requestUrl.replace("APPID", account.getAccountappid());
//                requestUrl = requestUrl.replace("SCOPE", "snsapi_base");//userinfo
//                requestUrl = requestUrl.replace("STATE", accountid);
//                String path = request.getContextPath();
//                String localhosturl = request.getScheme() + "://" + request.getServerName() + path + "/";
//                String url = "";
//                try {
//                    url = URLEncoder.encode(localhosturl + rdUrl, "utf-8");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                requestUrl = requestUrl.replace("REDIRECT_URI", url);
//                //LOGGER.info(requestUrl);
//                request.getSession().setAttribute("flowValue",flowCardInfoEntity.getFlowValue());//保存流量卡的流量币值
//                request.getSession().setAttribute("extractionCode",extractionCode);//保存提取码
//                request.getSession().setAttribute("flowType",flowType);//保存流量币的类型
//                long endTime=System.currentTimeMillis();//获取结束的当前时间
//                LOGGER.info("流量卡页的load方法运行时间：----"+(endTime-startTime)+"ms");
//                return new ModelAndView("redirect:" + requestUrl);
            }
        }
    }

}

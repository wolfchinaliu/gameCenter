package weixin.liuliangbao.controller.flowcontroller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.jeecgframework.core.aop.DataSourceSwitcher;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sdk.jfinal.JFinalUtils;
import weixin.advertisement.service.AdvertisementServiceI;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.PageAuthCallback;
import weixin.guanjia.account.service.PageAuthHandler;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.liuliangbao.jsonbean.MerchantInfoBean;
import weixin.liuliangbao.jsonbean.Update;
import weixin.liuliangbao.util.HttpUtil;
import weixin.member.entity.WeixinMemberEntity;
import weixin.tenant.entity.WeixinMerchantCoverAreaEntity;
import weixin.util.DataDictionaryUtil.AdPublishPosition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aa on 2015/12/4.
 */
@Scope("prototype")
@Controller
@RequestMapping("/signController")
public class SignController extends BaseController implements PageAuthHandler {

    private static final Logger LOGGER = Logger.getLogger(SignController.class);
    private String path = ResourceUtil.getConfigByName("jfinalUrl-config");
    @Autowired
    private WeixinAccountServiceI weixinAccountService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private AdvertisementServiceI adService;

    @RequestMapping(params = "startLoad")
    public ModelAndView startLoad(HttpServletRequest request, HttpServletResponse response) {
        long startTime = System.currentTimeMillis();//获取开始当前的时间
        StringBuffer buffer = new StringBuffer();
        try {
            buffer.append("signController_startLoad_begin:_");
            String accountid = request.getParameter("accountid");
            String openId = request.getParameter("openId");
            buffer.append("accountid_" + accountid + "_openId_" + openId);
            LOGGER.info("签到页的startLoad方法开始" + accountid + "__openid__" + openId);
            Map<String, String> properties = new HashMap<String, String>();
            properties.put("accountid", accountid);  //活动ID，传给后面用
            String url = "";
            if (StringUtils.isBlank(openId)) {
                url = weixinAccountService.pageAuth2(accountid, properties, this);   //调用授权封装：商户ID，
            } else {
                url = weixinAccountService.pageAuth2(accountid, properties, this, openId);
            }
//        String url=weixinAccountService.pageAuth(accountid,properties,this);   //调用授权封装：商户ID，
            buffer.append("_url_" + url);
            long endTime = System.currentTimeMillis();//获取结束的当前时间
            buffer.append("signController_startLoad_end_time:" + (endTime - startTime) + "ms");
            LOGGER.info(buffer.toString());
            LOGGER.info("签到页的startLoad方法运行时间：----" + (endTime - startTime) + "ms");
            return new ModelAndView("redirect:" + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();//获取结束的当前时间
        buffer.append("signController_startLoad_end_time:" + (endTime - startTime) + "ms");
        LOGGER.info(buffer.toString());
        return new ModelAndView("/common/404");
    }


    @RequestMapping(params = "load")
    public ModelAndView load(HttpServletRequest request) throws IOException {
        String accountid = request.getParameter("accountid");
        // String phoneNumber=request.getParameter("phoneNumber");
        String phoneNumber = request.getSession().getAttribute("phoneNumber").toString();
        Gson gson = new Gson();
        JSONObject myJsonAccount = new JSONObject();
        myJsonAccount.accumulate("phoneNumber", phoneNumber);
        MerchantInfoBean userFlowAccoun = JFinalUtils.getUserFlowAccount(null, phoneNumber);
        request.getSession().setAttribute("provinceFlow", userFlowAccoun.getData().get(0).getProvinceFlowValue());
        request.getSession().setAttribute("countryFlow", userFlowAccoun.getData().get(0).getCountryFlowValue());

        MerchantInfoBean merchantInfoBean = JFinalUtils.getMerchantFlowRule(accountid, "签到");
        request.getSession().setAttribute("merchantInfoBean", merchantInfoBean);
        request.getSession().setAttribute("title", "签到送流量");
        return new ModelAndView("liuliangbao/flowmanager/sign");
    }

    @RequestMapping(params = "sign")
    public void sign(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        StringBuffer buffer = new StringBuffer();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            buffer.append("signController_sign_begin:_");
            String merchantId = request.getParameter("accountid");
            String phoneNumber = request.getParameter("phoneNumber");
            String openId = request.getParameter("openId");

            buffer.append("_accountid_" + merchantId + "_phoneNumber_" + phoneNumber);

            long startSign = System.currentTimeMillis();
            Update update = JFinalUtils.sign(merchantId, phoneNumber, openId);
            long endSign = System.currentTimeMillis();

            buffer.append("_Jfinal_sign_time:" + (endSign - startSign) + "ms");
            map.put("flag", "1".equals(update.getCode()));
            map.put("msg", update.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            long endTime = System.currentTimeMillis();
            buffer.append("signController_sign_end_time:" + (endTime - startTime) + "ms");
            LOGGER.info(buffer.toString());
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(map);
            PrintWriter out = response.getWriter();
            out.write(json);
        }
    }

    @Override
    public ModelAndView followAndBind(PageAuthCallback msg, HttpServletRequest request) {
        return followAndBind(request, false);
    }

    @Override
    public ModelAndView follow(PageAuthCallback msg, HttpServletRequest request) throws Exception {
        return followAndBind(request, true);
    }

    /**
     * 跳转和绑定逻辑
     * 根据request中的phoneNumber和openId查询手机流量账户信息
     *
     * @param request                 http请求对象
     * @param allowNotBindPhoneNumber 是否允许没有绑定手机号码
     * @return
     */
    private ModelAndView followAndBind(HttpServletRequest request, boolean allowNotBindPhoneNumber) {
        long startTime = System.currentTimeMillis();
        StringBuffer buffer = new StringBuffer();
        try {
            String openId = request.getSession().getAttribute("openId").toString();
            String accountid = request.getSession().getAttribute("accountid").toString();

            // 新增商户运营商流量类型的展示
            WeixinAccountEntity weixinAccountEntity = this.systemService.getEntity(WeixinAccountEntity.class, accountid);

            String acctId = weixinAccountEntity.getAcctId(); //获取的是商户的id
            WeixinMerchantCoverAreaEntity weixinMerchantCoverAreaEntity = this.systemService.findUniqueByProperty(WeixinMerchantCoverAreaEntity.class, "accountID", acctId);

            if (null == weixinMerchantCoverAreaEntity) {
                weixinMerchantCoverAreaEntity = new WeixinMerchantCoverAreaEntity();  //防止为空时出现异常
            } else {
                String businessArea = weixinMerchantCoverAreaEntity.getBusinessArea();
                if (StringUtils.isBlank(businessArea) || "三网通".equals(businessArea)) {
                    businessArea = "所有运营商";
                } else {
                    businessArea = weixinMerchantCoverAreaEntity.getBusinessArea();
                }
                request.setAttribute("businessArea", businessArea);
            }
            WeixinMemberEntity weixinMemberEntity = null;
            List<WeixinMemberEntity> weixinMemberEntities;
            String hql = "from WeixinMemberEntity t where t.openId=? and t.accountId=?";
            weixinMemberEntities = this.systemService.findHql(hql, openId, accountid);

            if (weixinMemberEntities.size() > 0) {
                weixinMemberEntity = weixinMemberEntities.get(0);
            }
            String phoneNumber = weixinMemberEntity.getPhoneNumber();
            request.setAttribute("nickName", weixinMemberEntity.getNickName()); //昵称
            request.setAttribute("headImgUrl", weixinMemberEntity.getHeadImgUrl());//头像地址
            request.setAttribute("province", weixinMemberEntity.getProvince());
            request.setAttribute("city", weixinMemberEntity.getCity());
            request.setAttribute("phoneNumber", phoneNumber);

            buffer.append("_IsBingding_go_sign_begin:_");
            buffer.append("accountid_" + accountid + "_phoneNumber_" + phoneNumber);
            Gson gson = new Gson();
            long startFlowAccount = System.currentTimeMillis();
            MerchantInfoBean userFlowAccoun = JFinalUtils.getUserFlowAccount(openId, phoneNumber);
            long endFLowAccount = System.currentTimeMillis();
            buffer.append("_queryFlowAccount_time:_" + (endFLowAccount - startFlowAccount) + "ms");
            if (userFlowAccoun != null && userFlowAccoun.getCode().equals("200")) {
                request.getSession().setAttribute("provinceFlow", userFlowAccoun.getData().get(0).getProvinceFlowValue());
                request.getSession().setAttribute("countryFlow", userFlowAccoun.getData().get(0).getCountryFlowValue());
            } else {
                request.getSession().setAttribute("provinceFlow", 0);
                request.getSession().setAttribute("countryFlow", 0);
            }
            long startFlowRule = System.currentTimeMillis();
            MerchantInfoBean merchantInfoBean = JFinalUtils.getMerchantFlowRule(accountid, "签到");
            long endFlowRule = System.currentTimeMillis();
            buffer.append("_queryFLowRule_time:_" + (endFlowRule - startFlowRule) + "ms");
            request.getSession().setAttribute("merchantInfoBean", merchantInfoBean);
            request.getSession().setAttribute("title", "签到送流量");
            Map ad = this.adService.getPublishedAd(acctId, AdPublishPosition.sign.getCode());
            request.getSession().setAttribute("ad", ad);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            long endTime = System.currentTimeMillis();
            buffer.append("_IsBingding_go_sign_end_Time:" + (endTime - startTime) + "ms");
            LOGGER.info(buffer.toString());
            return new ModelAndView("liuliangbao/flowmanager/sign");
        }
    }

}

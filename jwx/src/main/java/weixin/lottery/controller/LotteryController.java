package weixin.lottery.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.annotation.DataSourceSwitch;
import org.jeecgframework.core.aop.DataSourceSwitcher;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONObject;
import sdk.jfinal.JFinalUtils;
import weixin.advertisement.service.AdvertisementServiceI;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.PageAuthCallback;
import weixin.guanjia.account.service.PageAuthHandler;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.SignUtil;
import weixin.liuliangbao.jsonbean.MerchantInfoBean;
import weixin.liuliangbao.jsonbean.Update;
import weixin.liuliangbao.util.HttpUtil;
import weixin.lottery.entity.WeixinLotteryEntity;
import weixin.lottery.entity.WeixinWinningrecordlxcEntity;
import weixin.lottery.service.WeixinLotteryServiceI;
import weixin.lottery.service.WeixinWinningrecordServiceI;
import weixin.lottery.service.WeixinWinningrecordlxcServiceI;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.tenant.entity.WeixinMerchantCoverAreaEntity;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.tenant.service.WeixinMerchantCoverAreaServiceI;
import weixin.util.AvoidRepeatOperationFilter;
import weixin.util.DataDictionaryUtil.AdPublishPosition;
import weixin.util.WeiXinConstants;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: 微信活动
 * @date 2015-02-05 14:26:01
 */
@Scope("prototype")
@Controller
@RequestMapping("/lotteryController")
public class LotteryController extends BaseController implements PageAuthHandler {
    /**
     * Logger for this class
     */
	public static final String SHILIU_ACCOUNT_ID = ResourceUtil.getShiliuAccountId();
    private static final Logger LOGGER = Logger.getLogger(LotteryController.class);
    private String path = ResourceUtil.getConfigByName("jfinalUrl-config");
    @Autowired
    private WeixinLotteryServiceI weixinLotteryService;

    @Autowired
    private SystemService systemService;
    @Autowired
    private WeixinWinningrecordServiceI winningrecordService;
    @Autowired
    private WeixinWinningrecordlxcServiceI winningrecordlxcService;
    @Autowired
    private WeixinMemberServiceI weixinMemberService;
    @Autowired
    private WeixinAcctFlowAccoutServiceI weixinAcctFlowAccountService;
    @Autowired
    private WeixinAccountServiceI weixinAccountService;
    @Autowired
    private WeixinMerchantCoverAreaServiceI weixinMerchantCoverAreaService;
    @Autowired
    private AdvertisementServiceI adService;
    
    //	private final Logger LOGGER=Logger.getLogger("");
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SuppressWarnings("unused")
    private String findAccountId(HttpServletRequest request) {
        // 非法请求，直接返回
        if (request == null) {
            return "";
        }
        // request请求中拿到accountid,直接返返回，如果拿不到。则从上下文中获取
        String accountid = request.getParameter("accountid");
        if (accountid != null && !"".equals(accountid)) {
            return accountid;
        } else {
            return ResourceUtil.getWeiXinAccountId();
        }
    }


    /********************************************刘晓春-2015年12月11日-begin**********************************************/

    /**
     * 引导授权界面
     *
     * @param request
     */
    @RequestMapping(params = "startLottery")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public ModelAndView startLottery(HttpServletRequest request, HttpServletResponse response) {
        Long start = System.currentTimeMillis();  //方法开始时间
        StringBuffer sb = new StringBuffer();
        sb.append("LotteryController.startLottery():");
        String hdid = request.getParameter("hdid");
        String openId = request.getParameter("openId");

        String url = null;
        try {
            WeixinLotteryEntity hdEntity = this.systemService.get(WeixinLotteryEntity.class, hdid);
            //活动不存在时的处理页面
            if (hdEntity == null) {
                return new ModelAndView("common/404");
            }
            sb.append("用户[" + openId + "]参与了活动[{" + hdid + ":" + hdEntity.getTitle() + "}]");
            String accountid = hdEntity.getAccountid();
            Map<String, String> properties = new HashMap<String, String>();
            properties.put("hdid", hdid);  //活动ID，传给后面用
            if (StringUtils.isBlank(openId)) {
                url = weixinAccountService.pageAuth2(accountid, properties, this);   //调用授权封装:商户ID，
            } else {
                url = weixinAccountService.pageAuth2(accountid, properties, this, openId);
            }
            sb.append(", 重定向地址:" + url);
            return new ModelAndView("redirect:" + url);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("common/404");
        } finally {
            Long end = System.currentTimeMillis();
            sb.append(", 方法耗时:" + (end - start) + "ms");
            LOGGER.info(sb.toString());
        }

    }

    @Override
    public ModelAndView followAndBind(PageAuthCallback msg, HttpServletRequest request) throws Exception {
        return this.followAndBind(msg, request, false);
    }

    @Override
    public ModelAndView follow(PageAuthCallback msg, HttpServletRequest request) throws Exception {
        return this.followAndBind(msg, request, true);
    }

    public ModelAndView followAndBind(PageAuthCallback msg, HttpServletRequest request, boolean allowNotBindPhoneNumber) {
        Long start = System.currentTimeMillis();  //方法开始时间
        StringBuffer sb = new StringBuffer();
        sb.append("LotteryController.followAndBind()");
        try {
            //判断活动id是否存在
            String hdid = msg.getProperties().get("hdid");
            if (hdid == null || "".equals(hdid)) {
                sb.append("活动ID为空，进入404页面");
                return new ModelAndView("common/404");
            }
            //获取openID,获取的是点击者的openId
            String openId = msg.getOpenId();
            
            //根据本次活动id查询活动规则
            WeixinLotteryEntity hdEntity = this.systemService.get(WeixinLotteryEntity.class, hdid);
            request.setAttribute("hdEntity", hdEntity);
            request.getSession().setAttribute("hdId", hdEntity.getId());
            request.setAttribute("openId", openId);
            sb.append("用户[" + openId + "]参与了活动[{" + hdid + ":" + hdEntity.getTitle() + "}]");

            // 获取当前公众帐号的活动规则
            String accountid = hdEntity.getAccountid();
            request.setAttribute("accountid", accountid);
            request.getSession().setAttribute("openId", openId);
            Map ad=null;
            WeixinMemberEntity memberEntity = new WeixinMemberEntity();
            String hql = "from WeixinMemberEntity t where t.accountId='" + accountid + "' and t.openId='" + openId + "'";
            List<WeixinMemberEntity> weixinMemberList = weixinMemberService.findByQueryString(hql);
            if (weixinMemberList != null && weixinMemberList.size() > 0) {
                memberEntity = weixinMemberList.get(0);
            }

            String nickname = memberEntity.getNickName();
            String phoneNumber = memberEntity.getPhoneNumber();
            String headImgUrl = memberEntity.getHeadImgUrl();
            String domain = ResourceUtil.getConfigByName("domain");
            request.getSession().setAttribute("nickname", nickname);
            request.getSession().setAttribute("phoneNumber", phoneNumber);
            request.getSession().setAttribute("headImgUrl", headImgUrl);

            Gson gson = new Gson();
            String urlFlowAccount = path + "userGetFlow/QueryFlowAccount";
            JSONObject myJsonAccount = new JSONObject();
            myJsonAccount.accumulate("phoneNumber", phoneNumber);
            myJsonAccount.accumulate("openId", openId);
            JSONObject contentFlowAccount = HttpUtil.httpPost(urlFlowAccount, myJsonAccount, false);
            String reStrFlowAccount = gson.toJson(contentFlowAccount);
            Type typeFlowAccount = new TypeToken<MerchantInfoBean>() {
            }.getType();
            MerchantInfoBean userFlowAccoun = gson.fromJson(reStrFlowAccount, typeFlowAccount);

            if (userFlowAccoun != null && userFlowAccoun.getCode().equals("200")) {
                request.getSession().setAttribute("provinceFlowValue", userFlowAccoun.getData().get(0).getProvinceFlowValue());
                request.getSession().setAttribute("countryFlowValue", userFlowAccoun.getData().get(0).getCountryFlowValue());
            } else {
                request.getSession().setAttribute("provinceFlowValue", 0);
                request.getSession().setAttribute("countryFlowValue", 0);
            }
            WeixinAccountEntity accountEntity = weixinAccountService.getEntity(WeixinAccountEntity.class, accountid);

            String lotteryType = hdEntity.getLotteryType();
            String hdUrl = null;
            //判断活动类型
            if (lotteryType != null && "2".equals(lotteryType)) {   //刮刮卡
                hdUrl = "weixin/lottery/weixinScratch";
                ad = this.adService.getPublishedAd(accountEntity.getAcctId(), AdPublishPosition.ggk.getCode());
            } else if (lotteryType != null && "3".equals(lotteryType)) {    //红包
                //判断个人抽奖次数
                StringBuffer buffer = new StringBuffer();
                buffer.append("SELECT COUNT(*) count FROM weixin_winningrecordlxc t where 1=1 ");
                buffer.append(" and t.hdid=").append("'").append(hdid).append("'");
                buffer.append(" and t.openid=").append("'").append(openId).append("'");
                int count = winningrecordlxcService.getCount(buffer.toString());
                if ((1 - count) <= 0) {
                    // 已经超过总抽奖数
                    request.setAttribute("ishave", "1");
                }
                hdUrl = "weixin/lottery/weixinRedpacket";
            } else if (lotteryType != null && "4".equals(lotteryType)) {//摇一摇----xiaoguai
                sb.append(", 摇一摇");

                try {
                    String description = "";
                    String ticket = weixinAccountService.getSignature(accountid);
                    String url = request.getRequestURL().toString();
                    String acctLogoImgUrl = accountEntity.getLogoAccount();
                    String params = request.getQueryString();

                    url = url + "?" + params;

                    //////start 商户归属地///////////////
                    WeixinAccountEntity weixinAccountEntity = new WeixinAccountEntity();
                    weixinAccountEntity = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", hdEntity.getAccountid());

                        WeixinMerchantCoverAreaEntity ww1 = new WeixinMerchantCoverAreaEntity();
                        if (weixinAccountEntity.getId() != null) {
                            ww1 = weixinMerchantCoverAreaService.findUniqueByProperty(WeixinMerchantCoverAreaEntity.class, "accountID", weixinAccountEntity.getAcctId());
                        }
                    String cityName=ww1.getCityname();

                    /**
                     * 添加运营商----xiaona--2016年4月30日
                     * 提示语添加运营商的类型
                     */

                    String businessArea=ww1.getBusinessArea();
                    if(StringUtils.isBlank(businessArea) ||"三网通".equals(businessArea)){
                        businessArea="所有运营商";
                    }
                    if(cityName==null){
                        request.setAttribute("provinceAccount", ww1.getProvincename() + "内");
                        description = "赠送流量适合" + ww1.getProvincename()+businessArea + "手机用户";
                    } else {
                        request.setAttribute("provinceAccount", ww1.getProvincename() + ww1.getCityname()+"内");
                        description = "赠送流量适合" + ww1.getProvincename() + ww1.getCityname() +businessArea+ "手机用户";
                    }

                    request.setAttribute("description", description);
                    /**
                     * 添加运营商----xiaona--2016年4月30日
                     * 提示语添加运营商的类型
                     */

                      String urlll = domain + "/" + "lotteryController.do?startLottery&hdid=" + hdid;
                    request.getSession().setAttribute("openid", openId);
                    request.getSession().setAttribute("hdid", hdid);
                    request.getSession().setAttribute("url", urlll);
                    request.getSession().setAttribute("acctLogoImgUrl", acctLogoImgUrl);
                    request.getSession().setAttribute("accountId", accountid);
                    Map<String, String> shakeMap = SignUtil.sign(ticket, url);
                    shakeMap.put("appId", accountEntity.getAccountappid());
                    request.setAttribute("shakeMap", shakeMap);

                    sb.append("_nickname:" + nickname + "_phoneNumber:" + phoneNumber);

                    ////////////////start分享的点击判断过期和是否摇完了//////////////////
                    //查询游戏剩余次数，如果为0 则活动摇完了
                    /*WeixinLotteryEntity huodongEntity = weixinLotteryService.get(WeixinLotteryEntity.class, hdid);
                    int firstprizetotal = huodongEntity.getFirstprizetotal() == null ? 0 : huodongEntity.getFirstprizetotal();
                    if (firstprizetotal <= 0) {
                        request.setAttribute("accountId", accountid);
                        request.setAttribute("openId", openId);
                        return new ModelAndView("weixin/shake/shakeyaowanle");
                    }

                    //判断是否处于活动日期
                    Date date = new Date();
                    if (huodongEntity.getStarttime().after(date)) {
                        request.setAttribute("accountId", accountid);
                        request.setAttribute("openId", openId);
                        return new ModelAndView("weixin/shake/shakelaichile");
                    }
                    if (huodongEntity.getEndtime().before(date)) {
                        request.setAttribute("accountId", accountid);
                        request.setAttribute("openId", openId);
                        return new ModelAndView("weixin/shake/shakelaichile");
                    }
                    */
                    request.setAttribute("hdid",hdid);
                    ad = this.adService.getPublishedAd(accountEntity.getAcctId(), AdPublishPosition.handshake.getCode());
                    sb.append(", 剩余次数:" + leftGameCount(hdid, openId));
                    hdUrl= "weixin/shake/weixinShakeHand";
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ModelAndView("commom/404");
                }
                //hdUrl="weixin/shake/weixinShakeHand";
                //查询用户剩余游戏次数
            } else {           	
            	 
                hdUrl = "weixin/lottery/zhuanpan"; // 默认大转盘
                ad = this.adService.getPublishedAd(accountEntity.getAcctId(), AdPublishPosition.zhuanpan.getCode());
            }

            request.getSession().setAttribute("ad", ad);
            Update countResponse = JFinalUtils.getLeftTimes(hdid, openId);
            int leftcount = Integer.parseInt("" + countResponse.getAttributes().get("count"));
            request.setAttribute("leftcount", Math.max(leftcount, 0));
            request.setAttribute("code", countResponse.getCode());
            request.setAttribute("message", countResponse.getMessage());

            //查询最近10条获奖名单
            String sql = "SELECT * FROM weixin_winningrecordlxc  WHERE (status = '"+WeiXinConstants.SUCCESS+"' or status= '"+WeiXinConstants.SUCCESS_NOBIND+"' ) and hdid='" + hdid + "' ORDER BY addtime DESC ";
            List<WeixinWinningrecordlxcEntity> winningRecordList = winningrecordlxcService.findObjForJdbc(sql, 1, 10, WeixinWinningrecordlxcEntity.class);
            for (WeixinWinningrecordlxcEntity e : winningRecordList) {
                this.winningrecordlxcService.getSession().evict(e);
                e.setPrizelevel(this.toUpperNumber(e.getPrizelevel()));
            }
            request.setAttribute("winningRecordList", winningRecordList);
            sb.append(", 内部跳转到活动地址:" + hdUrl);
            
            Map<String, String> map = weixinAccountService.getAccountJsticket(request, accountid);
            request.getSession().setAttribute("map", map);
            request.getSession().setAttribute("domain", ResourceUtil.getDomain());
            request.getSession().setAttribute("accountType", accountEntity.getAccounttype());
            return new ModelAndView(hdUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("common/404");
        } finally {
            Long end = System.currentTimeMillis();
            sb.append(MessageFormat.format(", 方法耗时:{0}ms", end - start));
            LOGGER.info(sb.toString());
        }
    }

    private String toUpperNumber(String number) {
        String upper = null;
        if(number==null){
            return null;
        }
        switch (number) {
        case "0":
            upper = "零";
            break;
        case "1":
            upper = "一";
            break;
        case "2":
            upper = "二";
            break;
        case "3":
            upper="三";
            break;
        case "4":
            upper="四";
            break;
        case "5":
            upper="五";
            break;
        case "6":
            upper="六";
            break;
        case "7":
            upper="七";
            break;
        case "8":
            upper="八";
            break;
        case "9":
            upper="九";
            break;
        case "10":
            upper="十";
            break;
        default :
            upper=number;
            break;
        }
        return upper;
    }

    /**
     * 大转盘
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "luckyTurntable")
    @ResponseBody
    public Update luckyTurnable(HttpServletRequest request) {
        String hdId = request.getParameter("hdId");
        String openId = request.getParameter("openId");
        return JFinalUtils.luckyTurnable(hdId, openId);
    }

    /**
     * 拆红包
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "openRedpacket")
    @ResponseBody
    public Update openRedpacket(HttpServletRequest request) {
        String hdid = request.getParameter("hdid");
        String openId = request.getParameter("openId");
        return JFinalUtils.openRedpacket(hdid, openId);
    }

    /**
     * 刮刮卡-刮开前抽奖，更换背景图片
     *
     * @return
     */
    @RequestMapping(params = "scratchCard")
    @ResponseBody
    public Update scratchMove(HttpServletRequest request) {
        String hdId = request.getParameter("hdId");
        String openId = request.getParameter("openId");
        Update update = JFinalUtils.scratchCard(hdId, openId);
        // 将当前的刮刮卡状态信息存入session中,以便保存刮刮卡中奖纪录
        request.getSession().setAttribute("scratch", update);

        // 不要展示过多的session中的信息
        Update result = new Update(update.getCode(), update.getMessage());
        result.getAttributes().put("prizeLevel", update.getAttributes().get("prizeLevel"));
        result.getAttributes().put("prizeValue", update.getAttributes().get("prizeValue"));
        return result;
    }

    /**
     * 保存刮刮卡的中奖纪录(当刮开面积超过50%时)
     *
     * @return
     */
    @RequestMapping(params = "saveScratchRecord")
    @ResponseBody
    public Update saveScratchRecord(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 通过scratch(key)获取session中的刮刮卡中奖状态
        Update scratchInfo = (Update) session.getAttribute("scratch");
        if (null == scratchInfo) {
            scratchInfo = new Update("400", "请刷新页面后重试！");
            return scratchInfo;
        }
        session.removeAttribute("scratch");
        return JFinalUtils.saveScratchRecord(scratchInfo);
    }

    @RequestMapping(params = "shakeHand")
    @ResponseBody
    public Update shakeHand(HttpServletRequest request) {
        String hdid = request.getParameter("hdid");
        String openId = request.getParameter("openId");
        return JFinalUtils.shakeHand(hdid, openId);
    }


    /////////////////////////////////////////公共方法/begin////////////////////////////////////////////

    //查询每个人的游戏剩余次数---xiaoguai
    public int leftGameCount(String hdid, String openId) {
        DataSourceType dsType = DataSourceSwitcher.getDataSource();
        DataSourceSwitcher.setDataSource(DataSourceType.dataSource_slave);
        WeixinLotteryEntity hdEntity = this.systemService.get(WeixinLotteryEntity.class, hdid);

        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT COUNT(*) count FROM weixin_winningrecordlxc t where 1=1 ");
        buffer.append(" and t.HDID=").append("'").append(hdEntity.getId()).append("'");
        buffer.append(" and t.OPENID=").append("'").append(openId).append("'");
        Integer count = 0;
        Integer leftcount = 0;

        //查询游戏剩余次数
        if (StringUtils.isNotBlank(openId)) {
            count = winningrecordService.getCount(buffer.toString());
            leftcount = hdEntity.getLotterynumberday() - count;
            return leftcount;
        }

        DataSourceSwitcher.setDataSource(dsType);
        //粉丝游戏剩余次数
        return 0;
    }


    /////////////////////////////////////////公共方法/end////////////////////////////////////////////

    // 抽奖并返回角度和奖项
    public Object[] award(Object[][] prizeArr) {
        // 概率数组
        Integer obj[] = new Integer[prizeArr.length];
        for (int i = 0; i < prizeArr.length; i++) {
            obj[i] = (Integer) prizeArr[i][4];
        }
        Integer prizeId = getRand(obj); // 根据概率获取奖项id
        // 旋转角度
        int angle = new Random().nextInt((Integer) prizeArr[prizeId][2] - (Integer) prizeArr[prizeId][1])
                + (Integer) prizeArr[prizeId][1];
        String msg = (String) prizeArr[prizeId][3];// 提示信息
        return new Object[]{angle, prizeId, msg};
    }

    // 根据概率获取奖项
    public Integer getRand(Integer obj[]) {
        Integer result = null;
        try {
            int sum = 0;// 概率数组的总概率精度
            for (int i = 0; i < obj.length; i++) {
                sum += obj[i];
            }
            for (int i = 0; i < obj.length; i++) {// 概率数组循环
                int randomNum = new Random().nextInt(sum);// 随机生成1到sum的整数
                if (randomNum < obj[i]) {// 中奖
                    result = i;
                    break;
                } else {
                    sum -= obj[i];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 微信活动列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "weixinLottery")
    public ModelAndView weixinLottery(HttpServletRequest request) {
        return new ModelAndView("weixin/lottery/weixinLotteryList");
    }

    /**
     * easyui AJAX请求数据
     *
     * @param request
     * @param response
     * @param dataGrid
     * @param
     */
    @RequestMapping(params = "datagrid")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void datagrid(WeixinLotteryEntity weixinLottery, HttpServletRequest request, HttpServletResponse response,
                         DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WeixinLotteryEntity.class, dataGrid);
        // 查询条件组装器ra
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinLottery,
                request.getParameterMap());
        try {
            // 自定义追加查询条件
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        this.weixinLotteryService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 删除微信活动
     *
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(WeixinLotteryEntity weixinLottery, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        weixinLottery = systemService.getEntity(WeixinLotteryEntity.class, weixinLottery.getId());
        message = "微信活动删除成功";
        try {
            weixinLotteryService.delete(weixinLottery);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信活动删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 批量删除微信活动
     *
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "微信活动删除成功";
        try {
            for (String id : ids.split(",")) {
                WeixinLotteryEntity weixinLottery = systemService.getEntity(WeixinLotteryEntity.class, id);
                weixinLotteryService.delete(weixinLottery);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信活动删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 添加微信活动
     *
     * @param
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(WeixinLotteryEntity weixinLottery, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "微信活动添加成功";
        try {
            weixinLotteryService.save(weixinLottery);
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信活动添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 更新微信活动
     *
     * @param
     * @return
     */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(WeixinLotteryEntity weixinLottery, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "微信活动更新成功";
        WeixinLotteryEntity t = weixinLotteryService.get(WeixinLotteryEntity.class, weixinLottery.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(weixinLottery, t);
            weixinLotteryService.saveOrUpdate(t);
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信活动更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 微信活动新增页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goAdd")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public ModelAndView goAdd(WeixinLotteryEntity weixinLottery, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weixinLottery.getId())) {
            weixinLottery = weixinLotteryService.getEntity(WeixinLotteryEntity.class, weixinLottery.getId());
            req.setAttribute("weixinLotteryPage", weixinLottery);
        }
        return new ModelAndView("weixin/lottery/weixinLottery-add");
    }

    /**
     * 微信活动编辑页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public ModelAndView goUpdate(WeixinLotteryEntity weixinLottery, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weixinLottery.getId())) {
            weixinLottery = weixinLotteryService.getEntity(WeixinLotteryEntity.class, weixinLottery.getId());
            req.setAttribute("weixinLotteryPage", weixinLottery);
        }
        return new ModelAndView("weixin/lottery/weixinLottery-update");
    }

    /**
     * 导入功能跳转
     *
     * @return
     */
    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        return new ModelAndView("weixin/lottery/weixinLotteryUpload");
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void exportXls(WeixinLotteryEntity weixinLottery, HttpServletRequest request, HttpServletResponse response,
                          DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "微信活动";
            // 根据浏览器进行转码，使其支持中文文件名
            if (BrowserUtils.isIE(request)) {
                response.setHeader("content-disposition",
                        "attachment;filename=" + java.net.URLEncoder.encode(codedFileName, "UTF-8") + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
                response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
            }
            // 产生工作簿对象
            HSSFWorkbook workbook = null;
            CriteriaQuery cq = new CriteriaQuery(WeixinLotteryEntity.class, dataGrid);
            org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinLottery,
                    request.getParameterMap());

            List<WeixinLotteryEntity> weixinLotterys = this.weixinLotteryService.getListByCriteriaQuery(cq, false);
            workbook = ExcelExportUtil.exportExcel(
                    new ExcelTitle("微信活动列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"),
                    WeixinLotteryEntity.class, weixinLotterys);
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (Exception e) {
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {

            }
        }
    }

    /**
     * 导出excel 使模板
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXlsByT")
    public void exportXlsByT(WeixinLotteryEntity weixinLottery, HttpServletRequest request,
                             HttpServletResponse response, DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "微信活动";
            // 根据浏览器进行转码，使其支持中文文件名
            if (BrowserUtils.isIE(request)) {
                response.setHeader("content-disposition",
                        "attachment;filename=" + java.net.URLEncoder.encode(codedFileName, "UTF-8") + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
                response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
            }
            // 产生工作簿对象
            HSSFWorkbook workbook = null;
            workbook = ExcelExportUtil.exportExcel(
                    new ExcelTitle("微信活动列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"),
                    WeixinLotteryEntity.class, null);
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (Exception e) {
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {

            }
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(params = "importExcel", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setSecondTitleRows(1);
            params.setNeedSave(true);
            try {
                List<WeixinLotteryEntity> listWeixinLotteryEntitys = (List<WeixinLotteryEntity>) ExcelImportUtil
                        .importExcelByIs(file.getInputStream(), WeixinLotteryEntity.class, params);
                for (WeixinLotteryEntity weixinLottery : listWeixinLotteryEntitys) {
                    weixinLotteryService.save(weixinLottery);
                }
                j.setMsg("文件导入成功！");
            } catch (Exception e) {
                j.setMsg("文件导入失败！");
                LOGGER.error(ExceptionUtil.getExceptionMessage(e));
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return j;
    }


}

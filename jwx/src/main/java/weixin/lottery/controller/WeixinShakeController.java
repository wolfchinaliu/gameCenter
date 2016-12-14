package weixin.lottery.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.account.service.impl.WeixinAccountServiceImpl;
import weixin.guanjia.core.util.SignUtil;
import weixin.liuliangbao.jsonbean.MerchantInfoBean;
import weixin.liuliangbao.jsonbean.UserFlowAccountBean;
import weixin.liuliangbao.util.HttpUtil;
import weixin.lottery.entity.WeixinLotteryEntity;
import weixin.lottery.entity.WeixinWinningrecordEntity;
import weixin.lottery.entity.WeixinWinningrecordlxcEntity;
import weixin.lottery.service.WeixinLotteryServiceI;
import weixin.lottery.service.WeixinWinningrecordlxcServiceI;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.util.DataDictionaryUtil.FlowType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xuxiaoguai
 * @version V1.0
 * @Title: Controller
 * @Description: 微信摇一摇活动
 * @date 2015-02-05 14:26:01
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinShakeController")
public class WeixinShakeController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger LOGGER = Logger.getLogger(WeixinRedPacketsController.class);
    private String path= ResourceUtil.getConfigByName("jfinalUrl-config");

    @Autowired
    private WeixinLotteryServiceI weixinLotteryService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private WeixinAcctFlowAccoutServiceI accoutServiceI;
    @Autowired
    private WeixinMemberServiceI memberServiceI;
    @Autowired
    private WeixinAccountServiceI weixinAccountServiceI;
    @Autowired
    private WeixinWinningrecordlxcServiceI winningrecordlxcServiceI;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    /**
     * 微信摇一摇活动列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "weixinShakeList")
    public ModelAndView weixinShakeList(HttpServletRequest request) {
        request.setAttribute("hdid", request.getParameter("hdid"));
        LOGGER.info(request.getParameter("hdid"));
        return new ModelAndView("weixin/shake/weixinShakeList");
    }


    /**
     * 活动列表页面
     *
     * @param weixinLottery
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "datagrid")
    public void datagrid(WeixinLotteryEntity weixinLottery, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WeixinLotteryEntity.class, dataGrid);
        //商户ID
        weixinLottery.setAccountid(ResourceUtil.getWeiXinAccountId());
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinLottery, request.getParameterMap());
        cq.add();
        this.weixinLotteryService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 微信摇一摇新增页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(WeixinLotteryEntity weixinLottery, HttpServletRequest req) {
        req.setAttribute("lotteryType", req.getParameter("lotteryType"));
        return new ModelAndView("weixin/shake/weixinShake-add");
    }
    /**
     * 添加微信（摇一摇）活动
     *
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(WeixinLotteryEntity weixinLottery, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        double totalFlow=Double.parseDouble(
                request.getParameter("abledotherprize")==null?"0":request.getParameter("abledotherprize"));
        int shaketotal = weixinLottery.getLotterynumber();

        if (shaketotal > totalFlow * 10) {
            j.setMsg("您设置的活动次数过多或者活动总流量太少，请重新设置！");
            j.setSuccess(false);
            return j;
        }
        String flowType=request.getParameter("flowtype");
        String accountId=ResourceUtil.getWeiXinAccountId();/**/
        //String accountId="402881ea52ecfe290152ed009ea30003";
        String start = request.getParameter("starttime");
        String endtime = request.getParameter("endtime");
        if (start == null || start.equals("")) {
            j.setMsg("请填写开始时间");
            j.setSuccess(false);
            return j;
        }
        if (endtime == null || endtime.equals("")) {
            j.setMsg("请填写结束时间");
            j.setSuccess(false);
            return j;
        }
        Date date = new Date();    //定义一个当前的日期

        if (date.getTime() > weixinLottery.getEndtime().getTime()) {
            j.setMsg("结束时间不能小于当前的时间");
            j.setSuccess(false);
            return j;
        }
        /*
        * 添加的判断
        * */
        if (weixinLottery.getLotterynumber() < weixinLottery.getLotterynumberday()) {
            j.setMsg("总的抽奖次数不能小于每人抽奖次数");
            j.setSuccess(false);
            return j;
        }
        if (weixinLottery.getStarttime().getTime() >= weixinLottery.getEndtime().getTime()) {
            j.setMsg("结束时间必须大于开始时间");
            j.setSuccess(false);
            return j;
        }
        message = "微信活动添加成功";
        try {
            weixinAcctFlowAccountEntity accountEntity=
                    accoutServiceI.findUniqueByProperty(weixinAcctFlowAccountEntity.class,"accountId",accountId);
            /**/double countryFlowValue=
                    accountEntity.getCountryFlowValue()==null?0:accountEntity.getCountryFlowValue();
            double provinceFlowValue=
                    accountEntity.getProvinceFlowValue()==null?0:accountEntity.getProvinceFlowValue();
           /*double countryFlowValue=
                    100;
            double provinceFlowValue=
                   100; */
            if(FlowType.country.getCode().equals(flowType)){
                if(countryFlowValue<totalFlow){
                    j.setMsg("您的商户全国流量余额不足，请及时充值");
                    j.setSuccess(false);
                    return j;
                }
            }
            if(FlowType.province.getCode().equals(flowType)) {
                if(provinceFlowValue<totalFlow){
                    j.setMsg("您的商户省内流量余额不足，请及时充值");
                    j.setSuccess(false);
                    return j;
                }

            }
            //剩余的活动次数初始值默认为活动总次数
            weixinLottery.setFirstprizetotal(weixinLottery.getLotterynumber());
            //已经摇过的次数初始值默认为0
            weixinLottery.setFrequency("0");
            if(date.getTime()>weixinLottery.getStarttime().getTime()){
                weixinLottery.setState("1");//设置为活动进行中
            }else {
                weixinLottery.setState("2");//设置活动为尚未开始。
            }
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
     * 微信活动（摇一摇）编辑页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(WeixinLotteryEntity weixinLottery, HttpServletRequest req) throws Exception {
        req.setAttribute("lotteryType", req.getParameter("lotteryType"));
        if (StringUtil.isNotEmpty(weixinLottery.getId())) {
            weixinLottery = weixinLotteryService.getEntity(WeixinLotteryEntity.class, weixinLottery.getId());
            Date createTime = weixinLottery.getStarttime();
            req.setAttribute("createTime", createTime);   //活动开始时间
            Date endTime = weixinLottery.getEndtime();
            req.setAttribute("endTime", endTime);   //活动结束时间

            String merchantFlow=weixinLottery.getAbledotherprize();
            req.setAttribute("AllFlowValue",merchantFlow);

            Date date = new Date();    //定义一个当前的日期
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(date);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   ////定义一个当前的日期，转换为相应的日期
            Date date1 = sdf.parse(time);
//            分三种情况进行判读
//            活动未开始的时候，如果当前日期小于开始日期，表示尚未开始
            if (date1.getTime() < createTime.getTime()) {
                req.setAttribute("hd", "-1");
            }
//            活动进行中，当前日期介于开始和结束日期之间
            if (date1.getTime() > createTime.getTime() && date1.getTime() < endTime.getTime()) {
                req.setAttribute("hd", "0");
            }
//            活动结束后，当前日期大于结束日期
            if (date1.getTime() > endTime.getTime()) {
                req.setAttribute("hd", "1");
            }
            req.setAttribute("weixinLotteryPage", weixinLottery);
        }
        return new ModelAndView("weixin/shake/weixinShake-update");
    }
    /**
     * 更新微信（摇一摇）活动
     *
     * @param
     * @return
     */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(WeixinLotteryEntity weixinLottery, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        Double totalvalue = Double.parseDouble(weixinLottery.getAbledotherprize());

        String Id=weixinLottery.getId();
        WeixinLotteryEntity weixinLotteryEntity1=this.systemService.getEntity(WeixinLotteryEntity.class,Id);
        Double totalNumber=Double.valueOf(weixinLotteryEntity1.getLotterynumber());
        Double totalFlow=Double.parseDouble(weixinLottery.getAbledotherprize());
        String totalNum=request.getParameter("lotterynumber");

        int shaketotal = weixinLottery.getLotterynumber();

        if (shaketotal > totalvalue * 10) {
            j.setMsg("您设置的活动次数过多或者活动总流量太少，请重新设置！");
            j.setSuccess(false);
            return j;
        }
        if (weixinLottery.getStarttime().getTime() >= weixinLottery.getEndtime().getTime()) {
            j.setMsg("结束时间必须大于开始时间");
            j.setSuccess(false);
            return j;
        }

        /*总次数和每人的次数只能增加*/
        if (totalNumber>Double.valueOf(totalNum)){
            j.setMsg("活动总次数只能增加，不能减少！");
            j.setSuccess(false);
            return j;
        }


        String AllFlowValue=request.getParameter("AllFlowValue").toString();

        if (Double.parseDouble(AllFlowValue) > Double.parseDouble(weixinLottery.getAbledotherprize())) {
            j.setSuccess(false);
            j.setMsg("总流量只能增加！");
            return j;
        }




        String flowType=weixinLotteryEntity1.getFlowtype();

        String accountId=ResourceUtil.getWeiXinAccountId();

        weixinAcctFlowAccountEntity accountEntity=
                accoutServiceI.findUniqueByProperty(weixinAcctFlowAccountEntity.class,"accountId",accountId);
            /**/double countryFlowValue=
                accountEntity.getCountryFlowValue()==null?0:accountEntity.getCountryFlowValue();
        double provinceFlowValue=
                accountEntity.getProvinceFlowValue()==null?0:accountEntity.getProvinceFlowValue();

        if(FlowType.country.getCode().equals(flowType)){
            if(countryFlowValue<totalFlow){
                j.setMsg("您的商户全国流量余额不足，请及时充值");
                j.setSuccess(false);
                return j;
            }
        }
        if(FlowType.province.getCode().equals(flowType)) {
            if(provinceFlowValue<totalFlow){
                j.setMsg("您的商户省内流量余额不足，请及时充值");
                j.setSuccess(false);
                return j;
            }

        }


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
     * 删除微信（摇一摇）活动
     * @author gaoqingjia
     * @param weixinLottery
     * @param request
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(WeixinLotteryEntity weixinLottery,HttpServletRequest request){
        AjaxJson a=new AjaxJson();
        message="微信活动删除成功";
        try {
            weixinLottery = systemService.getEntity(WeixinLotteryEntity.class, weixinLottery.getId());
//            systemService.deleteEntityById(WeixinLotteryEntity.class,weixinLottery.getId());
            weixinLotteryService.delWeixinLottery(weixinLottery);
        }catch (Exception e){
            e.printStackTrace();
            message="微信活动删除成功";
            throw new BusinessException(e.getMessage());
        }
        a.setMsg(message);
        return a;

    }

    /**
     * 查询中奖纪录
     * 看别人摇了多少
     * @param request
     * @return
     */
    @RequestMapping(params="shakeRecord")
    public ModelAndView shakeRecord(HttpServletRequest request){
        StringBuilder builder = new StringBuilder();
        builder.append("WeixinShakeController_ShakeRecord方法:看别人摇了多少，查询摇一摇获奖记录");

        String hdid=request.getParameter("hdId");
        ModelAndView mav = new ModelAndView();
        try {
            WeixinLotteryEntity HDEntity=weixinLotteryService.findUniqueByProperty(WeixinLotteryEntity.class,"id",hdid);
            String accountid=HDEntity.getAccountid();
            WeixinAccountEntity accountEntity=weixinAccountServiceI.findUniqueByProperty(WeixinAccountEntity.class,"id",accountid);
            String accountName=accountEntity.getAccountname();
            String hql = "from WeixinWinningrecordlxcEntity where hdid='" + hdid + "'order by addtime desc";
            List<WeixinWinningrecordlxcEntity> listCount=winningrecordlxcServiceI.findHql(hql);

            int toIndex=listCount.size()>=100?100:listCount.size();
            List<Object> winningrecord = winningrecordlxcServiceI.findHql(hql).subList(0,toIndex);

            //List<WeixinWinningrecordlxcEntity> winningrecord =winningrecordlxcServiceI.findListbySql("select * from weixin_winningrecordlxc where hdid='"+hdid+"'order by addtime desc limit 100" );
            if (winningrecord.size()>0){
                mav.addObject("winningrecord", winningrecord);
                mav.addObject("accountName",accountName);
                mav.setViewName("weixin/shake/shakeRecord");
                builder.append("获奖记录条数:"+winningrecord.size());
            }else{
                builder.append("目前没有获奖记录");
            }


            /////////////重新查询该用户的流量值////////////////
            Gson gson = new Gson();
            Type type = new TypeToken<UserFlowAccountBean>() {
            }.getType();
            //获取用户的手机号码，根据手机号码去重新查询账户流量值
//        String phoneNumber = "15201015003";
            // String phoneNumber = request.getParameter("phoneNumber");
            String phoneNumber = (String)request.getSession().getAttribute("phoneNumber");
            String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "chargeflow/QueryFlowAccountbyPhone";
            JSONObject myJsonObject = new JSONObject();
            myJsonObject.accumulate("phoneNumber", phoneNumber);
            JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
            String strContent = gson.toJson(content);

            UserFlowAccountBean jsonBean = gson.fromJson(strContent, type);
            UserFlowAccountBean.DataEntity accountBean = new UserFlowAccountBean.DataEntity();
            accountBean = jsonBean.getData();
            String provinceFlowValue = String.valueOf(accountBean.getProvinceFlowValue());
            String countryFlowValue = String.valueOf(accountBean.getCountryFlowValue());
            mav.addObject("provinceFlowValue", provinceFlowValue);
            mav.addObject("countryFlowValue",countryFlowValue);

        }catch(Exception e){
            e.printStackTrace();
            mav.setViewName("comcom/404");
        }finally {
            return mav;
        }

    }


    @RequestMapping(params = "weixinShakeAddress")
    public ModelAndView weixinShakeAddress(HttpServletRequest request) {
        request.setAttribute("hdid", request.getParameter("hdid"));
        request.setAttribute("rooturl",ResourceUtil.getConfigByName("domain"));
        return new ModelAndView("weixin/shake/weixinShakeAddress");
    }


}

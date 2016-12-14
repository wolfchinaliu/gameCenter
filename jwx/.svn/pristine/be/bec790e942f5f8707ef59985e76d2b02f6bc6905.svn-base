package weixin.personalredpacket.controller;

import org.apache.log4j.Logger;
import org.jeecgframework.core.annotation.DataSourceSwitch;
import org.jeecgframework.core.aop.DataSourceSwitcher;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.*;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import weixin.lottery.entity.WeixinLotteryEntity;
import weixin.personalredpacket.entity.PersonalRedpacketSetEntity;
import weixin.personalredpacket.entity.RedpacketListEntity;
import weixin.personalredpacket.service.PersonalRedpacketSetServiceI;
import weixin.personalredpacket.service.impl.leftRecordSendFlow;
import weixin.tenant.entity.FlowCardTradeRecordsEntity;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;
import weixin.tenant.service.FlowCardTradeRecordsServiceI;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.util.DataDictionaryUtil.FlowType;
import weixin.util.DataDictionaryUtil.MerchantFlowTradeType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 刘晓春
 * @version V1.0
 * @Title: Controller
 * @Description: 个人红包
 * @date 2016-1-21 15:02:32
 */
@Scope("prototype")
@Controller
@RequestMapping("/personalRedpacketSetController")
public class PersonalRedpacketSetController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(PersonalRedpacketSetController.class);
    @Autowired
    private PersonalRedpacketSetServiceI redpacketSetService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private WeixinAcctFlowAccoutServiceI weixinAcctFlowAccoutService;
    @Autowired
    private FlowCardTradeRecordsServiceI flowCardTradeRecordsService;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 微信活动列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "redpacketSet")
    public ModelAndView redpacketSet() {
        return new ModelAndView("weixin/personalredpacket/redpacketSetList");
    }


    /**
     * @param redpacket
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "datagrid")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void datagrid(PersonalRedpacketSetEntity redpacket, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(PersonalRedpacketSetEntity.class, dataGrid);
        //商户ID
        redpacket.setAccountid(ResourceUtil.getWeiXinAccountId());
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, redpacket, request.getParameterMap());
        cq.add();
        this.redpacketSetService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * @param redpacket
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "datagridbysql")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void datagridbysql(PersonalRedpacketSetEntity redpacket, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        String strTitle=request.getParameter("title");
        StringBuffer sql = new StringBuffer();
        sql.append("select r1.id,r1.title,r1.redpacketValue,r1.redpacketNum,r1.subsidyValue,r1.flowSendValue,r1.state,r1.sendnum,r1.getFlow,r1.getsum,r2.membersum ");
        sql.append("from (");
        sql.append("select p2.id,p2.title as title,p2.flowSendValue as redpacketValue,p2.redpacketNum as redpacketNum,p2.subsidyValue as subsidyValue,");
        sql.append("p2.flowSendValue as flowSendValue,p2.state as state,p2.sendnum as sendnum,p3.getflow as getFlow,p3.getsum as getsum ");
        sql.append("from ");
        sql.append("(");
        sql.append("select p0.id as id,p0.title as title,p0.redpacketValue as redpacketValue,p0.redpacketNum as redpacketNum,p0.subsidyValue as subsidyValue,");
        sql.append("p0.flowSendValue as flowSendValue,p0.state as state,p1.sendnum,p0.id as redId  ");
        sql.append("from (select pm.*,w.title ");
        sql.append("from weixin_personalredpacketset pm join weixin_commonforhd w where pm.id=w.id and w.accountid='");
        sql.append(ResourceUtil.getWeiXinAccountId());
        sql.append("') as p0 ");
        sql.append("left join ");
        sql.append("( ");
        sql.append("select p.*,count(r.redpacketsetId)*20 as sendnum ");
        sql.append("from weixin_personalredpacket r,weixin_personalredpacketset p ");
        sql.append("where p.id=r.redpacketsetId ");
        sql.append("group by redpacketsetId ");
        sql.append(") as p1 ");
        sql.append("on p0.id=p1.id ");
        sql.append(") as p2 ");
        sql.append("left join ");
        sql.append("(");
        sql.append("select sum(ps.redFlowValue) as getflow,count(ps.redpacksetId) as getsum,ps.redpacksetId ");
        sql.append("from weixin_personalredpacketrecords as ps ");
        sql.append("group by redpacksetId ");
        sql.append(") as p3 ");
        sql.append("on p3.redpacksetId=p2.redId");
        sql.append(" order by state desc");
        sql.append(") as r1 ");
        sql.append("left join ");
        sql.append("(select t.redpacksetId,count(*) as membersum from (");
        sql.append("select distinct p.openId,p.redpacksetId,p.accountId,p.nickname,m.subscribe_time ");
        sql.append("from weixin_personalredpacketrecords p join weixin_member m join weixin_commonforhd c ");
        sql.append("where p.openId=m.open_id and p.accountId=m.account_id and c.id=p.redpacksetId and m.subscribe_time between c.starttime and c.endtime ");
        sql.append(") as t ");
        sql.append("group by t.redpacksetId");
        sql.append(") as r2 ");
        sql.append("on r1.id=r2.redpacksetId ");
        if (strTitle != null) {
            sql.append(" where r1.title='");
            sql.append(strTitle);
            sql.append("'");
        }

        HqlQuery hqlQuery = new HqlQuery(RedpacketListEntity.class, sql.toString(), dataGrid);
        PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
        List<Object[]> list = pageList.getResultList();
        List<RedpacketListEntity> param = new ArrayList<RedpacketListEntity>();

        for (Object[] objects : list) {
            RedpacketListEntity wd = new RedpacketListEntity();
            Object gid = objects[0];
            if (gid != null) {
                wd.setId(gid.toString());
            }
            Object title = objects[1];
            if (title != null) {
                wd.setTitle(title.toString());
            }
            Object redpacketValue = objects[2];
            if (redpacketValue != null) {
                wd.setRedpacketValue(redpacketValue.toString());
            }

            Object redpacketNum = objects[3];
            if (redpacketNum != null) {
                wd.setRedpacketNum(Integer.valueOf(redpacketNum.toString()));
            }

            Object subsidyValue = objects[4];
            if (subsidyValue != null) {
                wd.setSubsidyValue((subsidyValue.toString()));
            }

            Object flowSendValue = objects[5];
            if (flowSendValue != null) {
                wd.setFlowSendValue((flowSendValue.toString()));
            }
            Object state = objects[6];
            if (state != null) {
                wd.setState((state.toString()));
            }

            Object sendnum = objects[7];
            if (sendnum != null) {
                wd.setSendnum((Integer.valueOf(sendnum.toString())));
            }else{
                wd.setSendnum(0);
            }
            Object getFlow = objects[8];
            if (getFlow != null) {
                wd.setGetFlow((Double.parseDouble(getFlow.toString())));
            }else{
                wd.setGetFlow(0.0);
            }
            Object getsum = objects[9];
            if (getsum != null) {
                wd.setGetsum((Integer.valueOf(getsum.toString())));
            }else{
                wd.setGetsum(0);
            }
            Object membersum = objects[10];
            if (membersum != null) {
                wd.setMembersum((Integer.valueOf(membersum.toString())));
            }else{
                wd.setMembersum(0);
            }


            param.add(wd);
        }
        dataGrid.setResults(param);
        dataGrid.setTotal(pageList.getCount());
        dataGrid.setPage(pageList.getCurPageNO());
        dataGrid.setRows(pageList.getOffset());
        TagUtil.datagrid(response, dataGrid);

    }

    /**
     * 微信活动新增页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(PersonalRedpacketSetEntity redpacket, HttpServletRequest req) {
        req.setAttribute("lotteryType", req.getParameter("lotteryType"));
        if (StringUtil.isNotEmpty(redpacket.getId())) {
            redpacket = redpacketSetService.getEntity(WeixinLotteryEntity.class, redpacket.getId());
            req.setAttribute("weixinLotteryPage", redpacket);
        }
        String startTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
        req.setAttribute("startTime", startTime);
        return new ModelAndView("weixin/personalredpacket/redpacketSet-add");
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(PersonalRedpacketSetEntity redpacket, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();

        String start = request.getParameter("starttime");
        String endtime = request.getParameter("endtime");

        if (redpacket.getRedpacketValue() > redpacket.getSubsidyValue()) {
            j.setSuccess(false);
            j.setMsg("补贴流量必须大于单个红包的流量！");
            return j;
        }


        if (endtime == null || endtime.equals("")) {
            j.setSuccess(false);
            j.setMsg("请填写结束时间");
            return j;
        }


        if (redpacket.getEndtime().getTime() <= System.currentTimeMillis()) {
            j.setSuccess(false);
            j.setMsg("结束时间应该大于开始时间！");
            return j;
        }

        Date date = new Date();    //定义一个当前的日期

        if (date.getTime() > redpacket.getEndtime().getTime()) {
            j.setMsg("结束时间不能小于当前的时间");
            j.setSuccess(false);
            return j;
        }

        if(date.getTime()>redpacket.getStarttime().getTime()){
            redpacket.setState("1");//设置为活动进行中
        }else {
            redpacket.setState("2");//设置活动为尚未开始。
        }

        message = "微信活动添加成功";
        try {

            weixinAcctFlowAccountEntity acctFlowAccountEntity = new weixinAcctFlowAccountEntity();
            acctFlowAccountEntity = this.systemService.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "accountId", ResourceUtil.getWeiXinAccountId());

            Double countryFlowValue = acctFlowAccountEntity.getCountryFlowValue();  //全国流量
            Double provinceFlowValue = acctFlowAccountEntity.getProvinceFlowValue();  //省内流量
            Double subsidyValue = redpacket.getSubsidyValue();  //补贴流量

            if (redpacket.getFlowtype().equals(FlowType.country.getCode())) {
                if (subsidyValue > countryFlowValue) {
                    j.setSuccess(false);
                    j.setMsg("您的全国流量账户余额只有：" + countryFlowValue + "M,不够本次设置的流量值：" + subsidyValue + "M,e 请充值后再来！");
                    return j;
                }
                acctFlowAccountEntity.setCountryFlowValue(countryFlowValue - subsidyValue);
            } else {
                if (subsidyValue > provinceFlowValue) {
                    j.setSuccess(false);
                    j.setMsg("您的省内流量账户余额只有：" + provinceFlowValue + "M,不够本次设置的流量值：" + subsidyValue + "M,请充值后再来！");
                    return j;
                }
                acctFlowAccountEntity.setProvinceFlowValue(provinceFlowValue - subsidyValue);
            }

            this.systemService.saveOrUpdate(acctFlowAccountEntity);  //更新商户账户
//            redpacket.setState("1"); //表示活动正在进行
            redpacket.setRedpacketNum(20); //表示活动正在进行
            redpacket.setSubsidyProportion(1.0); //表示活动正在进行
            redpacket.setFlowSendValue(0.0); //表示活动正在进行
            redpacketSetService.save(redpacket);  //添加个人红包设置
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信活动添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(PersonalRedpacketSetEntity redpacket, HttpServletRequest req) throws Exception {
        if (StringUtil.isNotEmpty(redpacket.getId())) {
            redpacket = redpacketSetService.getEntity(PersonalRedpacketSetEntity.class, redpacket.getId());

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String starttime = format.format(redpacket.getStarttime());
            req.setAttribute("starttime", starttime);

            req.setAttribute("leftflow", redpacket.getSubsidyValue() - redpacket.getFlowSendValue());


            req.setAttribute("redpacketSet", redpacket);
        }
        return new ModelAndView("weixin/personalredpacket/redpacketSet-update");
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(PersonalRedpacketSetEntity redpacket, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();

        String endtimeOld = request.getParameter("endtimeOld");
        String subsidyValueOld = request.getParameter("subsidyValueOld");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(endtimeOld);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (redpacket.getEndtime().getTime() < date.getTime()) {
            j.setSuccess(false);
            j.setMsg("结束时间只能延长！");
            return j;
        }

        if (Double.parseDouble(subsidyValueOld) > redpacket.getSubsidyValue()) {
            j.setSuccess(false);
            j.setMsg("补贴流量只能增加！");
            return j;
        }

        message = "微信活动更新成功";
        PersonalRedpacketSetEntity t = redpacketSetService.get(PersonalRedpacketSetEntity.class, redpacket.getId());
        try {

            if (Double.parseDouble(subsidyValueOld) < redpacket.getSubsidyValue()) {
                weixinAcctFlowAccountEntity acctFlowAccountEntity = new weixinAcctFlowAccountEntity();
                acctFlowAccountEntity = this.systemService.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "accountId", ResourceUtil.getWeiXinAccountId());

                Double countryFlowValue = acctFlowAccountEntity.getCountryFlowValue();  //全国流量
                Double provinceFlowValue = acctFlowAccountEntity.getProvinceFlowValue();  //省内流量
                Double subsidyValue = redpacket.getSubsidyValue() - Double.parseDouble(subsidyValueOld);  //补贴流量

                if (t.getFlowtype().equals(FlowType.country.getCode())) {
                    if (subsidyValue > countryFlowValue) {
                        j.setSuccess(false);
                        j.setMsg("您的全国流量账户余额只有：" + countryFlowValue + "M,不够本次新增设置的流量值：" + subsidyValue + "M,请充值后再来！");
                        return j;
                    }
                    acctFlowAccountEntity.setCountryFlowValue(countryFlowValue - subsidyValue);
                } else {
                    if (subsidyValue > provinceFlowValue) {
                        j.setSuccess(false);
                        j.setMsg("您的省内流量账户余额只有：" + provinceFlowValue + "M,不够本次新增设置的流量值：" + subsidyValue + "M,请充值后再来！");
                        return j;
                    }
                    acctFlowAccountEntity.setProvinceFlowValue(provinceFlowValue - subsidyValue);
                }

                this.systemService.saveOrUpdate(acctFlowAccountEntity);  //更新商户账户
            }

            MyBeanUtils.copyBeanNotNull2Bean(redpacket, t);
            redpacketSetService.saveOrUpdate(t);
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信活动更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(PersonalRedpacketSetEntity redpacket, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        redpacket = systemService.getEntity(PersonalRedpacketSetEntity.class, redpacket.getId());
        message = "微信活动删除成功";
        try {
            redpacketSetService.delete(redpacket);
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信活动删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "queryHdByState")
    @ResponseBody
    public AjaxJson queryHdByState(PersonalRedpacketSetEntity redpacket, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        Map<String, Object> params = new HashMap<String, Object>();


        try {
            StringBuffer sbSql = new StringBuffer();
            sbSql.append("select count(*) as count from weixin_personalredpacketset p join weixin_commonforhd c where p.id=c.id and accountid='");
            sbSql.append(ResourceUtil.getWeiXinAccountId());
            sbSql.append("' and state='1' or state='2'");

            int count = redpacketSetService.getCount(sbSql.toString());
            if (count >= 1) {
                message = "目前有正在进行的活动，请先停止后再开始新的活动！";
                params.put("msg", "true");
                j.setSuccess(false);
                j.setMsg(message);
                j.setAttributes(params);
                return j;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        j.setSuccess(true);
        return j;

    }

    @RequestMapping(params = "stopHd")
    @ResponseBody
    public AjaxJson stopHd(HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        Map<String, Object> params = new HashMap<String, Object>();
        String id = request.getParameter("id");
        try {
            PersonalRedpacketSetEntity personalRedpacket = this.systemService.getEntity(PersonalRedpacketSetEntity.class, id);
            personalRedpacket.setState("0");

            //     查询商户的流量账户
            weixinAcctFlowAccountEntity weixinAcctFlowAccount = weixinAcctFlowAccoutService.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "accountId", personalRedpacket.getAccountid());

            Double leftRedpacketset = personalRedpacket.getSubsidyValue() - personalRedpacket.getFlowSendValue();  //剩余的红包后台制作的流量值
// 红包的剩余计量
//                        leftRecordSendFlow ll=new leftRecordSendFlow();
            Double sendFlowValue = null;
            try {
                sendFlowValue = leftRecordSendFlow.sendFlow(id, personalRedpacket.getAccountid());
            } catch (Exception e) {
                e.printStackTrace();
            }
//                        个人红包回收总数
            Double selfFlow = personalRedpacket.getFlowSendValue() - sendFlowValue;


            Double totalValue = leftRedpacketset + selfFlow;
            if (personalRedpacket.getFlowtype().equals(FlowType.country.getCode())) {
                weixinAcctFlowAccount.setCountryFlowValue(weixinAcctFlowAccount.getCountryFlowValue() + totalValue);
                this.systemService.saveOrUpdate(weixinAcctFlowAccount);
            } else {
                weixinAcctFlowAccount.setProvinceFlowValue(weixinAcctFlowAccount.getProvinceFlowValue() + totalValue);
                this.systemService.saveOrUpdate(weixinAcctFlowAccount);

            }
            /**
             * 保存的关于商户的流量币回收情况
             */
            FlowCardTradeRecordsEntity flowCardTradeRecordsEntity = new FlowCardTradeRecordsEntity();
            flowCardTradeRecordsEntity.setFlowValue(totalValue);
            flowCardTradeRecordsEntity.setFromAcc_id(personalRedpacket.getAccountid());
            flowCardTradeRecordsEntity.setToAcc_id(personalRedpacket.getAccountid());
            flowCardTradeRecordsEntity.setTradingDate(new Date());
            flowCardTradeRecordsEntity.setTradeType(MerchantFlowTradeType.fallback_redpacket.getCode());
            flowCardTradeRecordsService.save(flowCardTradeRecordsEntity);



            this.systemService.saveOrUpdate(personalRedpacket);
            message = "活动结束成功！";
            j.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            message = "活动结束失败！";
            j.setSuccess(false);
        }
        j.setMsg(message);
        j.setAttributes(params);
        return j;
    }

    /**
     * 微信活动列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "weixinRedPacketAddress")
    public ModelAndView weixinRedPacketAddress(HttpServletRequest request) {
        request.setAttribute("hdid", request.getParameter("hdid"));
        request.setAttribute("rooturl",ResourceUtil.getConfigByName("domain"));
        return new ModelAndView("weixin/personalredpacket/weixinRedPacketAddress");
    }

}

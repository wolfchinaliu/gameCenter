package weixin.tenant.controller;

import jodd.typeconverter.Convert;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSDocument;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.liuliangbao.jsonbean.FlowAccount.flowAccountBean;
import weixin.tenant.entity.FlowCardTradeRecordsEntity;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;
import weixin.tenant.service.FlowCardTradeRecordsServiceI;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.util.DataDictionaryUtil.FlowType;
import weixin.util.DataDictionaryUtil.MerchantFlowTradeType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by aa on 2015/12/1.
 */
@Controller
@RequestMapping("/weixinAcctFlowAccountController")
public class weixinAcctFlowAccountController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(weixinAcctFlowAccountController.class);

    /**
     * merchant charge service
     */
    @Autowired
    private WeixinAcctFlowAccoutServiceI weixinAcctFlowAccoutServiceI;
    @Autowired
    private SystemService systemService;
    @Autowired
    private WeixinAccountServiceI weixinAccountService;
    @Autowired
    private FlowCardTradeRecordsServiceI flowCardTradeRecordsServiceI;
    //    message reference
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * belong currAcct's accts  页面
     *
     * @return
     */
    @RequestMapping(params = "weixinAcctFlowAccount")
    public ModelAndView weixinAcctFlowAccount(HttpServletRequest request) {


        weixinAcctFlowAccountEntity acctFlowAccountEntity1 = null;
        if (StringUtil.isNotEmpty(ResourceUtil.getSessionUserName().getTenantId())) {
            String id = ResourceUtil.getSessionUserName().getTenantId();

//            acctFlowAccountEntity1 = weixinAcctFlowAccoutServiceI.getEntity(weixinAcctFlowAccountEntity.class, ResourceUtil.getSessionUserName().getTenantId());
            acctFlowAccountEntity1 = weixinAcctFlowAccoutServiceI.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "tenantId", id);
            if (null == acctFlowAccountEntity1) {
                acctFlowAccountEntity1 = new weixinAcctFlowAccountEntity();
            }
            if (acctFlowAccountEntity1.getCountryFlowValue() == null || acctFlowAccountEntity1.getCountryFlowValue().equals("")) {
                acctFlowAccountEntity1.setProvinceFlowValue(0.0);
            }
            if (acctFlowAccountEntity1.getProvinceFlowValue() == null || acctFlowAccountEntity1.getProvinceFlowValue().equals("")) {
                acctFlowAccountEntity1.setCountryFlowValue(0.0);
            }
            request.setAttribute("weixinAcctFlowChargePage", acctFlowAccountEntity1);

        }
        
        return new ModelAndView("weixin/tenant/weixinAcctFlowAccountList");
    }

    /**
     * 进入的是当前的用户
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "accCurrAccount")
    public ModelAndView accCurrAccount(HttpServletRequest request) {
        weixinAcctFlowAccountEntity acctFlowAccountEntity1 = new weixinAcctFlowAccountEntity();
        if (StringUtil.isNotEmpty(ResourceUtil.getSessionUserName().getTenantId())) {
            String id = ResourceUtil.getSessionUserName().getTenantId();

//            acctFlowAccountEntity1 = weixinAcctFlowAccoutServiceI.getEntity(weixinAcctFlowAccountEntity.class, ResourceUtil.getSessionUserName().getTenantId());
            acctFlowAccountEntity1 = weixinAcctFlowAccoutServiceI.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "tenantId", id);
            if (acctFlowAccountEntity1.getCountryFlowValue() == null) {
                acctFlowAccountEntity1.setCountryFlowValue(0.0);
            }
            if (acctFlowAccountEntity1.getProvinceFlowValue() == null) {
                acctFlowAccountEntity1.setProvinceFlowValue(0.0);
            }
            if(acctFlowAccountEntity1.getCountryFlowCardValue() == null){
                acctFlowAccountEntity1.setCountryFlowCardValue(0.0);
            }
            if(acctFlowAccountEntity1.getProvinceFlowCardValue()==null){
                acctFlowAccountEntity1.setProvinceFlowCardValue(0.0);
            }
            request.setAttribute("weixinAcctFlowChargePage", acctFlowAccountEntity1);

        }
        Map<String,Object> totalCharge = this.weixinAcctFlowAccoutServiceI.findTotalCharge(ResourceUtil.getWeiXinAcctId());
        request.setAttribute("totalCharge", totalCharge);
        return new ModelAndView("weixin/tenant/myFlowAccount");
    }

    /**
     * easyui AJAX 查询数据
     *
     * @param request
     * @param response
     * @param dataGrid
     */

    @RequestMapping(params = "datagrid")
    public void datagrid(flowAccountBean flowAccount, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        StringBuilder datagrid = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        datagrid.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志
//        CriteriaQuery cq = new CriteriaQuery(weixinAcctFlowAccountEntity.class, dataGrid);
//        //条件查询组合器
//        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, acctFlowAccountEntity, request.getParameterMap());
//
////        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinMerchantCoverAreaEntity, request.getParameterMap());
//        cq.eq("acct_id", ResourceUtil.getSessionUserName().getTenantId());
//
//        try {
//            //自定义查询条件
//        } catch (Exception e) {
//            throw new BusinessException(e.getMessage());
//        }
//        cq.add();
//        this.weixinAcctFlowAccoutServiceI.getDataGridReturn(cq, true);
//        此处可以写模糊查询

        String acctForName = request.getParameter("acctForName");  //商户名称
        String username = request.getParameter("username");//微信公众账号名称
        String Acclevel = request.getParameter("acctlevel");  //商户级别

        String belogAcct = request.getParameter("belogAcct");   //所属商户


        String ids = ResourceUtil.getSessionUserName().getTenantId();  //登录的商户id，也就是查询其下面的所有商户
        StringBuffer sql = new StringBuffer();
        sql.append("select m.id,a.acctForName,t.username,m.accountId,m.tenantId,a.acct_level,a.belogAcct,m.countryFlowValue,m.provinceFlowValue,m.countryFlowCardValue,m.provinceFlowCardValue,a.create_date from MerchantFlowAccount m join weixin_acct a on m.tenantId=a.id join t_s_base_user t on t.accountId = m.accountId where");
        sql.append(" m.acct_id=").append("'").append(ids).append("'");
        //       动态sql查询，商户账号名称
        if (acctForName != null && !"".equals(acctForName)) {
            sql.append(" and a.acctForName like").append("'%").append(acctForName).append("%'");
        }
        if (username != null && !"".equals(username)) {
            sql.append(" and t.username like").append("'%").append(username).append("%'");
        }
        //       动态sql查询，商户账号名称
        if (Acclevel != null && !"".equals(Acclevel)) {
            sql.append(" and a.acct_level=").append("'").append(Acclevel).append("'");
        }
        //       动态sql查询，商户账号名称
        if (belogAcct != null && !"".equals(belogAcct)) {
            sql.append(" and a.belogAcct like").append("'%").append(belogAcct).append("%'");
        }
        datagrid.append("当前执行的sql语句" + sql + "_");  //添加的日志

        HqlQuery hqlQuery = new HqlQuery(flowAccountBean.class, sql.toString(), dataGrid);
        PageList pageList = this.weixinAccountService.getPageListBySql(hqlQuery, false);

        datagrid.append("当前pageList查询的条数" + pageList + "_");  //添加的日志
        List<Object[]> list = pageList.getResultList();

        List<flowAccountBean> param = new ArrayList<flowAccountBean>();
        for (Object[] objects : list) {
            flowAccountBean wd = new flowAccountBean();
            Object gid = objects[0];
            if (gid != null) {
                wd.setId(gid.toString());
            }
            
            Object gname = objects[1];
            if (gname != null) {
                wd.setAcctForName(gname.toString());
            }
            Object gnames = objects[2];
            if (gnames != null) {
                wd.setusername(gnames.toString());
            }
            Object tcid = objects[3];
            if (tcid != null) {
                wd.setAccountId(tcid.toString());
            }
            Object tid = objects[4];
            if (tid != null) {
                wd.setTenantId(tid.toString());
            }
            Object level = objects[5];
            if (level != null) {
                wd.setAcctlevel(level.toString());
            }
            Object beact = objects[6];
            if (beact != null) {
                wd.setBelogAcct(beact.toString());
            }
            Object countryFlowValue = objects[7];
            if (countryFlowValue != null) {
                wd.setCountryFlowValue(Double.parseDouble(countryFlowValue.toString()));
            }
            Object provinceFlowValue = objects[8];
            if (provinceFlowValue != null) {
                wd.setProvinceFlowValue(Double.parseDouble(provinceFlowValue.toString()));
            }
            Object countryFlowcard = objects[9];
            if (countryFlowcard != null) {
                wd.setCountryFlowCardValue(Double.parseDouble(countryFlowcard.toString()));
            }
            Object provinceFlowCard = objects[10];
            if (provinceFlowCard != null) {
                wd.setProvinceFlowCardValue(Double.parseDouble(provinceFlowCard.toString()));
            }
            Object ctdate = objects[11];
            if (ctdate != null) {
                wd.setCreateDate(ctdate.toString());
            }
            param.add(wd);
        }
        dataGrid.setResults(param);
        dataGrid.setTotal(pageList.getCount());
        dataGrid.setPage(pageList.getCurPageNO());
        dataGrid.setRows(pageList.getOffset());
        TagUtil.datagrid(response, dataGrid);

        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        datagrid.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        datagrid.append("方法执行总的时间" + totaltime + "ms");  //添加的日志

        logger.info("weixinAcctFlowController的__datagrid__方法执行过程中的各个操作的输入输出参数以及结果_" + datagrid.toString());
//        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * charge page
     *
     * @return
     */
    @RequestMapping(params = "goCharge")
    public ModelAndView goCharge(weixinAcctFlowAccountEntity acctFlowAccountEntity, HttpServletRequest req) {
        StringBuilder goCharge = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        goCharge.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志


        weixinAcctFlowAccountEntity currAcct = new weixinAcctFlowAccountEntity();
        if (StringUtil.isNotEmpty(acctFlowAccountEntity.getId())) {
            currAcct = weixinAcctFlowAccoutServiceI.getEntity(weixinAcctFlowAccountEntity.class, acctFlowAccountEntity.getId());
            if (currAcct.getCountryFlowValue() != null && !currAcct.getCountryFlowValue().equals("")) {
                currAcct.setCountryFlowValue(currAcct.getCountryFlowValue());

            } else {
                currAcct.setCountryFlowValue(0.0);
            }
            goCharge.append("当前被商户的全国流量币_" + currAcct.getCountryFlowValue() + "_");  //添加的日志
            if (currAcct.getProvinceFlowValue() != null && !currAcct.getProvinceFlowValue().equals("")) {
                currAcct.setProvinceFlowValue(currAcct.getProvinceFlowValue());

            } else {
                currAcct.setProvinceFlowValue(0.0);
            }
            goCharge.append("当前被商户的省流量币_" + currAcct.getProvinceFlowValue() + "_");  //添加的日志
            req.setAttribute("weixinCurrAcctFlowChargePage", currAcct);
        }

        //定义获取的当前商户的流量币值
        weixinAcctFlowAccountEntity acctFlowAccountEntity1 = new weixinAcctFlowAccountEntity();
        if (StringUtil.isNotEmpty(ResourceUtil.getSessionUserName().getTenantId())) {
            String id = ResourceUtil.getSessionUserName().getTenantId();

//            acctFlowAccountEntity1 = weixinAcctFlowAccoutServiceI.getEntity(weixinAcctFlowAccountEntity.class, ResourceUtil.getSessionUserName().getTenantId());
            acctFlowAccountEntity1 = weixinAcctFlowAccoutServiceI.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "tenantId", id);
            goCharge.append("当前商户的流量币记录_" + acctFlowAccountEntity1 + "_");  //添加的日志
            req.setAttribute("weixinAcctFlowChargePage", acctFlowAccountEntity1);


        }
        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        goCharge.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        goCharge.append("方法执行总的时间" + totaltime + "ms");  //添加的日志

        logger.info("weixinAcctFlowAccountController的__goCharge__方法执行过程中的各个操作的输入输出参数以及结果_" + goCharge.toString());
        return new ModelAndView("weixin/tenant/weixinAcct-chargeFlow");
    }

    /**
     * charge do
     *
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(FlowCardTradeRecordsEntity flowCardTradeRecordsEntity, HttpServletRequest request) {
        StringBuilder doAdd = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        doAdd.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志
//获取当前的操作人
        TSUser tsUser=ResourceUtil.getSessionUserName();
        String username=tsUser.getUserName();
        AjaxJson j = new AjaxJson();

//        从页面的隐藏字段获取相应的数据
        String toacc_id = request.getParameter("toAcc_id"); //被充值商户id

        String fromacc_id = request.getParameter("fromAcc_id");  //充值商户id

        String flowValue = request.getParameter("flowValue"); //流量值

        String flowType = request.getParameter("flowtype"); //流量类型

        String countryValue = request.getParameter("countryFlowValue");

        String proValue = request.getParameter("provinceFlowValue");

        String fromAcc_tententid = request.getParameter("fromAcc_tententid");  //商户id
        String toAcc_tententid = request.getParameter("toAcc_tententid");  //商户id

        // 根据商户id查询商户的名称
        WeixinAcctEntity weixinAcctEntityFrom = this.systemService.get(WeixinAcctEntity.class, fromAcc_tententid);
        WeixinAcctEntity weixinAcctEntityTo = this.systemService.get(WeixinAcctEntity.class, toAcc_tententid);

        Double provinceFlowValueto=Double.parseDouble(request.getParameter("toAcc_provinceFlowValue"));
        Double countryFlowValueTo=Double.parseDouble(request.getParameter("toAcc_countryFlowValue"));
//        //当前以及充值商户的账户余额值
//        weixinAcctFlowAccountEntity weixinAcctFlowAccountFrom = this.systemService.get(weixinAcctFlowAccountEntity.class, fromacc_id);
//        weixinAcctFlowAccountEntity weixinAcctFlowAccountTo = this.systemService.get(weixinAcctFlowAccountEntity.class, toacc_id);



        doAdd.append("toacc_id" + toacc_id + "_");  //添加的日志
        doAdd.append("fromacc_id" + fromacc_id + "_");  //添加的日志
        doAdd.append("flowValue" + flowValue + "_");  //添加的日志
        doAdd.append("flowType" + flowType + "_");  //添加的日志
        doAdd.append("countryValue" + countryValue + "_");  //添加的日志
        doAdd.append("proValue" + proValue + "_");  //添加的日志

        if (flowValue == null || flowValue.equals("")) {
            j.setSuccess(false);
            j.setMsg("商户充值流量值不能为空");
            return j;
        }
//        String id = ResourceUtil.getSessionUserName().getTenantId();  //双重验证以避免值的问题
        //根据页面的传入判断流量值是否为空，不为空再进行判断，否则直接返回提示信息
        if (flowValue != null && !"".equals(flowValue)) {
//            weixinAcctFlowAccountEntity AcctFlowPcur = weixinAcctFlowAccoutServiceI.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "tenantId", id);
        	//Double provinceFlowValueto=Double.parseDouble(request.getParameter("toAcc_provinceFlowValue"));
            //Double countryFlowValueTo=Double.parseDouble(request.getParameter("toAcc_countryFlowValue"));
            if (FlowType.country.getCode().equals(flowType)) {
                Double counFlow = Double.parseDouble(countryValue);  //页面表单的值

                Double DflowValue = Double.parseDouble(flowValue);
              if(DflowValue > 0){
                if (DflowValue > counFlow) {
                    j.setSuccess(false);
                    j.setMsg("不能超过" + counFlow);
                    return j;
                }
              } else {
            	  Double NegativeValue = 0 - DflowValue;
            	  if (NegativeValue > countryFlowValueTo) {
                      j.setSuccess(false);
                      j.setMsg("扣除流量不能超过" + countryFlowValueTo);
                      return j;
                }
              }
            } else {
                Double counFlow = Double.parseDouble(proValue);

                Double DflowValue = Double.parseDouble(flowValue);
                if(DflowValue > 0){
                if (DflowValue > counFlow) {
                    j.setSuccess(false);
                    j.setMsg("不能超过" + counFlow);
                    return j;
                	}
                } else {
              	  Double NegativeValue = 0 - DflowValue;
              	  if (NegativeValue > provinceFlowValueto) {
                        j.setSuccess(false);
                        j.setMsg("扣除流量不能超过" + provinceFlowValueto);
                        return j;
                  }
                }	
            }
        } else {
            j.setSuccess(false);
            j.setMsg("流量数目不能为空，请填写");
            return j;
        }
        message = "商户充值添加成功";
        //充值商户实体
        //根据id进行获取
        weixinAcctFlowAccountEntity fromAccEn = weixinAcctFlowAccoutServiceI.getEntity(weixinAcctFlowAccountEntity.class, fromacc_id);

        doAdd.append("fromAccEn当前商户记录" + fromAccEn + "_");  //添加的日志
//        将当前商户的流量币值信息设置到request中，这样在前面可以使用el表达式进行获取
        //被充值商户id
//        根据id进行获取
        weixinAcctFlowAccountEntity toAccEn = weixinAcctFlowAccoutServiceI.getEntity(weixinAcctFlowAccountEntity.class, toacc_id);
        doAdd.append("toAccEn当前被充值商户记录" + toAccEn + "_");  //添加的日志
        Map<String, Object> params = new HashMap<String, Object>();
        //因为页面上显示的是获取数字，从而根据数字来判断是全国还是省内的，从而判断是应该充值到哪里
        try {
            //            1代表的是全国
            if (FlowType.country.getCode().equals(flowType)) {
                //被充值商户的全国流量币值是Ϊnull或者被充值商户的全国流量币值是“”ʽ
                if (toAccEn.getCountryFlowValue() != null && !toAccEn.getCountryFlowValue().equals("")) {
                    fromAccEn.setCountryFlowValue(fromAccEn.getCountryFlowValue() - Double.parseDouble(flowValue));
                    toAccEn.setCountryFlowValue(toAccEn.getCountryFlowValue() + Double.parseDouble(flowValue));
                    weixinAcctFlowAccoutServiceI.saveOrUpdate(fromAccEn);
                    weixinAcctFlowAccoutServiceI.saveOrUpdate(toAccEn);
                    //更新交易记录表
//                    flowCardTradeRecordsEntity.setTradingDate(new Date());
//                    flowCardTradeRecordsServiceI.save(flowCardTradeRecordsEntity);
                    doAdd.append("类型是全国--fromAccEn当前商户保存成功" + fromAccEn + "_");  //添加的日志
                    doAdd.append("类型是全国--toAccEn当前被充值商户保存成功" + toAccEn + "_");  //添加的日志
                    doAdd.append("类型是全国--流量币交易成功记录保存成功" + flowCardTradeRecordsEntity + "_");  //添加的日志
                    systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
                } else {
                    //被充值商户的省内流量币值是Ϊnull或者被充值商户的省内流量币值是“”ʽ
                    fromAccEn.setCountryFlowValue(fromAccEn.getCountryFlowValue() - Double.parseDouble(flowValue));
                    toAccEn.setCountryFlowValue(0.0 + Double.parseDouble(flowValue));
                    weixinAcctFlowAccoutServiceI.saveOrUpdate(fromAccEn);
                    weixinAcctFlowAccoutServiceI.saveOrUpdate(toAccEn);

//                    flowCardTradeRecordsEntity.setTradingDate(new Date());
//                    flowCardTradeRecordsServiceI.save(flowCardTradeRecordsEntity);
                    doAdd.append("类型是全国--fromAccEn当前商户保存成功" + fromAccEn + "_");  //添加的日志
                    doAdd.append("类型是全国--toAccEn当前被充值商户保存成功" + toAccEn + "_");  //添加的日志
                    doAdd.append("类型是全国--流量币交易成功记录保存成功" + flowCardTradeRecordsEntity + "_");  //添加的日志
                    systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
                }

            } else {
//                //            2代表的是省内
                if (toAccEn.getProvinceFlowValue() != null && !toAccEn.getProvinceFlowValue().equals("")) {
                    fromAccEn.setProvinceFlowValue(fromAccEn.getProvinceFlowValue() - Double.parseDouble(flowValue));
                    toAccEn.setProvinceFlowValue(toAccEn.getProvinceFlowValue() + Double.parseDouble(flowValue));
                    weixinAcctFlowAccoutServiceI.saveOrUpdate(fromAccEn);
                    weixinAcctFlowAccoutServiceI.saveOrUpdate(toAccEn);

//                    flowCardTradeRecordsEntity.setTradingDate(new Date());
//                    flowCardTradeRecordsServiceI.save(flowCardTradeRecordsEntity);
                    doAdd.append("类型是省--fromAccEn当前商户保存成功" + fromAccEn + "_");  //添加的日志
                    doAdd.append("类型是省--toAccEn当前被充值商户保存成功" + toAccEn + "_");  //添加的日志
                    doAdd.append("类型是省--流量币交易成功记录保存成功" + flowCardTradeRecordsEntity + "_");  //添加的日志
                    systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
                } else {
                    fromAccEn.setProvinceFlowValue(fromAccEn.getProvinceFlowValue() - Double.parseDouble(flowType));
                    toAccEn.setProvinceFlowValue(0.0 + Double.parseDouble(flowType));
                    weixinAcctFlowAccoutServiceI.saveOrUpdate(fromAccEn);
                    weixinAcctFlowAccoutServiceI.saveOrUpdate(toAccEn);
//                  设置交易日期
//                    flowCardTradeRecordsEntity.setTradingDate(new Date());
//                    flowCardTradeRecordsServiceI.save(flowCardTradeRecordsEntity);
                    doAdd.append("类型是省--fromAccEn当前商户保存成功" + fromAccEn + "_");  //添加的日志
                    doAdd.append("类型是省--toAccEn当前被充值商户保存成功" + toAccEn + "_");  //添加的日志
                    doAdd.append("类型是省--流量币交易成功记录保存成功" + flowCardTradeRecordsEntity + "_");  //添加的日志
                    systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
                }

            }
            //更新交易记录表
            flowCardTradeRecordsEntity.setTradingDate(new Date());
            flowCardTradeRecordsEntity.setTradeType(MerchantFlowTradeType.charge.getCode());
            //新增的

            if (flowType.equals(FlowType.country.getCode())) {
                flowType="1";
            }else {
                flowType="2";
            }
            /**
             * 为了优化交易记录表，增加详细的交易记录单
             */
            flowCardTradeRecordsEntity.setFlowtype(flowType);
            flowCardTradeRecordsEntity.setCurOperator(username);
            flowCardTradeRecordsEntity.setFlowSource("增加");
            //被充值商户的充值前的流量值
            flowCardTradeRecordsEntity.setFlowQChargeBerf(countryFlowValueTo==null?0.0 :countryFlowValueTo);
            flowCardTradeRecordsEntity.setFlowPChargeBerf(provinceFlowValueto==null?0.0 :provinceFlowValueto);
            //被充值商户的充值后的流量值
            flowCardTradeRecordsEntity.setFlowQCharged(toAccEn.getCountryFlowValue()==null?0.0 :toAccEn.getCountryFlowValue());
            flowCardTradeRecordsEntity.setFlowPCharged(toAccEn.getProvinceFlowValue()==null?0.0 :toAccEn.getProvinceFlowValue());
            //充值商户名字
            flowCardTradeRecordsEntity.setFromAccountname(weixinAcctEntityFrom.getAcctForName());
//                    被充值商户的名字
            flowCardTradeRecordsEntity.setToAccountname(weixinAcctEntityTo.getAcctForName());
            /**
             * 为了优化交易记录表，增加详细的交易记录单
             */
            flowCardTradeRecordsServiceI.save(flowCardTradeRecordsEntity);

            params.put("curAccount", fromAccEn);   //设置当前的商户显示
            j.setAttributes(params);

        } catch (Exception e) {
            e.printStackTrace();
            message = "商户充值失败";
            throw new BusinessException(e.getMessage());
        }

        j.setMsg(message);
        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        doAdd.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        doAdd.append("方法执行总的时间" + totaltime + "ms");  //添加的日志
        logger.info("weixinAcctFlowAccountController的__doAdd__方法执行过程中的各个操作的输入输出参数以及结果_" + doAdd.toString());
        return j;
    }


    /**
     * 检测新增的用户名是否已经存在
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "checkFlowValue")
    @ResponseBody
    public AjaxJson checkFlowValue(weixinAcctFlowAccountEntity weixinAcct, HttpServletRequest request) {

        AjaxJson j = new AjaxJson();

        //获取页面输入项
        String flowValue = request.getParameter("flowValue");

//流量类型
        String flowType = request.getParameter("flowtype"); //流量类型

        String id = ResourceUtil.getSessionUserName().getTenantId();
//根据页面的传入判断流量值是否为空，不为空再进行判断，否则直接返回提示信息
        if (flowValue != null && !"".equals(flowValue)) {
            weixinAcctFlowAccountEntity AcctFlowPcur = weixinAcctFlowAccoutServiceI.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "tenantId", id);
            if (FlowType.country.getCode().equals(flowType)) {
                Double counFlow = AcctFlowPcur.getCountryFlowValue();

                Double DflowValue = Double.parseDouble(flowValue);
                if (DflowValue > counFlow) {
                    j.setMsg("不能超过" + counFlow);
                    j.setSuccess(false);
                }
                return j;


            } else {
                Double counFlow = AcctFlowPcur.getProvinceFlowValue();

                Double DflowValue = Double.parseDouble(flowValue);
                if (DflowValue > counFlow) {
                    j.setMsg("不能超过" + counFlow);
                    j.setSuccess(false);
                }
                return j;
            }
        } else {
            j.setMsg("流量数目不能为空，请填写");
            return j;
        }


    }
}

package weixin.lottery.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.*;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.account.service.PageAuthCallback;
import weixin.guanjia.account.service.PageAuthHandler;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.liuliangbao.jsonbean.Riddles.RiddleBean;
import weixin.lottery.entity.WeixinGuessRiddleEntity;
import weixin.lottery.entity.WeixinGuessRiddleListEntity;
import weixin.lottery.entity.WeixinLanternRiddlesBankEntity;
import weixin.lottery.entity.WeixinriddleWinningListEntity;
import weixin.lottery.service.WeixinCommonforhdI;
import weixin.lottery.service.WeixinGuessRiddleServiceI;
import weixin.lottery.service.WeixinLanternRiddlesBankI;
import weixin.lottery.service.WeixinLotteryServiceI;
import weixin.member.controller.ConnectionsManager;
import weixin.tenant.entity.FlowCardTradeRecordsEntity;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;
import weixin.tenant.service.FlowCardTradeRecordsServiceI;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.util.DataDictionaryUtil.FlowType;
import weixin.util.DataDictionaryUtil.MerchantFlowTradeType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 晓娜 on 2016/1/20.
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinGuessRiddlesController")
public class WeixinGuessRiddlesController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(WeixinLotteryController.class);

    @Autowired
    private SystemService systemService;
    private String message;
    @Autowired
    private WeixinGuessRiddleServiceI weixinGuessRiddleService;
    @Autowired
    private WeixinLanternRiddlesBankI weixinLanternRiddlesBank;
    @Autowired
    private WeixinCommonforhdI weixinCommonforhd;
    @Autowired
    private WeixinAccountServiceI weixinAccountService;
    @Autowired
    private WeixinAcctFlowAccoutServiceI weixinAcctFlowAccoutService;
    @Autowired
    private FlowCardTradeRecordsServiceI flowCardTradeRecordsService;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * @return
     */
    @RequestMapping(params = "weixinRiddlesHdList")
    public ModelAndView weixinLottery(HttpServletRequest request) {
        request.setAttribute("lotteryType", request.getParameter("lotteryType"));
        return new ModelAndView("weixin/lottery/weixinRiddlesHdList");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(WeixinGuessRiddleEntity weixinGuessRiddle, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WeixinGuessRiddleEntity.class, dataGrid);
        weixinGuessRiddle.setAccountid(ResourceUtil.getWeiXinAccountId());
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinGuessRiddle, request.getParameterMap());
        cq.add();
        this.weixinGuessRiddleService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);

    }

    @RequestMapping(params = "datagridbysql")
    public void datagridbysql(WeixinGuessRiddleListEntity weixinGuessRiddle, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws ParseException {

        StringBuffer sql = new StringBuffer();
        sql.append("select new.* from (");
        sql.append("select q.id,q.title,q.starttime,q.endtime,w.allFlow,w.riddleFlow,w.getFlow,qw.num,w.state ,q.accountid ");
        sql.append("from weixin_commonforhd q join weixin_guessriddlehd w on q.id=w.id LEFT JOIN ");
        sql.append("(select tablea.* from ( ");
        sql.append("select count(*) as num ,c.accountid from  weixin_commonforhd c  ");
        sql.append("JOIN  weixin_member m on c.accountid=m.account_id and m.subscribe_time BETWEEN c.starttime AND c.endtime  ");
        sql.append("join weixin_riddlewinninglist r on m.open_id= r.openid and m.account_id=r.accountid ");
        sql.append("JOIN weixin_guessriddlehd g on c.id=g.id and c.accountid='");
        sql.append(ResourceUtil.getWeiXinAccountId());
        sql.append("'");
        sql.append(" group by c.accountid ");
        sql.append(")");
        sql.append("as tablea where tablea.accountid='");
        sql.append(ResourceUtil.getWeiXinAccountId());
        sql.append("')qw on q.accountid=qw.accountid");
        sql.append(" ) as new where new.accountid='");
        sql.append(ResourceUtil.getWeiXinAccountId()+"'");


        HqlQuery hqlQuery = new HqlQuery(WeixinGuessRiddleListEntity.class, sql.toString(), dataGrid);
        PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
        List<Object[]> list = pageList.getResultList();
        List<WeixinGuessRiddleListEntity> param = new ArrayList<WeixinGuessRiddleListEntity>();

        for (Object[] objects : list) {
            WeixinGuessRiddleListEntity wd = new WeixinGuessRiddleListEntity();
            Object gid = objects[0];
            if (gid != null) {
                wd.setId(gid.toString());
            }
            Object title = objects[1];
            if (title != null) {
                wd.setTitle(title.toString());
            }
            Object startTime = objects[2];
            if (startTime != null) {
                String start=startTime.toString();
                wd.setStartTime(start);
            }

            Object endTime = objects[3];
            if (endTime != null) {
                wd.setEndTime(endTime.toString());
            }

            Object allFlow = objects[4];
            if (allFlow != null) {
                wd.setAllFlow(Double.parseDouble(allFlow.toString()));
            }

            Object riddleFlow = objects[5];
            if (riddleFlow != null) {
                wd.setRiddleFlow(Double.parseDouble(riddleFlow.toString()));
            }
            Object getFlow = objects[6];
            if (getFlow != null) {
                wd.setGetFlow(Double.parseDouble(getFlow.toString()));
            }else  {
                wd.setGetFlow(0.0);
             }

            Object num = objects[7];
            if (num != null) {
                wd.setNum(Integer.valueOf(num.toString()));
            }else{
                wd.setNum(0);
            }
            Object state = objects[8];
            if (state != null) {
                wd.setState(state.toString());
            }
//            Object getsum = objects[9];
//            if (getsum != null) {
//                wd.setGetsum((Integer.valueOf(getsum.toString())));
//            }else{
//                wd.setGetsum(0);
//            }
//            Object membersum = objects[10];
//            if (membersum != null) {
//                wd.setMembersum((Integer.valueOf(membersum.toString())));
//            }else{
//                wd.setMembersum(0);
//            }


            param.add(wd);
        }
        dataGrid.setResults(param);
        dataGrid.setTotal(pageList.getCount());
        dataGrid.setPage(pageList.getCurPageNO());
        dataGrid.setRows(pageList.getOffset());
        TagUtil.datagrid(response, dataGrid);

    }



    /**
     * ΢goAdd
     *
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(WeixinGuessRiddleEntity weixinGuessRiddle, HttpServletRequest req) {
        String accountid = ResourceUtil.getWeiXinAccountId();
        Date starttime = new Date();
        String starttime1 = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(starttime);
        req.setAttribute("starttime", starttime1);
        req.setAttribute("accountid", accountid);
        return new ModelAndView("weixin/lottery/weixinRiddlehd-add");
    }

    /**
     * doAdd
     *
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(WeixinGuessRiddleEntity weixinGuessRiddle, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String accountid = request.getParameter("accountid");
        String start = request.getParameter("starttime");
        String endtime = request.getParameter("endtime");
        if (start == null || start.equals("")) {
            j.setSuccess(false);
            j.setMsg("开始时间不能为空");
            return j;
        }

        if (endtime == null || endtime.equals("")) {
            j.setSuccess(false);
            j.setMsg("结束时间不能为空");
            return j;
        }
        Date date = new Date();    //
        if (weixinGuessRiddle.getStarttime().getTime() >= weixinGuessRiddle.getEndtime().getTime()) {
            j.setSuccess(false);
            j.setMsg("开始时间必须小于结束时间");
            return j;
        }
        if (date.getTime() > weixinGuessRiddle.getEndtime().getTime()) {
            j.setMsg("结束时间不能小于当前的时间");
            j.setSuccess(false);
            return j;
        }
        if(weixinGuessRiddle.getAllFlow()<weixinGuessRiddle.getRiddleFlow()){
            j.setSuccess(false);
            j.setMsg("送出的流量总值必须大于每道题的流量值");
            return j;
        }

//        if()
        message = "猜灯谜活动创建成功";
        StringBuffer buffer=new StringBuffer();
        try {
//            weixinGuessRiddle.setAccountid(accountid);
//            weixinGuessRiddle.setState("1");
            if(date.getTime()>weixinGuessRiddle.getStarttime().getTime()){
                weixinGuessRiddle.setState("1");//设置为活动进行中
            }else {
                weixinGuessRiddle.setState("2");//设置活动为尚未开始。
            }
            weixinGuessRiddle.setGetFlow(0.0);

            weixinAcctFlowAccountEntity weixinAcctFlowAccount = new weixinAcctFlowAccountEntity();
            weixinAcctFlowAccount = weixinAcctFlowAccoutService.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "accountId", accountid);

            if (FlowType.country.getCode().equals(weixinGuessRiddle.getFlowtype())) {
                if(weixinAcctFlowAccount.getCountryFlowValue()<weixinGuessRiddle.getAllFlow()){
                    message = "商户流量不足";
                    j.setMsg(message);
                    j.setSuccess(false);
                    return j;
                }
                weixinGuessRiddleService.save(weixinGuessRiddle);
                weixinAcctFlowAccount.setCountryFlowValue(weixinAcctFlowAccount.getCountryFlowValue() - weixinGuessRiddle.getAllFlow());
                weixinAcctFlowAccoutService.saveOrUpdate(weixinAcctFlowAccount);
                buffer.append("dengmi_添加_全国流量_accountid="+accountid);
            } else {
                if(weixinAcctFlowAccount.getProvinceFlowValue()<weixinGuessRiddle.getAllFlow()){
                    message = "商户流量不足";
                    j.setMsg(message);
                    j.setSuccess(false);
                    return j;
                }
                weixinGuessRiddleService.save(weixinGuessRiddle);
                weixinAcctFlowAccount.setProvinceFlowValue(weixinAcctFlowAccount.getProvinceFlowValue() - weixinGuessRiddle.getAllFlow());
                weixinAcctFlowAccoutService.saveOrUpdate(weixinAcctFlowAccount);
                buffer.append("dengmi_添加_省内流量_accountid="+accountid);
            }

            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "猜灯谜活动创建失败";
            throw new BusinessException(e.getMessage());
        }
        logger.info(buffer.toString());
        j.setMsg(message);
        return j;
    }


    /**
     *
     *
     * @return
     */
    @RequestMapping(params = "riddleListPage")
    public ModelAndView riddleListPage(WeixinGuessRiddleEntity weixinGuessRiddle, HttpServletRequest request) {
//        将组id设置到request里面
        String hdid = weixinGuessRiddle.getId();
        request.setAttribute("id", hdid);
        String accountid = weixinGuessRiddle.getAccountid();
        try {
            weixinGuessRiddle = weixinGuessRiddleService.getEntity(WeixinGuessRiddleEntity.class, hdid);
            request.setAttribute("weixinGuessRiddle", weixinGuessRiddle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("weixin/lottery/RiddleListforHd");
    }

    @RequestMapping(params = "riddleList")
    public void riddleList(WeixinLanternRiddlesBankEntity weixinLanternRiddlesBank, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        String hdid = request.getParameter("id");

        CriteriaQuery cq = new CriteriaQuery(WeixinLanternRiddlesBankEntity.class, dataGrid);
        weixinLanternRiddlesBank.setAccountId(ResourceUtil.getWeiXinAccountId());
        weixinLanternRiddlesBank.setId("");
        weixinLanternRiddlesBank.setHdid(hdid);
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinLanternRiddlesBank, request.getParameterMap());
        cq.add();
        this.weixinLanternRiddlesBank.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 导入功能跳转（进入到导入功能的页面）
     *
     * @return
     */
    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        String hdid = req.getParameter("hdid");
        req.setAttribute("hdid", hdid);
        req.setAttribute("accountid", ResourceUtil.getWeiXinAccountId());
        req.setAttribute("username", ResourceUtil.getSessionUserName().getUserName());
        return new ModelAndView("weixin/lottery/weixinRiddlsUpload");
    }

    /**
     * 导出excel 使模板（下载模板的实现）
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public void exportXls(WeixinLanternRiddlesBankEntity weixinLanternRiddlesBank, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        StringBuffer buffer=new StringBuffer();
        buffer.append("dengmi_模板下载");
        try {
            codedFileName = "灯谜题库";
            // 根据浏览器进行转码，使其支持中文文件名
            if (BrowserUtils.isIE(request)) {
                response.setHeader(
                        "content-disposition",
                        "attachment;filename="
                                + java.net.URLEncoder.encode(codedFileName,
                                "UTF-8") + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"),
                        "ISO8859-1");
                response.setHeader("content-disposition",
                        "attachment;filename=" + newtitle + ".xls");
            }
            // 产生工作簿对象
            HSSFWorkbook workbook = null;
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("灯谜题库表", "导出人:" + ResourceUtil.getSessionUserName().getUserName(),
                    "导出信息"), WeixinLanternRiddlesBankEntity.class, null);

            fOut = response.getOutputStream();
            workbook.write(fOut);
            buffer.append("_success");
        } catch (Exception e) {
        } finally {
            try {
                fOut.flush();
                fOut.close();
                logger.info(buffer.toString());
            } catch (IOException e) {

            }
        }
    }

    /**
     * 将模板中填充数据后进行导入
     *
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(params = "importExcel", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
        StringBuffer buffer=new StringBuffer();
        buffer.append("dengmi_导入");
        String hdid = request.getParameter("hdid");
        buffer.append("_hdid="+hdid);
//        String username = request.getParameter("hdid");
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
                List<WeixinLanternRiddlesBankEntity> listWeixinLanternRiddlesBankEntitys =
                        (List<WeixinLanternRiddlesBankEntity>) ExcelImportUtil.importExcelByIs(file.getInputStream(), WeixinLanternRiddlesBankEntity.class, params);
                listWeixinLanternRiddlesBankEntitys.remove(0);

                boolean flag=true;
                if(flag==true){
                    for (WeixinLanternRiddlesBankEntity weixinLanternRiddlesBan : listWeixinLanternRiddlesBankEntitys) {
                        if(weixinLanternRiddlesBan.getLanternRdown().equals("")||weixinLanternRiddlesBan.getLanternRdown()==null||weixinLanternRiddlesBan.getLanternReyes()==null||weixinLanternRiddlesBan.getLanternReyes().equals("")||weixinLanternRiddlesBan.getLanternRon()==null||weixinLanternRiddlesBan.getLanternRon().equals("")){
                            flag=false;
                            buffer.append("_存在题目为空的情况,请重新导入完整的题目，文件导入失败");
                            j.setMsg("存在题目为空的情况,请重新导入完整的题目，文件导入失败！");
                            break;
                        }
                    }
                }
            if(flag==true){
                    for (WeixinLanternRiddlesBankEntity weixinLanternRiddlesBan : listWeixinLanternRiddlesBankEntitys) {
                        if (weixinLanternRiddlesBan.getLanternRon() != null && !weixinLanternRiddlesBan.getLanternRon().equals("")) {
                            weixinLanternRiddlesBan.setAccountId(ResourceUtil.getWeiXinAccountId());
                            weixinLanternRiddlesBan.setHdid(hdid);
                            weixinLanternRiddlesBan.setAddTime(new Date());
                            weixinLanternRiddlesBan.setOperator(ResourceUtil.getSessionUserName().getUserName());
                            weixinLanternRiddlesBank.save(weixinLanternRiddlesBan);

                        }
                    }
                    j.setMsg("文件导入成功！");
                    buffer.append("_success");
                }

            } catch (Exception e) {
                j.setMsg("文件导入失败！");
                logger.error(ExceptionUtil.getExceptionMessage(e));
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.info(buffer.toString());
        return j;

    }


    @RequestMapping(params = "stopHd")
    @ResponseBody
    public AjaxJson stopHd(HttpServletRequest request) {
        StringBuffer buffer=new StringBuffer();
        buffer.append("dengmi_");
        AjaxJson j = new AjaxJson();
        Map<String, Object> params = new HashMap<String, Object>();
        String id = request.getParameter("id");
        buffer.append("id="+id);
        try {
            WeixinGuessRiddleEntity weixinGuessRiddleEntity = this.systemService.getEntity(WeixinGuessRiddleEntity.class, id);
            weixinGuessRiddleEntity.setState("0");

            //     查询商户的流量账户
            weixinAcctFlowAccountEntity weixinAcctFlowAccount = weixinAcctFlowAccoutService.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "accountId", weixinGuessRiddleEntity.getAccountid());

            double flow=weixinGuessRiddleEntity.getGetFlow()==null ? 0 : weixinGuessRiddleEntity.getGetFlow();
            Double leftRiddleFlow = weixinGuessRiddleEntity.getAllFlow() - flow;  //剩余的猜灯谜的流量
            buffer.append("_addFlow="+leftRiddleFlow);


//            Double totalValue = leftRedpacketset + selfFlow;
            if (weixinGuessRiddleEntity.getFlowtype().equals(FlowType.country.getCode())) {
                weixinAcctFlowAccount.setCountryFlowValue(weixinAcctFlowAccount.getCountryFlowValue() + leftRiddleFlow);
                this.systemService.saveOrUpdate(weixinAcctFlowAccount);
                buffer.append("_flowType=quanguo");
            } else {
                weixinAcctFlowAccount.setProvinceFlowValue(weixinAcctFlowAccount.getProvinceFlowValue() + leftRiddleFlow);
                this.systemService.saveOrUpdate(weixinAcctFlowAccount);
                buffer.append("_flowType=shengnei");

            }
            /**
             * 保存的关于商户的流量币回收情况
             */
            // TODO 流量币领取记录需要重新做
            FlowCardTradeRecordsEntity flowCardTradeRecordsEntity = new FlowCardTradeRecordsEntity();
            flowCardTradeRecordsEntity.setFlowValue(leftRiddleFlow);
            flowCardTradeRecordsEntity.setFromAcc_id(weixinGuessRiddleEntity.getAccountid());
            flowCardTradeRecordsEntity.setToAcc_id(weixinGuessRiddleEntity.getAccountid());
            flowCardTradeRecordsEntity.setTradingDate(new Date());
            flowCardTradeRecordsEntity.setTradeType(MerchantFlowTradeType.fallback_riddle.getCode());
            flowCardTradeRecordsService.save(flowCardTradeRecordsEntity);


            this.systemService.saveOrUpdate(weixinGuessRiddleEntity);
            message = "活动结束成功！";
            j.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            message = "活动结束失败！";
            j.setSuccess(false);
        }
        logger.info(buffer.toString());
        j.setMsg(message);
        j.setAttributes(params);
        return j;
    }

    @RequestMapping(params = "checkRiddle")
    @ResponseBody
    public AjaxJson checkRiddle(HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        Map<String, Object> params = new HashMap<String, Object>();
        String id = request.getParameter("id");
        try {
           String accountid=ResourceUtil.getWeiXinAccountId();
           List<WeixinLanternRiddlesBankEntity> list = this.systemService.findHql("from WeixinLanternRiddlesBankEntity w where w.hdid = ? and w.accountId = ?", id, accountid);
            if(list.size()>0){
                message = "活动已添加题目！";
                j.setSuccess(true);
            }else{
                message = "活动没有添加题目！";
                j.setSuccess(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            message = "活动没有添加题目！";
            j.setSuccess(false);
        }
        j.setMsg(message);
        j.setAttributes(params);
        return j;
    }


    @RequestMapping(params = "queryHdByState")
    @ResponseBody
    public AjaxJson queryHdByState(WeixinGuessRiddleEntity riddleEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        Map<String, Object> params = new HashMap<String, Object>();


        try {
            StringBuffer sbSql = new StringBuffer();
            sbSql.append("select count(*) as count from weixin_guessriddlehd p join weixin_commonforhd c where p.id=c.id and accountid='");
            sbSql.append(ResourceUtil.getWeiXinAccountId());
            sbSql.append("' and state='1' or state='2'");

            int count = weixinGuessRiddleService.getCount(sbSql.toString());

            if (count >= 1) {
                message = "目前有正在进行或尚未开始的活动，请先停止后再创建新的活动！";
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

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(WeixinGuessRiddleEntity riddleEntity, HttpServletRequest req) throws Exception {
        if (StringUtil.isNotEmpty(riddleEntity.getId())) {
            riddleEntity = weixinGuessRiddleService.getEntity(WeixinGuessRiddleEntity.class, riddleEntity.getId());

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String starttime = format.format(riddleEntity.getStarttime());
            req.setAttribute("starttime", starttime);


//            req.setAttribute("leftflow", riddleEntity.getSubsidyValue() - riddleEntity.getFlowSendValue());

            req.setAttribute("riddleEntity", riddleEntity);
        }
        return new ModelAndView("weixin/lottery/weixinRiddlehd-update");

    }


    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(WeixinGuessRiddleEntity riddleEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
StringBuffer buffer =new StringBuffer();
        buffer.append("dengmi_update");
        String endtimeOld = request.getParameter("endtimeOld");
        String allFlowOld = request.getParameter("allFlowOld");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(endtimeOld);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (riddleEntity.getEndtime().getTime() < date.getTime()) {
            j.setSuccess(false);
            j.setMsg("结束时间只能延长！");
            return j;
        }

        if (Double.parseDouble(allFlowOld) > riddleEntity.getAllFlow()) {
            j.setSuccess(false);
            j.setMsg("总流量只能增加！");
            return j;
        }

        message = "微信活动更新成功";
        WeixinGuessRiddleEntity t = weixinGuessRiddleService.get(WeixinGuessRiddleEntity.class, riddleEntity.getId());
        try {

            if (Double.parseDouble(allFlowOld) < riddleEntity.getAllFlow()) {
                weixinAcctFlowAccountEntity acctFlowAccountEntity = new weixinAcctFlowAccountEntity();
                acctFlowAccountEntity = this.systemService.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "accountId", ResourceUtil.getWeiXinAccountId());
                buffer.append("_accountid="+ResourceUtil.getWeiXinAccountId());
                Double countryFlowValue = acctFlowAccountEntity.getCountryFlowValue();  //全国流量
                Double provinceFlowValue = acctFlowAccountEntity.getProvinceFlowValue();  //省内流量
                Double flowValue=riddleEntity.getAllFlow() - Double.parseDouble(allFlowOld);  //补贴流量

                if (t.getFlowtype().equals(FlowType.country.getCode())) {
                    if (flowValue > countryFlowValue) {
                        j.setSuccess(false);
                        j.setMsg("您的全国流量账户余额只有：" + countryFlowValue + "M,不够本次新增设置的流量值：" + flowValue + "M,请充值后再来！");
                        return j;
                    }
                    acctFlowAccountEntity.setCountryFlowValue(countryFlowValue - flowValue);
                } else {
                    if (flowValue > provinceFlowValue) {
                        j.setSuccess(false);
                        j.setMsg("您的省内流量账户余额只有：" + provinceFlowValue + "M,不够本次新增设置的流量值：" + flowValue + "M,请充值后再来！");
                        return j;
                    }
                    buffer.append("_flowValue="+flowValue);
                    acctFlowAccountEntity.setProvinceFlowValue(provinceFlowValue - flowValue);
                }

                this.systemService.saveOrUpdate(acctFlowAccountEntity);  //更新商户账户
            }

            MyBeanUtils.copyBeanNotNull2Bean(riddleEntity, t);
            weixinGuessRiddleService.saveOrUpdate(t);
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信活动更新失败";
            throw new BusinessException(e.getMessage());
        }
        logger.info(buffer.toString());
        j.setMsg(message);
        return j;
    }

    /**
     * 中奖纪录列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goPrizeRecord")
    public ModelAndView goPrizeRecord(HttpServletRequest request) {
        String hdId = request.getParameter("hdId");
        request.setAttribute("hdId", hdId);
        return new ModelAndView("weixin/lottery/RiddleWinningrecordList");
    }
    @RequestMapping(params = "datagridList")
    public void datagridList(WeixinriddleWinningListEntity weixinWinningrecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WeixinriddleWinningListEntity.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinWinningrecord, request.getParameterMap());
        try{
            //自定义追加查询条件
        }catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        this.weixinGuessRiddleService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }


    @RequestMapping(params = "weixinRiddleAddress")
    public ModelAndView weixinRiddleAddress(HttpServletRequest request) {
        request.setAttribute("hdid", request.getParameter("hdid"));
        request.setAttribute("rooturl",ResourceUtil.getConfigByName("domain"));
        return new ModelAndView("weixin/lottery/weixinRiddleAddress");
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel( HttpServletRequest request) {
        StringBuffer buffer=new StringBuffer();
        buffer.append("dengmi_");
        AjaxJson j = new AjaxJson();
        String id = request.getParameter("id");
        buffer.append("id=" + id);
        try {
            WeixinLanternRiddlesBankEntity riddlesBankEntity = systemService.getEntity(WeixinLanternRiddlesBankEntity.class, id);
            systemService.delete(riddlesBankEntity);
            message = "题目删除成功";
            j.setSuccess(true);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        }catch(Exception e){
            e.printStackTrace();
            message = "题目删除失败";
            j.setSuccess(false);
            throw new org.jeecgframework.core.common.exception.BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    @RequestMapping(params = "goUpdateRiddle")
    public ModelAndView goUpdateRiddle(WeixinLanternRiddlesBankEntity riddleEntity, HttpServletRequest req) throws Exception {
//        if (StringUtil.isNotEmpty(riddleEntity.getId())) {
//            riddleEntity = weixinGuessRiddleService.getEntity(WeixinLanternRiddlesBankEntity.class, riddleEntity.getId());
//
////            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////            String starttime = format.format(riddleEntity.getStarttime());
////            req.setAttribute("starttime", starttime);
////            req.setAttribute("leftflow", riddleEntity.getSubsidyValue() - riddleEntity.getFlowSendValue());
//
//            req.setAttribute("riddleEntity", riddleEntity);
//        }
        String id=req.getParameter("id");
        riddleEntity = weixinGuessRiddleService.getEntity(WeixinLanternRiddlesBankEntity.class, id);
        req.setAttribute("riddleEntity", riddleEntity);
        return new ModelAndView("weixin/lottery/weixinRiddlehd-updateRiddle");

    }


    @RequestMapping(params = "goAddRiddle")
    public ModelAndView goAddRiddle(HttpServletRequest req) {
        String accountid = ResourceUtil.getWeiXinAccountId();
        String id=req.getParameter("id").toString();
        req.setAttribute("hdid",id);
        req.setAttribute("accountid", accountid);
        return new ModelAndView("weixin/lottery/weixinRiddlehd-addRiddle");
    }

    /**
     * doAddRiddle
     *
     * @return
     */
    @RequestMapping(params = "doAddRiddle")
    @ResponseBody
    public AjaxJson doAddRiddle(WeixinLanternRiddlesBankEntity riddleEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String accountid = request.getParameter("accountid");
        String id=request.getParameter("hdid");
        message = "添加题目成功";
        StringBuffer buffer=new StringBuffer();
        try {
            riddleEntity.setAccountId(accountid);
            riddleEntity.setHdid(id);
            riddleEntity.setAddTime(new Date());
            systemService.save(riddleEntity);

            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "添加题目失败";
            throw new BusinessException(e.getMessage());
        }
        logger.info(buffer.toString());
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdateRiddle")
    @ResponseBody
    public AjaxJson doUpdateRiddle(WeixinLanternRiddlesBankEntity riddleEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        StringBuffer buffer =new StringBuffer();
        buffer.append("dengmi_update");

        message = "题目更新成功";
        WeixinLanternRiddlesBankEntity t = systemService.get(WeixinLanternRiddlesBankEntity.class, riddleEntity.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(riddleEntity, t);
        } catch (Exception e) {
            message = "题目更新失败";
            e.printStackTrace();
        }
        systemService.saveOrUpdate(t);

        logger.info(buffer.toString());
        j.setMsg(message);
        return j;
    }


}

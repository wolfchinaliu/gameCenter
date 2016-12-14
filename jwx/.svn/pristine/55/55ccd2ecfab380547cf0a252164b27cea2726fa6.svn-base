package weixin.liuliangbao.flowcard.Controller;


import com.google.gson.Gson;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.liuliangbao.flowcard.Entity.FlowCardBatchEntity;
import weixin.liuliangbao.flowcard.Entity.FlowCardInfoEntity;
import weixin.liuliangbao.flowcard.Entity.FlowCardTaskEntity;
import weixin.liuliangbao.flowcard.Service.FlowCardBatchServiceI;
import weixin.liuliangbao.flowcard.Service.FlowCardInfoServiceI;
import weixin.liuliangbao.flowcard.Service.FlowCardTaskServiceI;
import weixin.liuliangbao.jsonbean.FlowCard.extractionCodeBean;
import weixin.liuliangbao.jsonbean.FlowCard.findExtractionCode;
import weixin.liuliangbao.util.FlowCardBatchInsert;
import weixin.liuliangbao.util.ZipUtil;
import weixin.manual.entity.ManualGivenRecords;
import weixin.member.controller.ConnectionsManager;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.tenant.service.WeixinAcctServiceI;
import weixin.util.DataDictionaryUtil.FlowType;
import weixin.util.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by aa on 2015/12/18.
 */
@Scope("prototype")
@Controller
@RequestMapping("/flowCardInfoController")
public class FlowCardInfoController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger LOGGER = Logger.getLogger(FlowCardInfoController.class);

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Autowired
    private SystemService systemService;
    @Autowired
    private FlowCardInfoServiceI flowCardInfoService;
    @Autowired
    private WeixinAcctServiceI weixinAcctService;
    @Autowired
    private FlowCardBatchServiceI flowCardBatchService;
    @Autowired
    private WeixinAccountServiceI weixinAccountService;
    @Autowired
    private WeixinAcctFlowAccoutServiceI weixinAcctFlowAccoutService;

    @Autowired
    private FlowCardTaskServiceI flowCardTaskService;

    /**
     * 显示商户和组列表
     *
     * @return
     */
    @RequestMapping(params = "flowCardquery")
    public ModelAndView flowCardquery(HttpServletRequest request) {
        return new ModelAndView("liuliangbao/flowcard/flowCardInfoList");
    }

    /**
     * 我的流量卡信息
     *
     * @param flowCardInfo
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "flowcardatagrid")
    public void flowcardatagrid(FlowCardInfoEntity flowCardInfo, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {

        CriteriaQuery cq = new CriteriaQuery(FlowCardInfoEntity.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, flowCardInfo, request.getParameterMap());
//		根据条件进行查询
//		cq.eq("id", ResourceUtil.getSessionUserName().getTenantId());//根据公众ID进行数据隔离
        cq.eq("belongAcctId", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离，根据登录的商户id进行查询

        try {
            //自定义追加查询条件
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();

        this.flowCardInfoService.getDataGridReturn(cq, true);
//       List<FlowCardInfoEntity> lisInfo= cq.getDataGrid().getResults();
//        for (int i = 0; i <lisInfo.size(); i++) {
//            String time1=lisInfo.get(i).getBeginDate().toString();
//            String time2=time1.substring(0,time1.length()-2);
//            lisInfo.get(i).setBeginDate1(time2);
//        }
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 制作流量卡
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAddFlowCard(HttpServletRequest request) throws Exception {
        // 获取当前商户的编码
        String accId = ResourceUtil.getSessionUserName().getTenantId();

        WeixinAcctEntity weixinAcctEntity = weixinAcctService.getEntity(WeixinAcctEntity.class, accId);
        // 将商户的编码设置到request里面
        String acctCode = weixinAcctEntity.getAcctCode();
        request.setAttribute("acctCode", acctCode);
        // 批次号
        String batchT = null;

        java.util.Date d = new java.util.Date();

        String ss = DateUtils.date_sdf.format(d);
        String ymd = DateUtils.yyyyMMdd.format(d);

        // 查询数据库看最新一次的制卡，在其基础上加一
        String sql = "select * from FlowCardBatch where date_format(createTime,'%Y-%m-%d')='" + ss + "' ORDER BY batchNo DESC LIMIT 0,1 ";

        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;
        String batchNo = null;
        Date ct = null;
        String s = DateUtils.date_sdf.format(d);;
        try {
            // 创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();

            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            while (es.next()) {
                batchNo = es.getString("batchNo");
                ct = es.getDate("createTime");
            }
            if (ct != null && batchNo != null) {
                String ct1 = DateUtils.date_sdf.format(ct);
                int i = s.compareTo(ct1);
                if (i > 0) {
                    batchT = acctCode + ymd + "0001";
                } else {
                    String sub1 = batchNo.substring(0, batchNo.length() - 4);
                    String subC = batchNo.substring(batchNo.length() - 4, batchNo.length());// 截取两个数字之间的部分

                    Integer count1 = Integer.parseInt(subC) + 1;
                    String count = new DecimalFormat("0000").format(count1);
                    batchT = sub1+count;
                }
            } else {
                batchT = acctCode + ymd + "0001";
            }
            request.setAttribute("batchT", batchT);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        return new ModelAndView("liuliangbao/flowcard/CardmadePage");
    }

    /**
     * 检测流量是否足够
     *
     * @return
     */
    @RequestMapping(params = "checkAcctFlow")
    @ResponseBody
    public String checkAcctFlow(String flowType, Double flowToal) throws Exception {

        AjaxJson j = new AjaxJson();

        String mes = null;

//        根据流量类型和商户id查询是否还有流量，是否大于了所剩余的值
        String accountid = ResourceUtil.getWeiXinAccountId();


        String sql = "select * from MerchantFlowAccount where accountId='" + accountid + "'";

        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;
//        String valueSub = null;
        String countValue = null;
        String provinceValue = null;

        try {
//            创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();

            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            while (es.next()) {
                countValue = es.getString("countryFlowValue");
                provinceValue = es.getString("provinceFlowValue");
            }
            Double dvalue = Double.parseDouble(countValue);
            Double dprovinceValue = Double.parseDouble(provinceValue);
            if (FlowType.country.getCode().equals(flowType)) {
                if (flowToal > dvalue) {
                    mes = "您制卡需" + flowToal + " M流量,当前剩余" + dvalue + "M,您的流量不足，请充值或更改制卡数量";
                } else {
                    mes = "true";
                }
            } else {
                if (flowToal > dprovinceValue) {
                    mes = "您制卡需" + flowToal + "+ M流量，当前剩余" + dprovinceValue + "M，您的流量不足，请充值或更改制卡数量";
                } else {
                    mes = "true";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        return mes;
    }

    /**
     * 查询制卡的次数，不能超过1000次某个商户的制卡次数
     *
     * @return
     */
    public int makeCardsTimes() throws Exception {
        int cardTimes = 0;
        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;

        String count = null;
        String sql = "select count(acctId) as totalTimes from flowcardbatch where  date(createTime)=curdate() and acctId='" + ResourceUtil.getWeiXinAccountId() + "'";
        try {
//            创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();

            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            while (es.next()) {
                count = es.getString("totalTimes");
            }
            cardTimes = Integer.parseInt(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        return cardTimes;
    }

    /**
     * 每天的制卡张数不能查过1000000张
     *
     * @return
     * @throws Exception
     */
    public int makeCardsNum() throws Exception {
        int cardsNum = 0;
        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;

        String count = null;
        String sql = "select sum(putFlowCardCount) as totalNum  from flowcardbatch where  date(createTime)=curdate() and acctId='" + ResourceUtil.getWeiXinAccountId() + "'";
        try {
//            创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();

            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            while (es.next()) {
                count = es.getString("totalNum");
            }
            cardsNum = StringUtils.isBlank(count)?0:Integer.parseInt(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        return cardsNum;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(FlowCardBatchEntity flowCardBatchEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AjaxJson j = new AjaxJson();
        String accid = ResourceUtil.getWeiXinAccountId();  //获取当前的商户id
        final String typeFlow = request.getParameter("type");  //流量类型
        String cardPrefix = request.getParameter("cardPrefix");  //制卡前缀就是商户的编码
        String batchCode = request.getParameter("batchNo");  //批次号
        final String cardCode = request.getParameter("cardCode");  //制卡编号:
        String putFlowCardCount = request.getParameter("putFlowCardCount");  //每次制卡的张数
        String putFlowTotal = request.getParameter("putFlowTotal");  //制卡流量
//        String stausLock = request.getParameter("staus");  //流量卡的状态，激活还是锁定
        Calendar disabledTime = Calendar.getInstance();
        disabledTime.setTime(flowCardBatchEntity.getDisabledDate());
        disabledTime.set(Calendar.HOUR_OF_DAY, 23);
        disabledTime.set(Calendar.MINUTE, 59);
        disabledTime.set(Calendar.SECOND, 59);
        
        Calendar enabledTime = Calendar.getInstance();
        enabledTime.setTime(flowCardBatchEntity.getEnableDate());
        enabledTime.set(Calendar.HOUR_OF_DAY, 0);
        enabledTime.set(Calendar.MINUTE, 0);
        enabledTime.set(Calendar.SECOND, 0);
        final String accountId = ResourceUtil.getWeiXinAccountId();
        final Double dputFlow = Double.parseDouble(putFlowTotal);  //制卡单张量
        int ICount = Integer.parseInt(putFlowCardCount);  //制卡的张数
        Double Total = dputFlow * ICount; //制卡的总流量
        /**
         * 查询商户剩余流量
         */
        weixinAcctFlowAccountEntity flowc = this.systemService.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "accountId", ResourceUtil.getWeiXinAccountId());
        if(dputFlow<=0){
            j.setSuccess(false);
            j.setMsg("制卡流量非法");
            return j;
        }
        if(ICount<=0){
            j.setSuccess(false);
            j.setMsg("制卡数量非法");
            return j;
        }
        /**
         * 制卡张数的限制
         */
        if (ICount > 10000) {
            j.setSuccess(false);
            j.setMsg("每次制卡的数目不能超过10000张！");
            return j;
        }

        /**
         * 开始时间均不能为空
         */
        if (flowCardBatchEntity.getEnableDate() == null || flowCardBatchEntity.getEnableDate().equals("")) {
            j.setSuccess(false);
            j.setMsg("请填写开始时间");
            return j;
        }

        /**
         * 结束时间均不能为空
         */
        if (flowCardBatchEntity.getDisabledDate() == null || flowCardBatchEntity.getDisabledDate().equals("")) {
            j.setSuccess(false);
            j.setMsg("请填写结束时间");
            return j;
        }
        /**
         * 制卡的有效期必须是结束日期不能小于开始日期
         */
        if (Integer.parseInt(DateUtils.yyyyMMdd.format(disabledTime.getTime())) < Integer.parseInt(DateUtils.yyyyMMdd.format(enabledTime.getTime()))) {
            j.setSuccess(false);
            j.setMsg("结束时间应该大于开始时间！");
            return j;
        }
        /**
         * 在制卡之前先进行判断，流量是否足够以及制作的批次，以及当天制作的张数
         * 调用方法判断流量是否充足,不充足的时候直接往上返回即可
         */
        String mes = this.checkAcctFlow(typeFlow, Total);
        if (!("true" == mes)) {
            if (typeFlow.equals(FlowType.province.getCode())) {
                j.setSuccess(false);
                j.setMsg("您制卡需要" + Total + "M流量，当前商户剩于" + flowc.getProvinceFlowValue() + "M,商户流量不足，请充值或者更改制卡数量");
                return j;
            } else {
                j.setSuccess(false);
                j.setMsg("您制卡需要" + Total + "M流量，当前商户剩于" + flowc.getCountryFlowValue() + "M,商户流量不足，请充值或者更改制卡数量");
                return j;
            }
        }
        /**
         * 查看当天的制卡次数是否已经超过了1000次了
         */
        int count = this.makeCardsTimes();
        if (count >= 1000) {
            j.setSuccess(false);
            j.setMsg("您每天最多可制作1000批卡片，您今天的次数已用完，请于明天制作");
            return j;
        }
        /**
         * 制卡张数每天的限制
         */
        int cardNum = this.makeCardsNum();
        if (cardNum+ICount > 10000000) {
            j.setSuccess(false);
            j.setMsg("您每天最多可制作10000000张卡片，今天还可制作"+(10000000-ICount)+"张卡");
            return j;
        }
        /**
         * 制卡总量
         * 根据spring的特性，是可以自动获取到相应的实体的，只要对应相应的实体字段即可
         */
        //将日期进行格式化为字符串
        String sdate = (new SimpleDateFormat("yyyy-MM-dd")).format(flowCardBatchEntity.getDisabledDate());
        message = "成功制卡" + ICount + "张，流量总共" + Total + "M,有效期至" + sdate + "";
        flowCardBatchEntity.setDisabledDate(disabledTime.getTime());
        flowCardBatchEntity.setEnableDate(enabledTime.getTime());
        final FlowCardTaskEntity flowCardTaskEntity = new FlowCardTaskEntity();
        try {
//            初始状态设为0表示线程开始；
            flowCardTaskEntity.setStatusValue("0");
            flowCardTaskService.save(flowCardTaskEntity);
//            当流量类型是全国的时候
            if (FlowType.country.getCode().equals(typeFlow)) {
                flowCardBatchEntity.setFlowType(typeFlow);
                flowCardBatchEntity.setCreateTime(new java.util.Date());
                flowCardBatchEntity.setPutFlowTotal(Total);
                flowCardBatchEntity.setAcctId(accid);
                flowCardBatchEntity.setIsValid("是");  //"是"表示尚未过期
                flowCardBatchService.save(flowCardBatchEntity);

                weixinAcctFlowAccountEntity glow = weixinAcctFlowAccoutService.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "accountId", accid);
//当制卡成功后此时需要将商户的账户中减去一定的量，并且将流量卡的值设到里面,也就是更新流量账户
                glow.setCountryFlowValue(glow.getCountryFlowValue() - Total);
                glow.setCountryFlowCardValue(glow.getCountryFlowCardValue()+Total);
                weixinAcctFlowAccoutService.saveOrUpdate(glow);
            } else {
                flowCardBatchEntity.setFlowType(typeFlow);
                flowCardBatchEntity.setCreateTime(new java.util.Date());
                flowCardBatchEntity.setPutFlowTotal(Total);     //流量类型是本省的时候
                flowCardBatchEntity.setAcctId(accid);
                flowCardBatchEntity.setIsValid("是");
                flowCardBatchService.save(flowCardBatchEntity);

                weixinAcctFlowAccountEntity glow = weixinAcctFlowAccoutService.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "accountId", accid);

                glow.setProvinceFlowValue(glow.getProvinceFlowValue() - Total);
                glow.setProvinceFlowCardValue(glow.getProvinceFlowCardValue()+Total);
                weixinAcctFlowAccoutService.saveOrUpdate(glow);
            }

//此时当流量卡批次添加成功后，再将其添加到流量卡的信息中
            final FlowCardBatchEntity flowEntity = flowCardBatchService.findUniqueByProperty(FlowCardBatchEntity.class, "batchNo", flowCardBatchEntity.getBatchNo());
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
//            制卡编码插入到流量卡表中
            int num = flowCardBatchEntity.getPutFlowCardCount();
//            String str = "http://xiaona.ngrok.natapp.cn/jwx/flowCardController.do?load&extractionCode=";
//            Double flowvalue = flowEntity.getPutFlowTotal() / flowEntity.getPutFlowCardCount();
//            根据num查询提取码list
            final List<extractionCodeBean> lis = findExtractionCode.find(num);    //读取另一张表来获取到相应的提取码以及提取码的数目
//读取二维码的生成路径，从配置文件的sysConfig.properties中读取
//            String urlprefix = ResourceUtil.getConfigByName("QRcode.storage.path");
            /**
             * 使用线程使得其可以直接显示制卡成功，这样的话可以使得线程在后台进行即可
             */
            Thread t = new InsertThread(lis, flowEntity, flowCardTaskEntity, cardCode, dputFlow);
            t.start();

        } catch (Exception e) {
//            状态值为2表示线程异常
            flowCardTaskEntity.setStatusValue("2");
            flowCardTaskService.saveOrUpdate(flowCardTaskEntity);
            e.printStackTrace();
            message = "制卡失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 对于流量卡的批量添加，我们采用的是线程进行添加，不能长时间停留在这个地方
     */
    public class InsertThread extends Thread {
        //        (flowEntity,flowCardTaskEntity,cardCode,dputFlow,typeFlow)
        private List<extractionCodeBean> lis;
        private FlowCardBatchEntity flowEntity;
        private FlowCardTaskEntity flowCardTaskEntity;
        private String cardCode;
        private Double dputFlow;

        public InsertThread(List<extractionCodeBean> lis, FlowCardBatchEntity flowEntity, FlowCardTaskEntity flowCardTaskEntity, String cardCode, Double dputFlow) {
            this.lis = lis;
            this.flowEntity = flowEntity;
            this.flowCardTaskEntity = flowCardTaskEntity;
            this.cardCode = cardCode;
            this.dputFlow = dputFlow;
        }

        public void run() {
            LOGGER.info("entering....");
            try {
                FlowCardBatchInsert.AddFlowCardBatchInfo(flowCardTaskService, flowCardInfoService, systemService, lis, flowEntity, flowCardTaskEntity, cardCode, dputFlow);
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
    }

    /**
     * 流量卡的下发现在不需要了，因为所有的人都是可以制作流量卡的
     *
     * @param acctFlowAccountEntity
     * @param req
     * @return
     */
    @RequestMapping(params = "goIncrease")
    public ModelAndView goIncrease(weixinAcctFlowAccountEntity acctFlowAccountEntity, HttpServletRequest req) {
        //显示所有的批次号和需要的张数，以及商户下属的商户
        String hql = "from FlowCardInfoEntity where acctId='" + ResourceUtil.getWeiXinAccountId() + "'";

        List<FlowCardInfoEntity> lis = new ArrayList<FlowCardInfoEntity>();
        lis = this.systemService.findHql(hql, (Object[]) null);
        Gson gson = new Gson();
//        String lisgson = gson.toJson(lis);
        req.setAttribute("lis", lis);

        String hql0 = "from weixinAcctFlowAccountEntity where acct_id='" + ResourceUtil.getSessionUserName().getTenantId() + "'";

        List<weixinAcctFlowAccountEntity> liRb = new ArrayList<weixinAcctFlowAccountEntity>();
        liRb = this.systemService.findHql(hql0, null);
//        String lisgson2 = gson.toJson(liRb);
        req.setAttribute("liRb", liRb);
        return new ModelAndView("liuliangbao/flowcard/CardchargePage");
    }

    public AjaxJson doCharge(FlowCardBatchEntity flowCardBatchEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AjaxJson j = new AjaxJson();

        return j;
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(params = "setTimeoutData")
    @ResponseBody
    public AjaxJson setTimeoutData(HttpServletRequest request) {

        AjaxJson j = new AjaxJson();
        String hql = "from FlowCardTaskEntity";
        List<FlowCardTaskEntity> lis = new ArrayList<FlowCardTaskEntity>();
        String statusCode = null;
        lis = this.systemService.findHql(hql, null);

        if (lis.size() > 0) {
            statusCode = lis.get(0).getStatusValue();
            if ("0".equals(statusCode)) {
                j.setMsg("开始插入");
            } else if ("1".equals(statusCode)) {
                j.setMsg("插入成功");
            } else {
                j.setMsg("插入失败");
            }
        } else {
            j.setMsg("");
        }

        return j;
    }

    /**
     * 激活当前的状态为锁定的流量卡
     *
     * @param QRcode
     * @param cardNumber
     * @param flowValue
     * @param beginDate
     * @param endDate
     * @param isValid
     * @param isUse
     * @param statusLock
     * @param countChecked
     * @param checkboxCount
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "jihuoState")
    @ResponseBody
    public AjaxJson jihuoState(String ids) throws Exception {
        AjaxJson a = new AjaxJson();
        StringBuffer sbHql = new StringBuffer();
        sbHql.append("from FlowCardInfoEntity t where t.belongAcctId='" + ResourceUtil.getWeiXinAccountId() + "' and t.statusLock='锁定'");

        if(StringUtils.length(ids)>1){
            sbHql.append(" and t.id in ("+ids.substring(0,ids.length()-1)+")");
        }
        List<FlowCardInfoEntity> entities = flowCardInfoService.findHql(sbHql.toString(), null);

        try {
            for (int i = 0; i < entities.size(); i++) {
                entities.get(i).setStatusLock("激活");
            }
            flowCardInfoService.batchSave(entities);
            a.setMsg("流量卡激活成功");
        } catch (Exception e) {
            a.setMsg("流量卡激活失败");
            throw new BusinessException(e.getMessage());
        }
        return a;
    }

    /**
     * 锁定当前的状态为激活的流量卡
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "suodingState")
    @ResponseBody
    public AjaxJson suodingState(String ids) throws Exception {
        AjaxJson a = new AjaxJson();
        StringBuffer sbHql = new StringBuffer();
        sbHql.append("from FlowCardInfoEntity t where t.belongAcctId='" + ResourceUtil.getWeiXinAccountId() + "' and t.statusLock='激活'");
        if(StringUtils.length(ids)>1){
            sbHql.append(" and t.id in ("+ids.substring(0,ids.length()-1)+")");
        }
        List<FlowCardInfoEntity> entities = flowCardInfoService.findHql(sbHql.toString(), null);
        try {
            for (int i = 0; i < entities.size(); i++) {
                entities.get(i).setStatusLock("锁定");
            }
            flowCardInfoService.batchSave(entities);
            a.setMsg("流量卡锁定成功");
        } catch (Exception e) {
            a.setMsg("流量卡锁定失败");
            throw new BusinessException(e.getMessage());
        }
        return a;
    }

    /**
     * 将流量卡进行锁定（已废弃的方法）
     *
     * @return
     */
    @RequestMapping(params = "doBatchAdd")
    @ResponseBody
    public AjaxJson doBatchAdd(String ids, HttpServletRequest request) {

//        String groupId = request.getParameter("id");
        AjaxJson j = new AjaxJson();
        message = "";
        int succeed = 0;
        int error = 0;
        try {
            for (String id : ids.split(",")) {
                FlowCardInfoEntity flowCardInfoEntity = new FlowCardInfoEntity();
                flowCardInfoEntity = flowCardInfoService.getEntity(FlowCardInfoEntity.class, id);
//                List<FlowCardInfoEntity> lisMG = this.flowCardInfoService.findByProperty(FlowCardInfoEntity.class, "acctId", id);
//                if (lisMG.size() > 0) {
//
//                }
                flowCardInfoEntity.setStatusLock("激活");
//
                flowCardInfoService.saveOrUpdate(flowCardInfoEntity);
                succeed += 1;
                systemService.addLog(message, Globals.Log_Type_DEL,
                        Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            error += 1;
            message = "流量卡激活失败";
            throw new BusinessException(e.getMessage());
        }
        message = "流量卡激活成功" + succeed + "张，失败" + error + "张";
        j.setMsg(message);
        return j;
    }

    /**
     * 将流量卡进行锁定（已废弃的方法）
     *
     * @return
     */
    @RequestMapping(params = "doBatchAddTwo")
    @ResponseBody
    public AjaxJson doBatchAddTwo(String ids, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "";
        int succeed = 0;
        int error = 0;
        try {
            for (String id : ids.split(",")) {
                FlowCardInfoEntity flowCardInfoEntity = new FlowCardInfoEntity();
                flowCardInfoEntity = flowCardInfoService.getEntity(FlowCardInfoEntity.class, id);
                flowCardInfoEntity.setStatusLock("锁定");
//
                flowCardInfoService.saveOrUpdate(flowCardInfoEntity);
                succeed += 1;
                systemService.addLog(message, Globals.Log_Type_DEL,
                        Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            error += 1;
            message = "流量卡锁定失败";
            throw new BusinessException(e.getMessage());
        }
        message = "流量卡锁定成功" + succeed + "张，失败" + error + "张";
        j.setMsg(message);
        return j;
    }

    /**
     * 将显示在网页上的二维码，把用户选中的二维码下载到指定位置
     *
     * @param QRcode
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "goQRcode1")
    @ResponseBody
    public AjaxJson goQRcode1(String QRcode, String cardNumber, Double flowValue, java.util.Date beginDate,
                             java.util.Date endDate, String isValid, String isUse, String statusLock, int countChecked, int checkboxCount) throws Exception {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();

        builder.append("flowCardInfoController_goQRcode");
        AjaxJson a = new AjaxJson();
        String fromPath = "";//图片来源
        String zipFile = "";//存放压缩文件的地址
        String zipUrl = "";//下载该压缩文件夹的地址
        List<String> pictureName = new ArrayList<String>();
        StringBuffer sbHql = new StringBuffer();

        sbHql.append("from FlowCardInfoEntity t where t.belongAcctId='" + ResourceUtil.getWeiXinAccountId() + "'");

        if (cardNumber != null && cardNumber != "") {
            sbHql.append(" and t.cardNumber= '" + cardNumber + "'");
        }
        if (flowValue != null) {
            sbHql.append(" and t.flowValue= '" + flowValue + "'");
        }
        if (beginDate != null) {
            sbHql.append(" and t.flowValue= '" + flowValue + "'");
        }
        if (endDate != null) {
            sbHql.append(" and t.flowValue= '" + flowValue + "'");
        }
        if (isValid != null && isValid != "") {
            sbHql.append(" and t.isValid= '" + isValid + "'");
        }
        if (isUse != null && isUse != "") {
            sbHql.append(" and t.isUse= '" + isUse + "'");
        }
        if (statusLock != null && statusLock != "") {
            sbHql.append(" and t.statusLock= '" + statusLock + "'");
        }
        builder.append("sbHql_" + sbHql);
        List<FlowCardInfoEntity> entities = flowCardInfoService.findHql(sbHql.toString(), null);
        //判断是导出所有符合条件的二维码还是当前页选中的二维码
        if (countChecked >= checkboxCount) {//导出所有符合条件的二维码
            for (FlowCardInfoEntity flowCardInfoEntity : entities) {
                pictureName.add(flowCardInfoEntity.getQRcode());
            }
        } else {//导出当前页选中的二维码
            String[] QRcodeName = QRcode.split(",");
            for (int i = 0; i < QRcodeName.length; i++) {
                pictureName.add(QRcodeName[i]);
            }
        }
        try {
            /**
             * 获取一个时间戳，用于标识是某个商户的导出的二维码的图片
             */
            fromPath = ResourceUtil.getConfigByName("QRcode.storage.path");
//            zipFile=ResourceUtil.getConfigByName("media.zipFile.prefix");
            /**
             * 创建文件的目录从而可以将不同商户的二维码进行导出
             */
            String path = ResourceUtil.getConfigByName("media.zipFile.prefix"); //读取的是整个压缩文件存储的根目录
            TSUser sessionUserName = ResourceUtil.getSessionUserName();
            assert sessionUserName != null;
            String newPath = path + "/" + sessionUserName.getUserName();//指定新路径，加上商户名称加以区分

            builder.append("newPath_" + newPath);
            boolean flag = createDir(newPath);  //将路径名字进行传入，创建新的文件夹
            if (flag) {  //成功创建后生成新的压缩文件的存储地址,以当前的时间区分不同的下载包System.currentTimeMillis()
                zipFile = newPath + "/" + sessionUserName.getUserName() + "-" + System.currentTimeMillis() + ".zip";
            } else {//表示此时已经存在了文件夹，也是可以直接存储的
                zipFile = newPath + "/" + sessionUserName.getUserName() + "-" + System.currentTimeMillis() + ".zip";
            }

            builder.append("zipFile_" + zipFile);
//            zipFile=ResourceUtil.getConfigByName("media.zipFile.prefix")+ResourceUtil.getSessionUserName().getUserName()+"-"+ymd+".zip";
            ZipUtil.zip(fromPath, zipFile, pictureName);  //将压缩文件进行存放
            zipUrl = ResourceUtil.getConfigByName("media.zip.prefix") + "/" + sessionUserName.getUserName(); //加上了商户名称的文件夹可以进行更好的过滤，区分不同商户的生成的二维码jar包
            builder.append("zipUrl_" + zipUrl);
            a.setMsg("二维码下载成功");
            a.setObj(zipUrl);
        } catch (Exception e) {
            a.setMsg("二维码下载失败");
            e.printStackTrace();
        }
        long runTime = System.currentTimeMillis() - startTime;
        LOGGER.info("_goDownQRCode_runTime_" + runTime + builder.toString());
        return a;
    }

    /**
     * 将显示在网页上的二维码，把用户选中的二维码下载到指定位置
     *
     * @param QRcode
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "goQRcode")
    @ResponseBody
    public void goQRcode(HttpServletResponse response, HttpServletRequest request) throws Exception {
        String cardNumber = request.getParameter("cardNumber");
        Double flowValue = StringUtils.isNotBlank(request.getParameter("flowValue")) ? Double.parseDouble(request.getParameter("flowValue")) : null;
        String isValid = request.getParameter("isValid");
        String isUse = request.getParameter("isUse");
        String batchNo = request.getParameter("batchNo");
        String statusLock = request.getParameter("statusLock");
        String flowType = request.getParameter("flowType");
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();

        builder.append("flowCardInfoController_goQRcode");
        AjaxJson a = new AjaxJson();
        String fromPath = "";//图片来源
        String zipFile = "";//存放压缩文件的地址
        List<String> pictureName = new ArrayList<String>();
        StringBuffer sbHql = new StringBuffer();

        sbHql.append("from FlowCardInfoEntity t where t.belongAcctId='" + ResourceUtil.getWeiXinAccountId() + "'");

        if (StringUtils.isNotBlank(cardNumber)) {
            sbHql.append(" and t.cardNumber= '" + cardNumber + "'");
        }
        if(StringUtils.isNotBlank(batchNo)){
            sbHql.append(" and t.batchNo= '" + batchNo + "'");
        }
        if (flowValue != null) {
            sbHql.append(" and t.flowValue= '" + flowValue + "'");
        }
        if (StringUtils.isNotBlank(isValid)) {
            sbHql.append(" and t.isValid= '" + isValid + "'");
        }
        if (StringUtils.isNotBlank(isUse)) {
            sbHql.append(" and t.isUse= '" + isUse + "'");
        }
        if (StringUtils.isNotBlank(flowType)) {
            sbHql.append(" and t.flowType= '" + flowType + "'");
        }
        if (StringUtils.isNotBlank(statusLock)) {
            sbHql.append(" and t.statusLock= '" + statusLock + "'");
        }
        builder.append("sbHql_" + sbHql);
        List<FlowCardInfoEntity> entities = flowCardInfoService.findHql(sbHql.toString(), new Object[]{});
        // 判断是导出所有符合条件的二维码还是当前页选中的二维码
        if(entities!=null){
            for (FlowCardInfoEntity flowCardInfoEntity : entities) {
                String qRcode = flowCardInfoEntity.getQRcode();
                if (StringUtils.isBlank(qRcode)) {
                    continue;
                }
                pictureName.add(qRcode);
            }
        }
        try {
            /**
             * 获取一个时间戳，用于标识是某个商户的导出的二维码的图片
             */
            TSUser sessionUserName = ResourceUtil.getSessionUserName();
            fromPath = ResourceUtil.getConfigByName("QRcode.storage.path");
            response.setHeader("Content-Type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + sessionUserName.getUserName() + "-" + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss") + ".zip");
            response.getOutputStream().write(ZipUtil.zip(fromPath, zipFile, pictureName));
        } catch (Exception e) {
            a.setMsg("二维码下载失败");
            e.printStackTrace();
        }
        long runTime = System.currentTimeMillis() - startTime;
        LOGGER.info("_goDownQRCode_runTime_" + runTime + builder.toString());
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public void exportXls(FlowCardInfoEntity flowCardInfo, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid) {

        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "流量卡详细信息表";
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
            CriteriaQuery cq = new CriteriaQuery(FlowCardInfoEntity.class, dataGrid);
            org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, flowCardInfo, request.getParameterMap());

            List<FlowCardInfoEntity> flowList = this.flowCardInfoService.getListByCriteriaQuery(cq, false);
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("流量卡详细信息表", "导出人:" + ResourceUtil.getSessionUserName().getUserName(),
                    "导出信息"), FlowCardInfoEntity.class, flowList);
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
     * 动态创建文件夹
     */
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);  //destDirName  文件夹所在路径以及文件名
        if (dir.exists()) {
            return false;
        }
        if (!destDirName.endsWith(File.separator))
            destDirName = destDirName + File.separator;
        // 创建单个目录
        if (dir.mkdirs()) {
            LOGGER.info("创建目录" + destDirName + "成功！");
            return true;
        } else {
            LOGGER.info("创建目录" + destDirName + "成功！");
            return false;
        }
    }
}
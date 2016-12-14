package weixin.report.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.annotation.DataSourceSwitch;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import weixin.report.model.MerchantCharge;
import weixin.report.model.SubMerchantCharge;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.service.WeixinAcctServiceI;
import weixin.util.CommonUtils;
import weixin.util.DateUtils;

/**
 * @author parallel_line
 * @version 2016年9月20日 下午9:17:54
 */
@Scope("prototype")
@Controller
@RequestMapping("/merchantChargeReportController")
public class MerchantChargeReportController extends BaseController {
    private static final Logger LOGGER = Logger.getLogger(MerchantChargeReportController.class);

    @Autowired
    private SystemService systemService;
    @Autowired
    private WeixinAcctServiceI acctService;

    /**
     * 我的流量充值记录
     */
    @RequestMapping(params = "chargeRecords")
    public ModelAndView chargeRecords(HttpServletRequest request) {
        String currAcctLevel= ResourceUtil.getWeiXinAcct() ==null ? "":ResourceUtil.getWeiXinAcct().getAcctLevel();
        request.getSession().setAttribute("currAcctLevel", currAcctLevel);
        return new ModelAndView("weixin/report/merchantcharge/chargeRecords");
    }

    @RequestMapping(params = "chargeRecordsList")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void chargeRecordsList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws ParseException {
        String beginDate = request.getParameter("chargetime_begin");
        String endDate = request.getParameter("chargetime_end");
        String merchantId = ResourceUtil.getWeiXinAcctId();
        if (StringUtils.isEmpty(merchantId)) {
            this.buildNullResult(response, dataGrid);
            return;
        }
        StringBuffer sql = new StringBuffer();
        sql.append(
                "select acctf.acctForName acctName,acctf.acct_level acctLevel,a.flowType flowType,a.flowValue flowValue,a.tradingDate chargetime,a.des from flowcardtraderecords a left join merchantflowaccount mt on a.toAcc_id=mt.id "
                        + "left join weixin_acct acctt on mt.tenantId=acctt.id " + "left join merchantflowaccount mf on a.fromAcc_id = mf.id "
                        + "left join weixin_acct acctf on mf.tenantId=acctf.id where a.flowSource='增加' ");
        sql.append(" and mt.tenantId='" + merchantId + "' ");
        if (StringUtils.isNotBlank(beginDate)) {
            sql.append(" and DATE_FORMAT(a.tradingDate,'%Y-%m-%d') >= '" + beginDate + "' ");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and DATE_FORMAT(a.tradingDate,'%Y-%m-%d') <= '" + endDate + "' ");
        }

        sql.append(" ORDER BY a.tradingDate DESC");

        String countSql = "select count(1) from (" + sql.toString() + ") c";
        Long count = this.systemService.getCountForJdbc(countSql);
        List<Map<String, Object>> data = this.systemService.findForJdbc(sql.toString(), dataGrid.getPage(), dataGrid.getRows());

        dataGrid.setResults(data);
        dataGrid.setTotal(count.intValue());
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "exportMerchantChargeXls")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void exportMerchantChargeXls(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            String dateTime = DateUtils.yyyyMMdd.format(new Date());
            codedFileName = "[" + ResourceUtil.getWeiXinAcct().getAcctForName() + "]" + "我的流量充值记录" + dateTime;

            // 根据浏览器进行转码，使其支持中文文件名
            if (BrowserUtils.isIE(request)) {
                response.setHeader("content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(codedFileName, "UTF-8") + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
                response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
            }
            // 产生工作簿对象
            HSSFWorkbook workbook = null;
            List<MerchantCharge> merchantCharges = new ArrayList<MerchantCharge>();
            String beginDate = request.getParameter("chargetime_begin");
            String endDate = request.getParameter("chargetime_end");
            String merchantId = ResourceUtil.getWeiXinAcctId();
            if (StringUtils.isEmpty(merchantId)) {
                return;
            }
            StringBuffer sql = new StringBuffer();
            sql.append(
                    "select acctf.acctForName acctName,acctf.acct_level acctLevel,a.flowType flowType,a.flowValue flowValue,a.tradingDate chargetime,a.des from flowcardtraderecords a left join merchantflowaccount mt on a.toAcc_id=mt.id "
                            + "left join weixin_acct acctt on mt.tenantId=acctt.id " + "left join merchantflowaccount mf on a.fromAcc_id = mf.id "
                            + "left join weixin_acct acctf on mf.tenantId=acctf.id where a.flowSource='增加' ");
            sql.append(" and mt.tenantId='" + merchantId + "' ");
            if (StringUtils.isNotBlank(beginDate)) {
                sql.append(" and DATE_FORMAT(a.tradingDate,'%Y-%m-%d') >= '" + beginDate + "' ");
            }
            if (StringUtils.isNotBlank(endDate)) {
                sql.append(" and DATE_FORMAT(a.tradingDate,'%Y-%m-%d') <= '" + endDate + "' ");
            }

            sql.append(" ORDER BY a.tradingDate asc");

            List<Map<String, Object>> data = this.systemService.findForJdbc(sql.toString(), dataGrid.getPage(), dataGrid.getRows());
            if (CollectionUtils.isEmpty(data)) {
                return;
            }
            for (Map<String, Object> m : data) {
                MerchantCharge mc = new MerchantCharge();
                mc.setAcctLevel(MapUtils.getString(m, "acctLevel"));
                mc.setAcctName(MapUtils.getString(m, "acctName"));
                mc.setFlowType(MapUtils.getString(m, "flowType"));
                mc.setFlowValue(MapUtils.getDouble(m, "flowValue"));
                mc.setChargetime((Date) m.get("chargetime"));
                mc.setDes(MapUtils.getString(m, "des"));
                merchantCharges.add(mc);
            }

            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("我的流量充值记录", "导出人:" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"),
                    MerchantCharge.class, merchantCharges);
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (Exception e) {
            LOGGER.error(weixin.util.LogUtil.printStackTrace(e));
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {

            }
        }
    }

    /**
     * 下级商户充值记录
     */
    @RequestMapping(params = "subMerchantChargeRecords")
    public ModelAndView subMerchantChargeRecords(HttpServletRequest request) {
        String currAcctLevel= ResourceUtil.getWeiXinAcct() ==null ? "":ResourceUtil.getWeiXinAcct().getAcctLevel();
        request.getSession().setAttribute("currAcctLevel", currAcctLevel);
        return new ModelAndView("weixin/report/merchantcharge/subChargeRecords");
    }

    @RequestMapping(params = "subMerchantChargeRecordsList")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void subMerchantChargeRecordsList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws ParseException {
        String acctName = request.getParameter("acctName");
        String beginDate = request.getParameter("chargetime_begin");
        String endDate = request.getParameter("chargetime_end");
        String acctLevel = request.getParameter("acctLevel");
        String belogAcct = request.getParameter("belogAcct");

        String merchantId = ResourceUtil.getWeiXinAcctId();
        if (StringUtils.isEmpty(merchantId)) {
            this.buildNullResult(response, dataGrid);
            return;
        }
        List<WeixinAcctEntity> subAccts = this.acctService.getAllSubAcct(merchantId);
        
        if (CollectionUtils.isEmpty(subAccts)) {
            this.buildNullResult(response, dataGrid);
            return;
        }
        StringBuffer sql = new StringBuffer();
        sql.append(
                "select acctt.acctForName acctName,acctt.acct_level acctLevel,accttp.acctForName belogAcct,a.flowType flowType,a.flowValue flowValue,a.tradingDate chargetime,a.des from flowcardtraderecords a left join merchantflowaccount mt on a.toAcc_id=mt.id "
                        + "left join weixin_acct acctt on mt.tenantId=acctt.id " + " left join weixin_acct accttp on acctt.pid= accttp.id "
                        + "left join merchantflowaccount mf on a.fromAcc_id = mf.id "
                        + "left join weixin_acct acctf on mf.tenantId=acctf.id where a.flowSource='增加' ");
        if (StringUtils.isNotBlank(acctName)) {
            sql.append(" and acctt.acctForName like '%" + StringUtils.trim(acctName) + "%' ");
        }
        if (StringUtils.isNotBlank(acctLevel)) {
            sql.append(" and acctt.acct_level = '" + StringUtils.trim(acctLevel) + "' ");
        }
        if(StringUtils.isNotBlank(belogAcct)){
            sql.append(" and acctt.pid = '"+StringUtils.trim(belogAcct)+"' ");
        }
        List<String> subAcctIds = new ArrayList<String>(subAccts.size());
        for (WeixinAcctEntity acct : subAccts) {
            subAcctIds.add(acct.getId());
        }
        sql.append(" and acctt.id in (").append(CommonUtils.listToSqlString(subAcctIds)).append(")");
        if (StringUtils.isNotBlank(beginDate)) {
            sql.append(" and DATE_FORMAT(a.tradingDate,'%Y-%m-%d') >= '" + beginDate + "' ");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and DATE_FORMAT(a.tradingDate,'%Y-%m-%d') <= '" + endDate + "' ");
        }

        sql.append(" ORDER BY a.tradingDate DESC");
        String countSql = "select count(1) from (" + sql.toString() + ") c";
        Long count = this.systemService.getCountForJdbc(countSql);
        List<Map<String, Object>> data = this.systemService.findForJdbc(sql.toString(), dataGrid.getPage(), dataGrid.getRows());

        dataGrid.setResults(data);
        dataGrid.setTotal(count.intValue());
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "exportSubChargeXls")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void exportSubChargeXls(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            String dateTime = DateUtils.yyyyMMdd.format(new Date());
            codedFileName = "[" + ResourceUtil.getWeiXinAcct().getAcctForName() + "]" + "下级商户充值列表" + dateTime;

            // 根据浏览器进行转码，使其支持中文文件名
            if (BrowserUtils.isIE(request)) {
                response.setHeader("content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(codedFileName, "UTF-8") + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
                response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
            }
            // 产生工作簿对象
            HSSFWorkbook workbook = null;
            List<SubMerchantCharge> merchantCharges = new ArrayList<SubMerchantCharge>();
            String acctName = request.getParameter("acctName");
            String beginDate = request.getParameter("chargetime_begin");
            String endDate = request.getParameter("chargetime_end");
            String acctLevel = request.getParameter("acctLevel");
            String belogAcct = request.getParameter("belogAcct");

            String merchantId = ResourceUtil.getWeiXinAcctId();
            if (StringUtils.isEmpty(merchantId)) {
                this.buildNullResult(response, dataGrid);
                return;
            }
            List<WeixinAcctEntity> subAccts = null;
            if (StringUtils.isBlank(belogAcct)) {
                subAccts = this.acctService.getAllSubAcct(merchantId);
            } else {
                subAccts = this.acctService.getAllSubAcct(belogAcct);
            }
            if (CollectionUtils.isEmpty(subAccts)) {
                this.buildNullResult(response, dataGrid);
                return;
            }
            StringBuffer sql = new StringBuffer();
            sql.append(
                    "select acctt.acctForName acctName,acctt.acct_level acctLevel,accttp.acctForName belogAcctName,a.flowType flowType,a.flowValue flowValue,a.tradingDate chargetime,a.des from flowcardtraderecords a left join merchantflowaccount mt on a.toAcc_id=mt.id "
                            + "left join weixin_acct acctt on mt.tenantId=acctt.id " + " left join weixin_acct accttp on acctt.pid= accttp.id "
                            + "left join merchantflowaccount mf on a.fromAcc_id = mf.id "
                            + "left join weixin_acct acctf on mf.tenantId=acctf.id where a.flowSource='增加' ");
            if (StringUtils.isNotBlank(acctName)) {
                sql.append(" and acctt.acctForName like '%" + StringUtils.trim(acctName) + "%' ");
            }
            if (StringUtils.isNotBlank(acctLevel)) {
                sql.append(" and acctt.acct_level = '" + StringUtils.trim(acctLevel) + "' ");
            }
            if(StringUtils.isNotBlank(belogAcct)){
                sql.append(" and acctt.pid = '"+StringUtils.trim(belogAcct)+"' ");
            }
            List<String> subAcctIds = new ArrayList<String>(subAccts.size());
            for (WeixinAcctEntity acct : subAccts) {
                subAcctIds.add(acct.getId());
            }
            sql.append(" and acctt.id in (").append(CommonUtils.listToSqlString(subAcctIds)).append(")");
            if (StringUtils.isNotBlank(beginDate)) {
                sql.append(" and DATE_FORMAT(a.tradingDate,'%Y-%m-%d') >= '" + beginDate + "' ");
            }
            if (StringUtils.isNotBlank(endDate)) {
                sql.append(" and DATE_FORMAT(a.tradingDate,'%Y-%m-%d') <= '" + endDate + "' ");
            }

            sql.append(" ORDER BY a.tradingDate asc");

            List<Map<String, Object>> data = this.systemService.findForJdbc(sql.toString(), dataGrid.getPage(), dataGrid.getRows());
            if (CollectionUtils.isEmpty(data)) {
                return;
            }
            for (Map<String, Object> m : data) {
                SubMerchantCharge mc = new SubMerchantCharge();
                mc.setAcctLevel(MapUtils.getString(m, "acctLevel"));
                mc.setAcctName(MapUtils.getString(m, "acctName"));
                mc.setFlowType(MapUtils.getString(m, "flowType"));
                mc.setFlowValue(MapUtils.getDouble(m, "flowValue"));
                mc.setChargetime((Date) m.get("chargetime"));
                mc.setDes(MapUtils.getString(m, "des"));
                mc.setBelogAcct(MapUtils.getString(m, "belogAcctName"));
                merchantCharges.add(mc);
            }

            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("下级商户充值列表", "导出人:" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"),
                    SubMerchantCharge.class, merchantCharges);
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (Exception e) {
            LOGGER.error(weixin.util.LogUtil.printStackTrace(e));
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {

            }
        }
    }

}

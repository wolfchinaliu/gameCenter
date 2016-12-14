package weixin.subActivityAnalysis.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.sql.Select;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.DataGrid;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import weixin.activityAnalysis.view.ActivityAnalysisBean;
import weixin.liuliangbao.jsonbean.ViewBean.UserFlowGiveBean;
import weixin.subActivityAnalysis.view.SubActivityAnalysisBean;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.util.CommonUtils;

/**
 * @author Wangpeng 2016年10月19日
 */

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
@RequestMapping("/subActivityAnalysisController")
public class SubActivityAnalysisController extends BaseController{

	@Autowired
	private SystemService systemService;
	@Autowired
	private WeixinAcctFlowAccoutServiceI weixinAcctFlowAccoutServiceI;
	
	/* 活动分析列表  */
	@RequestMapping(params="subActivityAnalysisList")
    public ModelAndView subActivityAnalysisList(HttpServletRequest req){
        return new ModelAndView("weixin/subActivityAnalysisList/SubActivityAnalysisList");
    }
	 
    /**
     * @author wangpeng 2016年10月19日
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "datagrid")
    @ResponseBody
    public void datagrid(SubActivityAnalysisBean subActivityAnalysisBean, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws ParseException {
       
       String name = request.getParameter("subacctForName");
       String acctlevel = request.getParameter("acctLevel");
       String acctForName = request.getParameter("acctForName");
       String reason1 = request.getParameter("reason");
       String beginDate = request.getParameter("operateDate_begin");
       String endDate = request.getParameter("operateDate_end");
       String merchantID = ResourceUtil.getWeiXinAccountId();
       StringBuffer sql = new StringBuffer();
       sql.append("select w.acctForName subAcctForName,w.acct_level,accttp.acctForName,u.reason,COUNT(u.id),SUM(u.flowValue),AVG(u.flowValue),DATE_FORMAT(u.operateDate,'%Y-%m-%d') from userflowgiverecords u LEFT JOIN weixin_account wa on u.merchantID = wa.id LEFT JOIN weixin_acct w on wa.acct_id = w.id LEFT JOIN weixin_acct accttp on w.pid= accttp.id where 1 = 1 ");
       List<String> subAccountIdList = weixinAcctFlowAccoutServiceI.findSubAccountIdList(merchantID);
       if(subAccountIdList.size() == 0){
    	   subAccountIdList.add("");
       }
       if (merchantID != null && !"".equals(merchantID)) {
			sql.append(" and u.merchantID in (").append(CommonUtils.listToSqlString(subAccountIdList)).append(")");
		}
       //商户名称模糊查询
       if (name != null && !"".equals(name)) {
           sql.append(" and w.acctForName like ").append("'%").append(name).append("%'");
       }
       //商户等级
       if (acctlevel != null && !"".equals(acctlevel)) {
           sql.append(" and w.acct_level=").append("'").append(acctlevel).append("'");
       }
       //所属商户
       if(StringUtils.isNotBlank(acctForName)){
           sql.append(" and w.pid = '"+StringUtils.trim(acctForName)+"' ");
       }
       //活动类型查询
       if (reason1 != null && !"".equals(reason1)) {
           sql.append(" and u.reason=").append("'").append(reason1).append("'");
       }
       //时间查询
       if (StringUtils.isNotBlank(beginDate)) {
           sql.append(" and DATE_FORMAT(u.operateDate,'%Y-%m-%d') >= '" + beginDate + "' ");
       }
       if (StringUtils.isNotBlank(endDate)) {
           sql.append(" and DATE_FORMAT(u.operateDate,'%Y-%m-%d') <= '" + endDate + "' ");
       }
       //排除未绑定手机号用户
       sql.append(" and u.status = '1' ");
       //按照时间和活动类型分组
       sql.append(" group by DATE_FORMAT(u.operateDate,'%Y%m%d'),reason");
       // 添加查询的用户记录的时间排序
       sql.append(" ORDER BY DATE_FORMAT(u.operateDate,'%Y%m%d') DESC");
       HqlQuery hqlQuery = new HqlQuery(SubActivityAnalysisBean.class, sql.toString(), dataGrid);
       PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
       List<Object[]> list = pageList.getResultList();
       List<SubActivityAnalysisBean> param = new ArrayList<SubActivityAnalysisBean>();
       for (Object[] objects : list) {
    	   SubActivityAnalysisBean subActivityAnalysis = new SubActivityAnalysisBean();
    	   
    	   Object subAcctForName = objects[0];
           if (subAcctForName != null) {
        	   subActivityAnalysis.setSubAcctForName(subAcctForName.toString());
           }
           Object acctLevel = objects[1];
           if (acctLevel != null) {
               subActivityAnalysis.setAcctLevel(acctLevel.toString());
           }
           Object acctforName = objects[2];
           if (acctforName != null) {
               subActivityAnalysis.setAcctForName(acctforName.toString());
           }
           Object reason = objects[3];
           if (reason != null) {
        	   subActivityAnalysis.setReason(reason.toString());
           }
           Object sumId = objects[4];
           if (sumId != null) {
        	   subActivityAnalysis.setSumId(sumId.toString());
           }
           Object sumValue = objects[5];
           if (sumValue != null) {
        	   subActivityAnalysis.setSumValue(Double.parseDouble(sumValue.toString()));
           }
           Object avgValue = objects[6];
           if (avgValue != null) {
        	   subActivityAnalysis.setAvgValue(Double.parseDouble(avgValue.toString()));
           }
           Object operateDate = objects[7];
           if (operateDate != null) {
        	   SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
               Date d = sim.parse(operateDate.toString());
               DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
               String starttime = format.format(d);
               subActivityAnalysis.setOperateDate(starttime);
           }
           
           
           param.add(subActivityAnalysis);
       }
       dataGrid.setResults(param);
       dataGrid.setTotal(pageList.getCount());
       dataGrid.setPage(pageList.getCurPageNO());
       dataGrid.setRows(pageList.getOffset());
       TagUtil.datagrid(response, dataGrid);
       
    }
    
	/**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public void exportXls(SubActivityAnalysisBean subActivityAnalysisBean, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "下级商户活动分析";
            // 根据浏览器进行转码，使其支持中文文件名
            if (BrowserUtils.isIE(request)) {
                response.setHeader("content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(codedFileName, "UTF-8") + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
                response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
            }
            // 产生工作簿对象
            HSSFWorkbook workbook = null;
            String name = request.getParameter("subacctForName");
            String acctlevel = request.getParameter("acctLevel");
            String acctForName = request.getParameter("acctForName");
            String reason1 = request.getParameter("reason");
            String beginDate = request.getParameter("operateDate_begin");
            String endDate = request.getParameter("operateDate_end");
            String merchantID = ResourceUtil.getWeiXinAccountId();
            StringBuffer sql = new StringBuffer();
            sql.append("select w.acctForName subAcctForName,w.acct_level,accttp.acctForName,u.reason,COUNT(u.id),SUM(u.flowValue),AVG(u.flowValue),DATE_FORMAT(u.operateDate,'%Y-%m-%d') from userflowgiverecords u LEFT JOIN weixin_account wa on u.merchantID = wa.id LEFT JOIN weixin_acct w on wa.acct_id = w.id LEFT JOIN weixin_acct accttp on w.pid= accttp.id where 1 = 1 ");
            List<String> subAccountIdList = weixinAcctFlowAccoutServiceI.findSubAccountIdList(merchantID);
            if(subAccountIdList.size() == 0){
         	   subAccountIdList.add("");
            }
            if (merchantID != null && !"".equals(merchantID)) {
     			sql.append(" and u.merchantID in (").append(CommonUtils.listToSqlString(subAccountIdList)).append(")");
     		}
            //商户名称模糊查询
            if (name != null && !"".equals(name)) {
                sql.append(" and w.acctForName like ").append("'%").append(name).append("%'");
            }
            //商户等级
            if (acctlevel != null && !"".equals(acctlevel)) {
                sql.append(" and w.acct_level=").append("'").append(acctlevel).append("'");
            }
            //所属商户
            if(StringUtils.isNotBlank(acctForName)){
                sql.append(" and w.pid = '"+StringUtils.trim(acctForName)+"' ");
            }
            //活动类型查询
            if (reason1 != null && !"".equals(reason1)) {
                sql.append(" and u.reason=").append("'").append(reason1).append("'");
            }
            //时间查询
            if (StringUtils.isNotBlank(beginDate)) {
                sql.append(" and DATE_FORMAT(u.operateDate,'%Y-%m-%d') >= '" + beginDate + "' ");
            }
            if (StringUtils.isNotBlank(endDate)) {
                sql.append(" and DATE_FORMAT(u.operateDate,'%Y-%m-%d') <= '" + endDate + "' ");
            }
            //排除未绑定手机号用户
            sql.append(" and u.status = '1' ");
            //按照时间和活动类型分组
            sql.append(" group by DATE_FORMAT(u.operateDate,'%Y%m%d'),reason");
            // 添加查询的用户记录的时间排序
            sql.append(" ORDER BY DATE_FORMAT(u.operateDate,'%Y%m%d') ASC");
            dataGrid.setPage(1);
            dataGrid.setRows(65535);
            HqlQuery hqlQuery = new HqlQuery(SubActivityAnalysisBean.class, sql.toString(), dataGrid,65535);
            PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
            List<Object[]> subActivityAnalysis = pageList.getResultList();
            List<SubActivityAnalysisBean> newSubActivityAnalysis = new ArrayList<SubActivityAnalysisBean>();
            for (int i = 0; i < subActivityAnalysis.size(); i++) {
            	Object[] result = subActivityAnalysis.get(i);
            	SubActivityAnalysisBean entity = new SubActivityAnalysisBean();
            	Object subacctForName = result[0];
                if (subacctForName != null) {
                	entity.setSubAcctForName(subacctForName.toString());
                }
                Object acctLevel = result[1];
                if (acctLevel != null) {
                	entity.setAcctLevel(acctLevel.toString());
                }
                Object acctforName = result[2];
                if (acctforName != null) {
                	entity.setAcctForName(acctforName.toString());
                }
                Object reason = result[3];
                if (reason != null) {
                	entity.setReason(reason.toString());
                }
                Object sumId = result[4];
                if (sumId != null) {
                	entity.setSumId(sumId.toString());
                }
                Object sumValue = result[5];
                if (sumValue != null) {
                	entity.setSumValue(Double.parseDouble(sumValue.toString()));
                }
                Object avgValue = result[6];
                if (avgValue != null) {
                	entity.setAvgValue(Double.parseDouble(avgValue.toString()));
                }
                Object operateDate = result[7];
                if (operateDate != null) {
             	   SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
                    Date d = sim.parse(operateDate.toString());
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String starttime = format.format(d);
                    entity.setOperateDate(starttime);
                }
                newSubActivityAnalysis.add(entity);
                }
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("下级商户活动分析记录", "导出人:" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"),
            		SubActivityAnalysisBean.class, newSubActivityAnalysis);
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {

            }
        }
    }
    
  
}

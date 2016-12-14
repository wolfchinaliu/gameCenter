package weixin.activityAnalysis.controller;

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
import weixin.tenant.entity.WeixinAcctEntity;

/**
 * @author Wangpeng 2016年10月18日
 */

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
@RequestMapping("/activityAnalysisController")
public class ActivityAnalysisController extends BaseController{

	@Autowired
	private SystemService systemService;

	/* 活动分析列表  */
	@RequestMapping(params="activityAnalysisList")
    public ModelAndView activityAnalysisList(HttpServletRequest req){
        return new ModelAndView("weixin/activityAnalysis/ActivityAnalysisList");
    }
    
    /**
     * @author wangpeng 2016年10月18日
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "datagrid")
    @ResponseBody
    public void datagrid(ActivityAnalysisBean activityAnalysisBean, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws ParseException {
       
       String reason1 = request.getParameter("reason");
       String beginDate = request.getParameter("operateDate_begin");
       String endDate = request.getParameter("operateDate_end");
       String merchantID = ResourceUtil.getWeiXinAccountId();
       StringBuffer sql = new StringBuffer();
       sql.append("select operateDate,reason,count(id),sum(flowValue),AVG(flowValue) from userflowgiverecords where 1 = 1 ");
       //商户id
       if (merchantID != null && !"".equals(merchantID)) {
           sql.append(" and merchantID=").append("'").append(merchantID).append("'");
       }
       //活动类型查询
       if (reason1 != null && !"".equals(reason1)) {
           sql.append(" and reason=").append("'").append(reason1).append("'");
       }
       //时间查询
       if (StringUtils.isNotBlank(beginDate)) {
           sql.append(" and DATE_FORMAT(operateDate,'%Y-%m-%d') >= '" + beginDate + "' ");
       }
       if (StringUtils.isNotBlank(endDate)) {
           sql.append(" and DATE_FORMAT(operateDate,'%Y-%m-%d') <= '" + endDate + "' ");
       }
       //排除未绑定手机号用户
       sql.append(" and status = '1' ");
       //按照时间和活动类型分组
       sql.append(" group by DATE_FORMAT(operateDate,'%Y%m%d'),reason");
       // 添加查询的用户记录的时间排序
       sql.append(" ORDER BY DATE_FORMAT(operateDate,'%Y%m%d') DESC");
       HqlQuery hqlQuery = new HqlQuery(ActivityAnalysisBean.class, sql.toString(), dataGrid);
       PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
       List<Object[]> list = pageList.getResultList();
       List<ActivityAnalysisBean> param = new ArrayList<ActivityAnalysisBean>();
       for (Object[] objects : list) {
    	   ActivityAnalysisBean activityAnalysis = new ActivityAnalysisBean();
           Object operateDate = objects[0];
           if (operateDate != null) {
        	   SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
               Date d = sim.parse(operateDate.toString());
               DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
               String starttime = format.format(d);
               activityAnalysis.setOperateDate(starttime);
           }
           Object reason = objects[1];
           if (reason != null) {
        	   activityAnalysis.setReason(reason.toString());
           }
           Object sumId = objects[2];
           if (sumId != null) {
        	   activityAnalysis.setSumId(sumId.toString());
           }
           Object sumValue = objects[3];
           if (sumValue != null) {
        	   activityAnalysis.setSumValue(Double.parseDouble(sumValue.toString()));
           }
           Object avgValue = objects[4];
           if (avgValue != null) {
        	   activityAnalysis.setAvgValue(Double.parseDouble(avgValue.toString()));
           }
           
           param.add(activityAnalysis);
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
    public void exportXls(ActivityAnalysisBean activityAnalysisBean, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "活动分析";
            // 根据浏览器进行转码，使其支持中文文件名
            if (BrowserUtils.isIE(request)) {
                response.setHeader("content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(codedFileName, "UTF-8") + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
                response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
            }
            // 产生工作簿对象
            HSSFWorkbook workbook = null;
            String reason1 = request.getParameter("reason");
			String beginDate = request.getParameter("operateDate_begin");
			String endDate = request.getParameter("operateDate_end");
			String merchantID = ResourceUtil.getWeiXinAccountId();
			StringBuffer sql = new StringBuffer();
			sql.append("select operateDate,reason,count(id),sum(flowValue),AVG(flowValue) from userflowgiverecords where 1 = 1 ");
			//商户id
			if (merchantID != null && !"".equals(merchantID)) {
				sql.append(" and merchantID=").append("'").append(merchantID).append("'");
			}
			//活动类型查询
			if (reason1 != null && !"".equals(reason1)) {
				sql.append(" and reason=").append("'").append(reason1).append("'");
			}
			//时间查询
			if (StringUtils.isNotBlank(beginDate)) {
				sql.append(" and DATE_FORMAT(operateDate,'%Y-%m-%d') >= '" + beginDate + "' ");
			}
			if (StringUtils.isNotBlank(endDate)) {
				sql.append(" and DATE_FORMAT(operateDate,'%Y-%m-%d') <= '" + endDate + "' ");
			}
		    //排除未绑定手机号用户
		    sql.append(" and status = '1' ");
			//按照时间和活动类型分组
			sql.append(" group by DATE_FORMAT(operateDate,'%Y%m%d'),reason");
			// 添加查询的用户记录的时间排序
			sql.append(" ORDER BY DATE_FORMAT(operateDate,'%Y%m%d') ASC");
            dataGrid.setPage(1);
            dataGrid.setRows(65535);
            HqlQuery hqlQuery = new HqlQuery(ActivityAnalysisBean.class, sql.toString(), dataGrid,65535);
            PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
            /* 过滤集合中不等于0.0的流量值 */
            List<Object[]> activityAnalysis = pageList.getResultList();
            List<ActivityAnalysisBean> newActivityAnalysis = new ArrayList<ActivityAnalysisBean>();
            for (int i = 0; i < activityAnalysis.size(); i++) {
            	Object[] result = activityAnalysis.get(i);
            	ActivityAnalysisBean entity = new ActivityAnalysisBean();
            	Object operateDate = result[0];
                if (operateDate != null) {
             	   SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
                    Date d = sim.parse(operateDate.toString());
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String starttime = format.format(d);
                    entity.setOperateDate(starttime);
                }
                Object reason = result[1];
                if (reason != null) {
                	entity.setReason(reason.toString());
                }
                Object sumId = result[2];
                if (sumId != null) {
                	entity.setSumId(sumId.toString());
                }
                Object sumValue = result[3];
                if (sumValue != null) {
                	entity.setSumValue(Double.parseDouble(sumValue.toString()));
                }
                Object avgValue = result[4];
                if (avgValue != null) {
                	entity.setAvgValue(Double.parseDouble(avgValue.toString()));
                }   
                    newActivityAnalysis.add(entity);
                }
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("活动分析记录", "导出人:" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"),
            		ActivityAnalysisBean.class, newActivityAnalysis);
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

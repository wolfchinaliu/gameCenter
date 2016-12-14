package weixin.report.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.annotation.DataSourceSwitch;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
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

import weixin.liuliangbao.jsonbean.AccountGroup.acctAndGroup;
import weixin.report.model.FlowCollect;
import weixin.report.model.UserGiveFlow;
import weixin.report.model.UserGiveFlowEntity;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.util.CommonUtils;
import weixin.util.DateUtils;
@Scope("prototype")
@Controller
@RequestMapping("/flowCollectController")
public class FlowCollectController extends BaseController{
	private static final Logger LOGGER = Logger.getLogger(MerchantGiveFlowController.class);
	@Autowired
    private SystemService systemService;
	@Autowired
	private WeixinAcctFlowAccoutServiceI weixinAcctFlowAccoutServiceI;
	@RequestMapping(params = "flowCollectList")
    public ModelAndView flowCollectList(HttpServletRequest request) {
        return new ModelAndView("weixin/report/flowCollectList");
    }
	@RequestMapping(params = "FlowCollectdatagrid")
	@DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void userFlowdatagrid(acctAndGroup accten, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws ParseException {
		
        String beginDate = request.getParameter("gettime_begin");
        String endDate = request.getParameter("gettime_end");
        String accountid = ResourceUtil.getWeiXinAccountId();
        
        //List<Map> subAccountIdList = weixinAcctFlowAccoutServiceI.findFlowCollect(accountid);
        
        StringBuffer sql = new StringBuffer();
        sql.append(
        		"select id,operateDate,businessCode,flowType,sum((case when status=1 then flowValue else 0 end)) from userflowgiverecords where 1=1 and flowValue > 0.0 ");
        if (accountid != null && !"".equals(accountid)) {
            sql.append(" and merchantID=").append("'").append(accountid).append("'");
        }
        if (StringUtils.isNotBlank(beginDate)) {
            sql.append(" and DATE_FORMAT(operateDate,'%Y-%m-%d') >= '" + beginDate + "' ");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and DATE_FORMAT(operateDate,'%Y-%m-%d') <= '" + endDate + "' ");
        }
        sql.append(" GROUP BY merchantID,businessCode,flowType,DATE_FORMAT(operateDate,'%Y-%m-%d') ORDER BY operateDate DESC"); // 添加查询的用户记录的时间排序
        HqlQuery hqlQuery = new HqlQuery(UserGiveFlow.class, sql.toString(), dataGrid);
        PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
        List<Object[]> list = pageList.getResultList();
        List<UserGiveFlow> param = new ArrayList<UserGiveFlow>();

        for (Object[] objects : list) {
            UserGiveFlow wd = new UserGiveFlow();
            Object id = objects[0];
            if (id != null) {
                wd.setId(id.toString());
            }
            Object gettime = objects[1];
            if (gettime != null) {
            	SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
            	String datesim = sim.format(gettime);
            	
                wd.setGettime(datesim);
            }
            Object businessCode = objects[2];
            if (businessCode != null) {
            	if(businessCode.equals("1")){
            		wd.setMerchant("移动");
            	}else if(businessCode.equals("2")){
            		wd.setMerchant("联通");
            	}else if(businessCode.equals("3")){
            		wd.setMerchant("电信");
            	}
               
            }
            
      
            Object flowType = objects[3];
            if (flowType != null) {
                wd.setFlowType(flowType.toString());
            }
            
            Object getFlow = objects[4];
            if (getFlow != null) {
                wd.setFlowValue((Double.parseDouble(getFlow.toString())));
            } else {
                wd.setFlowValue(0.0);
            }
            Object getDate = objects[1];
            if (getDate != null) {
                SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
                Date d = sim.parse(getDate.toString());
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String starttime = format.format(d);
                wd.setGettime(starttime);
            }
            
            
            
            param.add(wd);
        }
        
        dataGrid.setResults(param);
        dataGrid.setTotal(pageList.getCount());
        dataGrid.setPage(pageList.getCurPageNO());
        dataGrid.setRows(pageList.getOffset());
        TagUtil.datagrid(response, dataGrid);
	}
	
	
	@RequestMapping(params = "exportXls")
	@DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void exportXls(FlowCollect flowCollect, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "流量赠送汇总(商户)";
            // 根据浏览器进行转码，使其支持中文文件名
            if (BrowserUtils.isIE(request)) {
                response.setHeader("content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(codedFileName, "UTF-8") + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
                response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
            }
            // 产生工作簿对象
            HSSFWorkbook workbook = null;
            
            String beginDate = request.getParameter("gettime_begin");
            String endDate = request.getParameter("gettime_end");
            String accountid = ResourceUtil.getWeiXinAccountId();
            
            //List<Map> subAccountIdList = weixinAcctFlowAccoutServiceI.findFlowCollect(accountid);
            
            StringBuffer sql = new StringBuffer();
            sql.append(
            		"select id,operateDate,businessCode,flowType,sum((case when status=1 then flowValue else 0 end)) from userflowgiverecords where 1=1 and u.flowValue>0.0");
            if (accountid != null && !"".equals(accountid)) {
                sql.append(" and merchantID=").append("'").append(accountid).append("'");
            }
            if (StringUtils.isNotBlank(beginDate)) {
                sql.append(" and DATE_FORMAT(operateDate,'%Y-%m-%d') >= '" + beginDate + "' ");
            }
            if (StringUtils.isNotBlank(endDate)) {
                sql.append(" and DATE_FORMAT(operateDate,'%Y-%m-%d') <= '" + endDate + "' ");
            }
            sql.append(" GROUP BY merchantID,businessCode,flowType,DATE_FORMAT(operateDate,'%Y-%m-%d') ORDER BY operateDate DESC"); // 添加查询的用户记录的时间排序
            dataGrid.setPage(1);
            dataGrid.setRows(65535);
            HqlQuery hqlQuery = new HqlQuery(UserGiveFlow.class, sql.toString(), dataGrid);
            PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
            /* 过滤集合中不等于0.0的流量值 */
            List<Object[]> userflowlist = pageList.getResultList();
            List<FlowCollect> newUserFlowList = new ArrayList<FlowCollect>();
            for (int i = 0; i < userflowlist.size(); i++) {
            	Object[] result = userflowlist.get(i);
            	FlowCollect entity = new FlowCollect();
                     entity.setId(ObjectUtils.toString(result[0], ""));
            	entity.setGettime(ObjectUtils.toString(result[1], ""));
                     entity.setFlowType(ObjectUtils.toString(result[3], ""));
                     
                     Object flowvalue = result[4];
                     if (flowvalue != null) {
                     	entity.setFlowValue((Double.parseDouble(flowvalue.toString())));
                     }
                     
                     if(ObjectUtils.toString(result[3],"").equals("1")){
                    	 entity.setMerchant("移动");
                     }if(ObjectUtils.toString(result[3],"").equals("2")){
                    	 entity.setMerchant("联通");
                     }if(ObjectUtils.toString(result[3],"").equals("3")){
                    	 entity.setMerchant("电信");
                     }
                    	
                
                 
                
                newUserFlowList.add(entity);
                }
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("流量赠送汇总(商户)", "导出人:" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"),
            		FlowCollect.class, newUserFlowList);
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

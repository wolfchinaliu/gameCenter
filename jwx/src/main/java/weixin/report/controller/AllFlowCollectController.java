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
import weixin.report.model.AllFlowCollect;
import weixin.report.model.UserGiveFlow;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.util.CommonUtils;
import weixin.util.DateUtils;

/**
 * 
 * @author PC 流量赠送汇总
 */
@Scope("prototype")
@Controller
@RequestMapping("/allFlowCollectController")
public class AllFlowCollectController extends BaseController {
	private static final Logger LOGGER = Logger.getLogger(AllFlowCollectController.class);
	@Autowired
	private SystemService systemService;
	@Autowired
	private WeixinAcctFlowAccoutServiceI weixinAcctFlowAccoutServiceI;
	
	/**
	 * 页面跳转
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "flowCollectList")
	public ModelAndView flowCollectList(HttpServletRequest request) {

		
		WeixinAcctEntity acctEntity = ResourceUtil.getWeiXinAcct();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("weixin/report/allFlowCollectList");
		modelAndView.addObject("level", acctEntity.getAcctLevel());

		return modelAndView;

	}

	/**
	 * 获取本商户等级传给页面
	 * 
	 * @return
	 */
	/*public Map<String, Object> findLevel() {
		String accountid = ResourceUtil.getWeiXinAccountId();
		
		WeixinAcctEntity acctEntity = ResourceUtil.getWeiXinAcct();
		
		String sql = "select c.acct_level,c.acctForName from weixin_acct c where c.id=(select a.acct_id from weixin_account a where a.id=?)";
		Map<String, Object> level = systemService.findOneForJdbc(sql, accountid);
		return level;
	}*/

	@RequestMapping(params = "FlowCollectdatagrid")
	@DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
	public void userFlowdatagrid(acctAndGroup accten, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) throws ParseException {
		String accountname = request.getParameter("accountname");
		String level = request.getParameter("level");
		String belogAcct = request.getParameter("belogAcct");
		String flowType = request.getParameter("flowType");
		String merchant = request.getParameter("merchant");
		String beginDate = request.getParameter("gettime_begin");
		String endDate = request.getParameter("gettime_end");
		String accountid = ResourceUtil.getWeiXinAccountId();

		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT u.id,u.operateDate,a.acctForName,a.acct_level,pacct.acctForName belogAcct,u.businessCode,u.flowType,sum((case when u.status=1 then flowValue else 0 end)) FROM userflowgiverecords u LEFT JOIN weixin_account w ON u.merchantID = w.id LEFT JOIN weixin_acct a ON w.acct_id = a.id left join weixin_acct pacct on a.pid=pacct.id WHERE 1=1 AND u.flowValue > 0.0 and u.`status` = 1");
		List<String> subAccountIdList = weixinAcctFlowAccoutServiceI.findSubAccountIdList(accountid);
		subAccountIdList.add(accountid);
		if (accountid != null && !"".equals(accountid)) {
			sql.append(" and u.merchantID in (").append(CommonUtils.listToSqlString(subAccountIdList)).append(")");
		}
		if (accountname != null && !"".equals(accountname)) {
			sql.append(" and a.acctForName like").append("'%").append(accountname).append("%'");
		}
		if (merchant != null && !"".equals(merchant)) {
			sql.append(" and u.businessCode like").append("'%").append(merchant).append("%'");
		}
		if (level != null && !"".equals(level)) {
			sql.append(" and a.acct_level=").append("'").append(level).append("'");
		}
		if (belogAcct != null && !"".equals(belogAcct)) {
			sql.append(" and a.pid =").append("'").append(belogAcct).append("'");
		}
		if (flowType != null && !"".equals(flowType)) {
			sql.append(" and u.flowType =").append("'").append(flowType).append("'");
		}
		if (StringUtils.isNotBlank(beginDate)) {
			sql.append(" and DATE_FORMAT(u.operateDate,'%Y-%m-%d') >= '" + beginDate + "' ");
		}
		if (StringUtils.isNotBlank(endDate)) {
			sql.append(" and DATE_FORMAT(u.operateDate,'%Y-%m-%d') <= '" + endDate + "' ");
		}
		sql.append(
				" GROUP BY merchantID,businessCode,flowType,DATE_FORMAT(operateDate,'%Y-%m-%d') ORDER BY operateDate DESC"); // 添加查询的用户记录的时间排序
		HqlQuery hqlQuery = new HqlQuery(AllFlowCollect.class, sql.toString(), dataGrid);
		PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
		List<Object[]> list = pageList.getResultList();
		List<AllFlowCollect> param = new ArrayList<AllFlowCollect>();

		for (Object[] objects : list) {
			AllFlowCollect wd = new AllFlowCollect();
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
			Object acctForName = objects[2];
			if (acctForName != null) {
				wd.setAccountName(acctForName.toString());
				;
			}
			Object acct_level = objects[3];
			if (acct_level != null) {
				if (acct_level.equals("0")) {
					wd.setLevel("S级");
				}
				if (acct_level.equals("1")) {
					wd.setLevel("A级");
				}
				if (acct_level.equals("2")) {
					wd.setLevel("B级");
				}
				if (acct_level.equals("3")) {
					wd.setLevel("c级");
				}
			}
			Object belogAcct1 = objects[4];
			if (belogAcct1 != null) {
				wd.setBelogAcct(belogAcct1.toString());
			}
			Object businessCode = objects[5];
			if (businessCode != null) {
				if (businessCode.equals("1")) {
					wd.setMerchant("移动");
				} else if (businessCode.equals("2")) {
					wd.setMerchant("联通");
				} else if (businessCode.equals("3")) {
					wd.setMerchant("电信");
				}

			}

			Object flowType1 = objects[6];
			if (flowType1 != null) {
				wd.setFlowType(flowType1.toString());
			}

			Object getFlow = objects[7];
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

	/**
	 * excel表格导出
	 * 
	 * @param flowCollect
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "exportXls")
	@DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
	public void exportXls(AllFlowCollect flowCollect, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
		
			WeixinAcctEntity acctEntity = ResourceUtil.getWeiXinAcct();
			String dateTime = DateUtils.date_sdf.format(new Date());
			codedFileName = "[" + acctEntity.getAcctForName() + "]" + "流量赠送汇总" + dateTime;

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

			String accountname = request.getParameter("accountname");
			String levels1 = request.getParameter("level");
			
			String belogAcct = request.getParameter("belogAcct");
			String merchant = request.getParameter("merchant");
			String flowType = request.getParameter("flowType");
			String beginDate = request.getParameter("gettime_begin");
			String endDate = request.getParameter("gettime_end");
			String accountid = ResourceUtil.getWeiXinAccountId();

		

			StringBuffer sql = new StringBuffer();
			sql.append(
					"SELECT u.id,u.operateDate,a.acctForName,a.acct_level,pacct.acctForName belogAcct,u.businessCode,u.flowType,sum((case when u.status=1 then flowValue else 0 end)) FROM userflowgiverecords u LEFT JOIN weixin_account w ON u.merchantID = w.id LEFT JOIN weixin_acct a ON w.acct_id = a.id left join weixin_acct pacct on a.pid=pacct.id WHERE 1=1 AND u.flowValue > 0.0 and u.`status` = 1");
			List<String> subAccountIdList = weixinAcctFlowAccoutServiceI.findSubAccountIdList(accountid);
			subAccountIdList.add(accountid);
			if (accountid != null && !"".equals(accountid)) {
				sql.append(" and u.merchantID in (").append(CommonUtils.listToSqlString(subAccountIdList)).append(")");
			}
			if (accountname != null && !"".equals(accountname)) {
				sql.append(" and a.acctForName like").append("'%").append(accountname).append("%'");
			}
			if (merchant != null && !"".equals(merchant)) {
				sql.append(" and u.businessCode like").append("'%").append(merchant).append("%'");
			}
			if (levels1 != null && !"".equals(levels1)) {
				sql.append(" and a.acct_level=").append("'").append(levels1).append("'");
			}
			if (belogAcct != null && !"".equals(belogAcct)) {
				sql.append(" and a.pid =").append("'").append(belogAcct).append("'");
			}
			if (flowType != null && !"".equals(flowType)) {
				sql.append(" and u.flowType =").append("'").append(flowType).append("'");
			}
			if (StringUtils.isNotBlank(beginDate)) {
				sql.append(" and DATE_FORMAT(u.operateDate,'%Y-%m-%d') >= '" + beginDate + "' ");
			}
			if (StringUtils.isNotBlank(endDate)) {
				sql.append(" and DATE_FORMAT(u.operateDate,'%Y-%m-%d') <= '" + endDate + "' ");
			}
			sql.append(
					" GROUP BY merchantID,businessCode,flowType,DATE_FORMAT(operateDate,'%Y-%m-%d') ORDER BY operateDate asc"); // 添加查询的用户记录的时间排序
			dataGrid.setPage(1);
			dataGrid.setRows(65535);
			HqlQuery hqlQuery = new HqlQuery(UserGiveFlow.class, sql.toString(), dataGrid,65535);
			PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
			/* 过滤集合中不等于0.0的流量值 */
			List<Object[]> userflowlist = pageList.getResultList();
			List<AllFlowCollect> newUserFlowList = new ArrayList<AllFlowCollect>();
			for (int i = 0; i < userflowlist.size(); i++) {
				Object[] result = userflowlist.get(i);
				AllFlowCollect entity = new AllFlowCollect();
				entity.setId(ObjectUtils.toString(result[0], ""));
				Object getDate = result[1];
				if (getDate != null) {
					SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
					Date d = sim.parse(getDate.toString());
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String starttime = format.format(d);
					entity.setGettime(starttime);
				}
				entity.setAccountName(ObjectUtils.toString(result[2], ""));
				Object levels = result[3];
				if (levels != null) {
					if (levels.equals("0")) {
						entity.setLevel("S级");
					}
					if (levels.equals("1")) {
						entity.setLevel("A级");
					}
					if (levels.equals("2")) {
						entity.setLevel("B级");
					}
					if (levels.equals("3")) {
						entity.setLevel("C级");
					}

				}
				entity.setBelogAcct(ObjectUtils.toString(result[4], ""));

				if (ObjectUtils.toString(result[5], "").equals("1")) {
					entity.setMerchant("移动");
				}
				if (ObjectUtils.toString(result[5], "").equals("2")) {
					entity.setMerchant("联通");
				}
				if (ObjectUtils.toString(result[5], "").equals("3")) {
					entity.setMerchant("电信");
				}

				entity.setFlowType(ObjectUtils.toString(result[6], ""));

				Object flowvalue = result[7];
				if (flowvalue != null) {
					entity.setFlowValue((Double.parseDouble(flowvalue.toString())));
				}

				newUserFlowList.add(entity);
			}
			workbook = ExcelExportUtil.exportExcel(
					new ExcelTitle("流量赠送汇总", "导出人:" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"),
					AllFlowCollect.class, newUserFlowList);
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

package weixin.report.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
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
import weixin.report.model.MerchantGiveFlowListEntity;
import weixin.report.model.UserGiveFlow;
import weixin.report.model.UserGiveFlowEntity;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.tenant.service.WeixinAcctServiceI;
import weixin.util.CommonUtils;
import weixin.util.DateUtils;

/**
 * 流量赠送清单
 * 
 * @author PC
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/merchantGiveFlowController")
public class MerchantGiveFlowController extends BaseController {

	private static final Logger LOGGER = Logger.getLogger(MerchantGiveFlowController.class);
	@Autowired
	private WeixinAcctServiceI acctService;
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
	@RequestMapping(params = "merchantGiveFlowList")
	public ModelAndView merchantGiveFlowList(HttpServletRequest request) {
		
		WeixinAcctEntity acctEntity = ResourceUtil.getWeiXinAcct();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("weixin/report/merchantGiveFlowlist");
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
		String sql = "select c.acct_level,c.acctForName from weixin_acct c where c.id=(select a.acct_id from weixin_account a where a.id=?)";
		Map<String, Object> level = systemService.findOneForJdbc(sql, accountid);
		return level;
	}*/

	@RequestMapping(params = "merchantFlowdatagrid")
	@DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
	public void userFlowdatagrid(acctAndGroup accten, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) throws ParseException {
		String nickname = request.getParameter("nickname");
		// 手机号查询
		String phoneNumber = request.getParameter("phoneNumber");

		String flowType1 = request.getParameter("flowType");
		/*
		 * if(flowType1 !=null && !"".equals(flowType1)){
		 * if(flowType1.equals("2")){ flowType1 ="省内";
		 * }if(flowType1.equals("1")){ flowType1 ="全国"; } }
		 */
		String hdType1 = request.getParameter("hdType");

		String beginDate = request.getParameter("gettime_begin");

		String endDate = request.getParameter("gettime_end");

		String acctForName = request.getParameter("accountname");

		String belogAcct = request.getParameter("belogAcct");

		String level = request.getParameter("level");

		String merchant = request.getParameter("merchant");

		String accountid = ResourceUtil.getWeiXinAccountId();
		/*
		 * String merchantId = ResourceUtil.getWeiXinAcctId(); if
		 * (StringUtils.isEmpty(merchantId)) { this.buildNullResult(response,
		 * dataGrid); return; } List<WeixinAcctEntity> subAccts = null;
		 * if(StringUtils.isBlank(belogAcct)){ subAccts =
		 * this.acctService.getAllSubAcct(merchantId); } else { subAccts =
		 * this.acctService.getAllSubAcct(belogAcct); }
		 * if(CollectionUtils.isEmpty(subAccts)){ this.buildNullResult(response,
		 * dataGrid); return; }
		 */
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select u.id,m.nick_name,c.acctForName,c.acct_level,u.flowType,u.reason,u.operateDate,u.flowValue,u.phoneNumber,u.merchantID,u.businessCode,pacct.acctForName belogAcct from userflowgiverecords u left JOIN weixin_account w on u.merchantID=w.id left JOIN weixin_acct c on w.acct_id=c.id left join weixin_acct pacct ON c.pid = pacct.id LEFT JOIN weixin_member m ON u.merchantID = m.account_id AND u.phoneNumber = m.phoneNumber and u.openid=m.open_id where 1=1 and u.flowValue>0.0 and u.`status` = 1");
		List<String> subAccountIdList = weixinAcctFlowAccoutServiceI.findSubAccountIdList(accountid);
		subAccountIdList.add(accountid);

		if (accountid != null && !"".equals(accountid)) {
			/*
			 * if ("2".equals(scope)) {// 全部商户 subAccountIdList.add(accountid);
			 * } if ("1".equals(scope)) {// 本商户流量汇总报表 subAccountIdList.clear();
			 * subAccountIdList.add(accountid); } if ("0".equals(scope)) {//
			 * 下级商户 //do nothing
			 * 
			 * } else { subAccountIdList.clear();
			 * subAccountIdList.add(accountid); }
			 */
			sql.append(" and u.merchantID in (").append(CommonUtils.listToSqlString(subAccountIdList)).append(")");
		}
		if (belogAcct != null && !"".equals(belogAcct)) {
			sql.append(" and c.pid =").append("'").append(belogAcct).append("'");
		}
		if (nickname != null && !"".equals(nickname)) {
			sql.append(" and m.nick_name like").append("'%").append(nickname).append("%'");
		}
		if (acctForName != null && !"".equals(acctForName)) {
			sql.append(" and c.acctForName like").append("'%").append(acctForName).append("%'");
		}
		if (level != null && !"".equals(level)) {
			sql.append(" and c.acct_level =").append("'").append(level).append("'");
		}
		if (merchant != null && !"".equals(merchant)) {
			sql.append(" and u.businessCode=").append("'").append(merchant).append("'");
		}

		if (phoneNumber != null && !"".equals(phoneNumber)) {
			sql.append(" and u.phoneNumber=").append("'").append(phoneNumber).append("'");
		}
		if (flowType1 != null && !"".equals(flowType1)) {
			sql.append(" and u.flowType =").append("'").append(flowType1).append("'");
		}
		if (StringUtils.isNotBlank(beginDate)) {
			sql.append(" and DATE_FORMAT(u.operateDate,'%Y-%m-%d') >= '" + beginDate + "' ");
		}
		if (StringUtils.isNotBlank(endDate)) {
			sql.append(" and DATE_FORMAT(u.operateDate,'%Y-%m-%d') <= '" + endDate + "' ");
		}
		if (hdType1 != null && !"".equals(hdType1)) {
			sql.append(" and u.reason like").append("'%").append(hdType1).append("%'");
		}
		sql.append(" ORDER BY operateDate DESC"); // 添加查询的用户记录的时间排序
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
			Object name = objects[1];
			if (name != null) {
				wd.setNickname(name.toString());
			}
			Object accountname = objects[2];
			if (accountname != null) {
				wd.setAccountName(accountname.toString());
			}
			Object levels = objects[3];
			if (levels != null) {
				if (levels.equals("0")) {
					wd.setLevel("S级");
				}
				if (levels.equals("1")) {
					wd.setLevel("A级");
				}
				if (levels.equals("2")) {
					wd.setLevel("B级");
				}
				if (levels.equals("3")) {
					wd.setLevel("c级");
				}

			}

			Object flowType = objects[4];
			if (flowType != null) {
				wd.setFlowType(flowType.toString());
			}
			Object hdType = objects[5];
			if (hdType != null) {
				wd.setHdType(hdType.toString());
			}
			Object getDate = objects[6];
			if (getDate != null) {
				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date d = sim.parse(getDate.toString());
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String starttime = format.format(d);
				wd.setGettime(starttime);
			}
			Object getFlow = objects[7];
			if (getFlow != null) {
				wd.setFlowValue((Double.parseDouble(getFlow.toString())));
			} else {
				wd.setFlowValue(0.0);
			}

			Object phone = objects[8];
			if (phone != null) {
				phone = phone.toString();
				wd.setPhoneNumber(phone.toString());
			}
			Object accountId = objects[9];
			if (accountId != null) {
				wd.setAccountId(accountId.toString());
			}
			Object businessCode = objects[10];
			if (businessCode != null) {
				if (businessCode.equals("1")) {
					wd.setMerchant("移动");
				} else if (businessCode.equals("2")) {
					wd.setMerchant("联通");
				} else if (businessCode.equals("3")) {
					wd.setMerchant("电信");
				}

			}
			Object belogAccts = objects[11];
			if (belogAccts != null) {
				wd.setBelogAcct(belogAccts.toString());
				;
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
	 * 导出excel表格
	 * 
	 * @param merchantGiveFlowListEntity
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "exportXls")
	@DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
	public void exportXls(MerchantGiveFlowListEntity merchantGiveFlowListEntity, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			
			WeixinAcctEntity acctEntity = ResourceUtil.getWeiXinAcct();
			String dateTime = DateUtils.date_sdf.format(new Date());
			codedFileName = "[" + acctEntity.getAcctForName() + "]" + "流量赠送清单" + dateTime;
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
			String nickname = request.getParameter("nickname");
			String acctForName = request.getParameter("accountname");
			String levels = request.getParameter("level");

			String merchant = request.getParameter("merchant");

			// 手机号查询
			String phoneNumber = request.getParameter("phoneNumber");

			String flowType1 = request.getParameter("flowType");

			String hdType1 = request.getParameter("hdType");

			String beginDate = request.getParameter("gettime_begin");

			String endDate = request.getParameter("gettime_end");
			String belogAcct = request.getParameter("belogAcct");

			String accountid = ResourceUtil.getWeiXinAccountId();
			StringBuffer sql = new StringBuffer();
			sql.append(
					"select u.id,m.nick_name,c.acctForName,c.acct_level,u.flowType,u.reason,u.operateDate,u.flowValue,u.phoneNumber,u.merchantID,u.businessCode,pacct.acctForName belogAcct from userflowgiverecords u left JOIN weixin_account w on u.merchantID=w.id left JOIN weixin_acct c on w.acct_id=c.id left join weixin_acct pacct ON c.pid = pacct.id LEFT JOIN weixin_member m ON u.merchantID = m.account_id AND u.phoneNumber = m.phoneNumber and u.openid=m.open_id where 1=1 and u.flowValue>0.0 and u.`status` = 1");
			List<String> subAccountIdList = weixinAcctFlowAccoutServiceI.findSubAccountIdList(accountid);
			subAccountIdList.add(accountid);

			if (accountid != null && !"".equals(accountid)) {
				/*
				 * if ("2".equals(scope)) {// 全部商户
				 * subAccountIdList.add(accountid); } if ("1".equals(scope)) {//
				 * 本商户流量汇总报表 subAccountIdList.clear();
				 * subAccountIdList.add(accountid); } if ("0".equals(scope)) {//
				 * 下级商户 //do nothing
				 * 
				 * } else { subAccountIdList.clear();
				 * subAccountIdList.add(accountid); }
				 */
				sql.append(" and u.merchantID in (").append(CommonUtils.listToSqlString(subAccountIdList)).append(")");
			}
			if (belogAcct != null && !"".equals(belogAcct)) {
				sql.append(" and c.pid =").append("'").append(belogAcct).append("'");
			}
			if (nickname != null && !"".equals(nickname)) {
				sql.append(" and m.nick_name like").append("'%").append(nickname).append("%'");
			}
			if (acctForName != null && !"".equals(acctForName)) {
				sql.append(" and c.acctForName like").append("'%").append(acctForName).append("%'");
			}
			if (levels != null && !"".equals(levels)) {
				sql.append(" and c.acct_level =").append("'").append(levels).append("'");
			}
			if (merchant != null && !"".equals(merchant)) {
				sql.append(" and u.businessCode=").append("'").append(merchant).append("'");
			}

			if (phoneNumber != null && !"".equals(phoneNumber)) {
				sql.append(" and u.phoneNumber=").append("'").append(phoneNumber).append("'");
			}
			if (flowType1 != null && !"".equals(flowType1)) {
				sql.append(" and u.flowType =").append("'").append(flowType1).append("'");
			}
			if (StringUtils.isNotBlank(beginDate)) {
				sql.append(" and DATE_FORMAT(u.operateDate,'%Y-%m-%d') >= '" + beginDate + "' ");
			}
			if (StringUtils.isNotBlank(endDate)) {
				sql.append(" and DATE_FORMAT(u.operateDate,'%Y-%m-%d') <= '" + endDate + "' ");
			}
			if (hdType1 != null && !"".equals(hdType1)) {
				sql.append(" and u.reason like").append("'%").append(hdType1).append("%'");
			}
			sql.append(" ORDER BY operateDate asc"); // 添加查询的用户记录的时间排序
			dataGrid.setPage(1);
			dataGrid.setRows(65535);
			HqlQuery hqlQuery = new HqlQuery(UserGiveFlow.class, sql.toString(), dataGrid,65535);
			PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
			/* 过滤集合中不等于0.0的流量值 */
			List<Object[]> userflowlist = pageList.getResultList();
			List<MerchantGiveFlowListEntity> newUserFlowList = new ArrayList<MerchantGiveFlowListEntity>();
			for (int i = 0; i < userflowlist.size(); i++) {
				Object[] result = userflowlist.get(i);
				MerchantGiveFlowListEntity entity = new MerchantGiveFlowListEntity();
				entity.setOperator(ObjectUtils.toString(result[1], ""));
				entity.setAccountname(ObjectUtils.toString(result[2], ""));
				String string = ObjectUtils.toString(result[3], "");
				if (ObjectUtils.toString(result[3], "").equals("0")) {
					entity.setLevel("S级");
				}
				if (ObjectUtils.toString(result[3], "").equals("1")) {
					entity.setLevel("A级");
				}
				if (ObjectUtils.toString(result[3], "").equals("2")) {
					entity.setLevel("B级");
				}
				if (ObjectUtils.toString(result[3], "").equals("3")) {
					entity.setLevel("C级");
				}

				entity.setFlowType(ObjectUtils.toString(result[4], ""));
				entity.setReason(ObjectUtils.toString(result[5], ""));
				if (ObjectUtils.toString(result[10], "").equals("1")) {
					entity.setMerchant("移动");
				} else if (ObjectUtils.toString(result[10], "").equals("2")) {
					entity.setMerchant("联通");
				} else if (ObjectUtils.toString(result[10], "").equals("3")) {
					entity.setMerchant("电信");
				}

				Object operatedate = result[6];
				if (operatedate != null) {
					String shortOperateDate = DateUtils.date_sdf.format(operatedate);

					entity.setShortOperateDate(shortOperateDate);
					entity.setOperateDate((Date) operatedate);
				}
				Object flowvalue = result[7];
				if (flowvalue != null) {
					entity.setFlowValue((Double.parseDouble(flowvalue.toString())));
				}

				if (acctEntity.getAcctLevel().equals(levels)) {
					entity.setPhoneNumber(ObjectUtils.toString(result[8], ""));
				} else {
					entity.setPhoneNumber(
							ObjectUtils.toString(result[8], "").replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
				}
				entity.setBelogAcct(ObjectUtils.toString(result[11], ""));
				newUserFlowList.add(entity);
			}
			workbook = ExcelExportUtil.exportExcel(
					new ExcelTitle("流量赠送清单", "导出人:" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"),
					MerchantGiveFlowListEntity.class, newUserFlowList);
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
	 * 导出txt文本
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "portTxt")
	@DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
	public void portTxt(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
			throws UnsupportedEncodingException {
		// String userId = ServletRequestUtils.getStringParameter(request,
		// "userId", "test");
		ModelAndView mav = new ModelAndView();
		String nickname = request.getParameter("nickname");
		String acctForName = request.getParameter("accountname");
		String levels = request.getParameter("level");

		String merchant = request.getParameter("merchant");

		//Map<String, Object> level = this.findLevel();
		// 手机号查询
		String phoneNumber = request.getParameter("phoneNumber");

		String flowType1 = request.getParameter("flowType");

		String hdType1 = request.getParameter("hdType");

		String beginDate = request.getParameter("gettime_begin");

		String endDate = request.getParameter("gettime_end");
		String belogAcct = request.getParameter("belogAcct");

		String accountid = ResourceUtil.getWeiXinAccountId();
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select u.id,m.nick_name,c.acctForName,c.acct_level,u.flowType,u.reason,u.operateDate,u.flowValue,u.phoneNumber,u.merchantID,u.businessCode,pacct.acctForName belogAcct from userflowgiverecords u left JOIN weixin_account w on u.merchantID=w.id left JOIN weixin_acct c on w.acct_id=c.id left join weixin_acct pacct ON c.pid = pacct.id LEFT JOIN weixin_member m ON u.merchantID = m.account_id AND u.phoneNumber = m.phoneNumber and u.openid=m.open_id where 1=1 and u.flowValue>0.0 and u.`status` = 1");
		List<String> subAccountIdList = weixinAcctFlowAccoutServiceI.findSubAccountIdList(accountid);
		subAccountIdList.add(accountid);

		if (accountid != null && !"".equals(accountid)) {
			/*
			 * if ("2".equals(scope)) {// 全部商户 subAccountIdList.add(accountid);
			 * } if ("1".equals(scope)) {// 本商户流量汇总报表 subAccountIdList.clear();
			 * subAccountIdList.add(accountid); } if ("0".equals(scope)) {//
			 * 下级商户 //do nothing
			 * 
			 * } else { subAccountIdList.clear();
			 * subAccountIdList.add(accountid); }
			 */
			sql.append(" and u.merchantID in (").append(CommonUtils.listToSqlString(subAccountIdList)).append(")");
		}
		if (belogAcct != null && !"".equals(belogAcct)) {
			sql.append(" and c.pid =").append("'").append(belogAcct).append("'");
		}
		if (nickname != null && !"".equals(nickname)) {
			sql.append(" and m.nick_name like").append("'%").append(nickname).append("%'");
		}
		if (acctForName != null && !"".equals(acctForName)) {
			sql.append(" and c.acctForName like").append("'%").append(acctForName).append("%'");
		}
		if (levels != null && !"".equals(levels)) {
			sql.append(" and c.acct_level =").append("'").append(levels).append("'");
		}
		if (merchant != null && !"".equals(merchant)) {
			sql.append(" and u.businessCode=").append("'").append(merchant).append("'");
		}

		if (phoneNumber != null && !"".equals(phoneNumber)) {
			sql.append(" and u.phoneNumber=").append("'").append(phoneNumber).append("'");
		}
		if (flowType1 != null && !"".equals(flowType1)) {
			sql.append(" and u.flowType =").append("'").append(flowType1).append("'");
		}
		if (StringUtils.isNotBlank(beginDate)) {
			sql.append(" and DATE_FORMAT(u.operateDate,'%Y-%m-%d') >= '" + beginDate + "' ");
		}
		if (StringUtils.isNotBlank(endDate)) {
			sql.append(" and DATE_FORMAT(u.operateDate,'%Y-%m-%d') <= '" + endDate + "' ");
		}
		if (hdType1 != null && !"".equals(hdType1)) {
			sql.append(" and u.reason like").append("'%").append(hdType1).append("%'");
		}
		sql.append(" ORDER BY operateDate asc"); // 添加查询的用户记录的时间排序
		dataGrid.setPage(1);
		dataGrid.setRows(65535);
		HqlQuery hqlQuery = new HqlQuery(UserGiveFlow.class, sql.toString(), dataGrid,65535);
		PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
		List<Object[]> userflowlist = pageList.getResultList();
		WeixinAcctEntity acctEntity = ResourceUtil.getWeiXinAcct();
		List<MerchantGiveFlowListEntity> newUserFlowList = new ArrayList<MerchantGiveFlowListEntity>();
		for (int i = 0; i < userflowlist.size(); i++) {
			Object[] result = userflowlist.get(i);
			MerchantGiveFlowListEntity entity = new MerchantGiveFlowListEntity();
			entity.setOperator(ObjectUtils.toString(result[1], ""));
			entity.setAccountname(ObjectUtils.toString(result[2], ""));
			String string = ObjectUtils.toString(result[3], "");
			if (ObjectUtils.toString(result[3], "").equals("0")) {
				entity.setLevel("S级");
			}
			if (ObjectUtils.toString(result[3], "").equals("1")) {
				entity.setLevel("A级");
			}
			if (ObjectUtils.toString(result[3], "").equals("2")) {
				entity.setLevel("B级");
			}
			if (ObjectUtils.toString(result[3], "").equals("3")) {
				entity.setLevel("C级");
			}

			entity.setFlowType(ObjectUtils.toString(result[4], ""));
			entity.setReason(ObjectUtils.toString(result[5], ""));
			if (ObjectUtils.toString(result[10], "").equals("1")) {
				entity.setMerchant("移动");
			} else if (ObjectUtils.toString(result[10], "").equals("2")) {
				entity.setMerchant("联通");
			} else if (ObjectUtils.toString(result[10], "").equals("3")) {
				entity.setMerchant("电信");
			}

			Object operatedate = result[6];
			if (operatedate != null) {
				String shortOperateDate = DateUtils.date_sdf.format(operatedate);

				entity.setShortOperateDate(shortOperateDate);
				entity.setOperateDate((Date) operatedate);
			}
			Object flowvalue = result[7];
			if (flowvalue != null) {
				entity.setFlowValue((Double.parseDouble(flowvalue.toString())));
			}

			if (acctEntity.getAcctLevel().equals(levels)) {
				entity.setPhoneNumber(ObjectUtils.toString(result[8], ""));
			} else {
				entity.setPhoneNumber(
						ObjectUtils.toString(result[8], "").replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
			}
			entity.setBelogAcct(ObjectUtils.toString(result[11], ""));
			newUserFlowList.add(entity);
		}
		// 导出txt文件
		response.setContentType("text/plain");
		
		String dateTime = DateUtils.date_sdf.format(new Date());
		
		String fileName = "[" + acctEntity.getAcctForName() + "]" + "流量赠送清单" + dateTime;
		// String fileName="流量赠送清单";
		/*
		 * try { fileName = URLEncoder.encode(fileName, "UTF-8"); } catch
		 * (UnsupportedEncodingException e1) { // TODO Auto-generated catch
		 * block e1.printStackTrace(); }
		 * response.setHeader("Content-Disposition","attachment; filename=" +
		 * fileName + ".txt");
		 */
		// 根据浏览器进行转码，使其支持中文文件名
		if (BrowserUtils.isIE(request)) {
			response.setHeader("content-disposition",
					"attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8") + ".txt");
		} else {
			String newtitle = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".txt");
		}

		BufferedOutputStream buff = null;
		StringBuffer write = new StringBuffer();
		String enter = "\r\n";
		ServletOutputStream outSTr = null;
		try {
			outSTr = response.getOutputStream(); // 建立
			buff = new BufferedOutputStream(outSTr);
			// 把内容写入文件
			if (newUserFlowList.size() > 0) {
				for (int i = 0; i < userflowlist.size(); i++) {
					write.append(newUserFlowList.get(i).getOperator() + ",");
					write.append(newUserFlowList.get(i).getAccountname() + ",");
					write.append(newUserFlowList.get(i).getLevel() + ",");
					write.append(newUserFlowList.get(i).getBelogAcct() + ",");
					write.append(newUserFlowList.get(i).getPhoneNumber() + ",");
					String flowType = newUserFlowList.get(i).getFlowType();
					if (flowType.equals("1")) {
						flowType = "全国流量";
					}
					if (flowType.equals("2")) {
						flowType = " 省内流量";
					}
					write.append(flowType + ",");
					write.append(newUserFlowList.get(i).getMerchant() + ",");
					write.append(newUserFlowList.get(i).getReason() + ",");
					write.append(newUserFlowList.get(i).getFlowValue() + ",");
					write.append(newUserFlowList.get(i).getOperateDate() + ",");
					write.append(newUserFlowList.get(i).getShortOperateDate());
					write.append(enter);
				}
			}
			buff.write(write.toString().getBytes("UTF-8"));
			buff.flush();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buff.close();
				outSTr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

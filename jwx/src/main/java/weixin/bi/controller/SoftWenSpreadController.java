package weixin.bi.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import weixin.liuliangbao.jsonbean.ShareItem;
import weixin.liuliangbao.jsonbean.ShareRecordBean;
import weixin.liuliangbao.jsonbean.ShareRecordEntity;
import weixin.liuliangbao.jsonbean.AccountGroup.acctAndGroup;
import weixin.report.model.AllFlowCollect;
import weixin.report.model.UserGiveFlow;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.util.CommonUtils;
import weixin.util.DateUtils;

/**
 * 下级商户软文传播统计
 * 
 * @author PC
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/softWenSpreadController")
public class SoftWenSpreadController extends BaseController {

	private static final Logger LOGGER = Logger.getLogger(SoftWenSpreadController.class);
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
	@RequestMapping(params = "softWenSpread")
	public ModelAndView SoftWenSpread(HttpServletRequest request) {
		return new ModelAndView("weixin/report/softWenSpread");
	}

	@RequestMapping(params = "softWenSpreadDatagrid")
	@DataSourceSwitch(dataSource = DataSourceType.dataSource_slave)
	public void softWenSpreadDatagrid(acctAndGroup accten, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) throws ParseException {

		String beginDate = request.getParameter("createtime_begin");

		String endDate = request.getParameter("createtime_end");

		String acctForName = request.getParameter("acctForName");

		String belogAcct = request.getParameter("belogAcct");

		String level = request.getParameter("level");

		String title = request.getParameter("title");

		String accountid = ResourceUtil.getWeiXinAccountId();
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT DATE_FORMAT(sh.Createtime, '%Y-%m-%d'),wa.acctForName,wa.acct_level,acct.acctForName begloName,sh.title,count(case when reason='1' then reason end) readNumber,count(case when reason='2' then reason end) shareNumber FROM (select * from sharerecord ORDER BY createTime desc) sh LEFT JOIN weixin_acct wa ON wa.id=(SELECT acct_id FROM weixin_account wac WHERE wac.id = sh.accountid) LEFT JOIN weixin_acct acct ON wa.pid = acct.id where 1=1");
		List<String> subAccountIdList = weixinAcctFlowAccoutServiceI.findSubAccountIdList(accountid);

		// 不包含商户自己的id
		// subAccountIdList.add(accountid);
		if(subAccountIdList.size() ==0){
			subAccountIdList.add("");
		}
			sql.append(" and sh.accountid in (").append(CommonUtils.listToSqlString(subAccountIdList)).append(")");
		
		if (belogAcct != null && !"".equals(belogAcct)) {
			sql.append(" and wa.pid =").append("'").append(belogAcct).append("'");
		}
		if (acctForName != null && !"".equals(acctForName)) {
			sql.append(" and wa.acctForName like").append("'%").append(acctForName).append("%'");
		}
		if (title != null && !"".equals(title)) {
			sql.append(" and sh.title like").append("'%").append(title).append("%'");
		}
		if (level != null && !"".equals(level)) {
			sql.append(" and wa.acct_level =").append("'").append(level).append("'");
		}

		if (StringUtils.isNotBlank(beginDate)) {
			sql.append(" and DATE_FORMAT(sh.Createtime,'%Y-%m-%d') >= '" + beginDate + "' ");
		}
		if (StringUtils.isNotBlank(endDate)) {
			sql.append(" and DATE_FORMAT(sh.Createtime,'%Y-%m-%d') <= '" + endDate + "' ");
		}

		sql.append(" GROUP BY DATE_FORMAT(sh.Createtime, '%Y-%m-%d'),sh.shareId ORDER BY sh.Createtime desc"); // 添加查询的用户记录的时间排序
		HqlQuery hqlQuery = new HqlQuery(ShareItem.class, sql.toString(), dataGrid);
		PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
		List<Object[]> list = pageList.getResultList();
		List<ShareRecordBean> param = new ArrayList<ShareRecordBean>();

		for (Object[] objects : list) {
			ShareRecordBean sr = new ShareRecordBean();
			Object getDate = objects[0];
			if (getDate != null) {
				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
				Date d = sim.parse(getDate.toString());
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String createtime = format.format(d);

				sr.setCreatetime(createtime);
			}

			Object acctForNmae = objects[1];
			if (acctForNmae != null) {
				sr.setAcctForName(acctForNmae.toString());
			}
			Object levels = objects[2];
			if (levels != null) {
				if (levels.equals("0")) {
					sr.setLevel("S级");
				}
				if (levels.equals("1")) {
					sr.setLevel("A级");
				}
				if (levels.equals("2")) {
					sr.setLevel("B级");
				}
				if (levels.equals("3")) {
					sr.setLevel("c级");
				}

			}
			Object belogAccts = objects[3];
			if (belogAccts != null) {
				sr.setBelogAcct(belogAccts.toString());

			}
			Object articleNames = objects[4];
			if (articleNames != null) {
				sr.setTitle(articleNames.toString());

			}
			Object readNumber = objects[5];
			if (readNumber != null) {
				sr.setReadNumber(readNumber.toString());
			}
			Object shareNumber = objects[6];
			if (shareNumber != null) {
				sr.setShareNumber(shareNumber.toString());

			}	
			param.add(sr);
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
	@DataSourceSwitch(dataSource = DataSourceType.dataSource_slave)
	public void exportXls(AllFlowCollect flowCollect, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {

			WeixinAcctEntity acctEntity = ResourceUtil.getWeiXinAcct();
			String dateTime = DateUtils.date_sdf.format(new Date());
			codedFileName = "[" + acctEntity.getAcctForName() + "]" + "下级商户软文传播" + dateTime;

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

			String beginDate = request.getParameter("createtime_begin");

			String endDate = request.getParameter("createtime_end");

			String acctForName = request.getParameter("acctForName");

			String belogAcct = request.getParameter("belogAcct");

			String level = request.getParameter("level");

			String title = request.getParameter("title");

			String accountid = ResourceUtil.getWeiXinAccountId();
			StringBuffer sql = new StringBuffer();
			sql.append(
					"SELECT DATE_FORMAT(sh.Createtime, '%Y-%m-%d'),wa.acctForName,wa.acct_level,acct.acctForName begloName,sh.title,count(case when reason='1' then reason end) readNumber,count(case when reason='2' then reason end) shareNumber FROM (select * from sharerecord ORDER BY createTime desc) sh LEFT JOIN weixin_acct wa ON wa.id=(SELECT acct_id FROM weixin_account wac WHERE wac.id = sh.accountid) LEFT JOIN weixin_acct acct ON wa.pid = acct.id where 1=1");
			List<String> subAccountIdList = weixinAcctFlowAccoutServiceI.findSubAccountIdList(accountid);

			// 不包含商户自己的id
			// subAccountIdList.add(accountid);
			if(subAccountIdList.size() ==0){
				subAccountIdList.add("");
			}
				sql.append(" and sh.accountid in (").append(CommonUtils.listToSqlString(subAccountIdList)).append(")");
			
			if (belogAcct != null && !"".equals(belogAcct)) {
				sql.append(" and wa.pid =").append("'").append(belogAcct).append("'");
			}
			if (acctForName != null && !"".equals(acctForName)) {
				sql.append(" and wa.acctForName like").append("'%").append(acctForName).append("%'");
			}
			if (title != null && !"".equals(title)) {
				sql.append(" and sh.title like").append("'%").append(title).append("%'");
			}
			if (level != null && !"".equals(level)) {
				sql.append(" and wa.acct_level =").append("'").append(level).append("'");
			}

			if (StringUtils.isNotBlank(beginDate)) {
				sql.append(" and DATE_FORMAT(sh.Createtime,'%Y-%m-%d') >= '" + beginDate + "' ");
			}
			if (StringUtils.isNotBlank(endDate)) {
				sql.append(" and DATE_FORMAT(sh.Createtime,'%Y-%m-%d') <= '" + endDate + "' ");
			}

			sql.append(" GROUP BY DATE_FORMAT(sh.Createtime, '%Y-%m-%d'),sh.shareId ORDER BY sh.Createtime asc"); // 添加查询的用户记录的时间排序
			dataGrid.setPage(1);
			dataGrid.setRows(65535);
			HqlQuery hqlQuery = new HqlQuery(UserGiveFlow.class, sql.toString(), dataGrid,65535);
			PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
			List<Object[]> userflowlist = pageList.getResultList();
			List<ShareRecordBean> shareRecordBean = new ArrayList<ShareRecordBean>();
			for (int i = 0; i < userflowlist.size(); i++) {
				Object[] result = userflowlist.get(i);
				ShareRecordBean sr = new ShareRecordBean();
				Object getDate = result[0];
				if (getDate != null) {
					SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
					Date d = sim.parse(getDate.toString());
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String createtime = format.format(d);

					sr.setCreatetime(createtime);
				}

				Object acctForNmae = result[1];
				if (acctForNmae != null) {
					sr.setAcctForName(acctForNmae.toString());
				}
				Object levels = result[2];
				if (levels != null) {
					sr.setLevel(levels.toString());

				}
				Object belogAccts = result[3];
				if (belogAccts != null) {
					sr.setBelogAcct(belogAccts.toString());

				}
				Object articleNames = result[4];
				if (articleNames != null) {
					sr.setTitle(articleNames.toString());

				}
				Object readNumber = result[5];
				if (readNumber != null) {
					sr.setReadNumber(readNumber.toString());

				}
				Object shareNumber = result[6];
				if (shareNumber != null) {
					sr.setShareNumber(shareNumber.toString());

				}
				shareRecordBean.add(sr);
			}
			workbook = ExcelExportUtil.exportExcel(
					new ExcelTitle("下级商户软文传播", "导出人:" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"),
					ShareRecordBean.class, shareRecordBean);
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

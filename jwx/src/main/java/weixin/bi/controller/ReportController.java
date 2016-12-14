package weixin.bi.controller;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.DBTypeUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.report.datastatistics.useranalysis.JwUserAnalysisAPI;
import org.jeewx.api.report.datastatistics.useranalysis.model.UserAnalysisRtnInfo;

import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.tenant.entity.WeixinAnnouncementEntity;

/**
 * 报表
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/reportController")
public class ReportController extends BaseController {

	private String message;

	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	
	private SystemService systemService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	
	/**
	 * 用户分析报表
	 * 
	 * @return
	 */
	@RequestMapping(params = "memberList")
	public ModelAndView memberList(HttpServletRequest request) {
		
		String begin_date = request.getParameter("begin_date");
		if(StringUtil.isEmpty(begin_date))
			begin_date = getUpMothDate();
		
		String end_date = request.getParameter("end_date");
		if(StringUtil.isEmpty(end_date))
			end_date = getMothDate();
		
		List<UserAnalysisRtnInfo> memberList;
		
		try {
			
			memberList = JwUserAnalysisAPI.getUserSummary(weixinAccountService.getAccessToken(), begin_date, end_date);
			request.setAttribute("memberList", memberList);
		} catch (WexinReqException e) {
			
			e.printStackTrace();
		}
		return new ModelAndView("weixin/report/memberList");
	}
	
	
	
	@RequestMapping(params = "members")
	public ModelAndView members(HttpServletRequest request) {
		
		return new ModelAndView("weixin/report/members");
	}
	
	
	
	@RequestMapping(params = "listMember")
	public void listMember(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		String begin_date = request.getParameter("begin_date");
		if(StringUtil.isEmpty(begin_date))
			begin_date = getUpMothDate();
		
		String end_date = request.getParameter("end_date");
		if(StringUtil.isEmpty(end_date))
			end_date = getMothDate();
		
		List<UserAnalysisRtnInfo> memberList;
		
		try {
			
			memberList = JwUserAnalysisAPI.getUserSummary(weixinAccountService.getAccessToken(), begin_date, end_date);
			
			dataGrid.setTotal(memberList.size());
			dataGrid.setResults(memberList);
			TagUtil.datagrid(response, dataGrid);			
		} catch (WexinReqException e) {
			
			e.printStackTrace();
		}
		
//		List<Map<String,Object>> maplist=systemService.findForJdbc("SELECT s.classname classname ,count(className) personcount FROM T_s_Student s group by s.className", null);
//		Long countSutent = systemService.getCountForJdbc("SELECT COUNT(1) FROM T_S_student WHERE 1=1");
//		for(Map map:maplist){
//			Long personcount = Long.parseLong(map.get("personcount").toString());
//			Double  percentage = 0.0;
//			if (personcount != null && personcount.intValue() != 0) {
//				percentage = new Double(personcount)/countSutent;
//			}
//			
//			map.put("rate", String.format("%.2f", percentage*100)+"%");
//		}
//		Long count = 0L;
//		if(JdbcDao.DATABSE_TYPE_SQLSERVER.equals(DBTypeUtil.getDBType())){
//			count = systemService.getCountForJdbcParam("select count(0) from (SELECT s.className  classname ,count(className) totalclass FROM T_s_Student  s group by s.className) as t( classname, totalclass)",null);
//		}else{
//			count = systemService.getCountForJdbcParam("select count(0) from (SELECT s.className ,count(className) FROM T_s_Student s group by s.className)t",null);
//		}
//		
//		dataGrid.setTotal(count.intValue());
//		dataGrid.setResults(maplist);
//		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 获取距离现在一个月前的时间
	 * @return
	 */
	public String getUpMothDate() {
		
		String str = "";         
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");      
		
		Calendar lastDate = Calendar.getInstance();        
		lastDate.add(Calendar.DATE,-6);//
		
		str=sdf.format(lastDate.getTime());       
		return str;    
	}
	
	/**
	 * 获取当月
	 * @return
	 */
	public String getMothDate() {
		
		String str = "";         
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");      
		
		Calendar lastDate = Calendar.getInstance(); 
		
		str=sdf.format(lastDate.getTime());       
		return str;    
	}
}

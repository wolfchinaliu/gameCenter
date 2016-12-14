package weixin.crm.controller;
import weixin.cms.entity.CmsArticleEntity;
import weixin.goods.entity.WeixinShopCategoryEntity;
import weixin.goods.entity.WeixinShopGoodsEntity;
import weixin.goods.service.WeixinShopCategoryServiceI;
import weixin.goods.service.WeixinShopGoodsServiceI;
import weixin.shop.entity.WeixinShopAppraiseEntity;
import weixin.shop.service.WeixinShopAppraiseServiceI;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.RoletoJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;

import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

import org.jeecgframework.core.util.ExceptionUtil;

/**
 * 动态
 * @author Administrator
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/trendsController")
public class TrendsController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CrmController.class);

	@Autowired
	private SystemService systemService;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 动态列表
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "trends")
	public ModelAndView trends(HttpServletRequest request) {

		return new ModelAndView("crm/trends");
	}
	
	/**
	 * 发表动态
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "writeRrends")
	public ModelAndView writeRrends(HttpServletRequest request) {

		return new ModelAndView("crm/write-trends");
	}
}

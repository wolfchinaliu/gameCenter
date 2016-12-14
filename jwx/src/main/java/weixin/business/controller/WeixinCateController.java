package weixin.business.controller;
import weixin.business.entity.WeixinFoodCategoryEntity;
import weixin.business.entity.WeixinFoodEntity;
import weixin.business.entity.WeixinFoodOrderEntity;
import weixin.business.entity.WeixinLocationEntity;
import weixin.business.service.WeixinFoodCategoryServiceI;
import weixin.business.service.WeixinFoodOrderServiceI;
import weixin.business.service.WeixinFoodServiceI;
import weixin.business.service.WeixinLocationServiceI;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
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
 * @Title: Controller
 * @Description: 微餐饮
 * @author onlineGenerator
 * @date 2015-04-30 16:58:59
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinCateController")
public class WeixinCateController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinCateController.class);

	@Autowired
	private WeixinLocationServiceI weixinLocationService;
	
	@Autowired
	private WeixinFoodCategoryServiceI weixinFoodCategoryService;
	
	@Autowired
	private WeixinFoodServiceI weixinFoodService;
	
	@Autowired
	private WeixinFoodOrderServiceI weixinFoodOrderService;
	
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
	 * 设置当前公众号session
	 * @param request
	 */
	public void setAccountID(HttpServletRequest request){

		if(null != request.getParameter("accountid"))
			request.getSession().setAttribute(ACCOUNTID, request.getParameter("accountid"));
	}

	/**
	 * 微餐饮首页 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "index")
	public ModelAndView index(HttpServletRequest request) {
		
		this.setAccountID(request);
		
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);

		List weixinFoodCategoryList = weixinFoodCategoryService.findByProperty(WeixinFoodCategoryEntity.class, "sellerId", accountid);
		request.setAttribute("weixinFoodCategoryList", weixinFoodCategoryList);
		
		//促销商品
		List hotList = weixinFoodService.getHotList(accountid);
		request.setAttribute("hotList", hotList);
		
		//新品推荐
		List newList = weixinFoodService.getNewList(accountid);
		request.setAttribute("newList", newList);
		
		//热销商品
		List hotSaleList = weixinFoodService.getHotSaleList(accountid);
		request.setAttribute("hotSaleList", hotSaleList);
		
		return new ModelAndView("cate/index");
	}
	
	/**
	 * 门店查找 页面跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "location")
	public ModelAndView location(HttpServletRequest request) {
		
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);
		
		//查询所有门店
		List locationList = weixinLocationService.findByQueryString("from WeixinLocationEntity t where t.accountid='"+accountid+"'");
		request.setAttribute("locationList", locationList);
		
		return new ModelAndView("cate/location");
	}
	
	/**
	 * 门店详情
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "locationDetail")
	public ModelAndView locationDetail(HttpServletRequest request, String id) {
		
		WeixinLocationEntity weixinLocationEntity = weixinLocationService.getEntity(WeixinLocationEntity.class, id);
		request.setAttribute("weixinLocationEntity", weixinLocationEntity);
		
		return new ModelAndView("cate/locationDetail");
	}
	
	/**
	 * 菜品列表 页面跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "foodList")
	public ModelAndView foodList(HttpServletRequest request) {
		
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);
		
		//查询所有菜品
		List foodList = weixinFoodService.findByQueryString("from WeixinFoodEntity t where t.sellerId='"+accountid+"'");
		request.setAttribute("foodList", foodList);
		
		return new ModelAndView("cate/foodList");
	}
	
	/**
	 * 菜品详情
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "foodDetail")
	public ModelAndView foodDetail(HttpServletRequest request, String id) {
		
		WeixinFoodEntity weixinFoodEntity = weixinFoodService.getEntity(WeixinFoodEntity.class, id);
		request.setAttribute("weixinFoodEntity", weixinFoodEntity);
		
		return new ModelAndView("cate/foodDetail");
	}
	
	/**
	 * 跳转到创建订单页面
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "toOrder")
	public ModelAndView toOrder(HttpServletRequest request, String id) {
		
		//用户选择的门店
		WeixinLocationEntity weixinLocationEntity = weixinLocationService.getEntity(WeixinLocationEntity.class, id);
		request.setAttribute("weixinLocationEntity", weixinLocationEntity);
		
		return new ModelAndView("cate/createorder");
	}
	
	/**
	 * 保存订单
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "doOrder")
	public ModelAndView doOrder(HttpServletRequest request) {
		
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);
		
		String locationId = request.getParameter("locationId");//用户选择的门店
		WeixinLocationEntity weixinLocationEntity = weixinLocationService.getEntity(WeixinLocationEntity.class, locationId);
		
		String userName = request.getParameter("userName");
		String mobilphone = request.getParameter("mobilphone");
		String preDate = request.getParameter("preDate");
		String type = request.getParameter("type");
		String number = request.getParameter("number");
		
		WeixinFoodOrderEntity weixinFoodOrderEntity = new WeixinFoodOrderEntity();
		weixinFoodOrderEntity.setAccountid(accountid);
		weixinFoodOrderEntity.setWeixinLocationEntity(weixinLocationEntity);
		weixinFoodOrderEntity.setUserName(userName);
		weixinFoodOrderEntity.setMobilphone(mobilphone);
		weixinFoodOrderEntity.setPreDate(new Date(preDate));
		weixinFoodOrderEntity.setType(type);
		weixinFoodOrderEntity.setNumber(Integer.parseInt(number));
		weixinFoodOrderEntity.setCreateDate(new Date());
		
		//weixinFoodOrderService.save(weixinFoodOrderEntity);
		
		return new ModelAndView("cate/createorder");
	}
}

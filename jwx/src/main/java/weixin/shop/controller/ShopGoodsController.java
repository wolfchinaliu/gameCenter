package weixin.shop.controller;
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
 * @Title: Controller
 * @Description: 商品信息
 * @author onlineGenerator
 * @date 2015-01-20 17:31:31
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/shopGoodsController")
public class ShopGoodsController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ShopGoodsController.class);

	@Autowired
	private WeixinShopGoodsServiceI weixinShopGoodsService;
	
	@Autowired
	private WeixinShopCategoryServiceI weixinShopCategoryService;
	
	@Autowired
	private WeixinShopAppraiseServiceI weixinShopAppraiseService;
	
	@Autowired
	private SystemService systemService;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private Map<String, String> paramsToMap(HttpServletRequest request) {
		
		Map<String, String> params = new HashMap<String, String>();
		// 得到枚举类型的参数名称，参数名称若有重复的只能得到第一个
		Enumeration em = request.getParameterNames();
		while (em.hasMoreElements()) {
			String paramName = (String) em.nextElement();
			String paramValue = request.getParameter(paramName);
			// 形成键值对应的map
			params.put(paramName, paramValue);
		}
		
		return params;
	}

	@RequestMapping(params = "getGoodsList")
	public void getGoodsList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);
		
		//关键字
		String key_word = request.getParameter("key_word");
		
		//分类ID
		String categoryId = request.getParameter("categoryId");
		
		//排序字段
		String sort = request.getParameter("sort");
		
		//排序
		String order = request.getParameter("order");
		
		int pageSize = dataGrid.getRows();
		Integer nextpage = dataGrid.getPage();
		int begin = (nextpage-1)*pageSize;
		
		String hql = "from WeixinShopGoodsEntity t where t.sellerId='"+accountid+"' and t.statement='1' ";
		
		if(StringUtils.isNotEmpty(categoryId)){
			hql += " and t.weixinShopCategoryEntity.id = '"+categoryId+"'";
		}
		
		if(!StringUtils.isEmpty(key_word)){
			hql += " and t.title like '%"+key_word+"%'";
		}
		
		if(!StringUtils.isEmpty(order) && !StringUtils.isEmpty(sort)){
			
			hql += " order by "+sort+" "+order;
		}else{
			
			hql += " order by isNew,sellCount "+order;
		}
		
		List  list = weixinShopGoodsService.getListForPage(hql, begin, pageSize);
		int count = weixinShopGoodsService.getCountForPage(hql);
		
		dataGrid.setTotal(count);
		dataGrid.setResults(list);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 根据分类查询出来的商品列表
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "golist")
	public ModelAndView golist(HttpServletRequest request) {
		
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);

		String categoryId = request.getParameter("categoryId");
		String key_word = request.getParameter("key_word");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("key_word", key_word);
		request.setAttribute("sort", sort);
		request.setAttribute("order", order);
		
		//分类信息
		List weixinShopCategoryList = weixinShopCategoryService.findByProperty(WeixinShopCategoryEntity.class, "sellerId", accountid);
		request.setAttribute("weixinShopCategoryList", weixinShopCategoryList);
        request.setAttribute("domain", ResourceUtil.getConfigByName("media.url.prefix")+"/");
		return new ModelAndView("shop/goodslist");
	}
	
	/**
	 * 根据关键字查询出来的商品列表
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "searchGoods")
	public ModelAndView searchGoods(HttpServletRequest request) {
		
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);
		
		//关键字
		String key_word = request.getParameter("key_word");
		
		String hql = "from WeixinShopGoodsEntity t where t.sellerId='"+accountid+"' and t.statement='1'";
		
		if(!StringUtils.isEmpty(key_word)){
			
			hql += " and t.title like '%"+key_word+"%'";
		}
		
		hql += " order by t.sellCount desc";
		
		List goodsList = weixinShopGoodsService.findByQueryString(hql);
		request.setAttribute("goodsList", goodsList);
		
		//促销商品
		List hotGoodsList = weixinShopGoodsService.getHotGoodsList(accountid);
		request.setAttribute("hotGoodsList", hotGoodsList);
		
		//分类信息
		List weixinShopCategoryList = weixinShopCategoryService.findByProperty(WeixinShopCategoryEntity.class, "sellerId", accountid);
		request.setAttribute("weixinShopCategoryList", weixinShopCategoryList);
        request.setAttribute("domain", ResourceUtil.getConfigByName("media.url.prefix")+"/");
		
		return new ModelAndView("shop/goodslist");
	}
	
	/**
	 * 商品信息
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goodsinfo")
	public ModelAndView goodsinfo(HttpServletRequest request) {
		
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);
		
		String id = request.getParameter("id");
		
		WeixinShopGoodsEntity weixinShopGoods = weixinShopGoodsService.getEntity(WeixinShopGoodsEntity.class, id);
		request.setAttribute("weixinShopGoods", weixinShopGoods);
		
		//促销商品
		List hotGoodsList = weixinShopGoodsService.getHotGoodsList(accountid);
		request.setAttribute("hotGoodsList", hotGoodsList);
		
		//查询评价
		String hql = "from WeixinShopAppraiseEntity t where t.weixinShopGoodsEntity.id='"+id+"' and t.status=1";
		List<WeixinShopAppraiseEntity> weixinShopAppraiseList = weixinShopAppraiseService.findByQueryString(hql);
		request.setAttribute("appraiseNum", weixinShopAppraiseList.size());
        request.setAttribute("domain", ResourceUtil.getConfigByName("media.url.prefix")+"/");
		return new ModelAndView("shop/goodsinfo");
	}
	
	/**
	 * 商品详情
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "godetail")
	public ModelAndView godetail(HttpServletRequest request) {

		String id = request.getParameter("id");
		
		WeixinShopGoodsEntity weixinShopGoods = weixinShopGoodsService.getEntity(WeixinShopGoodsEntity.class, id);
		request.setAttribute("weixinShopGoods", weixinShopGoods);
		
		//查询评价
		String hql = "from WeixinShopAppraiseEntity t where t.weixinShopGoodsEntity.id='"+id+"' and t.status=1";
		List<WeixinShopAppraiseEntity> weixinShopAppraiseList = weixinShopAppraiseService.findByQueryString(hql);
		request.setAttribute("appraiseNum", weixinShopAppraiseList.size());

		return new ModelAndView("shop/goodsdetail");
	}
	
	/**
	 * 商品评价
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goodsappraise")
	public ModelAndView goodsappraise(HttpServletRequest request) {

		String id = request.getParameter("id");
		
		WeixinShopGoodsEntity weixinShopGoods = weixinShopGoodsService.getEntity(WeixinShopGoodsEntity.class, id);
		request.setAttribute("weixinShopGoods", weixinShopGoods);

		//查询评价
		String hql = "from WeixinShopAppraiseEntity t where t.weixinShopGoodsEntity.id='"+id+"' and t.status=1";
		List<WeixinShopAppraiseEntity> weixinShopAppraiseList = weixinShopAppraiseService.findByQueryString(hql);
		request.setAttribute("weixinShopAppraiseList", weixinShopAppraiseList);
		
		request.setAttribute("appraiseNum", weixinShopAppraiseList.size());
		
		return new ModelAndView("shop/appraise");
	}
}

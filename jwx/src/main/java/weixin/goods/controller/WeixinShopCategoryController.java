package weixin.goods.controller;
import weixin.goods.entity.WeixinShopCategoryEntity;
import weixin.goods.service.WeixinShopCategoryServiceI;
import weixin.shop.entity.WeixinShopEntity;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
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
 * @Description: 商品分类
 * @author onlineGenerator
 * @date 2015-01-20 17:31:10
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinShopCategoryController")
public class WeixinShopCategoryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinShopCategoryController.class);

	@Autowired
	private WeixinShopCategoryServiceI weixinShopCategoryService;
	@Autowired
	private SystemService systemService;
	private String message;
	private static String mediaUrlPrefix = ResourceUtil.getMediaUrlPrefix();
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 商品分类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinShopCategory")
	public ModelAndView weixinShopCategory(HttpServletRequest request) {
		return new ModelAndView("weixin/goods/weixinShopCategoryList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(WeixinShopCategoryEntity weixinShopCategory,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinShopCategoryEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinShopCategory, request.getParameterMap());
		cq.eq("sellerId", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinShopCategoryService.getDataGridReturn(cq, true);
		List data = cq.getDataGrid().getResults();
        if (CollectionUtils.isNotEmpty(data)) {
            for(Object o :data){
                WeixinShopCategoryEntity entity = (WeixinShopCategoryEntity) o;
                entity.setImgurl(mediaUrlPrefix + "/" + entity.getImgurl());
            }
        }
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除商品分类
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(String id, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		WeixinShopCategoryEntity weixinShopCategory = systemService.getEntity(WeixinShopCategoryEntity.class, id);
		message = "商品分类删除成功";
		try{
			
			String hql = "from WeixinShopGoodsEntity t where t.weixinShopCategoryEntity.id='"+id+"'";
			List list = systemService.findByQueryString(hql);
			
			if(list!=null && list.size()>0){
				message = "该商品分类下面有所属商品，不允许删除！";
				j.setMsg(message);
				return j;
			}
			weixinShopCategoryService.delete(weixinShopCategory);
			//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商品分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除商品分类
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "商品分类删除成功";
		try{
			for(String id:ids.split(",")){
				WeixinShopCategoryEntity weixinShopCategory = systemService.getEntity(WeixinShopCategoryEntity.class, 
				id
				);
				weixinShopCategoryService.delete(weixinShopCategory);
				//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "商品分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加商品分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinShopCategoryEntity weixinShopCategory, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "商品分类添加成功";
		try{
			
			//检查是否已经存在该分类
			String hql = "from WeixinShopCategoryEntity t where t.sellerId='"+ResourceUtil.getWeiXinAccountId()+"' and t.name='"+weixinShopCategory.getName()+"'";
			List list = weixinShopCategoryService.findByQueryString(hql);
			if(null != list && list.size()>0){
				
				message = "已经存在该商品分类!";
				j.setMsg(message);
				return j;
			}
			
			weixinShopCategory.setCreateName(ResourceUtil.getSessionUserName().getRealName());
			weixinShopCategory.setCreateDate(new Date());
			weixinShopCategory.setUpdateName(ResourceUtil.getSessionUserName().getRealName());
			weixinShopCategory.setUpdateDate(new Date());
			weixinShopCategory.setSellerId(ResourceUtil.getWeiXinAccountId());
			weixinShopCategoryService.save(weixinShopCategory);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商品分类添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新商品分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinShopCategoryEntity weixinShopCategory, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		
		try {
			
			//检查是否已经存在该分类
			String hql = "from WeixinShopCategoryEntity t where t.id!='"+weixinShopCategory.getId()+"' and t.sellerId='"+ResourceUtil.getWeiXinAccountId()+"' and t.name='"+weixinShopCategory.getName()+"'";
			List list = weixinShopCategoryService.findByQueryString(hql);
			if(null != list && list.size()>0){
				
				message = "已经存在该商品分类!";
				j.setMsg(message);
				return j;
			}
			
			WeixinShopCategoryEntity t = weixinShopCategoryService.get(WeixinShopCategoryEntity.class, weixinShopCategory.getId());
			MyBeanUtils.copyBeanNotNull2Bean(weixinShopCategory, t);
			weixinShopCategoryService.saveOrUpdate(t);
			message = "商品分类更新成功";
		} catch (Exception e) {
			e.printStackTrace();
			message = "商品分类更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 商品分类新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinShopCategoryEntity weixinShopCategory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinShopCategory.getId())) {
			weixinShopCategory = weixinShopCategoryService.getEntity(WeixinShopCategoryEntity.class, weixinShopCategory.getId());
			req.setAttribute("weixinShopCategoryPage", weixinShopCategory);
		}
		return new ModelAndView("weixin/goods/weixinShopCategory-add");
	}
	/**
	 * 商品分类编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinShopCategoryEntity weixinShopCategory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinShopCategory.getId())) {
			weixinShopCategory = weixinShopCategoryService.getEntity(WeixinShopCategoryEntity.class, weixinShopCategory.getId());
			req.setAttribute("weixinShopCategoryPage", weixinShopCategory);
		}
		return new ModelAndView("weixin/goods/weixinShopCategory-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/goods/weixinShopCategoryUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinShopCategoryEntity weixinShopCategory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "商品分类";
			// 根据浏览器进行转码，使其支持中文文件名
			if (BrowserUtils.isIE(request)) {
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ java.net.URLEncoder.encode(codedFileName,
										"UTF-8") + ".xls");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"),
						"ISO8859-1");
				response.setHeader("content-disposition",
						"attachment;filename=" + newtitle + ".xls");
			}
			// 产生工作簿对象
			HSSFWorkbook workbook = null;
			CriteriaQuery cq = new CriteriaQuery(WeixinShopCategoryEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinShopCategory, request.getParameterMap());
			
			List<WeixinShopCategoryEntity> weixinShopCategorys = this.weixinShopCategoryService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("商品分类列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinShopCategoryEntity.class, weixinShopCategorys);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (Exception e) {
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {

			}
		}
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public void exportXlsByT(WeixinShopCategoryEntity weixinShopCategory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "商品分类";
			// 根据浏览器进行转码，使其支持中文文件名
			if (BrowserUtils.isIE(request)) {
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ java.net.URLEncoder.encode(codedFileName,
										"UTF-8") + ".xls");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"),
						"ISO8859-1");
				response.setHeader("content-disposition",
						"attachment;filename=" + newtitle + ".xls");
			}
			// 产生工作簿对象
			HSSFWorkbook workbook = null;
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("商品分类列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinShopCategoryEntity.class, null);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (Exception e) {
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {

			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setSecondTitleRows(1);
			params.setNeedSave(true);
			try {
				List<WeixinShopCategoryEntity> listWeixinShopCategoryEntitys = 
					(List<WeixinShopCategoryEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),WeixinShopCategoryEntity.class,params);
				for (WeixinShopCategoryEntity weixinShopCategory : listWeixinShopCategoryEntitys) {
					weixinShopCategoryService.save(weixinShopCategory);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
}

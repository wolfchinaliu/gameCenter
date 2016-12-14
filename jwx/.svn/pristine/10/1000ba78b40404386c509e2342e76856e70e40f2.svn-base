package weixin.shop.controller;
import weixin.guanjia.message.model.TextItem;
import weixin.guanjia.message.model.TextMessageKf;
import weixin.guanjia.message.service.CustomerMessageService;
import weixin.member.entity.WeixinMemberEntity;
import weixin.shop.entity.WeixinOrderEntity;
import weixin.shop.entity.WeixinShopEntity;
import weixin.shop.service.WeixinOrderServiceI;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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
 * @Description: 订单
 * @author onlineGenerator
 * @date 2015-04-23 20:16:50
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinOrderController")
public class WeixinOrderController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinOrderController.class);

	@Autowired
	private WeixinOrderServiceI weixinOrderService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CustomerMessageService customerMessageService;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 订单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinOrder")
	public ModelAndView weixinOrder(HttpServletRequest request) {
		return new ModelAndView("weixin/shop/weixinOrderList");
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
	public void datagrid(WeixinOrderEntity weixinOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinOrderEntity.class, dataGrid);
		
//		if(StringUtils.isNotEmpty(weixinOrder.getOrderNo())){
//			
//			weixinOrder.setOrderNo("*"+weixinOrder.getOrderNo()+"*");
//		}
//		if(StringUtils.isNotEmpty(weixinOrder.getCreateName())){
//			
//			weixinOrder.setCreateName("*"+weixinOrder.getCreateName()+"*");
//		}

		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinOrder, request.getParameterMap());
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinOrderService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除订单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinOrderEntity weixinOrder, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinOrder = systemService.getEntity(WeixinOrderEntity.class, weixinOrder.getId());
		message = "订单删除成功";
		try{
			weixinOrderService.delete(weixinOrder);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "订单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除订单
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "订单删除成功";
		try{
			for(String id:ids.split(",")){
				WeixinOrderEntity weixinOrder = systemService.getEntity(WeixinOrderEntity.class, 
				id
				);
				weixinOrderService.delete(weixinOrder);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "订单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加订单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinOrderEntity weixinOrder, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "订单添加成功";
		try{
			weixinOrderService.save(weixinOrder);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "订单添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新订单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinOrderEntity weixinOrder, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "订单更新成功";
		WeixinOrderEntity t = weixinOrderService.get(WeixinOrderEntity.class, weixinOrder.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinOrder, t);
			weixinOrderService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "订单更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 订单新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinOrderEntity weixinOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinOrder.getId())) {
			weixinOrder = weixinOrderService.getEntity(WeixinOrderEntity.class, weixinOrder.getId());
			req.setAttribute("weixinOrderPage", weixinOrder);
		}
		return new ModelAndView("weixin/shop/weixinOrder-add");
	}
	/**
	 * 订单编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinOrderEntity weixinOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinOrder.getId())) {
			weixinOrder = weixinOrderService.getEntity(WeixinOrderEntity.class, weixinOrder.getId());
			req.setAttribute("weixinOrderPage", weixinOrder);
		}
		return new ModelAndView("weixin/shop/weixinOrder-update");
	}
	
	/**
	 * 查看
	 * @param weixinOrder
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "goView")
	public ModelAndView goView(WeixinOrderEntity weixinOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinOrder.getId())) {
			weixinOrder = weixinOrderService.getEntity(WeixinOrderEntity.class, weixinOrder.getId());
			req.setAttribute("weixinOrderPage", weixinOrder);
		}
		return new ModelAndView("weixin/shop/weixinOrder-view");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/shop/weixinOrderUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinOrderEntity weixinOrder,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			
			String type = request.getParameter("type");
			if("1".equals(type))
				codedFileName = "订单";
			else
				codedFileName = "发货清单";
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
			CriteriaQuery cq = new CriteriaQuery(WeixinOrderEntity.class, dataGrid);
			cq.eq("accountid", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
			
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinOrder, request.getParameterMap());
			
			List<WeixinOrderEntity> weixinOrders = this.weixinOrderService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("订单列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinOrderEntity.class, weixinOrders);
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
	public void exportXlsByT(WeixinOrderEntity weixinOrder,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "订单";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("订单列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinOrderEntity.class, null);
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
				List<WeixinOrderEntity> listWeixinOrderEntitys = 
					(List<WeixinOrderEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),WeixinOrderEntity.class,params);
				for (WeixinOrderEntity weixinOrder : listWeixinOrderEntitys) {
					weixinOrderService.save(weixinOrder);
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
	
	/**
	 * 跳转到发货页面
	 * @param weixinOrder
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "goDelivery")
	public ModelAndView goDelivery(WeixinOrderEntity weixinOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinOrder.getId())) {
			weixinOrder = weixinOrderService.getEntity(WeixinOrderEntity.class, weixinOrder.getId());
			
			//已付款，未发货的订单才可以发货
			if("1".equals(weixinOrder.getStatus()) && "0".equals(weixinOrder.getDeliverStatus())){
				
				req.setAttribute("weixinOrderPage", weixinOrder);
				return new ModelAndView("weixin/shop/weixinOrder-delivery");
			}
		}
		
		return new ModelAndView("weixin/shop/weixinOrder-view");
	}
	
	/**
	 * 跳转到发货页面
	 * @param weixinOrder
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "toDelivery")
	public ModelAndView toDelivery(String id, HttpServletRequest req) {
		
		WeixinOrderEntity weixinOrder = weixinOrderService.getEntity(WeixinOrderEntity.class, id);

		req.setAttribute("weixinOrderPage", weixinOrder);
		return new ModelAndView("weixin/shop/weixinOrder-delivery");
	}
	
	/**
	 * 发货
	 * @param weixinOrder
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doDelivery")
	@ResponseBody
	public AjaxJson doDelivery(String id, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "订单发货成功";
		WeixinOrderEntity weixinOrder = weixinOrderService.get(WeixinOrderEntity.class, id);
		try {
			
			String expressNum = request.getParameter("expressNum");
			String expressCompany = request.getParameter("expressCompany");
			
			weixinOrder.setExpressCompany(expressCompany);
			weixinOrder.setExpressNum(expressNum);
			weixinOrder.setExpressDate(new Date());
			weixinOrder.setDeliverStatus("1");
			
			weixinOrderService.saveOrUpdate(weixinOrder);
			
			//商城信息
			WeixinShopEntity WeixinShopEntity = systemService.findUniqueByProperty(WeixinShopEntity.class, "accountid", weixinOrder.getAccountid());
			
			//发送发货消息给买家
			String resContent = "您的宝贝订单号"+weixinOrder.getOrderNo()+"已发货，运单号："+weixinOrder.getExpressNum()+"("+weixinOrder.getExpressCompany()+")，感谢您的惠顾["+WeixinShopEntity.getShopName()+"]";
			String openId = weixinOrder.getWeixinMemberEntity().getOpenId();
			
			TextMessageKf customMessage = new TextMessageKf();
			customMessage.setMsgtype("text");
			TextItem textItem = new TextItem();
			textItem.setContent(resContent);
			customMessage.setText(textItem);
			customMessage.setTouser(openId);
			
			//客服接口发送文本消息
			JSONObject jsonObj = JSONObject.fromObject(customMessage);
			customerMessageService.sendMessage(jsonObj.toString());			
		} catch (Exception e) {
			e.printStackTrace();
			message = "订单发货失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 发货列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinOrderDelivery")
	public ModelAndView weixinOrderDelivery(HttpServletRequest request) {
		return new ModelAndView("weixin/shop/weixinOrderDeliveryList");
	}

	/**
	 * 发货数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagridDelivery")
	public void datagridDelivery(WeixinOrderEntity weixinOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinOrderEntity.class, dataGrid);
		
		if(StringUtils.isNotEmpty(weixinOrder.getOrderNo())){
			
			weixinOrder.setOrderNo("*"+weixinOrder.getOrderNo()+"*");
		}
		
		if(StringUtils.isNotEmpty(weixinOrder.getCreateName())){
			
			weixinOrder.setCreateName("*"+weixinOrder.getCreateName()+"*");
		}
		
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinOrder, request.getParameterMap());
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
		cq.notEq("status", "0");
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinOrderService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
}

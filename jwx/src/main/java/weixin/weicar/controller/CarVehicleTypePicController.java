package weixin.weicar.controller;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import weixin.weicar.entity.CarVehicleTypePicEntity;
import weixin.weicar.service.CarVehicleTypePicServiceI;



/**   
 * @Title: Controller
 * @Description: 车型图片
 * @author onlineGenerator
 * @date 2015-05-24 15:38:42
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/carVehicleTypePicController")
public class CarVehicleTypePicController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CarVehicleTypePicController.class);

	@Autowired
	private CarVehicleTypePicServiceI carVehicleTypePicService;
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
	 * 车型图片列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "carVehicleTypePic")
	public ModelAndView carVehicleTypePic(HttpServletRequest request) {
		return new ModelAndView("weixin/weicar/carVehicleTypePicList");
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
	public void datagrid(CarVehicleTypePicEntity carVehicleTypePic,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CarVehicleTypePicEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, carVehicleTypePic, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.carVehicleTypePicService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除车型图片
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CarVehicleTypePicEntity carVehicleTypePic, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		carVehicleTypePic = systemService.getEntity(CarVehicleTypePicEntity.class, carVehicleTypePic.getId());
		message = "车型图片删除成功";
		try{
			carVehicleTypePicService.delete(carVehicleTypePic);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "车型图片删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除车型图片
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "车型图片删除成功";
		try{
			for(String id:ids.split(",")){
				CarVehicleTypePicEntity carVehicleTypePic = systemService.getEntity(CarVehicleTypePicEntity.class, 
				id
				);
				carVehicleTypePicService.delete(carVehicleTypePic);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "车型图片删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加车型图片
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CarVehicleTypePicEntity carVehicleTypePic, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "车型图片添加成功";
		try{
			carVehicleTypePicService.save(carVehicleTypePic);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "车型图片添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新车型图片
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CarVehicleTypePicEntity carVehicleTypePic, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "车型图片更新成功";
		CarVehicleTypePicEntity t = carVehicleTypePicService.get(CarVehicleTypePicEntity.class, carVehicleTypePic.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(carVehicleTypePic, t);
			carVehicleTypePicService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "车型图片更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 车型图片新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CarVehicleTypePicEntity carVehicleTypePic, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(carVehicleTypePic.getId())) {
			carVehicleTypePic = carVehicleTypePicService.getEntity(CarVehicleTypePicEntity.class, carVehicleTypePic.getId());
			req.setAttribute("carVehicleTypePicPage", carVehicleTypePic);
		}
		return new ModelAndView("weixin/weicar/carVehicleTypePic-add");
	}
	/**
	 * 车型图片编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CarVehicleTypePicEntity carVehicleTypePic, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(carVehicleTypePic.getId())) {
			carVehicleTypePic = carVehicleTypePicService.getEntity(CarVehicleTypePicEntity.class, carVehicleTypePic.getId());
			req.setAttribute("carVehicleTypePicPage", carVehicleTypePic);
		}
		return new ModelAndView("weixin/weicar/carVehicleTypePic-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/weicar/carVehicleTypePicUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(CarVehicleTypePicEntity carVehicleTypePic,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "车型图片";
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
			CriteriaQuery cq = new CriteriaQuery(CarVehicleTypePicEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, carVehicleTypePic, request.getParameterMap());
			
			List<CarVehicleTypePicEntity> carVehicleTypePics = this.carVehicleTypePicService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("车型图片列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), CarVehicleTypePicEntity.class, carVehicleTypePics);
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
	public void exportXlsByT(CarVehicleTypePicEntity carVehicleTypePic,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "车型图片";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("车型图片列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), CarVehicleTypePicEntity.class, null);
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
				List<CarVehicleTypePicEntity> listCarVehicleTypePicEntitys = 
					(List<CarVehicleTypePicEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),CarVehicleTypePicEntity.class,params);
				for (CarVehicleTypePicEntity carVehicleTypePic : listCarVehicleTypePicEntitys) {
					carVehicleTypePicService.save(carVehicleTypePic);
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

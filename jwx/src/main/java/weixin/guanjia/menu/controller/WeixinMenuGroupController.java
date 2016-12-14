package weixin.guanjia.menu.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
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

import net.sf.json.JSONObject;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.entity.common.Button;
import weixin.guanjia.core.entity.common.CommonButton;
import weixin.guanjia.core.entity.common.ComplexButton;
import weixin.guanjia.core.entity.common.Menu;
import weixin.guanjia.core.entity.common.ViewButton;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.menu.entity.MenuEntity;
import weixin.guanjia.menu.entity.WeixinMenuGroupEntity;
import weixin.guanjia.menu.service.WeixinMenuGroupServiceI;

/**
 * @Title: Controller
 * @Description: 微信菜单组
 * @author onlineGenerator
 * @date 2015-01-24 17:53:27
 * @version V1.0
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinMenuGroupController")
public class WeixinMenuGroupController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = Logger
			.getLogger(WeixinMenuGroupController.class);

	@Autowired
	private WeixinMenuGroupServiceI weixinMenuGroupService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 微信菜单组列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinMenuGroup")
	public ModelAndView weixinMenuGroup(HttpServletRequest request) {
		return new ModelAndView("weixin/guanjia/menu/weixinMenuGroupList");
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
	public void datagrid(WeixinMenuGroupEntity weixinMenuGroup,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinMenuGroupEntity.class,
				dataGrid);
		// 查询条件组装器
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				weixinMenuGroup, request.getParameterMap());
		try {
			// 自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.addOrder("synchDate", SortDirection.desc);
		cq.add();
		this.weixinMenuGroupService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除微信菜单组
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinMenuGroupEntity weixinMenuGroup,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinMenuGroup = systemService.getEntity(WeixinMenuGroupEntity.class,
				weixinMenuGroup.getId());
		List<MenuEntity> menuList =weixinMenuGroup.getMenuList();
		if(menuList!=null&&menuList.size()>0){
			message = "请先删除菜单组里面的菜单";
			j.setMsg(message);
		}else{
			message = "微信菜单组删除成功";
			try {
				weixinMenuGroupService.delete(weixinMenuGroup);
				systemService.addLog(message, Globals.Log_Type_DEL,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "微信菜单组删除失败";
				throw new BusinessException(e.getMessage());
			}
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除微信菜单组
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "微信菜单组删除成功";
		try {
			for (String id : ids.split(",")) {
				WeixinMenuGroupEntity weixinMenuGroup = systemService
						.getEntity(WeixinMenuGroupEntity.class, id);
				weixinMenuGroupService.delete(weixinMenuGroup);
				systemService.addLog(message, Globals.Log_Type_DEL,
						Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "微信菜单组删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加微信菜单组
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinMenuGroupEntity weixinMenuGroup,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "微信菜单组添加成功";
		try {
			String accountid = ResourceUtil.getWeiXinAccountId();
			weixinMenuGroup.setAccountid(accountid);
			weixinMenuGroupService.save(weixinMenuGroup);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "微信菜单组添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 更新微信菜单组
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinMenuGroupEntity weixinMenuGroup,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "微信菜单组更新成功";
		WeixinMenuGroupEntity t = weixinMenuGroupService.get(
				WeixinMenuGroupEntity.class, weixinMenuGroup.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinMenuGroup, t);
			weixinMenuGroupService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "微信菜单组更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 微信菜单组新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinMenuGroupEntity weixinMenuGroup,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinMenuGroup.getId())) {
			weixinMenuGroup = weixinMenuGroupService.getEntity(
					WeixinMenuGroupEntity.class, weixinMenuGroup.getId());
			req.setAttribute("weixinMenuGroupPage", weixinMenuGroup);
		}
		return new ModelAndView("weixin/guanjia/menu/weixinMenuGroup-add");
	}

	/**
	 * 微信菜单组编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinMenuGroupEntity weixinMenuGroup,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinMenuGroup.getId())) {
			weixinMenuGroup = weixinMenuGroupService.getEntity(
					WeixinMenuGroupEntity.class, weixinMenuGroup.getId());
			req.setAttribute("weixinMenuGroupPage", weixinMenuGroup);
		}
		return new ModelAndView("weixin/guanjia/menu/weixinMenuGroup-update");
	}

	/**
	 * 方法描述: 查看成员列表 作 者： yiming.zhang 日 期： Dec 4, 2013-8:53:39 PM
	 * 
	 * @param request
	 * @param departid
	 * @return 返回类型： ModelAndView
	 */
	@RequestMapping(params = "menuInfoList")
	public ModelAndView menuInfoList(HttpServletRequest request,
			String menuGroupId) {
		request.setAttribute("menuGroupId", menuGroupId);
		return new ModelAndView("weixin/guanjia/menu/weixinMenuGroupDetailList");
	}

	/**
	 * 自定义菜单-刘晓春-2015年12月2日
	 * 保留了原有的菜单组功能，仅仅做了简单的改造，默认给用户创建一个菜单组
	 * @param request
	 * @param menuGroupId
	 * @return
	 */
	@RequestMapping(params = "menuInfoListLXC")
	public ModelAndView menuInfoListLXC(HttpServletRequest request,
										String menuGroupId) {

		//1.判断该商户是否有菜单组
		List<WeixinMenuGroupEntity> menuGroupEntityList=weixinMenuGroupService.findHql("from WeixinMenuGroupEntity t where t.accountid=?",ResourceUtil.getWeiXinAccountId());
		LOGGER.info(menuGroupEntityList);
		//2.有则根据id加载菜单，无则先添加菜单组在加载菜单
		if (menuGroupEntityList.size()>0) {
			menuGroupId=menuGroupEntityList.get(0).getId();
		}else if(menuGroupEntityList.size()==0){
			WeixinMenuGroupEntity weixinMenuGroup=new WeixinMenuGroupEntity();
			String accountid = ResourceUtil.getWeiXinAccountId();
			weixinMenuGroup.setAccountid(accountid);
			weixinMenuGroup.setGroupName(accountid);
			weixinMenuGroupService.save(weixinMenuGroup);
			menuGroupId=weixinMenuGroup.getId();
		}
		request.setAttribute("menuGroupId", menuGroupId);
		return new ModelAndView("weixin/guanjia/menu/weixinMenuLXC");
	}

	/**
	 * 根据组名称得到组的数据
	 * 
	 * @param menuEntity
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "menuDatagrid")
	@ResponseBody
	public List<TreeGrid> menuDatagrid(TreeGrid treegrid,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {

		CriteriaQuery cq = new CriteriaQuery(MenuEntity.class);
		
		if (treegrid.getId() != null) {
			cq.eq("menuEntity.id", treegrid.getId());
		} else {

			cq.isNull("menuEntity");
		}
		String name=request.getParameter("name");
		if(!StringUtil.isEmpty(name)){
			cq.like("name", "%"+name+"%");
		}
		DetachedCriteria dc = cq.getDetachedCriteria();
		DetachedCriteria dcDepart = dc.createCriteria("weixinMenuGroupEntity");
		dcDepart.add(Restrictions.eq("id", request.getParameter("menuGroupId")));
		cq.addOrder("orders", SortDirection.asc);
		cq.add();

		List<MenuEntity> menuList = systemService.getListByCriteriaQuery(cq,
				false);
		List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		TreeGridModel treeGridModel = new TreeGridModel();
		// treeGridModel.setIcon("orders");
		treeGridModel.setTextField("name");
		treeGridModel.setParentText("url");
		treeGridModel.setOrder("orders");
		treeGridModel.setSrc("type");
		treeGridModel.setIdField("id");
		treeGridModel.setChildList("menuList");
		// 添加排序字段
		treeGrids = systemService.treegrid(menuList, treeGridModel);
		return treeGrids;
	}

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/guanjia/menu/weixinMenuGroupUpload");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinMenuGroupEntity weixinMenuGroup,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "微信菜单组";
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
			CriteriaQuery cq = new CriteriaQuery(WeixinMenuGroupEntity.class,
					dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil
					.installHql(cq, weixinMenuGroup, request.getParameterMap());

			List<WeixinMenuGroupEntity> weixinMenuGroups = this.weixinMenuGroupService
					.getListByCriteriaQuery(cq, false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("微信菜单组列表",
					"导出人:" + ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinMenuGroupEntity.class, weixinMenuGroups);
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
	public void exportXlsByT(WeixinMenuGroupEntity weixinMenuGroup,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "微信菜单组";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("微信菜单组列表",
					"导出人:" + ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinMenuGroupEntity.class, null);
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
	public AjaxJson importExcel(HttpServletRequest request,
			HttpServletResponse response) {
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
				List<WeixinMenuGroupEntity> listWeixinMenuGroupEntitys = (List<WeixinMenuGroupEntity>) ExcelImportUtil
						.importExcelByIs(file.getInputStream(),
								WeixinMenuGroupEntity.class, params);
				for (WeixinMenuGroupEntity weixinMenuGroup : listWeixinMenuGroupEntitys) {
					weixinMenuGroupService.save(weixinMenuGroup);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				LOGGER.error(ExceptionUtil.getExceptionMessage(e));
			} finally {
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
	 * 同步菜单组到微信
	 * 
	 * @param menuGroupId
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "synchMenu")
	@ResponseBody
	public AjaxJson synchMenu(String menuGroupId, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isEmpty(menuGroupId)) {
			message = "没有可以同步的数据";
			j.setMsg(this.message);
		}
		String hql = "from MenuEntity where fatherid is null and weixinMenuGroupEntity.id = '"
				+ menuGroupId + "'  order by  orders asc";
		List<MenuEntity> menuList = this.systemService.findByQueryString(hql);
		org.jeecgframework.core.util.LogUtil.info(".....一级菜单的个数是....."
				+ menuList.size());
		Menu menu = new Menu();
		if (menuList.size() > 3) {
			message = "一级菜单不能超过三个";
			j.setMsg(this.message);
			return j;
		}
		if (menuList.size() == 0) {
			message = "没有可以同步的数据";
			j.setMsg(this.message);
			return j;
		}
		Button firstArr[] = new Button[menuList.size()];
		for (int a = 0; a < menuList.size(); a++) {
			MenuEntity entity = menuList.get(a);
			String hqls = "from MenuEntity where fatherid = '" + entity.getId()
					+ "'  order by  orders asc";
			List<MenuEntity> childList = this.systemService
					.findByQueryString(hqls);
			// org.jeecgframework.core.util.LogUtil.info("....二级菜单的大小....." +
			// childList.size());
			if (childList.size() == 0) {
				if ("view".equals(entity.getType())) {
					ViewButton viewButton = new ViewButton();
					viewButton.setName(entity.getName());
					viewButton.setType(entity.getType());
					viewButton.setUrl(entity.getUrl());
					firstArr[a] = viewButton;
				} else if ("click".equals(entity.getType())) {
					CommonButton cb = new CommonButton();
					cb.setKey(entity.getId());
					cb.setName(entity.getName());
					cb.setType(entity.getType());
					firstArr[a] = cb;
				}else if("viewtoo".equals(entity.getType())){
					ViewButton viewButton = new ViewButton();
					viewButton.setName(entity.getName());
					viewButton.setType("view");
					viewButton.setUrl(entity.getPagetype());
					firstArr[a] = viewButton;
				}else{
					ViewButton viewButton = new ViewButton();
					viewButton.setName(entity.getName());
					viewButton.setType("view");
					viewButton.setUrl(entity.getPageurl());
					firstArr[a] = viewButton;
				}

			} else {
				ComplexButton complexButton = new ComplexButton();
				complexButton.setName(entity.getName());

				Button[] secondARR = new Button[childList.size()];
				for (int i = 0; i < childList.size(); i++) {
					MenuEntity children = childList.get(i);
					String type = children.getType();
					if ("view".equals(type)) {
						ViewButton viewButton = new ViewButton();
						viewButton.setName(children.getName());
						viewButton.setType(children.getType());
						viewButton.setUrl(children.getUrl());
						secondARR[i] = viewButton;

					} else if ("click".equals(type)) {

						CommonButton cb1 = new CommonButton();
						cb1.setName(children.getName());
						cb1.setType(children.getType());
						cb1.setKey(children.getId());
						secondARR[i] = cb1;

					}else if("viewtoo".equals(type)){
						ViewButton viewButton = new ViewButton();
						viewButton.setName(children.getName());
						viewButton.setType("view");
						viewButton.setUrl(children.getPagetype());
						secondARR[i] = viewButton;
					}else{
						ViewButton viewButton = new ViewButton();
						viewButton.setName(children.getName());
						viewButton.setType("view");
						viewButton.setUrl(children.getPageurl());
						secondARR[i] = viewButton;
					}

				}
				complexButton.setSub_button(secondARR);
				firstArr[a] = complexButton;
			}
		}
		menu.setButton(firstArr);
		JSONObject jsonMenu = JSONObject.fromObject(menu);
		String keyId=ResourceUtil.getWeiXinAccountId();
		String accessToken = weixinAccountService.getAccessTokenByPrimaryKey(keyId);
		String url = WeixinUtil.menu_create_url.replace("ACCESS_TOKEN", accessToken);
		try {
            JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonMenu.toString());
			LogUtil.info(jsonObject);
			if (jsonObject != null) {
				if (0 == jsonObject.getInt("errcode")) {
					message = "同步菜单信息数据成功！";
					WeixinMenuGroupEntity t = weixinMenuGroupService.get(
							WeixinMenuGroupEntity.class, menuGroupId);
					t.setSynchDate(new Date());
					weixinMenuGroupService.saveOrUpdate(t);
				}else if(61007 == jsonObject.getInt("errcode")){
					message="同步菜单信息数据失败！原因可能为您将公众号菜单权限授权给了另一个平台！请您登陆微信公众平台查看！";
				} else {
					message = "同步菜单信息数据失败！错误码为：" + jsonObject.getInt("errcode")
							+ "错误信息为：" + jsonObject.getString("errmsg");
				}
			} else {
				message = "同步菜单信息数据失败！同步自定义菜单URL地址不正确。";
			}
		} catch (Exception e) {
			message = "同步菜单信息数据失败！";
		} finally {
			systemService.addLog(message, Globals.Log_Type_DEL,
					Globals.Log_Leavel_INFO);
			j.setMsg(this.message);
		}
		return j;
	}
	

//	/**
//	 * 同步微信到平台
//	 * 
//	 * @param menuGroupId
//	 * @param req
//	 * @return
//	 */
//	@RequestMapping(params = "downloadMenu")
//	@ResponseBody
//	public AjaxJson downloadMenu(HttpServletRequest req) {
//		AjaxJson j = new AjaxJson();
//		try {
//			message = "同步菜单信息数据成功！";
//			this.weixinMenuGroupService.refreshMenus(ResourceUtil.getWeiXinAccountId());
//		} catch (Exception e) {
//			e.printStackTrace();
//			message = "同步微信信息数据失败！ ";
//		} finally {
//			j.setMsg(this.message);
//		}
//		return j;
//	}
}

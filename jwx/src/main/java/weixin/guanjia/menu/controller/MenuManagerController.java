package weixin.guanjia.menu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.base.service.WeixinExpandconfigServiceI;
import weixin.guanjia.core.entity.common.Button;
import weixin.guanjia.core.entity.common.CommonButton;
import weixin.guanjia.core.entity.common.ComplexButton;
import weixin.guanjia.core.entity.common.Menu;
import weixin.guanjia.core.entity.common.ViewButton;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.menu.entity.MenuEntity;
import weixin.guanjia.menu.service.WeixinMenuServiceI;
import weixin.guanjia.message.entity.NewsTemplate;
import weixin.guanjia.message.entity.TextTemplate;

/**
 * 微信自定义菜单
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/menuManagerController")
public class MenuManagerController {
	@Autowired
	private SystemService systemService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	@Autowired
	private WeixinMenuServiceI weixinMenuService;
	@Autowired
	private WeixinExpandconfigServiceI weixinExpandconfigService;
	private String message;

	@RequestMapping(params = "list")
	public ModelAndView list() {
		return new ModelAndView("weixin/guanjia/menu/menulist");
	}
	
	@RequestMapping(params = "getSubMenuByGroup")
	public void getSubMenuByGroup(HttpServletRequest request,
			HttpServletResponse response) {
		String accountid = ResourceUtil.getWeiXinAccountId();
		String msgType = request.getParameter("msgType");
		String menuGroupId=request.getParameter("menuGroupId");
		String resMsg = "";
		 JsonConfig config = new JsonConfig();
		 config.setJsonPropertyFilter(new PropertyFilter(){  
			    public boolean apply(Object source, String name, Object value) {  
			        if(name.equals("menuList")) { //要过滤的areas ，Map对象中的  
			            return true;  
			        } else {  
			            return false;  
			        }  
			    }  
			});
		 StringBuffer bu=new StringBuffer();
		 bu.append(" from MenuEntity t  where 1=1");
		 bu.append(" and t.accountId =").append("'").append(accountid).append("'");
		 bu.append("  and t.weixinMenuGroupEntity.id=").append("'").append(menuGroupId).append("'");
			List<MenuEntity> textList = this.weixinMenuService
					.findByQueryString(bu.toString());
			JSONArray json = JSONArray.fromObject(textList,config);
			resMsg = json.toString();
	
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(resMsg);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@RequestMapping(params = "getSubMenu")
	public void getSubMenu(HttpServletRequest request,
			HttpServletResponse response) {
		String accountid = ResourceUtil.getWeiXinAccountId();
		String msgType = request.getParameter("msgType");
		String resMsg = "";
		 JsonConfig config = new JsonConfig();
		 config.setJsonPropertyFilter(new PropertyFilter(){  
			    public boolean apply(Object source, String name, Object value) {  
			        if(name.equals("menuList")) { //要过滤的areas ，Map对象中的  
			            return true;  
			        } else {  
			            return false;  
			        }  
			    }  
			});
		 config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			List<MenuEntity> textList = this.weixinMenuService
					.findByQueryString("from MenuEntity t  where t.accountId = '"
							+  accountid+ "'");
			JSONArray json = JSONArray.fromObject(textList,config);
			resMsg = json.toString();
	
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(resMsg);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@RequestMapping(params = "gettemplate")
	public void gettemplate(HttpServletRequest request,
			HttpServletResponse response) {
		String accountid = ResourceUtil.getWeiXinAccountId();
		String msgType = request.getParameter("msgType");
		String resMsg = "";
		if ("text".equals(msgType)) {
			List<TextTemplate> textList = this.weixinMenuService
					.findByQueryString("from TextTemplate t where t.accountId = '"
							+  accountid+ "'");
			JSONArray json = JSONArray.fromObject(textList);
			resMsg = json.toString();
		} else if ("news".equals(msgType)) {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "newsItemList" });
			List<NewsTemplate> newsList = this.weixinMenuService
					.findByQueryString("from NewsTemplate t where t.accountId = '"
							+ accountid + "'");
			JSONArray json = JSONArray.fromObject(newsList, jsonConfig);
			resMsg = json.toString();
		}else if("expand".equals(msgType)){

			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "newsItemList" });
			
			//List<NewsTemplate> newsList = this.weixinMenuService.findByQueryString("from WeixinExpandconfigEntity t where t.accountid = '" + accountid + "'");
			List<NewsTemplate> newsList = this.weixinMenuService.findByQueryString("from WeixinExpandconfigEntity t");
			
			JSONArray json = JSONArray.fromObject(newsList, jsonConfig);
			resMsg = json.toString();
		
		}
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(resMsg);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * 查询数据
	 * @param treegrid
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public List<TreeGrid> datagrid(TreeGrid treegrid,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {

		CriteriaQuery cq = new CriteriaQuery(MenuEntity.class);
		cq.eq("accountId", ResourceUtil.getWeiXinAccountId());
		if (treegrid.getId() != null) {
			cq.eq("menuEntity.id", treegrid.getId());
		} else {

			cq.isNull("menuEntity");
		}

		cq.addOrder("orders", SortDirection.asc);
		cq.add();

		List<MenuEntity> menuList = systemService.getListByCriteriaQuery(cq,
				false);
		List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		TreeGridModel treeGridModel = new TreeGridModel();
		// treeGridModel.setIcon("orders");
		treeGridModel.setCode("status");
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

	@RequestMapping(params = "jumpSuView")
	public ModelAndView jumpSuView(MenuEntity menuEntity, HttpServletRequest req) {

		org.jeecgframework.core.util.LogUtil.info("...menuEntity.getId()..." + menuEntity.getId());
		if (StringUtil.isNotEmpty(menuEntity.getId())) {
			menuEntity = this.systemService.getEntity(MenuEntity.class,
					menuEntity.getId());
			if (menuEntity.getMenuEntity() != null
					&& menuEntity.getMenuEntity().getId() != null) {
				req
						.setAttribute("fatherId", menuEntity.getMenuEntity()
								.getId());
				req.setAttribute("fatherName", menuEntity.getMenuEntity()
						.getName());
			}
			req.setAttribute("name", menuEntity.getName());
			req.setAttribute("type", menuEntity.getType());
			req.setAttribute("menuKey", menuEntity.getMenuKey());
			req.setAttribute("url", menuEntity.getUrl());
			req.setAttribute("orders", menuEntity.getOrders());
			req.setAttribute("templateId", menuEntity.getTemplateId());
			req.setAttribute("msgType", menuEntity.getMsgType());
		}
		String fatherId = req.getParameter("fatherId");
		if (StringUtil.isNotEmpty(fatherId)) {
			MenuEntity fatherMenuEntity = this.systemService.getEntity(
					MenuEntity.class, fatherId);
			req.setAttribute("fatherId", fatherId);
			req.setAttribute("fatherName", fatherMenuEntity.getName());
			org.jeecgframework.core.util.LogUtil.info(".....fatherName...."
					+ fatherMenuEntity.getName());
		}
		return new ModelAndView("weixin/guanjia/menu/menuinfo");
	}
	
	/**
	 * 
	 * @param menuEntity
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(MenuEntity menuEntity, HttpServletRequest req) {
		String menuGroupId=req.getParameter("menuGroupId");
		req.setAttribute("menuGroupId", menuGroupId);
		org.jeecgframework.core.util.LogUtil.info("...menuEntity.getId()..." + menuEntity.getId());
		if (StringUtil.isNotEmpty(menuEntity.getId())) {
			menuEntity = this.systemService.getEntity(MenuEntity.class,
					menuEntity.getId());
			if (menuEntity.getMenuEntity() != null
					&& menuEntity.getMenuEntity().getId() != null) {
				req
						.setAttribute("fatherId", menuEntity.getMenuEntity()
								.getId());
				req.setAttribute("fatherName", menuEntity.getMenuEntity()
						.getName());
				org.jeecgframework.core.util.LogUtil.info(".....fatherName...."
						+ menuEntity.getMenuEntity().getName());
			}
			req.setAttribute("name", menuEntity.getName());
			req.setAttribute("type", menuEntity.getType());
			req.setAttribute("menuKey", "lxc");
			req.setAttribute("url", menuEntity.getUrl());
			req.setAttribute("orders", menuEntity.getOrders());
			req.setAttribute("templateId", menuEntity.getTemplateId());
			req.setAttribute("msgType", menuEntity.getMsgType());
			req.setAttribute("pagetype", menuEntity.getPagetype());
			req.setAttribute("pageurl",menuEntity.getPageurl());
		}
		return new ModelAndView("weixin/guanjia/menu/menuinfoByGroup");
	}
	
	/**
	 * 校验自定义菜单 自定义菜单目前限制只能3个一级菜单，5个二级菜单
	 * @return
	 */
	public boolean chickMenuCount(){
		
		//查询一级菜单
		String hql = "from MenuEntity where status='1' and fatherid is null and accountId = '" + ResourceUtil.getWeiXinAccountId() + "'";
		List<MenuEntity> menuList = this.systemService.findByQueryString(hql);
		
		if(menuList.size() > 3){
			
			return false;
		}else{
			
			for (int a = 0; a < menuList.size(); a++) {
				MenuEntity entity = menuList.get(a);
				
				String hqls = "from MenuEntity where status='1' and fatherid = '" + entity.getId() + "' and accountId = '" + ResourceUtil.getWeiXinAccountId()+"'";
				List<MenuEntity> childList = this.systemService.findByQueryString(hqls);
				
				if(childList.size() > 5){
					
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * 保存或更新自定义菜单
	 * @param menuEntity
	 * @param req
	 * @param fatherName
	 * @return
	 */
	@RequestMapping(params = "su")
	@ResponseBody
	public AjaxJson su(MenuEntity menuEntity, HttpServletRequest req,String fatherName) {
		AjaxJson j = new AjaxJson();
		String id = oConvertUtils.getString(req.getParameter("id"));
		//String pagetype=req.getParameter("pagetype");

		switch (menuEntity.getType()){
			case"click":
				menuEntity.setUrl("");
				menuEntity.setPageurl("");
				menuEntity.setPagetype("");
				break;
			case"viewtoo":
				menuEntity.setUrl("");
				menuEntity.setPageurl("");
				break;
			case"view":
				menuEntity.setPagetype("");
				menuEntity.setPageurl("");
				try {
					URIBuilder builder = new URIBuilder(menuEntity.getUrl());
					List<NameValuePair> queryParams = builder.getQueryParams();
					if (!queryParams.contains("accountid")) {
						builder.addParameter("accountid", ResourceUtil.getWeiXinAccountId());
					}
					menuEntity.setUrl(builder.build().toString());
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
				
				break;
			case"viewplay":
				menuEntity.setUrl("");
				menuEntity.setPagetype("");
				break;
			default:
				break;
		}

		//校验:自定义菜单目前限制只能3个一级菜单，5个二级菜单
		if(!chickMenuCount()){
			
			message = "自定义菜单目前限制只能3个一级菜单，5个二级菜单";
			j.setMsg(message);
			return j;
		}		
				
		if (StringUtil.isNotEmpty(menuEntity.getId())) {
			
			MenuEntity tempMenu = this.systemService.getEntity(MenuEntity.class, menuEntity.getId());
			tempMenu.setPagetype(menuEntity.getPagetype());
			tempMenu.setPageurl(menuEntity.getPageurl());
			this.message = "更新" + tempMenu.getName() + "的菜单信息信息成功！";
			try {
				MyBeanUtils.copyBeanNotNull2Bean(menuEntity, tempMenu);
				this.weixinMenuService.saveOrUpdate(tempMenu);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				this.message = "更新" + tempMenu.getName() + "的菜单信息信息成功！";
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
				e.printStackTrace();
			}

		} else {
			this.message = "添加" + menuEntity.getName() + "的信息成功！";
			String fatherId = req.getParameter("fatherId");
			if (StringUtil.isNotEmpty(fatherName)) {
				MenuEntity tempMenu = this.systemService.getEntity(
						MenuEntity.class, fatherName);
				tempMenu.setPagetype(menuEntity.getPagetype());
				tempMenu.setPageurl(menuEntity.getPageurl());
				menuEntity.setMenuEntity(tempMenu);
			}
			String accountId = ResourceUtil.getWeiXinAccountId();
			if (!"-1".equals(accountId)) {
				this.weixinMenuService.save(menuEntity);
			} else {
				j.setSuccess(false);
				j.setMsg("请添加一个公众帐号。");
			}
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		return j;
	}

	@RequestMapping(params = "jumpselect")
	public ModelAndView jumpselect() {
		return new ModelAndView("");
	}

	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(MenuEntity menuEntity, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		menuEntity = this.systemService.getEntity(MenuEntity.class, menuEntity
				.getId());

		this.systemService.delete(menuEntity);

		message = "删除" + menuEntity.getName() + "菜单信息数据";
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		j.setMsg(this.message);
		return j;
	}
	
	/**
	 * 启用禁用
	 * @param menuEntity
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "upStatus")
	@ResponseBody
	public AjaxJson upStatus(String id, String type, HttpServletRequest req) {
		
		AjaxJson j = new AjaxJson();
		
		//校验:自定义菜单目前限制只能3个一级菜单，5个二级菜单
		if(!chickMenuCount()){
			
			message = "自定义菜单目前限制只能3个一级菜单，5个二级菜单";
			j.setMsg(message);
			return j;
		}
		
		MenuEntity menuEntity = this.systemService.getEntity(MenuEntity.class, id);

		menuEntity.setStatus(type);
		
		systemService.save(menuEntity);
		
//		if(upLoadMenu()){
//			
//			if(("1").equals(type))
//				message = "启用成功";
//			if(("0").equals(type))
//				message = "禁用成功";
//		}else{
//			
//			if(("1").equals(type))
//				message = "启用失败";
//			if(("0").equals(type))
//				message = "禁用失败";
//		}		
		message = "启用成功";
		j.setMsg(this.message);
		return j;
	}
	
	/**
	 * 上传自定义菜单
	 * @return
	 */
	public boolean upLoadMenu() {
		
		AjaxJson j = new AjaxJson();
		String hql = "from MenuEntity where status='1' and fatherid is null and accountId = '"
				+ ResourceUtil.getWeiXinAccountId() + "'  order by  orders asc";
		List<MenuEntity> menuList = this.systemService.findByQueryString(hql);
		org.jeecgframework.core.util.LogUtil.info(".....一级菜单的个数是....." + menuList.size());
		Menu menu = new Menu();
		Button firstArr[] = new Button[menuList.size()];
		for (int a = 0; a < menuList.size(); a++) {
			MenuEntity entity = menuList.get(a);
			String hqls = "from MenuEntity where status='1' and fatherid = '" + entity.getId()
					+ "' and accountId = '" + ResourceUtil.getWeiXinAccountId()
					+ "'  order by  orders asc";
			List<MenuEntity> childList = this.systemService
					.findByQueryString(hqls);
			// org.jeecgframework.core.util.LogUtil.info("....二级菜单的大小....." + childList.size());
			if (childList.size() == 0) {
				if("view".equals(entity.getType())){
					ViewButton viewButton = new ViewButton();
					viewButton.setName(entity.getName());
					viewButton.setType(entity.getType());
					viewButton.setUrl(entity.getUrl());
					firstArr[a] = viewButton;
				}else if("click".equals(entity.getType())){
					CommonButton cb = new CommonButton();
					cb.setKey(entity.getMenuKey());
					cb.setName(entity.getName());
					cb.setType(entity.getType());
					firstArr[a] = cb;
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
						cb1.setKey(children.getMenuKey());
						secondARR[i] = cb1;

					}

				}
				complexButton.setSub_button(secondARR);
				firstArr[a] = complexButton;
			}
		}
		menu.setButton(firstArr);
		JSONObject jsonMenu = JSONObject.fromObject(menu);
		String accessToken = weixinAccountService.getAccessToken();
		String url = WeixinUtil.menu_create_url.replace("ACCESS_TOKEN",
				accessToken);
		JSONObject jsonObject= new JSONObject();
		try {
			jsonObject = WeixinUtil.httpRequest(url, "POST", jsonMenu.toString());
			LogUtil.info(jsonObject);
			if(jsonObject!=null){
				if (0 == jsonObject.getInt("errcode")) {
						
					return true;
				}
				else {
					return false;
				}
			}else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}finally{
			return false;
		}
	}

	/**
	 * 上传
	 * @param menuEntity
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "sameMenu")
	@ResponseBody
	public AjaxJson sameMenu(MenuEntity menuEntity, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		
		//校验:自定义菜单目前限制只能3个一级菜单，5个二级菜单
		if(!chickMenuCount()){
			
			message = "自定义菜单目前限制只能3个一级菜单，5个二级菜单";
			j.setMsg(message);
			return j;
		}	
				
		String hql = "from MenuEntity where status='1' and fatherid is null and accountId = '"
				+ ResourceUtil.getWeiXinAccountId() + "'  order by  orders asc";
		List<MenuEntity> menuList = this.systemService.findByQueryString(hql);
		org.jeecgframework.core.util.LogUtil.info(".....一级菜单的个数是....." + menuList.size());
		Menu menu = new Menu();
		Button firstArr[] = new Button[menuList.size()];
		for (int a = 0; a < menuList.size(); a++) {
			MenuEntity entity = menuList.get(a);
			String hqls = "from MenuEntity where status='1' and fatherid = '" + entity.getId()
					+ "' and accountId = '" + ResourceUtil.getWeiXinAccountId()
					+ "'  order by  orders asc";
			List<MenuEntity> childList = this.systemService
					.findByQueryString(hqls);
			// org.jeecgframework.core.util.LogUtil.info("....二级菜单的大小....." + childList.size());
			if (childList.size() == 0) {
				if("view".equals(entity.getType())){
					ViewButton viewButton = new ViewButton();
					viewButton.setName(entity.getName());
					viewButton.setType(entity.getType());
					viewButton.setUrl(entity.getUrl());
					firstArr[a] = viewButton;
				}else if("click".equals(entity.getType())){
					CommonButton cb = new CommonButton();
					cb.setKey(entity.getMenuKey());
					cb.setName(entity.getName());
					cb.setType(entity.getType());
					firstArr[a] = cb;
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
						cb1.setKey(children.getMenuKey());
						secondARR[i] = cb1;

					}

				}
				complexButton.setSub_button(secondARR);
				firstArr[a] = complexButton;
			}
		}
		menu.setButton(firstArr);
		JSONObject jsonMenu = JSONObject.fromObject(menu);
		String accessToken = weixinAccountService.getAccessToken();
		String url = WeixinUtil.menu_create_url.replace("ACCESS_TOKEN",
				accessToken);
		JSONObject jsonObject= new JSONObject();
		try {
			jsonObject = WeixinUtil.httpRequest(url, "POST", jsonMenu.toString());
			LogUtil.info(jsonObject);
			if(jsonObject!=null){
				if (0 == jsonObject.getInt("errcode")) {
						message = "同步菜单信息数据成功！";
				}
				else {
					message = "同步菜单信息数据失败！错误码为："+jsonObject.getInt("errcode")+"错误信息为："+jsonObject.getString("errmsg");
				}
			}else{
				message = "同步菜单信息数据失败！同步自定义菜单URL地址不正确。";
			}
		} catch (Exception e) {
			message = "同步菜单信息数据失败！";
		}finally{
			systemService.addLog(message, Globals.Log_Type_DEL,
					Globals.Log_Leavel_INFO);
			j.setMsg(this.message);
		}
		return j;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		
	}
}
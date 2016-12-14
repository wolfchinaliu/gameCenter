package org.jeecgframework.web.system.controller.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboBox;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.ValidForm;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ListtoMenu;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.RoletoJson;
import org.jeecgframework.core.util.SetListSort;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.DataTableReturn;
import org.jeecgframework.tag.vo.datatable.DataTables;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.integrate.entity.WxIntegrateSecretEntity;
import weixin.util.CommonUtils;

/**
 * @author
 * @ClassName: UserController
 * @Description: TODO(用户管理处理类)
 */
@Scope("prototype")
@Controller
@RequestMapping("/userController")
public class UserController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(UserController.class);

	private UserService userService;
	private SystemService systemService;
	private String message = null;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 菜单列表
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "menu")
	public void menu(HttpServletRequest request, HttpServletResponse response) {
		SetListSort sort = new SetListSort();
		TSUser u = ResourceUtil.getSessionUserName();
		// 登陆者的权限
		Set<TSFunction> loginActionlist = new HashSet();// 已有权限菜单

		TSRole role = u.getTSRole();
		List<TSRoleFunction> roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id",
				role.getId());
		if (roleFunctionList.size() > 0) {
			for (TSRoleFunction roleFunction : roleFunctionList) {
				TSFunction function = (TSFunction) roleFunction.getTSFunction();
				loginActionlist.add(function);
			}
		}

		List<TSFunction> bigActionlist = new ArrayList();// 一级权限菜单
		List<TSFunction> smailActionlist = new ArrayList();// 二级权限菜单
		if (loginActionlist.size() > 0) {
			for (TSFunction function : loginActionlist) {
				if (function.getFunctionLevel() == 0) {
					bigActionlist.add(function);
				} else if (function.getFunctionLevel() == 1) {
					smailActionlist.add(function);
				}
			}
		}
		// 菜单栏排序
		Collections.sort(bigActionlist, sort);
		Collections.sort(smailActionlist, sort);
		String logString = ListtoMenu.getMenu(bigActionlist, smailActionlist);
		// request.setAttribute("loginMenu",logString);
		try {
			response.getWriter().write(logString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户列表页面跳转[跳转到标签和手工结合的html页面]
	 *
	 * @return
	 */
	@RequestMapping(params = "userDemo")
	public String userDemo(HttpServletRequest request) {

		// 系统管理员才有权限
		TSUser users = systemService.get(TSUser.class, ResourceUtil.getSessionUserName().getId());
		if (!"2".equals(users.getType())) {
			return "common/403";
		}

		// 给部门查询条件中的下拉框准备数据
		List<TSDepart> departList = systemService.getList(TSDepart.class);

		if (null != departList)
			request.setAttribute("departsReplace", RoletoJson.listToReplaceStr(departList, "departname", "id"));

		return "system/user/userList2";
	}

	/**
	 * 用户列表页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "user")
	public String user(HttpServletRequest request) {
		// 给部门查询条件中的下拉框准备数据
		List<TSDepart> departList = systemService.getList(TSDepart.class);

		if (null != departList)
			request.setAttribute("departsReplace", RoletoJson.listToReplaceStr(departList, "departname", "id"));

		List<TSRole> roleList = systemService.getList(TSRole.class);

		if (null != roleList)
			request.setAttribute("rolesReplace", RoletoJson.listToReplaceStr(roleList, "roleName", "id"));

		return "system/user/userList";
	}

	/**
	 * 用户信息
	 *
	 * @return
	 */
	@RequestMapping(params = "userinfo")
	public String userinfo(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("user", user);
		return "system/user/userinfo";
	}

	/**
	 * 修改密码
	 *
	 * @return
	 */
	@RequestMapping(params = "changepassword")
	public String changepassword(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("user", user);
		return "system/user/changepassword";
	}

	/**
	 * 判断修改商户密码之前原密码是否正确
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "checkPWD")
	@ResponseBody
	public AjaxJson checkPWD(HttpServletRequest request) {

		AjaxJson j = new AjaxJson();

		// 获取页面输入项
		// String password = request.getParameter("password");

		String accountId = ResourceUtil.getWeiXinAccountId();

		TSUser user = ResourceUtil.getSessionUserName();
		String password = oConvertUtils.getString(request.getParameter("password")); // 取到页面的密码进行加密
		String pString = PasswordUtil.encrypt(user.getUserName(), password, PasswordUtil.getStaticSalt());
		// String hql0 = "from TSBaseUser where accountid = '" + accountId + "'
		// AND password = '" + password + "'";
		// List<TSBaseUser> lisUser = systemService.findHql(hql0, null);
		// TSBaseUser user = lisUser.get(0);
		// TSUser users = systemService.findUniqueByProperty(TSUser.class,
		// "userName", password);
		if (!pString.equals(user.getPassword())) {
			j.setMsg("原密码输入错误，请重新输入");
			j.setSuccess(false);
		} else {
			j.setMsg("密码正确");
		}
		// if (lisUser.size() > 0) {
		//
		// j.setMsg("密码输入正确");
		// } else {
		// j.setMsg("原密码输入错误，请重新输入");
		// j.setSuccess(false);
		// }
		return j;
	}

	/**
	 * 修改密码
	 *
	 * @return
	 */
	@RequestMapping(params = "savenewpwd")
	@ResponseBody
	public AjaxJson savenewpwd(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		TSUser user = ResourceUtil.getSessionUserName();
		String password = oConvertUtils.getString(request.getParameter("password"));
		String newpassword = oConvertUtils.getString(request.getParameter("newpassword"));
		String pString = PasswordUtil.encrypt(user.getUserName(), password, PasswordUtil.getStaticSalt());
		if (!pString.equals(user.getPassword())) {
			j.setMsg("原密码不正确");
			j.setSuccess(false);
		} else {
			try {
				user.setPassword(PasswordUtil.encrypt(user.getUserName(), newpassword, PasswordUtil.getStaticSalt()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			systemService.updateEntitie(user);
			j.setMsg("修改成功");

		}
		return j;
	}

	/**
	 * 修改用户密码
	 *
	 * @author Chj
	 */

	@RequestMapping(params = "changepasswordforuser")
	public ModelAndView changepasswordforuser(TSUser user, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(user.getId())) {
			user = systemService.getEntity(TSUser.class, user.getId());
			req.setAttribute("user", user);
			idandname(req, user);
			org.jeecgframework.core.util.LogUtil.info(user.getPassword() + "-----" + user.getRealName());
		}
		return new ModelAndView("system/user/adminchangepwd");
	}

	@RequestMapping(params = "savenewpwdforuser")
	@ResponseBody
	public AjaxJson savenewpwdforuser(HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		String id = oConvertUtils.getString(req.getParameter("id"));
		String password = oConvertUtils.getString(req.getParameter("password"));
		if (StringUtil.isNotEmpty(id)) {
			TSUser users = systemService.getEntity(TSUser.class, id);
			org.jeecgframework.core.util.LogUtil.info(users.getUserName());
			users.setPassword(PasswordUtil.encrypt(users.getUserName(), password, PasswordUtil.getStaticSalt()));
			users.setStatus(Globals.User_Normal);
			users.setActivitiSync(users.getActivitiSync());
			systemService.updateEntitie(users);
			message = "用户: " + users.getUserName() + "密码重置成功";
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}

		j.setMsg(message);

		return j;
	}

	/**
	 * 锁定账户
	 *
	 * @author Chj
	 */
	@RequestMapping(params = "lock")
	@ResponseBody
	public AjaxJson lock(String id, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();

		TSUser user = systemService.getEntity(TSUser.class, id);
		if ("admin".equals(user.getUserName())) {
			message = "超级管理员[admin]不可锁定";
			j.setMsg(message);
			return j;
		}
		if (user.getStatus() != Globals.User_Forbidden) {
			user.setStatus(Globals.User_Forbidden);
			userService.updateEntitie(user);
			message = "用户：" + user.getUserName() + "锁定成功";
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} else {
			message = "锁定账户失败";
		}

		j.setMsg(message);
		return j;
	}

	/**
	 * 启用账户
	 *
	 * @author Chj
	 */
	@RequestMapping(params = "deblocking")
	@ResponseBody
	public AjaxJson deblocking(String id, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();

		TSUser user = systemService.getEntity(TSUser.class, id);

		if ("admin".equals(user.getUserName())) {
			message = "超级管理员[admin]不可操作";
			j.setMsg(message);
			return j;
		}
		if (user.getStatus() == Globals.User_Forbidden) {

			user.setStatus(Globals.User_Normal);
			userService.updateEntitie(user);
			message = "用户：" + user.getUserName() + "启用成功";
		} else {

			message = "启用账户失败";
		}

		j.setMsg(message);
		return j;
	}

	/**
	 * 得到角色列表
	 *
	 * @return
	 */
	@RequestMapping(params = "role")
	@ResponseBody
	public List<ComboBox> role(HttpServletResponse response, HttpServletRequest request, ComboBox comboBox) {

		String id = request.getParameter("id");
		TSUser user = systemService.findUniqueByProperty(TSUser.class, "id", id);

		List<ComboBox> comboBoxs = new ArrayList<ComboBox>();
		List<TSRole> roles = new ArrayList();

		roles.add(user.getTSRole());

		List<TSRole> roleList = systemService.getList(TSRole.class);
		comboBoxs = TagUtil.getComboBox(roleList, roles, comboBox);
		return comboBoxs;
	}

	/**
	 * 得到部门列表
	 *
	 * @return
	 */
	@RequestMapping(params = "depart")
	@ResponseBody
	public List<ComboBox> depart(HttpServletResponse response, HttpServletRequest request, ComboBox comboBox) {
		String id = request.getParameter("id");
		List<ComboBox> comboBoxs = new ArrayList<ComboBox>();
		List<TSDepart> departs = new ArrayList();
		if (StringUtil.isNotEmpty(id)) {
			TSUser user = systemService.get(TSUser.class, id);
			if (user.getTSDepart() != null) {
				TSDepart depart = systemService.get(TSDepart.class, user.getTSDepart().getId());
				departs.add(depart);
			}
		}
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		comboBoxs = TagUtil.getComboBox(departList, departs, comboBox);
		return comboBoxs;
	}

	/**
	 * easyuiAJAX用户列表请求数据
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, user);

		Short[] userstate = new Short[] { Globals.User_Normal, Globals.User_ADMIN, Globals.User_Forbidden };
		cq.in("status", userstate);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 用户信息录入和更新
	 *
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TSUser user, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		if ("admin".equals(user.getUserName())) {
			message = "超级管理员[admin]不可删除";
			j.setMsg(message);
			return j;
		}
		user = systemService.getEntity(TSUser.class, user.getId());

		if (!user.getStatus().equals(Globals.User_ADMIN)) {

			userService.delete(user);
			message = "用户：" + user.getUserName() + "删除成功";
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} else {
			message = "超级管理员不可删除";
		}

		j.setMsg(message);
		return j;
	}

	/**
	 * 检查用户名
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "checkUser")
	@ResponseBody
	public ValidForm checkUser(HttpServletRequest request) {
		ValidForm v = new ValidForm();
		String userName = oConvertUtils.getString(request.getParameter("param"));
		String code = oConvertUtils.getString(request.getParameter("code"));
		List<TSUser> roles = systemService.findByProperty(TSUser.class, "userName", userName);
		if (roles.size() > 0 && !code.equals(userName)) {
			v.setInfo("用户名已存在");
			v.setStatus("n");
		}
		return v;
	}

	/**
	 * 用户录入
	 *
	 * @param user
	 * @param req
	 * @return
	 */

	@RequestMapping(params = "saveUser")
	@ResponseBody
	public AjaxJson saveUser(HttpServletRequest req, TSUser user) {
		AjaxJson j = new AjaxJson();

		// 角色
		String roleid = oConvertUtils.getString(req.getParameter("roleid"));
		TSRole uRole = systemService.getEntity(TSUser.class, roleid);

		String password = oConvertUtils.getString(req.getParameter("password"));
		if (StringUtil.isNotEmpty(user.getId())) {
			TSUser users = systemService.getEntity(TSUser.class, user.getId());
			users.setEmail(user.getEmail());
			users.setOfficePhone(user.getOfficePhone());
			users.setMobilePhone(user.getMobilePhone());
			users.setTSDepart(user.getTSDepart());
			users.setTSRole(uRole);
			users.setRealName(user.getRealName());
			users.setStatus(Globals.User_Normal);
			users.setActivitiSync(user.getActivitiSync());
			systemService.updateEntitie(users);

			message = "用户: " + users.getUserName() + "更新成功";

			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			TSUser users = systemService.findUniqueByProperty(TSUser.class, "userName", user.getUserName());
			if (users != null) {
				message = "用户: " + users.getUserName() + "已经存在";
			} else {

				String shopSymbol = req.getParameter("shopSymbol");
				if (shopSymbol != null && "shop".equals(shopSymbol)) {
					TSRole tsRole = this.systemService.findUniqueByProperty(TSRole.class, "roleCode", "manager");
					roleid = tsRole.getId();
				}

				user.setPassword(PasswordUtil.encrypt(user.getUserName(), password, PasswordUtil.getStaticSalt()));

				if (user.getTSDepart().equals("")) {
					user.setTSDepart(null);
				}
				user.setStatus(Globals.User_Normal);
				systemService.save(user);
				message = "用户: " + user.getUserName() + "添加成功";

				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}

		}
		j.setMsg(message);

		return j;
	}

	/**
	 * 用户选择角色跳转页面
	 *
	 * @return
	 */
	@RequestMapping(params = "roles")
	public String roles() {
		return "system/user/users";
	}

	/**
	 * 角色显示列表
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridRole")
	public void datagridRole(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSRole.class, dataGrid);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * easyuiAJAX请求数据： 用户选择角色列表
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TSUser user, HttpServletRequest req) {
		List<TSDepart> departList = new ArrayList<TSDepart>();
		String departid = oConvertUtils.getString(req.getParameter("departid"));
		if (!StringUtil.isEmpty(departid)) {
			departList.add((TSDepart) systemService.getEntity(TSDepart.class, departid));
		} else {
			departList.addAll((List) systemService.getList(TSDepart.class));
		}
		req.setAttribute("departList", departList);
		if (StringUtil.isNotEmpty(user.getId())) {
			user = systemService.getEntity(TSUser.class, user.getId());

			req.setAttribute("user", user);
			idandname(req, user);

		}
		return new ModelAndView("system/user/user");

	}

	public void idandname(HttpServletRequest req, TSUser user) {

		req.setAttribute("id", user.getTSRole().getId());
		req.setAttribute("roleName", user.getTSRole().getRoleName());

	}

	/**
	 * 根据部门和角色选择用户跳转页面
	 */
	@RequestMapping(params = "choose")
	public String choose(HttpServletRequest request) {
		List<TSRole> roles = systemService.loadAll(TSRole.class);
		request.setAttribute("roleList", roles);
		return "system/membership/checkuser";
	}

	/**
	 * 部门和角色选择用户的panel跳转页面
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "chooseUser")
	public String chooseUser(HttpServletRequest request) {
		String departid = request.getParameter("departid");
		String roleid = request.getParameter("roleid");
		request.setAttribute("roleid", roleid);
		request.setAttribute("departid", departid);
		return "system/membership/userlist";
	}

	/**
	 * 部门和角色选择用户的用户显示列表
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridUser")
	public void datagridUser(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {

		String departid = request.getParameter("departid");
		String roleid = request.getParameter("roleid");

		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
		if (departid.length() > 0) {
			cq.eq("TDepart.departid", oConvertUtils.getInt(departid, 0));
			cq.eq("TSRole.roleid", oConvertUtils.getInt(roleid, 0));
			cq.add();
		}

		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 根据部门和角色选择用户跳转页面
	 */
	@RequestMapping(params = "roleDepart")
	public String roleDepart(HttpServletRequest request) {
		List<TSRole> roles = systemService.loadAll(TSRole.class);
		request.setAttribute("roleList", roles);
		return "system/membership/roledepart";
	}

	/**
	 * 部门和角色选择用户的panel跳转页面
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "chooseDepart")
	public ModelAndView chooseDepart(HttpServletRequest request) {
		String nodeid = request.getParameter("nodeid");
		ModelAndView modelAndView = null;
		if (nodeid.equals("role")) {
			modelAndView = new ModelAndView("system/membership/users");
		} else {
			modelAndView = new ModelAndView("system/membership/departList");
		}
		return modelAndView;
	}

	/**
	 * 部门和角色选择用户的用户显示列表
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridDepart")
	public void datagridDepart(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class, dataGrid);
		systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 测试
	 *
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "test")
	public void test(HttpServletRequest request, HttpServletResponse response) {
		String jString = request.getParameter("_dt_json");
		DataTables dataTables = new DataTables(request);
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataTables);
		String username = request.getParameter("userName");
		if (username != null) {
			cq.like("userName", username);
			cq.add();
		}
		DataTableReturn dataTableReturn = systemService.getDataTableReturn(cq, true);
		TagUtil.datatable(response, dataTableReturn, "id,userName,mobilePhone,TSDepart_departname");
	}

	/**
	 * 用户列表页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "index")
	public String index() {
		return "bootstrap/main";
	}

	/**
	 * 用户列表页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "main")
	public String main() {
		return "bootstrap/test";
	}

	/**
	 * 测试
	 *
	 * @return
	 */
	@RequestMapping(params = "testpage")
	public String testpage(HttpServletRequest request) {
		return "test/test";
	}

	/**
	 * 设置签名跳转页面
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "addsign")
	public ModelAndView addsign(HttpServletRequest request) {
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		return new ModelAndView("system/user/usersign");
	}

	/**
	 * 用户录入
	 *
	 * @param user
	 * @param req
	 * @return
	 */

	@RequestMapping(params = "savesign", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson savesign(HttpServletRequest req) {
		UploadFile uploadFile = new UploadFile(req);
		String id = uploadFile.get("id");
		TSUser user = systemService.getEntity(TSUser.class, id);
		uploadFile.setRealPath("signatureFile");
		uploadFile.setCusPath("signature");
		uploadFile.setByteField("signature");
		uploadFile.setBasePath("resources");
		uploadFile.setRename(false);
		uploadFile.setObject(user);
		AjaxJson j = new AjaxJson();
		message = user.getUserName() + "设置签名成功";
		systemService.uploadFile(uploadFile);
		systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		j.setMsg(message);

		return j;
	}

	/**
	 * 测试组合查询功能
	 *
	 * @param user
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "testSearch")
	public void testSearch(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
		if (user.getUserName() != null) {
			cq.like("userName", user.getUserName());
		}
		if (user.getRealName() != null) {
			cq.like("realName", user.getRealName());
		}
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "changestyle")
	public String changeStyle(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		if (user == null) {
			return "login/login";
		}
		String indexStyle = "shortcut";
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie == null || StringUtils.isEmpty(cookie.getName())) {
				continue;
			}
			if (cookie.getName().equalsIgnoreCase("JEECGINDEXSTYLE")) {
				indexStyle = cookie.getValue();
			}
		}
		request.setAttribute("indexStyle", indexStyle);
		return "system/user/changestyle";
	}

	/**
	 * @param request
	 * @return AjaxJson
	 * @throws @Title:
	 *             saveStyle
	 * @Description: 修改首页样式
	 */
	@RequestMapping(params = "savestyle")
	@ResponseBody
	public AjaxJson saveStyle(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		j.setSuccess(Boolean.FALSE);
		TSUser user = ResourceUtil.getSessionUserName();
		if (user != null) {
			String indexStyle = request.getParameter("indexStyle");
			if (StringUtils.isNotEmpty(indexStyle)) {
				Cookie cookie = new Cookie("JEECGINDEXSTYLE", indexStyle);
				// 设置cookie有效期为一个月
				cookie.setMaxAge(3600 * 24 * 30);
				response.addCookie(cookie);
				j.setSuccess(Boolean.TRUE);
				j.setMsg("样式修改成功，请刷新页面");
			}
			ClientManager.getInstance().getClient().getFunctions().clear();
		} else {
			j.setMsg("请登录后再操作");
		}
		return j;
	}

	// 跳转密钥页面
	@RequestMapping(params = "goSecretKey")
	public String goSecretKey(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("user", user);
		return "system/user/secretKey";
	}

	// 获取/重置密钥
	@RequestMapping(params = "optSecretKey")
	@ResponseBody
	public AjaxJson optSecretKey(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		String code = request.getParameter("code");
		if (code == null)
			return j;
		try{
		WeixinAccountEntity account = ResourceUtil.getWeiXinAccount();
		TSUser user = ResourceUtil.getSessionUserName();
		String password = oConvertUtils.getString(request.getParameter("password")); // 取到页面的密码进行加密
		String pString = PasswordUtil.encrypt(user.getUserName(), password, PasswordUtil.getStaticSalt());
		if (!pString.equals(user.getPassword())) {
			j.setMsg("登陆密码输入错误，请重新输入");
			j.setSuccess(false);
			return j;
		}
		// 获取
		if (StringUtil.isEmpty(account.getAcctId())) {
			j.setMsg("此商户不存在数据密钥");
			j.setSuccess(false);
			return j;
		}
		String hql = " from WxIntegrateSecretEntity where acctId = ?";
		List<WxIntegrateSecretEntity> list = systemService.findHql(hql, account.getAcctId());

		if (list != null && list.size() != 0) {
			if (code.equals("1")) {
				j.setMsg("获取密钥成功  密钥：" + list.get(0).getSecret());
				j.setSuccess(true);
				return j;
			}else if(code.equals("2")){
				String secretKey = CommonUtils.randomDesKey();
				WxIntegrateSecretEntity integrateSecret = list.get(0);
				integrateSecret.setSecret(secretKey);
				systemService.updateEntitie(integrateSecret);
				j.setMsg("重置密钥成功  密钥：" + secretKey);
				j.setSuccess(true);
				return j;
			}
		}
		String secretKey = CommonUtils.randomDesKey();
		WxIntegrateSecretEntity integrateSecret = new WxIntegrateSecretEntity();
		integrateSecret.setAcctId(account.getAcctId());
		integrateSecret.setCreateDate(new Date());
		integrateSecret.setSecret(secretKey);
		systemService.save(integrateSecret);
		j.setMsg("操作成功 密钥："+secretKey);
		j.setSuccess(true);
		return j;
		}catch(Exception e){
			j.setMsg("操作失败 ");
			logger.error("商户密钥操作异常" ,e);
			j.setSuccess(false);
			return j;
		}finally {
			logger.info("商户密钥操作--" +j.getMsg());
		}
	}
	

}
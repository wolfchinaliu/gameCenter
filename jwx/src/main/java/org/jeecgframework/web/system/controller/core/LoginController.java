package org.jeecgframework.web.system.controller.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.annotation.DataSourceSwitch;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.IpUtil;
import org.jeecgframework.core.util.ListtoMenu;
import org.jeecgframework.core.util.NumberComparator;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.minidao.datasource.DataSourceContextHolder;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.Client;
import org.jeecgframework.web.system.pojo.base.TSConfig;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.entity.WeixinIndividualizationEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.tenant.entity.TFavoLinkEntity;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.entity.WeixinAnnouncementEntity;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.tenant.service.WeixinAcctServiceI;
import weixin.tenant.service.WeixinAnnouncementServiceI;
import weixin.util.WeiXinConstants;

/**
 * 登陆初始化控制器
 * @author
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/loginController")
public class LoginController extends BaseController{
	
	private Logger log = Logger.getLogger(LoginController.class);
	
	private SystemService systemService;
	
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	
	@Autowired
	private WeixinAcctServiceI weixinAcctService;
	
	private UserService userService;
	
	@Autowired
	private WeixinAnnouncementServiceI weixinAnnouncementService;
	
	private String message = null;
	
	@Autowired
	private WeixinAcctFlowAccoutServiceI weixinAcctFlowAccoutServiceI;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@Autowired
	public void setUserService(UserService userService) {

		this.userService = userService;
	}

	/**
	 * admin密码初始化 屏蔽
	 * @return
	 */
	@RequestMapping(params = "goPwdInit")
	public String goPwdInit() {
		
		//return "login/pwd_init";
		return "login/login";
	}

	/**
	 * admin账户密码初始化
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "pwdInit")
	public ModelAndView pwdInit(HttpServletRequest request) {
		
		ModelAndView modelAndView = null;
		TSUser user = new TSUser();
		user.setUserName("admin");
		String newPwd = "shi123";
		userService.pwdInit(user, newPwd);
		modelAndView = new ModelAndView(new RedirectView("loginController.do?login"));
		return modelAndView;
	}

	/**
	 * 检查用户名称
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "checkuser")
	@DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
	@ResponseBody
	public AjaxJson checkuser(TSUser user, HttpServletRequest req) {
		
		HttpSession session = ContextHolderUtils.getSession();
		
		AjaxJson j = new AjaxJson();
		
		//验证码校验
        String randCode = req.getParameter("randCode");
        if (StringUtils.isEmpty(randCode)) {
            j.setMsg("请输入验证码");
            j.setSuccess(false);
        } else if (!randCode.equalsIgnoreCase(String.valueOf(session.getAttribute("randCode")))) {
            // todo "randCode"和验证码servlet中该变量一样，通过统一的系统常量配置比较好，暂时不知道系统常量放在什么地方合适
            j.setMsg("验证码错误！");
            j.setSuccess(false);
        } else {
        	
        	//查询用户是否唯一
            int users = userService.getList(TSUser.class).size();
            
            if (users == 0) {
                j.setMsg("登录错误，请联系管理员");
                j.setSuccess(false);
            } else {
            	
                TSUser u = userService.checkUserExits(user);
                if(u == null) {
                    j.setMsg("用户名或密码错误!");
                    j.setSuccess(false);
                    return j;
                }
                
                TSUser u2 = userService.getEntity(TSUser.class, u.getId());
            
                if (u != null && u2.getStatus()!=0) {
                    // if (user.getUserKey().equals(u.getUserKey())) {
                    	message = "用户: " + user.getUserName() + "登录成功";
                        Client client = new Client();
                        client.setIp(IpUtil.getIpAddr(req));
                        client.setLogindatetime(new Date());
                        client.setUser(u);
                        ClientManager.getInstance().addClinet(session.getId(),client);
                        
                        // 添加登陆日志
                        systemService.addLog(message, Globals.Log_Type_LOGIN, Globals.Log_Leavel_INFO);

                } else {
                    j.setMsg("用户名或密码错误!");
                    j.setSuccess(false);
                }
            }
        }
		return j;
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "login")
	@DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
	public String login(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) {
		
		
		TSUser user = ResourceUtil.getSessionUserName();
		if (user != null) {
			
			if(null == ResourceUtil.getWeiXinAccountId()){
			
				//根据登录用户获取一级公众号
				WeixinAccountEntity  weixinAccountEntity = weixinAccountService.findLoginWeixinAccount();
				request.getSession().setAttribute(WeiXinConstants.WEIXIN_ACCOUNT, weixinAccountEntity);
			}
			if(null == ResourceUtil.getWeiXinAcctId()){
			    WeixinAcctEntity acctEntity = weixinAccountService.findLoginWeixinAcct();
			    request.getSession().setAttribute(WeiXinConstants.WEIXIN_ACCT, acctEntity);
			}
			//modelMap.put("accountName", weixinAccountEntity.getAccountname());
			user = userService.getEntity(TSUser.class, user.getId());

            modelMap.put("roleName", user.getTSRole().getRoleName());
            modelMap.put("userName", user.getUserName());
			request.getSession().setAttribute("CKFinder_UserRole", "admin");
			request.getSession().setAttribute("isShowpoint", user.getIsShowPoint());
			// 默认风格
//			String indexStyle = "sliding";
//			Cookie[] cookies = request.getCookies();
//			for (Cookie cookie : cookies) {
//				if (cookie == null || StringUtils.isEmpty(cookie.getName())) {
//					continue;
//				}
//				if (cookie.getName().equalsIgnoreCase("JEECGINDEXSTYLE")) {
//					indexStyle = cookie.getValue();
//				}
//			}
//			// 要添加自己的风格，复制下面三行即可
//			if (StringUtils.isNotEmpty(indexStyle)
//					&& indexStyle.equalsIgnoreCase("bootstrap")) {
//				return "main/bootstrap_main";
//			}
//			if (StringUtils.isNotEmpty(indexStyle)
//					&& indexStyle.equalsIgnoreCase("shortcut")) {
//				return "main/shortcut_main";
//			}
//			if (StringUtils.isNotEmpty(indexStyle)
//					&& indexStyle.equalsIgnoreCase("sliding")) {
//				return "main/sliding_main";
//			}

			//当前公众号
			WeixinAccountEntity weixinAccount = (WeixinAccountEntity)weixinAccountService.getEntity(WeixinAccountEntity.class, ResourceUtil.getWeiXinAccountId());
			request.setAttribute("weixinAccount", weixinAccount);
			request.setAttribute("domain",ResourceUtil.getConfigByName("domain"));
		    weixinAcctFlowAccountEntity acctFlowAccountEntity1 = new weixinAcctFlowAccountEntity();
		    if (StringUtil.isNotEmpty(ResourceUtil.getSessionUserName().getTenantId())) {
	            String id = ResourceUtil.getSessionUserName().getTenantId();
	            acctFlowAccountEntity1 = weixinAcctFlowAccoutServiceI.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "tenantId", id);
	            if (null == acctFlowAccountEntity1) {
	            	acctFlowAccountEntity1 = new weixinAcctFlowAccountEntity();
	            }
	            if(acctFlowAccountEntity1 != null){
	            	if (acctFlowAccountEntity1.getCountryFlowValue() == null) {
	            		acctFlowAccountEntity1.setCountryFlowValue(0.0);
	            	}
	            	if (acctFlowAccountEntity1.getProvinceFlowValue() == null) {
	            		acctFlowAccountEntity1.setProvinceFlowValue(0.0);
	            	}
	            }
	            request.setAttribute("weixinAcctFlowChargePage", acctFlowAccountEntity1);
		    } 
		    
		    WeixinIndividualizationEntity  weixinIndividualizationEntity = new WeixinIndividualizationEntity();
		    if (StringUtil.isNotEmpty(ResourceUtil.getSessionUserName().getTenantId())) {
		    	String acctId = ResourceUtil.getSessionUserName().getTenantId();
		    	weixinIndividualizationEntity = weixinAccountService.getIndividualization(acctId);
	            request.setAttribute("WeixinIndividualization", weixinIndividualizationEntity);
		    }

			return "main/main";
		} else {
			return "login/login";
		}
		
		
	}
	
	/**
	 * 退出系统
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "logout")
	public ModelAndView logout(HttpServletRequest request) {
		
		HttpSession session = ContextHolderUtils.getSession();
		TSUser user = ResourceUtil.getSessionUserName();
		systemService.addLog("用户" + user.getUserName() + "已退出", Globals.Log_Type_EXIT, Globals.Log_Leavel_INFO);
		
		//移除session中的用户和公众号
		ClientManager.getInstance().removeClinet(session.getId());
		session.removeAttribute(WeiXinConstants.WEIXIN_ACCOUNT);
		session.removeAttribute(WeiXinConstants.WEIXIN_ACCT);
		
		ModelAndView modelAndView = new ModelAndView(new RedirectView("loginController.do?goLogin"));
		return modelAndView;
	}
	
	/**
	 * 跳转到登录页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goLogin")
	public ModelAndView goLogin(HttpServletRequest request) {
		return new ModelAndView("login/login");
	}

	/**
	 * 菜单跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "left")
	public ModelAndView left(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		HttpSession session = ContextHolderUtils.getSession();
        ModelAndView modelAndView = new ModelAndView();
		// 登陆者的权限
		if (user.getId() == null) {
			session.removeAttribute(Globals.USER_SESSION);
            modelAndView.setView(new RedirectView("loginController.do?login"));
		}else{
            List<TSConfig> configs = userService.loadAll(TSConfig.class);
            for (TSConfig tsConfig : configs) {
                request.setAttribute(tsConfig.getCode(), tsConfig.getContents());
            }
            modelAndView.setViewName("main/left");
            request.setAttribute("menuMap", getFunctionMap(user));
        }
		return modelAndView;
	}
	
	/**
	 * 折叠菜单导航
	 * @param request
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	@RequestMapping(params = "menu")
	public ModelAndView menu(HttpServletRequest request) throws NoSuchFieldException, SecurityException {
		TSUser user = ResourceUtil.getSessionUserName();
		HttpSession session = ContextHolderUtils.getSession();
        ModelAndView modelAndView = new ModelAndView();
		// 登陆者的权限
		if (user.getId() == null) {
			session.removeAttribute(Globals.USER_SESSION);
            modelAndView.setView(new RedirectView("loginController.do?login"));
		}else{
			
//			List<TSFunction> functionList = new ArrayList<TSFunction>();
//			
//			List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
//            
//			for (TSRoleUser ru : rUsers) {
//				TSRole role = ru.getTSRole();
//				List<TSRoleFunction> roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id",role.getId());
//				
//				
//				for (TSRoleFunction roleFunction : roleFunctionList) {
//					
//					TSFunction function = roleFunction.getTSFunction();
//					functionList.add(function);
//				}
//				
//				
//			}
			

//			String hql = "from TSFunction a right join TSRoleFunction b where b.TSRole.id='4028d881436d514601436d5215a00042' order by a.functionLevel,a.functionOrder asc";
//			List<TSFunction> functionList = systemService.findByQueryString(hql);
			
//			CriteriaQuery cq = new CriteriaQuery(TSRoleFunction.class);
//			
//			cq.eq("TSRole.id", role.getId());
//			cq.addOrder("TSFunction.functionOrder", SortDirection.asc);
//			cq.add();
//			
//			List<TSFunction> functionList = systemService.getListByCriteriaQuery(cq, false);
//			
			user = userService.getEntity(TSUser.class, user.getId());
			
			List<TSFunction> functionList = new ArrayList<TSFunction>();
			
			String sql = "select a.id as id,a.functionname as functionName,a.functionlevel as functionLevel,a.functionurl as functionUrl,a.functionorder as functionOrder,a.parentfunctionid,c.path,c.iconclas from t_s_function a right join t_s_role_function b on a.id=b.functionid left join t_s_icon c on a.iconid=c.id where b.roleid='"+user.getTSRole().getId()+"' order by a.functionlevel,a.functionorder asc";
			List list = systemService.findListbySql(sql);
			
			for(int i=0;i<list.size();i++){
				
				TSFunction function = new TSFunction();
				
				Object[] objects = (Object[])list.get(i);
				function.setId(objects[0].toString());
				function.setFunctionName(objects[1].toString());
				function.setFunctionLevel(new Short(objects[2].toString()));
				function.setFunctionUrl(objects[3].toString());
				function.setFunctionOrder(objects[4].toString());
				
				if(objects[5] != null){
					
					TSFunction pfunction = new TSFunction();
					pfunction.setId(objects[5].toString());
					function.setTSFunction(pfunction);
				}
				
				function.setMenuCoin(objects[6].toString());
				function.setAccordionCoin(objects[7].toString());
				
				functionList.add(function);
			}
			
			request.setAttribute("functionList", functionList);
			modelAndView.setViewName("main/menu");
            
        }
				
		return modelAndView;
	}

	/**
	 * 获取权限的map
	 * 
	 * @param user
	 * @return
	 */
	private Map<Integer, List<TSFunction>> getFunctionMap(TSUser user) {
		Map<Integer, List<TSFunction>> functionMap = new HashMap<Integer, List<TSFunction>>();
		Map<String, TSFunction> loginActionlist = getUserFunction(user);
		if (loginActionlist.size() > 0) {
			Collection<TSFunction> allFunctions = loginActionlist.values();
			for (TSFunction function : allFunctions) {
				if (!functionMap.containsKey(function.getFunctionLevel() + 0)) {
					functionMap.put(function.getFunctionLevel() + 0,
							new ArrayList<TSFunction>());
				}
				functionMap.get(function.getFunctionLevel() + 0).add(function);
			}
			// 菜单栏排序
			Collection<List<TSFunction>> c = functionMap.values();
			for (List<TSFunction> list : c) {
				Collections.sort(list, new NumberComparator());
			}
		}
		return functionMap;
	}

	/**
	 * 获取用户菜单列表
	 * 
	 * @param user
	 * @return
	 */
	private Map<String, TSFunction> getUserFunction(TSUser user) {
		HttpSession session = ContextHolderUtils.getSession();
		Client client = ClientManager.getInstance().getClient(session.getId());
		if (client.getFunctions() == null || client.getFunctions().size() == 0) {
			Map<String, TSFunction> loginActionlist = new HashMap<String, TSFunction>();

				TSRole role = user.getTSRole();
				List<TSRoleFunction> roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
				
				for (TSRoleFunction roleFunction : roleFunctionList) {
					
					TSFunction function = roleFunction.getTSFunction();
					loginActionlist.put(function.getId(), function);
				}
			
			client.setFunctions(loginActionlist);
		}
		return client.getFunctions();
	}

	/**
	 * 首页跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "home")
	public ModelAndView home(HttpServletRequest request) {
		
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("user", user);
		
		//公众号
		WeixinAccountEntity weixinAccount = (WeixinAccountEntity)weixinAccountService.getEntity(WeixinAccountEntity.class, ResourceUtil.getWeiXinAccountId());
		request.setAttribute("weixinAccount", weixinAccount);
		
		//租户信息
		WeixinAccountEntity weixinTenant = weixinAccountService.get(WeixinAccountEntity.class, user.getTenantId());
		request.setAttribute("weixinTenant", weixinTenant);
		
		//系统公告、新功能推荐
		List<WeixinAnnouncementEntity> weixinAnnouncementList = weixinAnnouncementService.findByProperty(WeixinAnnouncementEntity.class, "status", "1");
		request.setAttribute("weixinAnnouncementList", weixinAnnouncementList);
		
		Date date = new Date();
		request.setAttribute("nowDate", date);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String current=sdf.format(date)+" 00:00:00";
		
		//查询粉丝总量
		String sql1 = "select count(id) from weixin_member t where t.account_id='"+ResourceUtil.getWeiXinAccountId()+"'";
		Long memberCount = systemService.getCountForJdbc(sql1);
		request.setAttribute("memberCount", memberCount);
		
		//查询新加粉丝数量
		String sql2 = "select count(id) from weixin_member t where t.account_id='"+ResourceUtil.getWeiXinAccountId()+"' and t.subscribe_time > '"+current+"'";
		Long newMemberCount = systemService.getCountForJdbc(sql2);
		request.setAttribute("newMemberCount", newMemberCount);
		
		//订单总量
		String sql3 = "select count(id) from weixin_order t where t.accountid='"+ResourceUtil.getWeiXinAccountId()+"' and t.status='1'";
		Long orderCount = systemService.getCountForJdbc(sql3);
		request.setAttribute("orderCount", orderCount);
		
		//新订单数量
		String sql4 = "select count(id) from weixin_order t where t.accountid='"+ResourceUtil.getWeiXinAccountId()+"' and t.create_date > '"+current+"' and t.status='1'";
		Long newOrderCount = systemService.getCountForJdbc(sql4);
		request.setAttribute("newOrderCount", newOrderCount);
		
		//销售总量
		String sql5 = "select sum(order_amount) as ssum from weixin_order t where t.accountid='"+ResourceUtil.getWeiXinAccountId()+"' and t.status='1'";
		String orderAmountSum = String.valueOf(systemService.findOneForJdbc(sql5).get("ssum"));
		request.setAttribute("orderAmountSum", orderAmountSum.equalsIgnoreCase("null")?"0.0":orderAmountSum);
		
		//新销售额
		String sql6 = "select sum(order_amount) as ssum from weixin_order t where t.accountid='"+ResourceUtil.getWeiXinAccountId()+"' and t.create_date > '"+current+"' and t.status='1'";
		String newOrderAmountSum = String.valueOf(systemService.findOneForJdbc(sql6).get("ssum"));
		request.setAttribute("newOrderAmountSum", newOrderAmountSum.equalsIgnoreCase("null")?"0.0":newOrderAmountSum);
		
		//消息总量
		String sql7 = "select count(msgid) from weixin_receivetext t where t.accountid='"+ResourceUtil.getWeiXinAccountId()+"'";
		Long messageCount = systemService.getCountForJdbc(sql7);
		request.setAttribute("messageCount", messageCount);
		
		//新消息数量
		String sql8 = "select count(msgid) from weixin_receivetext t where t.accountid='"+ResourceUtil.getWeiXinAccountId()+"' and t.createtime > '"+current+"'";
		Long newMessageCount = systemService.getCountForJdbc(sql8);
		request.setAttribute("newMessageCount", newMessageCount);
		
		//域名地址
		String localhosturl = ResourceUtil.getDomain()+"/";
		request.setAttribute("localhosturl", localhosturl);
		
		return new ModelAndView("main/home");
	}
	/**
	 * 无权限页面提示跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "noAuth")
	public ModelAndView noAuth(HttpServletRequest request) {
		return new ModelAndView("common/noAuth");
	}
	/**
	 * @Title: top
	 * @Description: bootstrap头部菜单请求
	 * @param request
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(params = "top")
	public ModelAndView top(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		HttpSession session = ContextHolderUtils.getSession();
		// 登陆者的权限
		if (user.getId() == null) {
			session.removeAttribute(Globals.USER_SESSION);
			return new ModelAndView(
					new RedirectView("loginController.do?login"));
		}
		request.setAttribute("menuMap", getFunctionMap(user));
		List<TSConfig> configs = userService.loadAll(TSConfig.class);
		for (TSConfig tsConfig : configs) {
			request.setAttribute(tsConfig.getCode(), tsConfig.getContents());
		}
		return new ModelAndView("main/bootstrap_top");
	}
	/**
	 * @Title: top
	 * @author gaofeng
	 * @Description: shortcut头部菜单请求
	 * @param request
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(params = "shortcut_top")
	public ModelAndView shortcut_top(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		HttpSession session = ContextHolderUtils.getSession();
		// 登陆者的权限
		if (user.getId() == null) {
			session.removeAttribute(Globals.USER_SESSION);
			return new ModelAndView(
					new RedirectView("loginController.do?login"));
		}
		request.setAttribute("menuMap", getFunctionMap(user));
		List<TSConfig> configs = userService.loadAll(TSConfig.class);
		for (TSConfig tsConfig : configs) {
			request.setAttribute(tsConfig.getCode(), tsConfig.getContents());
		}
		return new ModelAndView("main/shortcut_top");
	}
	
	/**
	 * @Title: top
	 * @author:gaofeng
	 * @Description: shortcut头部菜单一级菜单列表，并将其用ajax传到页面，实现动态控制一级菜单列表
	 * @return AjaxJson
	 * @throws
	 */
    @RequestMapping(params = "primaryMenu")
    @ResponseBody
	public String getPrimaryMenu() {
		List<TSFunction> primaryMenu = getFunctionMap(ResourceUtil.getSessionUserName()).get(0);
        String floor = "";
        for (TSFunction function : primaryMenu) {
            if(function.getFunctionLevel() == 0){

                if("Online 开发".equals(function.getFunctionName())){

                    floor += " <li><img class='imag1' src='plug-in/login/images/online.png' /> "
                            + " <img class='imag2' src='plug-in/login/images/online_up.png' style='display: none;' />" + " </li> ";
                }else if("统计查询".equals(function.getFunctionName())){

                    floor += " <li><img class='imag1' src='plug-in/login/images/guanli.png' /> "
                            + " <img class='imag2' src='plug-in/login/images/guanli_up.png' style='display: none;' />" + " </li> ";
                }else if("系统管理".equals(function.getFunctionName())){

                    floor += " <li><img class='imag1' src='plug-in/login/images/xtgl.png' /> "
                            + " <img class='imag2' src='plug-in/login/images/xtgl_up.png' style='display: none;' />" + " </li> ";
                }else if("常用示例".equals(function.getFunctionName())){

                    floor += " <li><img class='imag1' src='plug-in/login/images/cysl.png' /> "
                            + " <img class='imag2' src='plug-in/login/images/cysl_up.png' style='display: none;' />" + " </li> ";
                }else if("系统监控".equals(function.getFunctionName())){

                    floor += " <li><img class='imag1' src='plug-in/login/images/xtjk.png' /> "
                            + " <img class='imag2' src='plug-in/login/images/xtjk_up.png' style='display: none;' />" + " </li> ";
                }else{
                    //其他的为默认通用的图片模式
                    String s = "";
                    if(function.getFunctionName().length()>=5 && function.getFunctionName().length()<7){
                        s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>"+ function.getFunctionName() +"</span></div>";
                    }else if(function.getFunctionName().length()<5){
                        s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'>"+ function.getFunctionName() +"</div>";
                    }else if(function.getFunctionName().length()>=7){
                        s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>"+ function.getFunctionName().substring(0, 6) +"</span></div>";
                    }
                    floor += " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/default.png' /> "
                            + " <img class='imag2' src='plug-in/login/images/default_up.png' style='display: none;' />"
                            + s +"</li> ";
                }
            }
        }
		
		return floor;
	}
	

	/**
	 * 返回数据
	 */
	@RequestMapping(params = "getPrimaryMenuForWebos")
	@ResponseBody
	public AjaxJson getPrimaryMenuForWebos() {
		AjaxJson j = new AjaxJson();
		//将菜单加载到Session，用户只在登录的时候加载一次
		Object getPrimaryMenuForWebos =  ContextHolderUtils.getSession().getAttribute("getPrimaryMenuForWebos");
		if(oConvertUtils.isNotEmpty(getPrimaryMenuForWebos)){
			j.setMsg(getPrimaryMenuForWebos.toString());
		}else{
			String PMenu = ListtoMenu.getWebosMenu(getFunctionMap(ResourceUtil.getSessionUserName()));
			ContextHolderUtils.getSession().setAttribute("getPrimaryMenuForWebos", PMenu);
			j.setMsg(PMenu);
		}
		return j;
	}
	
	/**
	 * 左侧菜单列表tree
	 * @return
	 */
	@RequestMapping(params = "menuTree")
    @ResponseBody
    public JSONArray menuTree() {
		
		JSONArray jsonArray = new JSONArray();
		
		TSUser user = ResourceUtil.getSessionUserName();
		
		String sql = "select a.id,a.functionname,a.functionlevel,a.functionurl,a.functionorder,a.parentfunctionid,c.path,c.iconclas from t_s_function a right join t_s_role_function b on a.id=b.functionid left join t_s_icon c on a.iconid=c.id where b.roleid='"+user.getTSRole().getId()+"' order by a.functionlevel,a.functionorder asc";
		List list = systemService.findListbySql(sql);
		
		for(int i=0;i<list.size();i++){
			
			Object[] objects = (Object[])list.get(i);
			
			if("0".equals(objects[2].toString())){
				
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("id", objects[0].toString());
				jsonobj.put("text", objects[1].toString());
				jsonobj.put("state", "closed");
				//jsonobj.put("iconCls", "icon-standard-resultset-next");
				
				JSONArray childrenArray = new JSONArray();
				
				for(int j=0;j<list.size();j++){
					
					Object[] children = (Object[])list.get(j);
					
					if("1".equals(children[2].toString()) && objects[0].toString().equals(children[5].toString())){
						
						JSONObject attrobjChildren = new JSONObject();
						attrobjChildren.put("href", children[3].toString());
						attrobjChildren.put("refreshable", false);
						
						JSONObject jsonobjChildren = new JSONObject();
						jsonobjChildren.put("id", children[0].toString());
						jsonobjChildren.put("text", children[1].toString());
						//jsonobjChildren.put("iconCls", "icon-standard-bullet-blue");
						jsonobjChildren.put("attributes", attrobjChildren);
						
						childrenArray.add(jsonobjChildren);						
					}
				}
				if(childrenArray.size() > 0){
					
					jsonobj.put("children", childrenArray);				
					jsonArray.add(jsonobj);
				}
			}
		}
		return jsonArray;
	}
	
	/**
	 * 快捷菜单
	 * @return
	 */
	@RequestMapping(params = "favoMenu")
    @ResponseBody
    public JSONArray favoMenu() {
		
		JSONArray jsonArray = new JSONArray();
		
		TSUser user = ResourceUtil.getSessionUserName();
		
		//菜单--开始
		String sql = "select a.id,a.functionname,a.functionlevel,a.functionurl,a.functionorder,a.parentfunctionid from t_favo_menu b left join t_s_function a on b.menu_id=a.id where b.user_id='"+user.getId()+"' order by a.functionlevel,a.functionorder asc";
		List list = systemService.findListbySql(sql);
		
		if(list!=null && list.size()>0){
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("id", "0");
			jsonobj.put("text", "菜单列表");
			jsonobj.put("state", "closed");
			
			JSONArray childrenArray = new JSONArray();
			for(int j=0;j<list.size();j++){
				
				Object[] children = (Object[])list.get(j);				
				
				if("1".equals(children[2].toString())){
					
					JSONObject jsonobjChildren = new JSONObject();
					jsonobjChildren.put("id", children[0].toString());
					jsonobjChildren.put("text", children[1].toString());
					jsonobjChildren.put("iconCls", "icon-standard-bullet-orange");
					
					JSONObject attrobjChildren = new JSONObject();
					attrobjChildren.put("href", children[3].toString());
					attrobjChildren.put("refreshable", false);
					jsonobjChildren.put("attributes", attrobjChildren);
					
					childrenArray.add(jsonobjChildren);						
				}
			}
			jsonobj.put("children", childrenArray);
			jsonArray.add(jsonobj);
		}
		//菜单--结束
		
		//个人链接--开始
		List<TFavoLinkEntity> tFavoLinkList = systemService.findByProperty(TFavoLinkEntity.class, "userId", user.getId());
		if(tFavoLinkList != null && tFavoLinkList.size()>0){
			
			JSONObject linkonobj = new JSONObject();
			linkonobj.put("id", "1");
			linkonobj.put("text", "链接列表");
			linkonobj.put("state", "closed");
			
			JSONArray linkchildrenArray = new JSONArray();
			
			for(int i=0; i<tFavoLinkList.size(); i++){
				
				TFavoLinkEntity tFavoLink = tFavoLinkList.get(i);
				
				JSONObject linkobjChildren = new JSONObject();
				linkobjChildren.put("id", tFavoLink.getId());
				linkobjChildren.put("text", tFavoLink.getName());
				linkobjChildren.put("iconCls", "icon-standard-bullet-blue");
				
				JSONObject attrobjChildren = new JSONObject();
				attrobjChildren.put("href", tFavoLink.getLink());
				attrobjChildren.put("refreshable", false);
				linkobjChildren.put("attributes", attrobjChildren);
				
				linkchildrenArray.add(linkobjChildren);				
			}
			linkonobj.put("children", linkchildrenArray);
			jsonArray.add(linkonobj);					
		}
		//个人链接--结束	
		
		return jsonArray;
	}
	
	/**
	 * 退出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "loginout")
	@ResponseBody
	public AjaxJson loginout(HttpServletRequest request) {
		
		AjaxJson j = new AjaxJson();
		
		try{
			
			HttpSession session = ContextHolderUtils.getSession();
			TSUser user = ResourceUtil.getSessionUserName();
			//systemService.addLog("用户" + user.getUserName() + "已退出", Globals.Log_Type_EXIT, Globals.Log_Leavel_INFO);
			
			//移除session中的用户和公众号
			ClientManager.getInstance().removeClinet(session.getId());
			session.removeAttribute(WeiXinConstants.WEIXIN_ACCOUNT);
			session.removeAttribute(WeiXinConstants.WEIXIN_ACCT);
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("url",ResourceUtil.getConfigByName("domain1"));
		j.setAttributes(params);
		return j;
	}
	
	/**
	 * 返回logo数据
	 */
	@RequestMapping(params = "getLogo")
	@ResponseBody
	public AjaxJson getLogo(HttpServletRequest request,HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		String id = request.getParameter("data");
		if(id !=null){
			WeixinIndividualizationEntity weixinIndividualizationEntity = new WeixinIndividualizationEntity();    
		    	weixinIndividualizationEntity = weixinAccountService.getIndividualizationLogo(id);
		    	if(weixinIndividualizationEntity == null){
		    		j.setMsg("石榴商盟");
		    	}else{
		    	try {
		    		String name = weixinIndividualizationEntity.getName();
		    		j.setMsg(name);					
				} catch (Exception e) {
					e.printStackTrace();
				}		
		}
		}
		return j;
	}
}

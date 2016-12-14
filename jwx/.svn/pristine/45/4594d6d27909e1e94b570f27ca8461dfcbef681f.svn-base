package weixin.register;
import weixin.cms.entity.WeixinCmsSiteEntity;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.base.entity.Subscribe;
import weixin.guanjia.menu.entity.MenuEntity;
import weixin.guanjia.message.entity.TextTemplate;
import weixin.payment.entity.WeixinPaymentConEntity;
import weixin.shop.entity.WeixinShopEntity;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.service.WeixinAcctServiceI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.io.File;
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
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;




/**   
 * @Title: Controller
 * @Description: 新用户注册
 * @author onlineGenerator
 * @date 2015-03-05 12:59:51
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/registerController")
public class RegisterController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RegisterController.class);

	@Autowired
	private WeixinAcctServiceI weixinAcctService;
	
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	
	private SystemService systemService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 跳转到注册页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "goRegister")
	public ModelAndView goRegister(HttpServletRequest request) {
		return new ModelAndView("register/register");
	}

	/**
	 * 用户自助注册
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doRegister")
	public ModelAndView doRegister(HttpServletRequest request) {

		String msg = "";
		try{
			
			//获取页面输入项
			String loginName = request.getParameter("loginName");
			String password = oConvertUtils.getString(request.getParameter("ligonPass"));
			
			String mobilePhone = request.getParameter("mobilePhone");
			String email = request.getParameter("email");
			String qqNumber = request.getParameter("qqNumber");
			String accountNumber = request.getParameter("accountNumber");
			
			//校验用户名

			TSUser userCheck = systemService.findUniqueByProperty(TSUser.class, "userName", loginName);
			if (userCheck == null) {
				
				//租户开户
				WeixinAcctEntity weixinTenant =  new WeixinAcctEntity();
				weixinTenant.setAcctName(loginName);
				weixinTenant.setMobilePhone(mobilePhone);
				weixinTenant.setEmail(email);
				weixinTenant.setQqNumber(qqNumber);
				weixinTenant.setRequestnum(1000);
				weixinTenant.setSmsnum(5);
				weixinTenant.setUsernum(5);
				weixinTenant.setAccountnum(3);
				weixinTenant.setNewsnum(5);
				weixinTenant.setCreateDate(new Date());
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.MONTH, 3);
				weixinTenant.setEndDate(calendar.getTime());
				
				weixinAcctService.save(weixinTenant);
				
				//获取租户信息
				WeixinAcctEntity tenantEntity = weixinAcctService.findUniqueByProperty(WeixinAcctEntity.class, "acctName", loginName);
				
				//创建用户
				TSUser user = new TSUser();
				user.setUserName(loginName);
				user.setPassword(PasswordUtil.encrypt(user.getUserName(), password, PasswordUtil.getStaticSalt()));
				user.setEmail(email);
				user.setMobilePhone(mobilePhone);
				user.setStatus(Globals.User_Normal);
				user.setTSDepart(null);
				user.setTenantId(tenantEntity.getId());
				user.setType("1");//用户类型 1：商家管理员账号
				
				//设置体验版角色,这是查询已经有的角色，从而进行授权和赋值
				TSRole tSRole = systemService.findUniqueByProperty(TSRole.class, "roleCode", "01");
				user.setTSRole(tSRole);
				
				//校验用户名
				TSUser users = systemService.findUniqueByProperty(TSUser.class, "userName",user.getUserName());
				if (users == null) {
				
					//创建公众号
					WeixinAccountEntity weixinAccountEntity = new WeixinAccountEntity();
					weixinAccountEntity.setAccountname(accountNumber);
					weixinAccountEntity.setAccountnumber(accountNumber);
					weixinAccountEntity.setAccounttoken("w_" + getRandomString(8));
					weixinAccountEntity.setUserName(loginName);
					weixinAccountService.save(weixinAccountEntity);
					
					WeixinAccountEntity weixinAccount = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "userName", loginName);
					
					//保存用户
					user.setAccountid(weixinAccount.getId());//公众号ID
					systemService.save(user);
					
					//创建基础数据
					this.createBaseSourceForAccount(weixinAccount.getId());
					//创建用户文件目录
					this.createUserFolder(request, loginName);
					
					//跳转到注册成功页面
					return new ModelAndView("register/register-ok");
				}
			}else{
				msg = "用户名已经存在！";
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ModelAndView("register/register-no");
		}
		
		request.setAttribute("msg", msg);
		//注册失败页面
		return new ModelAndView("register/register-no");
	}
	
	/**
	 * 创建用户文件目录
	 * @param request
	 * @param loginName
	 */
	public void createUserFolder(HttpServletRequest request, String loginName){
		
		//建立用户目录
		String requestPath = request.getSession().getServletContext().getRealPath("/upload/user");
		String realpath = requestPath + "/"+ loginName + "/";
		File tempfolder = new File(realpath);
		if (!tempfolder.exists()) {
			// 创建模板文件夹路径
			tempfolder.mkdirs();
		}
	}
	
	/**
	 * 创建基础数据
	 * @param accountid
	 */
	public void createBaseSourceForAccount(String accountId){
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//文本消息
		TextTemplate textTemplate = new TextTemplate();
		textTemplate.setAccountId(accountId);
		textTemplate.setTemplateName("欢迎关注语");
		textTemplate.setContent("欢迎您的关注！");
		textTemplate.setAddTime(sdf.format(new Date()));
		systemService.save(textTemplate);
		
		textTemplate = systemService.findUniqueByProperty(TextTemplate.class, "accountId", accountId);
		
		//欢迎关注语
		Subscribe subscribe = new Subscribe();
		subscribe.setAccountid(accountId);
		subscribe.setTemplateId(textTemplate.getId());
		subscribe.setTemplateName("欢迎关注语");
		subscribe.setMsgType("text");
		subscribe.setAddTime(sdf.format(new Date()));
		systemService.save(subscribe);
		
		//微网站数据
		WeixinCmsSiteEntity weixinCmsSiteEntity = new WeixinCmsSiteEntity();
		weixinCmsSiteEntity.setAccountid(accountId);
		weixinCmsSiteEntity.setSiteName("微网站");
		weixinCmsSiteEntity.setLinkUrl(ResourceUtil.getDomain() + "/cmsController.do?goPage&page=index&accountid="+ accountId);
		systemService.save(weixinCmsSiteEntity);
		
		//微商城数据
		WeixinShopEntity weixinShopEntity = new WeixinShopEntity();
		weixinShopEntity.setAccountid(accountId);
		weixinShopEntity.setShopName("微商城");
		weixinShopEntity.setLinkUrl(ResourceUtil.getDomain() + "/shopController.do?shopindex&accountid="+ accountId);
		systemService.save(weixinShopEntity); 
		
		//自定义菜单
		List<MenuEntity> menuList = new ArrayList<MenuEntity>();
		
		//自定义菜单:微网站
		MenuEntity menuCms = new MenuEntity();
		menuCms.setName("官网");
		menuCms.setAccountId(accountId);
		menuCms.setMenuKey("1");
		menuCms.setType("view");
		menuCms.setOrders("1");
		menuCms.setMsgType("");
		menuCms.setUrl(ResourceUtil.getDomain() + "/cmsController.do?goPage&page=index&accountid="+ accountId);
		menuList.add(menuCms);
		
		//自定义菜单:微商城
		MenuEntity menuShop = new MenuEntity();
		menuShop.setName("商城");
		menuShop.setAccountId(accountId);
		menuShop.setMenuKey("2");
		menuShop.setType("view");
		menuShop.setOrders("2");
		menuShop.setMsgType("");
		menuShop.setUrl(ResourceUtil.getDomain() + "/shopController.do?shopindex&accountid="+ accountId);
		menuList.add(menuCms);
		
		systemService.batchSave(menuList);
		
		//支付
		WeixinPaymentConEntity weixinPaymentConEntity = new WeixinPaymentConEntity();
		weixinPaymentConEntity.setPayType("1");
		weixinPaymentConEntity.setAccountid(accountId);
		weixinPaymentConEntity.setPayName("微信支付");
		
		systemService.save(weixinPaymentConEntity);
	}
	
	/**
	 * 检测用户名是否已经存在
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "checkRegisterName")
	@ResponseBody
	public AjaxJson checkRegisterName(HttpServletRequest request) {
		
		AjaxJson j = new AjaxJson();
		
		//获取页面输入项
		String loginName = request.getParameter("loginName");
		
		TSUser users = systemService.findUniqueByProperty(TSUser.class, "userName",loginName);
		if (users != null) {
			
			j.setMsg("true");
			j.setSuccess(false);
		}
		return j;
	}
	
	/**
	 * 生成随机数
	 * @param length 表示生成字符串的长度
	 * @return
	 */
	public static String getRandomString(int length) {
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  
}

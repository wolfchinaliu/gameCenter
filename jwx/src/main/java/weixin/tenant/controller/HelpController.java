package weixin.tenant.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.tenant.entity.WeixinAnnouncementEntity;
import weixin.util.WeiXinConstants;

/**
 * 帮助中心
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/helpController")
public class HelpController extends BaseController {

	private String message;

	private SystemService systemService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	
	/**
	 * 跳转到帮助中心
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		
		return new ModelAndView("weixin/tenant/help");
	}
	
	/**
	 * 跳转到在线咨询
	 * 
	 * @return
	 */
	@RequestMapping(params = "contact")
	public ModelAndView contact(HttpServletRequest request) {
		
		return new ModelAndView("weixin/tenant/contact");
	}
	
	/**
	 * 跳转到个性化设置
	 * 
	 * @return
	 */
	@RequestMapping(params = "styleset")
	public ModelAndView styleset(HttpServletRequest request) {
		
		return new ModelAndView("weixin/tenant/styleset");
	}
	
	/**
	 * 保存个性化设置
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doSaveStyleSet")
	@ResponseBody
	public AjaxJson doSaveStyleSet(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "个性化设置成功,请重新登录生效";
		try{
			
			String showPoint = request.getParameter("showPoint");
			TSUser user = ResourceUtil.getSessionUserName();
			
			TSUser tuser = systemService.getEntity(TSUser.class,user.getId());
			tuser.setIsShowPoint(showPoint);
			
			systemService.updateEntitie(tuser);
			request.getSession().setAttribute("isShowpoint", showPoint);
			
		}catch(Exception e){
			e.printStackTrace();
			message = "个性化设置失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 跳转到切换公众号
	 * 
	 * @return
	 */
	@RequestMapping(params = "changeaccount")
	public ModelAndView changeaccount(HttpServletRequest request) {
		
		//查询当前商户所有公众号
		WeixinAccountEntity weixinAccountEntity = systemService.getEntity(WeixinAccountEntity.class, ResourceUtil.getSessionUserName().getAccountid());
		request.setAttribute("weixinAccountEntity", weixinAccountEntity);
		
		List<WeixinAccountEntity> weixinAccountList = systemService.findByProperty(WeixinAccountEntity.class, "pid", ResourceUtil.getSessionUserName().getAccountid());
		request.setAttribute("weixinAccountList", weixinAccountList);
		
		return new ModelAndView("weixin/tenant/changeaccount");
	}
	
	/**
	 * 切换公众号
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "dochangeaccount")
	@ResponseBody
	public AjaxJson dochangeaccount(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "公众号切换成功";
		try{
			
			String accountid = request.getParameter("accountid");
			WeixinAccountEntity  weixinAccountEntity = systemService.getEntity(WeixinAccountEntity.class, accountid);
			
			//重设公众号session
			request.getSession().setAttribute(WeiXinConstants.WEIXIN_ACCOUNT, weixinAccountEntity);
		}catch(Exception e){
			e.printStackTrace();
			message = "公众号切换失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
}

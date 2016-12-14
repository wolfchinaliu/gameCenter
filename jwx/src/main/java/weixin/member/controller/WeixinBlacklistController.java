package weixin.member.controller;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.member.entity.WeixinBlackListEntity;

/**
 * @Auth popl
 * @Date 2016年8月4日 下午5:48:31
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.member.controller.WeixinBlacklistController
 * @dec 黑名单控制
 */
@Controller
@RequestMapping("/weixinBlacklistController")
public class WeixinBlacklistController extends BaseController {

	@Autowired
	private SystemService systemService;

	private static final Logger logger = Logger.getLogger(WeixinBlacklistController.class);

	@RequestMapping(params = "balcklist")
	public ModelAndView weixinBlacklist(HttpServletRequest request) {
		return new ModelAndView("weixin/member/weixinBlacklistList");
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(WeixinBlackListEntity blacklistEntity, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinBlackListEntity.class, dataGrid);
		// 商户ID
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, blacklistEntity,
				request.getParameterMap());
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	@RequestMapping(params = "doSave")
	@ResponseBody
	public AjaxJson doSave(WeixinBlackListEntity blacklistEntity, HttpServletRequest request) {
		AjaxJson ajaxJson = new AjaxJson();
		if (StringUtil.isEmpty(blacklistEntity.getId())) {
			ajaxJson.setMsg("手机号不能为空");
			return ajaxJson;
		}
		String message = "保存 " + blacklistEntity.getId() + "黑名单成功";
		try {
			Pattern p = Pattern
					.compile("^(1\\d{10}$)");
			Matcher m = p.matcher(blacklistEntity.getId());
			if (!m.matches()) {
				ajaxJson.setMsg("手机号格式不正确");
				return ajaxJson;
			}

			WeixinBlackListEntity blackListEntity2 = systemService.getEntity(WeixinBlackListEntity.class,
					blacklistEntity.getId());
			if (blackListEntity2 == null) {
				blacklistEntity.setAddTime(new Date());
				blacklistEntity.setEnabledTime(new Date());
				blacklistEntity.setState(1);
				systemService.save(blacklistEntity);
			} else {
				if (blacklistEntity.getState() == null) {
					ajaxJson.setMsg("参数不正确");
					return ajaxJson;
				}
				blackListEntity2.setState(blacklistEntity.getState());
				if (1 == blacklistEntity.getState()) {
					blackListEntity2.setEnabledDec(blacklistEntity.getEnabledDec());
					blackListEntity2.setEnabledTime(new Date());
				} else {
					blackListEntity2.setDisableDec(blacklistEntity.getDisableDec());
					blackListEntity2.setDisableTime(new Date());
				}
				systemService.updateEntitie(blackListEntity2); 
			}
		} catch (Exception e) {
			logger.error("黑名单保存 异常", e);
			message = "微信活动添加失败";
			throw new BusinessException(e.getMessage());
		}
		ajaxJson.setMsg(message);
		return ajaxJson;
	}
	@RequestMapping(params = "goEdit")
	public String goEdit(@RequestParam(value="phoneNumber" ,required=false) String phoneNumber ,HttpServletRequest request){
		if(StringUtil.isNotEmpty(phoneNumber)){
			WeixinBlackListEntity blacklistEntity = systemService.getEntity(WeixinBlackListEntity.class,phoneNumber);
			request.setAttribute("phoneNumber", phoneNumber);
			request.setAttribute("state", blacklistEntity.getState());
		}else{
			request.setAttribute("state", "2");
		}
		return "weixin/member/weixinBlacklistEdit";
		
	}
}

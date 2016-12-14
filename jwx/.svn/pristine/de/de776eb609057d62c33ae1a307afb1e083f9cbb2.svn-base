package weixin.gameCenter.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.gameCenter.entity.WeixinGameSafeRuleEntity;
import weixin.gameCenter.entity.WeixinOtherGameEntity;
import weixin.gameCenter.service.IWeixinGameService;

/**
 * @Auth popl
 * @Date 2016年9月28日 上午11:12:55
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.gameCenter.controller.WeixinGameSafeRuleController
 * @dec 
 */
@Controller
@RequestMapping("/weixinGameSafeRuleController")
public class WeixinGameSafeRuleController {
	private static final Logger logger = Logger.getLogger(WeixinGameSafeRuleController.class);

	@Autowired
	private SystemService systemService;
	@Autowired
	private IWeixinGameService weixinGameTypeService;

	@RequestMapping(params = "goList")
	public ModelAndView weixinLottery(HttpServletRequest request) {
		return new ModelAndView("weixin/gameCenter/weixinGameSafeRuleList");
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(WeixinGameSafeRuleEntity gameSafeRuleEntity, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinGameSafeRuleEntity.class, dataGrid);
		// 商户ID
		gameSafeRuleEntity.setAccountId(ResourceUtil.getWeiXinAccountId());
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, gameSafeRuleEntity, request.getParameterMap());
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinGameSafeRuleEntity gameSafeRuleEntity, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "删除成功";
		try {
			weixinGameTypeService.delete(gameSafeRuleEntity);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			logger.error("删除安全规则异常 ", e);
			message = "删除安全规则失败";
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(params = "doSave")
	@ResponseBody
	public AjaxJson doSave(WeixinGameSafeRuleEntity gameSafeRuleEntity, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "游戏安全规则添加成功";
		try {
			gameSafeRuleEntity.setAccountId(ResourceUtil.getWeiXinAccountId());
			weixinGameTypeService.save(gameSafeRuleEntity);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			logger.error("游戏安全规则保存异常", e);
			message = "游戏安全规则保存失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinGameSafeRuleEntity gameSafeRuleEntity, HttpServletRequest req) {
		int code = 1;
		if (gameSafeRuleEntity != null && StringUtil.isNotEmpty(gameSafeRuleEntity.getId())) {
			WeixinGameSafeRuleEntity safeRule = weixinGameTypeService.get(WeixinGameSafeRuleEntity.class,
					gameSafeRuleEntity.getId());
			if (safeRule != null )
				gameSafeRuleEntity = safeRule;
			code = 0;
		}
		req.setAttribute("safeRule", gameSafeRuleEntity);
		req.setAttribute("code", code);
		return new ModelAndView("weixin/gameCenter/weixinGameSafeRule-add");
	}


}

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
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.gameCenter.entity.WeixinGameTypeEntity;
import weixin.gameCenter.entity.WeixinOtherGameEntity;
import weixin.gameCenter.service.IWeixinGameService;
import weixin.util.CommonUtils;

/**
 * @Auth popl
 * @Date 2016年9月7日 上午10:54:08
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.gameCenter.controller.WeixinGameTypeController
 * @dec 游戏的后台操作
 */
@Controller
@RequestMapping("/weixinOtherGameController")
public class WeixinOtherGameController {

	private static final Logger logger = Logger.getLogger(WeixinOtherGameController.class);

	@Autowired
	private SystemService systemService;
	@Autowired
	private IWeixinGameService weixinGameTypeService;

	@RequestMapping(params = "otherGameList")
	public ModelAndView otherGameList(HttpServletRequest request) {
		request.setAttribute("gameType", request.getParameter("gameType"));
		return new ModelAndView("weixin/gameCenter/weixinOtherGameList");
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(WeixinOtherGameEntity gameEntity, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinOtherGameEntity.class, dataGrid);
		// 商户ID
		gameEntity.setAccountId(ResourceUtil.getWeiXinAccountId());
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, gameEntity, request.getParameterMap());
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "otherGameAddress")
	public ModelAndView weixinLotteryAddress(HttpServletRequest request) {
		request.setAttribute("gameId", request.getParameter("gameId"));
		return new ModelAndView("weixin/gameCenter/weixinOtherGameAddress");
	}

	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinOtherGameEntity gameEntity, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "删除成功";
		try {
			weixinGameTypeService.delete(gameEntity);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			logger.error("删除游戏类型异常 ", e);
			message = "删除游戏类型失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(params = "doSave")
	@ResponseBody
	public AjaxJson doSave(WeixinOtherGameEntity gameEntity, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "游戏保存成功";
		String start = request.getParameter("startTime");
		String endtime = request.getParameter("endTime");
		if (gameEntity == null || start.equals("")) {
			j.setSuccess(false);
			j.setMsg("请填写开始时间");
			return j;
		}

		if (endtime == null || endtime.equals("")) {
			j.setSuccess(false);
			j.setMsg("请填写结束时间");
			return j;
		}
		Date date = new Date();
		if (gameEntity.getStartTime().getTime() >= gameEntity.getEndTime().getTime()) {
			j.setSuccess(false);
			j.setMsg("结束时间必须大于开始时间");
			return j;
		}
		if (date.getTime() > gameEntity.getEndTime().getTime()) {
			j.setMsg("结束时间不能小于当前的时间");
			j.setSuccess(false);
			return j;
		}
		try {
			if (gameEntity == null || StringUtil.isEmpty(gameEntity.getId())) {
				gameEntity.setAddTime(new Date());
				gameEntity.setAccountId(ResourceUtil.getWeiXinAccountId());
				weixinGameTypeService.save(gameEntity);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			} else {
				WeixinOtherGameEntity entity = weixinGameTypeService.getEntity(WeixinOtherGameEntity.class,
						gameEntity.getId());
				MyBeanUtils.copyBeanNotNull2Bean(gameEntity, entity);
				systemService.updateEntitie(entity);
				systemService.addLog(message, Globals.Log_Type_UPLOAD, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			logger.error("游戏保存异常", e);
			message = "游戏保存失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(params = "goEdit")
	public ModelAndView goAdd(WeixinOtherGameEntity gameEntity, HttpServletRequest req) {
		int code = 1;
		if (gameEntity != null && StringUtil.isNotEmpty(gameEntity.getId())) {
			WeixinOtherGameEntity entity = weixinGameTypeService.get(WeixinOtherGameEntity.class, gameEntity.getId());
			if (entity != null)
				gameEntity = entity;
			Date now = new Date();
			if (entity.getStartTime().getTime() - now.getTime() < 600000)
				code = 0;
		}
		req.setAttribute("game", gameEntity);
		req.setAttribute("code", code);
		return new ModelAndView("weixin/gameCenter/weixinOtherGame-edit");
	}
	@RequestMapping(params = "goRuleEdit")
	public ModelAndView goRuleEdit(WeixinOtherGameEntity gameEntity, HttpServletRequest req){
		if(gameEntity == null || StringUtil.isEmpty(gameEntity.getId()))
			return new ModelAndView("common/404");
		WeixinOtherGameEntity entity = weixinGameTypeService.get(WeixinOtherGameEntity.class, gameEntity.getId());
		if(entity == null) return new ModelAndView("common/404");;
		WeixinGameTypeEntity gameTypeEntity = weixinGameTypeService.get(WeixinGameTypeEntity.class, entity.getGameType());
		ModelAndView andView = new ModelAndView("redirect:"+ gameTypeEntity.getEditUrl());
		andView.setViewName("redirect:"+ gameTypeEntity.getEditUrl());
		System.out.println("--名称--"+andView.getViewName() + " ---地址- " + gameTypeEntity.getEditUrl());
		andView.addObject("gameId", entity.getId());
		andView.addObject("startTime", entity.getStartTime());
		andView.addObject("endTime", entity.getEndTime());
		andView.addObject("title", CommonUtils.getStringUnicode(entity.getTitle()));
		return andView;
	}
}

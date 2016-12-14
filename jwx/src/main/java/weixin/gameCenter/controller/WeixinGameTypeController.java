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

import weixin.activity.entity.WeixinActivityEntity;
import weixin.gameCenter.entity.WeixinGameTypeEntity;
import weixin.gameCenter.service.IWeixinGameService;

/**
 * @Auth popl
 * @Date 2016年9月7日 上午10:54:08
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.gameCenter.controller.WeixinGameTypeController
 * @dec 游戏类类型的后台操作
 */
@Controller
@RequestMapping("/weixinGameTypeController")
public class WeixinGameTypeController {

	private static final Logger logger = Logger.getLogger(WeixinGameTypeController.class);

	@Autowired
	private SystemService systemService;
	@Autowired
	private IWeixinGameService weixinGameTypeService;

	@RequestMapping(params = "goList")
	public ModelAndView weixinGolist(HttpServletRequest request) {
		return new ModelAndView("weixin/gameCenter/weixinGameTypeList");
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(WeixinGameTypeEntity gameTypeEntity, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		weixinGameTypeService.getDataGrid(gameTypeEntity, dataGrid);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinGameTypeEntity gameTypeEntity, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "删除成功";
		try {
			weixinGameTypeService.deleteGameType(gameTypeEntity.getId());
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
	public AjaxJson doSave(WeixinGameTypeEntity gameTypeEntity, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "游戏类型添加成功";
		try {
			gameTypeEntity.setAddTime(new Date());
			gameTypeEntity.setStatus((short) 2);
			gameTypeEntity.setAccountId(ResourceUtil.getWeiXinAccountId());
			weixinGameTypeService.save(gameTypeEntity);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			logger.error("游戏类型保存异常", e);
			message = "游戏类型保存失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinGameTypeEntity gameTypeEntity, HttpServletRequest req) {
		int code = 1;
		if (gameTypeEntity != null && StringUtil.isNotEmpty(gameTypeEntity.getId())) {
			WeixinGameTypeEntity typeEntity = weixinGameTypeService.get(WeixinGameTypeEntity.class,
					gameTypeEntity.getId());
			if (typeEntity != null)
				gameTypeEntity = typeEntity;
			code = 0;
		}
		req.setAttribute("gameType", gameTypeEntity);
		req.setAttribute("code", code);
		return new ModelAndView("weixin/gameCenter/weixinGameType-add");
	}

	@RequestMapping(params = "updateStatus")
	@ResponseBody
	public AjaxJson updateStatus(WeixinGameTypeEntity gameTypeEntity, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		if (gameTypeEntity == null || StringUtil.isEmpty(gameTypeEntity.getId())) {
			j.setMsg("提交参数有误");
			return j;
		}
		j.setMsg(weixinGameTypeService.updateStatus(gameTypeEntity.getId()));
		return j;
	}
	@RequestMapping(params = "isCanCat")
	@ResponseBody
	public AjaxJson isCanCat(WeixinGameTypeEntity gameTypeEntity, HttpServletRequest req){
		AjaxJson j = new AjaxJson();
			j.setSuccess(weixinGameTypeService.canCat(gameTypeEntity.getId()));
		return j;
	}
}

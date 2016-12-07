package com.shiliu.game.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.shiliu.game.common.FlowOperator;
import com.shiliu.game.domain.ExcelUser;
import com.shiliu.game.domain.GameExcel;
import com.shiliu.game.domain.bean.FlowResult;
import com.shiliu.game.domain.bean.PlayGameBean;
import com.shiliu.game.service.IExcelUserService;
import com.shiliu.game.service.IGameExcelService;
import com.shiliu.game.utils.PropertyUtil;

/**
 * 用户流量充值控制器
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("clientFolw")
public class ClientFolwController {
	Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private IExcelUserService excelUserService;
	@Resource 
	private IGameExcelService gameExcelService;
	@RequestMapping(value="/folwview")
	public String folw(@ModelAttribute PlayGameBean playGameBean,ModelMap modelMap ,HttpSession session){
		
		session.setAttribute("openid", playGameBean.getOpenId());
		session.setAttribute("gameid", playGameBean.getGameId());
		GameExcel gameExcel = gameExcelService.selectByPrimaryKey(playGameBean.getGameId());
		if (gameExcel != null) {
			session.setAttribute("flowValue", gameExcel.getFlux());
			return "/client/FlowOperatorUser";
		}
		return "error1";
	}

	@RequestMapping(value = "/flowOperator")
	@ResponseBody
	public String flowOperator(String lab1, String lab2, HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		String url = PropertyUtil.getProperty("hend_flow_url").substring(7,
				PropertyUtil.getProperty("hend_flow_url").indexOf("/jwx"));
		map.put("url", url);
		if (lab1 == null && lab2 == null) {
			map.put("code", "107");
			map.put("message", "姓名或手机号后四位的值缺失或不合法");
			return JSON.toJSONString(map);
		}
		String gameid = (String) session.getAttribute("gameid");
		ExcelUser user = new ExcelUser();
		user.setLab1(lab1);
		user.setLab2(lab2);
		user.setGameId(gameid);
		ExcelUser selectUser = excelUserService.selectUser(user);
		if (selectUser == null) {
			map.put("code", "106");
			map.put("message", "还未查询到您的注册信息，请稍后在查询");
			return JSON.toJSONString(map);
		}
		if("1".equals(selectUser.getState())){
			map.put("code", "106");
			map.put("message", "您已经领取不能重复领取");
			return JSON.toJSONString(map);
		}
		
		String openid = (String) session.getAttribute("openid");
		String flowValue = (String) session.getAttribute("flowValue");
		if (gameid != null && openid != null && flowValue != null) {
			FlowResult flow = FlowOperator.hlenFlowGZ(gameid, flowValue, openid);
			logger.info(flow.getMsg() + flow.getCode());
			if (flow.getCode() == 0 || flow.getCode() == 1) {
				//selectUser.setGameId(gameid);
				selectUser.setState("1");
				DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				String format = sdf.format(new Date());
				selectUser.setRechargeTime(format);
				excelUserService.updateByPrimaryKeySelective(selectUser);
				map.put("code", "0");
				map.put("message", "流量充值成功");
				map.put("flowValue", flowValue);
			} else {
				map.put("code", Integer.toString(flow.getCode()));
				map.put("message", flow.getMsg());
			}
		} else {
			map.put("code", "105");
			map.put("message", "充值参数报错");
		}

		return JSON.toJSONString(map);
	}
}

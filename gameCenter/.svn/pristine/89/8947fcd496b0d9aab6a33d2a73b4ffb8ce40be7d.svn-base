package com.shiliu.game.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.shiliu.game.domain.UserGame;
import com.shiliu.game.domain.Vote;
import com.shiliu.game.domain.VoteOption;
import com.shiliu.game.domain.bean.PlayGameBean;
import com.shiliu.game.service.IVoteService;
import com.shiliu.game.utils.StringUtils;

/** 
* @author popl 
* @version 1.0
* @createDate 2016年7月11日 下午5:36:09 
* @description 投票玩法
*/
@Controller
@RequestMapping("vote")
public class VotePlayController {
	Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private IVoteService voteService;
	@RequestMapping(value="play/{type}")//,method=RequestMethod.POST)
	public String play(@PathVariable String type,@ModelAttribute PlayGameBean playGameBean,ModelMap modelMap ,HttpSession session){
		session.setAttribute("openid", playGameBean.getOpenId());
		String gameId = playGameBean.getGameId();
		System.out.println();
		Vote vote = voteService.getVoteForId(gameId);
		List<VoteOption> options = voteService.getVoteOption(gameId);
		//TODO 判断是否玩过
		UserGame userGame = voteService.getUserGame(gameId, playGameBean.getOpenId(), vote.getFrequency(),vote.getTimes());
		modelMap.put("bean", playGameBean);
		modelMap.put("vote", vote);
		modelMap.put("options", options);
		modelMap.put("userGame", userGame);
		return type+"Play";
	}
	@RequestMapping(value="submitSelect")
	@ResponseBody
	public String submit(@RequestParam(value="voteId") String voteId,@RequestParam(value="userGameId") Long userGameId,@RequestParam(value="options") String options,HttpSession session){
		Map<String,String> map = new HashMap<String,String>();
		try{
			
			String openid = (String)session.getAttribute("openid");
			if(StringUtils.isEmpty(openid)) {
				map.put("code","0");
				map.put("message", "请求失败 请刷新再试");
			}
			map = voteService.subSelectVote(userGameId,voteId,openid, options);
		}catch (Exception e) {
			logger.error("提交投票",e);
			map.put("code","0");
			map.put("message", "请求出现异常 请稍后刷新再试");
		}
		return JSON.toJSONString(map);
	}
}

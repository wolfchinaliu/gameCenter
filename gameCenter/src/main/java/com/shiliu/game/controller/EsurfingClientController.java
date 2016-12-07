package com.shiliu.game.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shiliu.game.domain.Game;
import com.shiliu.game.domain.PlayRecord;
import com.shiliu.game.domain.UserJHDX;
import com.shiliu.game.domain.UserPhone;
import com.shiliu.game.service.IGameService;
import com.shiliu.game.service.IPlayRecordService;
import com.shiliu.game.service.IUserJHDXService;
import com.shiliu.game.service.IUserPhoneService;

/**
 * @author wkr
 * @Date 2016-12-4 15:02
 * 金华电信，抽奖与送奖，前端
 */
@Controller
@RequestMapping(value="/esurfingClient")
public class EsurfingClientController {
	
	@Resource
	private IGameService gameService;
	
	@Resource
	private IUserPhoneService userPhoneService;
	
	@Resource
	private IUserJHDXService userJHDXService;
	
	@Resource
	private IPlayRecordService playRecordService;


	@RequestMapping(value="/clientInport/{type}")
	public String clientInportMethod(
									@RequestParam(value="gameId")String gameId,
									@RequestParam(value="openId")String openId,
									@RequestParam(value="nickName")String nickName,
									@PathVariable(value="type")Integer gameType,
									HttpServletRequest request,
									HttpSession session
	){
		
		//成员变量
		String phoneNumber = null;
		UserJHDX latestInfo = null;
		Integer total = 0;//总次数
		int playNumber = 0;//已经玩过的次数
		
		//天翼送话费 状态键值对
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("您已获得10元电信话费、校园宽带提速资格、iPhon7抽奖机会，2017年1月7日我们相约映客直播！", 1);//送礼物
		map.put("天翼视讯使用12G流量就可以获得10元话费、校园宽带提速资格及iPhon7抽奖机会哦！", 2);//不送
		
		//效验GameID
		if (gameService.checkUpGameID(new Game(gameId, gameType)) == 0)return null ;
		
		//判断是否绑定
		UserPhone userPhone = userPhoneService.selectByPrimaryKey(openId);
		
		if(userPhone!=null){
			phoneNumber = userPhone.getPhoneNumber();
			//通过手机号、Gameid	到白名单中查询最新的信息
			latestInfo = userJHDXService.queryTheLatestUserInfo(new UserJHDX(phoneNumber, gameId));
			
			// 抽奖页面、送奖页面
			if (gameType.equals(1)) {
				
				total = latestInfo.getTotal();  //总次数
				//判断用户是否可以玩大转盘
				if(total>0){
					//查询已经玩过的次数
					playNumber = playRecordService.NumberOfTimesToPlay(new PlayRecord(openId, nickName, gameId, phoneNumber));
					
				}else{
					//不能玩大转盘
				}
			} else if(gameType.equals(2)){
				String status = latestInfo.getStatus();	//状态
			}
			request.setAttribute("userJHDX", latestInfo);
			//return "redirect:.htm?phone"+phoneNumber;
		}else{
			
		}
			
			
		
		return null;
	}
}

package com.shiliu.game.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.shiliu.game.common.FlowOperator;
import com.shiliu.game.domain.Game;
import com.shiliu.game.domain.UserGame;
import com.shiliu.game.domain.UserJHDX;
import com.shiliu.game.domain.UserRecord;
import com.shiliu.game.service.IGameService;
import com.shiliu.game.service.IUserGameService;
import com.shiliu.game.service.IUserJHDXService;
import com.shiliu.game.service.IUserRecordService;
/**
 * 用户游戏界面
 * @author lzh
 * @version 1.0
 */
@Controller
@RequestMapping("/userGame")
public class UserGameController {
	public Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private IUserGameService userGameService;
	
	@Resource
	private IGameService gameService;
	
	@Resource
	private IUserRecordService userRecordService;
	
	@Resource
	private IUserJHDXService userJHDXService;
	/**
	 * 进入游戏初始页面方法，已经入大转盘界面就异步ajax进入该方法
	 * @param gameId 初始页面传过来的gameId
	 * @param session session 
	 * @param openId openId 初始页面传入openId
	 * @return 返回json数据包含用户信息和提示信息
	 */
	@RequestMapping(value="/intoGame")
	@ResponseBody
	public String into(@RequestParam(value="gameId") String gameId,HttpSession session,
						@RequestParam(value="openId") String openId){
		Map<String, String> map = new HashMap<String, String>();
		String message;
		//判断是否值是否正确
		if (gameId==null&&openId==null){
			message = "该用户的gameID或openID参数不对";
			map.put("code", "2");
			map.put("message",message );
			return JSON.toJSONString(map);
		}
		//判断是都存在该游戏
		Game game = gameService.getGameById(gameId);
		if(game==null){
			message = "该游戏不存在";
			map.put("code", "3");
			map.put("message", message);
			return JSON.toJSONString(map);
		}
		Date creatTime = game.getCreatTime();
		Date endTimes = game.getEndTimes();
		Date nowTime = new Date();
		if(nowTime.after(endTimes)||nowTime.before(creatTime)){
			message = "该游戏尚未开始";
			map.put("code", "4");
			map.put("message", message);
			return JSON.toJSONString(map);
		}
		//判断是否之前玩过该游戏
		UserGame userGame = userGameService.selectOpenID(openId, gameId);
		if(userGame==null){
			message = "流量更新时间["+userJHDXService.selectUpdate()+"]，绑定手机号码查询流量，送20M全国流量";
			map.put("code", "1");
			map.put("message", message);
			return JSON.toJSONString(map);
		}
		//该用户确定在user_game表中有过记录。则将userGame信息添加到session中
		session.setAttribute("userGame", userGame);
		//获取一个截止日期
		String phone = userGame.getPhone();
		UserJHDX userJHDX = userJHDXService.SelectUpdatePhone(phone);
		String uptodate = userJHDX.getUptodate();
		int surplusPlayTimes = userGame.getTotalTimes()-userGame.getPlayTimes();
		message = "流量更新时间["+uptodate+"]：您已使用["+userJHDX.getFlux()+"]MB，"
				+ "		共有["+userJHDX.getTotal()+"]次抽奖机会，剩余["+surplusPlayTimes+"]抽奖机会。";
		map.put("code", "0");
		map.put("message", message);
		return JSON.toJSONString(map);
	}
	/**
	 * 绑定手机号码方法
	 * @param phone  传入的手机号码
	 * @param gameId 传入的gameId
	 * @param openId 传入的
	 * @param session
	 * @return 返回标识符和提示信息
	 */
	@RequestMapping(value="/bindingPhone")
	@ResponseBody
	public String bindingPhone(@RequestParam(value="phone") String phone,@RequestParam(value="gameId") 
			String gameId,@RequestParam(value="openId") String openId,HttpSession session){
		Map<String, String> map = new HashMap<String, String>();
		String message;
		if(phone==null||phone.length()!=13){
			message = "输入的手机号码不对或则未输入";
			map.put("code", "5");
			map.put("message",message);
			return JSON.toJSONString(map);
		}
		UserJHDX userJHDX = userJHDXService.SelectUpdatePhone(phone);
		if(userJHDX==null){
			message = "您绑定的手机号码不能参加本次活动";
			map.put("code", "6");
			map.put("message",message);
			return JSON.toJSONString(map);
		}
		if(userJHDX.getTotal()==0){
			message = "截止"+userJHDX.getUptodate()+"您使用的流量不足3G，暂无抽奖资格";
			map.put("code", "7");
			map.put("message",message);
			return JSON.toJSONString(map);
		}
		UserGame user = new UserGame();
		user.setOpenid(openId);
		user.setGameId(gameId);
		user.setPhone(phone);
		user.setTotalTimes(userJHDX.getTotal());
		user.setPlayTimes(0);
		user.setFlowNum(Integer.valueOf(userJHDX.getFlux()));
		//将数据添加到数据库中
		userGameService.insertSelective(user);
		//同时将user信息添加到session中
		session.setAttribute("userGame", user);
		//返回提示和标识号
		String uptodate = userJHDX.getUptodate();
		int surplusPlayTimes = user.getTotalTimes()-user.getPlayTimes();
		message = "流量更新时间["+uptodate+"]：您已使用["+userJHDX.getFlux()+"]MB，"
				+ "		共有["+userJHDX.getTotal()+"]次抽奖机会，剩余["+surplusPlayTimes+"]抽奖机会。";
		map.put("code", "0");
		map.put("message", message);
		return JSON.toJSONString(map);
	}
	/**
	 * 领取奖励方法
	 * @param rewardValue 中奖代码，用1-6代替
	 * @param session session参数
	 * @param nickName 微信名称
	 * @return 返回提示信息和结果
	 */
	@RequestMapping(value="/startGame")
	@ResponseBody
	public String startGame(@RequestParam(value="rewardValue") String rewardValue,HttpSession session, @RequestParam(value="nickName") String nickName){
		Map<String, String> map = new HashMap<String, String>();
		String message;
		UserRecord userRecord;
		//先判断剩余中奖次数
		UserGame userGame = (UserGame) session.getAttribute("userGame");
		//判断userGame是否存在
		if(userGame==null){
			message = "用户等待时间 过长，请重新进入游戏！";
			map.put("code", "13");
			map.put("message", message);
			return JSON.toJSONString(map);
		}
		int surplusPlayTimes = userGame.getTotalTimes()-userGame.getPlayTimes();
		if(surplusPlayTimes==0){//该用户剩余中奖次数为零时，返回提示
			//添加记录 表中
			userRecord = new UserRecord();
			userRecord.setAward("0");
			userRecord.setGameId(userGame.getGameId());
			userRecord.setNickName(nickName);
			userRecord.setOpenId(userGame.getOpenid());
			userRecord.setPhone(userGame.getPhone());
			userRecord.setPlayTime(new Date());
			userRecordService.insertSelective(userRecord);	
			message = "您的抽奖次数已用完，继续使用流量获得更多抽奖机会！";
			map.put("code", "8");
			map.put("message", message);
			return JSON.toJSONString(map);
		}
		//当剩余抽奖次数大于 零，则查看中奖奖品作出相应的奖励
		if(rewardValue=="6"){
			//未中奖，谢谢惠顾
			//添加信息到记录表中
			userRecord = new UserRecord();
			userRecord.setAward("6");
			userRecord.setGameId(userGame.getGameId());
			userRecord.setNickName(nickName);
			userRecord.setOpenId(userGame.getOpenid());
			userRecord.setPhone(userGame.getPhone());
			userRecord.setPlayTime(new Date());
			userRecordService.insertSelective(userRecord);	
			//添加返回 信息
			message = "蓝瘦、香菇，本次未中奖，看天翼视讯有机会获得iPhone7哦！";
			map.put("code", "9");
			map.put("message", message);
			return JSON.toJSONString(map);
			
		}
		if(rewardValue=="4"||rewardValue=="5"){
			//抽中话费 ，显示 提示。
			String rewardName = UserRecord.awardMap.get(rewardValue);
			//添加信息到记录表中
			userRecord = new UserRecord();
			if(rewardValue=="4"){				
				userRecord.setAward("4");
			}else{
				userRecord.setAward("5");
			}
			userRecord.setGameId(userGame.getGameId());
			userRecord.setNickName(nickName);
			userRecord.setOpenId(userGame.getOpenid());
			userRecord.setPhone(userGame.getPhone());
			userRecord.setPlayTime(new Date());
			userRecordService.insertSelective(userRecord);	
			
			//添加返回 信息
			message = "恭喜您获得["+rewardName+"]，将于2017年1月20日前完成充值，继续使用流量获得更多抽奖机会！";
			map.put("code", "10");
			map.put("message", message);
			return JSON.toJSONString(map);
			
		}
		if(rewardValue=="1"||rewardValue=="2"||rewardValue=="3"){
			//抽中流量,调用流量接口，充值流量
			String rewardName = UserRecord.awardMap.get(rewardValue);
			//添加信息到记录表中
			userRecord = new UserRecord();
			//根据不同奖项充值
			if(rewardValue=="1"){				
				userRecord.setAward("1");
				FlowOperator.hlenFlowGZ(userGame.getGameId(), "1024", userGame.getOpenid());
			}else if(rewardValue=="2"){
				FlowOperator.hlenFlowGZ(userGame.getGameId(), "500", userGame.getOpenid());
				userRecord.setAward("2");
			}else{
				FlowOperator.hlenFlowGZ(userGame.getGameId(), "30s", userGame.getOpenid());
				userRecord.setAward("3");
			}
			userRecord.setGameId(userGame.getGameId());
			userRecord.setNickName(nickName);
			userRecord.setOpenId(userGame.getOpenid());
			userRecord.setPhone(userGame.getPhone());
			userRecord.setPlayTime(new Date());
			userRecordService.insertSelective(userRecord);	
			
			//添加返回 信息
			message = "恭喜您获得["+rewardName+"]，已存入？？，随用随充，继续使用流量获得更多抽奖机会！";
			map.put("code", "11");
			map.put("message", message);
			return JSON.toJSONString(map);
			
		}
		//传入的中奖参数不对，返回提示
		message = "中奖参数不对，请于管理员联系！";
		map.put("code", "12");
		map.put("message", message);
		return JSON.toJSONString(map);
	}
}

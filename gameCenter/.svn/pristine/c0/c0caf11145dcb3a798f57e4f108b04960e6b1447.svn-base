package com.shiliu.game.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.shiliu.game.domain.AppCount;
import com.shiliu.game.domain.Ballot;
import com.shiliu.game.domain.Poll;
import com.shiliu.game.service.IBallotService;
import com.shiliu.game.service.IPollService;
@Controller
@RequestMapping("/ballot")
public class BallotController {
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private IBallotService ballotService;
	@Resource
	private IPollService pollService;
	@RequestMapping(value="/ballotJsp")
	public String ballotJsp(@RequestParam(value="title") String title,@RequestParam(value="startTime") String startTime,
							@RequestParam(value="endTime") String endTime,HttpServletRequest request,HttpSession session){
		List<String> appList = new ArrayList<String>();
		for(int i=1;i<=10;i++){
			String name = "app"+Integer.toString(i);
			String value= request.getParameter(name);
			appList.add(value);
		}
		Ballot ballot = new Ballot();
		ballot.setStartTime(startTime);
		ballot.setEndTime(endTime);
		ballot.setTitle(title);
		session.setAttribute("appList", appList);
		ballotService.insertSelective(ballot);
		return "ballot";
	}
	@RequestMapping(value="/selectPhone")
	@ResponseBody
	public String selectPhone(@RequestParam(value="phone") String phone,HttpSession session){
		Map<String,String> map = new HashMap<String,String>();
		if(phone==null){
			map.put("message", "手机参数为空");
			return JSON.toJSONString(map);
		}
		Poll poll;
		poll = pollService.selectPhone(phone);
		if(poll==null){
			poll = new Poll();
			poll.setPhone(phone);
			pollService.insertSelective(poll);
			map.put("message", "success");
			session.setAttribute("phone", phone);
			return JSON.toJSONString(map);
		}else{
			map.put("message", "该手机号已经参与过该活动");
			return JSON.toJSONString(map);
		}
	}
	@RequestMapping(value="/app")
	public String app(@RequestParam(value="app") String app,HttpSession session){
		String phone = (String) session.getAttribute("phone");
		Poll poll;
		poll = pollService.selectPhone(phone);
		poll.setAppName(app);
		pollService.updateByPrimaryKeySelective(poll);
		List<String> applist = (List<String>) session.getAttribute("appList");
		List<AppCount> countList = new ArrayList<AppCount>();
		for(int i=0;i<applist.size();i++){
			String appName = applist.get(i);
			int appCount = pollService.selectApp(appName);
			AppCount count = new AppCount(appName,appCount);
			countList.add(count);
		}
		session.setAttribute("countList", countList);
		return "appResult";
	}
}

package com.shiliu.game.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.shiliu.game.common.Constant;
import com.shiliu.game.domain.Vote;
import com.shiliu.game.domain.VoteOption;
import com.shiliu.game.service.IVoteService;
import com.shiliu.game.utils.StringUtils;

/** 
* @author popl 
* @version 1.0
* @createDate 2016年7月11日 下午3:12:11 
* @description 投票竞猜游戏类编辑游戏规则接口
*/
@Controller
@RequestMapping("/vote")
public class VoteController {
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Resource
    private IVoteService voteService;
   
    @InitBinder
    public void initBinder(WebDataBinder binder) {
             /**
              * 自动转换日期类型的字段格式
              */
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
             binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        }
    /**
     * 游戏编辑
     * @param modelMap
     * @param gameId
     * @param acctId
     * @param type vote投票  guess 竞猜
     * @return
     */
	@RequestMapping(value="/edit/{type}")
	public String edit(@PathVariable String type,ModelMap modelMap ,@RequestParam(value="gameId") String gameId,
			@RequestParam(value="title") String title,@RequestParam(value="startTime") Date startTime,@RequestParam(value="endTime") Date endTime){
		Vote vote = voteService.getVoteForId(gameId);
		List<VoteOption> list = null;
		if(vote == null){
			vote = new Vote();
			vote.setVoteId(gameId);
			vote.setVoteName(title);
			vote.setVoteEndTime(endTime);
			System.out.println(startTime + " 结束时间" + endTime);
		}else{
			list = voteService.getVoteOption(gameId);
		}
		if(list == null)
			list = new ArrayList<VoteOption>();
		modelMap.put("vote", vote);
		modelMap.put("options", list);
		return "voteEdit";
	}
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute Vote vote,@RequestParam(value = "file", required = false) MultipartFile file,HttpSession session){
		String fileName = file.getOriginalFilename();
		if(fileName !=null && !"".equals(fileName)){
			String[] temp = fileName.split("\\.");
			String	newFileName = "vote." + temp[temp.length - 1];
			String path = session.getServletContext().getRealPath(Constant.FILE_PATH+"/" + vote.getVoteId());
			File targetFile = new File(path, newFileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存
			try {
				file.transferTo(targetFile);
			} catch (Exception e) {
				logger.error("保存文件失败", e);
				return "0";
			}
			vote.setImgUrl(Constant.FILE_PATH+"/" +vote.getVoteId() +"/" + newFileName);
		}
		return "" + voteService.saveVote(vote);
	}
	@RequestMapping("/saveoption")
	@ResponseBody
	public String voteOption(@ModelAttribute VoteOption option,@RequestParam(value = "file", required = false) MultipartFile file,HttpSession session){
		boolean isNew = false;
		if(StringUtils.isNULL(option.getOptionId())){
			option.setOptionId(StringUtils.getUUID());
			isNew = true;
		}
		String fileName = file.getOriginalFilename();
		if(fileName !=null && !"".equals(fileName)){
			String[] temp = fileName.split("\\.");
			String	newFileName = option.getOptionId() + "." + temp[temp.length - 1];
			String path = session.getServletContext().getRealPath(Constant.FILE_PATH+"/" + option.getVoteId());
			File targetFile = new File(path, newFileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存
			try {
				file.transferTo(targetFile);
			} catch (Exception e) {
				logger.error("保存文件失败", e);
				return "0";
			}
			option.setImgUrl(Constant.FILE_PATH+"/" +option.getVoteId() +"/" + newFileName);
		}
		if(isNew){
			voteService.insertVoteOption(option);
		}else{
			voteService.updateVoteOption(option);
		}
		return JSON.toJSONString(voteService.getVoteOption(option.getVoteId()));
	}
	@RequestMapping("/deleteoption")
	@ResponseBody
	public String deleteOption(@RequestParam(value="optionId") String optionId,@RequestParam(value="voteId") String voteId){
		voteService.deleteOptions(optionId);
		return JSON.toJSONString(voteService.getVoteOption(voteId));
	}
}

package com.shiliu.game.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import com.shiliu.game.common.Constant;
import com.shiliu.game.domain.Game;
import com.shiliu.game.domain.GameQuestion;
import com.shiliu.game.service.IGameService;

/**
 * @Auth popl_lu
 * @Date 2016年7月18日 下午2:32:16
 * @authEmail popl_lu@sian.cn
 * @CalssName com.shiliu.game.controller.GameController
 * @dec 游戏后台编辑
 */
@Controller
@RequestMapping("game")
public class GameController {
	 private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private IGameService gameService;
	/********
	 * 游戏编辑
	 * @param type
	 * @param modelMap
	 * @param gameId
	 * @param acctId
	 * @return
	 */
	@RequestMapping(value="/edit/{type}")
	public String edit(@PathVariable String type,ModelMap modelMap ,@RequestParam(value="gameId") String gameId,@RequestParam(value="acctId") String acctId){
		Game game = gameService.getGameById(gameId);
		List<GameQuestion> list = null;
		if(game == null){
			game = new Game();
			game.setGameId(gameId);
		}else{
			if( 200 < game.getGameType() && game.getGameType() < 400)
				list = gameService.getQuestion(gameId);
		}
		if(list == null)
			list = new ArrayList<GameQuestion>();
		modelMap.put("game", game);
		modelMap.put("question", list);
		return type+"Edit";
	}
	/***
	 * 游戏保存
	 * @param game
	 * @param session
	 * @param imageUrl1
	 * @param imageUrl2
	 * @param imageUrl3
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute Game game,HttpSession session,
			@RequestParam(value = "imageUrl1", required = false) MultipartFile imageUrl1,
			@RequestParam(value = "imageUrl2", required = false) MultipartFile imageUrl2,
			@RequestParam(value = "imageUrl3", required = false) MultipartFile imageUrl3){
		String fileName = imageUrl1.getOriginalFilename();
		if(fileName !=null && !"".equals(fileName)){
			String[] temp = fileName.split("\\.");
			String	newFileName = "vote." + temp[temp.length - 1];
			String path = session.getServletContext().getRealPath(Constant.FILE_PATH+"/" + game.getGameId()+"1");
			File targetFile = new File(path, newFileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存
			try {
				imageUrl1.transferTo(targetFile);
			} catch (Exception e) {
				logger.error("保存文件失败", e);
				return "0";
			}
			game.setImageUrl1(Constant.FILE_PATH+"/" +game.getGameId() +"/" + newFileName);
		}
		fileName = imageUrl2.getOriginalFilename();
		if(fileName !=null && !"".equals(fileName)){
			String[] temp = fileName.split("\\.");
			String	newFileName = "vote." + temp[temp.length - 1];
			String path = session.getServletContext().getRealPath(Constant.FILE_PATH+"/" + game.getGameId()+"2");
			File targetFile = new File(path, newFileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存
			try {
				imageUrl2.transferTo(targetFile);
			} catch (Exception e) {
				logger.error("保存文件失败", e);
				return "0";
			}
			game.setImageUrl1(Constant.FILE_PATH+"/" +game.getGameId() +"/" + newFileName);
		}
		fileName = imageUrl3.getOriginalFilename();
		if(fileName !=null && !"".equals(fileName)){
			String[] temp = fileName.split("\\.");
			String	newFileName = "vote." + temp[temp.length - 1];
			String path = session.getServletContext().getRealPath(Constant.FILE_PATH+"/" + game.getGameId()+"3");
			File targetFile = new File(path, newFileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存
			try {
				imageUrl1.transferTo(targetFile);
			} catch (Exception e) {
				logger.error("保存文件失败", e);
				return "0";
			}
			game.setImageUrl3(Constant.FILE_PATH+"/" +game.getGameId() +"/" + newFileName);
		}
		return "" + gameService.saveGame(game);
	}
	
}

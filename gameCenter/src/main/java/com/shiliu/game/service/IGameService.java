package com.shiliu.game.service;

import java.util.List;

import com.shiliu.game.domain.Game;
import com.shiliu.game.domain.GameQuestion;
import com.shiliu.game.domain.QuestionOption;

/**
 * @Auth popl_lu
 * @Date 2016年7月21日 上午9:44:38
 * @authEmail popl_lu@sian.cn
 * @CalssName com.shiliu.game.service.IGameService
 * @dec 
 */
public interface IGameService {

	/**获取游戏基本信息*/
	Game getGameById(String gameId);
	/**获取游戏下题目基本信息*/
	List<GameQuestion> getQuestion(String gameId);
	/**保存游戏*/
	int saveGame(Game game);
	/**保存题目信息*/
	int saveQuestion(GameQuestion gameQuestion);
	/**保存选项信息*/
	int saveQuestionOption(QuestionOption option);
	/** 选择性添加 */
	int insertSelective(Game record);
    /**
     * 金华电信效验GameId
     * 效验内容
     * 		1.GameId 是否存在
     * 		2.gameType 必须对应
     * 		3.现在时间必须是，开始与结束时间区间内。
     * @author wkr
     * @Date 2016-12-05 15:37
     * @param game
     * @return	count	查询到的条数
     */
    int checkUpGameID(Game game);
}

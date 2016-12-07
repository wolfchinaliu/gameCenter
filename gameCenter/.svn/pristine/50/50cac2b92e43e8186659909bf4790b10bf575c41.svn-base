package com.shiliu.game.service;

import java.util.List;
import java.util.Map;

import com.shiliu.game.domain.UserGame;
import com.shiliu.game.domain.Vote;
import com.shiliu.game.domain.VoteOption;

public interface IVoteService {

	/**获取投票游戏信息*/
	Vote getVoteForId(String gameId);
	/**保存信息*/
	int saveVote(Vote vote);
	/**获取选项列表*/
	List<VoteOption> getVoteOption(String gameId);
	/**批量保存选项*/
	int saveVoteOptionList(List<VoteOption> list);
	/**保存选项信息*/
	int saveVoteOption(VoteOption vote);
	/**插入选项信息*/
	public int insertVoteOption(VoteOption voteOption);
	/**修改选项信息*/
	public int updateVoteOption(VoteOption voteOption);
	/**更新投票数*/
	public int updateVoteNum(String optinIds);
	/**获取用户与活动的记录*/
	public UserGame getUserGame(String gameId,String oppenId,int frequency,int evenTimes);
	/**提交投票*/
	public Map<String,String> subSelectVote(Long userGameId,String voteId, String openid, String options);
	/**删除选项*/
	public void deleteOptions(String optionId);
}

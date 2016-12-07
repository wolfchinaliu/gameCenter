package com.shiliu.game.dao;

import com.shiliu.game.domain.UserGame;
import com.shiliu.game.domain.UserGameExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserGameMapper {
	
	UserGame selectOpenID(String openID,String gameID);//查询是该用户是否玩过该游戏
	
	UserGame selectByGameIdAndOpenId(@Param("gameId") String gameId,@Param("openId") String openId);
    int countByExample(UserGameExample example);

    int deleteByExample(UserGameExample example);

    int deleteByPrimaryKey(Long userGameId);

    int insert(UserGame record);

    int insertSelective(UserGame record);

    List<UserGame> selectByExample(UserGameExample example);

    UserGame selectByPrimaryKey(Long userGameId);

    int updateByExampleSelective(@Param("record") UserGame record, @Param("example") UserGameExample example);

    int updateByExample(@Param("record") UserGame record, @Param("example") UserGameExample example);

    int updateByPrimaryKeySelective(UserGame record);

    int updateByPrimaryKey(UserGame record);
}
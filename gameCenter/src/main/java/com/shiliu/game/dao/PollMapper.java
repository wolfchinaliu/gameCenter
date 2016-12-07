package com.shiliu.game.dao;

import com.shiliu.game.domain.Poll;
import com.shiliu.game.domain.PollExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PollMapper {
	Poll selectPhone(String phone);
	
	int selectApp(String appName);
	
    int countByExample(PollExample example);

    int deleteByExample(PollExample example);

    int deleteByPrimaryKey(String pollId);

    int insert(Poll record);

    int insertSelective(Poll record);

    List<Poll> selectByExample(PollExample example);

    Poll selectByPrimaryKey(String pollId);

    int updateByExampleSelective(@Param("record") Poll record, @Param("example") PollExample example);

    int updateByExample(@Param("record") Poll record, @Param("example") PollExample example);

    int updateByPrimaryKeySelective(Poll record);

    int updateByPrimaryKey(Poll record);
}
package com.shiliu.game.dao;

import com.shiliu.game.domain.Vote;
import com.shiliu.game.domain.VoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteMapper {
    int countByExample(VoteExample example);

    int deleteByExample(VoteExample example);

    int deleteByPrimaryKey(String voteId);

    int insert(Vote record);

    int insertSelective(Vote record);

    List<Vote> selectByExample(VoteExample example);

    Vote selectByPrimaryKey(String voteId);

    int updateByExampleSelective(@Param("record") Vote record, @Param("example") VoteExample example);

    int updateByExample(@Param("record") Vote record, @Param("example") VoteExample example);

    int updateByPrimaryKeySelective(Vote record);

    int updateByPrimaryKey(Vote record);
}
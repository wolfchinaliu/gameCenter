package com.shiliu.game.dao;

import com.shiliu.game.domain.VoteOption;
import com.shiliu.game.domain.VoteOptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteOptionMapper {
    int countByExample(VoteOptionExample example);

    int deleteByExample(VoteOptionExample example);

    int deleteByPrimaryKey(String optionId);

    int insert(VoteOption record);

    int insertSelective(VoteOption record);

    List<VoteOption> selectByExample(VoteOptionExample example);

    VoteOption selectByPrimaryKey(String optionId);

    int updateByExampleSelective(@Param("record") VoteOption record, @Param("example") VoteOptionExample example);

    int updateByExample(@Param("record") VoteOption record, @Param("example") VoteOptionExample example);

    int updateByPrimaryKeySelective(VoteOption record);

    int updateByPrimaryKey(VoteOption record);
    
    int insertList(List<VoteOption> list);
    
    int updateVoteNum(List<String> list);
}
package com.shiliu.game.dao;

import com.shiliu.game.domain.Ballot;
import com.shiliu.game.domain.BallotExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BallotMapper {
    int countByExample(BallotExample example);

    int deleteByExample(BallotExample example);

    int insert(Ballot record);

    int insertSelective(Ballot record);

    List<Ballot> selectByExample(BallotExample example);

    int updateByExampleSelective(@Param("record") Ballot record, @Param("example") BallotExample example);

    int updateByExample(@Param("record") Ballot record, @Param("example") BallotExample example);
}
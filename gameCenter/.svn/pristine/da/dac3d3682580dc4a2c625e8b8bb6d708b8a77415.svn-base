package com.shiliu.game.dao;

import com.shiliu.game.domain.UserVoteLog;
import com.shiliu.game.domain.UserVoteLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserVoteLogMapper {
    int countByExample(UserVoteLogExample example);

    int deleteByExample(UserVoteLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserVoteLog record);

    int insertSelective(UserVoteLog record);

    List<UserVoteLog> selectByExample(UserVoteLogExample example);

    UserVoteLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserVoteLog record, @Param("example") UserVoteLogExample example);

    int updateByExample(@Param("record") UserVoteLog record, @Param("example") UserVoteLogExample example);

    int updateByPrimaryKeySelective(UserVoteLog record);

    int updateByPrimaryKey(UserVoteLog record);
}
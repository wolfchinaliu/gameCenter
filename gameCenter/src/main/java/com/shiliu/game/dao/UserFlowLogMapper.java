package com.shiliu.game.dao;

import com.shiliu.game.domain.UserFlowLog;
import com.shiliu.game.domain.UserFlowLogExample;
import com.shiliu.game.domain.UserFlowLogKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserFlowLogMapper {
    int countByExample(UserFlowLogExample example);

    int deleteByExample(UserFlowLogExample example);

    int deleteByPrimaryKey(UserFlowLogKey key);

    int insert(UserFlowLog record);

    int insertSelective(UserFlowLog record);

    List<UserFlowLog> selectByExample(UserFlowLogExample example);

    UserFlowLog selectByPrimaryKey(UserFlowLogKey key);

    int updateByExampleSelective(@Param("record") UserFlowLog record, @Param("example") UserFlowLogExample example);

    int updateByExample(@Param("record") UserFlowLog record, @Param("example") UserFlowLogExample example);

    int updateByPrimaryKeySelective(UserFlowLog record);

    int updateByPrimaryKey(UserFlowLog record);
}
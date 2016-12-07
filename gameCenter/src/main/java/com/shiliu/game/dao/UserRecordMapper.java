package com.shiliu.game.dao;

import com.shiliu.game.domain.UserRecord;
import com.shiliu.game.domain.UserRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRecordMapper {

	List<UserRecord> selectAll();//查询所有用户记录
	
	List<UserRecord> selectAward();//查询中奖用户信息
	
    int countByExample(UserRecordExample example);

    int deleteByExample(UserRecordExample example);

    int deleteByPrimaryKey(Long userRecordId);

    int insert(UserRecord record);

    int insertSelective(UserRecord record);

    List<UserRecord> selectByExample(UserRecordExample example);

    UserRecord selectByPrimaryKey(Long userRecordId);

    int updateByExampleSelective(@Param("record") UserRecord record, @Param("example") UserRecordExample example);

    int updateByExample(@Param("record") UserRecord record, @Param("example") UserRecordExample example);

    int updateByPrimaryKeySelective(UserRecord record);

    int updateByPrimaryKey(UserRecord record);
}
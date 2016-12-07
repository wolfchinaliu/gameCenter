package com.shiliu.game.dao;

import com.shiliu.game.domain.ExcelUser;
import com.shiliu.game.domain.ExcelUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ExcelUserMapper {
	/** 查询对应客户的方法 */
	List<ExcelUser> selectId(@Param(value="start") int start,@Param(value="size") int size,@Param(value="gameId") String gameId);
	/** 查询对应客户的总条数 */
	int count(@Param(value="gameId") String gameId);
	/** 添加Excel读取出的对象 */
    int insertClient(List<ExcelUser> list);
	
	ExcelUser selectUser(ExcelUser record);
	
    int countByExample(ExcelUserExample example);

    int deleteByExample(ExcelUserExample example);

    int deleteByPrimaryKey(String euId);
    
    int insert(ExcelUser record);
    
    int insertSelective(ExcelUser record);

    List<ExcelUser> selectByExample(ExcelUserExample example);

    ExcelUser selectByPrimaryKey(String euId);

    int updateByExampleSelective(@Param("record") ExcelUser record, @Param("example") ExcelUserExample example);

    int updateByExample(@Param("record") ExcelUser record, @Param("example") ExcelUserExample example);

    int updateByPrimaryKeySelective(ExcelUser record);

    int updateByPrimaryKey(ExcelUser record);
}
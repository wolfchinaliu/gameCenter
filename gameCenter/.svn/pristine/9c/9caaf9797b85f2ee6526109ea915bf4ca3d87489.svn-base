package com.shiliu.game.dao;

import com.shiliu.game.domain.GameExcel;
import com.shiliu.game.domain.GameExcelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GameExcelMapper {
	int countByExample(GameExcelExample example);

    int deleteByExample(GameExcelExample example);

    int deleteByPrimaryKey(String gameId);

    int insert(GameExcel record);

    int insertSelective(GameExcel record);

    List<GameExcel> selectByExample(GameExcelExample example);

    GameExcel selectByPrimaryKey(String gameId);

    int updateByExampleSelective(@Param("record") GameExcel record, @Param("example") GameExcelExample example);

    int updateByExample(@Param("record") GameExcel record, @Param("example") GameExcelExample example);

    int updateByPrimaryKeySelective(GameExcel record);

    int updateByPrimaryKey(GameExcel record);
}
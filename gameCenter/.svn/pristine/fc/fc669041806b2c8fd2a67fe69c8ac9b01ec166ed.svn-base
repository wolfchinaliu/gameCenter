package com.shiliu.game.dao;

import com.shiliu.game.domain.GameQuestion;
import com.shiliu.game.domain.GameQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GameQuestionMapper {
    int countByExample(GameQuestionExample example);

    int deleteByExample(GameQuestionExample example);

    int deleteByPrimaryKey(String questionId);

    int insert(GameQuestion record);

    int insertSelective(GameQuestion record);

    List<GameQuestion> selectByExample(GameQuestionExample example);

    GameQuestion selectByPrimaryKey(String questionId);

    int updateByExampleSelective(@Param("record") GameQuestion record, @Param("example") GameQuestionExample example);

    int updateByExample(@Param("record") GameQuestion record, @Param("example") GameQuestionExample example);

    int updateByPrimaryKeySelective(GameQuestion record);

    int updateByPrimaryKey(GameQuestion record);
}
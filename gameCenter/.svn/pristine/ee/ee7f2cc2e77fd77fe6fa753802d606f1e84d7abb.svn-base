package com.shiliu.game.dao;

import com.shiliu.game.domain.QuestionOption;
import com.shiliu.game.domain.QuestionOptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionOptionMapper {
    int countByExample(QuestionOptionExample example);

    int deleteByExample(QuestionOptionExample example);

    int deleteByPrimaryKey(String id);

    int insert(QuestionOption record);

    int insertSelective(QuestionOption record);

    List<QuestionOption> selectByExample(QuestionOptionExample example);

    QuestionOption selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") QuestionOption record, @Param("example") QuestionOptionExample example);

    int updateByExample(@Param("record") QuestionOption record, @Param("example") QuestionOptionExample example);

    int updateByPrimaryKeySelective(QuestionOption record);

    int updateByPrimaryKey(QuestionOption record);
}
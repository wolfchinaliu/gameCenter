package com.shiliu.game.dao;

import com.shiliu.game.domain.GameAwardRecode;
import com.shiliu.game.domain.GameAwardRecodeExample;
import com.shiliu.game.domain.GameAwardRecodeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GameAwardRecodeMapper {
    int countByExample(GameAwardRecodeExample example);

    int deleteByExample(GameAwardRecodeExample example);

    int deleteByPrimaryKey(GameAwardRecodeKey key);

    int insert(GameAwardRecode record);

    int insertSelective(GameAwardRecode record);

    List<GameAwardRecode> selectByExample(GameAwardRecodeExample example);

    GameAwardRecode selectByPrimaryKey(GameAwardRecodeKey key);

    int updateByExampleSelective(@Param("record") GameAwardRecode record, @Param("example") GameAwardRecodeExample example);

    int updateByExample(@Param("record") GameAwardRecode record, @Param("example") GameAwardRecodeExample example);

    int updateByPrimaryKeySelective(GameAwardRecode record);

    int updateByPrimaryKey(GameAwardRecode record);
}
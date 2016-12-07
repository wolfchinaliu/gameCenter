package com.shiliu.game.dao;

import com.shiliu.game.domain.Game;
import com.shiliu.game.domain.GameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * Game接口
 * @author asus
 *
 */
public interface GameMapper {
    int countByExample(GameExample example);

    int deleteByExample(GameExample example);

    int deleteByPrimaryKey(String gameId);

    int insert(Game record);

    int insertSelective(Game record);

    List<Game> selectByExample(GameExample example);

    Game selectByPrimaryKey(String gameId);

    int updateByExampleSelective(@Param("record") Game record, @Param("example") GameExample example);

    int updateByExample(@Param("record") Game record, @Param("example") GameExample example);

    int updateByPrimaryKeySelective(Game record);

    int updateByPrimaryKey(Game record);
    
    /**
     * 金华电信效验GameId
     * 效验内容
     * 		1.GameId 是否存在
     * 		2.gameType 必须对应
     * 		3.现在时间必须是，开始与结束时间区间内。
     * @author wkr
     * @Date 2016-12-05 15:37
     * @param game
     * @return	count	查询到的条数
     */
    int checkUpGameID(Game game);
}
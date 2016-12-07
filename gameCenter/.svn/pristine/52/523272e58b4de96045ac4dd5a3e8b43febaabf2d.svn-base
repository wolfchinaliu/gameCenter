package com.shiliu.game.dao;

import com.shiliu.game.domain.PlayRecord;
import com.shiliu.game.domain.PlayRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 参与游戏接口
 * @author wkr
 *
 */
public interface PlayRecordMapper {
    int countByExample(PlayRecordExample example);

    int deleteByExample(PlayRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PlayRecord record);

    int insertSelective(PlayRecord record);

    List<PlayRecord> selectByExample(PlayRecordExample example);

    PlayRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PlayRecord record, @Param("example") PlayRecordExample example);

    int updateByExample(@Param("record") PlayRecord record, @Param("example") PlayRecordExample example);

    int updateByPrimaryKeySelective(PlayRecord record);

    int updateByPrimaryKey(PlayRecord record);
    
    /**查询用户玩过游戏的次数 */
    int NumberOfTimesToPlay (PlayRecord playRecord);
}
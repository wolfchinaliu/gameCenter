package com.shiliu.game.dao;

import com.shiliu.game.domain.UserPhone;
import com.shiliu.game.domain.UserPhoneExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPhoneMapper {
    int countByExample(UserPhoneExample example);

    int deleteByExample(UserPhoneExample example);

    int deleteByPrimaryKey(String openid);

    int insert(UserPhone record);

    int insertSelective(UserPhone record);

    List<UserPhone> selectByExample(UserPhoneExample example);

    UserPhone selectByPrimaryKey(String openid);

    int updateByExampleSelective(@Param("record") UserPhone record, @Param("example") UserPhoneExample example);

    int updateByExample(@Param("record") UserPhone record, @Param("example") UserPhoneExample example);

    int updateByPrimaryKeySelective(UserPhone record);

    int updateByPrimaryKey(UserPhone record);
    
    /**
     * 金华电信效验OpenId
     * 效验内容
     * 		1.OpenId 是否存在
     * @author wkr
     * @Date 2016-12-05 16:00
     * @param game
     * @return	count	查询到的条数
     */
    int checkUpOpenID(String openid);
}
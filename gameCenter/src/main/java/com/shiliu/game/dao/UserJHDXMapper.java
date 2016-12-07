package com.shiliu.game.dao;

import com.shiliu.game.domain.UserJHDX;
import com.shiliu.game.domain.UserJHDXExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserJHDXMapper {
	//查询最新更新数据时间
	Date selectUpdate();
	//查询该手机号码的最新的信息
	UserJHDX SelectUpdatePhone(String phone);	
    int countByExample(UserJHDXExample example);

    int deleteByExample(UserJHDXExample example);

    int deleteByPrimaryKey(Long userid);

    int insert(UserJHDX record);

    int insertSelective(UserJHDX record);

    List<UserJHDX> selectByExample(UserJHDXExample example);

    UserJHDX selectByPrimaryKey(Long userid);

    int updateByExampleSelective(@Param("record") UserJHDX record, @Param("example") UserJHDXExample example);

    int updateByExample(@Param("record") UserJHDX record, @Param("example") UserJHDXExample example);

    int updateByPrimaryKeySelective(UserJHDX record);

    int updateByPrimaryKey(UserJHDX record);
    
    /** 添加抽奖客户 */
    int insertClient(@Param("list") List<UserJHDX> list,@Param("gameid") String gameid);
    
    /** 根据GameID,手机号查询最新一次更新的一条数据 */
    UserJHDX queryTheLatestUserInfo(UserJHDX userJHDX);
    
}
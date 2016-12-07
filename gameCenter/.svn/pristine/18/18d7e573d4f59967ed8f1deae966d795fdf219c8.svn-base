package com.shiliu.game.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shiliu.game.domain.UserJHDX;

/**
 * 金华电信用户业务逻辑类
 * @author wkr
 * @Date 2016-11-30 10:25
 *
 */
public interface IUserJHDXService {
	//查询最新更新数据时间
	String selectUpdate();
	
	/** 添加用户 */
	int insert(UserJHDX record);
	/**
	 * 查询该手机用户的最新更新的用户信息
	 * @param phone 用户的手机号
	 * @return 返回最新的用户信息。没有返回null
	 */
	UserJHDX SelectUpdatePhone(String phone);	
	
	/** 选择性添加 */
    int insertSelective(UserJHDX record);
    
    /** 添加抽奖客户 */
    int insertClient(@Param("list") List<UserJHDX> list,@Param("gameid") String gameid);
    
    /** 根据GameID,手机号查询最新一次更新的一条数据 */
    UserJHDX queryTheLatestUserInfo(UserJHDX userJHDX);
    
}

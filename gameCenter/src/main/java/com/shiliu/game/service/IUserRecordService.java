package com.shiliu.game.service;

import java.util.List;

import com.shiliu.game.domain.UserRecord;
/**
 * 用户记录信息接口设计
 * @author lzh
 * @version 1.0
 */
public interface IUserRecordService {
	/**
	 * 查询所有用户记录信息
	 * @return 返回用户List集合
	 */
	List<UserRecord> selectAll();
	/**
	 * 查询中奖用户信息
	 * @return
	 */
	List<UserRecord> selectAward();
	/**
	 * 添加用户记录信息
	 * @param userRecord 用户信息实体
	 * @return 返回影响的行数
	 */
	int insertSelective(UserRecord userRecord);
}

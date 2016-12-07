package com.shiliu.game.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shiliu.game.dao.UserRecordMapper;
import com.shiliu.game.domain.UserRecord;
import com.shiliu.game.service.IUserRecordService;

/**
 * 用户记录service
 * @author lzh
 * @version 1.0
 */

@Service
public class UserRecordServiceImpl implements IUserRecordService{
	
	@Resource 
	private UserRecordMapper userRecordMapper;
	/**
	 * 查询所有记录数
	 */
	@Override
	public List<UserRecord> selectAll() {
		return userRecordMapper.selectAll();
	}
	/**
	 * 查询中的记录数
	 */
	@Override
	public List<UserRecord> selectAward() {
		return userRecordMapper.selectAward();
	}
	/**
	 * 添加记录数
	 */
	@Override
	public int insertSelective(UserRecord userRecord) {
		return userRecordMapper.insertSelective(userRecord);
	}

}

package com.shiliu.game.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shiliu.game.dao.UserPhoneMapper;
import com.shiliu.game.domain.UserPhone;
import com.shiliu.game.service.IUserPhoneService;
/**
 * 绑定表业务逻辑层实现类
 * @author wkr
 * @Date 2016-12-05 14:23
 *
 */
@Service
public class UserPhoneServiceImpl implements IUserPhoneService {
	
	@Resource
	private UserPhoneMapper userPhoneMapper;

	@Override
	public UserPhone selectByPrimaryKey(String openid) {
		
		return userPhoneMapper.selectByPrimaryKey(openid);
	}

	@Override
	public int checkUpOpenID(String openid) {
		
		return userPhoneMapper.checkUpOpenID(openid);
	}

}

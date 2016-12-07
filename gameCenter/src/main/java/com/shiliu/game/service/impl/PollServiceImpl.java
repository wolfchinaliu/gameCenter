package com.shiliu.game.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shiliu.game.dao.PollMapper;
import com.shiliu.game.domain.Poll;
import com.shiliu.game.service.IPollService;
@Service
public class PollServiceImpl implements IPollService{
	@Resource
	private PollMapper pollMapper;
	@Override
	public int insertSelective(Poll record) {
		return pollMapper.insertSelective(record);
	}

	@Override
	public Poll selectPhone(String phone) {
		return pollMapper.selectPhone(phone);
	}

	@Override
	public int updateByPrimaryKeySelective(Poll record) {
		return pollMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int selectApp(String appName) {
		return pollMapper.selectApp(appName);
	}

}

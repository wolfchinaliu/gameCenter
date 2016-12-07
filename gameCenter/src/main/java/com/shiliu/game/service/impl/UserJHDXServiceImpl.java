package com.shiliu.game.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shiliu.game.dao.UserJHDXMapper;
import com.shiliu.game.domain.UserJHDX;
import com.shiliu.game.service.IUserJHDXService;

@Service
public class UserJHDXServiceImpl implements IUserJHDXService {

	@Resource
	private UserJHDXMapper userJHDXMapper;
	@Override
	public int insert(UserJHDX record) {
		
		return userJHDXMapper.insert(record);
	}
	@Override
	public UserJHDX SelectUpdatePhone(String phone) {
		return userJHDXMapper.SelectUpdatePhone(phone);
	}
	@Override
	public String selectUpdate() {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String updateTime = sdf.format(userJHDXMapper.selectUpdate());
		return updateTime;
	}
	@Override
	public int insertSelective(UserJHDX record) {
		
		return userJHDXMapper.insertSelective(record);
	}
	@Override
	public int insertClient(List<UserJHDX> list, String gameid) {
		
		return userJHDXMapper.insertClient(list, gameid);
	}
	@Override
	public UserJHDX queryTheLatestUserInfo(UserJHDX userJHDX) {
		
		return userJHDXMapper.queryTheLatestUserInfo(userJHDX);
	}
	
}

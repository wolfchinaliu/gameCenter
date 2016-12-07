package com.shiliu.game.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shiliu.game.dao.UserGameMapper;
import com.shiliu.game.domain.UserGame;
import com.shiliu.game.service.IUserGameService;
@Service
public class UserGameServiceImpl implements IUserGameService {
	@Resource
	private UserGameMapper userGameMapper;
	@Override
	public UserGame selectOpenID(String openID, String gameID) {
		return userGameMapper.selectOpenID(openID, gameID);
	}

	@Override
	public int insertSelective(UserGame userGame) {
		return userGameMapper.insertSelective(userGame);
	}

	@Override
	public UserGame selectByGameIdAndOpenId(String openId, String gameId) {
		return userGameMapper.selectByGameIdAndOpenId(gameId, openId);
	}

}

package com.shiliu.game.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shiliu.game.dao.GameExcelMapper;
import com.shiliu.game.domain.GameExcel;
import com.shiliu.game.service.IGameExcelService;

@Service
public class GameExcelServiceImpl implements IGameExcelService {
	
	@Resource
	private GameExcelMapper gameExcelMapper;
	
	@Override
	public GameExcel selectByPrimaryKey(String gameId) {
		
		return gameExcelMapper.selectByPrimaryKey(gameId);
	}

	@Override
	public int insertSelective(GameExcel record) {
		
		return gameExcelMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(GameExcel record) {
		//TODO Test ADD Commit
		return gameExcelMapper.updateByPrimaryKeySelective(record);
	}

}

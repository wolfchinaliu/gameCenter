package com.shiliu.game.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shiliu.game.dao.PlayRecordMapper;
import com.shiliu.game.domain.PlayRecord;
import com.shiliu.game.service.IPlayRecordService;

@Service
public class PlayRecordServiceImpl implements IPlayRecordService {

	@Resource
	private PlayRecordMapper playRecordMapper;
	
	@Override
	public int NumberOfTimesToPlay(PlayRecord playRecord) {
		
		return playRecordMapper.NumberOfTimesToPlay(playRecord);
	}

}

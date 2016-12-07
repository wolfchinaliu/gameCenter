package com.shiliu.game.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shiliu.game.dao.BallotMapper;
import com.shiliu.game.domain.Ballot;
import com.shiliu.game.service.IBallotService;
@Service
public class BallotServiceImpl implements IBallotService {
	@Resource
	private BallotMapper ballotMapper;
	@Override
	public int insertSelective(Ballot record) {
		return ballotMapper.insertSelective(record);
	}

}

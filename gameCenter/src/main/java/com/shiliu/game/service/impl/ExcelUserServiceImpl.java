package com.shiliu.game.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.shiliu.game.dao.ExcelUserMapper;
import com.shiliu.game.domain.ExcelUser;
import com.shiliu.game.service.IExcelUserService;
@Service
public class ExcelUserServiceImpl implements IExcelUserService {
	@Resource
	private ExcelUserMapper excelUserMapper;
	@Override
	public List<ExcelUser> selectId(int start,int size,String gameId) {
		return excelUserMapper.selectId(start,size,gameId);
	}
	public int insertSelective(ExcelUser record){
		return excelUserMapper.insertSelective(record);	
	}
	@Override
	public ExcelUser selectUser(ExcelUser record) {
		return excelUserMapper.selectUser(record);
	}
	@Override
	public int count(String gameId) {
		return excelUserMapper.count(gameId);
	}
	@Override
	public int updateByPrimaryKeySelective(ExcelUser record) {
		return excelUserMapper.updateByPrimaryKeySelective(record);
		
	}
	@Override
	public int insertClient(List<ExcelUser> list) {
		return excelUserMapper.insertClient(list);
	}

}

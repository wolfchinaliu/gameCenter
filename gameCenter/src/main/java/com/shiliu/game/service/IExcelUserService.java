package com.shiliu.game.service;


import java.util.List;

import com.shiliu.game.domain.ExcelUser;
/**
 * @author lzh
 * @author lzh
 * @Date 2016年11月21号
 *
 */
public interface IExcelUserService {
	/**
	 * 分页查询数据
	 * @param start 查询页
	 * @param size	查询页数记录数
	 * @return	返回查询页的记录
	 */
	List<ExcelUser> selectId(int start,int size,String gameId);
	/**
	 * 查询中记录数
	 * @return 返回中记录数
	 */
	int count(String gameId);
	
	/**
	 * 添加用户信息
	 * @param record 用户实体Bean
	 * @return 返回一影响行数
	 */
	int insertSelective(ExcelUser record);
	/**
	 * 查询该用户是否存在
	 * @param record 要查询用户的信息的实体Bean 
	 * @return 有就返回查询到的实体Bean，查询不到则返回null
	 */
	ExcelUser selectUser(ExcelUser record);
	/**
	 * 更新用户信息
	 * @param record  更新好的信息的实体Bean
	 * @return
	 */
	int updateByPrimaryKeySelective(ExcelUser record);
	
	/** 添加Excel读取出的对象 */
    int insertClient(List<ExcelUser> list);
}

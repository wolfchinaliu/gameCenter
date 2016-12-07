package com.shiliu.game.service;

import com.shiliu.game.domain.UserPhone;

/**
 * 绑定表业务逻辑层
 * @author wkr
 * @Date 2016-12-05 14:23
 *
 */
public interface IUserPhoneService {

	/** 根据ID查询对象 */
	UserPhone selectByPrimaryKey(String openid);
	
	/**
     * 金华电信效验OpenId
     * 效验内容
     * 		1.OpenId 是否存在
     * @author wkr
     * @Date 2016-12-05 16:00
     * @param game
     * @return	count	查询到的条数
     */
    int checkUpOpenID(String openid);
}

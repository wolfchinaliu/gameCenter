package weixin.lottery.service.impl;

import weixin.lottery.service.WeixinDrawDetailServiceI;
import weixin.lottery.service.WeixinLotteryServiceI;
import weixin.lottery.service.WeixinWinningrecordServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import weixin.lottery.entity.WeixinLotteryEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("weixinLotteryService")
@Transactional
public class WeixinLotteryServiceImpl extends CommonServiceImpl implements WeixinLotteryServiceI {

	@Autowired
	private WeixinDrawDetailServiceI drawDetailService;
	@Autowired
	private WeixinWinningrecordServiceI weixinWinningrecordService;

	public <T> void delete(T entity) {
		super.delete(entity);
		// 执行删除操作配置的sql增强
		this.doDelSql((WeixinLotteryEntity) entity);
	}

	public <T> Serializable save(T entity) {
		Serializable t = super.save(entity);
		// 执行新增操作配置的sql增强
		this.doAddSql((WeixinLotteryEntity) entity);
		return t;
	}

	public <T> void saveOrUpdate(T entity) {
		super.saveOrUpdate(entity);
		// 执行更新操作配置的sql增强
		this.doUpdateSql((WeixinLotteryEntity) entity);
	}

	/**
	 * 默认按钮-sql增强-新增操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doAddSql(WeixinLotteryEntity t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-更新操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doUpdateSql(WeixinLotteryEntity t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-删除操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doDelSql(WeixinLotteryEntity t) {
		return true;
	}


	public boolean delWeixinLottery(WeixinLotteryEntity t) {
		drawDetailService.deteWeixinDrawDetailByHdid(t.getId());
		weixinWinningrecordService.detWeixinWinningrecordByHdid(t.getId());
		this.delete(t);
		return true;
	}
}
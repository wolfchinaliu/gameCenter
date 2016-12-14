package weixin.lottery.service;
import weixin.lottery.entity.WeixinWinningrecordEntity;

import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface WeixinWinningrecordServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinWinningrecordEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinWinningrecordEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinWinningrecordEntity t);
 	
 	public Integer getCount(String sql);
 	
 	public void detWeixinWinningrecordByHdid(String hdid);
 	

}

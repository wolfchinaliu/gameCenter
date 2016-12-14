package weixin.business.service;
import weixin.business.entity.WeixinFoodEntity;

import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;

public interface WeixinFoodServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinFoodEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinFoodEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinFoodEntity t);
 	
 	/**
 	 * 促销商品
 	 * @param accountid
 	 * @return
 	 */
 	public List getHotList(String accountid);
 	
 	/**
 	 * 新品推荐
 	 * @param accountid
 	 * @return
 	 */
 	public List getNewList(String accountid);
 		
 	/**
 	 * 热销商品
 	 * @param accountid
 	 * @return
 	 */
 	public List getHotSaleList(String accountid);
 	
}

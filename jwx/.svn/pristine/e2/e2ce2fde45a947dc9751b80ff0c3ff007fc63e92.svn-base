package weixin.goods.service;
import weixin.goods.entity.WeixinShopGoodsEntity;

import org.hibernate.Query;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;

public interface WeixinShopGoodsServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinShopGoodsEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinShopGoodsEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinShopGoodsEntity t);
 	
 	/**
 	 * 促销商品
 	 * @param accountid
 	 * @return
 	 */
 	public List getHotGoodsList(String accountid);
 	
 	/**
 	 * 新品推荐
 	 * @param accountid
 	 * @return
 	 */
 	public List getNewGoodsList(String accountid);
 		
 	/**
 	 * 热销商品
 	 * @param accountid
 	 * @return
 	 */
 	public List getHotSaleGoodsList(String accountid);
 	
 	public List getListForPage(String hql, int begin, int pageSize);
	
	public int getCountForPage(String hql);
 	
}

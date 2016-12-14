package weixin.liuliangbao.service.flowcontroller;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.util.ResourceUtil;

import weixin.liuliangbao.jsonbean.ShareItem;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by aa on 2015/12/14.
 */
public interface BindingService extends CommonService {
	public static String path = ResourceUtil.getConfigByName("jfinalUrl-config");

	public <T> void delete(T entity);

	public <T> Serializable save(T entity);

	public <T> void saveOrUpdate(T entity);

	/**
	 * 默认按钮-sql增强-新增操作
	 *
	 * @param id
	 * @return
	 */
	public boolean doAddSql(ShareItem t);

	/**
	 * 默认按钮-sql增强-更新操作
	 *
	 * @param id
	 * @return
	 */
	public boolean doUpdateSql(ShareItem t);

	/**
	 * 默认按钮-sql增强-删除操作
	 *
	 * @param id
	 * @return
	 */
	public boolean doDelSql(ShareItem t);

}

package weixin.memberStructure.service;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;

import weixin.memberStructure.entity.LogMemberDatailedEntity;


/**
 * @author WangPeng
 * 2016年11月3日
 */
public interface LogMemberDatailedService  extends CommonService{

    public <T> Serializable save(LogMemberDatailedEntity entity);

    public <T> void saveOrUpdate(LogMemberDatailedEntity entity);
    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(LogMemberDatailedEntity t);

    /**
	 * 默认按钮-sql增强-更新操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doUpdateSql(LogMemberDatailedEntity t);

	


}

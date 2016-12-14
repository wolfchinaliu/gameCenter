package weixin.memberStructure.service;

import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;

import weixin.memberStructure.entity.MemberStatEntity;


/**
 * @author WangPeng
 * 2016年11月7日
 */
public interface MemberStatService  extends CommonService{

    public <T> Serializable save(MemberStatEntity entity);

    public <T> void saveOrUpdate(MemberStatEntity entity);
    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(MemberStatEntity t);

    /**
	 * 默认按钮-sql增强-更新操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doUpdateSql(MemberStatEntity t);

	


}

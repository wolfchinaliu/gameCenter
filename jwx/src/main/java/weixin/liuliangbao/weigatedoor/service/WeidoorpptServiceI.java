package weixin.liuliangbao.weigatedoor.service;

import org.jeecgframework.core.common.service.CommonService;
import weixin.liuliangbao.weigatedoor.entity.WeidoorpptEntity;

import java.io.Serializable;


/**
 * Created by aa on 2015/12/9.
 */
public interface WeidoorpptServiceI  extends CommonService {

    public <T> void delete(T entity);

    public <T> Serializable save(T entity);

    public <T> void saveOrUpdate(T entity);

    /**
     * 默认按钮-sql增强-新增操作
     * @param t
     * @return
     */
    public boolean doAddSql(WeidoorpptEntity t);
    /**
     * 默认按钮-sql增强-更新操作
     * @param t
     * @return
     */
    public boolean doUpdateSql(WeidoorpptEntity t);
    /**
     * 默认按钮-sql增强-删除操作
     * @param t
     * @return
     */
    public boolean doDelSql(WeidoorpptEntity t);


}

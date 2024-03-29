package weixin.liuliangbao.service.flowcontroller;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.liuliangbao.jsonbean.ShareItem;
import weixin.liuliangbao.service.flowcontroller.ShareItemServiceI;

import java.io.Serializable;

/**
 * Created by aa on 2015/12/14.
 */
@Service("shareItemService")
@Transactional
public class ShareItemServiceImpl extends CommonServiceImpl implements ShareItemServiceI {

    public <T> void delete(T entity) {
        super.delete(entity);
        //执行删除操作配置的sql增强
        this.doDelSql((ShareItem) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        //执行新增操作配置的sql增强
        this.doAddSql((ShareItem) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((ShareItem) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(ShareItem t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(ShareItem t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(ShareItem t) {
        return true;
    }
}

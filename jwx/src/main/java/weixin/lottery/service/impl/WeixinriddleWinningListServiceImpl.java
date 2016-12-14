package weixin.lottery.service.impl;

import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.lottery.entity.WeixinriddleWinningListEntity;
import weixin.lottery.service.WeixinriddleWinningListServiceI;

import java.io.Serializable;

/**
 * Created by aa on 2016/1/21.
 */
@Service("weixinriddleWinningListService")
@Transactional
public class WeixinriddleWinningListServiceImpl extends CommonServiceImpl implements WeixinriddleWinningListServiceI {

    @Autowired
    private JdbcDao jdbcDao;

    public <T> void delete(T entity) {
        super.delete(entity);
        // 执行删除操作配置的sql增强
        this.doDelSql((WeixinriddleWinningListEntity) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        // 执行新增操作配置的sql增强
        this.doAddSql((WeixinriddleWinningListEntity) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        // 执行更新操作配置的sql增强
        this.doUpdateSql((WeixinriddleWinningListEntity) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(WeixinriddleWinningListEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(WeixinriddleWinningListEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(WeixinriddleWinningListEntity t) {
        return true;
    }

    public Integer getCount(String sql) {
        return jdbcDao.countByJdbc(sql, null);
    }
}

package weixin.lottery.service.impl;

import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.lottery.entity.WeixinGuessRiddleEntity;
import weixin.lottery.service.WeixinGuessRiddleServiceI;

import java.io.Serializable;

/**
 * Created by aa on 2016/1/20.
 */
@Service("weixinGuessRiddleService")
@Transactional
public class WeixinGuessRiddleServiceImpl extends CommonServiceImpl implements WeixinGuessRiddleServiceI {

    @Autowired
    private JdbcDao jdbcDao;

    public <T> void delete(T entity) {
        super.delete(entity);
        // ִ��ɾ���������õ�sql��ǿ
        this.doDelSql((WeixinGuessRiddleEntity) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        // ִ�������������õ�sql��ǿ
        this.doAddSql((WeixinGuessRiddleEntity) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        // ִ�и��²������õ�sql��ǿ
        this.doUpdateSql((WeixinGuessRiddleEntity) entity);
    }

    /**
     * Ĭ�ϰ�ť-sql��ǿ-��������
     *
     * @param id
     * @return
     */
    public boolean doAddSql(WeixinGuessRiddleEntity t) {
        return true;
    }

    /**
     * Ĭ�ϰ�ť-sql��ǿ-���²���
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(WeixinGuessRiddleEntity t) {
        return true;
    }

    /**
     * Ĭ�ϰ�ť-sql��ǿ-ɾ������
     *
     * @param id
     * @return
     */
    public boolean doDelSql(WeixinGuessRiddleEntity t) {
        return true;
    }
    public Integer getCount(String sql) {
        return jdbcDao.countByJdbc(sql, null);
    }
}

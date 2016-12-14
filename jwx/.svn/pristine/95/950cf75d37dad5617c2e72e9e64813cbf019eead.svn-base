package weixin.lottery.service;

import org.jeecgframework.core.common.service.CommonService;
import weixin.lottery.entity.WeixinGuessRiddleEntity;

import java.io.Serializable;

/**
 * Created by aa on 2016/1/20.
 */
public interface WeixinGuessRiddleServiceI extends CommonService {
    public <T> void delete(T entity);

    public <T> Serializable save(T entity);

    public <T> void saveOrUpdate(T entity);

    /**
     * Ĭ�ϰ�ť-sql��ǿ-��������
     *
     * @param id
     * @return
     */
    public boolean doAddSql(WeixinGuessRiddleEntity t);

    /**
     * Ĭ�ϰ�ť-sql��ǿ-���²���
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(WeixinGuessRiddleEntity t);

    /**
     * Ĭ�ϰ�ť-sql��ǿ-ɾ������
     *
     * @param id
     * @return
     */
    public boolean doDelSql(WeixinGuessRiddleEntity t);

    public Integer getCount(String sql);
}

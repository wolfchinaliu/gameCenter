package weixin.tenant.service;

import net.sf.json.JSONObject;
import org.jeecgframework.core.common.service.CommonService;
import weixin.tenant.entity.WeixinMerchantCoverAreaEntity;

import java.io.Serializable;

/**
 * Created by aa on 2015/12/1.
 */
public interface WeixinMerchantCoverAreaServiceI extends CommonService {
    public <T> void delete(T entity);

    public <T> Serializable save(T entity);

    public <T> void saveOrUpdate(T entity);


    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(WeixinMerchantCoverAreaEntity t);


    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(WeixinMerchantCoverAreaEntity t);

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(WeixinMerchantCoverAreaEntity t);

    JSONObject getCoverAndLocation(String phoneNumber, String accountId);

    JSONObject getCoverArea(String accountId);
}

package weixin.tenant.service.impl;

import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.liuliangbao.util.HttpUtil;
import weixin.tenant.entity.WeixinMerchantCoverAreaEntity;
import weixin.tenant.service.WeixinMerchantCoverAreaServiceI;

import java.io.Serializable;

/**
 * Created by aa on 2015/12/1.
 */
@Service("WeixinMerchantCoverAreaService")
@Transactional
public class WeixinMerchantCoverAreaServiceImpl extends CommonServiceImpl implements WeixinMerchantCoverAreaServiceI {

    public <T> void delete(T entity) {
        super.delete(entity);
        //执行删除操作配置的sql增强
        this.doDelSql((WeixinMerchantCoverAreaEntity) entity);
    }


    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        //执行新增操作配置的sql增强
        this.doAddSql((WeixinMerchantCoverAreaEntity) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((WeixinMerchantCoverAreaEntity) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(WeixinMerchantCoverAreaEntity t) {
        return true;
    }


    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(WeixinMerchantCoverAreaEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(WeixinMerchantCoverAreaEntity t) {
        return true;
    }


    @Override
    public JSONObject getCoverAndLocation(String phoneNumber, String accountId) {
        String getCoverAndLocation = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/getCoverAndLocation";
        JSONObject params = new JSONObject();
        params.element("phoneNumber", phoneNumber);
        params.element("id", accountId);
        return HttpUtil.httpPost(getCoverAndLocation, params, false);
    }


    @Override
    public JSONObject getCoverArea(String accountId) {
        String coverArea = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/getCoverArea";
        JSONObject params = new JSONObject();
        params.element("id", accountId);
        return HttpUtil.httpPost(coverArea, params, false);
    }

}

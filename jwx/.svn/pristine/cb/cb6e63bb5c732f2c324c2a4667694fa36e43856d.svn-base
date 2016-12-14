package weixin.report.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.liuliangbao.jsonbean.MerchantInfoBean;
import weixin.liuliangbao.util.HttpUtil;
import weixin.report.model.UserGiveFlowEntity;
import weixin.report.service.UserGiveFlowServiceI;
import weixin.tenant.service.WeixinMerchantCoverAreaServiceI;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.MessageFormat;

/**
 * Created by 晓春 on 2016/3/16.
 */
@Service("userGiveFlowService")
@Transactional
public class UserGiveFlowServiceImpl extends CommonServiceImpl implements UserGiveFlowServiceI {
    public static final transient Logger LOGGER = LoggerFactory.getLogger(UserGiveFlowServiceImpl.class);

    @Resource
    private WeixinAccountServiceI weixinAccountService;
    @Resource
    private WeixinMerchantCoverAreaServiceI weixinMerchantCoverAreaService;

    public <T> void delete(T entity) {
        super.delete(entity);
        //执行删除操作配置的sql增强
        this.doDelSql((UserGiveFlowEntity) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        //执行新增操作配置的sql增强
        this.doAddSql((UserGiveFlowEntity) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((UserGiveFlowEntity) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(UserGiveFlowEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(UserGiveFlowEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(UserGiveFlowEntity t) {
        return true;
    }

    @Override
    public String giveUserFlow(String openId, String phoneNumber, String accountId, String operateType) {
        JSONObject resultObject = this.weixinMerchantCoverAreaService.getCoverAndLocation(phoneNumber, accountId);
        LOGGER.info(String.format("获取手机号[%s], 商家Id:[%s], 操作类型:[%s]:返回结果: %s", phoneNumber, accountId, operateType, resultObject));
        if (null == resultObject) {
            return null;
        }
        String statusCode = resultObject.optString("code");
        if (!"200".equals(statusCode)) {
            return null;
        }
        Gson gson = new Gson();
        String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/QueryFlowRule";
        JSONObject myJson = new JSONObject();
        myJson.accumulate("id", accountId);
        myJson.accumulate("opreateType", operateType);
        JSONObject contentBinding = HttpUtil.httpPost(url, myJson, false);
        String reStrBinding = gson.toJson(contentBinding);
        MerchantInfoBean merchantInfoBean = gson.fromJson(reStrBinding, new TypeToken<MerchantInfoBean>() { }.getType());

        if (null == merchantInfoBean || CollectionUtils.isEmpty(merchantInfoBean.getData())) {
            LOGGER.info(String.format("首次关注流量赠送:商户[%s]尚未设置流量赠送规则", accountId));
            return null;
        }
        MerchantInfoBean.DataEntity merchantEntity = merchantInfoBean.getData().get(0);
        //更新商户或者活动剩余流量, 更新用户账户流量余额
        String url2 = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/UpdateFlowAndAddFlowRecord";
        myJson.element("phoneNumber", phoneNumber);
        myJson.element("flowValue", merchantEntity.getCountryFlowValue());
        myJson.element("id", accountId);
        myJson.element("opreateType", operateType);
        myJson.element("openid", openId);
        myJson.element("flowType", merchantEntity.getFlowType());
        resultObject = HttpUtil.httpPost(url2, myJson, false);
        if (null == resultObject || !"200".equals(resultObject.optString("code"))) {
            LOGGER.warn("商家[" + accountId + "]的用户[" + openId + "]触发了" + operateType + "动作,但在充值时,出现:" + resultObject);
            return null;
        }
        WeixinAccountEntity accountEntity = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", accountId);
        return MessageFormat.format("感谢您{0}{1}，您已获得{2}赠送您的{3}M{4}，点击公众号流量相关菜单进行查看。", operateType, accountEntity.getAccountname(), accountEntity.getAccountname(), merchantEntity.getCountryFlowValue(), merchantEntity.getFlowType());
    }

}

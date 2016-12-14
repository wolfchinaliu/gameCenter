package weixin.report.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.liuliangbao.jsonbean.Update;
import weixin.liuliangbao.util.HttpUtil;
import weixin.report.model.UserGiveFlowEntity;
import weixin.report.model.UserGiveFlowHistoryEntity;
import weixin.report.service.UserGiveFlowHistoryServiceI;
import weixin.report.service.UserGiveFlowServiceI;
import weixin.util.DateUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by guoliang on 2016/4/12.
 */
@Service("userGiveFlowHistoryService")
@Transactional
public class UserGiveFlowHistoryServiceImpl extends CommonServiceImpl implements UserGiveFlowHistoryServiceI {
    private static final transient Logger LOGGER = Logger.getLogger(UserGiveFlowHistoryServiceImpl.class);

    @Resource
    private UserGiveFlowServiceI userGiveFlowService;

    public <T> void delete(T entity) {
        super.delete(entity);
        //执行删除操作配置的sql增强
        this.doDelSql((UserGiveFlowHistoryEntity) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        //执行新增操作配置的sql增强
        this.doAddSql((UserGiveFlowHistoryEntity) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((UserGiveFlowHistoryEntity) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param entity
     * @return
     */
    public boolean doAddSql(UserGiveFlowHistoryEntity entity) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param entity
     * @return
     */
    public boolean doUpdateSql(UserGiveFlowHistoryEntity entity) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param entity
     * @return
     */
    public boolean doDelSql(UserGiveFlowHistoryEntity entity) {
        return true;
    }

    /**
     * 定时删除用户赠送的流量记录到历史纪录表中
     *
     * @param start
     * @param end
     * @return
     */
    @Override
    public void removeExpiredUserFlowGiveRecords(Date start, Date end) {
        String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/releasePreHoldResource";
        Gson gson = new Gson();
        JSONObject myJson = new JSONObject();
        myJson.element("start", DateUtils.datetimeFormat.format(start));
        myJson.element("end", DateUtils.datetimeFormat.format(end));
         JSONObject contentFlow = HttpUtil.httpPost(url, myJson, false);
        String strFlow = gson.toJson(contentFlow);
        Type type = new TypeToken<Update>() { }.getType();
        Update update = gson.fromJson(strFlow, type);
        if (null != update) {
            LOGGER.debug(MessageFormat.format("code={0}, message={1}", update.getCode(), update.getMessage()));
        }
    }
}

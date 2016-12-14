package weixin.advertisement.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;

import weixin.advertisement.entity.WeixinAdMaterialEntity;
import weixin.advertisement.service.MaterialServiceI;
import weixin.util.DataDictionaryUtil;
import weixin.util.DataDictionaryUtil.AdPublishStatus;

@Service()
public class MaterialServiceImpl extends CommonServiceImpl implements MaterialServiceI {
    public static final transient Logger LOGGER = Logger.getLogger(MaterialServiceImpl.class);

    @Override
    public void commit(String id) {
        WeixinAdMaterialEntity entity = this.get(WeixinAdMaterialEntity.class, id);
        entity.setStatus(DataDictionaryUtil.MaterialStatus.commit.getCode());
        entity.setOperateDate(new Date());
        this.updateEntitie(entity);
        LOGGER.info(entity);
    }

}

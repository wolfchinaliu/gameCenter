package weixin.integrate.job;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weixin.integrate.controller.IntegrateController;
import weixin.integrate.service.IntegrateService;

@Service("weixinBusinessJob")
public class WeixinBusinessJob {
    @Autowired
    private IntegrateService integrateService;
    private static final Logger logger = Logger.getLogger(IntegrateController.class);

    public void setBusinessTimeout() {
        String timeout = ResourceUtil.getCuConfigByName("integrate.business.userreceive.timeout");
        Integer seconds = NumberUtils.toInt(timeout, 0);
        if (seconds <= 0) {
            logger.error("integrate.business.userreceive.timeout error");
            return;
        }
        this.integrateService.setBusinessFailByTimeout(seconds);
    }
}
package weixin.advertisement.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;

import weixin.advertisement.entity.WeixinAdvertisementEntity;

public interface AdvertisementServiceI extends CommonService{

    public List<Map<String, Object>> getConflictAd(String acctId, String position, Date begin, Date end);

    public Map<String, Object> getPublishedAd(String merchantId, String position);

    public void updateAdMerchant(String id, List<String> merchantIdList);

    public List<WeixinAdvertisementEntity> findToStartAd();

    public List<WeixinAdvertisementEntity> findToFinishAd();
    
    public void terminalAd(String publishId,String remarks);
}
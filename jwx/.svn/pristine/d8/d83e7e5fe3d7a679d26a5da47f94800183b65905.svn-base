package weixin.advertisement.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weixin.advertisement.entity.WeixinAdvertisementEntity;
import weixin.advertisement.service.AdvertisementServiceI;
import weixin.util.DataDictionaryUtil.AdPublishStatus;

@Service("advertisementJob")
public class AdvertisementJob {
    @Autowired
    private AdvertisementServiceI adService;

    public void startAd() {
        List<WeixinAdvertisementEntity> adList = this.adService.findToStartAd();
        if (adList != null && !adList.isEmpty()) {
            for (WeixinAdvertisementEntity ad : adList) {
                ad.setStatus(AdPublishStatus.publish.getCode());
            }
            this.adService.batchSave(adList);
        }
    }

    public void finishAd() {
        List<WeixinAdvertisementEntity> adList = this.adService.findToFinishAd();
        if (adList != null && !adList.isEmpty()) {
            for (WeixinAdvertisementEntity ad : adList) {
                ad.setStatus(AdPublishStatus.finish.getCode());
                ad.setFinishDate(ad.getPeriodEnd());
            }
            this.adService.batchSave(adList);
        }
    }
}

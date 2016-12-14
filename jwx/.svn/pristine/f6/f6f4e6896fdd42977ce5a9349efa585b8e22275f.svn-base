package weixin.advertisement.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.stereotype.Service;

import weixin.advertisement.entity.WeixinAdMerchantEntity;
import weixin.advertisement.entity.WeixinAdvertisementEntity;
import weixin.advertisement.service.AdvertisementServiceI;
import weixin.util.DataDictionaryUtil.AdPublishStatus;
import weixin.util.DataDictionaryUtil.UrlType;

@Service()
public class AdvertisementServiceImpl extends CommonServiceImpl implements AdvertisementServiceI {
    private static String mediaUrlPrefix = ResourceUtil.getMediaUrlPrefix();
    @Override
    public List<Map<String, Object>> getConflictAd(String merchantId, String position, Date begin, Date end) {
        return getEffectiveAd(merchantId,position,begin,end);
    }

    // 查找某商户下，某页面有效的广告列表
    private List<Map<String, Object>> getEffectiveAd(String merchantId, String position,Date begin,Date end) {
        String sql = "select a.id,a.title,a.position,am.merchantId,a.period_begin,a.period_end from weixin_advertisement a  join weixin_ad_merchant am on a.id=am.adId left join weixin_ad_material m on m.id=a.materialId "
                + "where NOW() < a.period_end and am.merchantId=? and a.position=? and not (a.period_begin> ? or a.period_end < ?) and a.status <> ? and a.status <> ?";
        List<Map<String,Object>> ads = this.commonDao.findForJdbc(sql, merchantId,position,end,begin,AdPublishStatus.active_terminate.getCode(),AdPublishStatus.passive_terminate.getCode());
        return ads;
    }

    @Override
    public Map<String, Object> getPublishedAd(String merchantId, String position) {
        String sql = "select a.id , a.materialId,m.pic,m.url_type,m.url,m.content from weixin_advertisement a  join weixin_ad_merchant am on a.id=am.adId left join weixin_ad_material m on m.id=a.materialId "
                + "where am.merchantId=? and a.position=? and a.status= '"+AdPublishStatus.publish.getCode()+"'";
        List<Map<String,Object>> ads = this.commonDao.findForJdbc(sql, merchantId,position);
        Map<String,Object> ad=null;
        if(ads==null || ads.size()==0){
            ad = new HashMap<>();
            ad.put("pic",ResourceUtil.getResourcePath()+"/images/banner/02.jpg");
            ad.put("adDetailUrl", "#");
        }else {
            ad = ads.get(0);
            String urlType = MapUtils.getString(ad, "url_type");
            String pic = MapUtils.getString(ad, "pic");
            String adDetailUrl = null;
            if(StringUtils.equals(urlType, UrlType.inner.getCode())){
                adDetailUrl = "adAction.do?adDetail&id="+MapUtils.getString(ad, "materialId");
            } else {
                adDetailUrl = MapUtils.getString(ad, "url");
            }
            ad.put("adDetailUrl", adDetailUrl);
            pic = mediaUrlPrefix + "/" + pic;
            ad.put("pic", pic);
        }
        return ad;
    }

    @Override
    public void updateAdMerchant(String adId, List<String> merchantIdList) {
        List<WeixinAdMerchantEntity> currAdMerchant = this.commonDao.findByProperty(WeixinAdMerchantEntity.class, "adId", adId);
        this.commonDao.deleteAllEntitie(currAdMerchant);
        List<WeixinAdMerchantEntity> newAdMerchant = new ArrayList<WeixinAdMerchantEntity>();
        if(merchantIdList!=null && !merchantIdList.isEmpty()){
            for(String merchantId:merchantIdList){
                WeixinAdMerchantEntity entity = new WeixinAdMerchantEntity();
                entity.setAdId(adId);
                entity.setMerchantId(merchantId);
                newAdMerchant.add(entity);
            }
        }
        this.commonDao.batchSave(newAdMerchant);
    }

    @Override
    public List<WeixinAdvertisementEntity> findToStartAd() {
        String hql = "from WeixinAdvertisementEntity where status = ? and periodBegin <= ?";
        List<WeixinAdvertisementEntity> adList = this.commonDao.findHql(hql, AdPublishStatus.nopublish.getCode(),new Date());
        return adList;
    }

    @Override
    public List<WeixinAdvertisementEntity> findToFinishAd() {
        String hql = "from WeixinAdvertisementEntity where status = ? and periodEnd <= ?";
        List<WeixinAdvertisementEntity> adList = this.commonDao.findHql(hql, AdPublishStatus.publish.getCode(),new Date());
        return adList;
    }

    @Override
    public void terminalAd(String publishId,String remarks) {
        String hql = "update WeixinAdvertisementEntity set finishDate=:finishDate , status=:newstatus , remarks=:remarks where (status=:oldstatus1 or  status=:oldstatus2) and publishId=:publishId ";
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("finishDate", new Date());
        param.put("newstatus", AdPublishStatus.passive_terminate.getCode());
        param.put("remarks", remarks==null?"":remarks);
        param.put("oldstatus1", AdPublishStatus.nopublish.getCode());
        param.put("oldstatus2", AdPublishStatus.publish.getCode());
        param.put("publishId", publishId);
        this.commonDao.executeHql(hql, param);
    }
}
package weixin.cms.cmsdata.impl;

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.util.ApplicationContextUtil;

import weixin.cms.cmsdata.CmsDataCollectI;
import weixin.cms.common.CmsConstant;
import weixin.cms.common.CmsDataContent;
import weixin.cms.entity.AdEntity;
import weixin.cms.entity.CmsMenuEntity;
import weixin.cms.entity.WeixinCmsSiteEntity;
import weixin.cms.service.AdServiceI;
import weixin.cms.service.CmsMenuServiceI;
import weixin.cms.service.WeixinCmsSiteServiceI;

/**
 * CMS首页数据收集器
 * @author zhangdaihao
 *
 */
public class CmsIndexDataCollect implements CmsDataCollectI {

	@Override
	public void collect(Map<String, String> params) {
		
		//注意其他方法调用只能写在里面
		AdServiceI adService = (AdServiceI) ApplicationContextUtil.getContext().getBean("adService");
		CmsMenuServiceI cmsMenuService = (CmsMenuServiceI) ApplicationContextUtil.getContext().getBean("cmsMenuService");
		WeixinCmsSiteServiceI weixinCmsSiteService = (WeixinCmsSiteServiceI) ApplicationContextUtil.getContext().getBean("weixinCmsSiteService");
		
		//广告
		List<AdEntity> adList = adService.list(params);
		CmsDataContent.put(CmsConstant.CMS_DATA_LIST_AD, adList);
		
		//菜单
		List<CmsMenuEntity> menuList = cmsMenuService.list(params);
		CmsDataContent.put(CmsConstant.CMS_DATA_LIST_MENU, menuList);
		String res = CmsConstant.CMS_ROOT_PATH + "/" + params.get(CmsConstant.CMS_STYLE_NAME);
		
		//官网名称
		String accountid = params.get("accountid");
		WeixinCmsSiteEntity  weixinCmsSiteEntity = weixinCmsSiteService.findUniqueByProperty(WeixinCmsSiteEntity.class, "accountid", params.get("accountid"));
		
		CmsDataContent.put(CmsConstant.CMS_DATA_STR_TITLE, weixinCmsSiteEntity.getSiteName());
		
		//资源路径
		CmsDataContent.put(CmsConstant.BASE, res);
	}

}

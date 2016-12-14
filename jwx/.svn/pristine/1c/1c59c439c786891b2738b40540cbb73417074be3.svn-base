package weixin.advertisement.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.annotation.DataSourceSwitch;
import org.jeecgframework.core.aop.DataSourceSwitcher;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.advertisement.entity.WeixinAdMaterialEntity;
import weixin.advertisement.entity.WeixinAdMerchantEntity;
import weixin.advertisement.entity.WeixinAdvertisementEntity;
import weixin.advertisement.service.AdvertisementServiceI;
import weixin.advertisement.service.MaterialServiceI;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.service.WeixinAcctServiceI;
import weixin.util.DataDictionaryUtil.AdPublishStatus;
import weixin.util.DataDictionaryUtil.MaterialStatus;
import weixin.util.DateUtils;

@Controller
@RequestMapping("/advertisementController")
@SuppressWarnings("all")
public class AdvertisementController {
    @Autowired
    private AdvertisementServiceI adService;
    @Autowired
    private MaterialServiceI materialService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private WeixinAcctServiceI acctService;

    private static String mediaUrlPrefix = ResourceUtil.getMediaUrlPrefix();

    /**
     * 跳转发布广告页面
     */
    @RequestMapping(params = "goList")
    public ModelAndView goList() {
        return new ModelAndView("weixin/advertisement/ad-list");
    }

    /**
     * 查询素材列表
     */
    @RequestMapping(params = "query")
    @ResponseBody
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void query(WeixinAdvertisementEntity entity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        String title = request.getParameter("title");
        String materialType = request.getParameter("materialType");
        String position = request.getParameter("position");
        String status = request.getParameter("status");
        StringBuilder sql = new StringBuilder(
                "select a.id,a.title,m.title materialTitle,m.pic,m.material_type materialType,m.url,a.position,a.period_begin periodBegin,a.period_end periodEnd,a.finish_date finishDate,a.status from weixin_advertisement a  left join weixin_ad_material m on m.id=a.materialId where 1=1 ");
        if (StringUtils.isNotBlank(title)) {
            sql.append(" and a.title like '%" + StringUtils.trim(title) + "%'");
        }
        if (StringUtils.isNotBlank(materialType)) {
            sql.append(" and m.material_type = '" + StringUtils.trim(materialType) + "'");
        }
        if (StringUtils.isNotBlank(position)) {
            sql.append(" and a.position = '" + StringUtils.trim(position) + "'");
        }
        if (StringUtils.isNotBlank(status)) {
            sql.append(" and a.status = '" + StringUtils.trim(status) + "'");
        }
        sql.append(" and a.publishId = '" + ResourceUtil.getWeiXinAccount().getAcctId() + "' ");
        String countSql = "select count(1) from (" + sql.toString() + ") c";
        Long count = this.adService.getCountForJdbc(countSql);
        List<Map<String, Object>> data = this.adService.findForJdbc(sql.toString(), dataGrid.getPage(), dataGrid.getRows());
        if (CollectionUtils.isNotEmpty(data)) {
            for (Object o : data) {
                Map<String, Object> ma = (Map<String, Object>) o;
                ma.put("pic", mediaUrlPrefix + "/" + ma.get("pic"));
                if (MapUtils.getObject(ma, "finishDate") == null) {
                    ma.put("finishDate", "");
                }
            }
        }
        dataGrid.setResults(data);
        dataGrid.setTotal(count.intValue());
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "goAdd")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public ModelAndView goAdd() {
        ModelAndView mv = new ModelAndView("weixin/advertisement/ad-add");
        mv.addObject("currAcct", this.systemService.get(WeixinAcctEntity.class, ResourceUtil.getSessionUserName().getTenantId()));
        mv.addObject("merchants", "[]");
        return mv;
    }

    @RequestMapping(params = "goUpdate")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public ModelAndView goUpdate(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ModelAndView mav = new ModelAndView("weixin/advertisement/ad-add");
        WeixinAdvertisementEntity entity = this.adService.get(WeixinAdvertisementEntity.class, id);
        WeixinAcctEntity currAcct = this.systemService.get(WeixinAcctEntity.class, ResourceUtil.getSessionUserName().getTenantId());
        List<WeixinAdMerchantEntity> merchantList = this.adService.findByProperty(WeixinAdMerchantEntity.class, "adId", entity.getId());
        if (merchantList != null && !merchantList.isEmpty()) {
            List<String> ids = new ArrayList<String>();
            for (WeixinAdMerchantEntity e : merchantList) {
                ids.add(e.getMerchantId());
            }

            mav.addObject("merchants", this.toComboboxValueFormat(ids));
        }

        mav.addObject("currAcct", currAcct);
        mav.addObject("ad", entity);
        return mav;
    }

    @RequestMapping(params = "viewMerchant")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public ModelAndView viewMerchant(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ModelAndView mav = new ModelAndView("weixin/advertisement/ad-viewMerchant");
        WeixinAdvertisementEntity entity = this.adService.get(WeixinAdvertisementEntity.class, id);
        WeixinAcctEntity currAcct = this.systemService.get(WeixinAcctEntity.class, ResourceUtil.getSessionUserName().getTenantId());
        List<WeixinAdMerchantEntity> adMerchantList = this.adService.findByProperty(WeixinAdMerchantEntity.class, "adId", entity.getId());
        List<Map<String,String>> merchantList = new ArrayList<Map<String,String>>();
        if(adMerchantList!=null && !adMerchantList.isEmpty()){
            for(WeixinAdMerchantEntity e:adMerchantList){
                Map<String,String> merchant = new HashMap<String,String>();
                merchant.put("id", e.getMerchantId());
                merchant.put("name", this.acctService.get(WeixinAcctEntity.class, e.getMerchantId()).getAcctName());
                merchantList.add(merchant);
            }
        }
        mav.addObject("currAcct", currAcct);
        mav.addObject("ad", entity);
        mav.addObject("merchantList", merchantList);
        return mav;
    }

    private String toComboboxValueFormat(List data) {
        Iterator it = data.iterator();
        if (data == null || data.isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (;;) {
            Object e = it.next();
            sb.append("'" + e + "'");
            if (!it.hasNext()) {
                sb.append("]").toString();
                break;
            }
            sb.append(",");
        }
        return sb.toString();
    }

    @RequestMapping(params = "terminate")
    public AjaxJson terminate(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        String id = request.getParameter("id");
        WeixinAdvertisementEntity ad = this.adService.get(WeixinAdvertisementEntity.class, id);
        if (!AdPublishStatus.publish.getCode().equals(ad.getStatus())) {
            j.setSuccess(false);
            j.setMsg("只能终止正在发布中的广告");
            return j;
        }
        ad.setStatus(AdPublishStatus.active_terminate.getCode());
        ad.setFinishDate(new Date());
        this.adService.saveOrUpdate(ad);

        j.setSuccess(true);
        j.setMsg("终止" + ad.getTitle() + "成功");
        return j;
    }

    @RequestMapping(params = "del")
    public AjaxJson del(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        String id = request.getParameter("id");
        WeixinAdvertisementEntity ad = this.adService.get(WeixinAdvertisementEntity.class, id);
        if (!AdPublishStatus.nopublish.getCode().equals(ad.getStatus())) {
            j.setSuccess(false);
            j.setMsg("只能删除未发布的广告");
            return j;
        }
        this.adService.delete(ad);
        j.setSuccess(true);
        j.setMsg("删除" + ad.getTitle() + "成功");
        return j;
    }

    @RequestMapping(params = "addOrUpdate")
    @ResponseBody
    public AjaxJson addOrUpdate(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        AjaxJson j = new AjaxJson();
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String materialId = request.getParameter("materialId");
        String position = request.getParameter("position");
        String periodBegin = request.getParameter("periodBegin");
        String periodEnd = request.getParameter("periodEnd");
        String[] merchants = request.getParameterValues("merchants");
        WeixinAdvertisementEntity ad = null;
        if (StringUtils.isBlank(id)) {
            ad = new WeixinAdvertisementEntity();
        } else {
            ad = this.adService.get(WeixinAdvertisementEntity.class, id);
            if (!AdPublishStatus.nopublish.getCode().equals(ad.getStatus())) {
                j.setSuccess(false);
                j.setMsg("只能编辑未发布的广告");
                return j;
            }
        }

        WeixinAdMaterialEntity material = this.materialService.get(WeixinAdMaterialEntity.class, materialId);
        if (material == null) {
            j.setSuccess(false);
            j.setMsg("无效的素材");
            return j;
        }
        if (!MaterialStatus.audit_pass.getCode().equals(material.getStatus())) {
            j.setSuccess(false);
            j.setMsg("素材未审核通过");
            return j;
        }
        if (StringUtils.isBlank(title)) {
            j.setSuccess(false);
            j.setMsg("请输入广告名称");
            return j;
        }
        if (StringUtils.isBlank(position)) {
            j.setSuccess(false);
            j.setMsg("请选择广告投放页面");
            return j;
        }
        if (StringUtils.isBlank(periodBegin)) {
            j.setSuccess(false);
            j.setMsg("请选择广告投放开始时间");
            return j;
        }
        if (StringUtils.isBlank(periodEnd)) {
            j.setSuccess(false);
            j.setMsg("请选择广告投放结束时间");
            return j;
        }
        Date begin = null;
        try {
            begin = DateUtils.datetimeFormat.parse(periodBegin + " 00:00:00");
        } catch (ParseException e) {
            j.setSuccess(false);
            j.setMsg("开始时间格式有误");
            return j;
        }

        Date end = null;
        try {
            end = DateUtils.datetimeFormat.parse(periodEnd + " 23:59:59");
        } catch (ParseException e) {
            j.setSuccess(false);
            j.setMsg("开始时间格式有误");
            return j;
        }
        if (begin.compareTo(end) >= 0) {
            j.setSuccess(false);
            j.setMsg("结束时间不能晚于开始时间");
            return j;
        }
        Date now = new Date();
        if (end.compareTo(now) < 0) {
            j.setSuccess(false);
            j.setMsg("结束时间不能早于当前时间");
            return j;
        }
        WeixinAcctEntity currAcct = this.systemService.get(WeixinAcctEntity.class, ResourceUtil.getWeiXinAccount().getAcctId());
        List<String> merchantIdList = new ArrayList<String>();
        if (StringUtils.equals(currAcct.getAdAgency(), "0")) {
            // 非广告代理商，只能给自己发布广告
            List<Map<String, Object>> conflictList = this.adService.getConflictAd(ResourceUtil.getWeiXinAccount().getAcctId(), position, begin, end);
            boolean conflict = false;
            if (!(conflictList == null || conflictList.isEmpty())) {
                j.setSuccess(false);
                StringBuilder s = new StringBuilder("时间冲突(" + title + ")，请修改发布时间");
                for (Map<String, Object> conMap : conflictList) {
                    if(StringUtils.isNotBlank(id) && conMap.get("id").equals(id)){
                        continue;
                    }
                    conflict=true;
                    s.append("<p>广告名称:" + conMap.get("title") + "</br>开始时间:" + DateUtils.datetimeFormat.format(conMap.get("period_begin")) + "</br>结束时间:"
                            + DateUtils.datetimeFormat.format(conMap.get("period_end")) + "</p>");
                }
                if(conflict){
                    j.setMsg(s.toString());
                    return j;
                }
            }
            merchantIdList.add(currAcct.getId());
        } else {
            if(merchants==null || merchants.length==0){
                j.setSuccess(false);
                j.setMsg("请选择要投放广告的商户");
                return j;
            }
            List<WeixinAcctEntity> subAcct = this.acctService.getSubAcctForAd(ResourceUtil.getWeiXinAccount().getAcctId());
            List<String> subAcctId = new ArrayList<String>();
            if(subAcct!=null && !subAcct.isEmpty()){
                for(WeixinAcctEntity e: subAcct){
                    subAcctId.add(e.getId());
                }
            }
            subAcctId.add(ResourceUtil.getWeiXinAccount().getAcctId());
            for(String merchantId:merchants){
                if(!subAcctId.contains(merchantId)){
                    j.setSuccess(false);
                    j.setMsg("投放商户中包含非法商户，请刷新页面重新进行业务");
                    return j;
                }
            }

            // 广告代理商，可以给下级选择了出售广告位的商户广告位上发布广告
            for(String merchantId:merchants){
                List<Map<String, Object>> conflictList = this.adService.getConflictAd(merchantId, position, begin, end);
                if (!(conflictList == null || conflictList.isEmpty())) {
                    boolean conflict = false;
                    j.setSuccess(false);
                    WeixinAcctEntity acct = this.acctService.get(WeixinAcctEntity.class, merchantId);
                    StringBuilder s = new StringBuilder(acct.getAcctName()+"时间冲突(" + title + ")，请修改发布时间");
                    for (Map<String, Object> conMap : conflictList) {
                        if(StringUtils.isNotBlank(id) && conMap.get("id").equals(id)){
                            continue;
                        }
                        conflict=true;
                        s.append("<p>广告名称:" + conMap.get("title") + "</br>开始时间:" + DateUtils.datetimeFormat.format(conMap.get("period_begin")) + "</br>结束时间:"
                                + DateUtils.datetimeFormat.format(conMap.get("period_end")) + "</p>");
                    }
                    if(conflict){
                        j.setMsg(s.toString());
                        return j;
                    }
                }
            }

            merchantIdList.addAll(Arrays.asList(merchants));
        }

        ad.setMaterialId(materialId);
        ad.setPosition(position);
        ad.setPeriodBegin(begin);
        ad.setPeriodEnd(end);
        ad.setTitle(title);
        ad.setCreateDate(now);
        ad.setPublishId(ResourceUtil.getWeiXinAccount().getAcctId());
        if (begin.compareTo(now) <= 0) {
            ad.setStatus(AdPublishStatus.publish.getCode());
        } else {
            ad.setStatus(AdPublishStatus.nopublish.getCode());
        }
        WeixinAdMerchantEntity adMerchant = new WeixinAdMerchantEntity();
        this.adService.save(ad);

        this.adService.updateAdMerchant(ad.getId(), merchantIdList);
        j.setSuccess(true);
        j.setMsg("添加成功");
        return j;
    }
}
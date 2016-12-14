package weixin.advertisement.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.FlushMode;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.annotation.DataSourceSwitch;
import org.jeecgframework.core.aop.DataSourceSwitcher;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import weixin.advertisement.entity.WeixinAdMaterialEntity;
import weixin.advertisement.service.MaterialServiceI;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.service.WeixinAcctServiceI;
import weixin.util.DataDictionaryUtil;
import weixin.util.DataDictionaryUtil.AdPublishStatus;
import weixin.util.DataDictionaryUtil.MaterialStatus;
import weixin.util.DataDictionaryUtil.MaterialType;
import weixin.util.DataDictionaryUtil.UrlType;

/**
 * @author dyt
 * @version V1.0
 * @Title: Controller
 * @Description: 广告
 * @date 2014-01-09 21:55:30
 */
@Controller
@RequestMapping("/materialController")
@SuppressWarnings("all")
public class MaterialController extends BaseController {
    @Autowired
    private MaterialServiceI materialService;
    @Autowired
    private WeixinAcctServiceI acctService;
    private static String mediaUrlPrefix = ResourceUtil.getMediaUrlPrefix();

    /**
     * 跳转素材列表页面
     */
    @RequestMapping(params = "goList")
    public ModelAndView goList() {
        return new ModelAndView("weixin/advertisement/material-list");
    }

    /**
     * 查询素材列表
     */
    @RequestMapping(params = "query")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    @ResponseBody
    public void query(WeixinAdMaterialEntity materialEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WeixinAdMaterialEntity.class, dataGrid);
        //素材名称模糊搜索
        if (StringUtils.isNotBlank(materialEntity.getTitle())) {
        	materialEntity.setTitle("*" + materialEntity.getTitle() + "*");
        }
        String acctId= ResourceUtil.getWeiXinAccount().getAcctId();
        cq.eq("merchantId", acctId==null?" ":acctId);
        HqlGenerateUtil.installHql(cq, materialEntity);
        this.materialService.getDataGridReturn(cq, true);
        List data = cq.getDataGrid().getResults();
        if (CollectionUtils.isNotEmpty(data)) {
            for(Object o :data){
                WeixinAdMaterialEntity ma = (WeixinAdMaterialEntity) o;
                ma.setPic(mediaUrlPrefix + "/" + ma.getPic());
            }
        }
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 跳转审核列表页面
     */
    @RequestMapping(params = "goAuditList")
    public ModelAndView goAuditList() {
        return new ModelAndView("weixin/advertisement/material-auditList");
    }

    /**
     * 查询要审核的素材列表
     */
    @RequestMapping(params = "queryToAudit")
    @ResponseBody
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void queryToAudit(WeixinAdMaterialEntity materialEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WeixinAdMaterialEntity.class, dataGrid);
        if (StringUtils.isBlank(materialEntity.getStatus())) {
            cq.notEq("status", MaterialStatus.nocommit.getCode());
        }
        cq.addOrder("status", SortDirection.asc);
        HqlGenerateUtil.installHql(cq, materialEntity);

        this.materialService.getDataGridReturn(cq, true);
        List data = cq.getDataGrid().getResults();
        if (CollectionUtils.isNotEmpty(data)) {
            for(Object o:data){
                // 设置记录只读。设置flushmode不好使
                this.materialService.getSession().evict(o);
                WeixinAdMaterialEntity ma = (WeixinAdMaterialEntity) o;
                ma.setMerchantName(this.acctService.get(WeixinAcctEntity.class, ma.getMerchantId()).getAcctForName());
                ma.setPic(mediaUrlPrefix + "/" + ma.getPic());
                if(StringUtils.equals(ma.getUrlType(), UrlType.inner.getCode())){
//                    ma.setUrl(url);
                }
            }
        }
        TagUtil.datagrid(response, dataGrid);
        
    }

    /**
     * 跳转审核素材页面
     */
    @RequestMapping(params = "goAudit")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public ModelAndView goAudit(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        WeixinAdMaterialEntity entity = this.materialService.get(WeixinAdMaterialEntity.class, id);
        entity.setMerchantName(this.acctService.get(WeixinAcctEntity.class, entity.getMerchantId()).getAcctForName());
        ModelAndView mav = new ModelAndView("weixin/advertisement/material-audit");
        mav.addObject("material", entity);
        mav.addObject("pic", mediaUrlPrefix + "/" + entity.getPic());
        if(StringUtils.equals(entity.getUrlType(), UrlType.inner.getCode())){
            mav.addObject("url", "adAction.do?adDetail&id="+entity.getId());
            mav.addObject("urlTitle","查看图文");
        } else if(StringUtils.equals(entity.getUrlType(), UrlType.outer.getCode())){
            mav.addObject("url",entity.getUrl());
            mav.addObject("urlTitle",entity.getUrl());
        }
        return mav;
    }

    /**
     * 跳转添加素材页面
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd() {
        ModelAndView mav = new ModelAndView("weixin/advertisement/material-add");
        return mav;
    }

    /**
     * 跳转编辑素材页面
     */
    @RequestMapping(params = "goUpdate")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public ModelAndView goUpdate(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        WeixinAdMaterialEntity entity = this.materialService.get(WeixinAdMaterialEntity.class, id);
        ModelAndView mav = new ModelAndView("weixin/advertisement/material-add");
        mav.addObject("material", entity);
        return mav;
    }

    /**
     * 添加，修改素材
     */
    @RequestMapping(params = "addOrUpdate")
    @ResponseBody
    public AjaxJson addOrUpdate(WeixinAdMaterialEntity entity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        Date now = new Date();
        String audit = request.getParameter("toaudit");
        String id = entity.getId();
        AjaxJson j = new AjaxJson();
        WeixinAdMaterialEntity material =null;
        if (StringUtils.isBlank(id)) {
            material = new WeixinAdMaterialEntity();
        } else {
            material = this.materialService.get(WeixinAdMaterialEntity.class, id);
            
            if(material==null){
                j.setSuccess(false);
                j.setMsg("查询不到相关信息，不能修改(id:"+ id+")");
                return j;
            } else {
                if (MaterialStatus.audit_pass.getCode().equals(material.getStatus())) {
                    j.setSuccess(false);
                    j.setMsg("当前状态不能修改");
                    return j;
                }
            }
        }

        material.setMerchantId(ResourceUtil.getWeiXinAccount().getAcctId());

        material.setStatus(DataDictionaryUtil.MaterialStatus.nocommit.getCode());
        material.setAuditInfo(null);
        material.setCreateDate(now);
        material.setCommitDate(now);
        material.setOperateDate(now);
        material.setPic(entity.getPic());
        material.setUrl(entity.getUrl());
        material.setUrlType(entity.getUrlType());
        material.setMaterialType(entity.getMaterialType());
        material.setContent(entity.getContent());
        material.setTitle(entity.getTitle());
        this.materialService.saveOrUpdate(material);
        if (StringUtils.equals(audit, "true")) {
            this.materialService.commit(material.getId());
        }

        j.setSuccess(true);
        return j;
    }

    /**
     * 删除素材
     */
    @RequestMapping(params = "del", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson del(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        this.materialService.deleteEntityById(WeixinAdMaterialEntity.class, id);

        AjaxJson j = new AjaxJson();
        j.setSuccess(true);
        j.setMsg("删除成功");
        return j;
    }

    @RequestMapping(params = "view")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        WeixinAdMaterialEntity entity = this.materialService.getEntity(WeixinAdMaterialEntity.class, id);

        ModelAndView mav = new ModelAndView("weixin/advertisement/material-view");
        entity.setPic(mediaUrlPrefix + "/" + entity.getPic());
        mav.addObject("material", entity);
        return mav;
    }

    /**
     * 提交审核
     */
    @RequestMapping(params = "commit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson commit(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        AjaxJson j = new AjaxJson();
        WeixinAdMaterialEntity entity = this.materialService.get(WeixinAdMaterialEntity.class, id);
        if (!MaterialStatus.nocommit.getCode().equals(entity.getStatus())) {
            j.setSuccess(false);
            j.setMsg("当前状态不能提交");
            return j;
        }
        this.materialService.commit(id);

        j.setSuccess(true);
        j.setMsg("添加成功");
        return j;
    }

    /**
     * 审核
     */
    @RequestMapping(params = "audit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson audit(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String auditStatus = request.getParameter("auditStatus");
        String auditInfo = request.getParameter("auditInfo");
        if(StringUtils.equals(auditInfo, "简单说明原因")){
            auditInfo=null;
        }
        AjaxJson j = new AjaxJson();
        if(StringUtils.equals(auditStatus, MaterialStatus.audit_fail.getCode())){
            if(StringUtils.isBlank(auditInfo)){
                j.setSuccess(false);
                j.setMsg("请简单说明审核不通过的原因");
                return j;
            }
        }
        WeixinAdMaterialEntity entity = this.materialService.get(WeixinAdMaterialEntity.class, id);
        entity.setStatus(auditStatus);
        entity.setAuditInfo(auditInfo);
        entity.setAuditDate(new Date());
        this.materialService.updateEntitie(entity);
        j.setSuccess(true);
        j.setMsg("审核成功");
        return j;
    }

    /**
     * 已审核通过的素材列表(下拉框使用)
     * @throws IOException 
     */
    @RequestMapping(params = "usefulMaterial", method = RequestMethod.POST)
    @ResponseBody
    public void usefulMaterial(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String merchantId = ResourceUtil.getWeiXinAccount().getAcctId();
        CriteriaQuery cq = new CriteriaQuery(WeixinAdMaterialEntity.class);
        cq.add(Restrictions.eq("merchantId", merchantId));
        cq.add(Restrictions.eq("status", MaterialStatus.audit_pass.getCode()));
        List<WeixinAdMaterialEntity> list = this.materialService.getListByCriteriaQuery(cq, false);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        List materialList = new ArrayList<>();
        for(WeixinAdMaterialEntity e:list){
            Map<String, String> m = new HashMap<String, String>();
            m.put("code", e.getId());
            m.put("name", e.getTitle());
            materialList.add(m);
        }
        String json = gson.toJson(materialList);
        out.write(json);
    }
}
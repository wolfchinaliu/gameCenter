package weixin.iplimit.controller;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.cgform.exception.BusinessException;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import weixin.iplimit.entity.IPLimitEntity;
import weixin.iplimit.service.IPlimitServiceI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by aa on 2016/3/21.
 */
@Controller
@RequestMapping("/ipLimitController")
public class IPlimitController extends BaseController {

    private static final Logger logger = Logger.getLogger(IPlimitController.class);
    @Autowired
    private IPlimitServiceI ipLimitService;

    @Autowired
    private SystemService systemService;

    private String message;

    @RequestMapping(params = "weixinIplimitList")
    public ModelAndView WeixinMainList(HttpServletRequest request) {
        return new ModelAndView("weixin/Iplimit/weixinIplimitList");
    }

    @RequestMapping(params = "mydatagrid")
    @ResponseBody
    public void datagrid(IPLimitEntity ipLimitEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(IPLimitEntity.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ipLimitEntity, request.getParameterMap());

        try {
            //自定义追加查询条件
            cq.eq("acctid", ResourceUtil.getWeiXinAccount().getAcctId());

        } catch (Exception e) {
            throw new org.jeecgframework.core.common.exception.BusinessException(e.getMessage());
        }
        cq.add();
        this.ipLimitService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);

    }


    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(HttpServletRequest request) {
        return new ModelAndView("weixin/Iplimit/addWeixinIplimit");
    }
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(IPLimitEntity ipLimitEntity, HttpServletRequest request) {
        if (StringUtil.isNotEmpty(ipLimitEntity.getId())) {
            ipLimitEntity = ipLimitService.getEntity(IPLimitEntity.class, ipLimitEntity.getId());
            request.setAttribute("ipLimit",ipLimitEntity);
        }
        return new ModelAndView("/weixin/Iplimit/updateWeixinIplimit");
    }


    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(IPLimitEntity ipLimitEntity, HttpServletRequest request) throws BusinessException {

        long startTime = System.currentTimeMillis();
        StringBuffer buffer = new StringBuffer();
        buffer.append("ipLimitController_doUpdate_begin:_");
        try {
            AjaxJson j = new AjaxJson();
            message = "Ip名单更新成功";
            if (ipLimitEntity.getId()==null) {
                ipLimitEntity.setAcctid(ResourceUtil.getWeiXinAccount().getAcctId());
                ipLimitEntity.setCreateDate(new Date());
                ipLimitService.save(ipLimitEntity);
            } else {
                ipLimitEntity.setOperateDate(new Date());
                IPLimitEntity t = ipLimitService.get(IPLimitEntity.class, ipLimitEntity.getId());
                try {
                    MyBeanUtils.copyBeanNotNull2Bean(ipLimitEntity, t);
                } catch (Exception e) {
                    e.printStackTrace();
                    message = "Ip名单更新失败";
                    throw new BusinessException(e.getMessage());
                }
                ipLimitService.saveOrUpdate(t);
            }

            long endTime = System.currentTimeMillis();
            buffer.append("_time:_" + (endTime - startTime) + "ms");
            logger.info(buffer.toString());
            j.setMsg(message);
            return j;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(IPLimitEntity ipLimitEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        ipLimitEntity = systemService.getEntity(IPLimitEntity.class, ipLimitEntity.getId());
        message = "Ip名单删除成功";
        try{
            ipLimitService.delete(ipLimitEntity);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        }catch(Exception e){
            e.printStackTrace();
            message = "Ip名单删除失败";
            throw new org.jeecgframework.core.common.exception.BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }




}

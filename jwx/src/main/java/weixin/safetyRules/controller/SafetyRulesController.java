package weixin.safetyRules.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.cgform.exception.BusinessException;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.safetyRules.entity.SafetyRulesEntity;
import weixin.safetyRules.service.SafetyRulesRecordsServiceI;
import weixin.safetyRules.service.SafetyRulesServiceI;

/**
 * Created by aa on 2016/3/21.
 */
@Controller
@RequestMapping("/safetyRulesController")
public class SafetyRulesController extends BaseController {

    private static final Logger logger = Logger.getLogger(SafetyRulesController.class);
    @Autowired
    private SafetyRulesServiceI safetyRulesService;

    @Autowired
    private SystemService systemService;
    
    @Autowired
    private SafetyRulesRecordsServiceI recordService;

    private String message;

    @RequestMapping(params = "weixinSafetyRulesList")
    public ModelAndView WeixinMainList(HttpServletRequest request) {
        return new ModelAndView("weixin/safetyRules/weixinSafetyRulesList");
    }

    @RequestMapping(params = "mydatagrid")
    @ResponseBody
    public void datagrid(SafetyRulesEntity safetyRulesEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(SafetyRulesEntity.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, safetyRulesEntity, request.getParameterMap());

        try {
            //自定义追加查询条件
            cq.eq("acctid", ResourceUtil.getWeiXinAccount().getAcctId());

        } catch (Exception e) {
            throw new org.jeecgframework.core.common.exception.BusinessException(e.getMessage());
        }
        cq.add();
        this.safetyRulesService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);

    }


    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(HttpServletRequest request) {
        return new ModelAndView("weixin/safetyRules/addWeixinSafetyRules");
    }
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(SafetyRulesEntity safetyRulesEntity, HttpServletRequest request) {
        if (StringUtil.isNotEmpty(safetyRulesEntity.getId())) {
            safetyRulesEntity = safetyRulesService.getEntity(SafetyRulesEntity.class, safetyRulesEntity.getId());
            request.setAttribute("safetyRules",safetyRulesEntity);
        }
        return new ModelAndView("/weixin/safetyRules/updateWeixinSafetyRules");
    }


    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(SafetyRulesEntity safetyRulesEntity, HttpServletRequest request) throws BusinessException {

        long startTime = System.currentTimeMillis();
        StringBuffer buffer = new StringBuffer();
        buffer.append("safetyRulesController_doUpdate_begin:_");
        try {
            AjaxJson j = new AjaxJson();
            message = "安全规则信息更新成功";
            if (safetyRulesEntity.getId()==null) {
                safetyRulesEntity.setAcctid(ResourceUtil.getWeiXinAccount().getAcctId());
                safetyRulesEntity.setCreateDate(new Date());
                safetyRulesService.save(safetyRulesEntity);
                recordService.createRecords(safetyRulesEntity);
            } else {
                safetyRulesEntity.setOperateDate(new Date());
                SafetyRulesEntity t = safetyRulesService.get(SafetyRulesEntity.class, safetyRulesEntity.getId());
                try {
                    MyBeanUtils.copyBeanNotNull2Bean(safetyRulesEntity, t);
                } catch (Exception e) {
                    e.printStackTrace();
                    message = "安全规则更新失败";
                    throw new BusinessException(e.getMessage());
                }
                safetyRulesService.saveOrUpdate(t);
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
    public AjaxJson doDel(SafetyRulesEntity safetyRulesEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        safetyRulesEntity = systemService.getEntity(SafetyRulesEntity.class, safetyRulesEntity.getId());
        message = "安全规则删除成功";
        try{
            safetyRulesService.delete(safetyRulesEntity);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        }catch(Exception e){
            e.printStackTrace();
            message = "安全规则删除失败";
            throw new org.jeecgframework.core.common.exception.BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public void exportXls(SafetyRulesEntity safetyRulesEntity,HttpServletRequest request,HttpServletResponse response
            , DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "安全规则";
            // 根据浏览器进行转码，使其支持中文文件名
            if (BrowserUtils.isIE(request)) {
                response.setHeader(
                        "content-disposition",
                        "attachment;filename="
                                + java.net.URLEncoder.encode(codedFileName,
                                "UTF-8") + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"),
                        "ISO8859-1");
                response.setHeader("content-disposition",
                        "attachment;filename=" + newtitle + ".xls");
            }
            // 产生工作簿对象
            HSSFWorkbook workbook = null;
            CriteriaQuery cq = new CriteriaQuery(SafetyRulesEntity.class, dataGrid);
            org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, safetyRulesEntity, request.getParameterMap());

            List<SafetyRulesEntity> safetyRulesEntityList = this.safetyRulesService.getListByCriteriaQuery(cq,false);
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("安全规则","",
                    "导出信息"), SafetyRulesEntity.class, safetyRulesEntityList);
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (Exception e) {
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {

            }
        }
    }

}

package weixin.tenant.controller;

import weixin.tenant.entity.WeixinAnnouncementEntity;
import weixin.tenant.service.WeixinAnnouncementServiceI;

import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;

import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

import org.jeecgframework.core.util.ExceptionUtil;


/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: 系统公告
 * @date 2015-04-03 12:50:55
 */
//@Scope("prototype")
@Controller
@RequestMapping("/weixinAnnouncementController")
public class WeixinAnnouncementController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(WeixinAnnouncementController.class);

    @Autowired
    private WeixinAnnouncementServiceI weixinAnnouncementService;

    @Autowired
    private SystemService systemService;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * 系统公告列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "weixinAnnouncement")
    public ModelAndView weixinAnnouncement(HttpServletRequest request) {
        return new ModelAndView("weixin/tenant/weixinAnnouncementList");
    }


    @RequestMapping(params = "getAnnouncements")
    @ResponseBody
    public String getAnnouncements(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {

        AjaxJson j = new AjaxJson();

        //系统公告、新功能推荐
        List<WeixinAnnouncementEntity> weixinAnnouncementList = weixinAnnouncementService.findByProperty(WeixinAnnouncementEntity.class, "status", "1");

        return com.alibaba.fastjson.JSONArray.toJSONString(weixinAnnouncementList);
    }

    /**
     * easyui AJAX请求数据
     *
     * @param request
     * @param response
     * @param dataGrid
     * @param user
     */

    @RequestMapping(params = "datagrid")
    public void datagrid(WeixinAnnouncementEntity weixinAnnouncement, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WeixinAnnouncementEntity.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinAnnouncement, request.getParameterMap());
        try {
            //自定义追加查询条件
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        this.weixinAnnouncementService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 删除系统公告
     *
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(WeixinAnnouncementEntity weixinAnnouncement, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        weixinAnnouncement = systemService.getEntity(WeixinAnnouncementEntity.class, weixinAnnouncement.getId());
        message = "系统公告删除成功";
        try {
            weixinAnnouncementService.delete(weixinAnnouncement);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "系统公告删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 批量删除系统公告
     *
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "系统公告删除成功";
        try {
            for (String id : ids.split(",")) {
                WeixinAnnouncementEntity weixinAnnouncement = systemService.getEntity(WeixinAnnouncementEntity.class,
                        id
                );
                weixinAnnouncementService.delete(weixinAnnouncement);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "系统公告删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 添加系统公告
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(WeixinAnnouncementEntity weixinAnnouncement, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "系统公告添加成功";
        try {
            weixinAnnouncementService.save(weixinAnnouncement);
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "系统公告添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 更新系统公告
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(WeixinAnnouncementEntity weixinAnnouncement, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "系统公告更新成功";
        WeixinAnnouncementEntity t = weixinAnnouncementService.get(WeixinAnnouncementEntity.class, weixinAnnouncement.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(weixinAnnouncement, t);
            weixinAnnouncementService.saveOrUpdate(t);
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "系统公告更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 系统公告新增页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(WeixinAnnouncementEntity weixinAnnouncement, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weixinAnnouncement.getId())) {
            weixinAnnouncement = weixinAnnouncementService.getEntity(WeixinAnnouncementEntity.class, weixinAnnouncement.getId());
            req.setAttribute("weixinAnnouncementPage", weixinAnnouncement);
        }
        return new ModelAndView("weixin/tenant/weixinAnnouncement-add");
    }

    /**
     * 系统公告编辑页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(WeixinAnnouncementEntity weixinAnnouncement, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weixinAnnouncement.getId())) {
            weixinAnnouncement = weixinAnnouncementService.getEntity(WeixinAnnouncementEntity.class, weixinAnnouncement.getId());
            req.setAttribute("weixinAnnouncementPage", weixinAnnouncement);
        }
        return new ModelAndView("weixin/tenant/weixinAnnouncement-update");
    }

    /**
     * 系统公告查看页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goShow")
    public ModelAndView goShow(String id, HttpServletRequest req) {

        WeixinAnnouncementEntity weixinAnnouncement = weixinAnnouncementService.getEntity(WeixinAnnouncementEntity.class, id);
        req.setAttribute("weixinAnnouncementPage", weixinAnnouncement);

        return new ModelAndView("weixin/tenant/weixinAnnouncement-show");
    }

    /**
     * 导入功能跳转
     *
     * @return
     */
    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        return new ModelAndView("weixin/tenant/weixinAnnouncementUpload");
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public void exportXls(WeixinAnnouncementEntity weixinAnnouncement, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "系统公告";
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
            CriteriaQuery cq = new CriteriaQuery(WeixinAnnouncementEntity.class, dataGrid);
            org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinAnnouncement, request.getParameterMap());

            List<WeixinAnnouncementEntity> weixinAnnouncements = this.weixinAnnouncementService.getListByCriteriaQuery(cq, false);
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("系统公告列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
                    "导出信息"), WeixinAnnouncementEntity.class, weixinAnnouncements);
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

    /**
     * 导出excel 使模板
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXlsByT")
    public void exportXlsByT(WeixinAnnouncementEntity weixinAnnouncement, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "系统公告";
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
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("系统公告列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
                    "导出信息"), WeixinAnnouncementEntity.class, null);
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

    @SuppressWarnings("unchecked")
    @RequestMapping(params = "importExcel", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setSecondTitleRows(1);
            params.setNeedSave(true);
            try {
                List<WeixinAnnouncementEntity> listWeixinAnnouncementEntitys =
                        (List<WeixinAnnouncementEntity>) ExcelImportUtil.importExcelByIs(file.getInputStream(), WeixinAnnouncementEntity.class, params);
                for (WeixinAnnouncementEntity weixinAnnouncement : listWeixinAnnouncementEntitys) {
                    weixinAnnouncementService.save(weixinAnnouncement);
                }
                j.setMsg("文件导入成功！");
            } catch (Exception e) {
                j.setMsg("文件导入失败！");
                logger.error(ExceptionUtil.getExceptionMessage(e));
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return j;
    }
}

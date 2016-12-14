package weixin.source.controller;

import org.jeecgframework.core.util.*;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.entity.common.WeixinMedia;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.source.entity.WeixinSourceEntity;
import weixin.source.service.WeixinSourceServiceI;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;

import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: 素材管理
 * @date 2015-01-29 10:45:04
 */
@Controller
@RequestMapping("/weixinSourceController")
public class WeixinSourceController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger LOGGER = Logger.getLogger(WeixinSourceController.class);

    @Autowired
    private WeixinSourceServiceI weixinSourceService;

    @Autowired
    private WeixinAccountServiceI weixinAccountService;

    @Autowired
    private SystemService systemService;

    private String message;

    //文件保存路径的前缀 add by mike
    private static String filePathPrefix = null;

    //文件保存url的前缀 add by mike
    private static String fileUrlPrefix = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 素材管理列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "weixinSource")
    public ModelAndView weixinSource(HttpServletRequest request, String type) {

        if (("image").equals(type)) {
            return new ModelAndView("weixin/source/imageList");
        }

        if (("voice").equals(type)) {
            return new ModelAndView("weixin/source/voiceList");
        }

        if (("video").equals(type)) {
            return new ModelAndView("weixin/source/videoList");
        }

        return new ModelAndView("weixin/source/weixinSourceList");
    }

    /**
     * easyui AJAX请求数据
     *
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "datagrid")
    public void datagrid(WeixinSourceEntity weixinSource, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, String type) {
        CriteriaQuery cq = new CriteriaQuery(WeixinSourceEntity.class, dataGrid);
        weixinSource.setSourceType(type);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinSource, request.getParameterMap());
        cq.eq("accountid", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
        try {
            //自定义追加查询条件
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        this.weixinSourceService.getDataGridReturn(cq, true);
        //将数据库中取出来的图片,加上前缀
        if(dataGrid.getResults() != null){
            WeixinSourceEntity tempEntity = null;
            for(Object obj : dataGrid.getResults()){
                tempEntity = (WeixinSourceEntity)obj;
                tempEntity.setSourcePath(ResourceUtil.getMediaUrlPrefix() + "/" + tempEntity.getSourcePath());
            }
        }

        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 删除素材管理
     *
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(WeixinSourceEntity weixinSource, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        weixinSource = systemService.getEntity(WeixinSourceEntity.class, weixinSource.getId());
        message = "素材管理删除成功";
        try {
            weixinSourceService.delete(weixinSource);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "素材管理删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 批量删除素材管理
     *
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "素材管理删除成功";
        try {
            for (String id : ids.split(",")) {
                WeixinSourceEntity weixinSource = systemService.getEntity(WeixinSourceEntity.class,
                        id
                );
                weixinSourceService.delete(weixinSource);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "素材管理删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 更新素材管理
     *
     * @return
     */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(WeixinSourceEntity weixinSource, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "素材管理更新成功";
        WeixinSourceEntity t = weixinSourceService.get(WeixinSourceEntity.class, weixinSource.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(weixinSource, t);
            weixinSourceService.saveOrUpdate(t);
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "素材管理更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 素材管理编辑页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(WeixinSourceEntity weixinSource, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weixinSource.getId())) {
            weixinSource = weixinSourceService.getEntity(WeixinSourceEntity.class, weixinSource.getId());
            req.setAttribute("weixinSourcePage", weixinSource);
        }
        return new ModelAndView("weixin/source/weixinSource-update");
    }

    /**
     * 素材管理查看页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goView")
    public ModelAndView goView(WeixinSourceEntity weixinSource, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weixinSource.getId())) {
            weixinSource = weixinSourceService.getEntity(WeixinSourceEntity.class, weixinSource.getId());
            weixinSource.setSourcePath(ResourceUtil.getMediaUrlPrefix() + "/" + weixinSource.getSourcePath());
            req.setAttribute("weixinSourcePage", weixinSource);
        }

        String type = req.getParameter("type");
        if (("image").equals(type)) {

            return new ModelAndView("weixin/source/weixinSource-image-view");
        }
        return new ModelAndView("weixin/source/weixinSource-update");
    }

    /**
     * 导入功能跳转
     *
     * @return
     */
    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        return new ModelAndView("weixin/source/weixinSourceUpload");
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public void exportXls(WeixinSourceEntity weixinSource, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "素材管理";
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
            CriteriaQuery cq = new CriteriaQuery(WeixinSourceEntity.class, dataGrid);
            org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinSource, request.getParameterMap());

            List<WeixinSourceEntity> weixinSources = this.weixinSourceService.getListByCriteriaQuery(cq, false);
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("素材管理列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
                    "导出信息"), WeixinSourceEntity.class, weixinSources);
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
    public void exportXlsByT(WeixinSourceEntity weixinSource, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "素材管理";
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
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("素材管理列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
                    "导出信息"), WeixinSourceEntity.class, null);
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
                List<WeixinSourceEntity> listWeixinSourceEntitys =
                        (List<WeixinSourceEntity>) ExcelImportUtil.importExcelByIs(file.getInputStream(), WeixinSourceEntity.class, params);
                for (WeixinSourceEntity weixinSource : listWeixinSourceEntitys) {
                    weixinSourceService.save(weixinSource);
                }
                j.setMsg("文件导入成功！");
            } catch (Exception e) {
                j.setMsg("文件导入失败！");
                LOGGER.error(ExceptionUtil.getExceptionMessage(e));
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


    //////////////////////////////////////////    mike add start   //////////////////////////////////////////

    //素材管理新增jsp页面跳转
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(WeixinSourceEntity weixinSource, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weixinSource.getId())) {
            weixinSource = weixinSourceService.getEntity(WeixinSourceEntity.class, weixinSource.getId());
            req.setAttribute("weixinSourcePage", weixinSource);
        }
        return new ModelAndView("weixin/source/weixinSource-add");
    }

    //素材管理上传图片
    @RequestMapping(params = "uploadthumbMike", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson uploadthumbMike(MultipartHttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
    	
        Map<String, MultipartFile> fileMap = request.getFileMap();
        String filePathName = null;
        String fileRelativeUrl = null;//图片相对url.  相对url加上前缀,就构成了完整的图片url

        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile mf = entity.getValue();// 获取上传文件对象
            String fileName = mf.getOriginalFilename();// 获取文件名
            String extend = FileUtils.getExtend(fileName);// 获取文件扩展名
            filePathName = ResourceUtil.getMediaPathPrefix() + File.separator + System.currentTimeMillis() + "." + extend;
            fileRelativeUrl = System.currentTimeMillis() + "." + extend;
            try {
                byte[] imageBytes = mf.getBytes();
                
                //将文件内容写到指定的文件之中
                writeData2File(filePathName, imageBytes);
                LOGGER.info("文件已经保存到" + filePathName);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        AjaxJson j = new AjaxJson();
        String imgUrl = ResourceUtil.getMediaUrlPrefix()+"/"+fileRelativeUrl;
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("imgUrl",imgUrl);
        attributes.put("imagePath", filePathName);
        attributes.put("imageRelativeUrl", fileRelativeUrl);
        attributes.put("fileKey", "");
        attributes.put("name", "");
        attributes.put("viewhref", "");
        attributes.put("delurl", "");
        j.setMsg("文件添加成功");
        j.setAttributes(attributes);

        return j;
    }

    //素材管理上传图片到微信,并保存到数据库之中
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(WeixinSourceEntity weixinSource, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        weixinSource.setSourceType("image");
        try {
            String accessTocken = weixinAccountService.getAccessToken();// 调用接口获取access_token
            String mediaFileUrl = request.getParameter("imagePath");
            String imageRelativeUrl = request.getParameter("imageRelativeUrl");
            File file = new File(mediaFileUrl);
            WeixinMedia weixinMedia = WeixinUtil.uploadMediaByLocal(accessTocken, weixinSource.getSourceType(), file);
            if (weixinMedia != null) {
                weixinSource.setMediaId(weixinMedia.getMediaId());
                weixinSource.setCreateDate(new Date());
                weixinSource.setSourcePath(imageRelativeUrl);

                weixinSourceService.save(weixinSource);
                message = "素材上传成功";
                systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
            } else {

                message = "素材上传失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "素材上传失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    //工具方法:将文件内容,写到指定的全路径文件名之中  fileName:完整的路径和文件名,如:/Users/zhixu/Desktop/123.jpg
    public static File writeData2File(String fileName, byte[] by) {
        FileOutputStream fileout = null;
        File file = new File(fileName);
        //如果存在,则删除
        if (file.exists()) {
            file.delete();
        }
        try {
            fileout = new FileOutputStream(file);
            fileout.write(by, 0, by.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    //工具方法:从配置文件mediaFile.properties中,读取文件保存路径的前缀,比如:/Users/zhixu/Desktop/TEST_MEDIA
//    public static String getFilePathPrefix() {
//        if (filePathPrefix == null) {
//            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("mediaFile.properties");
//            Properties p = new Properties();
//            try {
//                p.load(is);
//                filePathPrefix = p.getProperty("media.path.prefix");
//                is.close();//关闭
//                is = null;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return filePathPrefix;
//    }

    //工具方法:从配置文件mediaFile.properties中,读取文件url的前缀,比如:http://localhost/xampp/images
//    public static String getFileUrlPrefix() {
//        if (fileUrlPrefix == null) {
//            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("mediaFile.properties");
//            Properties p = new Properties();
//            try {
//                p.load(is);
//                fileUrlPrefix = p.getProperty("media.url.prefix");
//                is.close();//关闭
//                is = null;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return fileUrlPrefix;
//    }


    //////////////////////////////////////////    mike add end   //////////////////////////////////////////

}

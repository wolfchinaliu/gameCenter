package weixin.guanjia.message.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDocument;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.entity.common.WeixinMedia;
import weixin.guanjia.core.util.CompressUtil;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.message.entity.NewsItem;
import weixin.guanjia.message.entity.NewsTemplate;
import weixin.guanjia.message.service.NewsItemServiceI;
import weixin.guanjia.message.service.NewsTemplateServiceI;
import weixin.member.entity.WeixinGroupEntity;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinGroupServiceI;
import weixin.member.service.WeixinMemberServiceI;
import weixin.message.entity.WeixinMessageEntity;
import weixin.message.entity.WeixinMessageGroupEntity;
import weixin.util.DateUtils;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: 微信图文-明细页面
 * @date 2014-01-09 21:55:30
 */
@Controller
@RequestMapping("/weixinArticleController")
public class WeixinArticleController extends BaseController {

    /**
     * Logger for this class
     */
    private static final Logger LOGGER = Logger.getLogger(WeixinArticleController.class);

    @Autowired
    private NewsItemServiceI newsItemService;

    @Autowired
    private NewsTemplateServiceI newsTemplateService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private WeixinAccountServiceI weixinAccountService;

    @Autowired
    private WeixinMemberServiceI weixinMemberService;

    @Autowired
    WeixinGroupServiceI weixinGroupService;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 微信单图消息列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goMessage")
    public ModelAndView goMessage(HttpServletRequest request) {
        String templateId = request.getParameter("templateId");
        // request.setAttribute("templateId", templateId);
        if (StringUtil.isNotEmpty(templateId)) {
            String hql = "from NewsItem where newsTemplate.id='" + templateId + "' order by orders asc";
            org.jeecgframework.core.util.LogUtil.info("...hql..." + hql);
            List<NewsItem> headerList = this.systemService.findByQueryString(hql);
            if (headerList.size() > 0) {
                request.setAttribute("headerNews", headerList.get(0));
                if (headerList.size() > 1) {
                    ArrayList list = new ArrayList(headerList);
                    list.remove(0);
                    request.setAttribute("newsList", list);
                }
            }
            NewsTemplate newsTemplate = this.systemService.getEntity(NewsTemplate.class, templateId);
            String temp = newsTemplate.getAddTime().replace("-", "/");
            Date addTime = new Date(temp);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            request.setAttribute("addtime", sdf.format(addTime));
            request.setAttribute("mediaurl", ResourceUtil.getConfigByName("media.url.prefix"));
        }
        return new ModelAndView("weixin/guanjia/newstemplate/showmessage");
    }

    /**
     * ajax图文详情
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "showMessage")
    @ResponseBody
    public AjaxJson showMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {

        AjaxJson j = new AjaxJson();

        // 查询模板对象
        String mediaId = request.getParameter("mediaId");
        String hql = "from NewsTemplate where mediaId='" + mediaId + "'";
        List<NewsTemplate> newsTemplateList = this.systemService.findByQueryString(hql);

        if (newsTemplateList != null && newsTemplateList.size() > 0) {

            NewsTemplate newsTemplate = newsTemplateList.get(0);

            // 查询图文明细
            String hql2 = "from NewsItem where newsTemplate.id='" + newsTemplate.getId() + "' order by orders asc";
            List<NewsItem> headerList = this.systemService.findByQueryString(hql2);

            // return com.alibaba.fastjson.JSONArray.toJSONString(headerList);

            JSONArray jsonList = new JSONArray();
            for (int i = 0; i < headerList.size(); i++) {

                NewsItem newsItem = headerList.get(i);

                JSONObject jsonO = new JSONObject();
                jsonO.put("imagePath", newsItem.getImagePath());
                jsonO.put("title", newsItem.getTitle());
                jsonO.put("description", newsItem.getDescription());
                jsonList.add(jsonO);
            }
            j.setObj(jsonList);
        }

        return j;
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
    public void datagrid(NewsItem weixinArticle, HttpServletRequest request, HttpServletResponse response,
                         DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(NewsItem.class, dataGrid);
        cq.eq("accountId", ResourceUtil.getWeiXinAccountId());
        // 查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinArticle,
                request.getParameterMap());
        try {
            // 自定义追加查询条件
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        this.newsItemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 删除微信单图消息
     *
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(NewsItem weixinArticle, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        weixinArticle = systemService.getEntity(NewsItem.class, weixinArticle.getId());
        message = "微信单图消息删除成功";
        try {
            newsItemService.delete(weixinArticle);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信单图消息删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 批量删除微信单图消息
     *
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "微信单图消息删除成功";
        try {
            for (String id : ids.split(",")) {
                NewsItem weixinArticle = systemService.getEntity(NewsItem.class, id);
                newsItemService.delete(weixinArticle);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信单图消息删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 添加微信单图消息
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(NewsItem weixinArticle, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();

        String jumptype = request.getParameter("jumptype");
        String pagetype = request.getParameter("pagetype");
        if (jumptype.equals("flowpage") || jumptype.equals("lottery")) {
            weixinArticle.setUrl(pagetype);
        }
        weixinArticle.setUrlType(jumptype);

        message = "微信单图消息添加成功";
        try {

            String templateId = request.getParameter("templateId");
            NewsTemplate temp1 = this.systemService.getEntity(NewsTemplate.class, templateId);
            weixinArticle.setNewsTemplate(temp1);
            if (StringUtils.isBlank(weixinArticle.getContent())) {
                weixinArticle.setContent("此内容必填");
            }
            String accountId = ResourceUtil.getWeiXinAccountId();
            if (!"-1".equals(accountId)) {
                newsItemService.save(weixinArticle);
            } else {
                j.setSuccess(false);
                j.setMsg("请添加一个公众帐号。");
            }
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信单图消息添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 更新微信单图消息
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(NewsItem weixinArticle, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "微信单图消息更新成功";
        String jumptype = request.getParameter("jumptype");
        String pagetype = request.getParameter("pagetype");
        if (jumptype.equals("flowpage") || jumptype.equals("lottery")) {
            weixinArticle.setUrl(pagetype);
        }
        weixinArticle.setUrlType(jumptype);
        NewsItem t = newsItemService.get(NewsItem.class, weixinArticle.getId());

        try {

            if (null != t) {

                if (jumptype.equals("flowpage") || jumptype.equals("lottery")) {
                    t.setUrl(pagetype);
                }
                t.setUrlType(jumptype);

                MyBeanUtils.copyBeanNotNull2Bean(weixinArticle, t);
                String str = "http://";
                String url = weixinArticle.getImagePath();
                String mediaurl = ResourceUtil.getConfigByName("media.url.prefix");
                if (url.startsWith(str)) {
                    t.setImagePath(url.replace(mediaurl + "/", ""));
                }

                newsItemService.saveOrUpdate(t);
            } else {

                newsItemService.save(weixinArticle);
            }

            //systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信单图消息更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 微信单图消息新增页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(NewsItem weixinArticle, HttpServletRequest req) {
        String templateId = req.getParameter("templateId");
        req.setAttribute("templateId", templateId);
        req.setAttribute("readNumber", 0);
        if (StringUtil.isNotEmpty(weixinArticle.getId())) {
            weixinArticle = newsItemService.getEntity(NewsItem.class, weixinArticle.getId());
            req.setAttribute("weixinArticlePage", weixinArticle);
        }
        return new ModelAndView("weixin/guanjia/newstemplate/weixinArticle-add");
    }

    /**
     * 微信单图消息编辑页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(NewsItem weixinArticle, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weixinArticle.getId())) {
            weixinArticle = newsItemService.getEntity(NewsItem.class, weixinArticle.getId());
            req.setAttribute("readNumber", 0);
            req.setAttribute("weixinArticle", weixinArticle);
            req.setAttribute("mediaurl", ResourceUtil.getConfigByName("media.url.prefix"));
        }
        return new ModelAndView("weixin/guanjia/newstemplate/weixinArticle-update");
    }

    /**
     * 图片压缩上传缩略图
     *
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(params = "uploadthumb", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson uploadthumb(MultipartHttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {

        AjaxJson j = new AjaxJson();
        Map<String, Object> attributes = new HashMap<String, Object>();

        TSTypegroup tsTypegroup = systemService.getTypeGroup("fieltype", "文档分类");
        TSType tsType = systemService.getType("files", "附件", tsTypegroup);
        String fileKey = oConvertUtils.getString(request.getParameter("fileKey"));// 文件ID
        String documentTitle = oConvertUtils.getString(request.getParameter("documentTitle"));// 文件标题
        TSDocument document = new TSDocument();
        if (StringUtil.isNotEmpty(fileKey)) {
            document.setId(fileKey);
            document = systemService.getEntity(TSDocument.class, fileKey);
            document.setDocumentTitle(documentTitle);

        }
        document.setSubclassname(MyClassLoader.getPackPath(document));
        document.setCreatedate(DateUtils.gettimestamp());
        document.setTSType(tsType);
        UploadFile uploadFile = new UploadFile(request, document);
        uploadFile.setCusPath("files");
        uploadFile.setSwfpath("swfpath");

        // 头条用720*400 其他400*400
        String uploadImgPath = CompressUtil.uploadThumb(uploadFile, 720, 400);// 压缩上传缩略图

        LOGGER.info("============" + uploadImgPath);
        // document = systemService.uploadFile(uploadFile);

        // attributes.put("url", document.getRealpath());
        attributes.put("url", uploadImgPath);
        attributes.put("fileKey", document.getId());
        attributes.put("name", document.getAttachmenttitle());
        attributes.put("viewhref", "commonController.do?openViewFile&fileid=" + document.getId());
        attributes.put("delurl", "commonController.do?delObjFile&fileKey=" + document.getId());
        j.setMsg("文件添加成功");
        j.setAttributes(attributes);

        return j;
    }

    /**
     * 保存文件信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(params = "upload", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson upload(MultipartHttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();

        Map<String, Object> attributes = new HashMap<String, Object>();
        TSTypegroup tsTypegroup = systemService.getTypeGroup("fieltype", "文档分类");
        TSType tsType = systemService.getType("files", "附件", tsTypegroup);
        String fileKey = oConvertUtils.getString(request.getParameter("fileKey"));// 文件ID
        String documentTitle = oConvertUtils.getString(request.getParameter("documentTitle"));// 文件标题
        TSDocument document = new TSDocument();
        if (StringUtil.isNotEmpty(fileKey)) {
            document.setId(fileKey);
            document = systemService.getEntity(TSDocument.class, fileKey);
            document.setDocumentTitle(documentTitle);

        }
        document.setSubclassname(MyClassLoader.getPackPath(document));
        document.setCreatedate(DateUtils.gettimestamp());
        document.setTSType(tsType);
        UploadFile uploadFile = new UploadFile(request, document);
        uploadFile.setCusPath("files");
        uploadFile.setSwfpath("swfpath");
        document = systemService.uploadFile(uploadFile);
        attributes.put("url", document.getRealpath());
        attributes.put("fileKey", document.getId());
        attributes.put("name", document.getAttachmenttitle());
        attributes.put("viewhref", "commonController.do?openViewFile&fileid=" + document.getId());
        attributes.put("delurl", "commonController.do?delObjFile&fileKey=" + document.getId());
        j.setMsg("文件添加成功");
        j.setAttributes(attributes);

        return j;
    }

    /**
     * 上传单个永久图文消息素材
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doUploadPermanentNews")
    @ResponseBody
    public AjaxJson doUploadPermanentNews(String templateId, HttpServletRequest request) {

        AjaxJson j = new AjaxJson();

        try {

            String hql = "from NewsItem where newsTemplate.id='" + templateId + "' order by orders asc";
            List<NewsItem> newsItemList = this.systemService.findByQueryString(hql);

            if (newsItemList == null) {

                message = "请添加图文信息再上传";
                return j;
            }

            // 修改素材
            NewsTemplate newsT = newsTemplateService.get(NewsTemplate.class, templateId);
            if (("1").equals(newsT.getStatus())) {

            }

            String path = request.getContextPath();

            String localhosturl = ResourceUtil.getDomain() + "/";

            String accessTocken = weixinAccountService.getAccessToken();
            if (StringUtil.isEmpty(accessTocken)) {

                message = "请配置微信公众号";
                return j;
            }

            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < newsItemList.size(); i++) {

                NewsItem weixinArticle = (NewsItem) newsItemList.get(i);

                // 上传缩略图至微信服务器
                WeixinMedia weixinMedia = WeixinUtil.uploadPermanentMedia(accessTocken, "image",
                        localhosturl + weixinArticle.getImagePath());

                if (weixinMedia != null) {
                    weixinArticle.setThumbMediaId(weixinMedia.getMediaId());
                }

                // 获取工程访问地址
                String url = localhosturl + "weixinArticleController.do?goMessage&templateId=" + templateId;
//                weixinArticle.setUrl(url);
                systemService.save(weixinArticle);

                // 上传图文素材
                JSONObject jsonNew = new JSONObject();
                jsonNew.put("thumb_media_id", weixinArticle.getThumbMediaId());
                jsonNew.put("author", weixinArticle.getAuthor());
                jsonNew.put("title", weixinArticle.getTitle());
                jsonNew.put("content_source_url", weixinArticle.getUrl());
                jsonNew.put("content", weixinArticle.getContent());
                jsonNew.put("digest", "");
                jsonNew.put("show_cover_pic", "1");

                jsonArray.add(jsonNew);
            }

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("articles", jsonArray);

            // 上传永久图文素材
            String weixinurl = WeixinUtil.upload_permanent_news_url.replace("ACCESS_TOKEN", accessTocken);
            JSONObject jsonObject = WeixinUtil.httpRequest(weixinurl, "POST", jsonObj.toString());

            if (jsonObject != null) {
                if (null != jsonObject.get("media_id")) {

                    NewsTemplate newsTemplate = newsTemplateService.get(NewsTemplate.class, templateId);

                    // 媒体文件/图文消息上传后获取的唯一标识
                    newsTemplate.setMediaId(jsonObject.get("media_id").toString());
                    newsTemplate.setUploadtime(new Date());
                    newsTemplate.setStatus("1");

                    newsTemplateService.saveOrUpdate(newsTemplate);

                    message = "上传图文消息素材成功";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "上传图文消息素材失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 批量上传永久图文消息素材
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doBatchUploadPermanentNews")
    @ResponseBody
    public AjaxJson doBatchUploadPermanentNews(HttpServletRequest request) {

        AjaxJson j = new AjaxJson();

        String localhosturl = ResourceUtil.getDomain() + "/";

        String[] ids = request.getParameter("ids").split(",");

        for (int i = 0; i < ids.length; i++) {

            String templateId = ids[i].trim();
            NewsTemplate newsTemplate = newsTemplateService.get(NewsTemplate.class, templateId);

            // 如果是已审核素材，则进行修改
            // if("1".equals(newsTemplate.getStatus())){
            //
            //
            // }else{

            try {

                String hql = "from NewsItem where newsTemplate.id='" + templateId + "' order by orders asc";
                List<NewsItem> newsItemList = this.systemService.findByQueryString(hql);

                if (newsItemList == null || newsItemList.size() == 0) {

                    message = "请添加图文信息再上传,详见图文：" + newsTemplate.getTemplateName();
                    j.setMsg(message);
                    return j;
                }

                String accessTocken = weixinAccountService.getAccessToken();
                if (StringUtil.isEmpty(accessTocken)) {

                    message = "请配置微信公众号";
                    j.setMsg(message);
                    return j;
                }

                JSONArray jsonArray = new JSONArray();
                for (int h = 0; h < newsItemList.size(); h++) {

                    NewsItem weixinArticle = (NewsItem) newsItemList.get(h);

                    // 上传缩略图至微信服务器
                    String realPath = ResourceUtil.getConfigByName("media.path.prefix");
//					String realPath = request.getSession().getServletContext().getRealPath("/");
                    String fileImageUrl = realPath + "/" + weixinArticle.getImagePath();
                    File file = new File(fileImageUrl);
                    if (!file.exists() || !file.isFile()) {
                        message = "部分文件不存在";
                        j.setMsg(message);
                        return j;
                    }
                    WeixinMedia media = WeixinUtil.uploadMediaByLocal(accessTocken, "image", file);
                    if (media == null) {
                        message = "接口调用次数达到每日上限：10次！";
                        j.setMsg(message);
                        return j;
                    }
                    String mediaId = media.getMediaId();
                    if (mediaId == null) {

                        message = "上传缩略图失败";
                        j.setMsg(message);
                        return j;
                    }
                    if (mediaId != null) {
                        weixinArticle.setThumbMediaId(mediaId);
                    }
                    //
                    // //旧的上传方法
                    // //String logoUrl =
                    // WeixinUtil.uploadLocationLogo(accessTocken,
                    // WeixinUtil.upload_card_logo_url, localhosturl +
                    // weixinArticle.getImagePath());
                    //
                    // //新上传图片方法
                    // String realPath =
                    // request.getSession().getServletContext().getRealPath("/")
                    // ;
                    // String fileImageUrl=realPath+
                    // weixinArticle.getImagePath();
                    // String logoUrl=
                    // WeixinUtil.uploadLocationLogoByLocal(accessTocken,WeixinUtil.upload_card_logo_url,
                    // fileImageUrl);
                    // //
                    //
                    //
                    //
                    //
                    //
                    //
                    // if(StringUtils.isNotEmpty(logoUrl)){
                    // weixinArticle.setThumbMediaId(logoUrl);
                    // }else{
                    // message = "上传缩略图失败";
                    // j.setMsg(message);
                    // return j;
                    // }

                    // 获取工程访问地址
                    String url = localhosturl + "weixinArticleController.do?goMessage&templateId=" + templateId;
//					weixinArticle.setUrl(url);
                    systemService.save(weixinArticle);

                    // 上传图文素材
                    JSONObject jsonNew = new JSONObject();
                    jsonNew.put("thumb_media_id", weixinArticle.getThumbMediaId());
                    jsonNew.put("author", weixinArticle.getAuthor());
                    jsonNew.put("title", weixinArticle.getTitle());
                    jsonNew.put("content_source_url", weixinArticle.getUrl());
                    jsonNew.put("content", weixinArticle.getContent());
                    jsonNew.put("digest", "");
                    jsonNew.put("show_cover_pic", "1");

                    jsonArray.add(jsonNew);
                }

                JSONObject jsonObj = new JSONObject();
                jsonObj.put("articles", jsonArray);

                // 上传永久图文素材
                String weixinurl = WeixinUtil.upload_permanent_news_url.replace("ACCESS_TOKEN", accessTocken);
                JSONObject jsonObject = WeixinUtil.httpRequest(weixinurl, "POST", jsonObj.toString());

                if (jsonObject != null) {
                    if (null != jsonObject.get("media_id")) {

                        // 媒体文件/图文消息上传后获取的唯一标识
                        newsTemplate.setMediaId(jsonObject.get("media_id").toString());
                        newsTemplate.setUploadtime(new Date());
                        newsTemplate.setStatus("1");

                        newsTemplateService.saveOrUpdate(newsTemplate);

                        message = "图文素材上传成功";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                message = "图文素材上传失败";
                throw new BusinessException(e.getMessage());
            }
            // }
        }

        j.setMsg(message);
        return j;
    }

    /**
     * 上传临时图文消息素材
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doUploadNews")
    @ResponseBody
    public AjaxJson doUploadNews(String templateId, HttpServletRequest request) {

        AjaxJson j = new AjaxJson();

        try {

            String hql = "from NewsItem where newsTemplate.id='" + templateId + "' order by orders asc";
            List<NewsItem> newsItemList = this.systemService.findByQueryString(hql);

            if (newsItemList == null) {

                message = "请添加图文信息再上传";
                return j;
            }

            String path = request.getContextPath();

            String localhosturl = ResourceUtil.getDomain() + "/";

            String accessTocken = weixinAccountService.getAccessToken();
            if (StringUtil.isEmpty(accessTocken)) {

                message = "请配置微信公众号";
                return j;
            }

            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < newsItemList.size(); i++) {

                NewsItem weixinArticle = (NewsItem) newsItemList.get(i);

                // 上传缩略图至微信服务器
                WeixinMedia weixinMedia = WeixinUtil.uploadMedia(accessTocken, "image",
                        localhosturl + weixinArticle.getImagePath());

                if (weixinMedia != null) {
                    weixinArticle.setThumbMediaId(weixinMedia.getMediaId());
                }

                // 获取工程访问地址
                String url = localhosturl + "weixinArticleController.do?goMessage&templateId=" + templateId;
                weixinArticle.setUrl(url);
                systemService.save(weixinArticle);

                // 上传图文素材
                JSONObject jsonNew = new JSONObject();
                jsonNew.put("thumb_media_id", weixinArticle.getThumbMediaId());
                jsonNew.put("author", weixinArticle.getAuthor());
                jsonNew.put("title", weixinArticle.getTitle());
                jsonNew.put("content_source_url", weixinArticle.getUrl());
                jsonNew.put("content", weixinArticle.getContent());
                jsonNew.put("digest", "");
                jsonNew.put("show_cover_pic", "1");

                jsonArray.add(jsonNew);
            }

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("articles", jsonArray);

            // 上传永久图文素材
            String weixinurl = WeixinUtil.uploadnews_url.replace("ACCESS_TOKEN", accessTocken);
            JSONObject jsonObject = WeixinUtil.httpRequest(weixinurl, "POST", jsonObj.toString());

            if (jsonObject != null) {
                if (null != jsonObject.get("media_id")) {

                    NewsTemplate newsTemplate = newsTemplateService.get(NewsTemplate.class, templateId);

                    // 媒体文件/图文消息上传后获取的唯一标识
                    newsTemplate.setMediaId(jsonObject.get("media_id").toString());
                    newsTemplate.setUploadtime(new Date());
                    newsTemplate.setStatus("1");

                    newsTemplateService.saveOrUpdate(newsTemplate);

                    message = "上传图文消息素材成功";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "上传图文消息素材失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }
}

package weixin.guanjia.message.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.entity.common.WeixinMedia;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.message.entity.AutoResponse;
import weixin.guanjia.message.entity.NewsTemplate;
import weixin.guanjia.message.entity.TextTemplate;
import weixin.guanjia.message.service.AutoResponseServiceI;
import weixin.source.entity.WeixinSourceEntity;
import weixin.source.service.WeixinSourceServiceI;
import weixin.util.DateUtils;

/**
 * 素材上传
 * 
 */
@Controller
@RequestMapping("/groupMessageNewsTemplateController")
public class GroupMessageNewsTemplateController {

	@Autowired
	private SystemService systemService;

	@Autowired
	private AutoResponseServiceI autoResponseService;

	@Autowired
	private WeixinAccountServiceI weixinAccountService;

	@Autowired
	private WeixinSourceServiceI weixinSourceService;

	private String message;

	/**
	 * 素材查询
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "getAllUploadNewsTemplate")
	public ModelAndView getAllUploadNewsTemplate(HttpServletRequest request) {
		String type = request.getParameter("type");
		request.setAttribute("type", type);

		if (type.equals("image")) {

			return new ModelAndView("weixin/message/getAllUploadNewsTemplate_image");
		} else if (type.equals("video")) {

			return new ModelAndView("weixin/message/getAllUploadNewsTemplate_video");
		} else if (type.equals("voice")) {

			return new ModelAndView("weixin/message/getAllUploadNewsTemplate_voice");
		} else if (type.equals("mpnews")) {

			// 查询上传过的图文消息素材
			String hql = "from NewsTemplate where accountId='" + ResourceUtil.getWeiXinAccountId() + "'";
			hql += " and mediaId !=null and status='1'";
			hql += " order by uploadtime desc";

			List<NewsTemplate> newsTemplateList = this.systemService.findByQueryString(hql);
			request.setAttribute("newsTemplateList", newsTemplateList);
			request.setAttribute("mediaurl",ResourceUtil.getConfigByName("media.url.prefix"));
			return new ModelAndView("weixin/guanjia/newstemplate/mpnewsList");
		} else {

			// 显示模版
			return new ModelAndView("weixin/message/goSendGroupMessagePage");
		}

	}

	/**
	 * 素材上传至本地以及微信服务器
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "upload", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson upload(MultipartHttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
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
		uploadFile.setCusPath("files/" + type);
		uploadFile.setSwfpath("swfpath");
		document = systemService.uploadFile(uploadFile);

		attributes.put("url", document.getRealpath());
		attributes.put("fileKey", document.getId());
		attributes.put("name", document.getAttachmenttitle());
		attributes.put("viewhref", "commonController.do?openViewFile&fileid=" + document.getId());
		attributes.put("delurl", "commonController.do?delObjFile&fileKey=" + document.getId());

		// 上传至微信服务器
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String fileImageUrl = realPath + document.getRealpath();
		String accessTocken = weixinAccountService.getAccessToken();
		File file = new File(fileImageUrl);
		if (!file.exists() || !file.isFile()) {
			message = "部分文件不存在";
			j.setMsg(message);
			return j;
		}
		WeixinMedia weixinMedia = null;
		try {
			weixinMedia = WeixinUtil.uploadMediaByLocal(accessTocken, type, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (weixinMedia != null) {

			WeixinSourceEntity weixinSourceEntity = new WeixinSourceEntity();
			weixinSourceEntity.setMediaId(weixinMedia.getMediaId());
			weixinSourceEntity.setSourceType(type);
			weixinSourceEntity.setSourcePath(document.getRealpath());
			weixinSourceEntity.setCreateDate(new Date());
			weixinSourceService.save(weixinSourceEntity);

			// 素材上传
			attributes.put("media_id", weixinMedia.getMediaId());
		}

		j.setMsg("文件上传成功");
		j.setAttributes(attributes);

		return j;
	}

	/**
	 * 素材上传至本地以及微信服务器
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "uploadMV", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson uploadMV(MultipartHttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
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
		uploadFile.setCusPath("files/" + type);
		uploadFile.setSwfpath("swfpath");
		document = systemService.uploadFile(uploadFile);

		attributes.put("url", document.getRealpath());
		attributes.put("fileKey", document.getId());
		attributes.put("name", document.getAttachmenttitle());
		attributes.put("viewhref", "commonController.do?openViewFile&fileid=" + document.getId());
		attributes.put("delurl", "commonController.do?delObjFile&fileKey=" + document.getId());

		// 上传至微信服务器
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String fileImageUrl = realPath + document.getRealpath();
		String accessTocken = weixinAccountService.getAccessToken();
		File file = new File(fileImageUrl);
		if (!file.exists() || !file.isFile()) {
			message = "部分文件不存在";
			j.setMsg(message);
			return j;
		}
		String weixinMedia = null;
		try {
			weixinMedia = WeixinUtil.uploadMediaForLocal(accessTocken, type, file);
			JSONObject videoObj = new JSONObject();
			videoObj.put("media_id", weixinMedia);
			videoObj.put("title", "TITLE");
			videoObj.put("description", "description");
			String url = " https://api.weixin.qq.com/cgi-bin/media/uploadvideo?access_token=ACCESS_TOKEN";
			url = url.replace("ACCESS_TOKEN", accessTocken);
			JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", videoObj.toString());
			if (jsonObject != null) {
				weixinMedia = jsonObject.getString("media_id");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			j.setMsg("发送消息时异常");
			e.printStackTrace();
		}
		if (weixinMedia != null) {

			WeixinSourceEntity weixinSourceEntity = new WeixinSourceEntity();
			weixinSourceEntity.setMediaId(weixinMedia);
			weixinSourceEntity.setSourceType(type);
			weixinSourceEntity.setSourcePath(document.getRealpath());
			weixinSourceEntity.setCreateDate(new Date());
			weixinSourceService.save(weixinSourceEntity);
			j.setMsg("文件上传成功");
			// 素材上传
			attributes.put("media_id", weixinMedia);
		} else {
			j.setMsg("文件上传失败");
		}

		j.setAttributes(attributes);

		return j;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
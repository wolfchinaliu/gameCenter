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
 * 图片上传控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/uploadImgController")
public class UploadImgController extends BaseController {
    public static final transient Logger LOGGER = Logger.getLogger(UploadImgController.class);

    @Autowired
	private SystemService systemService;
	
	/**
	 * 上传图片，根据需要进行压缩
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	 @RequestMapping(params = "upload", method = RequestMethod.POST)
	 @ResponseBody
	 public  AjaxJson upload(MultipartHttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 
		AjaxJson j = new AjaxJson();
		Map<String, Object> attributes = new HashMap<String, Object>();
 
		TSTypegroup tsTypegroup=systemService.getTypeGroup("fieltype","文档分类");
		TSType tsType = systemService.getType("files","附件", tsTypegroup);
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
		
		//头条用720*400 其他400*400
		double width = 720;
		double height = 400;
		
		String imgType = request.getParameter("imgType");
		
		//分类图片
		if("category".equals(imgType)){
			width = 200;
			height = 200;
		}
		
		//商品
		if("shopgoods".equals(imgType)){
			width = 400;
			height = 400;
		}
		
		String uploadImgPath = CompressUtil.uploadThumb(uploadFile, width, height);//压缩上传缩略图

		LOGGER.info("============"+uploadImgPath);
		//document = systemService.uploadFile(uploadFile);

		//attributes.put("url", document.getRealpath());
		attributes.put("url", uploadImgPath);
		attributes.put("fileKey", document.getId());
		attributes.put("name", document.getAttachmenttitle());
		attributes.put("viewhref", "commonController.do?openViewFile&fileid=" + document.getId());
		attributes.put("delurl", "commonController.do?delObjFile&fileKey=" + document.getId());
		j.setMsg("文件添加成功");
		j.setAttributes(attributes);
		
		return j;
	 }
}

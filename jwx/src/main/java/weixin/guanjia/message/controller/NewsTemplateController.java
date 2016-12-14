package weixin.guanjia.message.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.message.entity.NewsItem;
import weixin.guanjia.message.entity.NewsTemplate;
import weixin.guanjia.message.entity.TextTemplate;
import weixin.guanjia.message.service.AutoResponseServiceI;
import weixin.guanjia.message.service.NewsItemServiceI;
import weixin.guanjia.message.service.NewsTemplateServiceI;


/**
 * 图文消息
* 
 */
@Controller
@RequestMapping("/newsTemplateController")
public class NewsTemplateController {
	@Autowired
	private NewsTemplateServiceI newsTemplateService;
	@Autowired
	private NewsItemServiceI newsItemService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private AutoResponseServiceI autoResponseService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	private String message;
	
	/**
	 * 列表展示
	 * @return
	 */
    @RequestMapping(params="list")
	public ModelAndView list(){
		return new ModelAndView("weixin/guanjia/newstemplate/newsTemplateList");
	}
    
    /**
	 * 查询数据
	 * @param newsTemplate
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
    @RequestMapping(params = "datagrid")
	@ResponseBody
	public void datagrid(NewsTemplate newsTemplate,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		CriteriaQuery cq = new CriteriaQuery(NewsTemplate.class, dataGrid);
		cq.eq("accountId", ResourceUtil.getWeiXinAccountId());
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, newsTemplate);
		
		this.newsTemplateService.getDataGridReturn(cq, true);
		
		TagUtil.datagrid(response, dataGrid);
	}
    
    /**
     * 删除信息模板
     * @param newsTemplate
     * @param req
     * @return
     */
	@RequestMapping(params="del")
	@ResponseBody
	public AjaxJson del(NewsTemplate newsTemplate,HttpServletRequest req){
		AjaxJson j = new AjaxJson();
		newsTemplate = this.newsTemplateService.getEntity(NewsTemplate.class, newsTemplate.getId());
		
		if("1".equals(newsTemplate.getStatus())){
			
			//删除微信服务器永久素材
			String accessTocken = weixinAccountService.getAccessToken();
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("media_id", newsTemplate.getMediaId());
			
			String weixinurl = WeixinUtil.del_permanent_news_url.replace("ACCESS_TOKEN",accessTocken);
        	JSONObject jsonObject = WeixinUtil.httpRequest(weixinurl, "POST", jsonObj.toString());
		}	
		
		this.newsTemplateService.delete(newsTemplate);
	
		message = "删除信息数据成功！";
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(this.message);
		return j;
	}
	
	/**
	 * 批量删除图文消息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "删除信息数据成功";
		int succeed = 0;
		int error = 0;
		try {
			for (String id : ids.split(",")) {
				
				NewsTemplate newsTemplate = this.newsTemplateService.getEntity(NewsTemplate.class,id);
				
				if("1".equals(newsTemplate.getStatus())){
					
					//删除微信服务器永久素材
					String accessTocken = weixinAccountService.getAccessToken();
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("media_id", newsTemplate.getMediaId());
					
					String weixinurl = WeixinUtil.del_permanent_news_url.replace("ACCESS_TOKEN",accessTocken);
		        	JSONObject jsonObject = WeixinUtil.httpRequest(weixinurl, "POST", jsonObj.toString());
				}				
	        	
				this.newsTemplateService.delete(newsTemplate);
				succeed += 1;
				systemService.addLog(message, Globals.Log_Type_DEL,
						Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			error += 1;
			message = "删除信息数据失败";
			throw new BusinessException(e.getMessage());
		}
		message="删除信息数据成功"+succeed+"条，失败"+error+"条";
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 跳转到信息模板
	 * @param req
	 * @return
	 */
	@RequestMapping(params="goSuView")
	public ModelAndView goSuView(HttpServletRequest req){
		String id = req.getParameter("id");
		req.setAttribute("id", id);
		if(StringUtil.isNotEmpty(id)){
			NewsTemplate newsTemplate = this.newsTemplateService.getEntity(NewsTemplate.class, id);
			String type = newsTemplate.getType();
			req.setAttribute("type", type);
			req.setAttribute("tempateName", newsTemplate.getTemplateName());
		}
		return new ModelAndView("weixin/guanjia/newstemplate/newsTemplateInfo");
	}

	/**
	 * 保存或更新
	 * @param newsTemplate
	 * @param req
	 * @return
	 */
	@RequestMapping(params="doSave")
	@ResponseBody
	public AjaxJson doSave(NewsTemplate newsTemplate,HttpServletRequest req){
		AjaxJson j = new AjaxJson();
		String id= newsTemplate.getId();
		if(StringUtil.isNotEmpty(id)){
			
			NewsTemplate  tempAutoResponse= this.newsTemplateService.getEntity(NewsTemplate.class, newsTemplate.getId());
			this.message =  "修改图文模板成功！";
			try {
				
				MyBeanUtils.copyBeanNotNull2Bean(newsTemplate, tempAutoResponse);
				this.newsTemplateService.saveOrUpdate(tempAutoResponse);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else{
			
			//新增
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			newsTemplate.setAddTime(sdf.format(new Date()));
			String accountId = ResourceUtil.getWeiXinAccountId();
			if (!"-1".equals(accountId)) {
				
				this.newsTemplateService.save(newsTemplate);
			} else {
				j.setSuccess(false);
				j.setMsg("请添加一个公众帐号。");
			}
		}
		return j;
	}
	
    /**
     * 跳转到消息模板
     * @param req
     * @return
     */
	@RequestMapping(params="goNewsItem")
	public ModelAndView goNewsItem(HttpServletRequest req){
		String templateId = req.getParameter("templateId");
		req.setAttribute("templateId", templateId);
		
		if(StringUtil.isNotEmpty(templateId)){
			NewsTemplate newsTemplate = this.newsTemplateService.getEntity(NewsTemplate.class, templateId);
			req.setAttribute("type", newsTemplate.getType());
		}
		
		String id = req.getParameter("id");
		req.setAttribute("id", id);
		if(StringUtil.isNotEmpty(id)){
			NewsItem newsItem = this.newsTemplateService.getEntity(NewsItem.class, id);
			req.setAttribute("title", newsItem.getTitle());
			req.setAttribute("content", newsItem.getContent());
			req.setAttribute("author", newsItem.getAuthor());
			req.setAttribute("imagePath", newsItem.getImagePath());
			req.setAttribute("description", newsItem.getDescription());
			req.setAttribute("templateId", newsItem.getNewsTemplate().getId());
			req.setAttribute("type", newsItem.getNewsTemplate().getType());
			req.setAttribute("orders", newsItem.getOrders());
		}else{
			//判断是否是头一条短信
			List<NewsItem> newsItemList = this.newsTemplateService.findByProperty(NewsItem.class, "newsTemplate.id", templateId);
			req.setAttribute("orders", (newsItemList.size()+1));
		}
		
		return new ModelAndView("weixin/guanjia/newstemplate/itemInfo");
	}
	
	@RequestMapping(params="jumpupload")
	public ModelAndView jumpUpload(HttpServletRequest req){
		return new ModelAndView("weixin/guanjia/newstemplate/upload");
	}
	
	/**
	 * 保存新增图文项目
	 * @param newsItem
	 * @param req
	 * @return
	 */
	@RequestMapping(params="saveNewsTemplate")
	@ResponseBody
	public AjaxJson saveNewsTemplate(NewsItem newsItem,HttpServletRequest req){
		
		AjaxJson j = new AjaxJson();
		String id= newsItem.getId();
		if(StringUtil.isNotEmpty(id)){
			
			NewsItem  tempAutoResponse= this.newsTemplateService.getEntity(NewsItem.class, newsItem.getId());
			this.message =  "修改关键字回复成功！";
			try {
				MyBeanUtils.copyBeanNotNull2Bean(newsItem, tempAutoResponse);
				this.newsItemService.saveOrUpdate(tempAutoResponse);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else{
			String templateId = req.getParameter("templateId");
//			org.jeecgframework.core.util.LogUtil.info("....imagepath...."+newsItem.getImagePath()+".......content..."+newsItem.getContent());
//			org.jeecgframework.core.util.LogUtil.info("....title...."+newsItem.getTitle()+"....templateId...."+templateId);
			NewsTemplate newsTemplate = this.newsTemplateService.getEntity(NewsTemplate.class, templateId);
			newsItem.setNewsTemplate(newsTemplate);
			this.newsItemService.save(newsItem);
		}
		return j;
	}
	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 跳转到复制素材页面
	 * @param weixinArticle
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "goCopy")
	public ModelAndView goCopy(String ids, HttpServletRequest req) {
		
		req.setAttribute("ids", ids);
		
		//顶级公众号
		WeixinAccountEntity weixinAccountEntity = systemService.getEntity(WeixinAccountEntity.class, ResourceUtil.getSessionUserName().getAccountid());
		req.setAttribute("weixinAccountEntity", weixinAccountEntity);
		
		//子公众号
		List<WeixinAccountEntity> weixinAccountList = systemService.findByProperty(WeixinAccountEntity.class, "pid", ResourceUtil.getSessionUserName().getAccountid());
		req.setAttribute("weixinAccountList", weixinAccountList);
		
		return new ModelAndView("weixin/guanjia/newstemplate/newsTemplate-copy");
	}
	
	/**
	 * 复制素材
	 * @param ids
	 * @param accountid
	 * @param req
	 * @return
	 */
	@RequestMapping(params="doCopy")
	@ResponseBody
	public AjaxJson doCopy(HttpServletRequest req){
		
		AjaxJson j = new AjaxJson();
		
		String ids = req.getParameter("ids");
		String accountid = req.getParameter("accountid");;
		
		if(StringUtil.isEmpty(accountid)){
			message = "复制素材失败，您未选择任何公众账号";
			j.setMsg(message);
			return j;
		}
		
		try {
			
			//复制素材给公众号
			for (String id : ids.split(",")) {
				
				NewsTemplate old_newsTemplate = this.newsTemplateService.getEntity(NewsTemplate.class, id);
				
				NewsTemplate newsTemplate = new NewsTemplate();

				newsTemplate.setAccountId(accountid);
				newsTemplate.setTemplateName(old_newsTemplate.getTemplateName());
				newsTemplate.setMediaId(old_newsTemplate.getMediaId());
				newsTemplate.setAddTime(old_newsTemplate.getAddTime());
				newsTemplate.setStatus(old_newsTemplate.getStatus());
				newsTemplate.setType(old_newsTemplate.getType());
				newsTemplate.setUploadtime(old_newsTemplate.getUploadtime());
				
				this.newsTemplateService.save(newsTemplate);
				
				List<NewsItem> newsItemList = old_newsTemplate.getNewsItemList();
				for (NewsItem newsItem : newsItemList) {
					
					newsItem.setId(null);
					newsItem.setNewsTemplate(newsTemplate);
					systemService.save(newsItem);
				}
			}
			message = "复制素材信息成功";
		} catch (Exception e) {
			e.printStackTrace();
			message = "复制素材信息失败";
		}
			
		j.setMsg(message);
		return j;
	}
}
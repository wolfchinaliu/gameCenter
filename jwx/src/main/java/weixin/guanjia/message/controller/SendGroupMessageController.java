package weixin.guanjia.message.controller;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.message.entity.NewsTemplate;
import weixin.guanjia.message.service.AutoResponseServiceI;
import weixin.member.entity.WeixinGroupEntity;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinGroupServiceI;
import weixin.member.service.WeixinMemberServiceI;
import weixin.message.entity.WeixinMessageEntity;
import weixin.message.entity.WeixinMessageGroupEntity;
import weixin.message.service.WeixinMessageGroupServiceI;

/**
 * 高级群发功能
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/sendGroupMessageController")
public class SendGroupMessageController {

	@Autowired
	private SystemService systemService;
	
	@Autowired
	private AutoResponseServiceI autoResponseService;
	
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	
	private String message;
    
	@Autowired
	private WeixinGroupServiceI weixinGroupService;
	
	@Autowired
	private WeixinMemberServiceI weixinMemberService;
	
	@Autowired
	private WeixinMessageGroupServiceI WeixinMessageGroupService;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
	/**
	 * 跳转到高级群发页面
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "goSendGroupMessagePage")
	public ModelAndView goSendGroupMessagePage(HttpServletRequest request) {
		
		WeixinGroupEntity weixinGroupEntity = new WeixinGroupEntity();
		weixinGroupEntity.setAccountid(ResourceUtil.getWeiXinAccountId());
		//分组列表
		CriteriaQuery cq = new CriteriaQuery(WeixinGroupEntity.class);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinGroupEntity, request.getParameterMap());
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
		List<WeixinGroupEntity> weixinGroupList = weixinGroupService.getListByCriteriaQuery(cq, false);
		request.setAttribute("weixinGroupList", weixinGroupList);
				
		return new ModelAndView("weixin/message/goSendGroupMessagePage");
	}
	
	/**
	 * 高级群发
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "sendGroupMessage")
	@ResponseBody
	public AjaxJson sendGroupMessage(WeixinMessageEntity weixinMessage,HttpServletRequest request) {
		AjaxJson j = new AjaxJson();

		try {
			
			request. setCharacterEncoding("UTF-8");
			
			String msgtype = request.getParameter("msgtype");//文本消息为text，语音为voice, 图片为image，视频为video
			String groupId = request.getParameter("groupId");//组ID
			String isToAll = request.getParameter("isToAll");//是否全部：true 是， false 否
			
			//String str = new String(request.getParameter("param").getBytes("ISO-8859-1"),"utf-8");//文本输入内容,如果选择的是图片、语音、视频，则为medio_id
			//String msgcontent = request.getParameter("param");
			//String str = URLDecoder.decode(msgcontent, "UTF-8");
			
			String str =weixinMessage.getNote();//文本输入内容,如果选择的是图片、语音、视频，则为medio_id
			
			//json参数对象
			JSONObject jsonObj = new JSONObject();

			//群发
			if(("true").equals(isToAll)){
				
				//查询所有关注用户
				String hql = "from WeixinMemberEntity where accountId='"+ResourceUtil.getWeiXinAccountId()+"' and subscribe='1'";
				List<WeixinMemberEntity> weixinMemberList = this.systemService.findByQueryString(hql);
				
				String[] boolArray = new String[weixinMemberList.size()];
				
				for(int i=0;i<weixinMemberList.size();i++){
					
					WeixinMemberEntity weixinMember = weixinMemberList.get(i);
					
					boolArray[i] = weixinMember.getOpenId();
				}
				
				JSONArray jsonArray1 = JSONArray.fromObject(boolArray);
				
				//图文消息
				if(("mpnews").equals(msgtype)){
					
					JSONObject mpnewsObjObj = new JSONObject();
					mpnewsObjObj.put("media_id", str);
					
					jsonObj.put("mpnews", mpnewsObjObj);
					jsonObj.put("msgtype", "mpnews");
					jsonObj.put("touser", jsonArray1);
					
					if(seadMessageCommon(jsonObj, isToAll)){
						
						message = "群发消息成功";
					}
				}

				//文本消息
				if(("text").equals(msgtype)){
					
					JSONObject contentObj = new JSONObject();
					contentObj.put("content", str);
					
					jsonObj.put("text", contentObj);
					jsonObj.put("msgtype", "text");
					jsonObj.put("touser", jsonArray1);
					
					if(seadMessageCommon(jsonObj, isToAll)){
						
						message = "群发消息成功";
					}
				}
				
				//图片
				if(("image").equals(msgtype)){
					
					JSONObject mediaObj = new JSONObject();
					mediaObj.put("media_id", str);
					
					jsonObj.put("image", mediaObj);
					jsonObj.put("msgtype", "image");
					jsonObj.put("touser", jsonArray1);
					
					if(seadMessageCommon(jsonObj, isToAll)){
						
						message = "群发消息成功";
					}
				}
				
				//语音
				if(("voice").equals(msgtype)){
					
					JSONObject voiceObj = new JSONObject();
					voiceObj.put("media_id", str);
					
					jsonObj.put("voice", voiceObj);
					jsonObj.put("msgtype", "voice");
					jsonObj.put("touser", jsonArray1);
					
					if(seadMessageCommon(jsonObj, isToAll)){
						
						message = "群发消息成功";
					}
				}
				
				//视频
				if(("video").equals(msgtype)){
					
					JSONObject videoObj = new JSONObject();
					videoObj.put("media_id", str);
					
					jsonObj.put("video", videoObj);
					jsonObj.put("msgtype", "video");
					jsonObj.put("touser", jsonArray1);
					
					if(seadMessageCommon(jsonObj, isToAll)){
						
						message = "群发消息成功";
					}
				}
				
				//卡券消息
				if(("wxcard").equals(msgtype)){
					
					JSONObject videoObj = new JSONObject();
					videoObj.put("card_id", str);
					
					jsonObj.put("wxcard", videoObj);
					jsonObj.put("msgtype", "wxcard");
					jsonObj.put("touser", jsonArray1);
					
					if(seadMessageCommon(jsonObj, isToAll)){
						
						message = "群发消息成功";
					}
				}
			}

			//按组群发
			if(("false").equals(isToAll)){
				
				JSONObject filterObj = new JSONObject();
				filterObj.put("is_to_all", false);
				filterObj.put("group_id", groupId);
				
				//图文消息
				if(("mpnews").equals(msgtype)){
					
					JSONObject mpnewsObj = new JSONObject();
					mpnewsObj.put("media_id", str);
					
					jsonObj.put("filter", filterObj);
					jsonObj.put("msgtype", "mpnews");
					jsonObj.put("mpnews", mpnewsObj);
					
					if(seadMessageCommon(jsonObj, isToAll)){
						
						message = "群发消息成功";
					}
				}
				
				//文本消息
				if(("text").equals(msgtype)){

					JSONObject contentObj = new JSONObject();
					contentObj.put("content", str);
					
					jsonObj.put("filter", filterObj);
					jsonObj.put("msgtype", "text");
					jsonObj.put("text", contentObj);
					
					if(seadMessageCommon(jsonObj, isToAll)){
						
						message = "群发消息成功";
					}
				}
				
				//图片
				if(("image").equals(msgtype)){
					
					JSONObject imageObj = new JSONObject();
					imageObj.put("media_id", str);
					
					jsonObj.put("filter", filterObj);
					jsonObj.put("msgtype", "image");
					jsonObj.put("image", imageObj);
					
					if(seadMessageCommon(jsonObj, isToAll)){
						
						message = "群发消息成功";
					}
				}
				
				//语音
				if(("voice").equals(msgtype)){
					
					JSONObject voiceObj = new JSONObject();
					voiceObj.put("media_id", str);
					
					jsonObj.put("filter", filterObj);
					jsonObj.put("msgtype", "voice");
					jsonObj.put("voice", voiceObj);
					
					if(seadMessageCommon(jsonObj, isToAll)){
						
						message = "群发消息成功";
					}
				}
				
				//视频
				if(("video").equals(msgtype)){
					
					JSONObject videoObj = new JSONObject();
					videoObj.put("media_id", str);
					
					jsonObj.put("filter", filterObj);
					jsonObj.put("msgtype", "mpvideo");
					jsonObj.put("mpvideo", videoObj);
					
					if(seadMessageCommon(jsonObj, isToAll)){
						
						message = "群发消息成功";
					}
				}
				
				//卡券消息
				if(("wxcard").equals(msgtype)){
					
					JSONObject wxcardObj = new JSONObject();
					wxcardObj.put("card_id", str);
					
					jsonObj.put("filter", filterObj);
					jsonObj.put("wxcard", wxcardObj);
					jsonObj.put("msgtype", "wxcard");
					
					if(seadMessageCommon(jsonObj, isToAll)){
						
						message = "群发消息成功";
					}
				}
			}
			
			//指定用户群发,根据OpenID列表群发(订阅号不可用，服务号认证后可用)
			if(("byuser").equals(isToAll)){
				
				String receiveUserId = request.getParameter("receiveUserId");
				String[] memberIds = receiveUserId.split(",");
				
				String[] boolArray = new String[memberIds.length];
				for(int i=0; i<memberIds.length; i++){
					
					WeixinMemberEntity weixinMember = weixinMemberService.get(WeixinMemberEntity.class, memberIds[i]);
					
					boolArray[i] = weixinMember.getOpenId();
				}
				
				JSONArray jsonArray1 = JSONArray.fromObject(boolArray);
				
				//卡券消息
				if(("wxcard").equals(msgtype)){
					
					JSONObject videoObj = new JSONObject();
					videoObj.put("card_id", str);
					
					jsonObj.put("wxcard", videoObj);
					jsonObj.put("msgtype", "wxcard");
					jsonObj.put("touser", jsonArray1);
					
					if(seadMessageCommon(jsonObj, isToAll)){
						
						message = "群发消息成功";
					}
				}
			}

			//群发记录
			WeixinMessageGroupEntity weixinMessageGroupEntity = new WeixinMessageGroupEntity();
			weixinMessageGroupEntity.setAccountid(ResourceUtil.getWeiXinAccountId());
			weixinMessageGroupEntity.setCreateTime(new Date());
			weixinMessageGroupEntity.setNote(str);
			weixinMessageGroupEntity.setMsgType(msgtype);
						
			if(("true").equals(isToAll))
				weixinMessageGroupEntity.setSendType("0");
			if(("false").equals(isToAll))
				weixinMessageGroupEntity.setSendType("1");
			if(("byuser").equals(isToAll))
				weixinMessageGroupEntity.setSendType("2");
			
			WeixinMessageGroupService.save(weixinMessageGroupEntity);
			
			j.setMsg(message);
		} catch (Exception e) {
			e.printStackTrace();
			message = "群发消息失败";
			throw new BusinessException(e.getMessage());
		}
		
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 调用微信接口执行高级群发
	 * @param jsonObj
	 * @param isToAll
	 * @return
	 */
	public boolean seadMessageCommon(JSONObject jsonObj, String isToAll){
		
		//高级群发接口
        String accessTocken = weixinAccountService.getAccessToken();// 调用接口获取access_token
        if(StringUtil.isNotEmpty(accessTocken)){
        	
        	String url = "";
        	if(("true").equals(isToAll)){
        		
        		url = WeixinUtil.send_openid_message_url.replace("ACCESS_TOKEN",accessTocken);
        	}
        	
        	if(("false").equals(isToAll)){
        		
        		url = WeixinUtil.send_group_message_url.replace("ACCESS_TOKEN",accessTocken);
        	}
        	
        	JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonObj.toString());
        	
        	if(jsonObject!=null){
    			if (("0").equals(jsonObject.get("errcode").toString())) {
    				
    				return true;
    			}
    		}
        }
        
        return false;
	}
}
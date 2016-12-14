package weixin.personalredpacket.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.PageAuthCallback;
import weixin.guanjia.account.service.PageAuthHandler;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.SignUtil;
import weixin.liuliangbao.jsonbean.UserFlowAccountBean;
import weixin.liuliangbao.util.HttpUtil;
import weixin.member.controller.ConnectionsManager;
import weixin.member.entity.WeixinMemberEntity;
import weixin.personalredpacket.entity.MoreRedpacketEntity;
import weixin.personalredpacket.entity.PersonalRedpacketEntity;
import weixin.personalredpacket.entity.PersonalRedpacketSetEntity;
import weixin.personalredpacket.service.PersonalRedpacketServiceI;
import weixin.personalredpacket.service.impl.MoreRedpacketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * @Title: Controller
 * @Description: 微信活动
 * @author onlineGenerator
 * @date 2015-02-05 14:26:01
 * @version V1.0
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/moreRedpacketController")
public class MoreRedpacketController extends BaseController{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MoreRedpacketController.class);

	@Autowired
	private PersonalRedpacketServiceI personalRedpacketService;

	@Autowired
	private SystemService systemService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@RequestMapping(params = "moreRedpacketList")
	public ModelAndView moreRedpacketList(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String phoneNumber=request.getParameter("phoneNumber");
		MoreRedpacketService moreRedpacketService=new MoreRedpacketService();
		List<MoreRedpacketEntity> moreRedpacketEntities=moreRedpacketService.queryMoreRedpacket(phoneNumber);
		request.setAttribute("mediaurl",ResourceUtil.getConfigByName("media.url.prefix")+"/");
		request.setAttribute("url",ResourceUtil.getConfigByName("domain")+"/");
		request.setAttribute("moreRedpacketEntities",moreRedpacketEntities);

		//todo:无数据进入悲剧页面
		if (moreRedpacketEntities.size() == 0) {
			return new ModelAndView("weixin/personalredpacket/sad");
		}

		return new ModelAndView("weixin/personalredpacket/moreRedpacketList");
	}


}

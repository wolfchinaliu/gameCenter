package weixin.gameCenter.controller;

import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.aop.DataSourceSwitcher;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.IpUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONObject;
import weixin.gameCenter.entity.WeixinGameTypeEntity;
import weixin.gameCenter.entity.WeixinOtherGameEntity;
import weixin.gameCenter.service.IGameService;
import weixin.gameCenter.service.ResultCode;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.PageAuthCallback;
import weixin.guanjia.account.service.PageAuthHandler;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.liuliangbao.jsonbean.Update;
import weixin.liuliangbao.util.DESUtil;
import weixin.liuliangbao.util.HttpUtil;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;

/**
 * @author popl
 * @version V1.0
 * @Title: Controller
 * @Description: 微信游戏
 * @date 2016-09-01
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller
@RequestMapping("/gameController")
public class GameController extends BaseController implements PageAuthHandler {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GameController.class);
	private String path = ResourceUtil.getConfigByName("jfinalUrl-config");
	@Autowired
	private SystemService systemService;
	@Autowired
	private IGameService gameService;
	@Autowired
	private WeixinMemberServiceI weixinMemberService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 引导授权界面
	 *
	 * @param request
	 */
	@RequestMapping(params = "startGame")
	public ModelAndView startGame(HttpServletRequest request, HttpServletResponse response) {
		Long start = System.currentTimeMillis(); // 方法开始时间
		StringBuffer sb = new StringBuffer();
		sb.append("GameController.startGame():");
		String gameId = request.getParameter("gameId");
		String openId = request.getParameter("openId");

		logger.info("信息 ：" + gameId);
		String url = null;
		try {
			WeixinOtherGameEntity gameEntity = this.systemService.get(WeixinOtherGameEntity.class, gameId);
			// 活动不存在时的处理页面
			if (gameEntity == null) {
				return new ModelAndView("common/404");
			}
			sb.append("用户[" + openId + "]参与了游戏[{" + gameId + "}]");
			String accountid = gameEntity.getAccountId();
			/*
			 * WeixinAccountEntity account =
			 * this.systemService.get(WeixinAccountEntity.class, accountid); if
			 * (account == null) { return new ModelAndView("common/404"); }
			 */
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("gameId", gameId); // 活动ID，传给后面用
			// 不为空 或者 游戏不需要关注注公众号

			if (StringUtils.isBlank(openId)) {
				// if(account.getAccounttype().equalsIgnoreCase("1"))
				url = weixinAccountService.pageAuth2(accountid, properties, this); // 调用授权封装:商户ID，
				// else{
				// properties.put("is_shiliu", "yes");
				// }
			} else {
				url = weixinAccountService.pageAuth2(accountid, properties, this, openId);
			}
			sb.append(", 重定向地址:" + url);
			return new ModelAndView("redirect:" + url);
		} catch (Exception e) {
			logger.error("开始进入游戏异常--", e);
			return new ModelAndView("common/404");
		} finally {
			Long end = System.currentTimeMillis();
			sb.append(", 方法耗时:" + (end - start) + "ms");
			logger.info(sb.toString());
		}

	}

	@Override
	public ModelAndView followAndBind(PageAuthCallback msg, HttpServletRequest request) throws Exception {
		return this.followAndBind(msg, request, false);
	}

	@Override
	public ModelAndView follow(PageAuthCallback msg, HttpServletRequest request) throws Exception {
		return this.followAndBind(msg, request, true);
	}

	public ModelAndView followAndBind(PageAuthCallback msg, HttpServletRequest request,
			boolean allowNotBindPhoneNumber) {
		Long start = System.currentTimeMillis(); // 方法开始时间
		StringBuffer sb = new StringBuffer();
		sb.append("GameController.followAndBind()");
		ModelAndView view = new ModelAndView();
		try {
			// 判断活动id是否存在
			String gameId = msg.getProperties().get("gameId");
			if (gameId == null || "".equals(gameId)) {
				sb.append("活动ID为空，进入404页面");
				return new ModelAndView("common/404");
			}
			// 获取openID,获取的是点击者的openId
			String openId = msg.getOpenId();
			// 根据本次活动id查询活动
			WeixinOtherGameEntity gameEntity = this.systemService.get(WeixinOtherGameEntity.class, gameId);
			view.addObject("activity", gameEntity);
			sb.append("用户[" + openId + "]参与了活动[{" + gameId + "}]");
			// 获取当前公众帐号
			String accountid = gameEntity.getAccountId();
			// 获取用户信息
			WeixinMemberEntity memberEntity = new WeixinMemberEntity();
			String hql = "from WeixinMemberEntity t where t.accountId='" + accountid + "' and t.openId='" + openId
					+ "'";
			List<WeixinMemberEntity> weixinMemberList = weixinMemberService.findByQueryString(hql);
			if (weixinMemberList != null && weixinMemberList.size() > 0) {
				memberEntity = weixinMemberList.get(0);
			}
			WeixinAccountEntity accountEntity = weixinAccountService.get(WeixinAccountEntity.class, accountid);
			view.addObject("account", accountEntity);
			view.addObject("game", gameEntity);
			view.addObject("member", memberEntity);
			String phoneNumber = memberEntity.getPhoneNumber();

			String gameType = gameEntity.getGameType();
			WeixinGameTypeEntity gameTypeEntity = systemService.get(WeixinGameTypeEntity.class, gameType);
			if (gameTypeEntity == null
					|| (gameTypeEntity.getStatus() != 0 && !accountid.equals(gameTypeEntity.getAccountId()))) {
				sb.append(" 游戏还未完成");
				return new ModelAndView("common/404");
			}
			view.setViewName("weixin/gameCenter/playGame");
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date now = new Date();
			if (now.before(gameEntity.getStartTime())) {
				view.addObject("code", 1);
				view.addObject("msg", "活动还未开始   开始时间为：" + fm.format(gameEntity.getStartTime()));
				return view;
			}
			// 验证手机号是否在覆盖区
			if (StringUtil.isNotEmpty(phoneNumber)) {
				String url1 = path + "userGetFlow/getCoverAndLocation";
				Gson gson1 = new Gson();
				JSONObject myJson1 = new JSONObject();
				myJson1.accumulate("phoneNumber", phoneNumber);
				myJson1.accumulate("id", accountid);
				JSONObject jsonObject1 = HttpUtil.httpPost(url1, myJson1, false);
				String strFlow1 = gson1.toJson(jsonObject1);
				Type type1 = new TypeToken<Update>() {
				}.getType();
				Update update1 = gson1.fromJson(strFlow1, type1);
				if (update1 == null) {
					view.addObject("code", 4);
					view.addObject("msg", "手机号验证失败！");
					return view;
				}
				if (update1.getCode().equals("201")) {
					view.addObject("code", 5);
					view.addObject("msg", "手机号不在活动覆盖区");
					return view;
				}
			}
			request.setAttribute("map", weixinAccountService.getAccountJsticket(request, accountid));
			// 链接
			String link = ResourceUtil.getConfigByName("domain") + "/" + "gameController.do?startGame&gameId=" + gameId;
			request.setAttribute("link", link);
			// 判断活动类型
			
			view.addObject("code", 0);
			view.addObject("gameTypeEntity", gameTypeEntity);
			view.addObject("openId", openId);
			view.addObject("gameId", gameId);
			return view;
		} catch (Exception e) {
			logger.error("进入活动异常场", e);
			return new ModelAndView("common/404");
		} finally {
			Long end = System.currentTimeMillis();
			sb.append(MessageFormat.format(", 方法耗时:{0}ms", end - start));
			logger.info(sb.toString());
		}
	}

	/**
	 * 
	 * @param request
	 * @param gameId
	 * @param data
	 * @return
	 */
	@RequestMapping(params = "userGetFlow")
	@ResponseBody
	public JSONObject userGetFlow(HttpServletRequest request, @RequestBody JSONObject json) {
		JSONObject result = new JSONObject();
		String data = MapUtils.getString(json, "data");
		String gameId = MapUtils.getString(json, "gameId");
		Date now = new Date();
		WeixinOtherGameEntity gameEntity = systemService.get(WeixinOtherGameEntity.class, gameId);
		if (gameEntity == null || StringUtil.isEmpty(gameEntity.getGameType())) {
			result.putAll(ResultCode.noGame.getThisMap());
			return result;
		}
		WeixinGameTypeEntity gameTypeEntity = systemService.get(WeixinGameTypeEntity.class, gameEntity.getGameType());
		if (gameTypeEntity == null) {
			result.putAll(ResultCode.noGame.getThisMap());
			return result;
		}
		String clientIp = IpUtil.getIpAddr(request);
		String flowValue = "";
		String openId = null;
		try {
			WeixinAccountEntity GameAccount = systemService.get(WeixinAccountEntity.class,
					gameTypeEntity.getAccountId());
			// 获取ip地址 根据商户id判断ip地址是否在
			if (!gameService.ipIsUsable(clientIp, GameAccount.getAcctId())) {
				result.putAll(ResultCode.noip.getThisMap());
				return result;
			}
			//判断活动是否还在进行
			if(now.after(gameEntity.getEndTime()) || now.before(gameEntity.getStartTime())){
				result.putAll(ResultCode.noip.getThisMap());
				return result;
			}
			// 获取商户的密钥
			String secret = gameService.getCustomerSecret(GameAccount.getAcctId());
			if (StringUtil.isEmpty(secret)) {
				result.putAll(ResultCode.praDecError.getThisMap());
				return result;
			}
			JSONObject jsonData;
			try {
				String decData = java.net.URLDecoder.decode(DESUtil.decrypt(data, secret), "utf-8");
				jsonData = JSONObject.fromObject(decData);
			} catch (Exception e) {
				logger.error("获取流量 - gameID:" + gameId + "-解密过程出错", e);
				result.putAll(ResultCode.praDecError.getThisMap());
				return result;
			}
			openId = jsonData.getString("openId");
			flowValue = jsonData.getString("flowValue");
			String accountId = gameEntity.getAccountId();
			if (StringUtil.isEmpty(openId) || StringUtil.isEmpty(accountId) || StringUtil.isEmpty(flowValue)) {
				result.putAll(ResultCode.parMiss.getThisMap());
				return result;
			}
			if (!flowValue.matches("^[1-9]+[0-9]*$") && !flowValue.matches("^[1-9]+[0-9]*\\.[0-9]+$")) {
				result.putAll(ResultCode.parError.getThisMap());
				return result;
			}
			if(!gameService.isSafeRule(gameId,gameEntity.getRuleId(), openId, Double.valueOf(flowValue))){
				result.putAll(ResultCode.noSafe.getThisMap());
				return result;
			}
			// 是否在覆盖区
			WeixinMemberEntity memberEntity = new WeixinMemberEntity();
			String hql = "from WeixinMemberEntity t where t.accountId='" + accountId + "' and t.openId='" + openId
					+ "'";
			List<WeixinMemberEntity> weixinMemberList = weixinMemberService.findByQueryString(hql);
			if (weixinMemberList != null && weixinMemberList.size() > 0) {
				memberEntity = weixinMemberList.get(0);
			} else {
				result.putAll(ResultCode.parError.getThisMap());
				return result;
			}
			String phoneNumber = memberEntity.getPhoneNumber();
			if (StringUtil.isNotEmpty(phoneNumber)) {
				String url1 = path + "userGetFlow/getCoverAndLocation";
				Gson gson1 = new Gson();
				JSONObject myJson1 = new JSONObject();
				myJson1.accumulate("phoneNumber", phoneNumber);
				myJson1.accumulate("id", accountId);
				JSONObject jsonObject1 = HttpUtil.httpPost(url1, myJson1, false);
				String strFlow1 = gson1.toJson(jsonObject1);
				Type type1 = new TypeToken<Update>() {
				}.getType();
				Update update1 = gson1.fromJson(strFlow1, type1);
				if (update1 == null) {
					result.putAll(ResultCode.noArea.getThisMap());
					return result;
				}
				if (update1.getCode().equals("201")) {
					result.putAll(ResultCode.noArea.getThisMap());
					return result;
				}
			}
			// 流量赠送
			String url = path + "userGetFlow/UpdateFlowAndAddFlowRecord";
			Gson gson = new Gson();
			JSONObject myJson = new JSONObject();
			myJson.accumulate("phoneNumber", phoneNumber);
			myJson.accumulate("flowValue", flowValue);
			myJson.accumulate("id", accountId);
			myJson.accumulate("opreateType", gameTypeEntity.getGameName());
			myJson.accumulate("openid", openId);
			myJson.accumulate("flowType", gameEntity.getFlowType()); // 省内：省内流量；全国：全国流量
			myJson.element("nickName", memberEntity.getNickName());
			JSONObject contentFlow = HttpUtil.httpPost(url, myJson, false);
			String strFlow = gson.toJson(contentFlow);
			Type type = new TypeToken<Update>() {
			}.getType();
			Update update = gson.fromJson(strFlow, type);
			if (!"200".equals(update.getCode()) && !"10026".equals(update.getCode())) {
				result.putAll(ResultCode.flowError.getThisMap());
				return result;
			}
			result.putAll(ResultCode.success.getThisMap());
			return result;
		} catch (Exception e) {
			logger.error("游戏中心领取异常", e);
			result.putAll(ResultCode.findEx.getThisMap());
			return result;
		} finally {
			logger.info("收到领取流量请求 gameId ：" + gameId + "IP ：" + clientIp + "flowValue:" + flowValue + "\n返回"
					+ result.getString("msg"));
			// 保存记录
			gameService.saveGameFlowRecord(gameId, openId,clientIp, flowValue, result.getInt("code"));
		}

	}
	//用户流量支出
	public JSONObject expendFLow(HttpServletRequest request, @RequestBody JSONObject json){
		JSONObject result = new JSONObject();
		String data = MapUtils.getString(json, "data");
		String gameId = MapUtils.getString(json, "gameId");
		Date now = new Date();
		WeixinOtherGameEntity gameEntity = systemService.get(WeixinOtherGameEntity.class, gameId);
		if (gameEntity == null || StringUtil.isEmpty(gameEntity.getGameType())) {
			result.putAll(ResultCode.noGame.getThisMap());
			return result;
		}
		WeixinGameTypeEntity gameTypeEntity = systemService.get(WeixinGameTypeEntity.class, gameEntity.getGameType());
		if (gameTypeEntity == null) {
			result.putAll(ResultCode.noGame.getThisMap());
			return result;
		}
		String clientIp = IpUtil.getIpAddr(request);
		String flowValue = "";
		String openId = null;
		try {
			WeixinAccountEntity GameAccount = systemService.get(WeixinAccountEntity.class,
					gameTypeEntity.getAccountId());
			// 获取ip地址 根据商户id判断ip地址是否在
			if (!gameService.ipIsUsable(clientIp, GameAccount.getAcctId())) {
				result.putAll(ResultCode.noip.getThisMap());
				return result;
			}
			//判断活动是否还在进行
			if(now.after(gameEntity.getEndTime()) || now.before(gameEntity.getStartTime())){
				result.putAll(ResultCode.noip.getThisMap());
				return result;
			}
			// 获取商户的密钥
			String secret = gameService.getCustomerSecret(GameAccount.getAcctId());
			if (StringUtil.isEmpty(secret)) {
				result.putAll(ResultCode.praDecError.getThisMap()); 
				return result;
			}
			JSONObject jsonData;
			try {
				String decData = java.net.URLDecoder.decode(DESUtil.decrypt(data, secret), "utf-8");
				jsonData = JSONObject.fromObject(decData);
			} catch (Exception e) {
				logger.error("支出流量 - gameID:" + gameId + "-解密过程出错", e);
				result.putAll(ResultCode.praDecError.getThisMap());
				return result;
			}
			//获取数据
			
		}catch(Exception e){
			
		}
		return null;
	}
}
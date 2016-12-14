package weixin.lottery.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.annotation.DataSourceSwitch;
import org.jeecgframework.core.aop.DataSourceSwitcher;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONObject;
import sdk.jfinal.JFinalUtils;
import sun.util.logging.resources.logging;
import weixin.advertisement.service.AdvertisementServiceI;
import weixin.gameCenter.service.ResultCode;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.PageAuthCallback;
import weixin.guanjia.account.service.PageAuthHandler;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.SignUtil;
import weixin.integrate.entity.WxIntegrateSecretEntity;
import weixin.integrate.util.WxIntegrateConstant;
import weixin.liuliangbao.jsonbean.MerchantInfoBean;
import weixin.liuliangbao.jsonbean.Update;
import weixin.liuliangbao.util.DESUtil;
import weixin.liuliangbao.util.HttpUtil;
import weixin.lottery.entity.WeixinLotteryEntity;
import weixin.lottery.entity.WeixinWinningrecordlxcEntity;
import weixin.lottery.service.WeixinLotteryServiceI;
import weixin.lottery.service.WeixinWinningrecordServiceI;
import weixin.lottery.service.WeixinWinningrecordlxcServiceI;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.tenant.entity.WeixinMerchantCoverAreaEntity;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.tenant.service.WeixinMerchantCoverAreaServiceI;
import weixin.util.DataDictionaryUtil.AdPublishPosition;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: 微信活动
 * @date 2015-02-05 14:26:01
 */
@Scope("prototype")
@Controller
@RequestMapping("/applotteryController")
public class AppLotteryController extends BaseController {
	/**
	 * Logger for this class
	 */
	public static final String SHILIU_ACCOUNT_ID = ResourceUtil.getShiliuAccountId();
	private static final Logger LOGGER = Logger.getLogger(AppLotteryController.class);
	private String path = ResourceUtil.getConfigByName("jfinalUrl-config");

	@Autowired
	private SystemService systemService;
	@Autowired
	private WeixinWinningrecordServiceI winningrecordService;
	@Autowired
	private WeixinWinningrecordlxcServiceI winningrecordlxcService;
	@Autowired
	private WeixinMemberServiceI weixinMemberService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	@Autowired
	private WeixinMerchantCoverAreaServiceI weixinMerchantCoverAreaService;
	@Autowired
	private AdvertisementServiceI adService;

	/**
	 * 进入活动
	 *
	 * @param request
	 */
	@RequestMapping(params = "startLottery")
	@DataSourceSwitch(dataSource = DataSourceType.dataSource_slave)
	public ModelAndView startLottery(HttpServletRequest request, HttpServletResponse response) {
		Long start = System.currentTimeMillis(); // 方法开始时间
		StringBuffer sb = new StringBuffer();
		ModelAndView errorPage = new ModelAndView("integrate/error");
		sb.append("AppLotteryController.startLottery()");
		try {
			// 判断活动id是否存在
			String acctId = request.getParameter("acctId");
			sb.append("商户 -" + acctId + "调用涌动进入页");
			// 查询商户密钥
			String hql = " from WxIntegrateSecretEntity where acctId = ?";
			List<WxIntegrateSecretEntity> list = systemService.findHql(hql, acctId);
			if (list == null || list.isEmpty() || StringUtil.isEmpty(list.get(0).getSecret())) {
				sb.append("商户不存在密钥，进入404页面");
				errorPage.addObject("msg", "商户信息错误");
				return errorPage;
			}
			String data = request.getParameter("data");
			// 解密
			JSONObject decData = null;
			try {
				String decDataString = java.net.URLDecoder.decode(DESUtil.decrypt(data, list.get(0).getSecret()),
						"utf-8");
				decData = JSONObject.fromObject(decDataString);
			} catch (Exception e) {
				sb.append("-解密过程出错");
				errorPage.addObject("msg", "商户密钥错误");
				return errorPage;
			}
			sb.append("解密数据为：" + decData);
			String phoneNumber = decData.getString("phoneNumber");
			String times = decData.getString("times");
			String hdid = decData.getString("hdid");

			if (StringUtil.isEmpty(hdid)) {
				sb.append("活动ID为空，进入404页面");
				errorPage.addObject("msg", "活动Id错误");
				return errorPage;
			}
			sb.append("活动 " + hdid + " ");
			if (StringUtil.isEmpty(phoneNumber) || !phoneNumber.matches("^1[3-9][0-9]{9}$")) {
				sb.append("手机号为空，进入404页面");
				errorPage.addObject("msg", "手机号不合法");
				return errorPage;
			}
			sb.append("手机号 " + phoneNumber + " ");
			// 获取openID,获取的是点击者的openId
			Long nowTimes = System.currentTimeMillis();
			if (StringUtil.isEmpty(times) || !times.matches("^[1-9]+[0-9]*$")
					|| Math.abs(nowTimes - Long.parseLong(times)) > 900000) {
				errorPage.addObject("msg", "超时请求");
				return errorPage;
			}
			// 根据本次活动id查询活动规则
			WeixinLotteryEntity hdEntity = this.systemService.get(WeixinLotteryEntity.class, hdid);
			request.setAttribute("hdEntity", hdEntity);
			request.getSession().setAttribute("hdId", hdEntity.getId());
			String accountid = hdEntity.getAccountid();
			request.setAttribute("accountid", accountid);
			request.setAttribute("acctId", acctId);
			// 获取商户信息
			WeixinAccountEntity accountEntity = weixinAccountService.getEntity(WeixinAccountEntity.class, accountid);

			// 查询用户是否存在
			WeixinMemberEntity member = this.weixinMemberService.getWeixinMemberByPhone(phoneNumber, accountid);
			if (member == null) {
				request.setAttribute("businessPram", hdid);
				request.setAttribute("accountName", accountEntity.getAccountname());
				request.setAttribute("accountHeadImg",
						ResourceUtil.getConfigByName("media.url.prefix") + "/" + accountEntity.getQRcode());
				request.setAttribute("phoneNumber", phoneNumber);
				request.setAttribute("bindgingUrl", "applotteryController.do?bindingPhone");
				return new ModelAndView("weixin/lottery/verifyphone_bindingapp");
			}
			// 封装提取的参数
			String temp = "{\"acctId\":\"" + acctId + "\",\"phoneNumber\":\"" + phoneNumber + "\",\"times\":\""
					+ (nowTimes + 600000) + "\"}";
			request.setAttribute("endata", DESUtil.toHexString(DESUtil.encrypt(temp, list.get(0).getSecret())));
			Map ad = null;
			String openId = member.getOpenId();
			request.setAttribute("openId", openId);
			String nickname = StringUtil.isEmpty(member.getNickName(), phoneNumber);
			String headImgUrl = member.getHeadImgUrl();
			String domain = ResourceUtil.getConfigByName("domain");
			request.getSession().setAttribute("nickname", nickname);
			request.getSession().setAttribute("phoneNumber", phoneNumber);
			request.getSession().setAttribute("headImgUrl", headImgUrl);

			Gson gson = new Gson();
			String urlFlowAccount = path + "userGetFlow/QueryFlowAccount";
			JSONObject myJsonAccount = new JSONObject();
			myJsonAccount.accumulate("phoneNumber", phoneNumber);
			myJsonAccount.accumulate("openId", member.getOpenId());
			JSONObject contentFlowAccount = HttpUtil.httpPost(urlFlowAccount, myJsonAccount, false);
			String reStrFlowAccount = gson.toJson(contentFlowAccount);
			Type typeFlowAccount = new TypeToken<MerchantInfoBean>() {
			}.getType();
			MerchantInfoBean userFlowAccoun = gson.fromJson(reStrFlowAccount, typeFlowAccount);

			if (userFlowAccoun != null && userFlowAccoun.getCode().equals("200")) {
				request.getSession().setAttribute("provinceFlowValue",
						userFlowAccoun.getData().get(0).getProvinceFlowValue());
				request.getSession().setAttribute("countryFlowValue",
						userFlowAccoun.getData().get(0).getCountryFlowValue());
			} else {
				request.getSession().setAttribute("provinceFlowValue", 0);
				request.getSession().setAttribute("countryFlowValue", 0);
			}

			String lotteryType = hdEntity.getLotteryType();
			String hdUrl = null;
			// 判断活动类型
			if (lotteryType != null && "2".equals(lotteryType)) { // 刮刮卡
				hdUrl = "weixin/lottery/weixinScratchapp";
				ad = this.adService.getPublishedAd(accountEntity.getAcctId(), AdPublishPosition.ggk.getCode());
			} else if (lotteryType != null && "3".equals(lotteryType)) { // 红包
				// 判断个人抽奖次数
				StringBuffer buffer = new StringBuffer();
				buffer.append("SELECT COUNT(*) count FROM weixin_winningrecordlxc t where 1=1 ");
				buffer.append(" and t.hdid=").append("'").append(hdid).append("'");
				buffer.append(" and t.openid=").append("'").append(openId).append("'");
				int count = winningrecordlxcService.getCount(buffer.toString());
				if ((1 - count) <= 0) {
					// 已经超过总抽奖数
					request.setAttribute("ishave", "1");
				}
				hdUrl = "weixin/lottery/weixinRedpacketapp";
			} else if (lotteryType != null && "4".equals(lotteryType)) {// 摇一摇----xiaoguai
				sb.append(", 摇一摇不支持");
				errorPage.addObject("msg", "不支持此活动");
				return errorPage;			
				} else {
				hdUrl = "weixin/lottery/zhuanpanapp"; // 默认大转盘
				ad = this.adService.getPublishedAd(accountEntity.getAcctId(), AdPublishPosition.zhuanpan.getCode());
			}
			request.getSession().setAttribute("ad", ad);
			Update countResponse = JFinalUtils.getLeftTimes(hdid, openId);
			int leftcount = Integer.parseInt("" + countResponse.getAttributes().get("count"));
			request.setAttribute("leftcount", Math.max(leftcount, 0));
			request.setAttribute("code", countResponse.getCode());
			request.setAttribute("message", countResponse.getMessage());
			// 查询最近10条获奖名单
			String sql = "SELECT * FROM weixin_winningrecordlxc  WHERE status = 1 and hdid='" + hdid
					+ "' ORDER BY addtime DESC ";
			List<WeixinWinningrecordlxcEntity> winningRecordList = winningrecordlxcService.findObjForJdbc(sql, 1, 10,
					WeixinWinningrecordlxcEntity.class);
			for (WeixinWinningrecordlxcEntity e : winningRecordList) {
				this.winningrecordlxcService.getSession().evict(e);
				e.setPrizelevel(this.toUpperNumber(e.getPrizelevel()));
			}
			request.setAttribute("winningRecordList", winningRecordList);
			sb.append(", 内部跳转到活动地址:" + hdUrl);

			Map<String, String> map = weixinAccountService.getAccountJsticket(request, accountid);
			request.getSession().setAttribute("map", map);
			request.getSession().setAttribute("domain", ResourceUtil.getDomain());
			request.getSession().setAttribute("accountType", accountEntity.getAccounttype());
			return new ModelAndView(hdUrl);
		} catch (Exception e) {
			LOGGER.error("app进入异常", e);
			errorPage.addObject("msg", "出现异常");
			return errorPage;
		} finally {
			Long end = System.currentTimeMillis();
			sb.append(MessageFormat.format(", 方法耗时:{0}ms", end - start));
			LOGGER.info(sb.toString());
		}
	}

	private String toUpperNumber(String number) {
		String upper = null;
		if (number == null) {
			return null;
		}
		switch (number) {
		case "0":
			upper = "零";
			break;
		case "1":
			upper = "一";
			break;
		case "2":
			upper = "二";
			break;
		case "3":
			upper = "三";
			break;
		case "4":
			upper = "四";
			break;
		case "5":
			upper = "五";
			break;
		case "6":
			upper = "六";
			break;
		case "7":
			upper = "七";
			break;
		case "8":
			upper = "八";
			break;
		case "9":
			upper = "九";
			break;
		case "10":
			upper = "十";
			break;
		default:
			upper = number;
			break;
		}
		return upper;
	}

	/**
	 * 大转盘
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "luckyTurntable")
	@ResponseBody
	public Update luckyTurnable(HttpServletRequest request) {
		String hdId = request.getParameter("hdId");
		String openId = request.getParameter("openId");
		return JFinalUtils.luckyTurnable(hdId, openId);
	}

	/**
	 * 拆红包
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "openRedpacket")
	@ResponseBody
	public Update openRedpacket(HttpServletRequest request) {
		String hdid = request.getParameter("hdid");
		String openId = request.getParameter("openId");
		return JFinalUtils.openRedpacket(hdid, openId);
	}

	/**
	 * 刮刮卡-刮开前抽奖，更换背景图片
	 *
	 * @return
	 */
	@RequestMapping(params = "scratchCard")
	@ResponseBody
	public Update scratchMove(HttpServletRequest request) {
		String hdId = request.getParameter("hdId");
		String openId = request.getParameter("openId");
		Update update = JFinalUtils.scratchCard(hdId, openId);
		// 将当前的刮刮卡状态信息存入session中,以便保存刮刮卡中奖纪录
		request.getSession().setAttribute("scratch", update);

		// 不要展示过多的session中的信息
		Update result = new Update(update.getCode(), update.getMessage());
		result.getAttributes().put("prizeLevel", update.getAttributes().get("prizeLevel"));
		result.getAttributes().put("prizeValue", update.getAttributes().get("prizeValue"));
		return result;
	}

	/**
	 * 保存刮刮卡的中奖纪录(当刮开面积超过50%时)
	 *
	 * @return
	 */
	@RequestMapping(params = "saveScratchRecord")
	@ResponseBody
	public Update saveScratchRecord(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 通过scratch(key)获取session中的刮刮卡中奖状态
		Update scratchInfo = (Update) session.getAttribute("scratch");
		if (null == scratchInfo) {
			scratchInfo = new Update("400", "请刷新页面后重试！");
			return scratchInfo;
		}
		session.removeAttribute("scratch");
		return JFinalUtils.saveScratchRecord(scratchInfo);
	}

	@RequestMapping(params = "shakeHand")
	@ResponseBody
	public Update shakeHand(HttpServletRequest request) {
		String hdid = request.getParameter("hdid");
		String openId = request.getParameter("openId");
		return JFinalUtils.shakeHand(hdid, openId);
	}

	///////////////////////////////////////// 公共方法/begin////////////////////////////////////////////

	// 查询每个人的游戏剩余次数---xiaoguai
	public int leftGameCount(String hdid, String openId) {
		DataSourceType dsType = DataSourceSwitcher.getDataSource();
		DataSourceSwitcher.setDataSource(DataSourceType.dataSource_slave);
		WeixinLotteryEntity hdEntity = this.systemService.get(WeixinLotteryEntity.class, hdid);

		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT COUNT(*) count FROM weixin_winningrecordlxc t where 1=1 ");
		buffer.append(" and t.HDID=").append("'").append(hdEntity.getId()).append("'");
		buffer.append(" and t.OPENID=").append("'").append(openId).append("'");
		Integer count = 0;
		Integer leftcount = 0;

		// 查询游戏剩余次数
		if (StringUtils.isNotBlank(openId)) {
			count = winningrecordService.getCount(buffer.toString());
			leftcount = hdEntity.getLotterynumberday() - count;
			return leftcount;
		}

		DataSourceSwitcher.setDataSource(dsType);
		// 粉丝游戏剩余次数
		return 0;
	}

	///////////////////////////////////////// 公共方法/end////////////////////////////////////////////

	// 抽奖并返回角度和奖项
	public Object[] award(Object[][] prizeArr) {
		// 概率数组
		Integer obj[] = new Integer[prizeArr.length];
		for (int i = 0; i < prizeArr.length; i++) {
			obj[i] = (Integer) prizeArr[i][4];
		}
		Integer prizeId = getRand(obj); // 根据概率获取奖项id
		// 旋转角度
		int angle = new Random().nextInt((Integer) prizeArr[prizeId][2] - (Integer) prizeArr[prizeId][1])
				+ (Integer) prizeArr[prizeId][1];
		String msg = (String) prizeArr[prizeId][3];// 提示信息
		return new Object[] { angle, prizeId, msg };
	}

	// 根据概率获取奖项
	public Integer getRand(Integer obj[]) {
		Integer result = null;
		try {
			int sum = 0;// 概率数组的总概率精度
			for (int i = 0; i < obj.length; i++) {
				sum += obj[i];
			}
			for (int i = 0; i < obj.length; i++) {// 概率数组循环
				int randomNum = new Random().nextInt(sum);// 随机生成1到sum的整数
				if (randomNum < obj[i]) {// 中奖
					result = i;
					break;
				} else {
					sum -= obj[i];
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(params = "bindingPhone")
	@ResponseBody
	public JSONObject bingPhoneNumber(HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String weixinMemberId = null;
		try{
		String hdid = request.getParameter("businessPram");
		String phoneNumber = request.getParameter("phoneNumber");
		String captcha = request.getParameter("captcha");
		HttpSession session = request.getSession();
		if (session.getAttribute("code") == null) {
			result.put("flag", false);
			result.put("code", 3);
			result.put("msg", "验证码过期，请重新发送后验证！");
			return result;
		}
		int times = (Integer) session.getAttribute("codeTimes");
		if (times <= 0) {
			result.put("flag", false);
			result.put("code", 3);
			result.put("msg", "验证码已失效，请重新发送后验证！");
			return result;
		}
		session.setAttribute("codeTimes", times - 1);
		String phone_session = (String) session.getAttribute("phoneNumber");
		String captcha_session = (String) session.getAttribute("code");
		if (!(captcha.equals(captcha_session) && phoneNumber.equals(phone_session))) {
			result.put("flag", false);
			result.put("code", 1);
			result.put("msg", "验证码不正确，请重新输入");
			return result;
		}
		// 查询
		WeixinLotteryEntity hdEntity = this.systemService.get(WeixinLotteryEntity.class, hdid);
		String accountid = hdEntity.getAccountid();
		// 获取商户信息
		WeixinAccountEntity accountEntity = weixinAccountService.getEntity(WeixinAccountEntity.class, accountid);
		WeixinMemberEntity member = this.weixinMemberService.getWeixinMemberByPhone(phoneNumber, accountid);
		if (member == null) {
			// 查询石榴商盟下有没有
			member = this.weixinMemberService.getWeixinMemberByPhone(phoneNumber, SHILIU_ACCOUNT_ID);
			if (member == null) {
				// 新建一个保存
				member = new WeixinMemberEntity();
			}
			member.setId(null);
			member.setOpenId(UUID.randomUUID().toString());
			member.setAccountId(accountid);
			member.setIsRealOpenid((short) 0);
			member.setPhoneNumber(phoneNumber);
			member.setSubscribe("1");
			member.setSubscribeTime(new Date());
			this.weixinMemberService.saveOrUpdate(member);
		}
		weixinMemberId = member.getId();
		LOGGER.info("weixinMemberId  :  " +weixinMemberId);
		JFinalUtils.appBind(member.getOpenId(), phoneNumber, accountid);
		session.removeAttribute("code");
		result.put("flag", true);
		result.put("msg", "绑定成功");
		String temp = "{\"acctId\":\"" + accountEntity.getAcctId() + "\",\"phoneNumber\":\"" + phoneNumber + "\",\"times\":\""
				+ (System.currentTimeMillis() + 300000) + "\",\"hdid\":\""+hdid+"\"}";
		String hql = " from WxIntegrateSecretEntity where acctId = ?";
		List<WxIntegrateSecretEntity> list = systemService.findHql(hql, accountEntity.getAcctId());
		String enData = DESUtil.toHexString(DESUtil.encrypt(temp, list.get(0).getSecret()));
		
		result.put("jumpUrl", "applotteryController.do?startLottery&acctId="+accountEntity.getAcctId()+"&data="+enData);
		
		}catch(Exception e){
			LOGGER.error("绑定手机异常。。",e);
			if(weixinMemberId != null){
				weixinMemberService.deleteEntityById(WeixinMemberEntity.class, weixinMemberId);
			}
			result.put("flag", false);
			result.put("msg", "绑定失败 ，稍后重试");
		}
		
		return result;

	}
}

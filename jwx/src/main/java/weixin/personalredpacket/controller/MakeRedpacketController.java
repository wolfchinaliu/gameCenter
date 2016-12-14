package weixin.personalredpacket.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.*;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.advertisement.service.AdvertisementServiceI;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.PageAuthCallback;
import weixin.guanjia.account.service.PageAuthHandler;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.SignUtil;
import weixin.liuliangbao.jsonbean.Update;
import weixin.liuliangbao.jsonbean.UserFlowAccountBean;
import weixin.liuliangbao.util.HttpUtil;
import weixin.member.controller.ConnectionsManager;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.personalredpacket.entity.MoreRedpacketEntity;
import weixin.personalredpacket.entity.PersonalRedpacketEntity;
import weixin.personalredpacket.entity.PersonalRedpacketSetEntity;
import weixin.personalredpacket.service.PersonalRedpacketServiceI;
import weixin.personalredpacket.service.impl.MoreRedpacketService;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.entity.WeixinMerchantCoverAreaEntity;
import weixin.tenant.service.WeixinMerchantCoverAreaServiceI;
import weixin.util.DataDictionaryUtil.AdPublishPosition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.sql.*;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

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
@RequestMapping("/makeRedpacketController")
public class MakeRedpacketController extends BaseController implements PageAuthHandler{
	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = Logger.getLogger(MakeRedpacketController.class);

	@Autowired
	private PersonalRedpacketServiceI personalRedpacketService;

	@Autowired
	private SystemService systemService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	@Autowired
	private WeixinMerchantCoverAreaServiceI weixinMerchantCoverAreaService;
    @Autowired
    private WeixinMemberServiceI weixinMemberService;
    @Autowired
    private AdvertisementServiceI adService;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 引导授权界面
	 * @param request
	 */
	@RequestMapping(params = "makeRedpacket")
	public ModelAndView makeRedpacket(HttpServletRequest request, HttpServletResponse response) {
		Long start=System.currentTimeMillis();  //方法开始时间
		StringBuffer sb=new StringBuffer();
		sb.append("makeRedpacketController_makeRedpacket");
		String hdid = request.getParameter("hdid");
		String openId = request.getParameter("openId");
		sb.append("_hdid:"+hdid+"_openId:"+openId);
		String url = null;
		try {
			PersonalRedpacketSetEntity hdEntity = this.systemService.get(PersonalRedpacketSetEntity.class, hdid);
			//活动不存在时的处理页面
			if (hdEntity == null) {
                return new ModelAndView("common/404");
            }
			String accountid = hdEntity.getAccountid();
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("hdid",hdid);  //活动ID，传给后面用
			url = "";
			if (StringUtils.isBlank(openId)) {
                url = weixinAccountService.pageAuth(accountid,properties,this);   //调用授权封装:商户ID，
            } else {
                url = weixinAccountService.pageAuth(accountid,properties,this, openId);
            }
			sb.append("_url:"+url);
			return new ModelAndView("redirect:" +url);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("common/404");
		} finally {
			Long end=System.currentTimeMillis();
			sb.append("_time:"+(end-start));
			LOGGER.info(sb.toString());
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

    public ModelAndView followAndBind(PageAuthCallback msg, HttpServletRequest request, boolean allowNotBindPhoneNumber) {
		MoreRedpacketService moreRedpacketService=new MoreRedpacketService();
		Long start=System.currentTimeMillis();  //方法开始时间
		StringBuffer sb=new StringBuffer();
		sb.append("lotteryController_followAndBind");
		try {
			//判断活动id是否存在
			String hdid=msg.getProperties().get("hdid");
			if (hdid == null || "".equals(hdid)) {
                sb.append("活动ID为空，进入404页面");
                return new ModelAndView("common/404");
            }

			//获取openID
			String openId = msg.getOpenId();
			sb.append("_hdid:"+hdid+"_openId:"+openId);

			//个人红包设置
			PersonalRedpacketSetEntity hdEntity = this.systemService.get(PersonalRedpacketSetEntity.class, hdid);
			request.setAttribute("hdEntity", hdEntity);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String starttime = format.format(hdEntity.getStarttime());
			String endtime = format.format(hdEntity.getEndtime());
			request.setAttribute("starttime",starttime);
			request.setAttribute("endtime",endtime);

			//剩余补贴流量
			//判断流量补贴流量是否充足
			PersonalRedpacketSetEntity personalRedpacketSetEntity=this.systemService.getEntity(PersonalRedpacketSetEntity.class,hdid);
			Double leftflow=personalRedpacketSetEntity.getSubsidyValue()-personalRedpacketSetEntity.getFlowSendValue();
			request.setAttribute("leftValue", leftflow);

			//粉丝信息
			WeixinMemberEntity memberEntity=this.weixinMemberService.getWeixinMemberEntity(openId, hdEntity.getAccountid());
			request.setAttribute("memberEntity", memberEntity);

            if (!allowNotBindPhoneNumber) {
                if (StringUtils.isBlank(memberEntity.getPhoneNumber())) {
                    String redirectURL = "bindingController.do?redirectBinding&merchantId={0}&openId={1}&operateType={2}";
                    return new ModelAndView("redirect:" + MessageFormat.format(redirectURL, memberEntity.getAccountId(), memberEntity.getOpenId(), "红包"));
                }
            }

			//对活动尚未开始的处理
			if (hdEntity.getState().equals("2")) {
				String time1 = hdEntity.getStarttime().toString();
				String time2=hdEntity.getEndtime().toString();
				time1=time1.substring(0,time1.length()-2);
				time2=time2.substring(0,time2.length()-2);
//				Date date1 = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(time1);
//				Date date2 = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(time2);
				request.setAttribute("starttime",time1);
				request.setAttribute("endtime",time2);
				return new ModelAndView("weixin/personalredpacket/beforeSad");
			}

			//对已过期和未开始的活动进行处理
			if (hdEntity.getState().equals("0")) {
				List<MoreRedpacketEntity> moreRedpacketEntities=moreRedpacketService.queryMoreRedpacket(memberEntity.getPhoneNumber());
				request.setAttribute("mediaurl",ResourceUtil.getConfigByName("media.url.prefix")+"/");
				request.setAttribute("url",ResourceUtil.getConfigByName("domain")+"/");
				request.setAttribute("moreRedpacketEntities",moreRedpacketEntities);
				request.setAttribute("message","该活动已结束，");
				if (moreRedpacketEntities.isEmpty()) {
					return new ModelAndView("weixin/personalredpacket/sad");
				}
				return new ModelAndView("weixin/personalredpacket/moreRedpacketList");
			}

			WeixinAccountEntity weixinAccountEntity = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", hdEntity.getAccountid());
			WeixinMerchantCoverAreaEntity ww1 = new WeixinMerchantCoverAreaEntity();
			if (weixinAccountEntity.getId() != null) {
				ww1 = weixinMerchantCoverAreaService.findUniqueByProperty(WeixinMerchantCoverAreaEntity.class, "accountID", weixinAccountEntity.getAcctId());
			}
			WeixinAcctEntity acctEntity = this.systemService.get(WeixinAcctEntity.class, weixinAccountEntity.getAcctId());
			String cityName=ww1.getCityname();
            // 添加运营商: 提示语添加运营商的类型
            String businessArea = ww1.getBusinessArea();
            if (StringUtils.isBlank(businessArea) || "三网通".equals(businessArea)) {
                businessArea = "所有运营商";
            }

            if (cityName == null) {
                request.setAttribute("flowArea", ww1.getProvincename() + "内" + businessArea);
            } else {
                request.setAttribute("flowArea", ww1.getProvincename() + ww1.getCityname() + "内" + businessArea);
            }
			// 判断归属地是否和商户统一，
			String url=ResourceUtil.getConfigByName("jfinalUrl-config")+"userGetFlow/getCoverAndLocation";
			Gson gson=new Gson();
			JSONObject myJson = new JSONObject();
			myJson.accumulate("phoneNumber", memberEntity.getPhoneNumber());
			myJson.accumulate("id", weixinAccountEntity.getId());
			JSONObject jsonObject = HttpUtil.httpPost(url, myJson, false);
			String strFlow = gson.toJson(jsonObject);
			Type type = new TypeToken<Update>() {
			}.getType();
			Update update = gson.fromJson(strFlow, type);

			//code为“10025”代表商户区域为空，为“20015”代表不是正确的手机号，
			// “200”代表在商户的覆盖区域内，messge的值为商户的覆盖区域，
			// “201"代表的不在商户的覆盖区域内，message的值为商户的覆盖区域
			if (update.getCode().equals("201")) {
				List<MoreRedpacketEntity> moreRedpacketEntities=moreRedpacketService.queryMoreRedpacket(memberEntity.getPhoneNumber());
				request.setAttribute("mediaurl",ResourceUtil.getConfigByName("media.url.prefix")+"/");
				request.setAttribute("url",ResourceUtil.getConfigByName("domain")+"/");
				request.setAttribute("moreRedpacketEntities",moreRedpacketEntities);
				request.setAttribute("message","该公众号不支持您手机号所在区域的红包，");
				if (moreRedpacketEntities.isEmpty()) {
					return new ModelAndView("weixin/personalredpacket/sad");
				}
				return new ModelAndView("weixin/personalredpacket/moreRedpacketList");
			}

			//根据手机号码查询 流量值
			Gson gson1 = new Gson();
			Type userFlowType = new TypeToken<UserFlowAccountBean>(){}.getType();
            if (!allowNotBindPhoneNumber) {
                String url1 = ResourceUtil.getConfigByName("jfinalUrl-config") + "chargeflow/QueryFlowAccountbyPhone";
                JSONObject myJsonObjectFlow = new JSONObject();
                myJsonObjectFlow.accumulate("phoneNumber", memberEntity.getPhoneNumber());
                JSONObject content = HttpUtil.httpPost(url1, myJsonObjectFlow, false);
                String strContent = gson1.toJson(content);

                UserFlowAccountBean jsonBean = gson1.fromJson(strContent, userFlowType);
                UserFlowAccountBean.DataEntity accountBean = jsonBean.getData();
                String provinceFlowValue = String.valueOf(accountBean.getProvinceFlowValue());
                String countryFlowValue = String.valueOf(accountBean.getCountryFlowValue());
                request.setAttribute("provinceFlowValue", provinceFlowValue);
                request.setAttribute("countryFlowValue", countryFlowValue);
            }

            //查询个人剩余红包发送个数
			String sql="select count(*) count from weixin_personalredpacket t where openId='"+openId+"' and accountId='"+hdEntity.getAccountid()+"' and redpacketsetId='"+hdEntity.getId()+"'";
			int count=this.personalRedpacketService.getCount(sql);
			int leftCount=(3-count)>0?(3-count):0;
			request.setAttribute("count",leftCount );
			Map<String, String> map = generateShareLink(request, hdEntity.getAccountid());
	        request.getSession().setAttribute("map", map);
			Map ad = this.adService.getPublishedAd(weixinAccountEntity.getAcctId(), AdPublishPosition.weixinRedpacket.getCode());
			String accountType = weixinAccountEntity.getAccounttype();
			request.getSession().setAttribute("accountType", accountType);
			request.getSession().setAttribute("ad", ad);
			request.getSession().setAttribute("acctEntity", acctEntity);
			
			String hdUrl="weixin/personalredpacket/makeRedpacket";
			sb.append("_hdUrl:"+hdUrl);
			return new ModelAndView(hdUrl);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("common/404");
		} finally {
			Long end=System.currentTimeMillis();
			sb.append("_time:"+(end-start));
			LOGGER.info(sb.toString());
		}
	}
    
    public Map<String, String> generateShareLink(HttpServletRequest request, String accountid) throws Exception {
        WeixinAccountEntity account = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", accountid);
        // 1 认证服务号 2 认证订阅号 3 未认证服务号  4 未认证订阅号
        String appId = account.getAccountappid();
        String ticket = weixinAccountService.getSignature(account.getId());

        // 注意 URL 一定要动态获取，不能 hardcode
        String url = request.getRequestURL().toString();
        Map<String, String> ret = SignUtil.sign(ticket, url);
        ret.put("appId", appId);
        return ret;
    }

	@RequestMapping(params = "doMake")
	@ResponseBody
	public AjaxJson doMake(HttpServletRequest request) {
		Long start=System.currentTimeMillis();  //方法开始时间
		StringBuffer sb=new StringBuffer();
		AjaxJson j = new AjaxJson();
		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("continueFlag", true);

		String hdId = request.getParameter("hdId");  //活动Id
		String openId = request.getParameter("openId");  //
		String accountId=request.getParameter("accountId");  //商户Id

		//TODO 暂时将用户制作红包的数量定位20个
		// String redpacketNum=request.getParameter("redpacketNum");  //红包数量
        Integer redpacketNum = 20;

		String blessing=request.getParameter("blessing");  //祝福语
        //根据活动id查询该次活动设置的流量值,也就是制作的时候使用的流量值
        PersonalRedpacketSetEntity personalRedpacketSetEntity=this.systemService.getEntity(PersonalRedpacketSetEntity.class,hdId);
        //        String flowValue=request.getParameter("flowValue");  //红包流量值
        double flowValue=personalRedpacketSetEntity.getRedpacketValue();  //红包流量值
        //对活动的日期进行判断
        Date curtime=new Date();
        if(curtime.getTime()>personalRedpacketSetEntity.getEndtime().getTime()){
            params.put("error", "invalid");
            params.put("msg", "请求超时，活动无效！");
            j.setSuccess(false);
            j.setAttributes(params);
            sb.append("openIdIsIllegal");
            return j;
        }

		if(StringUtils.isBlank(openId)){
			params.put("error", "invalid");
			params.put("msg", "请求超时，请重新打开该页面！");
			j.setSuccess(false);
			j.setAttributes(params);
			sb.append("openIdIsIllegal");
			return j;
		}
		sb.append("_hdid:"+hdId+"_openId:"+openId);

        // 如果用户的手机号码在黑名单列表中，禁止其进一步的操作
        WeixinMemberEntity weixinMemberEntity = this.weixinMemberService.getWeixinMemberEntityByOpenIdAndAccountId(openId, accountId);
        if (null != weixinMemberEntity) {
            String phoneNumber = weixinMemberEntity.getPhoneNumber();
            if (StringUtils.isNotBlank(phoneNumber)) {
                if (weixinMemberService.getPhoneNumberInBlackList(phoneNumber)) {
                    params.put("error", "invalid");
                    params.put("msg", "您的权限不足，无法操作");
                    params.put("result", "false");
                    j.setSuccess(false);
                    j.setAttributes(params);
                    sb.append(MessageFormat.format("用户的手机号{0}为黑名单列表中的手机号码，禁止其进行操作", phoneNumber));
                    return j;
                }
            }
        }

        try {
			if(flowValue<=0){
				params.put("error", "invalid");
				params.put("msg", "又来偷流量了！您的IP为：" + request.getRemoteHost());
				params.put("result", "false");
				j.setSuccess(false);
				j.setAttributes(params);
				sb.append("又来偷流量了：IP为：" + request.getRemoteHost());
				return j;
			}
            // 领取红包的时候查看领取的总的红包流量值是否大于红包的最大的流量值
            if (flowValue > personalRedpacketSetEntity.getSubsidyValue()) {
                params.put("error", "invalid");
                params.put("msg", "商户流量不足！");
                params.put("result", "false");
                j.setSuccess(false);
                j.setAttributes(params);
                return j;
            }
            // 校验openId是否有效
            weixinMemberEntity = this.weixinMemberService.getWeixinMemberEntityByOpenIdAndAccountId(openId, accountId);
            if (null == weixinMemberEntity) {
                params.put("error", "invalid");
                params.put("msg", "您尚未关注该商家");
                params.put("result", "false");
                j.setSuccess(false);
                j.setAttributes(params);
                sb.append(MessageFormat.format("警告：在用户{0}制作红包时，发现用户尚未关注商家{1}，禁止用户制作红包", openId, accountId));
                return j;
            }
            // 制作红包的剩余数量

			//判断流量补贴流量是否充足
			Double leftflow=personalRedpacketSetEntity.getSubsidyValue()-personalRedpacketSetEntity.getFlowSendValue();
			if (leftflow < flowValue) {
				params.put("error", "invalid");
				params.put("msg", "商户流量不足！");
				params.put("result", "false");
				j.setSuccess(false);
				j.setAttributes(params);
				return j;
			}
//			PersonalRedpacketSetEntity personalRedpacketSetEntity=this.systemService.getEntity(PersonalRedpacketSetEntity.class,hdId);

			Double flowSendValue=personalRedpacketSetEntity.getFlowSendValue();
			flowSendValue=flowSendValue+flowValue;
			personalRedpacketSetEntity.setFlowSendValue(flowSendValue);
			this.systemService.saveOrUpdate(personalRedpacketSetEntity);
			//保存红包纪录
			PersonalRedpacketEntity personalRedpacketEntity=new PersonalRedpacketEntity();
			personalRedpacketEntity.setOpenId(openId);
			personalRedpacketEntity.setAccountId(accountId);
			personalRedpacketEntity.setRedpacketNum(redpacketNum);
			personalRedpacketEntity.setFlowvalue(flowValue);
			personalRedpacketEntity.setRedpacketsetId(hdId);
			personalRedpacketEntity.setBlessing(blessing);
			personalRedpacketEntity.setCreateTime(new Date());
			this.systemService.saveOrUpdate(personalRedpacketEntity);

			params.put("msg", "创建成功！");
			params.put("result", "true");
			params.put("id",personalRedpacketEntity.getId());
			params.put("total", "34");
			j.setSuccess(true);
			j.setAttributes(params);
			return j;
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			params.put("error", "invalid");
			params.put("msg", "意外的错误！");
			j.setSuccess(false);
			j.setAttributes(params);
			return j;
		} finally {
			Long end=System.currentTimeMillis();
			sb.append("_time:"+(end-start));
			LOGGER.info(sb.toString());
		}
	}




	@RequestMapping(params = "shareRedpacket")
	public ModelAndView shareRedpacket(HttpServletRequest request, HttpServletResponse response) {
		//红包信息
		String id=request.getParameter("id");
		PersonalRedpacketEntity personalRedpacketEntity=this.systemService.getEntity(PersonalRedpacketEntity.class,id);
		request.setAttribute("personalRedpacketEntity",personalRedpacketEntity);

		//粉丝信息
		List<WeixinMemberEntity> memberEntities=this.systemService.findHql("from WeixinMemberEntity t where 1=1  and t.openId='"+personalRedpacketEntity.getOpenId()+"' and t.accountId='"+personalRedpacketEntity.getAccountId()+"'",null);
		WeixinMemberEntity memberEntity=null;
		if (memberEntities.size() > 0) {
			memberEntity=memberEntities.get(0);
		}
		request.setAttribute("memberEntity", memberEntity);

		//商户信息
		WeixinAccountEntity weixinAccountEntity=this.systemService.getEntity(WeixinAccountEntity.class,personalRedpacketEntity.getAccountId());
		request.setAttribute("weixinAccountEntity", weixinAccountEntity);

		//红包设置信息
		PersonalRedpacketSetEntity personalRedpacketSetEntity=this.systemService.getEntity(PersonalRedpacketSetEntity.class,personalRedpacketEntity.getRedpacketsetId());
		request.setAttribute("personalRedpacketSetEntity",personalRedpacketSetEntity);
		personalRedpacketSetEntity.getFlowtype(); //TODO:商户覆盖区域

		//分享
		String jsapi_ticket =weixinAccountService.getSignature(weixinAccountEntity.getId());
		// 注意 URL 一定要动态获取，不能 hardcode
		String url = request.getRequestURL().toString();
		String param = request.getQueryString();
		url = url + "?" + param;

		Map<String, String> ret = SignUtil.sign(jsapi_ticket, url);
		for (Map.Entry entry : ret.entrySet()) {
			LOGGER.info(entry.getKey() + ", " + entry.getValue());
		}
		ret.put("appId",weixinAccountEntity.getAccountappid());
		request.setAttribute("ret",ret);

		//链接
		String link=ResourceUtil.getConfigByName("domain")+"/"+"makeRedpacketController.do?makeRedpacket&hdid=4028aa4f5277a2ea015277afd00a005c";
		request.setAttribute("link",link);
		LOGGER.info(link);
		return new ModelAndView("weixin/personalredpacket/shareRedpacket");

	}

	public Double leftFlow(String hdId){
		PersonalRedpacketSetEntity personalRedpacketSetEntity=this.systemService.getEntity(PersonalRedpacketSetEntity.class,hdId);
		//剩余补贴流量
		Double sendflow=null;
		Double leftflow=null;
		Double totalflow=personalRedpacketSetEntity.getSubsidyValue();
		String sqlflow="select sum(flowValue) as sendflow from weixin_personalredpacket where redpacketsetId='"+personalRedpacketSetEntity.getId()+"'";
		Connection connection = null;
		Statement stmt = null;
		ResultSet es = null;
		try {
			try {
                //创建的jdbc连接语句
                connection = ConnectionsManager.getMysqlConn();
                stmt = connection.createStatement();
                es = stmt.executeQuery(sqlflow);
                while (es.next()) {
                    sendflow=es.getDouble("sendflow");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return 0.0;
            } finally {
                es.close();
                stmt.close();
                connection.close();
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		leftflow=totalflow-sendflow;
		return leftflow>0?leftflow:0;
	}
}

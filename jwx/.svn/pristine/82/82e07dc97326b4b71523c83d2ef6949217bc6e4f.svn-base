package weixin.shop.controller;
import weixin.business.entity.WeixinCardEntity;
import weixin.business.entity.WeixinLocationEntity;
import weixin.business.service.WeixinCardServiceI;
import weixin.business.service.WeixinLocationServiceI;
import weixin.goods.entity.WeixinShopCategoryEntity;
import weixin.goods.entity.WeixinShopGoodsEntity;
import weixin.goods.service.WeixinShopCategoryServiceI;
import weixin.goods.service.WeixinShopGoodsServiceI;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.entity.WeixinOpenPlatformEntity;
import weixin.guanjia.account.service.WeixinOpenPlatformServiceI;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.member.entity.WeixinCoinBalanceEntity;
import weixin.member.entity.WeixinMemberEntity;
import weixin.payment.entity.WeixinPaymentConEntity;
import weixin.payment.entity.WeixinUsergetcardEntity;
import weixin.shop.entity.WeixinOrderDetailEntity;
import weixin.shop.entity.WeixinOrderEntity;
import weixin.shop.entity.WeixinShopAddressEntity;
import weixin.shop.entity.WeixinShopAppraiseEntity;
import weixin.shop.entity.WeixinShopCartEntity;
import weixin.shop.entity.WeixinShopEntity;
import weixin.shop.service.WeixinOrderServiceI;
import weixin.shop.util.IdUtil;
import weixin.shop.util.PayCommonUtil;
import weixin.shop.util.XMLUtil;

import java.util.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.web.system.service.SystemService;

import java.io.UnsupportedEncodingException;

import org.jeecgframework.core.util.ResourceUtil;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;

/**
 * 购物商城
 * @author Administrator
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/shopController")
public class ShopController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = Logger.getLogger(ShopController.class);

	@Autowired
	private WeixinShopGoodsServiceI weixinShopGoodsService;
	
	@Autowired
	private WeixinShopCategoryServiceI weixinShopCategoryService;
	
	@Autowired
	private WeixinLocationServiceI weixinLocationService;
	
	@Autowired
	private WeixinCardServiceI weixinCardService;

    @Autowired
    private WeixinOrderServiceI weixinOrderService;
	
	@Autowired
	private SystemService systemService;
	
    @Autowired
    private WeixinOpenPlatformServiceI weixinOpenPlatformService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 设置当前公众号session
	 * @param request
	 */
	public void setAccountID(HttpServletRequest request){

		if(null != request.getParameter("accountid"))
			request.getSession().setAttribute(ACCOUNTID, request.getParameter("accountid"));
	}
	
	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(params = "shopindex")
	public ModelAndView shopindex(HttpServletRequest request) {
		
		this.setAccountID(request);
		
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);
		
		WeixinAccountEntity account = this.systemService.get(WeixinAccountEntity.class, accountid);
		String dyt="_dyt_";
		LOGGER.info(dyt+request.getParameter("code"));
		LOGGER.info(dyt+request.getParameter("state"));
		/**网页授权--开始*/
		try { 
		 
			//粉丝为空，则通过网页授权获取粉丝ID
			if(request.getSession().getAttribute(MEMBERID) == null){
				
				String code = request.getParameter("code");
				
				//第一步：用户同意授权，获取code
				if(code == null){
				    WeixinOpenPlatformEntity platform = weixinOpenPlatformService.get(WeixinOpenPlatformEntity.class, account.getOpenPlatformId());
					//获取用户信息
                    String localhosturl = ResourceUtil.getDomain()+"/";

                    String url = "";
                    try {
                        url = URLEncoder.encode(localhosturl + "shopController.do?shopindex&accountid="+accountid, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

					String requestUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&component_appid=%s#wechat_redirect";
				
					requestUrl = String.format(requestUrl, account.getAccountappid(), url, "snsapi_userinfo", accountid, platform.getAppId());
					return new ModelAndView("redirect:" + requestUrl);
				
				}else{
										
					accountid = request.getParameter("state");
					
					//第二步：通过code换取网页授权access_token
					String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
					requestUrl = requestUrl.replace("APPID", account.getAccountappid());
					requestUrl = requestUrl.replace("SECRET", account.getAccountappsecret());
					requestUrl = requestUrl.replace("CODE", code);
					
					JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
					if (null != jsonObject) {
						try {
						
							//用户id
							String openId = jsonObject.getString("openid");
							
							String hql = "from WeixinMemberEntity t where t.openId='"+openId+"' and t.accountId='"+accountid+"'";
							WeixinMemberEntity weixinMember = (WeixinMemberEntity) systemService.findByQueryString(hql).get(0);
							
							request.getSession().setAttribute(MEMBERID, weixinMember.getId());
						} catch (Exception e) {
						
							systemService.addLog("code换取网页授权access_token出错，原因："+e.getMessage(), Globals.Log_Type_OTHER, Globals.Log_Leavel_ERROR);
						}
					}				
				}			
			}
		} catch (Exception e) {
			
			systemService.addLog("用户授权出错，原因："+e.getMessage(), Globals.Log_Type_OTHER, Globals.Log_Leavel_ERROR);
		}
		/**网页授权--结束*/
		LOGGER.info("网页授权");
		//当前粉丝信息
		String memberid = (String) request.getSession().getAttribute(MEMBERID);
		WeixinMemberEntity weixinMember = (WeixinMemberEntity) systemService.get(WeixinMemberEntity.class, memberid);
		request.setAttribute("weixinMember", weixinMember);
		LOGGER.info(dyt+"1");
		//request.getSession().setAttribute(MEMBERID,"402883874f44c014014f44cc2fb70001");
		
		//促销商品
		List hotGoodsList = weixinShopGoodsService.getHotGoodsList(accountid);
		request.setAttribute("hotGoodsList", hotGoodsList);
		LOGGER.info(dyt+"2");
		//新品推荐
		List newGoodsList = weixinShopGoodsService.getNewGoodsList(accountid);
		request.setAttribute("newGoodsList", newGoodsList);
		LOGGER.info(dyt+"3");
		//热销商品
		List hotSaleList = weixinShopGoodsService.getHotSaleGoodsList(accountid);
		request.setAttribute("hotSaleList", hotSaleList);
		LOGGER.info(dyt+"4");
		//分类信息
		List weixinShopCategoryList = weixinShopCategoryService.findByProperty(WeixinShopCategoryEntity.class, "sellerId", accountid);
		request.setAttribute("weixinShopCategoryList", weixinShopCategoryList);
		LOGGER.info(dyt+"5");
		//商城信息
		WeixinShopEntity WeixinShopEntity = systemService.findUniqueByProperty(WeixinShopEntity.class, "accountid", accountid);
		request.getSession().setAttribute("WeixinShopEntity", WeixinShopEntity);
		LOGGER.info(dyt+"6");
        request.setAttribute("domain", ResourceUtil.getConfigByName("media.url.prefix")+"/");
		return new ModelAndView("shop/shopindex");
	}

	
	/**
	 * 商品分类
	 * 
	 * @return
	 */
	@RequestMapping(params = "category")
	public ModelAndView category(HttpServletRequest request) {
		
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);
		
		//分类信息
		List weixinShopCategoryList = weixinShopCategoryService.findByProperty(WeixinShopCategoryEntity.class, "sellerId", accountid);
		request.setAttribute("weixinShopCategoryList", weixinShopCategoryList);
		
		//促销商品
		List hotGoodsList = weixinShopGoodsService.getHotGoodsList(accountid);
		request.setAttribute("hotGoodsList", hotGoodsList);

		return new ModelAndView("shop/category");
	}
	
	/**
	 * 处理登录
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "dologin")
	public ModelAndView dologin(HttpServletRequest request) {
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		return new ModelAndView("shop/shopindex");
	}
	
	/**
	 * 跳转到登录
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "gologin")
	public ModelAndView gologin(HttpServletRequest request) {
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		return new ModelAndView("shop/login");
	}
	
	/**
	 * 处理注册
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doregister")
	public ModelAndView doregister(HttpServletRequest request) {
		
		return new ModelAndView("shop/register-result");
	}
	
	/**
	 * 跳转到注册
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goregister")
	public ModelAndView goregister(HttpServletRequest request) {
		
		return new ModelAndView("shop/register");
	}
	
	
	/**
	 * 个人中心
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(params = "userindex")
	public ModelAndView user(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);
		String memberid = (String) request.getSession().getAttribute(MEMBERID);
		
		//String hql = "from WeixinMemberEntity t where t.openId='"+memberid+"' and t.accountId='"+accountid+"'";
		WeixinMemberEntity weixinMember = (WeixinMemberEntity) systemService.get(WeixinMemberEntity.class, memberid);
		
		request.setAttribute("weixinMember", weixinMember);
		
		return new ModelAndView("shop/userindex");
	}
	
	/**
	 * 购物车
	 * 
	 * @return
	 */
	@RequestMapping(params = "shopcar")
	public ModelAndView shopcar(HttpServletRequest request) {
		
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);
		
		String memberid = (String) request.getSession().getAttribute(MEMBERID);
		
		String hql = "from WeixinShopCartEntity t where t.memberId='"+memberid+"' and accountid='"+accountid+"' order by createDate desc";
		List<WeixinShopCartEntity> weixinShopCartList = systemService.findByQueryString(hql);
		request.setAttribute("weixinShopCartList", weixinShopCartList);
        request.setAttribute("domain", ResourceUtil.getConfigByName("media.url.prefix")+"/");
		
		return new ModelAndView("shop/shopcar");
	}

	private void addShopCardInfo(HttpServletRequest request){

        String accountid = (String) request.getSession().getAttribute(ACCOUNTID);

        String memberid = (String) request.getSession().getAttribute(MEMBERID);
        String hql = "from WeixinShopCartEntity t where t.memberId='"+memberid+"' and accountid='"+accountid+"' order by createDate desc";
        List<WeixinShopCartEntity> weixinShopCartList = systemService.findByQueryString(hql);
        request.setAttribute("weixinShopCartList", weixinShopCartList);
        request.setAttribute("domain", ResourceUtil.getConfigByName("media.url.prefix")+"/");
    }

	/**
	 * 加入购物车
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "addshopcar")
	public ModelAndView addshopcar(HttpServletRequest request) {
		AjaxJson j=new AjaxJson();

        Map<String, Object> params = new HashMap<String, Object>();
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);
		
		String goodsid = request.getParameter("goodsid");
		WeixinShopGoodsEntity weixinShopGoodsEntity = systemService.get(WeixinShopGoodsEntity.class, goodsid);
		
		String memberid = (String) request.getSession().getAttribute(MEMBERID);
		
		String hql = "from WeixinShopCartEntity t where t.memberId='"+memberid+"' and t.weixinShopGoodsEntity.id='"+goodsid+"'";
		List<WeixinShopCartEntity> weixinShopCartList = systemService.findByQueryString(hql);
		
		//如果存折，则累加数量
		if(null != weixinShopCartList && weixinShopCartList.size()>0){
			
			WeixinShopCartEntity weixinShopCartEntity = weixinShopCartList.get(0);
			weixinShopCartEntity.setQuantity(weixinShopCartEntity.getQuantity()+1);
			
			systemService.save(weixinShopCartEntity);
		}else{
			
			//新增
			WeixinShopCartEntity weixinShopCart = new WeixinShopCartEntity();
			weixinShopCart.setMemberId(memberid);
			weixinShopCart.setWeixinShopGoodsEntity(weixinShopGoodsEntity);
			weixinShopCart.setCreateDate(new Date());
			weixinShopCart.setQuantity(1);
			weixinShopCart.setAccountid(accountid);
			
			systemService.save(weixinShopCart);
		}
		return this.shopcar(request);
	}

	/**
	 * 删除购物车一条记录
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "delshopcar")
	public ModelAndView delshopcar(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		systemService.deleteEntityById(WeixinShopCartEntity.class, id);
		
		return this.shopcar(request);
	}
	
	/**
	 * 查询订单
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "orders")
	public ModelAndView orders(HttpServletRequest request) {
		
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);
		String memberid = (String) request.getSession().getAttribute(MEMBERID);
		
		//用户订单
		String hql = "from WeixinOrderEntity t where t.weixinMemberEntity.id='"+memberid+"' and t.accountid='"+accountid+"' and t.isShow='0' order by createDate desc";
		List<WeixinOrderEntity> weixinOrderList = systemService.findByQueryString(hql);
		request.setAttribute("weixinOrderList", weixinOrderList);
        request.setAttribute("domain", ResourceUtil.getConfigByName("media.url.prefix")+"/");
		return new ModelAndView("shop/orders");
	}

	/**
	 * 确认订单
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "confirmOrder")
	public ModelAndView confirmOrder(HttpServletRequest request) {
		
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);
		String memberid = (String) request.getSession().getAttribute(MEMBERID);
		
		String[] cartids = request.getParameterValues("cartids");//选中的购物车记录
		StringBuffer shopCartIds = new StringBuffer();
		//查询选择要提交的购物车商品
		String hql = "from WeixinShopCartEntity t left join fetch t.weixinShopGoodsEntity where t.memberId='"+memberid+"' and t.id in(";
				
		for(int i=0;i<cartids.length;i++){
			
			if(i==0){
				hql += "'"+cartids[i]+"'";
				shopCartIds.append(cartids[i]);
			}else{
				
				hql += ",'"+cartids[i]+"'";
				shopCartIds.append(",");
				shopCartIds.append(cartids[i]);
			}	
		}
		hql += ")";
		
		List<WeixinShopCartEntity> weixinShopCartList = systemService.findByQueryString(hql);
		request.setAttribute("weixinShopCartList", weixinShopCartList);
		request.setAttribute("cartids", shopCartIds);
				
		//计算商品总金额
		BigDecimal amount=new BigDecimal(0);
		//计算运费
		BigDecimal expressPrice=new BigDecimal(0);
		
		for(int i=0;i<weixinShopCartList.size();i++){
			
			WeixinShopCartEntity tempCart = weixinShopCartList.get(i);
			WeixinShopGoodsEntity weixinShopGoodsEntity = tempCart.getWeixinShopGoodsEntity();
			
			//金额计算，先取优惠价，如果没有则取正常销售价格
			if(null != weixinShopGoodsEntity.getRealPrice()){
				
				if(weixinShopGoodsEntity.getRealPrice().compareTo(new BigDecimal(0)) == 1)
					amount = amount.add(weixinShopGoodsEntity.getRealPrice().multiply(new BigDecimal(tempCart.getQuantity())));
			}else{
				
				if(null != weixinShopGoodsEntity.getPrice()){
					
					if(weixinShopGoodsEntity.getPrice().compareTo(new BigDecimal(0)) == 1)
						amount = amount.add(weixinShopGoodsEntity.getPrice().multiply(new BigDecimal(tempCart.getQuantity())));
				}
			}
			
			//运费计算，取运费大的
			if(null != weixinShopGoodsEntity.getExpressPrice() && weixinShopGoodsEntity.getExpressPrice().compareTo(new BigDecimal(0)) == 1){
				
				if(weixinShopGoodsEntity.getExpressPrice().compareTo(expressPrice) == 1)
					expressPrice = weixinShopGoodsEntity.getExpressPrice();
			}
		}
		
		//订单总额
		BigDecimal totalAmount = amount.add(expressPrice);
		
		request.setAttribute("amount", amount);
		request.setAttribute("expressPrice", expressPrice);
		request.setAttribute("totalAmount", totalAmount);
		
		//查询收货地址
		String hql2 = "from WeixinShopAddressEntity t where t.openId='"+memberid+"' and t.accountid='"+accountid+"' order by isDefault desc";
		List<WeixinShopAddressEntity> weixinShopAddressList = systemService.findByQueryString(hql2);
		
		request.setAttribute("weixinShopAddressList", weixinShopAddressList);
        request.setAttribute("domain", ResourceUtil.getConfigByName("media.url.prefix")+"/");
		return new ModelAndView("shop/confirmorder");
	}
	
	/**
	 * 创建订单
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "createOrder")
	public ModelAndView createOrder(HttpServletRequest request) {
		
		String amount = request.getParameter("amount");
		String expressPrice = request.getParameter("expressPrice");
		String totalAmount = request.getParameter("totalAmount");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String address = request.getParameter("address");
		//String postcode = request.getParameter("postcode");
		String deliveryName = request.getParameter("deliveryName");
		String deliveryPhone = request.getParameter("deliveryPhone");
		String leaveWord = request.getParameter("leaveWord");
		
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);
		
		String memberid = (String) request.getSession().getAttribute(MEMBERID);
		WeixinMemberEntity weixinMember = systemService.get(WeixinMemberEntity.class, memberid);
		
		WeixinOrderEntity weixinOrderEntity = new WeixinOrderEntity();
		weixinOrderEntity.setAccountid(accountid);
		weixinOrderEntity.setWeixinMemberEntity(weixinMember);
		weixinOrderEntity.setCreateName(weixinMember.getNickName());
		weixinOrderEntity.setCreateDate(new Date());
		weixinOrderEntity.setProvince(province);
		weixinOrderEntity.setCity(city);
		weixinOrderEntity.setDistrict(district);
		weixinOrderEntity.setAddress(address);
		//weixinOrderEntity.setPostcode(postcode);
		weixinOrderEntity.setDeliveryName(deliveryName);
		weixinOrderEntity.setDeliveryPhone(deliveryPhone);
		weixinOrderEntity.setLeaveWord(leaveWord);
		
		weixinOrderEntity.setOrderAmount(new BigDecimal(totalAmount));
		weixinOrderEntity.setPayAmount(new BigDecimal(totalAmount));
		weixinOrderEntity.setFreight(new BigDecimal(expressPrice));
		weixinOrderEntity.setStatus("0");
		weixinOrderEntity.setIsShow("0");
		weixinOrderEntity.setOrderNo(IdUtil.nextCode());
				
		//保存订单
		systemService.save(weixinOrderEntity);
		
		//保存明细
		String[] cartids = request.getParameter("cartids").split(",");//选中的购物车记录
		//查询选择要提交的购物车商品
		String hql = "from WeixinShopCartEntity t where t.memberId='"+memberid+"' and t.id in(";
				
		for(int i=0;i<cartids.length;i++){
			
			if(i==0)
				hql += "'"+cartids[i]+"'";
			else
				hql += ",'"+cartids[i]+"'";
		}
		hql += ")";
		
		List<WeixinShopCartEntity> weixinShopCartList = systemService.findByQueryString(hql);
		for(int i=0;i<weixinShopCartList.size();i++){
			
			WeixinShopCartEntity weixinShopCartEntity = weixinShopCartList.get(i);
			
			WeixinOrderDetailEntity weixinOrderDetailEntit = new WeixinOrderDetailEntity();
			weixinOrderDetailEntit.setOrderId(weixinOrderEntity.getId());
			WeixinShopGoodsEntity weixinShopGoodsEntity = weixinShopCartEntity.getWeixinShopGoodsEntity();
			weixinOrderDetailEntit.setWeixinShopGoodsEntity(weixinShopGoodsEntity);
			weixinOrderDetailEntit.setQuantity(weixinShopCartEntity.getQuantity());
			if(null != weixinShopGoodsEntity.getRealPrice())
				weixinOrderDetailEntit.setPrice(weixinShopCartEntity.getWeixinShopGoodsEntity().getRealPrice());
			else
				weixinOrderDetailEntit.setPrice(weixinShopCartEntity.getWeixinShopGoodsEntity().getPrice());
			systemService.save(weixinOrderDetailEntit);
		}
		systemService.deleteAllEntitie(weixinShopCartList);
		
		//跳转到付款页面
		return this.toPay(request, weixinOrderEntity.getId());
	}
	
	/**
	 * 去支付页面
	 * @param request
	 * @param orderId
	 * @return
	 */
	@RequestMapping(params = "toPay")
	public ModelAndView toPay(HttpServletRequest request, String orderId) {
		
		WeixinOrderEntity weixinOrderEntity = systemService.get(WeixinOrderEntity.class, orderId);
		request.setAttribute("weixinOrderEntity", weixinOrderEntity);
		
		return new ModelAndView("shop/dopay");
	}
	
	/**
	 * 支付失败提示页面
	 * @param request
	 * @param orderId
	 * @return
	 */
	@RequestMapping(params = "payfail")
	public ModelAndView payfail(HttpServletRequest request) {

		return new ModelAndView("shop/payfail");
	}
	
	@RequestMapping(params = "paysucc")
	public ModelAndView paysucc(HttpServletRequest request) {

		return new ModelAndView("shop/paysucc");
	}
	
	/**
	 * 提交支付
	 * @param request
	 * @param orderId
	 * @return
	 */
	@RequestMapping(params = "doPay")
	@ResponseBody
	public String doPay(HttpServletRequest request, String orderId) {
		
		WeixinOrderEntity weixinOrderEntity = systemService.get(WeixinOrderEntity.class, orderId);		

		try {
			
			//查询当前公众号信息
			String accountid = (String) request.getSession().getAttribute(ACCOUNTID);
		
			String localhosturl = ResourceUtil.getDomain()+"/";
			
			String memberid = (String) request.getSession().getAttribute(MEMBERID);
			WeixinMemberEntity weixinMember = systemService.get(WeixinMemberEntity.class, memberid);
			
			//查询商户号
			WeixinPaymentConEntity weixinPaymentConEntity = (WeixinPaymentConEntity) systemService.findByQueryString("from WeixinPaymentConEntity t where t.payType='1' and accountid='"+accountid+"'").get(0);
			String mch_id = weixinPaymentConEntity.getPartnerId();
			String appid = weixinPaymentConEntity.getAppId();
			String key = weixinPaymentConEntity.getPartnerKey();
			
			//随机数
			String nonce_str = UUID.randomUUID().toString().trim().replaceAll("-", "");
			
			BigDecimal OrderAmount = weixinOrderEntity.getPayAmount().multiply(new BigDecimal(100));
			DecimalFormat decimalFormat = new DecimalFormat("#0");
			String total_fee = Integer.parseInt(decimalFormat.format(OrderAmount))+"";
			
			String out_trade_no = weixinOrderEntity.getId();
			String openid = weixinMember.getOpenId();
			
			String spbill_create_ip = InetAddress.getLocalHost().getHostAddress();
			
			String notify_url = localhosturl+"wxpay.do?api";
			
			SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
			parameters.put("appid", StringUtils.trim(appid));
			parameters.put("mch_id", StringUtils.trim(mch_id));
			parameters.put("nonce_str", StringUtils.trim(nonce_str));
			parameters.put("body", "微信购物");
			parameters.put("out_trade_no", StringUtils.trim(out_trade_no));
			parameters.put("total_fee", StringUtils.trim(total_fee));
			parameters.put("spbill_create_ip",StringUtils.trim(spbill_create_ip));
			parameters.put("notify_url", StringUtils.trim(notify_url));
			parameters.put("trade_type", "JSAPI");
			parameters.put("openid", StringUtils.trim(openid));
			String signt = PayCommonUtil.createSign("UTF-8", parameters, StringUtils.trim(key));
			parameters.put("sign", signt);
			String requestXML = PayCommonUtil.getRequestXml(parameters);

			String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
			String result =PayCommonUtil.httpsRequest(url, "POST", requestXML);
			//解析微信返回的信息，以Map形式存储便于取值
			Map<String, String> map = XMLUtil.doXMLParse(result);

			
			
			
			SortedMap<Object,Object> params = new TreeMap<Object,Object>();
	        params.put("appId", appid);
	        params.put("timeStamp", Long.toString(new Date().getTime()));
	        params.put("nonceStr", nonce_str);
	        params.put("package", "prepay_id="+map.get("prepay_id"));
	        params.put("signType", "MD5");
	        String paySign =  PayCommonUtil.createSign("UTF-8", params, key);
	        params.put("packageValue", "prepay_id="+map.get("prepay_id"));    //这里用packageValue是预防package是关键字在js获取值出错
	        params.put("paySign", paySign);                                                          //paySign的生成规则和Sign的生成规则一致
	        params.put("sendUrl", "shopController.do?paysucc"); //付款成功后跳转的页面
	        String userAgent = request.getHeader("user-agent");
	        char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger")+15);
	        params.put("agent", new String(new char[]{agent}));//微信版本号，用于前面提到的判断用户手机微信的版本是否是5.0以上版本。
	        String json = JSONObject.fromObject(params).toString();

	        return json;
		} catch (UnknownHostException ue) {
			
			ue.printStackTrace();
		} catch (Exception e){
			
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param orderId
	 * @return
	 */
	@RequestMapping(params = "payOrder")
	@ResponseBody
	public String payOrder(HttpServletRequest request, String orderId) {
		
		WeixinOrderEntity weixinOrderEntity = weixinOrderService.get(WeixinOrderEntity.class, orderId);
        StringBuffer sb = new StringBuffer();
        sb.append("shoppingController_payOrder"+weixinOrderEntity.getStatus());

		if("0".equals(weixinOrderEntity.getStatus())){
			
			weixinOrderEntity.setStatus("1");//已支付
			weixinOrderEntity.setDeliverStatus("0");//未发货
			weixinOrderEntity.setCheckDate(new Date());
			
//			systemService.save(weixinOrderEntity);   //不是保存，而是需要再次进行刷新，错误的来源
            weixinOrderService.updateEntitie(weixinOrderEntity);  //更新订单的信息

			return "1";
		}
        LOGGER.info(sb.toString());
		return "0";
	}

	 
	/**
	 * 确认收货
	 * @param request
	 * @param orderId
	 * @return
	 */
	@RequestMapping(params = "confirmdelivery")
	public ModelAndView confirmdelivery(HttpServletRequest request, String orderId) {
		
		WeixinOrderEntity weixinOrderEntity = systemService.get(WeixinOrderEntity.class, orderId);
		if("1".equals(weixinOrderEntity.getStatus())){
			
			weixinOrderEntity.setStatus("2");
			weixinOrderEntity.setDeliverStatus("2");
			weixinOrderEntity.setIsAppraise("0");
			systemService.saveOrUpdate(weixinOrderEntity);
		}		
		
		return this.orders(request);
	}
	
	/**
	 * 订单详情
	 * @param request
	 * @param orderId
	 * @return
	 */
	@RequestMapping(params = "orderdetail")
	public ModelAndView orderdetail(HttpServletRequest request, String orderId) {
		
		WeixinOrderEntity weixinOrderEntity = systemService.get(WeixinOrderEntity.class, orderId);
        request.setAttribute("domain", ResourceUtil.getConfigByName("media.url.prefix")+"/");
		request.setAttribute("weixinOrderEntity", weixinOrderEntity);
		
		return new ModelAndView("shop/orderdetail");
	}
	
	/**
	 * 取消订单
	 * @param request
	 * @param orderId
	 * @return
	 */
	@RequestMapping(params = "celorder")
	public ModelAndView celorder(HttpServletRequest request, String orderId) {
		
		WeixinOrderEntity weixinOrderEntity = systemService.get(WeixinOrderEntity.class, orderId);
		if("0".equals(weixinOrderEntity.getStatus())){
			
			weixinOrderEntity.setStatus("9");
			systemService.saveOrUpdate(weixinOrderEntity);
		}		
		
		return this.orders(request);
	}
	
	/**
	 * 申请退货、退款
	 * @param request
	 * @param orderId
	 * @return
	 */
	@RequestMapping(params = "refundorder")
	public ModelAndView refundorder(HttpServletRequest request, String orderId) {
		
		WeixinOrderEntity weixinOrderEntity = systemService.get(WeixinOrderEntity.class, orderId);
		
		//已付款、未收货，走退款流程
		if("1".equals(weixinOrderEntity.getStatus()))
			weixinOrderEntity.setStatus("3");
			
		//已付款、已经收货，走退货流程
		if("2".equals(weixinOrderEntity.getStatus()))
			weixinOrderEntity.setStatus("4");
			
		systemService.saveOrUpdate(weixinOrderEntity);
				
		
		return this.orders(request);
	}
	
	/**
	 * 删除订单
	 * @param request
	 * @param orderId
	 * @return
	 */
	@RequestMapping(params = "delorder")
	public ModelAndView delorder(HttpServletRequest request, String orderId) {
		
		WeixinOrderEntity weixinOrderEntity = systemService.get(WeixinOrderEntity.class, orderId);
		
		//已取消的订单直接删除
		if("9".equals(weixinOrderEntity.getStatus())){
			
			systemService.delete(weixinOrderEntity);			
		}
		
		//交易成功的订单，隐藏
		if("2".equals(weixinOrderEntity.getStatus())){
			
			weixinOrderEntity.setIsShow("1");
			systemService.saveOrUpdate(weixinOrderEntity);
		}
		
		return this.orders(request);
	}
	
	/**
	 * 跳转到评价页面
	 * @param request
	 * @param orderId
	 * @return
	 */
	@RequestMapping(params = "toAppraise")
	public ModelAndView toAppraise(HttpServletRequest request, String orderId) {
		
		WeixinOrderEntity weixinOrderEntity = systemService.get(WeixinOrderEntity.class, orderId);

        //添加图片的路径
        request.setAttribute("domain", ResourceUtil.getConfigByName("media.url.prefix")+"/");

		request.setAttribute("weixinOrderEntity", weixinOrderEntity);
		
		return new ModelAndView("shop/toappraise");
	}
	
	/**
	 * 保存评价
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doAppraise")
	public ModelAndView doAppraise(HttpServletRequest request) {
		
		String memberid = (String) request.getSession().getAttribute(MEMBERID);
		WeixinMemberEntity weixinMember = systemService.get(WeixinMemberEntity.class, memberid);
		
		String[] goodsids = request.getParameterValues("goodsId");
		String[] notes = request.getParameterValues("note");
		
		for(int i=0;i<goodsids.length;i++){
			
			if(StringUtils.isNotEmpty(notes[i])){
				
				//
				WeixinShopGoodsEntity weixinShopGoods = weixinShopGoodsService.getEntity(WeixinShopGoodsEntity.class, goodsids[i]);
				
				WeixinShopAppraiseEntity weixinShopAppraiseEntity = new WeixinShopAppraiseEntity();
				
				weixinShopAppraiseEntity.setWeixinShopGoodsEntity(weixinShopGoods);
				weixinShopAppraiseEntity.setNotes(notes[i]);
				weixinShopAppraiseEntity.setCreateDate(new Date());
				weixinShopAppraiseEntity.setStatus(0);
				weixinShopAppraiseEntity.setAccountid(weixinShopGoods.getSellerId());
				weixinShopAppraiseEntity.setOpenId(weixinMember.getId());
				weixinShopAppraiseEntity.setOpenName(weixinMember.getNickName());
				
				systemService.save(weixinShopAppraiseEntity);
			}			
		}

		String orderId = request.getParameter("orderId");
		WeixinOrderEntity weixinOrderEntity = systemService.get(WeixinOrderEntity.class, orderId);
		weixinOrderEntity.setIsAppraise("1");
		systemService.saveOrUpdate(weixinOrderEntity);
		
		return this.orders(request);
	}
	
	/**
	 * 积分查询
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "coin")
	public ModelAndView coin(HttpServletRequest request) {
		
		String memberid = (String) request.getSession().getAttribute(MEMBERID);
		WeixinCoinBalanceEntity weixinCoinBalanceEntity = systemService.findUniqueByProperty(WeixinCoinBalanceEntity.class, "memberid", memberid);
		request.setAttribute("weixinCoinBalanceEntity", weixinCoinBalanceEntity);
		
		return new ModelAndView("shop/coin");
	}
	
	/**
	 * 我的优惠券
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "mycard")
	public ModelAndView mycard(HttpServletRequest request) {
		
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);		
		String memberid = (String) request.getSession().getAttribute(MEMBERID);
		
		WeixinMemberEntity weixinMember = systemService.get(WeixinMemberEntity.class, memberid);
		
		String hql = "from WeixinUsergetcardEntity t where t.accountid='"+accountid+"' and t.openId='"+weixinMember.getOpenId()+"' and t.status='0'";
		List<WeixinUsergetcardEntity> myCardList = systemService.findByQueryString(hql);
		request.setAttribute("myCardList", myCardList);
		
		return new ModelAndView("shop/mycard");
	}
	
	/**
	 * 优惠券中心
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "weixincard")
	public ModelAndView weixincard(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = sdf.format(new Date());
		
		String hql = "from WeixinCardEntity t where t.accountid='"+accountid+"' and status='1' and t.endTimestamp>'"+nowTime+"'";
		List<WeixinCardEntity> weixinCardList = weixinCardService.findByQueryString(hql);
		request.setAttribute("weixinCardList", weixinCardList);
		
		return new ModelAndView("shop/cardlist");
	}
	
	/**
	 * 优惠商品
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "promotion")
	public ModelAndView promotion(HttpServletRequest request) {
		
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);

		//促销商品
		List goodsList = weixinShopGoodsService.getHotGoodsList(accountid);
		request.setAttribute("goodsList", goodsList);
        request.setAttribute("domain", ResourceUtil.getConfigByName("media.url.prefix")+"/");
		return new ModelAndView("shop/promotion");
	}
	
	/**
	 * 门店查找 页面跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "location")
	public ModelAndView location(HttpServletRequest request) {
		
		String accountid = request.getParameter("accountid");
		
		//查询所有门店
		List locationList = weixinLocationService.findByQueryString("from WeixinLocationEntity t where t.accountid='"+accountid+"'");
		request.setAttribute("locationList", locationList);
		
		return new ModelAndView("shop/location");
	}
	
	/**
	 * 门店详情
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "locationDetail")
	public ModelAndView locationDetail(HttpServletRequest request, String id) {
		
		WeixinLocationEntity weixinLocationEntity = weixinLocationService.getEntity(WeixinLocationEntity.class, id);
		request.setAttribute("weixinLocationEntity", weixinLocationEntity);
		
		return new ModelAndView("shop/locationDetail");
	}
	
	/**
	 * 优惠券领取页面
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "cardList")
	public ModelAndView cardList(HttpServletRequest request) {
		
		this.setAccountID(request);
		
		String accountid = (String) request.getSession().getAttribute(ACCOUNTID);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = sdf.format(new Date());
		
		String hql = "from WeixinCardEntity t where t.accountid='"+accountid+"' and t.status='1' and t.endTimestamp>'"+nowTime+"'";
		List<WeixinCardEntity> weixinCardList = weixinCardService.findByQueryString(hql);
		request.setAttribute("weixinCardList", weixinCardList);
		
		return new ModelAndView("shop/cardlist");
	}
}

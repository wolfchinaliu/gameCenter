package weixin.shop.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.cgform.engine.FreemarkerHelper;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weixin.business.entity.WeixinCardEntity;
import weixin.business.entity.WeixinLocationEntity;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.base.entity.Subscribe;
import weixin.guanjia.base.entity.WeixinExpandconfigEntity;
import weixin.guanjia.base.service.SubscribeServiceI;
import weixin.guanjia.base.service.WeixinExpandconfigServiceI;
import weixin.guanjia.core.entity.message.resp.Article;
import weixin.guanjia.core.entity.message.resp.NewsMessageResp;
import weixin.guanjia.core.entity.message.resp.TextMessageResp;
import weixin.guanjia.core.util.MessageUtil;
import weixin.guanjia.menu.entity.MenuEntity;
import weixin.guanjia.menu.service.WeixinMenuServiceI;
import weixin.guanjia.message.dao.TextTemplateDao;
import weixin.guanjia.message.entity.AutoResponse;
import weixin.guanjia.message.entity.AutoResponseDefault;
import weixin.guanjia.message.entity.NewsItem;
import weixin.guanjia.message.entity.NewsTemplate;
import weixin.guanjia.message.entity.ReceiveText;
import weixin.guanjia.message.entity.TextTemplate;
import weixin.guanjia.message.service.AutoResponseDefaultServiceI;
import weixin.guanjia.message.service.AutoResponseServiceI;
import weixin.guanjia.message.service.NewsItemServiceI;
import weixin.guanjia.message.service.NewsTemplateServiceI;
import weixin.guanjia.message.service.ReceiveTextServiceI;
import weixin.guanjia.message.service.TextTemplateServiceI;
import weixin.idea.extend.function.KeyServiceI;
import weixin.member.entity.WeixinGroupEntity;
import weixin.member.entity.WeixinMemberCardEntity;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinGroupServiceI;
import weixin.member.service.WeixinMemberServiceI;
import weixin.member.util.MemberUtil;
import weixin.payment.entity.WeixinPaymentLogEntity;
import weixin.payment.entity.WeixinUsergetcardEntity;
import weixin.shop.entity.WeixinOrderEntity;
import weixin.shop.util.PayCommonUtil;
import weixin.util.DateUtils;

/**
 * 接收微信推送付款通知事件处理
 * @author Administrator
 *
 */
@Service("payReceiveService")
public class PayReceiveService {

	@Autowired
	private SystemService systemService;
	
	private static final Logger logger = Logger.getLogger(PayReceiveService.class);

	/**
	 * 事件处理核心控制方法
	 * @param request
	 * @return
	 */
	public String coreService(HttpServletRequest request) {

		String respMessage = null;
		
		try {
		
			systemService.addLog("付款通知2", Globals.Log_Type_OTHER, Globals.Log_Leavel_ERROR);
			
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			
			String appid = requestMap.get("appid");//公众账号ID
			String mchId = requestMap.get("mch_id");//商户号
			String result_code = requestMap.get("result_code");//SUCCESS
			
			if("SUCCESS".equals(result_code)){
				
				String openid = requestMap.get("openid");//用户
				String total_fee = requestMap.get("total_fee");//支付金额，单位为分
				String bank_type = requestMap.get("bank_type");
				String transactionId = requestMap.get("transaction_id");//微信订单号
				String out_trade_no = requestMap.get("out_trade_no");//订单号
				String attach = requestMap.get("attach");//原样返回的
				
				String hql = "from WeixinMemberEntity t where t.openId='"+openid+"' and t.accountId='"+attach+"'";
				WeixinMemberEntity weixinMember = (WeixinMemberEntity) systemService.findByQueryString(hql).get(0);
				
				//查询付款通知是否已经记录
				String loghql = "from WeixinPaymentLogEntity t where t.transactionId='"+transactionId+"'";
				List<WeixinPaymentLogEntity> logList = systemService.findByQueryString(loghql);
				if(logList == null){
					
					//记录支付记录
					WeixinPaymentLogEntity weixinPaymentLog = new WeixinPaymentLogEntity();
					weixinPaymentLog.setAccountid(attach);
					weixinPaymentLog.setAmount(new BigDecimal(total_fee));
					weixinPaymentLog.setOrderId(out_trade_no);
					weixinPaymentLog.setTransactionId(transactionId);
					weixinPaymentLog.setBankType(bank_type);
					weixinPaymentLog.setOpenid(openid);
					weixinPaymentLog.setMchId(mchId);
					weixinPaymentLog.setCreateDate(new Date());
					weixinPaymentLog.setCreateName(weixinMember.getNickName());
					
					systemService.save(weixinPaymentLog);
				}
				
				//审核订单
				WeixinOrderEntity weixinOrderEntity = systemService.get(WeixinOrderEntity.class, out_trade_no);
				if("0".equals(weixinOrderEntity.getStatus())){
					
					weixinOrderEntity.setStatus("1");//已支付
					weixinOrderEntity.setDeliverStatus("0");//未发货
					systemService.save(weixinOrderEntity);
				}
				
				SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
				parameters.put("return_code", "SUCCESS");
				respMessage = PayCommonUtil.getRequestXml(parameters);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return respMessage;
	}
}

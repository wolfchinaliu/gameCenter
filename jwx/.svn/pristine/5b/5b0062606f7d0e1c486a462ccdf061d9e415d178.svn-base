package weixin.shop.controller;
import weixin.guanjia.core.util.MessageUtil;
import weixin.member.entity.WeixinMemberEntity;
import weixin.payment.entity.WeixinPaymentLogEntity;
import weixin.shop.entity.WeixinOrderEntity;
import weixin.shop.service.impl.PayReceiveService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.web.system.service.SystemService;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

/**   
 * @Title: Controller
 * @Description: 微信支付
 * @author onlineGenerator
 * @date 2015-04-23 20:16:50
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/wxpay")
public class PayReceiveController {
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	PayReceiveService payReceiveService;
	
	@RequestMapping(params="api", method = RequestMethod.GET)
	public void wechatGet(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "signature") String signature,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "nonce") String nonce,
			@RequestParam(value = "echostr") String echostr) throws IOException {
	
		systemService.addLog("付款通知get", Globals.Log_Type_OTHER, Globals.Log_Leavel_ERROR);
		
		String respMessage = payReceiveService.coreService(request);
		if(respMessage!=null){
			PrintWriter out = response.getWriter();
			out.print(respMessage);
			out.close();
		}
	}
	
	
	@RequestMapping(params = "api", method = RequestMethod.POST)
	public void wechatPost(HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		
		systemService.addLog("付款通知1", Globals.Log_Type_OTHER, Globals.Log_Leavel_ERROR);
		
		String respMessage = payReceiveService.coreService(request);
		if(respMessage!=null){
			PrintWriter out = response.getWriter();
			out.print(respMessage);
			out.close();
		}
	}
}

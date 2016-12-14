package weixin.idea.huodong.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.RoletoJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.wxaccount.JwAccountAPI;
import org.jeewx.api.wxaccount.model.WxQrcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.goods.entity.WeixinShopCategoryEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.idea.huodong.entity.HdRecordEntity;
import weixin.idea.huodong.entity.HuodongEntity;
import weixin.idea.huodong.entity.PrizeRecordEntity;
import weixin.idea.huodong.service.HuodongServiceI;
import weixin.idea.huodong.utils.HdUtils;


/**
 * 微信二维码
* 
 */
@Controller
@RequestMapping("/qrcodeController")
public class QrcodeController {
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	
	@RequestMapping(params = "qrcode")
	public ModelAndView qrcode(HttpServletRequest request) {
		
		WxQrcode wxQrcode = null;
		try {
			
			wxQrcode = JwAccountAPI.createQrcode(weixinAccountService.getAccessToken(), "123", "QR_LIMIT_SCENE", "1800");
		} catch (WexinReqException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("wxQrcode", wxQrcode);
		return new ModelAndView("weixin/idea/huodong/huodong/qrcode");
	}
}
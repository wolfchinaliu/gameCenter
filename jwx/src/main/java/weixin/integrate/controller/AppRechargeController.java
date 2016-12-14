package weixin.integrate.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;
import sdk.jfinal.JFinalUtils;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.integrate.entity.UserFlowAccountEntity;
import weixin.integrate.entity.WxIntegrateBusinessEntity;
import weixin.integrate.service.IntegrateService;
import weixin.integrate.util.WxIntegrateConstant;
import weixin.liuliangbao.jsonbean.MerchantInfoBean;
import weixin.liuliangbao.util.DESUtil;
import weixin.safetyRules.entity.SafetyRulesEntity;
import weixin.safetyRules.service.SafetyRulesServiceI;
import weixin.tenant.entity.WeixinAcctEntity;


@Controller
@RequestMapping("appRechargeController")
public class AppRechargeController {
	private static final Logger logger = Logger.getLogger(AppRechargeController.class);
    @Autowired
    private CommonService commonService;
    @Autowired
    private WeixinAccountServiceI accountService;
    @Autowired
    private IntegrateService integrateService;
    @Autowired
    private SafetyRulesServiceI ruleService;
    
    @RequestMapping(params = "test")
    public void test(){
    	JSONObject json = new JSONObject();
    	json.put("data", "13520289150");
    	json.put("acctId", "ff80808154bdc82e0154c20db50b0042");
    	goAppUserCharge(json);
    }
	@RequestMapping(params = "goAppUserCharge", method = RequestMethod.POST)
    public ModelAndView goAppUserCharge(@RequestBody JSONObject json) {
		StringBuilder builder = new StringBuilder();
        String userData = MapUtils.getString(json, "data");
        String acctId = MapUtils.getString(json, "acctId");
        ModelAndView view = new ModelAndView();
        WeixinAcctEntity acctEntity = this.integrateService.getWeixinAcct(acctId);
        if (acctEntity == null) {
//            builder.append("-return:acctId");
//            logger.info(builder);
        	view = new ModelAndView("integrate/error");
            return view;
            
        }
        String secret = this.integrateService.getCustomerSecret(acctId);
        if (StringUtils.isEmpty(secret)) {
        	view = new ModelAndView("integrate/error");
            return view;
        }
        JSONObject integrate = null;
        String decData = null;
        try {
            decData = java.net.URLDecoder.decode(DESUtil.decrypt(userData, secret), "utf-8");
            integrate = JSONObject.fromObject(decData);
        } catch (Exception e) {
        	view = new ModelAndView("integrate/error");
            return view;
        }

        if (!StringUtils.equals(acctId, MapUtils.getString(integrate, "acctId"))) {
        	view = new ModelAndView("integrate/error");
            return view;
        }
        String phonenumber = MapUtils.getString(integrate, "phonenumber");
		if(StringUtils.isEmpty(phonenumber)){
			view = new ModelAndView("integrate/error");
            return view;
		}
		
		MerchantInfoBean userFlowAccount = JFinalUtils.getUserFlowAccount(null, "13520289150");
		if(userFlowAccount == null){
			view = new ModelAndView("integrate/error");
            return view;
		}
		
		
		String sql="select * from userflowaccount where phoneNumber=?";
		List<UserFlowAccountEntity> userFlowAccountEntity = this.commonService.findByProperty(UserFlowAccountEntity.class, "phoneNumber", "13520289150");
		String paymentpwd = userFlowAccountEntity.get(0).getPaymentpwd();
		
		if(StringUtils.isEmpty(paymentpwd)){
			view.setViewName("integrate/setpaypwd");
			return view;
		}
		view.setViewName("appusercharge");
		return view;
	}
	
    
}

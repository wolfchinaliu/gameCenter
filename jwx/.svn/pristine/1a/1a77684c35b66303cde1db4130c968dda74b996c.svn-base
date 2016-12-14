package weixin.integrate.service.impl;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONObject;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.integrate.entity.UserFlowAccountEntity;
import weixin.integrate.entity.WxIntegrateBusinessEntity;
import weixin.integrate.entity.WxIntegrateSecretEntity;
import weixin.integrate.service.IntegrateService;
import weixin.integrate.util.IntegrateUtils;
import weixin.integrate.util.WxIntegrateConstant;
import weixin.iplimit.entity.IPLimitEntity;
import weixin.liuliangbao.jsonbean.Success;
import weixin.liuliangbao.jsonbean.Update;
import weixin.liuliangbao.util.HttpUtil;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.safetyRules.entity.SafetyRulesEntity;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;

@Service
@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class IntegrateServiceImpl implements IntegrateService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private WeixinAccountServiceI accountService;
    @Autowired
    private WeixinMemberServiceI weixinMemberService;

    private String path = ResourceUtil.getConfigByName("jfinalUrl-config");

    @Override
    public boolean IsAllowAccess(String acctId, String ip) {
        if (StringUtils.trimToNull(ip) == null) {
            return false;
        }
        List<IPLimitEntity> ipLimits = this.commonService.findByProperty(IPLimitEntity.class, "acctid", acctId);

        if (ipLimits != null && ipLimits.size() != 0) {
            for (IPLimitEntity e : ipLimits) {
                if (e.getIp().equals(ip)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getCustomerSecret(String acctId) {
        WxIntegrateSecretEntity wxSecret = commonService.findUniqueByProperty(WxIntegrateSecretEntity.class, "acctId", acctId);
        if (wxSecret == null) {
            return null;
        } else {
            return wxSecret.getSecret();
        }
    }

    @Override
    public boolean isComplyRules(String acctId, String activityId, double flowValue, Date date) {
        return true;
    }

    @Override
    public boolean isAcctFlowSufficient(String acctId, String flowType, double flowValue) {
        WeixinAccountEntity entity = this.commonService.findUniqueByProperty(WeixinAccountEntity.class, "acctId", acctId);
        weixinAcctFlowAccountEntity flowAccount = commonService.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "accountId",
                entity.getId());
        if (flowAccount == null) {
            return false;
        }
        if (WxIntegrateConstant.flowtype_national.equals(flowType)) {
            if (flowValue > flowAccount.getCountryFlowValue()) {
                return false;
            }
        } else if (WxIntegrateConstant.flowtype_provincial.equals(flowType)) {
            if (flowValue > flowAccount.getProvinceFlowValue()) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean isMemberFlowSufficient(String openId, String accountId, String flowType, double flowValue) {
        WeixinMemberEntity member = this.weixinMemberService.getWeixinMember(openId, accountId);
        if (member == null) {
            return false;
        }
        UserFlowAccountEntity userFlow = this.findByPhone(member.getPhoneNumber());
        if (WxIntegrateConstant.flowtype_national.equals(flowType)) {
            if (flowValue > userFlow.getCountryFlowValue()) {
                return false;
            }
        } else if (WxIntegrateConstant.flowtype_provincial.equals(flowType)) {
            if (flowValue > userFlow.getProvinceFlowValue()) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean isMemberFlowSufficient(String phoneNumber,  String flowType, double flowValue) {
        UserFlowAccountEntity userFlow = this.findByPhone(phoneNumber);
        if (WxIntegrateConstant.flowtype_national.equals(flowType)) {
            if (flowValue > userFlow.getCountryFlowValue()) {
                return false;
            }
        } else if (WxIntegrateConstant.flowtype_provincial.equals(flowType)) {
            if (flowValue > userFlow.getProvinceFlowValue()) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    private UserFlowAccountEntity findByPhone(String phoneNumber) {
        UserFlowAccountEntity userFlow = this.commonService.findUniqueByProperty(UserFlowAccountEntity.class, "phoneNumber", phoneNumber);
        return userFlow;
    }

    @Override
    public WxIntegrateBusinessEntity saveIntegrateBusiness(String acctId, String businessType, Map integrate) {
        WxIntegrateBusinessEntity entity = new WxIntegrateBusinessEntity();
        entity.setAcctId(acctId);
        if (StringUtils.equals(businessType, WxIntegrateConstant.businesstype_wxuserpay)
                || StringUtils.equals(businessType, WxIntegrateConstant.businesstype_wxuserreceive)) {
            entity.setOpenId(MapUtils.getString(integrate, "openId"));
        } else if (StringUtils.equals(businessType, WxIntegrateConstant.businesstype_appuserpay)
                || StringUtils.equals(businessType, WxIntegrateConstant.businesstype_appuserreceive)) {
            entity.setPhoneNumber(MapUtils.getString(integrate, "phoneNumber"));
        }
        entity.setActivityId(MapUtils.getString(integrate, "activityId"));
        entity.setFlowType(IntegrateUtils.toFlowTypeName(MapUtils.getString(integrate, "flowType")));
        entity.setFlowValue(MapUtils.getString(integrate, "flowValue"));
        entity.setStatus(WxIntegrateConstant.status_getbusinesskey);
        entity.setCreateDate(new Date());
        entity.setBusinessType(businessType);
        this.commonService.save(entity);
        return entity;
    }

    @Override
    public WxIntegrateBusinessEntity getIntegrateBusiness(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        return this.commonService.get(WxIntegrateBusinessEntity.class, id);
    }

    @Override
    public String getBindingPhone(String openId, String accountId) {
        WeixinMemberEntity entity = this.weixinMemberService.getWeixinMember(openId, accountId);
        return entity == null ? null : entity.getPhoneNumber();
    }

    
    @Override
    public Map bindingPhone(String openId, String accountId, String phoneNumber) {
        WeixinMemberEntity member = this.weixinMemberService.getWeixinMember(openId, accountId);
        String url = path + "userGetFlow/Banding";
        JSONObject myJsonObject = new JSONObject();
        myJsonObject.put("openId", openId);
        myJsonObject.put("operateType", "关注");
        myJsonObject.put("phoneNumber", phoneNumber);
        myJsonObject.put("userName", member.getNickName());
        myJsonObject.put("merchantID", accountId);
        JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
        Gson gson = new Gson();
        String reStr = gson.toJson(content);
        Type type = new TypeToken<Update>() {
        }.getType();
        Update update = gson.fromJson(reStr, type);
        Map map = new HashMap();
        if (update.getCode().equals("200")) {
            map.put("flag", true);
            map.put("msg", update.getMessage());
        } else {
            map.put("flag", false);
            map.put("msg", "手机验证失败，请稍后再试！");
        }
        return map;
    }

    @Override
    public void receiveSuccess(String businessKey,SafetyRulesEntity rule) {
        WxIntegrateBusinessEntity integrateEntity = this.getIntegrateBusiness(businessKey);
        String openId = integrateEntity.getOpenId();
        String acctId = integrateEntity.getAcctId();
        WeixinAccountEntity account = this.accountService.findUniqueByProperty(WeixinAccountEntity.class, "acctId", acctId);
        String phoneNumber = this.getBindingPhone(openId, account.getId());
        String url = path + "userGetFlow/UpdateFlowAndAddFlowRecord";
        Gson gson = new Gson();
        JSONObject myJson = new JSONObject();
        myJson.put("phoneNumber", phoneNumber);
        myJson.put("flowValue", integrateEntity.getFlowValue());
        myJson.put("id", account.getId());
        myJson.accumulate("opreateType", rule.getRuleName());
        myJson.put("flowType", IntegrateUtils.toFlowTypeName(integrateEntity.getFlowType())); // 省内：省内流量；全国：全国流量
        JSONObject contentFlow = HttpUtil.httpPost(url, myJson, false);
        String strFlow = gson.toJson(contentFlow);
        Type type = new TypeToken<Update>() {
        }.getType();
        Update update = gson.fromJson(strFlow, type);
        if (update.getCode().equals("200")) {
            integrateEntity.setStatus(WxIntegrateConstant.status_success);
            integrateEntity.setOperateDate(new Date());
            this.commonService.saveOrUpdate(integrateEntity);
        } else {
            integrateEntity.setStatus(WxIntegrateConstant.status_fail);
            integrateEntity.setOperateDate(new Date());
            this.commonService.saveOrUpdate(integrateEntity);
        }
    }

    @Override
    public WeixinAcctEntity getWeixinAcct(String acctId) {
        return this.commonService.get(WeixinAcctEntity.class, acctId);
    }

    @Override
    public WeixinAccountEntity getWeixinAccount(String acctId) {
        if (StringUtils.isBlank(acctId)) {
            return null;
        }
        WeixinAccountEntity entity = this.commonService.findUniqueByProperty(WeixinAccountEntity.class, "acctId", acctId);
        return entity;
    }

    @Override
    public void setBusinessFailByTimeout(int seconds) {
        this.commonService.executeSql(
                "UPDATE weixin_integrate_business b SET b.status='4' AND b.operateDate = now() WHERE b.createDate < DATE_SUB(NOW(),INTERVAL ? SECOND) AND b.status=?",
                seconds, WxIntegrateConstant.status_getbusinesskey);
    }

    @Override
    public void pay(String businessKey) {
        WxIntegrateBusinessEntity business = this.getIntegrateBusiness(businessKey);
        WeixinAccountEntity entity = this.commonService.findUniqueByProperty(WeixinAccountEntity.class, "acctId", business.getAcctId());
        weixinAcctFlowAccountEntity acctFlow = commonService.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "accountId",
                entity.getId());
        WeixinMemberEntity member = this.weixinMemberService.getWeixinMember(business.getOpenId(), entity.getId());
        UserFlowAccountEntity userFlow = this.findByPhone(member.getPhoneNumber());
        double flowValue = Double.parseDouble(business.getFlowValue());
        String flowType = business.getFlowType();
        if (WxIntegrateConstant.flowtype_national.equals(flowType)) {
            acctFlow.setCountryFlowValue(acctFlow.getCountryFlowValue() + flowValue);
            userFlow.setCountryFlowValue(userFlow.getCountryFlowValue() - flowValue);
        } else if (WxIntegrateConstant.flowtype_provincial.equals(flowType)) {
            acctFlow.setProvinceFlowValue(acctFlow.getProvinceFlowValue() + flowValue);
            userFlow.setProvinceFlowValue(userFlow.getProvinceFlowValue() - flowValue);
        }
        this.commonService.saveOrUpdate(acctFlow);
        this.commonService.saveOrUpdate(userFlow);
        business.setStatus(WxIntegrateConstant.status_success);
        this.commonService.saveOrUpdate(business);
    }

    @Override
    public UserFlowAccountEntity getUser(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber)) {
            return null;
        }
        UserFlowAccountEntity user = this.commonService.findUniqueByProperty(UserFlowAccountEntity.class, "phoneNumber", phoneNumber);
        return user;
    }

    @Override
    public void setBusinessFailByKey(String businessKey) {
        this.commonService.executeSql(
                "UPDATE weixin_integrate_business b SET b.status='3' AND b.operateDate = now() WHERE b.id = ? AND b.status='1'",
                businessKey);
    }

    @Override
    public boolean isUserSetTradePwd(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber)) {
            return false;
        }
        UserFlowAccountEntity user = this.getUser(phoneNumber);
        if (user == null) {
            return false;
        }
        if (StringUtils.isBlank(user.getPaymentpwd())) {
            return false;
        }
        return true;
    }

    @Override
    public void setpaypwd(String phoneNumber, String pwd) {
        UserFlowAccountEntity entity = this.findByPhone(phoneNumber);
        String paypwd = PasswordUtil.encrypt(phoneNumber, pwd, PasswordUtil.getStaticSalt());
        entity.setPaymentpwd(paypwd);
        this.commonService.save(entity);
    }

    @Override
    public boolean isPaypwdRight(String phoneNumber, String pwd) {
        UserFlowAccountEntity entity = this.findByPhone(phoneNumber);
        if (entity == null) {
            return false;
        }
        String paypwd = PasswordUtil.encrypt(phoneNumber, pwd, PasswordUtil.getStaticSalt());
        if (!StringUtils.equals(paypwd, entity.getPaymentpwd())) {
            return false;
        }
        return true;
    }

    @Override
    public void saveIntegrateBusiness(WxIntegrateBusinessEntity entity) {
        this.commonService.saveOrUpdate(entity);
    }

    @Override
    public boolean isUserInMachantCoverArea(String phoneNumber, String merchantId) {
        String url = path + "UserGetFlow/isUserInMachantCoverArea";
        JSONObject myJsonObject = new JSONObject();
        myJsonObject.put("phoneNumber", phoneNumber);
        myJsonObject.put("id", merchantId);
        JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
        Gson gson = new Gson();
        String reStr = gson.toJson(content);
        Type type = new TypeToken<Success>() {
        }.getType();
        Success success = gson.fromJson(reStr, type);
        if(success.getSuccess().equals("true")){
            return true;
        } else {
            return false;
        }
    }

}

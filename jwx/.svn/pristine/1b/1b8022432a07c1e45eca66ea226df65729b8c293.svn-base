package weixin.integrate.service;

import java.util.Date;
import java.util.Map;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.integrate.entity.UserFlowAccountEntity;
import weixin.integrate.entity.WxIntegrateBusinessEntity;
import weixin.member.entity.WeixinMemberEntity;
import weixin.safetyRules.entity.SafetyRulesEntity;
import weixin.tenant.entity.WeixinAcctEntity;

@SuppressWarnings("rawtypes")
public interface IntegrateService {

    /**
     * ip白名单匹配
     */
    public boolean IsAllowAccess(String acctId, String ip);

    public WeixinAcctEntity getWeixinAcct(String acctId);

    /**
     * 获取客户密钥
     */
    public String getCustomerSecret(String acctId);

    /**
     * 是否符合安全规则
     */
    public boolean isComplyRules(String acctId, String activityId, double flowValue, Date date);

    /**
     * 用户流量余额是否充足
     */
    public boolean isAcctFlowSufficient(String acctId, String flowType, double flowValue);

    /**
     * 保存商户给接口的传值
     */
    public WxIntegrateBusinessEntity saveIntegrateBusiness(String acctId, String businessType, Map integrate);

    public WxIntegrateBusinessEntity getIntegrateBusiness(String id);

    public String getBindingPhone(String openId, String accountId);

    public void receiveSuccess(String businessKey,SafetyRulesEntity rule);

    public Map bindingPhone(String openId, String accountId, String phoneNumber);

    public WeixinAccountEntity getWeixinAccount(String acctId);

    public void setBusinessFailByTimeout(int seconds);

    public void setBusinessFailByKey(String businessKey);

    public boolean isMemberFlowSufficient(String openId, String accountId, String flowType, double flowValue);

    public void pay(String businessKey);

    public boolean isUserSetTradePwd(String bindingPhone);

    public UserFlowAccountEntity getUser(String phoneNumber);

    public void setpaypwd(String phoneNumber, String pwd);

    public boolean isPaypwdRight(String phoneNumber, String payPwd);

    public void saveIntegrateBusiness(WxIntegrateBusinessEntity entity);

    public boolean isUserInMachantCoverArea(String phoneNumber,String merchantId);

    public boolean isMemberFlowSufficient(String phoneNumber, String flowType, double flowValue);
}

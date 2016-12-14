package weixin.manual.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.integrate.entity.UserFlowAccountEntity;
import weixin.integrate.service.UserFlowAccountService;
import weixin.manual.entity.ManualGiven;
import weixin.manual.entity.ManualGivenRecords;
import weixin.manual.service.ManualGivenRecordsService;
import weixin.manual.service.ManualGivenService;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.report.model.UserGiveFlowEntity;
import weixin.report.service.UserGiveFlowServiceI;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.util.DataDictionaryUtil.FlowType;

/**
 * @author Sukin
 * 2016年9月5日
 */
@Service("manualGivenService")
@Transactional
public class ManualGivenServiceImpl  extends CommonServiceImpl implements ManualGivenService{
	
    @Autowired
    private SystemService systemService;
    
    @Autowired
    private WeixinAcctFlowAccoutServiceI accountService;
    
    @Autowired
    private UserFlowAccountService userFlowService;
    
    @Autowired
    private UserGiveFlowServiceI ugfService;
    
    @Autowired
    private  ManualGivenRecordsService manualGivenRecordsService;
    
    @Autowired
    private WeixinMemberServiceI weixinMember;

	
	public <T> void saveOrUpdate(T entity) {
		super.saveOrUpdate(entity);
		// 执行更新操作配置的sql增强
		this.doUpdateSql((ManualGiven) entity);
	}
	
	@Override
    public boolean doAddSql(ManualGiven t) {
		return false;
	}

	@Override
	public boolean doUpdateSql(ManualGiven t) {
		return true;
	}

	@Override
	public boolean doDelSql(ManualGiven t) {
		return false;
	}
    /**
     * 根据赠送时间,赠送结果,以及状态查询结果
     */
	@Override
	public List<ManualGiven> queryGiveTime() {
		String hql ="from ManualGiven where givenTime < NOW() and result='3' and state='2'";       
		return this.findHql(hql);
	}
	
	@Override
	public String giveFlowToUser(ManualGiven mg){
		try{
		List<WeixinAccountEntity> weixinAccount = this.systemService.findHql("from WeixinAccountEntity t where t.acctId=?",mg.getAccountid());
		if(null==weixinAccount || weixinAccount.isEmpty()){ //微信公账号为空
			return "0";
		}
		//需要赠送的记录,如果没有记录,则将批次记录设置为失败
		 List<ManualGivenRecords> mgrlist = this.manualGivenRecordsService.findByQueryString("from ManualGivenRecords where reason ='4' and patchNo ='"+mg.getPatchNo()+"'");
		 if(0>=mgrlist.size()){ //没有赠送成功的手机号
			 return "6";
		 }
		 //赠送流量
		 for(ManualGivenRecords mgr:mgrlist){ //合格手机号
 			 List<UserFlowAccountEntity> userAccount = this.systemService.findHql("from UserFlowAccountEntity t where t.phoneNumber=?",mgr.getPhoneNum()); //个人流量账户		
			 UserGiveFlowEntity ugf = new UserGiveFlowEntity();
			 if(userAccount.size() == 0 || null == userAccount){ //没有对应的流量账户
				 // 创建流量账户
				 UserFlowAccountEntity ufAccount = new  UserFlowAccountEntity();
				 ufAccount.setId(UUID.randomUUID().toString()); //生成id
				 ufAccount.setPhoneNumber(mgr.getPhoneNum());//手机号
				 if(mg.getFlowType().equals(FlowType.country.getCode())){ //全国流量
					 ufAccount.setCountryFlowValue(mg.getFlowValue()); 
					 ufAccount.setProvinceFlowValue(0);
				 }else{ //省内流量
					 ufAccount.setProvinceFlowValue(mg.getFlowValue());
					 ufAccount.setCountryFlowValue(0); 
				 }
				 userFlowService.save(ufAccount); //个人流量账户				
			 }else{
				 /**个人流量*/
				 if(mg.getFlowType().equals(FlowType.country.getCode())){ //全国流量
					 userAccount.get(0).setCountryFlowValue(mg.getFlowValue()+userAccount.get(0).getCountryFlowValue());
				 }else{
					 userAccount.get(0).setProvinceFlowValue(mg.getFlowValue()+userAccount.get(0).getProvinceFlowValue());
				 }
				 userFlowService.saveOrUpdate(userAccount.get(0)); //个人流量账户
				 
				 //判断该用户是不是当前商户的粉丝
				  List<WeixinMemberEntity> member = this.weixinMember.findByQueryString(" from WeixinMemberEntity where accountId ='"+mg.getAccountid()+"' and phoneNumber ='"+mgr.getPhoneNum()+"'");
				  if(member.size() != 0  &&  null != member){ //是当前商户的粉丝
					  ugf.setOpenId(member.get(0).getOpenId()); //openid
				  }		
				  ugf.setOperator(userAccount.get(0).getUserName()); //昵称
			 }
			//用户的获奖记录
			 ugf.setId(UUID.randomUUID().toString()); //id
			 ugf.setAccountid(weixinAccount.get(0).getId()); //商户id
			 if(mg.getFlowType().equals(FlowType.country.getCode())){ //全国
				 ugf.setFlowType("1");
			 }else{ //省内
				 ugf.setFlowType("2");
			 }
			 ugf.setFlowValue(mg.getFlowValue()); //流量值
			 ugf.setOperateDate(new Date()); 
			 ugf.setPhoneNumber(mgr.getPhoneNum()); //手机号
			 ugf.setReason("赠送");
			 ugf.setStatus(1); //已经领取
			 ugf.setDescription(mg.getDes()); //活动描述
			 ugfService.save(ugf); //用户领取记录			 
			 mgr.setResult("1");
			 mgr.setReason("1");
			 manualGivenRecordsService.saveOrUpdate(mgr); //手工赠送记录
			 mg.setSucnum(mgrlist.size());
			 this.saveOrUpdate(mg);
		 }
		 return "1";
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	public List<ManualGiven> queryState() {
		String hql ="from ManualGiven where givenTime < NOW() and result='3' and state='0'";       
		return  this.findHql(hql);
	}




	
     


}

package weixin.memberStructure.job;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.memberStructure.entity.MemberStatEntity;
import weixin.memberStructure.service.MemberStatService;

@Service("memberStrustureJob") 
public class memberStrustureJob {
	@Autowired
    private SystemService systemService;
	@Autowired
    private MemberStatService memberStatService;
	
	public void memberStrusture()  {
		
		long yesterday = System.currentTimeMillis() - 24 * 60 * 60 *1000;
		Date date = new Date(yesterday);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ss = sdf.format(date);
		/**
		 * 定时查询每天新增粉丝数量
		 * 定时查询每天取关粉丝数量
		 * 定时查询每天绑定手机号数量
		 * 定时查询每个商户每天粉丝
		 */
				
		String sql =  "select w.account_id,"
				+ "(select COUNT(*) from log_member_datailed l where subscribe = '1' and DATE_FORMAT(l.time,'%Y%m%d') = "+ss+" and l.account_id = w.account_id),"
				+ "(select count(*) from log_member_datailed d where subscribe = '0' and DATE_FORMAT(d.time,'%Y%m%d') = "+ss+" and d.account_id = w.account_id),"
				+ "count(CASE when w.phoneNumber is not null and DATE_FORMAT(w.bingding_time,'%Y%m%d') = "+ss+" then 1 end),"
				+ "count(case when w.subscribe = '1' then 2 end) "
				+ "from weixin_member w GROUP BY w.account_id";

	   List<Object[]> list = systemService.findListbySql(sql);
	   for(Object[] objects : list){
		   MemberStatEntity wd = new MemberStatEntity();
//		   wd.setId(UUIDGenerator.generate());
		   Object accountid = objects[0];
           if (accountid != null) {
               wd.setAccountId(accountid.toString());
           }
           Object addmember = objects[1];
           if (addmember != null) {
        	   wd.setAddCount(new BigInteger(ObjectUtils.toString(addmember, "0")));
           }
           Object cancelCount = objects[2];
           if (cancelCount != null) {
        	   wd.setCancelCount(new BigInteger(ObjectUtils.toString(cancelCount, "0")));
           }
           Object bindedCount = objects[3];
           if (bindedCount != null) {
               wd.setBindedCount(new BigInteger(ObjectUtils.toString(bindedCount, "0")));
           }
           Object subscribeCount = objects[4];
           if (subscribeCount != null) {
               wd.setSubscribeCount(new BigInteger(ObjectUtils.toString(subscribeCount,"0")));
           }
           if(accountid != null){
           WeixinAccountEntity accountEntity = this.systemService.findUniqueByProperty(WeixinAccountEntity.class, "id",accountid.toString());
           if(accountEntity != null){
        	   String acctId = accountEntity.getAcctId();
        	   wd.setAcctId(acctId);
           		}
           }else {
        	   wd.setAcctId(null);
           }
           wd.setCreated(date);
           wd.setNetCount(wd.getAddCount().subtract(wd.getCancelCount()));
           this.memberStatService.saveOrUpdate(wd);
	   }
	   
	}
	 
}

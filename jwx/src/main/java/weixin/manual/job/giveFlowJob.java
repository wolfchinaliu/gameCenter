package weixin.manual.job;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sdk.jfinal.JFinalUtils;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.integrate.entity.UserFlowAccountEntity;
import weixin.integrate.service.UserFlowAccountService;
import weixin.manual.controller.ManualGivenController;
import weixin.manual.entity.ManualGiven;
import weixin.manual.entity.ManualGivenRecords;
import weixin.manual.service.ManualGivenRecordsService;
import weixin.manual.service.ManualGivenService;
import weixin.manual.util.ManualGivenUtil;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.report.model.UserGiveFlowEntity;
import weixin.report.service.UserGiveFlowServiceI;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;

@Service("giveFlowJob")
public class giveFlowJob {
    public static final transient Logger LOGGER = Logger.getLogger(giveFlowJob.class);
    @Autowired
    private ManualGivenService manualService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private ManualGivenService manualGivenService;
    @Autowired
    private WeixinAcctFlowAccoutServiceI accountService;

    public void giveFlowToUser() throws FileNotFoundException, IOException {
        StringBuilder ss = new StringBuilder();
        ss.append("定时赠送流量给用户giveFlowToUser:");
            List<ManualGiven> manlist = manualService.queryGiveTime();
            if (null == manlist || manlist.isEmpty()) {
                return;
            }
            for (ManualGiven mg : manlist) { // 将检索出来的实体状态做修改,避免重复赠送
                mg.setState("0");
                manualGivenService.saveOrUpdate(mg);
            }
            for (ManualGiven mg : manlist) {
            	try {
	                String flag = manualGivenService.giveFlowToUser(mg);
	                if ("1".equals(flag)) {
	                    mg.setReason("1");
	                    mg.setResult("1");
	                    mg.setState("1");
	                    manualGivenService.saveOrUpdate(mg);
	                    ss.append("赠送成功!");
	                } else if ("6".equals(flag)) {
	                	mg.setTotalFlow(0.0);
	                    mg.setReason("6");
	                    mg.setResult("2");
	                    mg.setState("1");
	                    manualGivenService.saveOrUpdate(mg);
	                    ss.append("赠送失败!");
	                } else {
	                	mg.setTotalFlow(0.0);
	    				mg.setResult("2");
	    				mg.setReason("5");
	    				mg.setState("1");
	    				manualGivenService.saveOrUpdate(mg);
	    				ss.append("赠送失败!");
	                }
            	 } catch (Exception e) {
                 	List<weixinAcctFlowAccountEntity> account = this.systemService.findHql("from weixinAcctFlowAccountEntity t where t.tenantId=?", mg.getAccountid());
         			if("1".equals(mg.getFlowType())){
         				account.get(0).setCountryFlowValue(account.get(0).getCountryFlowValue()+mg.getTotalFlow());
         			}else{
         				account.get(0).setProvinceFlowValue(account.get(0).getProvinceFlowValue()+mg.getTotalFlow());
         			}			
         			accountService.saveOrUpdate(account.get(0));
         			mg.setTotalFlow(0.0);
         			mg.setResult("2");
         			mg.setReason("5");
         			mg.setState("1");
         			manualGivenService.saveOrUpdate(mg);
         			systemService.updateBySqlString("update ManualGivenRecords set result = '2',reason = '8' where patchNo='"+mg.getPatchNo()+"'");
                    e.printStackTrace();
                    ss.append("赠送失败" + e);
                 }
                
            }
       
        LOGGER.info(ss.toString());

    }

}

package weixin.acctlist;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.log4j.Logger;

import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weixin.acctlist.entity.AccountListEntity;
import weixin.lottery.entity.WeixinGuessRiddleEntity;
import weixin.lottery.entity.WeixinLotteryEntity;
import weixin.member.util.ConnectionsManager;
import weixin.personalredpacket.entity.PersonalRedpacketSetEntity;
import weixin.util.LogUtil;

@Service("TimeActiveState")
public class TimeActiveState {

	private static final Logger LOGGER = Logger.getLogger(TimeActiveState.class);
	 @Autowired
	    private SystemService systemService;
	
	 //List<AccountListEntity> listAcctList = new ArrayList<AccountListEntity>();
		
		public void schedualJob() {
			/*String sql = "update weixin_lotterylxc set state='3' where state='0'";
			String sql1 = "update weixin_guessRiddlehd set state='3' where state='0'";
			String sql2= "update weixin_personalredpacketset set state='3' where state='0'";
	       try {
	    	   this.systemService.updateBySqlString(sql);
	    	   this.systemService.updateBySqlString(sql1);
	    	   this.systemService.updateBySqlString(sql2);
	           
	       } catch (Exception e) {
	    	   LOGGER.error("定时任务： 把游戏结束的状态更改为3失败", e);
	       }*/
	   
			
	    }
		
		public void queryMerchantList(){
			Long start = System.currentTimeMillis();  //方法开始时间
	        StringBuffer sb = new StringBuffer();
	        sb.append("TimeActiveState.queryMerchantList():");
		LOGGER.info("定时查询任务开始执行.....");
			StringBuffer sql= new StringBuffer();
            sql.append("SELECT a.acct_level,a.business_type,(case when acl.acctlistName is null THEN w.accountname ELSE acl.acctlistName END)as acctlistName, w.id as accountid,accounttype,acct_id,acl.pictureUrl,acl.addressPicUrl FROM weixin_account w LEFT JOIN weixin_acct a ON a.id=w.acct_id ");
            sql.append("LEFT JOIN weixin_acctlist acl ON w.acct_id= acl.acctid WHERE a.status='激活' and a.acct_level !='0' and authorization_type !='3' ORDER BY w.accounttype,w.open_platform_auth_time");
            /*Connection connection =null;
            Statement stmt =null;
            ResultSet es =null;
            connection = ConnectionsManager.getMysqlConn();*/
           List<Map<String, Object>> data = this.systemService.findForJdbc(sql.toString());
            List<AccountListEntity> listAcct = new ArrayList<AccountListEntity>();
            for(int i=0;i<data.size();i++){
            	AccountListEntity accountList = new AccountListEntity();
            	
            	
            	accountList.setAcctLevel((String) data.get(i).get("acct_level"));
            	accountList.setBusinessType((String) data.get(i).get("business_type"));
            	accountList.setAccountid((String) data.get(i).get("accountid"));
            	accountList.setAccounttype((String) data.get(i).get("accounttype"));
            	accountList.setAcctlistName((String) data.get(i).get("acctlistName"));
            	accountList.setAcctId((String) data.get(i).get("acct_id"));
            	accountList.setPictureUrl((String) data.get(i).get("pictureUrl"));
            	accountList.setAddressPicUrl((String) data.get(i).get("addressPicUrl"));
            	listAcct.add(accountList);
            }
           
           /* try {
				stmt = connection.createStatement();
				es = stmt.executeQuery(sql.toString());
				
            while(es.next()){
            	AccountListEntity accountList = new AccountListEntity();
            	accountList.setAcctLevel(es.getString("acct_level"));
            	accountList.setBusinessType(es.getString("business_type"));
            	accountList.setAccountid(es.getString("accountid"));
            	accountList.setAccounttype(es.getString("accounttype"));
            	accountList.setAcctlistName(es.getString("acctlistName"));
            	accountList.setAcctId(es.getString("acct_id"));
            	accountList.setPictureUrl(es.getString("pictureUrl"));
            	accountList.setAddressPicUrl(es.getString("addressPicUrl"));
            	
            	listAcct.add(accountList);
            } 
            } catch (SQLException e) {
            	LOGGER.error(LogUtil.printStackTrace(e));
            }*/
          //获取设置关注/签到/分享的商家及活动流量
            String strSql="SELECT merchantID,sum(case operateType WHEN '分享绑定' THEN flowValue else 0 END) shareflow,sum(case operateType WHEN '关注' THEN flowValue else 0 END) focusflow,sum(case operateType WHEN '签到' THEN flowValue else 0 END) signflow FROM merchantflowgiverules where flowValue != 0.0 or flowValue is not null GROUP BY merchantID order by flowValue desc";
            List<AccountListEntity> listRules=new ArrayList<AccountListEntity>();
            try {
            	/*es = stmt.executeQuery(strSql.toString());
				while(es.next()){
					AccountListEntity accountList = new AccountListEntity();
					accountList.setAccountid(es.getString("merchantID"));
					accountList.setFocusflow(es.getBigDecimal("focusflow").doubleValue()); //关注
					accountList.setShareflow(es.getBigDecimal("shareflow").doubleValue());//分享
					accountList.setSignflow(es.getBigDecimal("signflow").doubleValue());   //签到
					listRules.add(accountList);
				}*/
            	List<Map<String, Object>> findForJdbc = this.systemService.findForJdbc(strSql.toString());
            	 for(int i=0;i<findForJdbc.size();i++){
            		 AccountListEntity accountList = new AccountListEntity();
            		 accountList.setAccountid((String)findForJdbc.get(i).get("merchantID"));
            		 accountList.setFocusflow(((BigDecimal)findForJdbc.get(i).get("focusflow")).doubleValue());
            		 accountList.setShareflow(((BigDecimal)findForJdbc.get(i).get("shareflow")).doubleValue());
            		 accountList.setSignflow(((BigDecimal)findForJdbc.get(i).get("signflow")).doubleValue());
            		 listRules.add(accountList);
            	 }
				List<AccountListEntity> listPlays = queryHuodong();
				//商家覆盖区域
				List<AccountListEntity> queryMerchantcoverarea = queryMerchantcoverarea();
				for(int i=0;i<queryMerchantcoverarea.size();i++){
					for(int n=0;n<listAcct.size();n++){
						if(queryMerchantcoverarea.get(i).getAccountid().equals(listAcct.get(n).getAcctId())){
							listAcct.get(n).setProvincename(queryMerchantcoverarea.get(i).getProvincename());
						}
						
					}
				}
				//判断有关注/签到/分享的是否设置游戏
					
					for(int i=0;i<listRules.size();i++){
						for(int j=0;j< listPlays.size();j++){
	                    if(listRules.get(i).getAccountid().equals(listPlays.get(j).getAccountid())){
	                    	
	                    	listRules.get(i).setIsPlay("1");
	                    }else{
	                    	AccountListEntity acct =new AccountListEntity();
	                        acct.setState(listPlays.get(j).getState());
	                        acct.setAccountid(listRules.get(i).getAccountid());
	                    }
	                }
	            }
				
				
				 List<AccountListEntity> listAcctNew = new ArrayList<AccountListEntity>();
		        
		        for(int j=0;j<listRules.size();j++){
            		for(int i=0;i<listAcct.size();i++){

                    if(Objects.equals(listAcct.get(i).getAccountid(), listRules.get(j).getAccountid())){
                    	AccountListEntity acct = new AccountListEntity();
                    	acct.setAcctLevel(listAcct.get(i).getAcctLevel());
                    	acct.setBusinessType(listAcct.get(i).getBusinessType());
                        acct.setAcctId(listAcct.get(i).getAcctId());
                        acct.setAccountid(listAcct.get(i).getAccountid());
                        acct.setAcctlistName(listAcct.get(i).getAcctlistName());
                        acct.setFocusflow(listRules.get(j).getFocusflow());
                        acct.setSignflow(listRules.get(j).getSignflow());
                        acct.setShareflow(listRules.get(j).getShareflow());
                        acct.setState(listRules.get(j).getState());
                        acct.setIsPlay(listRules.get(j).getIsPlay());
                        acct.setPictureUrl(listAcct.get(i).getPictureUrl());
                        acct.setAddressPicUrl(listAcct.get(i).getAddressPicUrl());
                        acct.setProvincename(listAcct.get(i).getProvincename());
                       listAcctNew.add(acct);
                    }
                }
            }
		        List<AccountListEntity> accountList = this.systemService.findHql("from AccountListEntity", null);
		        if(accountList.size() !=0 ){
		        	for(int i =0;i<accountList.size();i++){
		        		AccountListEntity accountListEntity = accountList.get(i);
		        	this.systemService.delete(accountListEntity);
		        	}
		        	for(int i=0;i<listAcctNew.size();i++){
			        	
			        	AccountListEntity accountListEntity = listAcctNew.get(i);
			        	this.systemService.save(accountListEntity);
		        	}
		        
		        }else{
		        	for(int i=0;i<listAcctNew.size();i++){
			        	
			        	AccountListEntity accountListEntity = listAcctNew.get(i);
			        	this.systemService.save(accountListEntity);
		        	}
		        }
		        
				LOGGER.info("定时查询任务结束.....");
			} catch (SQLException e) {
				 LOGGER.error(LogUtil.printStackTrace(e));
			}finally{
				Long end = System.currentTimeMillis();
	            sb.append(", 方法耗时:" + (end - start) + "ms");
	            LOGGER.info(sb.toString());
			}
            
		}
		/*查询有活动的商家公众号id*/
	    public List<AccountListEntity> queryHuodong ( ){
	        String hql = "from WeixinLotteryEntity where state='1' ORDER BY state desc";
	        //查询参加大转盘/红包/摇一摇/刮刮乐的商家
	        List<WeixinLotteryEntity> weixinLotteryEntities = this.systemService.findHql(hql, null);

	        //查询参加猜灯谜的商家
	        List<WeixinGuessRiddleEntity> guessRiddleEntityList=this.systemService.findHql("from WeixinGuessRiddleEntity where state='1' ORDER BY state desc", null);

	        //查询参加个人红包的商家
	        List<PersonalRedpacketSetEntity> personalRedpacketSetEntityList=this.systemService.findHql("from PersonalRedpacketSetEntity where state='1' ORDER BY state desc", null);

	        List<AccountListEntity> listAcctList = new ArrayList<AccountListEntity>();
	        if(weixinLotteryEntities.size()>0){
	            for(int i=0;i<weixinLotteryEntities.size();i++){
	            	AccountListEntity accountList = new AccountListEntity();
	                WeixinLotteryEntity weixinLotteryEntity = weixinLotteryEntities.get(i);
	                String state = weixinLotteryEntity.getState();
	                String abledotherprize = weixinLotteryEntity.getAbledotherprize();
	                String accountid = weixinLotteryEntity.getAccountid();
	                accountList.setAbledotherprize(abledotherprize);
	                accountList.setState(state);
	                accountList.setAccountid(accountid);
	                listAcctList.add(accountList);
	            }
	        }

	        if(guessRiddleEntityList.size()>0){
	            for(int i=0;i<guessRiddleEntityList.size();i++){
	            	AccountListEntity accountList = new AccountListEntity();
	            	accountList.setState(guessRiddleEntityList.get(i).getState());
	            	accountList.setAccountid(guessRiddleEntityList.get(i).getAccountid());
	            	listAcctList.add(accountList);
	                
	            }
	        }
	        if (personalRedpacketSetEntityList.size()>0){
	            for(int i=0;i<personalRedpacketSetEntityList.size();i++){
	            	AccountListEntity accountList = new AccountListEntity();
	            	accountList.setState(personalRedpacketSetEntityList.get(i).getState());
	            	accountList.setAccountid(personalRedpacketSetEntityList.get(i).getAccountid());
	            	listAcctList.add(accountList);
	            }
	        }
	        List<AccountListEntity> list = new ArrayList<AccountListEntity>();
	      //去除重复
	        for(int i=0; i<listAcctList.size();i++){
	            AccountListEntity accountListEntity = listAcctList.get(i);
	            if(!list.contains(accountListEntity)){
	               list.add(accountListEntity);
	            }
	        }
	        return list;
	        
	    }
	    
	    public List<AccountListEntity> queryMerchantcoverarea ( ) throws SQLException{
	    	
	    	
	    	//商家覆盖区域
	    		String sql ="select * from MerchantCoverArea";
	    	
			/*Connection connection = ConnectionsManager.getMysqlConn();
          Statement createStatement = connection.createStatement();*/
            //ResultSet executeQuery = createStatement.executeQuery(sql.toString());
            List<Map<String, Object>> findForJdbc = this.systemService.findForJdbc(sql.toString());
			List<AccountListEntity>  acctlist = new ArrayList<AccountListEntity>();
	    	/*while(executeQuery.next()){
	    		AccountListEntity ac = new AccountListEntity();
	    		String provincename = executeQuery.getString("provincename");
	    		String accountid = executeQuery.getString("accountid");
	    		ac.setProvincename(provincename);
	    		ac.setAccountid(accountid);
	    	acctlist.add(ac);
	    	}*/
			for(int i=0;i<findForJdbc.size();i++){
				AccountListEntity ac = new AccountListEntity();
				ac.setProvincename((String)findForJdbc.get(i).get("provincename"));
				ac.setAccountid((String)findForJdbc.get(i).get("accountid"));
				acctlist.add(ac);
			}
	    	return acctlist;
	    }
		
}

package weixin.activity.job;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.JsonUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONObject;
import weixin.activity.entity.WeixinActivityEntity;
import weixin.activity.entity.WeixinGameDetailEntity;
import weixin.activity.entity.WeixinPracticalityRecordEntity;
import weixin.activity.service.IWeixinActivityService;
import weixin.liuliangbao.jsonbean.Update;
import weixin.liuliangbao.util.HttpUtil;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.util.DateUtils;

@Service("weixinActivityJob")
public class WeixinActivityJob {
    @Autowired
    private IWeixinActivityService activityService;
    @Autowired
	private WeixinMemberServiceI weixinMemberService;
    
    private static final Logger logger = Logger.getLogger(WeixinActivityJob.class);
    long oneDateM =  86400000;
//    @Scheduled(cron = "0 0 0 * * ?")
    public void beginDayRules() {
    	logger.info("日  游戏排名奖励处理开始----------------------------");
        this.beginRules(1);
        logger.info("日 游戏排名奖励处理结束-----------------------------");
    }

//    @Scheduled(cron = "0 0 0 * * 1")
    public void beginWeekRules() {
    	logger.info("周  游戏排名奖励处理开始-----------------------------");
        this.beginRules(2);
        logger.info("周  游戏排名奖励处理结束-----------------------------");
    }

//    @Scheduled(cron = "0 0 0 1 * ?")
    public void beginMonthRules() {
    	logger.info("月  游戏排名奖励处理开始-----------------------------");
        this.beginRules(3);
        logger.info("月  游戏排名奖励处理结束-----------------------------");
    }


    private void beginRules(int frequencyUnit) {
    	//获取当前时间的0点0分0秒
    	long curren = System.currentTimeMillis()+ TimeZone.getDefault().getRawOffset();
    	logger.info("当前时间--"+curren );
    	long zero = curren/oneDateM*oneDateM - TimeZone.getDefault().getRawOffset();
    	long lastTimes = zero;
    	switch (frequencyUnit) {
		case 1:
			lastTimes = zero - oneDateM;
		break;
		case 2:
			lastTimes = zero - 7*oneDateM;
		break;
		case 3:
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(zero);
			calendar.add(Calendar.MONTH, -1);
			lastTimes = calendar.getTimeInMillis();
		break;
		default:
			break;
		}
    	Date nowDate = new Date(zero);
    	Date lastDate = new Date(lastTimes);
        String hql = "from WeixinActivityEntity a where (a.type > 200 and a.type < 300) and a.frequency = ? and startTime < ? and endTime > ?";
        List<WeixinActivityEntity> activityEntities = activityService.findHql(hql, frequencyUnit,nowDate,lastDate);
        for(WeixinActivityEntity activityEntity : activityEntities){
        	logger.info("开始处理活动---"+activityEntity.getId()+" 名称："+activityEntity.getTitle());
        	String sql = "select opendid , max(score) as score ,addtime from (select * from weixin_game_detail d where d.hdid = '"+activityEntity.getId()+"' and d.addtime between '"+DateUtils.date2Str(lastDate,DateUtils.date_sdf)+"' and '"+DateUtils.date2Str(nowDate,DateUtils.date_sdf)+"' order by score desc ,addtime asc) t group by t.opendid  order by score desc ,addtime asc";
        	List<WeixinGameDetailEntity> detailEntities = activityService.findObjForJdbc( sql, 1 ,10,WeixinGameDetailEntity.class);
        	Map<Object, Object> rule = JsonUtil.parseJSON2Map(activityEntity.getActivityRule());
        	List<Object> rankList = (List<Object>)rule.get("rankRule");
        	int size = rankList.size() > detailEntities.size() ? detailEntities.size():rankList.size();
        	String recordId = null;
        	for(int i = 0 ; i < size ; i++){
        		WeixinGameDetailEntity detailEntity = detailEntities.get(i);
        		try{
        		int flow = Integer.valueOf(rankList.get(i).toString());
        		if(flow <= 0) break;
        		WeixinMemberEntity weixinMember = weixinMemberService.getWeixinMember(detailEntity.getOpendid(),
        				activityEntity.getAccountid());
        		//判断覆盖区域
        		if(StringUtil.isEmpty(weixinMember.getPhoneNumber()))break;
        		String url1 = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/getCoverAndLocation";
				Gson gson1 = new Gson();
				JSONObject myJson1 = new JSONObject();
				myJson1.accumulate("phoneNumber", weixinMember.getPhoneNumber());
				myJson1.accumulate("id", weixinMember.getAccountId());
				JSONObject jsonObject1 = HttpUtil.httpPost(url1, myJson1, false);
				String strFlow1 = gson1.toJson(jsonObject1);
				Type type1 = new TypeToken<Update>() {
				}.getType();
				Update update1 = gson1.fromJson(strFlow1, type1);
				if (update1 == null || update1.getCode().equals("201")) {
					logger.info("      用户：" +detailEntity.getOpendid() + " 昵称 ：" +detailEntity.getNickname()+"  手机号不在覆盖区 不予赠送");
					break;
				}
        		recordId = savePracticalityRecord(flow,i+1,activityEntity,weixinMember);
        		sendFlow(flow, activityEntity, weixinMember, recordId);
        		logger.info("      用户：" +detailEntity.getOpendid() + " 昵称 ：" +detailEntity.getNickname()+"  赠送成功");
        		}catch (Exception e) {
        			logger.info("      用户：" +detailEntity.getOpendid() + " 昵称 ：" +detailEntity.getNickname()+"  赠送失败");
        			if(!StringUtil.isEmpty(recordId))
        				activityService.executeSql("update WEIXIN_PRAC_RECORD set is_send =  1 where id = ?", recordId);
					logger.error("名次送流量任务异常" ,e);
					logger.info("openid= " + detailEntity.getOpendid() +"  aconutId= "+activityEntity.getAccountid() +  " 名次 "+ i+" 赠送流量值失败");
				}
        	}
        }
        
    }
    public void sendFlow(int flow,WeixinActivityEntity activityEntity,WeixinMemberEntity weixinMember ,String recordId){
    	
    	String url = ResourceUtil.getConfigByName("jfinalUrl-config")
				+ "userGetFlow/UpdateFlowAndAddFlowRecord";
		Gson gson = new Gson();
		JSONObject myJson = new JSONObject();
		myJson.accumulate("phoneNumber", weixinMember.getPhoneNumber());
		myJson.accumulate("flowValue", flow);
		myJson.accumulate("id", activityEntity.getAccountid());
		myJson.accumulate("opreateType", activityService.getOpreateType(activityEntity.getType()));
		myJson.accumulate("openid", weixinMember.getOpenId());
		myJson.accumulate("flowType", activityEntity.getFlowType()); // 省内：省内流量；全国：全国流量
		myJson.element("nickName", weixinMember.getNickName());
		JSONObject contentFlow = HttpUtil.httpPost(url, myJson, false);
		String strFlow = gson.toJson(contentFlow);
		Type type = new TypeToken<Update>() {
		}.getType();
		// 是否中奖参数
		Update update = gson.fromJson(strFlow, type);
		if (StringUtils.isNotBlank(weixinMember.getPhoneNumber())) {
			if (!"200".equals(update.getCode()) && !"10026".equals(update.getCode())) {
				logger.info("openid= " + weixinMember.getOpenId() +"  aconutId= "+activityEntity.getAccountid() +  " 更新新流量值失败");
				activityService.executeSql("update WEIXIN_PRAC_RECORD set is_send =  1 where id = ?", recordId);
			}
		}
    }
    private String savePracticalityRecord(int flow,  int rank, WeixinActivityEntity activityEntity,
			WeixinMemberEntity memberEntity) {
		WeixinPracticalityRecordEntity entity = new WeixinPracticalityRecordEntity();
		entity.setAccountid(activityEntity.getAccountid());
		entity.setAddtime(new Date());
		entity.setHdid(activityEntity.getId());
		entity.setMobile(memberEntity.getPhoneNumber());
		entity.setNickname(memberEntity.getNickName());
		entity.setPrizevalue("流量 " + flow + "M");
		entity.setPrizelevel("第 " + rank + "名");
		entity.setLevel(activityService.getLevel(activityEntity.getFrequency(),activityEntity.getStartTime()) - 1);
		entity.setType(1);
		entity.setIsSend(2);
		entity.setOpenid(memberEntity.getOpenId());
		entity.setSendTime(new Date());
		return (String) activityService.save(entity);
	}
    
}

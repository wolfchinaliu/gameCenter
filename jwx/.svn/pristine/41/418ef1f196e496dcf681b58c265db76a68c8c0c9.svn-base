package weixin.activity.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.mapping.Map;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.JsonUtil;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import weixin.activity.entity.WeixinActivityEntity;
import weixin.activity.entity.WeixinActivityQuestionEntity;
import weixin.activity.job.WeixinActivityJob;
import weixin.activity.service.IWeixinActivityService;
import weixin.lottery.service.WeixinDrawDetailServiceI;

/**
 * @Auth popl
 * @Date 2016年7月28日 下午2:03:34
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.lottery.service.impl.WeixinActivityServiceImpl
 * @dec 
 */
@Service("weixinActivityService")
@Transactional
public class WeixinActivityServiceImpl extends CommonServiceImpl implements IWeixinActivityService {
	  private static final Logger logger = Logger.getLogger(WeixinActivityServiceImpl.class);
	@Autowired
	private WeixinDrawDetailServiceI drawDetailService;
	@Override
	public void deleteActivity(WeixinActivityEntity entity) {
		drawDetailService.deteWeixinDrawDetailByHdid(entity.getId());
		deletePracticalityRecordByHdid(entity.getId());
		this.delete(entity);
	}

	public void deletePracticalityRecordByHdid(String hdid){
		String hql = " delete from  weixin_prac_record where hdid='" + hdid + "' ";
		this.updateBySqlString(hql);
	}

	public List<WeixinActivityQuestionEntity> getQuestions(String activityId){
		String hql = "from WeixinActivityQuestionEntity t where t.activityId='" + activityId + "' ORDER BY t.serial";
		List<WeixinActivityQuestionEntity>  list = this.findByQueryString(hql);
		for(WeixinActivityQuestionEntity questionEntity : list){
			questionEntity.setOptions(JsonUtil.parseJSON2List(questionEntity.getQuestionOption()));
		}
		return list;
	}
	/**
	 * 试题类 以周期做等级
	 * 
	 * @param frequency
	 * @param startTime
	 * @return
	 */
	public int getLevel(int frequency, Date startTime) {
		int level = 0;
		Calendar now = Calendar.getInstance();
		Calendar temp = Calendar.getInstance();
		temp.setTime(startTime);
		;
		switch (frequency) {
		case 1:
			level = now.get(Calendar.DAY_OF_YEAR) - temp.get(Calendar.DAY_OF_YEAR);
			break;
		case 2:
			level = now.get(Calendar.WEEK_OF_YEAR) - temp.get(Calendar.WEEK_OF_YEAR);
			break;
		case 3:
			level = now.get(Calendar.MONTH) - temp.get(Calendar.MONTH);
			break;
		default:
			break;
		}
		return level;
	}

	@Override
	public boolean inTimePart(WeixinActivityEntity activityEntity) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int nowHHmm = calendar.get(Calendar.HOUR_OF_DAY)*100+calendar.get(Calendar.MINUTE);
		String timePart = activityEntity.getTimePart();
		if(StringUtil.isEmpty(timePart))
			return true;
		try{
			switch (activityEntity.getFrequency()) {
			case 1:
				return inDayTimePart(timePart, nowHHmm);
			case 2:
				int day = calendar.get(Calendar.DAY_OF_WEEK)-1;
				if(day == 0)day = 7;
				return inTimePart(timePart, day+"", nowHHmm);
			case 3:
				int day1 = calendar.get(Calendar.DAY_OF_MONTH);
				return inTimePart(timePart, day1+"", nowHHmm);
			default:
				return true;
			}
			
		}catch (Exception e){
			logger.info("时间段设置异常",e);
			return true;
		}
		
	}
	/***
	 * 判断当日时间是否在之内
	 * @param part
	 * @param nowTime HHmm
	 * @return
	 */
	private boolean inDayTimePart(String part,int nowTime){
		String[] partList = part.split(";");
		for(String temp : partList){
			String[] startAndEnd = temp.split("-");
			if(startAndEnd.length != 2) return true;
			int startTime = Integer.valueOf(startAndEnd[0].replace(":","").replace("：", ""));
			int endTime = Integer.valueOf(startAndEnd[1].replace(":","").replace("：", ""));
			if(nowTime >= startTime && nowTime < endTime)
				return true;
		}
		return false;
	}
	/***
	 * 天数 加时间判断在不在之内
	 * @param part
	 * @param day number
	 * @param nowTime HHmm
	 * @return
	 */
	private boolean inTimePart(String part,String day,int nowTime){
		String[] timeAndDay = part.split("/");
		if(timeAndDay.length < 2)
			return inDayTimePart(part, nowTime);
		String[] days = timeAndDay[1].split(",");
		for(String dayString : days){
			if(day.equals(dayString))
				return inDayTimePart(timeAndDay[0], nowTime);
		}
		return false;
	}
	public String getOpreateType(int type){
		String Optype =  "未知";
		switch (type) {
		case 1:
			Optype = "大转盘";
			break;
		case 101:
			Optype = "答题";
			break;
		case 201:
			Optype = "吃月饼";
			break;
		case 202:
			Optype = "六角拼拼";
			break;
		case 203:
			Optype = "大转盘";
			break;

		default:
			break;
		}
		return Optype;
	}
}

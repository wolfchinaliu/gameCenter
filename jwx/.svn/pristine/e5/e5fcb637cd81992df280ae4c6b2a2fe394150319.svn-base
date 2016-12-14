package weixin.activity.service;

import java.util.Date;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import weixin.activity.entity.WeixinActivityEntity;
import weixin.activity.entity.WeixinActivityQuestionEntity;

/**
 * @Auth popl
 * @Date 2016年7月28日 下午2:00:55
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.lottery.service.WeixinActivityServiceI
 * @dec 
 */
public interface IWeixinActivityService extends CommonService {

	public void deleteActivity(WeixinActivityEntity entiy);

	public List<WeixinActivityQuestionEntity> getQuestions(String activityId);
	
	/**
	 * 试题类 以周期做等级
	 * 
	 * @param frequency
	 * @param startTime
	 * @return
	 */
	public int getLevel(int frequency, Date startTime);
	
	public boolean inTimePart(WeixinActivityEntity activityEntity);
	/**
	 * 获取游戏类型名称
	 * @param type
	 * @return
	 */
	public String getOpreateType(int type);
}


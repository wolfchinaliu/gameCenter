package weixin.gameCenter.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weixin.gameCenter.entity.WeixinGameFlowRecordEntity;
import weixin.gameCenter.entity.WeixinGameSafeRuleEntity;
import weixin.gameCenter.service.IGameService;
import weixin.integrate.entity.WxIntegrateSecretEntity;
import weixin.iplimit.entity.IPLimitEntity;

/**
 * @Auth popl
 * @Date 2016年9月7日 下午4:31:21
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.gameCenter.service.impl.GameServiceImpl
 * @dec
 */
@Service
public class GameServiceImpl extends CommonServiceImpl implements IGameService {

	@Autowired
	private CommonService commonService;

	@Override
	public boolean ipIsUsable(String ip, String acctId) {
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
		WxIntegrateSecretEntity wxSecret = commonService.findUniqueByProperty(WxIntegrateSecretEntity.class, "acctId",
				acctId);
		if (wxSecret == null) {
			return null;
		} else {
			return wxSecret.getSecret();
		}
	}

	public String saveGameFlowRecord(String gameId, String openId, String reqIP, String flowValue, int renCode) {
		WeixinGameFlowRecordEntity flowRecord = new WeixinGameFlowRecordEntity();
		flowRecord.setFlowValue(flowValue);
		flowRecord.setOpenId(openId);
		flowRecord.setGameId(gameId);
		flowRecord.setRenCode(renCode);
		flowRecord.setReqIP(reqIP);
		flowRecord.setReqTime(new Date());
		return (String)this.save(flowRecord);
	}

	@Override
	public boolean isSafeRule(String gameId, String ruleId, String openId, double flowValue) {
		WeixinGameSafeRuleEntity gameSafeRuleEntity = this.get(WeixinGameSafeRuleEntity.class, ruleId);
		// 先判断流量值
		if (flowValue > gameSafeRuleEntity.getMaxFlow())
			return false;
		// 判断总次数
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dm = new SimpleDateFormat("yyyy-MM");
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT COUNT(*) count FROM weixin_game_flow_rec t where (ren_code = 1 or ren_code = 0) ");
		buffer.append(" and t.game_id=").append("'").append(gameId).append("'");
		int frequency = gameSafeRuleEntity.getFrequency();
		switch (frequency) {
		case 1:
			buffer.append(" and DATE_FORMAT(t.req_time,'%Y-%m-%d')= ").append("'").append(d.format(new java.util.Date()))
					.append("'");
			break;
		case 2:
			buffer.append(" and DATE_FORMAT(t.req_time,'%Y-%m-%d')>= ").append("'").append(convertWeekByDate(new Date()))
					.append("'");
			break;
		case 3:
			buffer.append(" and DATE_FORMAT(t.req_time,'%Y-%m-%d')>= ").append("'").append(dm.format(new Date()))
					.append("-01'");
			break;
		default:
			break;
		}
		if (gameSafeRuleEntity.getMaxTimes() > 0) {
			long count = this.getCountForJdbc(buffer.toString());
			if (count >= gameSafeRuleEntity.getMaxTimes())
				return false;
		}
		// 判断个人次数
		buffer.append(" and t.OPEN_ID=").append("'").append(openId).append("'");
		long evencount = this.getCountForJdbc(buffer.toString());
		if (evencount >= gameSafeRuleEntity.getEveryoneTimes())
			return false;
		return true;
	}

	public String convertWeekByDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// LOGGER.info("要计算日期为:" + sdf.format(cal.getTime())); //输出要计算日期
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		String imptimeBegin = sdf.format(cal.getTime());
		// LOGGER.info("所在周星期一的日期:" + imptimeBegin);
		cal.add(Calendar.DATE, 6);
		// LOGGER.info("所在周星期日的日期:" + imptimeEnd);
		return imptimeBegin;
	}
}

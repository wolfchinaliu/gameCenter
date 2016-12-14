package weixin.gameCenter.service;

import org.jeecgframework.core.common.service.CommonService;

/**
 * @Auth popl
 * @Date 2016年9月6日 下午2:04:55
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.gameCenter.service.IGameService
 * @dec 游戏业务处理
 */
public interface IGameService extends CommonService{

	/**
	 * 判读供应商ip是否可用
	 * @param ip
	 * @return
	 */
	boolean ipIsUsable(String ip,String acctId);
	/**
	 * 获取商户密钥
	 * @param acctId
	 * @return
	 */
	public String getCustomerSecret(String acctId);
	
	/***
	 * 保存游戏 变换流量记录
	 * @param gameId
	 * @param reqIP
	 * @param flowValue
	 * @param renCode
	 */
	public String saveGameFlowRecord(String gameId,String openId,String reqIP,String flowValue,int renCode);
	/**
	 * 判断是否符合安全规则
	 * @param ruleId
	 * @param openId
	 * @param flowValue
	 * @return
	 */
	public boolean isSafeRule(String gameId,String ruleId,String openId,double flowValue);
}

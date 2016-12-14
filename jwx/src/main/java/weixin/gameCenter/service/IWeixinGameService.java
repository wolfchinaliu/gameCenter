package weixin.gameCenter.service;

import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.CommonService;

import weixin.gameCenter.entity.WeixinGameTypeEntity;

/**
 * @Auth popl
 * @Date 2016年9月7日 上午11:24:07
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.gameCenter.service.IWeixinGameService
 * @dec 
 */
public interface IWeixinGameService extends CommonService{

	/***
	 * 删除游戏类型
	 * 只能删除自己提交测试的 一旦审核成功 不允许删除
	 * @param id
	 * @return
	 */
	String deleteGameType(String id);
	
	void getDataGrid(WeixinGameTypeEntity gameTypeEntity,DataGrid dataGrid );
	
	String updateStatus(String id);
	public boolean canCat(String id);
}

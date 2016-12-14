package weixin.gameCenter.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.stereotype.Service;

import weixin.gameCenter.entity.WeixinGameTypeEntity;
import weixin.gameCenter.service.IWeixinGameService;
import weixin.liuliangbao.jsonbean.FlowAccount.WeixinAcctBean;
import weixin.report.model.UserGiveFlow;

/**
 * @Auth popl
 * @Date 2016年9月7日 下午4:34:10
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.gameCenter.service.impl.WeixinGameServiceImpl
 * @dec 游戏后端操作
 */
@Service
public class WeixinGameServiceImpl extends CommonServiceImpl implements IWeixinGameService {
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public String deleteGameType(String id) {
		try {
			WeixinGameTypeEntity entity = this.getEntity(WeixinGameTypeEntity.class, id);
			if (entity == null)
				return "记录不存在";
			if (entity.getStatus() != 2)
				return "此记录不允许删除";
			if (!ResourceUtil.getWeiXinAccountId().equals(entity.getAccountId()))
				return "没有操作权限";
			this.delete(entity);
			String hql = " delete from  weixin_other_game where game_type='" + id + "' ";
			this.updateBySqlString(hql);
			return "删除成功";
		} catch (Exception e) {
			logger.error("删除游戏类型异常", e);
			return "删除游戏类型失败";
		}
	}

	@Override
	public void getDataGrid(WeixinGameTypeEntity gameTypeEntity, DataGrid dataGrid) {
		// 先判断角色 如果是管理员 全部显示
		String sql = "select acct_level  from weixin_acct where id = '" + ResourceUtil.getWeiXinAccount().getAcctId()
				+ "'";
		String hql = "";
		List<Object> listAccount = this.findListbySql(sql);
		if (listAccount == null || listAccount.size() <= 0 || "0".equals(listAccount.get(0))) {
			hql = "select c.id ,c.game_Name ,c.logo_Path,c.status,c.add_Time from weixin_game_type c where 1 = 1 ";
		} else {
			// 不是管理员只显示 已上架的 和自己商户下的
			hql = "select c.id ,c.game_name ,c.logo_Path,c.status,c.add_Time from (select * from weixin_game_type  where status = 0 union all select * from weixin_game_type  where status != 0 and account_Id='"
					+ ResourceUtil.getWeiXinAccountId() + "') c where 1=1";
		}
		if (StringUtil.isNotEmpty(gameTypeEntity.getGameName())) {
			hql += " and c.game_Name like '%" + gameTypeEntity.getGameName() + "%' ";
		}
			hql += " order by  add_Time desc ";
		HqlQuery hqlQuery = new HqlQuery(WeixinGameTypeEntity.class, hql.toString(), dataGrid);
		PageList pageList = this.getPageListBySql(hqlQuery, false);
		List<Object[]> list = pageList.getResultList();
		List<WeixinGameTypeEntity> param = new ArrayList<WeixinGameTypeEntity>();

		for (Object[] objects : list) {
			WeixinGameTypeEntity typeEntity = new WeixinGameTypeEntity();
			Object id = objects[0];
			if (id != null) {
				typeEntity.setId(id.toString());
			}
			Object name = objects[1];
			if (name != null) {
				typeEntity.setGameName(name.toString());
			}
			Object logoPath = objects[2];
			if (logoPath != null) {
				typeEntity.setLogoPath(logoPath.toString());
			}
			Object status = objects[3];
			if (status != null) {
				typeEntity.setStatus((short) status);
			}
			Object getDate = objects[4];
			if (getDate != null) {
//				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				Date d = null;
//				try {
//					d = sim.parse(getDate.toString());
//				} catch (ParseException e) {
//					logger.error("游戏类型列表日期转换异常", e);
//				}
				typeEntity.setAddTime((Date)getDate);
			}
			param.add(typeEntity);
		}
		dataGrid.setResults(param);
		dataGrid.setTotal(pageList.getCount());
		dataGrid.setPage(pageList.getCurPageNO());
		dataGrid.setRows(pageList.getOffset());
	}

	@Override
	public String updateStatus(String id) {
		WeixinGameTypeEntity gameTypeEntity = this.get(WeixinGameTypeEntity.class, id);
		if(gameTypeEntity == null) return "游戏类型不存在";
		if(gameTypeEntity.getStatus() == 2){
			if(ResourceUtil.getWeiXinAccountId().equals(gameTypeEntity.getAccountId())){
				gameTypeEntity.setStatus((short)1);
				this.updateEntitie(gameTypeEntity);
				return "提交审核成功";
			}
			return "没有操作权限";
		}
		else if(gameTypeEntity.getStatus() == 1){
			String sql = "select acct_level  from weixin_acct where id = '" + ResourceUtil.getWeiXinAccount().getAcctId()
					+ "'";
			List<Object> listAccount = this.findListbySql(sql);
			if (listAccount == null || listAccount.size() <= 0 || "0".equals(listAccount.get(0))) {
				gameTypeEntity.setStatus((short)0);
				this.updateEntitie(gameTypeEntity);
				return "审核成功";
			}
			return "没有操作权限";
		}
		return "操作失败";
	}
	public boolean canCat(String id){
		WeixinGameTypeEntity gameTypeEntity = this.get(WeixinGameTypeEntity.class, id);
		if(gameTypeEntity == null) return false;
		String sql = "select acct_level  from weixin_acct where id = '" + ResourceUtil.getWeiXinAccount().getAcctId()
				+ "'";
		List<Object> listAccount = this.findListbySql(sql);
		if (listAccount == null || listAccount.size() <= 0 || "0".equals(listAccount.get(0))) return true;
		if(ResourceUtil.getWeiXinAccountId().equals(gameTypeEntity.getAccountId())) return true;
		return false;
	}
}

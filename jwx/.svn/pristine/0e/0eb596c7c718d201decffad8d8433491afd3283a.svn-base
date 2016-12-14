package weixin.guanjia.menu.service.impl;

import weixin.guanjia.menu.service.WeixinMenuGroupServiceI;

import org.antlr.grammar.v3.ANTLRParser.id_return;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.web.system.service.SystemService;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.core.req.model.menu.WeixinButton;
import org.jeewx.api.wxmenu.JwMenuAPI;

import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.menu.entity.MenuEntity;
import weixin.guanjia.menu.entity.WeixinMenuGroupEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.io.Serializable;

@Service("weixinMenuGroupService")
@Transactional
public class WeixinMenuGroupServiceImpl extends CommonServiceImpl implements WeixinMenuGroupServiceI {
	private static final Logger LOGGER = Logger.getLogger(WeixinMenuGroupServiceImpl.class);
	
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	@Autowired
	private SystemService systemService;
	

	private String[] viewtooTypeURLs = new String[] {"/jwx/mainController.do?load&accountid=",
	"/jwx/personFlowCenterController.do?goPersonCenter&accountid=",
	"/jwx/signController.do?startLoad&accountid=",
	"/jwx/bindingController.do?load&accountid=",
	"/jwx/userChargeController.do?userChargeView&accountid=",
	"/jwx/earnFlowController.do?moreFlow&accountid="};
	private String viewplayTypeURL = "/jwx/lotteryController.do?startLottery&hdid=";

	public <T> void delete(T entity) {
		super.delete(entity);
		// 执行删除操作配置的sql增强
		this.doDelSql((WeixinMenuGroupEntity) entity);
	}

	public <T> Serializable save(T entity) {
		Serializable t = super.save(entity);
		// 执行新增操作配置的sql增强
		this.doAddSql((WeixinMenuGroupEntity) entity);
		return t;
	}

	public <T> void saveOrUpdate(T entity) {
		super.saveOrUpdate(entity);
		// 执行更新操作配置的sql增强
		this.doUpdateSql((WeixinMenuGroupEntity) entity);
	}

	/**
	 * 默认按钮-sql增强-新增操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doAddSql(WeixinMenuGroupEntity t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-更新操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doUpdateSql(WeixinMenuGroupEntity t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-删除操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doDelSql(WeixinMenuGroupEntity t) {
		return true;
	}

	/**
	 * 替换sql中的变量
	 * 
	 * @param sql
	 * @return
	 */
	public String replaceVal(String sql, WeixinMenuGroupEntity t) {		
		sql = sql.replace("#{id}", String.valueOf(t.getId()));
		sql = sql.replace("#{synch_date}", String.valueOf(t.getSynchDate()));
		sql = sql.replace("#{group_name}", String.valueOf(t.getGroupName()));
		sql = sql.replace("#{accountid}", String.valueOf(t.getAccountid()));
		sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
		return sql;
	}

	@Override
	public void refreshMenus(String accountId) {
		try {
			String accessToken = weixinAccountService.getAccessTokenByPrimaryKey(ResourceUtil.getWeiXinAccountId());
			List<WeixinButton> weixinButtons = JwMenuAPI.getAllMenu(accessToken);
			if (CollectionUtils.isNotEmpty(weixinButtons)){
				String sql = "delete from weixin_menuentity where accountid = ?";
				this.systemService.executeSql(sql,accountId);
			}
			LOGGER.info("从微信端获取到的菜单的内容为： " + weixinButtons); 
			this.saveMenus(weixinButtons, accountId, null);
			
		} catch (WexinReqException e) {
			e.printStackTrace();
		}
	}

	private void saveMenus(List<WeixinButton> weixinButtons, String accountId, String fatherId) {
		if (CollectionUtils.isEmpty(weixinButtons))
			return;
		int order = 1;
		for (WeixinButton weixinButton : weixinButtons) {
			MenuEntity menuEntity = new MenuEntity();
			menuEntity.setId(UUID.randomUUID().toString());
			menuEntity.setMenuKey(weixinButton.getKey());
			menuEntity.setName(weixinButton.getName());
			menuEntity.setUrl(weixinButton.getUrl());
			List<MenuEntity> fatherMenuEntities = this.systemService.findHql("from MenuEntity where id=?", fatherId);
			if (CollectionUtils.isNotEmpty(fatherMenuEntities)) {
				MenuEntity fatherMenuEntity = fatherMenuEntities.get(0);
				menuEntity.setMenuEntity(fatherMenuEntity);
			}
			
			if (StringUtils.isNotBlank(weixinButton.getUrl())) {
				for (int i = 0; i < viewtooTypeURLs.length; i++) {
					if (weixinButton.getUrl().contains(viewtooTypeURLs[i])) {
						menuEntity.setPagetype(weixinButton.getUrl());
						menuEntity.setType("viewtoo");
						menuEntity.setUrl("");
						break;
					}
					if (weixinButton.getUrl().contains(viewplayTypeURL)) {
						menuEntity.setPageurl(weixinButton.getUrl());
						menuEntity.setType("viewplay");
						menuEntity.setUrl("");
						break;
					}
				}
			}else {
				menuEntity.setType(weixinButton.getType());
			}
			menuEntity.setAccountId(accountId);
			if(StringUtil.isEmpty(menuEntity.getAccountId())) 
			
			menuEntity.setOrders((order += 1) + "");			
			List<WeixinMenuGroupEntity> weixinMenuGroupEntities = this.findByProperty(WeixinMenuGroupEntity.class, "accountid", accountId);
			if (CollectionUtils.isNotEmpty(weixinMenuGroupEntities)) {
				WeixinMenuGroupEntity weixinMenuGroupEntity = weixinMenuGroupEntities.get(0);
				weixinMenuGroupEntity.setSynchDate(new Date());
				this.updateEntitie(weixinMenuGroupEntity);
				menuEntity.setWeixinMenuGroupEntity(weixinMenuGroupEntity);
			}

			this.systemService.save(menuEntity);

			List<WeixinButton> sub_button = weixinButton.getSub_button();
			if (CollectionUtils.isNotEmpty(sub_button)) {
				this.saveMenus(sub_button, accountId, menuEntity.getId());
			}
		}
	}
}
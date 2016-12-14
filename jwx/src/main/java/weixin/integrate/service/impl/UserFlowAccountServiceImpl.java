package weixin.integrate.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.integrate.entity.UserFlowAccountEntity;
import weixin.integrate.service.UserFlowAccountService;
@Service("userFlowAccountService")
@Transactional
public class UserFlowAccountServiceImpl  extends CommonServiceImpl implements UserFlowAccountService{
	public <T> void saveOrUpdate(T entity) {
		super.saveOrUpdate(entity);
		// 执行更新操作配置的sql增强
		this.doUpdateSql((UserFlowAccountEntity) entity);
	}
	@Override
	public boolean doAddSql(UserFlowAccountEntity t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdateSql(UserFlowAccountEntity t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doDelSql(UserFlowAccountEntity t) {
		// TODO Auto-generated method stub
		return false;
	}

}

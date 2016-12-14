package weixin.memberStructure.service.impl;

import java.io.Serializable;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.memberStructure.entity.LogMemberDatailedEntity;
import weixin.memberStructure.service.LogMemberDatailedService;

/**
 * @author Sukin
 * 2016年9月5日
 */
@Service("logMemberDatailedService")
@Transactional
public class LogMemberDatailedServiceImpl  extends CommonServiceImpl implements LogMemberDatailedService{

	@Override
	public boolean doAddSql(LogMemberDatailedEntity t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdateSql(LogMemberDatailedEntity t) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
    public <T> void saveOrUpdate(LogMemberDatailedEntity entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((LogMemberDatailedEntity) entity);
    }

	@Override
	public <T> Serializable save(LogMemberDatailedEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	 
 
 
	
	
}

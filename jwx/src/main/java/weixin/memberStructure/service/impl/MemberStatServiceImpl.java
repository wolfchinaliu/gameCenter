package weixin.memberStructure.service.impl;

import java.io.Serializable;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.memberStructure.entity.LogMemberDatailedEntity;
import weixin.memberStructure.entity.MemberStatEntity;
import weixin.memberStructure.service.MemberStatService;

/**
 * @author Sukin
 * 2016年9月5日
 */
@Service("memberStatService")
@Transactional
public class MemberStatServiceImpl  extends CommonServiceImpl implements MemberStatService{

	@Override
	public <T> Serializable save(MemberStatEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
 
	@Override
    public <T> void saveOrUpdate(MemberStatEntity entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((MemberStatEntity) entity);
    }
	@Override
	public boolean doAddSql(MemberStatEntity t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdateSql(MemberStatEntity t) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}

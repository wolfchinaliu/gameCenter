package weixin.manual.service.impl;

import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.manual.entity.ManualGiven;
import weixin.manual.entity.ManualGivenRecords;
import weixin.manual.service.ManualGivenRecordsService;

@Service("manualGivenRecordsService")
@Transactional
public class ManualGivenRecordsServiceImpl extends CommonServiceImpl implements ManualGivenRecordsService{
	public <T> void saveOrUpdate(T entity) {
		super.saveOrUpdate(entity);
		// 执行更新操作配置的sql增强
		this.doUpdateSql((ManualGivenRecords) entity);
	}
	@Override
	public boolean doAddSql(ManualGivenRecords t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdateSql(ManualGivenRecords t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doDelSql(ManualGivenRecords t) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void updateByPatchNo(List<ManualGivenRecords> lm) {
		for(ManualGivenRecords mgr:lm){
			this.doUpdateSql(mgr);
		}				
	}
	@Override
	public <T> void batchSave(List<T> entitys) {
		this.commonDao.batchSave(entitys);
	}

}

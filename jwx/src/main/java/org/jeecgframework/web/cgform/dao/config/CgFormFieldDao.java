package org.jeecgframework.web.cgform.dao.config;

import java.util.List;
import java.util.Map;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;

/**
 * 
 * @Title:CgFormFieldDao
 * @description:
 * @author
 * @date Aug 24, 2013 11:33:33 AM
 * @version V1.0
 */
@MiniDao
public interface CgFormFieldDao {
	
	@Arguments("tableName")
	public List<Map<String, Object>> getCgFormFieldByTableName(String tableName);
	
	@Arguments("tableName")
	public List<Map<String, Object>> getCgFormHiddenFieldByTableName(String tableName);
	
}

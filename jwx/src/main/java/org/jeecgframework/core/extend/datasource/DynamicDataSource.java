package org.jeecgframework.core.extend.datasource;

import java.util.Map;

import org.jeecgframework.core.aop.DataSourceSwitcher;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;

/**
 *类名：DynamicDataSource.java
 *功能：动态数据源类
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	/* 
	 * 该方法必须要重写  方法是为了根据数据库标示符取得当前的数据库
	 */
	protected Object determineCurrentLookupKey() {
	    return DataSourceSwitcher.getDataSource();
	}

}
package org.jeecgframework.core.aop;

import org.jeecgframework.core.extend.datasource.DataSourceType;

/**
 * 读写分离数据库切换工具
 * Created by zhaotongbeyond@qq.com on 2015/7/27.
 */

import org.springframework.util.Assert;

public class DataSourceSwitcher {
    @SuppressWarnings("rawtypes")
    public static final ThreadLocal<DataSourceType> THREAD_LOCAL = new ThreadLocal<>();

    @SuppressWarnings("unchecked")
    public static void setDataSource(DataSourceType dataSourceType) {
        if(dataSourceType==null){
            dataSourceType = DataSourceType.dataSource_master;
        }
        THREAD_LOCAL.set(dataSourceType);
    }

    public static DataSourceType getDataSource() {
        DataSourceType dsType = THREAD_LOCAL.get();
        if(dsType==null){
            dsType= DataSourceType.dataSource_master;
        }
        return dsType;
    }

    public static void clearDataSource() {
        THREAD_LOCAL.remove();
    }
}
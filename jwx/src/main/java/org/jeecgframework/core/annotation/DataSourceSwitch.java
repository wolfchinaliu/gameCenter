package org.jeecgframework.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.jeecgframework.core.extend.datasource.DataSourceType;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Documented
public @interface DataSourceSwitch {
    DataSourceType dataSource() default DataSourceType.dataSource_master;
}

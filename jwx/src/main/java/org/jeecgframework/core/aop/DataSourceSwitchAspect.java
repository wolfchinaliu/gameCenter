package org.jeecgframework.core.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.jeecgframework.core.annotation.DataSourceSwitch;
import org.jeecgframework.core.extend.datasource.DataSourceType;

public class DataSourceSwitchAspect {
    
    @Around(value="@annotation(org.jeecgframework.core.annotation.DataSourceSwitch)")
    public Object switchDataSource(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();
        DataSourceType origen = DataSourceSwitcher.getDataSource();
        Object result=null;
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        DataSourceSwitch ds = (DataSourceSwitch)targetMethod.getAnnotation(DataSourceSwitch.class);
        
        try {
            if(ds!=null){
                DataSourceType dsType = ds.dataSource();
                DataSourceSwitcher.setDataSource(dsType);
            }
            if ((args != null) && (args.length != 0)) {
                result = joinPoint.proceed(args);
            } else {
                result = joinPoint.proceed();
            }
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        } finally {
            DataSourceSwitcher.setDataSource(origen);
        }
        return result;
    }
}

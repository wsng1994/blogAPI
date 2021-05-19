package com.wsng.blog.config.datasource;/**
 * @Author Cooper
 * @Date: 2021/4/11 18:32
 * @Version 0.01
 */

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 *  @Author Sean
 *  @Date: 2021/4/11 18:32
 *  @Version 0.01
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

//    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
//        super.setDefaultTargetDataSource(defaultTargetDataSource);
//        super.setTargetDataSources(targetDataSources);
//        super.afterPropertiesSet();
//    }



    @Override
    protected Object determineCurrentLookupKey() {
//        System.out.println("数据源为{}"+DataSourceContextHolder.get());
        return DataSourceContextHolder.get();
    }
}


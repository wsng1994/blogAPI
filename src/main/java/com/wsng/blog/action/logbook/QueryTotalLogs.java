package com.wsng.blog.action.logbook;

import com.wsng.blog.core.plugin.CoreService;
import com.wsng.blog.config.datasource.DataSourceKey;
import com.wsng.blog.config.annotation.TargetDataSource;
import com.wsng.blog.core.plugin.IDatasProcessor;
import com.wsng.blog.core.plugin.IRouterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Cooper
 * @Date: 20210220 12:40
 * @Version 0.01
 */

@Component
public class QueryTotalLogs implements CoreService {

    @Autowired
    private IRouterDao iRouterDao;
   @TargetDataSource(dataSourceKey = DataSourceKey.DB_BLOG)
    @Override
    public int executor(IDatasProcessor dr) {
//        IRouterDao iRouterDao = new RouterDao();
        int totals = iRouterDao.getTotalLogs("");
        dr.setParam("total",totals);
        return 1;
    }
}

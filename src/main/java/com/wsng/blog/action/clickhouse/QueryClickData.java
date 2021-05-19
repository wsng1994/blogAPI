package com.wsng.blog.action.clickhouse;/**
 * @Author Cooper
 * @Date: 2021/4/9 17:37
 * @Version 0.01
 */

import com.wsng.blog.core.plugin.CoreService;
import com.wsng.blog.config.datasource.DataSourceKey;
import com.wsng.blog.config.annotation.TargetDataSource;
import com.wsng.blog.config.annotation.LogPoint;
import com.wsng.blog.core.plugin.IDatasProcessor;
import com.wsng.blog.core.plugin.IRouterDaoCK;
import com.wsng.blog.core.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  @Author Sean
 *  @Date: 2021/4/9 17:37
 *  @Version 0.01
 *
 */
@Component
public class QueryClickData implements CoreService {

    @Autowired
    IRouterDaoCK iRouterDao;


    @LogPoint
    @TargetDataSource(dataSourceKey = DataSourceKey.DB_CK)
    @Override
    public int executor(IDatasProcessor dr) {

        String tot = (String) dr.getParam("total");
        if(tot==null||!"".equals(tot)){
          Integer tt =   iRouterDao.getClickHouseCounts("",dr);
          dr.setParam("total",tt);
        }

        dr = PageUtil.pageHandle(dr);
        Integer offSet = (Integer)dr.getParam("offSet");
        Integer pageSize = (Integer)dr.getParam("pageSize");

        List<Object> list =  iRouterDao.getClickHouseDatas("",offSet,pageSize);
        dr.setParam("list",list);
        return 0;
    }
}

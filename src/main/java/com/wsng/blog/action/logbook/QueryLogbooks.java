package com.wsng.blog.action.logbook;

import com.wsng.blog.core.plugin.CoreService;
import com.wsng.blog.core.plugin.IDatasProcessor;
import com.wsng.blog.core.plugin.IRouterDao;
import com.wsng.blog.core.util.DateUtil;
import com.wsng.blog.core.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author Cooper
 * @Date: 20210313 10:57
 * @Version 0.01
 */
@Component
public class QueryLogbooks implements CoreService {

    @Autowired
    private IRouterDao iRouterDao;

    @Override
    public int executor(IDatasProcessor dr) {

        dr = PageUtil.pageHandle(dr);

        Integer offSet = (Integer)dr.getParam("offSet");
        Integer pageSize = (Integer)dr.getParam("pageSize");

        List<Map> list = iRouterDao.queryLogbooks("",offSet, pageSize);

        list = DateUtil.reList(list,"yyyy-MM-dd HH:mm:ss","create_time");

        dr.setParam("logbooks",list);
        return 1;
    }
}

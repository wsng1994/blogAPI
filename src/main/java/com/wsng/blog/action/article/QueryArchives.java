package com.wsng.blog.action.article;

import com.wsng.blog.core.plugin.CoreService;
import com.wsng.blog.core.plugin.IDatasProcessor;
import com.wsng.blog.core.plugin.IRouterDao;
import com.wsng.blog.core.util.ResListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author Cooper
 * @Date: 20210311 17:21
 * @Version 0.01
 */

@Component
public class QueryArchives implements CoreService {

    @Autowired
    IRouterDao iRouterDao;

    @Override
    public int executor(IDatasProcessor dr) {

        List<Map> archives = iRouterDao.queryArchives("",dr);

        List<Map<String,Object>> list =  ResListUtil.getReList(archives);

        dr.setParam("archives",list);

        return 1;
    }
}

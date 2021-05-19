package com.wsng.blog.action.article;

import com.wsng.blog.core.plugin.CoreService;
import com.wsng.blog.core.plugin.IDatasProcessor;
import com.wsng.blog.core.plugin.IRouterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Cooper
 * @Date: 20210314 18:12
 * @Version 0.01
 */
@Component
public class GetCommentsCount implements CoreService {

    @Autowired
    IRouterDao iRouterDao;
    @Override
    public int executor(IDatasProcessor dr) {

        String id = (String) dr.getParam("id");
        int count = iRouterDao.getCommentsCount("",id);
        dr.setParam("count",count);

        return 1;
    }
}

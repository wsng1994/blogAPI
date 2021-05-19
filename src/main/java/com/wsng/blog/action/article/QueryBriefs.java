package com.wsng.blog.action.article;


import com.wsng.blog.core.plugin.CoreService;
import com.wsng.blog.core.plugin.IDatasProcessor;
import com.wsng.blog.core.plugin.IRouterDao;
import com.wsng.blog.core.util.PageUtil;
import com.wsng.blog.entity.Articles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class QueryBriefs implements CoreService {

    @Autowired
    private IRouterDao iRouterDao;

    @Override
    public int executor(IDatasProcessor dr){


        dr = PageUtil.pageHandle(dr);
        Integer offSet = (Integer)dr.getParam("offSet");
        Integer pageSize = (Integer)dr.getParam("pageSize");
        List<Articles> list = iRouterDao.queryBriefs("", offSet, pageSize);
        if(list ==null) return 0;
        dr.setParam("acticleBgs",list);
        return 1;
    }

}

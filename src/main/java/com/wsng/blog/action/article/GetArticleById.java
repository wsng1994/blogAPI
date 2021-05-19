package com.wsng.blog.action.article;

import com.wsng.blog.core.plugin.CoreService;
import com.wsng.blog.core.plugin.IDatasProcessor;
import com.wsng.blog.core.plugin.IRouterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author Cooper
 * @Date: 20210312 11:03
 * @Version 0.01
 */
@Component
public class GetArticleById implements CoreService {

    @Autowired
    private IRouterDao iRouterDao;

    @Override
    public int executor(IDatasProcessor dr) {

        String id = (String)dr.getParam("id");

        Map<String,Object> articleInfo = iRouterDao.getArticleById("",id);

        dr.setParam("articleInfo",articleInfo);
        return 1;
    }
}

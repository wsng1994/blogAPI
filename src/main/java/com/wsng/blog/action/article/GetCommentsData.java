package com.wsng.blog.action.article;

import com.wsng.blog.action.clickhouse.SaveClickData;
import com.wsng.blog.core.plugin.CoreService;
import com.wsng.blog.config.annotation.LogPoint;
import com.wsng.blog.core.plugin.IDatasProcessor;
import com.wsng.blog.core.plugin.IRouterDao;
import com.wsng.blog.core.util.CommentsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author Cooper
 * @Date: 20210313 17:35
 * @Version 0.01
 */
@Component
public class GetCommentsData implements CoreService {

    private final static Logger logger = LoggerFactory
            .getLogger(SaveClickData.class);

    @Autowired
    IRouterDao iRouterDao;
    @Autowired
    CommentsUtil commentsUtil;

    @LogPoint
    @Override
    public int executor(IDatasProcessor dr) {
        String id = (String) dr.getParam("id");
        List list = iRouterDao.getCommentsData("",id);
        list = commentsUtil.commentsHandle(list);
        dr.setParam("commentsList",list);
        return 1;
    }
}

package com.wsng.blog.action.article;

import com.wsng.blog.core.plugin.CoreService;
import com.wsng.blog.core.plugin.IDatasProcessor;
import com.wsng.blog.core.plugin.IRouterDao;
import com.wsng.blog.entity.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author Cooper
 * @Date: 20210314 15:21
 * @Version 0.01
 */
@Component
public class SetComments implements CoreService {

    @Autowired
    private IRouterDao iRouterDao;

    @Override
    public int executor(IDatasProcessor dr) {

        //获取ip 设备号 后期补完暂时不管

        //添加评论审核 后期补完
//        System.out.println("************"+dr);

        Comments comments = new Comments();

        Map<String,Object> map =  (Map<String,Object>)dr.getParam("cfg");

        comments.setAuthor(map.get("nc").toString());
        comments.setEmail(map.get("dzyj").toString());
        comments.setPost_id(Long.parseLong(map.get("postId").toString()));
        comments.setParent_id(Long.parseLong(map.get("pid").toString()));
        comments.setType(0);
        comments.setContent((String)map.get("content"));
        Boolean flag = iRouterDao.setComments("",comments);
        if(flag){
            dr.setParam("status",200);
            return 1;
        }else{
            return 0;
        }

    }
}

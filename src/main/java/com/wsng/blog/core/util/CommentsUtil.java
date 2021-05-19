package com.wsng.blog.core.util;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author Cooper
 * @Date: 20210313 17:22
 * @Version 0.01
 * 用于处理评论数据的多级问题
 */
@Component
public class CommentsUtil {



    //评论最多展示二级深度，超过皆按二级处理
    //首先获取所有的一级标签 按评论时间倒叙排列
    //根据一级评论查找所有的二级评论 并将二级评论数据组装到对应的一级评论下


    public  List<Map> commentsHandle(List<Map> list){

        //处理日期
        list  = DateUtil.reList(list,"yyyy/MM/dd HH:mm:ss","update_time");
        return this.recursive(list);
    }

    //暂时不用递归处理
    private List<Map> recursive(List<Map> list) {
        List<Map> listFirst = new ArrayList<>();
        for(int i=list.size()-1;i>=0;i--){
            Map map = new HashMap();
            map = (Map)list.get(i);
            long pid = (Long)map.get("parent_id");

            List<Object> cl = new ArrayList<>();
            if (pid==0){
                Map map1 = new HashMap();
                map1.put("isParent",1);
                map1.put("parentInfo",map);
                map1.put("childInfo",cl);
                map1.put("id",map.get("id"));
                map1.put("author",map.get("author"));
                listFirst.add(map1);
                list.remove(i);
            }else{
                 for(int j=0;j<listFirst.size();j++){
                     Map map2 = (Map)listFirst.get(j);
                     cl = (List<Object>) map2.get("childInfo");
                     if(cl.size()>0){
                         for(int k=0; k<cl.size();k++){
                             Map map3 = (Map) cl.get(k);
                             long cid = (Long)map3.get("id");
                             String pAuthor3 = (String)map3.get("author");
                             if(cid==pid){
                                 map.put("backTo",pAuthor3);
                                 cl.add(map);
                                 if(list.size()>0){
                                     list.remove(i);
                                 }
                             }
                         }

                     }
                     long id = (Long)map2.get("id");
                     String pAuthor2 = (String)map2.get("author");
                     if(pid==id){
                         map.put("backTo",pAuthor2);
                         cl.add(map);
                         map2.put("childInfo",cl);
                         if(list.size()>0){
                             list.remove(i);
                         }
                     }
                 }

            }
        }
        return listFirst;
    }
}

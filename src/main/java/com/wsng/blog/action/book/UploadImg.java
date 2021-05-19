package com.wsng.blog.action.book;


import com.wsng.blog.core.plugin.CoreService;
import com.wsng.blog.core.plugin.IDatasProcessor;
import com.wsng.blog.core.util.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  @Author Sean
 *  @Date: 2021/3/22 19:33
 *  @Version 0.01
 *
 */
@Component
public class UploadImg implements CoreService {

    @Autowired
    private ImgUtil imgUtil;
    @Override
    public int executor(IDatasProcessor dr) {

        Map drMap = (Map) dr.getParam("params");
        List<Map> imgList = (List<Map>)drMap.get("imgs");
        String markdownContext = (String) drMap.get("context");
        List urlList = new ArrayList();
        for (Map map:imgList){
            String base = (String) map.get("base");
            String type = (String) map.get("name");

           String url =  imgUtil.uploadImg(base,type);
            urlList.add(url);
            if(markdownContext.contains(type)){
                markdownContext =  markdownContext.replaceAll(type,url);
            }
        }
        drMap.put("context",markdownContext);
        Map map = reloadData(drMap,urlList);
        dr.removeParam("params");
        dr.getParamMap().putAll(map);
        return 1;
    }
    private Map reloadData(Map map,List list){
        Map resultMap = new HashMap<>();
        resultMap.put("bookName",map.get("bookName"));
        resultMap.put("brief",map.get("brief"));
        resultMap.put("cover", list==null?"":list.get(0));
        resultMap.put("context",map.get("context"));
        resultMap.put("comments",map.get("comments"));
        return resultMap;
    }
}

package com.wsng.blog.config.base;

import com.wsng.blog.core.base.DatasProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Sean
 * @Date: 2021/4/20 20:38
 * @Version 0.01
 */

@Component
public class ParamFilter {
    /**
     * 配置拦截
     */
    public  boolean filters(Object[] args){
        boolean isFilter = true;
        Map map = new HashMap<>();
        for(Object o:args){
            if(o instanceof DatasProcessor){
                map = ((DatasProcessor) o).getParamMap();
            }
        }
        String id="0";
        if(map.size()>0){
            id = map.get("id")==null?"0":map.get("id").toString();
        }
        if("3".equals(id)){
            return false;
        }

        return true;
    }

}

package com.wsng.blog.core.plugin;

import java.util.Map;

/**
 * @auther wsng
 * @date
 */

public interface IDatasProcessor {
    Map<String,Object> getDRMap();
    Map<String,Object> getParamMap();
    boolean setParamMap(Object o);
    boolean setParam(String var1,Object o);
    Object getParam(String var1);
    boolean getParamMap(Map o);
    boolean removeParam(String var1);
}

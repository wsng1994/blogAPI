package com.wsng.blog.core.base;

import com.wsng.blog.core.plugin.IDatasProcessor;

import java.util.HashMap;
import java.util.Map;

public class DatasProcessor implements IDatasProcessor {

    private Map<String,Object> a;
    @Override
    public Map<String, Object> getDRMap() {
        return null;
    }

    public DatasProcessor() {
        this(new HashMap());
    }

    public DatasProcessor(Map param) {
        this.a = param;
    }

    @Override
    public Map getParamMap() {
        return this.a;
    }

    @Override
    public boolean getParamMap(Map o) {
        this.a = o;
        return true;
    }

    @Override
    public boolean removeParam(String var1) {
        this.a.remove(var1);
        return var1==null?false:true;
    }

    @Override
    public boolean setParamMap(Object o) {
        this.a = (Map)o;
        return o==null?false:true;
    }

    @Override
    public boolean setParam(String var1, Object o) {
        this.a.put(var1,o);
        return true;
    }

    @Override
    public Object getParam(String var1) {
        return this.a.get(var1);
    }

    @Override
    public String toString(){
        return "";
    }
}

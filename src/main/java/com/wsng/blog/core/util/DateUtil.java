package com.wsng.blog.core.util;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @Author Cooper
 * @Date: 20210313 11:40
 * @Version 0.01
 */
public class DateUtil {

    public static List<Map> reList(List<Map> list,String fmt,String key){

        for(Map map : list){

            java.sql.Timestamp time = (Timestamp) map.get(key);
            String str =  ResListUtil.dateToStr(time,fmt);
            map.put(key,str);

        }

        return list;
    }
}

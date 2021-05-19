package com.wsng.blog.core.util;

import com.wsng.blog.entity.MyLogPoint;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author Cooper
 * @Date: 20210311 17:53
 * @Version 0.01
 */
public class ResListUtil {

    public static  List<Map<String,Object>> getReList(List<Map> list){

        List<Map<String,Object>> zl = new ArrayList<>();
        for(Map map:list){
            java.sql.Timestamp time = (Timestamp) map.get("create_time");
            String str =  dateToStr(time,"yyyy-MM");
            String str1 =  dateToStr(time,"MM-dd");
            map.put("create_time",str);
            map.put("w_time",str1);
        }

        zl = buildList(list);

        return zl;
    }


    public static List<Map<String,Object>> buildList(List<Map> list){

        List<Map<String,Object>> zl = new ArrayList<>();
        Set<Object> set = new HashSet();

        for(int i=0;i<list.size();i++){
            Map<String,Object> nm = new HashMap();
            Map m = list.get(i);
            String date = m.get("create_time").toString();
            List<Map> listMap = new ArrayList<>();
            for(int j=0;j<list.size();j++){
                Map m1 = list.get(j);
                String date1 = m1.get("create_time").toString();
                if(date.equals(date1)){
                    listMap.add(m1);
                }
            }
            nm.put("date",date);
            nm.put("dateList",listMap);

            //利用set特性处理重复数据，简化业务逻辑
            boolean flag = set.add(date);
            if(zl!=null&&zl.size()>0){
                if(flag){
                    zl.add(nm);
                }
            }else{
                zl.add(nm);
            }
        }
        return zl;
    }


    public static String dateToStr(java.sql.Timestamp time, String strFormat) {
        DateFormat df = new SimpleDateFormat(strFormat);
        String str = df.format(time);
        return str;
    }

    public static MyLogPoint rebuildObj(List<String> list){

        MyLogPoint myLogPoint = new MyLogPoint();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss.SSS");

        myLogPoint.setClazz(list.get(4));
        myLogPoint.setNodeName(list.get(2));
        myLogPoint.setTransSeq(list.get(0));
        myLogPoint.setMethod(list.get(5));

        if("0".equals(list.get(2))){
            myLogPoint.setEndMilsTime(null);
            myLogPoint.setEndTime(null);
            Date date = new Date(Long.parseLong(list.get(1)));
            myLogPoint.setStrMilsTime(Long.parseLong(list.get(1)));
            myLogPoint.setStartTime(format.format(date));
        }else if("2".equals(list.get(2))){
            myLogPoint.setStrMilsTime(null);
            myLogPoint.setStartTime(null);
            Date date = new Date(Long.parseLong(list.get(3)));
            myLogPoint.setEndMilsTime(Long.parseLong(list.get(3)));
            myLogPoint.setEndTime(format.format(date));
        }else{
            Date date1 = new Date(Long.parseLong(list.get(1)));
            myLogPoint.setStrMilsTime(Long.parseLong(list.get(1)));
            myLogPoint.setStartTime(format.format(date1));
            Date date = new Date(Long.parseLong(list.get(3)));
            myLogPoint.setEndMilsTime(Long.parseLong(list.get(3)));
            myLogPoint.setEndTime(format.format(date));
        }

        return myLogPoint;
    }

    public static Object splitList( Object list,int t) {
        List<List<Object>> listResult = new ArrayList<>();
        int size = ((List)list).size();
        int temp = size/t +1;
        boolean result = size % t == 0;
        for (int i = 0; i < temp; i++) {
            if (i == temp - 1) {
                if (result) {
                    break;
                }
                listResult.add(((List)list).subList(t * i, size)) ;
            } else {
                listResult.add(((List)list).subList(t * i, t * (i + 1))) ;
            }
        }
        return listResult;
    }
}

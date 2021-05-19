package com.wsng.blog.core.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wsng.blog.config.logpoint.LogPointAspect;
import com.wsng.blog.core.plugin.IRouterFlow;
import com.wsng.blog.core.plugin.IDatasProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author wsng
 * @date 2019/12/29.
 */
@Service("routerFlow")
@PropertySource(value = {"classpath:config/config.properties"})
public class RouterFlow implements IRouterFlow {

    private static final Logger logger = LoggerFactory
            .getLogger(RouterFlow.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${coreMethodName}")
    private String coreMethodName;

    @Value("${serviceClassName}")
    private String serviceClassName;

    /**
     * @return
     */
    @Override
    public boolean execute(IDatasProcessor dr, String transCode) {

        String[] paths = transCode.split("/");
        String className = paths[1];

//        String path = RouterFlow.class.getClassLoader().getResource("flow/"+transCode.toLowerCase()+".json").getPath();

        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("flow/"+transCode.toLowerCase()+".json");

        String s = readJsonFile(inputStream);
        JSONObject jobJson = JSON.parseObject(s);

        List<Map<String,Object>> flwList =
                (List<Map<String,Object>>)jobJson.get("flwList");

        System.out.println("接口执行：{}"+flwList.size());
        for(Map<String,Object> map:flwList){
            String name = (String) map.get("node");
            className = serviceClassName+name;

            try {
                Class clz = Class.forName(className);
                //这里可以使反射的时候自动注入spring的bean
                Object bean = applicationContext.getBean(clz);
                //获取方法
                Method m = clz.getMethod(coreMethodName,IDatasProcessor.class);
                //调用方法
                int result =  (Integer) m.invoke(bean,dr);
//                if(result!=1){
////                    System.out.println("----流程执行失败----");
//                    break;
//                }

            } catch (Exception e) {
                System.out.println("反射执行方法失败：{}"+map.toString());
                e.printStackTrace();
            }
        }

        return false;
    }


    /**
     * 读取json文件，返回json串
     * @param inputStream
     * @return
     */
    public static String readJsonFile(InputStream inputStream) {
        String jsonStr = "";
        try {
            InputStreamReader reader = new InputStreamReader(inputStream,"utf-8");

            BufferedReader bfReader = new BufferedReader( reader );
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = bfReader.read()) != -1) {
                sb.append((char) ch);
            }
            bfReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            System.out.println("读取json文件失败：{}"+inputStream.toString());
            e.printStackTrace();
            return null;
        }


    }


}

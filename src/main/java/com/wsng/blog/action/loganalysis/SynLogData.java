package com.wsng.blog.action.loganalysis;

import com.wsng.blog.action.clickhouse.AsyncClickDataIns;
import com.wsng.blog.action.clickhouse.SaveClickData;
import com.wsng.blog.config.annotation.LogPoint;
import com.wsng.blog.core.plugin.CoreService;
import com.wsng.blog.core.plugin.IDatasProcessor;
import com.wsng.blog.core.util.ResListUtil;
import com.wsng.blog.entity.CKbatchData;
import com.wsng.blog.entity.MyLogPoint;
import javafx.print.Collation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author Sean
 * @Date: 2021/4/27 15:40
 * @Version 0.01
 */
@Component
public class SynLogData implements CoreService {

    private final static Logger logger = LoggerFactory
            .getLogger(SaveClickData.class);

    @Autowired
    private AsyncClickDataIns asyncClickDataIns;


    private  void generatorDBList(List<String> list)  {

        List<MyLogPoint> logPointList = new ArrayList<>();
        for(int i=0;i<list.size()-1;i++){
            MyLogPoint logPoint = new MyLogPoint();
            List<String> stringList = (List<String>)CollectionUtils.arrayToList(list.get(i).split(" "));
            logPoint = ResListUtil.rebuildObj(stringList);
            logPointList.add(logPoint);
        }
        List<List<MyLogPoint>> lists = (List<List<MyLogPoint>>)ResListUtil.splitList(logPointList, 5000);
        CountDownLatch countDownLatch = new CountDownLatch(lists.size());
        for (List<MyLogPoint> listSub:lists) {
            asyncClickDataIns.executeAsync2(listSub,countDownLatch);
        }
        try {
            countDownLatch.await(); //保证之前的所有的线程都执行完成，才会走下面的；
            // 这样就可以在下面拿到所有线程执行完的集合结果
        } catch (Exception e) {
            logger.error("阻塞异常:"+e.getMessage());
        }


    }

    @LogPoint
    @Override
    public int executor(IDatasProcessor dr) {
        try {
            List<String> list = new LinkedList<String>();

            StringBuffer sb= new StringBuffer("");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("/Users/haidongwang/workspace/workspaceg/wsng_blog/log/sys.log"),"utf-8"));
            String linestr;//按行读取 将每次读取一行的结果赋值给linestr
            while ((linestr = br.readLine()) != null) {

                if(list.size()>=100000){
                    generatorDBList(list);
                    list.clear();
                }
                //清空list
                list.add(linestr);

            }
            generatorDBList(list);
            br.close();//关闭IO

        } catch (Exception e) {
            System.out.println("文件操作失败");
            e.printStackTrace();
        }
        return 1;
    }
}

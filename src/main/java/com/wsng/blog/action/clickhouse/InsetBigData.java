package com.wsng.blog.action.clickhouse;

import com.wsng.blog.core.plugin.CoreService;
import com.wsng.blog.core.plugin.IDatasProcessor;
import com.wsng.blog.entity.BigData4CKTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Author Sean
 * @Date: 2021/4/20 15:03
 * @Version 0.01
 */
@Component
public class InsetBigData implements CoreService {

    private final static Logger logger = LoggerFactory
            .getLogger(InsetBigData.class);



    //提供异步支持
    @Autowired
    private AsyncClickDataIns asyncClickDataIns;



    @Override
    public int executor(IDatasProcessor dr) {

        List<BigData4CKTest> list = new ArrayList<BigData4CKTest>();
        long t1 = System.currentTimeMillis();
        Random random = new Random();
        Date date = new Date();

        for (int i=9000000;i<10000000;i++){
            BigData4CKTest bigData4CKTest = new BigData4CKTest();
            bigData4CKTest.setMid(i);
            bigData4CKTest.setParentId(8);
            bigData4CKTest.setZid(8);
            bigData4CKTest.setQid((i-5000000)/100);
            bigData4CKTest.setJid((i-5000000)/10);
            bigData4CKTest.setTransTime(random.nextInt(1000000));
            bigData4CKTest.setTransDate(date);
            bigData4CKTest.setTransName("交易测试"+i);
            bigData4CKTest.setTransSeq("SEQ"+i);
            list.add(bigData4CKTest);
        }
        long t2 = System.currentTimeMillis();
        logger.info("for循环生成list耗时：{} ms",(t2-t1));
//        List<List<BigData4CKTest>> lists = splitList(list, 5000);
//        CountDownLatch countDownLatch = new CountDownLatch(lists.size());
//        for (List<BigData4CKTest> listSub:lists) {
//            asyncClickDataIns.executeAsync1(listSub,countDownLatch);
//        }
//        try {
//            countDownLatch.await(); //保证之前的所有的线程都执行完成，才会走下面的；
//            // 这样就可以在下面拿到所有线程执行完的集合结果
//        } catch (Exception e) {
//            logger.error("阻塞异常:"+e.getMessage());
//        }
        long t3 = System.currentTimeMillis();
        logger.info("交易完成耗时：{} ms",(t3-t1));

        return 0;
    }

}

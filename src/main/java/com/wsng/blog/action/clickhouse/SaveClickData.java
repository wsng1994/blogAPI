package com.wsng.blog.action.clickhouse;

import com.wsng.blog.core.plugin.CoreService;
import com.wsng.blog.config.annotation.LogPoint;
import com.wsng.blog.core.plugin.IDatasProcessor;
import com.wsng.blog.core.util.ResListUtil;
import com.wsng.blog.entity.CKbatchData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 *  @Author Sean
 *  @Date: 2021/4/13 19:24
 *  @Version 0.01
 *
 */

@Component
public class SaveClickData implements CoreService {

    private final static Logger logger = LoggerFactory
            .getLogger(SaveClickData.class);

    @Autowired
    private AsyncClickDataIns asyncClickDataIns;

    @LogPoint
    @Override
    public int executor(IDatasProcessor dr) {
        List<CKbatchData> list = new ArrayList<CKbatchData>();
        long t1 = System.currentTimeMillis();

        for (int i=0;i<10000;i++){
            CKbatchData cKbatchData = new CKbatchData();
            cKbatchData.setPid(i);
            cKbatchData.setDisallowComment("test-中文数据");
            cKbatchData.setType(i);
            list.add(cKbatchData);
        }

        List<List<CKbatchData>> lists =
                (List<List<CKbatchData>>)ResListUtil.splitList(list, 5000);
        CountDownLatch countDownLatch = new CountDownLatch(lists.size());
        for (List<CKbatchData> listSub:lists) {
            asyncClickDataIns.executeAsync(listSub,countDownLatch);
        }
        try {
            countDownLatch.await(); //保证之前的所有的线程都执行完成，才会走下面的；
            // 这样就可以在下面拿到所有线程执行完的集合结果
        } catch (Exception e) {
            logger.error("阻塞异常:"+e.getMessage());
        }
        long t2 = System.currentTimeMillis();
        logger.info("for循环生成list耗时：{} ms",(t2-t1));
        return 0;
    }


}

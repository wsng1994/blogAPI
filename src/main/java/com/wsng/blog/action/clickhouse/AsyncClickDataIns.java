package com.wsng.blog.action.clickhouse;/**
 * @Author Cooper
 * @Date: 2021/4/13 20:56
 * @Version 0.01
 */

import com.wsng.blog.config.datasource.DataSourceKey;
import com.wsng.blog.config.annotation.TargetDataSource;
import com.wsng.blog.config.annotation.LogPoint;
import com.wsng.blog.core.plugin.IRouterDaoCK;
import com.wsng.blog.entity.BigData4CKTest;
import com.wsng.blog.entity.CKbatchData;
import com.wsng.blog.entity.MyLogPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 *  @Author Sean
 *  @Date: 2021/4/13 20:56
 *  @Version 0.01
 *
 */
@Component
public class AsyncClickDataIns {
    @Autowired
    private IRouterDaoCK iRouterDaoCK;

    private final static Logger logger = LoggerFactory
            .getLogger(SaveClickData.class);


    @TargetDataSource(dataSourceKey = DataSourceKey.DB_CK)
    @LogPoint
    @Async("asyncServiceExecutor")
    public void executeAsync(List<CKbatchData> logOutputResults, CountDownLatch countDownLatch) {
        try{
            //异步线程要做的事情
            iRouterDaoCK.saveLogData("",logOutputResults);
        }finally {
            countDownLatch.countDown();// 很关键, 无论上面程序是否异常必须执行countDown,否则await无法释放
        }
    }


    @TargetDataSource(dataSourceKey = DataSourceKey.DB_CK)
    @LogPoint
    @Async("asyncServiceExecutor")
    public void executeAsync1(List<BigData4CKTest> logOutputResults, CountDownLatch countDownLatch) {
        try{
            //异步线程要做的事情
            iRouterDaoCK.saveBigData(logOutputResults);
        }finally {
            countDownLatch.countDown();// 很关键, 无论上面程序是否异常必须执行countDown,否则await无法释放
        }
    }

    @TargetDataSource(dataSourceKey = DataSourceKey.DB_CK)
    @Async("asyncServiceExecutor")
    public void executeAsync2(List<MyLogPoint> logOutputResults, CountDownLatch countDownLatch) {
        try{
            //异步线程要做的事情
            iRouterDaoCK.synLogData(logOutputResults);
        }finally {
            countDownLatch.countDown();// 很关键, 无论上面程序是否异常必须执行countDown,否则await无法释放
        }
    }

}

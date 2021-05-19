package com.ss;/**
 * @Author Cooper
 * @Date: 2021/4/13 15:18
 * @Version 0.01
 */

import jdk.nashorn.internal.runtime.regexp.joni.Option;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  @Author Sean
 *  @Date: 2021/4/13 15:18
 *  @Version 0.01
 *
 */
//高并发测试类 待优化
public class CurrentChatTest {

    //定义CyclicBarrier 的屏障，需要等多少个线程到了才发起请求
    CyclicBarrier cyclicBarrier = new CyclicBarrier(200);

    private void runThread() {
        //定义线程池
        ExecutorService executorService = Executors.newFixedThreadPool(400);
        //执行线程
        for (int i = 0; i < 400; i++) {
            executorService.submit(buildThread(i));
        }
    }

    private Thread buildThread(int i) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Thread：" + Thread.currentThread().getName() + "准备...");
                    //cyclicBarrier一定要等到满200个线程到了才往后执行
                    cyclicBarrier.await();
                    String str = httpRequest("http://localhost:8081/blog/wsng/queryBriefs.do"
                    ,"POST","id=3");
                    System.out.println("Thread：" + Thread.currentThread().getName() + "开始执行");
                    //do something

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setName("name:" + i);
        return thread;
    }

    public static void main(String[] args) {

        CurrentChatTest currentChatTest = new CurrentChatTest();
        currentChatTest.runThread();
    }


    //处理http请求  requestUrl为请求地址  requestMethod请求方式，值为"GET"或"POST"
    public static String httpRequest(String requestUrl,String requestMethod,String outputStr){
        StringBuffer buffer=null;
        try{
            URL url=new URL(requestUrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod(requestMethod);
            conn.connect();
            //往服务器端写内容 也就是发起http请求需要带的参数
            if(null!=outputStr){
                OutputStream os=conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }

            //读取服务器端返回的内容
            InputStream is=conn.getInputStream();
            InputStreamReader isr=new InputStreamReader(is,"utf-8");
            BufferedReader br=new BufferedReader(isr);
            buffer=new StringBuffer();
            String line=null;
            while((line=br.readLine())!=null){
                buffer.append(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return buffer.toString();
    }
}

package com.wsng.blog.entity;

import java.util.Date;

/**
 * @Author Sean
 * @Date: 2021/4/25 10:33
 * @Version 0.01
 */
public class MyLogPoint {

    //服务开始时间 ms
    private Long strMilsTime;
    //格式化当前时间
    private String startTime;
    //当前节点序号（交易开始0，交易结束2，交易中1）
    private String nodeName;
    //服务结束时间 ms
    private Long endMilsTime;
    //格式化结束时间
    private String endTime;
    //原子服务名称
    private String clazz;
    //交易流水号
    private String transSeq;
    //执行方法
    private String method;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Long getStrMilsTime() {
        return strMilsTime;
    }

    public void setStrMilsTime(Long strMilsTime) {
        this.strMilsTime = strMilsTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Long getEndMilsTime() {
        return endMilsTime;
    }

    public void setEndMilsTime(Long endMilsTime) {
        this.endMilsTime = endMilsTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTransSeq() {
        return transSeq;
    }

    public void setTransSeq(String transSeq) {
        this.transSeq = transSeq;
    }
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}

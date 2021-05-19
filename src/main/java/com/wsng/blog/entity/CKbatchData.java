package com.wsng.blog.entity;/**
 * @Author Cooper
 * @Date: 2021/4/13 18:05
 * @Version 0.01
 */

import java.util.Date;

/**
 *  @Author Sean
 *  @Date: 2021/4/13 18:05
 *  @Version 0.01
 *
 */
public class CKbatchData {

    @Override
    public String toString() {
        return "CKbatchData{" +
                "pid=" + pid +
                ", createTime=" + createTime +
                ", type=" + type +
                ", disallowComment='" + disallowComment + '\'' +
                ", editTime=" + editTime +
                '}';
    }

    private long pid;
    private Date createTime;

    private int type;

    private  String disallowComment;

    private Date editTime;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDisallowComment() {
        return disallowComment;
    }

    public void setDisallowComment(String disallowComment) {
        this.disallowComment = disallowComment;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}

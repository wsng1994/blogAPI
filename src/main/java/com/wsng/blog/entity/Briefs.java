package com.wsng.blog.entity;

public class Briefs {

    public Briefs() {
    }

    /**
     *文章id
     */
    private String id;

    /**
     *文章封面图片路径
     */
    private String url;


    /**
     *文章标题
     */
    private String title;


    /**
     *文章介绍
     */
    private String dsc;


    /**
     *发布时间
     */
    private String time;

    /**
     *是否有评论
     */
    private String remark;

    /**
     *备用字段
     */
    private String bkp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBkp() {
        return bkp;
    }

    public void setBkp(String bkp) {
        this.bkp = bkp;
    }

    @Override
    public String toString() {
        return "Briefs{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", dsc='" + dsc + '\'' +
                ", time='" + time + '\'' +
                ", remark='" + remark + '\'' +
                ", bkp='" + bkp + '\'' +
                '}';
    }
}

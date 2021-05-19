package com.wsng.blog.core.util;

import com.github.pagehelper.Page;
import com.wsng.blog.core.plugin.IDatasProcessor;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author Cooper
 * @Date: 2021年02月20日 11:46
 * @Version 0.01
 */
public class PageUtil {

    private static final long serialVersionUID = 1L;
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //总记录数
    private long total;
    //总页数
    private int pages;
    //结果集
    private List<Object> list;
    //是否为第一页
    private boolean isFirstPage = false;
    //是否为最后一页
    private boolean isLastPage = false;


    public PageUtil() {
    }

    /**
     * 包装Page对象
     *
     * @param list
     */
    public PageUtil(List<Object> list) {
        if (list instanceof Page) {
            Page page = (Page) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();

            this.pages = page.getPages();
            this.list = page;
            this.total = page.getTotal();
        } else if (list instanceof Collection) {
            this.pageNum = 1;
            this.pageSize = list.size();

            this.pages = 1;
            this.list = list;
            this.total = list.size();
        }
        if (list instanceof Collection) {
            //判断页面边界
            judgePageBoudary();
        }
    }

    /**
     * 判定页面边界
     */
    private void judgePageBoudary() {
        isFirstPage = pageNum == 1;
        isLastPage = pageNum == pages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    /**
     * 处理分页数据
     * @return
     */

//    public static Integer pageHandle(Integer pageNum,Integer pageSize){
//        Integer offSet = (pageNum-1)*pageSize;
//
//        return offSet;
//    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("pageNum=").append(pageNum);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", total=").append(total);
        sb.append(", pages=").append(pages);
        sb.append(", list=").append(list);
        sb.append(", isFirstPage=").append(isFirstPage);
        sb.append(", isLastPage=").append(isLastPage);
        sb.append(", navigatepageNums=");
        sb.append('}');
        return sb.toString();
    }

    public static IDatasProcessor pageHandle(IDatasProcessor dr){

        Integer pageNum = (Integer)dr.getParam("pageNum")==null?0:(Integer)dr.getParam("pageNum");

        Integer pageSize = (Integer)dr.getParam("pageSize")==null?100:(Integer)dr.getParam("pageSize");

        Integer n = (Integer)dr.getParam("total");

        int pages = n%pageSize==0?n/pageSize:n/pageSize+1;

        Integer offSet = (pageNum-1)*pageSize;

        dr.setParam("pages",pages);

        dr.setParam("offSet",offSet);

        return dr;
    }

}

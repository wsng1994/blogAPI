package com.wsng.blog.core.plugin;

import com.wsng.blog.entity.Articles;
import com.wsng.blog.entity.Briefs;
import com.wsng.blog.entity.Comments;
import com.wsng.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @auther wsng
 * @date 2019/12/29.
 */
@Mapper
public interface IRouterDao {
    List<User> queryUsers(String var1, Object var2);
    Integer getCounts(String var1, Object var2);
    Map<String,Object> getUsers(String var1, String var2);
    List<Articles> queryBriefs(String var1, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
    Integer getTotals(String var1);
    Map<String,Object> getArticleById(String var1, @Param("id") String var2);
    List<Map> queryArchives(String var1, Object var2);
    List<Map> queryLogbooks(String var1, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
    Integer getTotalLogs(String var1);
    List<Map> getCommentsData(String var1, @Param("id") String var2);
    Boolean setComments(String var1, @Param("cmt")Comments var2);
    Integer getCommentsCount(String var1, @Param("id") String var2);


}

package com.wsng.blog.core.dao;


import com.wsng.blog.core.plugin.IRouterDao;
import com.wsng.blog.entity.Articles;
import com.wsng.blog.entity.Comments;
import com.wsng.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

//import java.util.List;
import java.util.List;
import java.util.Map;

/**
 * @auther wsng
 * @date 2019/12/29.
 */
//@Repository
public class RouterDao extends SqlSessionDaoSupport implements IRouterDao {

    public RouterDao() {
    }

    @Override
    public List<User> queryUsers(String var1, Object var2) {

        return getSqlSession().selectList(var1);
    }

    @Override
    public Integer getCounts(String var1, Object var2) {
        return getSqlSession().selectOne(var1,var2);
    }

    @Override
    public Map<String, Object> getUsers(String var1, String var2) {
        return getSqlSession().selectMap(var1,var2);
    }

    @Override
    public List<Articles> queryBriefs(String var1, Integer pageNum, Integer pageSize) {
        System.out.println(var1);
        return getSqlSession().selectList(var1);
    }

    @Override
    public Integer getTotals(String var1) {
        System.out.println("9999999");
        return null;
    }

    @Override
    public Map<String, Object> getArticleById(String var1, String var2) {
        return null;
    }

    @Override
    public List<Map> queryArchives(String var1, Object var2) {
        return null;
    }

    @Override
    public List<Map> queryLogbooks(String var1, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public Integer getTotalLogs(String var1) {
        return null;
    }

    @Override
    public List<Map> getCommentsData(String var1, String var2) {
        return null;
    }

    @Override
    public Boolean setComments(String var1, Comments var2) {
        return null;
    }

    @Override
    public Integer getCommentsCount(String var1, String var2) {
        return null;
    }

}

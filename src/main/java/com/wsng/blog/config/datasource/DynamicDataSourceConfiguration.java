package com.wsng.blog.config.datasource;/**
 * @Author Cooper
 * @Date: 2021/4/11 19:01
 * @Version 0.01
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.wsng.blog.config.base.ChParam;
import com.wsng.blog.config.base.YmlConfigFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import ru.yandex.clickhouse.BalancedClickhouseDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *  @Author Sean
 *  @Date: 2021/4/11 19:01
 *  @Version 0.01
 *
 */
@Configuration
@PropertySource(value = {"classpath:application.yml"},factory = YmlConfigFactory.class)
@MapperScan(basePackages = "com.wsng.blog.core.dao")
public class DynamicDataSourceConfiguration {

    @Autowired
    private ChParam chParam;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String pwd;

    @Bean()
    public DataSource dataSourceCk() {
        Properties properties = new Properties();
        properties.getProperty("user",username);
        properties.getProperty("password",pwd);

        DataSource dataSource = new BalancedClickhouseDataSource(chParam.getUrl(),properties);
        return dataSource;
    }

    @Bean()
    public DataSource dataSourceBlog() {
        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setUrl(url);
        dataSource1.setUsername(username);//?????????
        dataSource1.setPassword(pwd);//??????
        dataSource1.setDriverClassName(driver);
        dataSource1.setInitialSize(2);//???????????????????????????????????????
        dataSource1.setMaxActive(20);//?????????????????????
        dataSource1.setMinIdle(0);//?????????????????????
        dataSource1.setMaxWait(60000);//???????????????????????????????????????????????????
        dataSource1.setValidationQuery("SELECT 1");//?????????????????????????????????sql
        dataSource1.setTestOnBorrow(false);//?????????????????????validationQuery????????????????????????
        dataSource1.setTestWhileIdle(true);//???????????????true?????????????????????????????????????????????
        dataSource1.setPoolPreparedStatements(false);//????????????preparedStatement????????????PSCache
        return dataSource1;
    }

    /**
     * ?????????????????????
     *
     * @return ???????????????
     */
    @Bean
    @Primary
    public DataSource dynamicDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setDefaultTargetDataSource(dataSourceBlog());
        dataSourceMap = new HashMap<>(4);
        dataSourceMap.put(DataSourceKey.DB_CK,dataSourceCk());
        dataSourceMap.put(DataSourceKey.DB_BLOG, dataSourceBlog());
        dataSource.setTargetDataSources(dataSourceMap);
        return dataSource;
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        //?????????????????????????????????mapper???????????????
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    /**
     * ????????????
     *
     * @return ??????????????????
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }





}

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
        dataSource1.setUsername(username);//用户名
        dataSource1.setPassword(pwd);//密码
        dataSource1.setDriverClassName(driver);
        dataSource1.setInitialSize(2);//初始化时建立物理连接的个数
        dataSource1.setMaxActive(20);//最大连接池数量
        dataSource1.setMinIdle(0);//最小连接池数量
        dataSource1.setMaxWait(60000);//获取连接时最大等待时间，单位毫秒。
        dataSource1.setValidationQuery("SELECT 1");//用来检测连接是否有效的sql
        dataSource1.setTestOnBorrow(false);//申请连接时执行validationQuery检测连接是否有效
        dataSource1.setTestWhileIdle(true);//建议配置为true，不影响性能，并且保证安全性。
        dataSource1.setPoolPreparedStatements(false);//是否缓存preparedStatement，也就是PSCache
        return dataSource1;
    }

    /**
     * 核心动态数据源
     *
     * @return 数据源实例
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
        //此处设置为了解决找不到mapper文件的问题
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    /**
     * 事务管理
     *
     * @return 事务管理实例
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }





}

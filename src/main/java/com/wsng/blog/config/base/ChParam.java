package com.wsng.blog.config.base;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ChParam {
    private String driverClassName;
    private String url;
    private String password;
    private Integer initialSize;
    private Integer maxActive;
    private Integer minIdle;
    private Integer maxWait;
    private String user;

    @Value("${clickhouse.driverClassName}")
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    @Value("${clickhouse.user}")
    public void setUser(String user) {
        this.user = user;
    }


    @Value("${clickhouse.url}")
    public void setUrl(String url) {
        this.url = url;
    }

    @Value("${clickhouse.password}")
    public void setPassword(String password) {
        this.password = password;
    }

    @Value("${clickhouse.initialSize}")
    public void setInitialSize(Integer initialSize) {
        this.initialSize = initialSize;
    }

    @Value("${clickhouse.maxActive}")
    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    @Value("${clickhouse.minIdle}")
    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    @Value("${clickhouse.maxWait}")
    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }
}


package com.wsng.blog;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication(scanBasePackages = {"com.wsng.blog.**"},exclude = {
        DataSourceAutoConfiguration.class
})
@ServletComponentScan
public class WsngBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(WsngBlogApplication.class, args);
    }

}

package com.wsng.blog.config.extension;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.wsng.blog.config.logpoint.LogPointAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Sean
 * @Date: 2021/4/25 11:05
 * @Version 0.01
 */
@Configuration
public class LogMessageConverter extends MessageConverter {

    private static final Logger logger = LoggerFactory
            .getLogger(LogPointAspect.class);

    //重写logger日志输出格式
    @Override
    public String convert(ILoggingEvent event) {

        return super.convert(event);
    }

}

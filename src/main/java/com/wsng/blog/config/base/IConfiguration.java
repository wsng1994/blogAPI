/**
 * Copyright (c) 2019 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 * 
 * <p>项目名称	:ares-api-gateway</p>
 * <p>包名称    	:cn.com.yitong.ares.gateway.configuration</p>
 * <p>文件名称	:GatewayConfiguration.java</p>
 * <p>创建时间	:2019-3-22 18:51:58 </p>
 */
package com.wsng.blog.config.base;

import com.wsng.blog.channel.JsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *
 * @author wsng
 */
@Configuration
//@ImportResource(locations = { "classpath:spring/application-context.xml" })
public class IConfiguration {


	/**
	 * 新增 HTTP message 转换器
	 *
	 * @return the http message converters
	 */
	@Bean
	public HttpMessageConverters addHttpMessageConverter() {
		JsonHttpMessageConverter converter = new JsonHttpMessageConverter();
		return new HttpMessageConverters(converter);
	}
	

}

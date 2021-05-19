/**
 * Copyright (c) 2018 Shanghai P&C Information Technology Co.,Ltd. All rights reserved.
 * 
 * 系统名称：ares-web-core
 * 模块名称：cn.com.yitong.ares.channel
 * @version 1.0.0
 * @author zwb
 * @date 2018-7-5 19:51:55
 */
package com.wsng.blog.channel;

import com.alibaba.fastjson.JSON;
import com.wsng.blog.core.base.ConstDefined;
import com.wsng.blog.core.base.DatasProcessor;
import com.wsng.blog.core.util.SEQUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Map;

/**
 *
 *
 */
public class JsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

	/**
	 * 记录日志工具.
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());


	/**
	 * The Constant UTF8.
	 */
	public final static Charset UTF8 = Charset.forName("UTF-8");

	/**
	 * The charset.
	 */
	private Charset charset = UTF8;


	/**
	 * 请求参数前缀
	 */
	private final static String REQ_PREFIX = "{";
	/** 国密算法前缀标识 */
	private final static String REQ_PREFIX_SM = "#";

	/**
	 * Instantiates a new json http message converter.
	 */
	public JsonHttpMessageConverter() {
		super(new MediaType("application", "json", UTF8), new MediaType("application", "*+json", UTF8));
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see AbstractHttpMessageConverter#supports(
	 *      Class)
	 */
	@Override
	protected boolean supports(Class<?> clazz) {
		return true;
	}

	/**
	 * Gets the charset.
	 *
	 * @return the charset
	 */
	public Charset getCharset() {
		return this.charset;
	}

	/**
	 * Sets the charset.
	 *
	 * @param charset
	 *            the new charset
	 */
	public void setCharset(Charset charset) {
		this.charset = charset;
	}





	/**
	 * 处理前端请求报文
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream in = inputMessage.getBody();
		DatasProcessor datasProcessor = null;
		String reqStr = null;
		try {
			// 读取请求内容
			byte[] buf = new byte[1024];
			for (;;) {
				int len = in.read(buf);
				if (len == -1) {
					break;
				}
				if (len > 0) {
					baos.write(buf, 0, len);
				}
			}

			byte[] bytes = baos.toByteArray();
			reqStr = new String(bytes, charset);

			logger.info("前端请求报文 ==>\n{}", reqStr);

			// 总线初始化
			datasProcessor = new DatasProcessor();

			datasProcessor.getParamMap().putAll(JSON.parseObject(reqStr, Map.class));
			datasProcessor.setParam(ConstDefined.TRANS_SEQ, SEQUtil.seqGenerator(0));
			MDC.put("seq",SEQUtil.seqGenerator(0));

		} catch (Exception e) {
			logger.error("处理前端请求报文异常:", e);
			datasProcessor = null;
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(baos);
		}

		return datasProcessor;
	}

	/**
	 * 处理请求报文
	 * 
	 * @param ctx
	 * @param reqStr
	 * @return
	 * @throws Exception
	 */




	/**
	 * 处理前端响应报文
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

		OutputStream out = outputMessage.getBody();
		try {
			String text = null;
			Map resultMap=(Map)obj;
			

			text = JSON.toJSONString(resultMap);

			byte[] bytes = text.getBytes(charset);
			out.write(bytes);

			
//			logger.info("<== 前端响应报文\n{}", text);
		} catch (Exception e) {
//			logger.error("响应前端报文异常:", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}



}
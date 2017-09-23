package com.jiudianlianxian.cache;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;


/**
 * 
 * @Title: CacheResponseWrapper
 * @Description: 给此类一个描述
 * @Company: 济宁九点连线信息技术有限公司
 * @ProjectName: filter
 * @author fupengpeng
 * @date 2017年9月23日 下午4:15:32
 */
public class CacheResponseWrapper extends HttpServletResponseWrapper {

	// 缓存字符类输出
	private CharArrayWriter cacheWriter = new CharArrayWriter();

	public CacheResponseWrapper(HttpServletResponse response)
			throws IOException {
		super(response);
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(cacheWriter);
	}

	@Override
	public void flushBuffer() throws IOException {
		cacheWriter.flush();
	}

	public void finishResponse() throws IOException {
		cacheWriter.close();
	}

	public CharArrayWriter getCacheWriter() {
		return cacheWriter;
	}

	public void setCacheWriter(CharArrayWriter cacheWriter) {
		this.cacheWriter = cacheWriter;
	}
}

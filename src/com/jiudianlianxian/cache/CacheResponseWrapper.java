package com.jiudianlianxian.cache;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;


/**
 * 
 * @Title: CacheResponseWrapper
 * @Description: ������һ������
 * @Company: �����ŵ�������Ϣ�������޹�˾
 * @ProjectName: filter
 * @author fupengpeng
 * @date 2017��9��23�� ����4:15:32
 */
public class CacheResponseWrapper extends HttpServletResponseWrapper {

	// �����ַ������
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

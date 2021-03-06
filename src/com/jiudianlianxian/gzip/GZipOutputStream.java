package com.jiudianlianxian.gzip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @Title: GZipOutputStream
 * @Description: 给此类一个描述
 * @Company: 济宁九点连线信息技术有限公司
 * @ProjectName: filter
 * @author fupengpeng
 * @date 2017年9月23日 下午3:27:45
 */
public class GZipOutputStream extends ServletOutputStream {

	private HttpServletResponse response;

	// JDK 自带的压缩数据的类
	private GZIPOutputStream gzipOutputStream;

	// 将压缩后的数据存放到 ByteArrayOutputStream 对象中
	private ByteArrayOutputStream byteArrayOutputStream;

	public GZipOutputStream(HttpServletResponse response) throws IOException {
		this.response = response;
		byteArrayOutputStream = new ByteArrayOutputStream();
		gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
	}

	public void write(int b) throws IOException {
		gzipOutputStream.write(b);
	}

	public void close() throws IOException {

		// 压缩完毕 一定要调用该方法
		gzipOutputStream.finish();

		// 将压缩后的数据输出到客户端
		byte[] content = byteArrayOutputStream.toByteArray();

		// 设定压缩方式为 GZIP, 客户端浏览器会自动将数据解压
		response.addHeader("Content-Encoding", "gzip");
		response.addHeader("Content-Length", Integer.toString(content.length));

		// 输出
		ServletOutputStream out = response.getOutputStream();
		out.write(content);
		out.close();
	}

	public void flush() throws IOException {
		gzipOutputStream.flush();
	}

	public void write(byte[] b, int off, int len) throws IOException {
		gzipOutputStream.write(b, off, len);
	}

	public void write(byte[] b) throws IOException {
		gzipOutputStream.write(b);
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setWriteListener(WriteListener arg0) {
		// TODO Auto-generated method stub
		
	}
}

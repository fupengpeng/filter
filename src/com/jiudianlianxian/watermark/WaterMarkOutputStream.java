package com.jiudianlianxian.watermark;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;


/**
 * 
 * @Title: WaterMarkOutputStream
 * @Description: 给此类一个描述
 * @Company: 济宁九点连线信息技术有限公司
 * @ProjectName: filter
 * @author fupengpeng
 * @date 2017年9月23日 下午3:55:28
 */
public class WaterMarkOutputStream extends ServletOutputStream {

	// 缓冲图片数据
	private ByteArrayOutputStream byteArrayOutputStream;

	public WaterMarkOutputStream() throws IOException {
		byteArrayOutputStream = new ByteArrayOutputStream();
	}

	public void write(int b) throws IOException {
		byteArrayOutputStream.write(b);
	}

	public void close() throws IOException {
		byteArrayOutputStream.close();
	}

	public void flush() throws IOException {
		byteArrayOutputStream.flush();
	}

	public void write(byte[] b, int off, int len) throws IOException {
		byteArrayOutputStream.write(b, off, len);
	}

	public void write(byte[] b) throws IOException {
		byteArrayOutputStream.write(b);
	}

	public ByteArrayOutputStream getByteArrayOutputStream() {
		return byteArrayOutputStream;
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

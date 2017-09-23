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
 * @Description: ������һ������
 * @Company: �����ŵ�������Ϣ�������޹�˾
 * @ProjectName: filter
 * @author fupengpeng
 * @date 2017��9��23�� ����3:27:45
 */
public class GZipOutputStream extends ServletOutputStream {

	private HttpServletResponse response;

	// JDK �Դ���ѹ�����ݵ���
	private GZIPOutputStream gzipOutputStream;

	// ��ѹ��������ݴ�ŵ� ByteArrayOutputStream ������
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

		// ѹ����� һ��Ҫ���ø÷���
		gzipOutputStream.finish();

		// ��ѹ���������������ͻ���
		byte[] content = byteArrayOutputStream.toByteArray();

		// �趨ѹ����ʽΪ GZIP, �ͻ�����������Զ������ݽ�ѹ
		response.addHeader("Content-Encoding", "gzip");
		response.addHeader("Content-Length", Integer.toString(content.length));

		// ���
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

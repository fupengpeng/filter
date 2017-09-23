package com.jiudianlianxian.watermark;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.jiudianlianxian.util.ImageUtil;

/**
 * 
 * @Title: WaterMarkResponseWrapper
 * @Description: ������һ������
 * @Company: �����ŵ�������Ϣ�������޹�˾
 * @ProjectName: filter
 * @author fupengpeng
 * @date 2017��9��23�� ����3:48:35
 */
public class WaterMarkResponseWrapper extends HttpServletResponseWrapper {

	// ˮӡͼƬλ��
	private String waterMarkFile;

	// ԭresponse
	private HttpServletResponse response;

	// �Զ���servletOutputStream�����ڻ���ͼ������
	private WaterMarkOutputStream waterMarkOutputStream;

	public WaterMarkResponseWrapper(HttpServletResponse response,
			String waterMarkFile) throws IOException {
		super(response);
		this.response = response;
		this.waterMarkFile = waterMarkFile;
		this.waterMarkOutputStream = new WaterMarkOutputStream();
	}

	// ����getOutputStream()�������Զ����waterMarkOutputStream
	public ServletOutputStream getOutputStream() throws IOException {
		return waterMarkOutputStream;
	}

	public void flushBuffer() throws IOException {
		waterMarkOutputStream.flush();
	}

	// ��ͼ�����ݴ�ˮӡ����������ͻ��������
	public void finishResponse() throws IOException {

		// ԭͼƬ����
		byte[] imageData = waterMarkOutputStream.getByteArrayOutputStream()
				.toByteArray();

		// ��ˮӡ���ͼƬ����
		byte[] image = ImageUtil.waterMark(imageData, waterMarkFile);

		// ��ͼ������������
		response.setContentLength(image.length);
		response.getOutputStream().write(image);

		waterMarkOutputStream.close();
	}
}

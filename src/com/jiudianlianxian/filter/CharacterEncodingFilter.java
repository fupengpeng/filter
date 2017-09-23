package com.jiudianlianxian.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * 
 * @Title: CharacterEncodingFilter
 * @Description: �ύ���ݱ����������
 * @Company: �����ŵ�������Ϣ�������޹�˾
 * @ProjectName: filter
 * @author fupengpeng
 * @date 2017��9��23�� ����9:30:20
 */
public class CharacterEncodingFilter implements Filter {

	private String characterEncoding;  //�����ʽ
	private boolean enabled;    //�Ƿ����ø�filter

	@Override
	public void init(FilterConfig config) throws ServletException {

		//��ʼ��������ȡ
		characterEncoding = config.getInitParameter("characterEncoding");

		//���ô�filter
		enabled = "true".equalsIgnoreCase(characterEncoding.trim())
				|| "1".equalsIgnoreCase(characterEncoding.trim());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (enabled || characterEncoding != null) {
			request.setCharacterEncoding(characterEncoding);    //����request�ı����ʽ
			response.setCharacterEncoding(characterEncoding);    //����response�ı����ʽ
		}

		//ִ����һ��filter����Servlet
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		characterEncoding = null;    //�����Դ
	}
}

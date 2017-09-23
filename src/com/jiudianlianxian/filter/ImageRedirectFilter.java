package com.jiudianlianxian.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @Title: ImageRedirectFilter
 * @Description: ������filter
 * @Company: �����ŵ�������Ϣ�������޹�˾
 * @ProjectName: filter
 * @author fupengpeng
 * @date 2017��9��23�� ����9:10:24
 */
public class ImageRedirectFilter implements Filter {

	//��ʼ��
	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// ��ֹ����
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragrma", "no-cache");
		response.setDateHeader("Expires", 0);

		// ������Դ��ַ
		String referer = request.getHeader("referer");

		if (referer == null || !referer.contains(request.getServerName())) {

			/**
			 * ��� ���ӵ�ַ����������վ���򷵻ش���ͼƬ
			 */
			request.getRequestDispatcher("/error.gif").forward(request,
					response);

		} else {

			/**
			 * ͼƬ������ʾ
			 */
			chain.doFilter(request, response);
		}

	}

	public void destroy() {
	}
}

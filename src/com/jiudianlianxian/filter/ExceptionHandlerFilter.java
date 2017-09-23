package com.jiudianlianxian.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.jiudianlianxian.exception.AccountException;
import com.jiudianlianxian.exception.BusinessException;

/**
 * 
 * @Title: ExceptionHandlerFilter
 * @Description: ������һ������
 * @Company: �����ŵ�������Ϣ�������޹�˾
 * @ProjectName: filter
 * @author fupengpeng
 * @date 2017��9��23�� ����11:12:30
 */
public class ExceptionHandlerFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		try {
			chain.doFilter(request, response);
		} catch (Exception e) {

			Throwable rootCause = e;

			while (rootCause.getCause() != null) {
				rootCause = rootCause.getCause();
			}

			String message = rootCause.getMessage();

			message = message == null ? "�쳣��" + rootCause.getClass().getName()
					: message;

			request.setAttribute("message", message);
			request.setAttribute("e", e);

			if (rootCause instanceof AccountException) {
				request.getRequestDispatcher("/accountException.jsp").forward(
						request, response);
			} else if (rootCause instanceof BusinessException) {
				request.getRequestDispatcher("/businessException.jsp").forward(
						request, response);
			} else {
				request.getRequestDispatcher("/exception.jsp").forward(request,
						response);
			}
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
}

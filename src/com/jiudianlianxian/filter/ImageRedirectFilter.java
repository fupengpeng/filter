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
 * @Description: 防盗链filter
 * @Company: 济宁九点连线信息技术有限公司
 * @ProjectName: filter
 * @author fupengpeng
 * @date 2017年9月23日 上午9:10:24
 */
public class ImageRedirectFilter implements Filter {

	//初始化
	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// 禁止缓存
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragrma", "no-cache");
		response.setDateHeader("Expires", 0);

		// 链接来源地址
		String referer = request.getHeader("referer");

		if (referer == null || !referer.contains(request.getServerName())) {

			/**
			 * 如果 链接地址来自其他网站，则返回错误图片
			 */
			request.getRequestDispatcher("/error.gif").forward(request,
					response);

		} else {

			/**
			 * 图片正常显示
			 */
			chain.doFilter(request, response);
		}

	}

	public void destroy() {
	}
}

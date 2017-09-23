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
 * @Description: 提交数据编码乱码过滤
 * @Company: 济宁九点连线信息技术有限公司
 * @ProjectName: filter
 * @author fupengpeng
 * @date 2017年9月23日 上午9:30:20
 */
public class CharacterEncodingFilter implements Filter {

	private String characterEncoding;  //编码格式
	private boolean enabled;    //是否启用该filter

	@Override
	public void init(FilterConfig config) throws ServletException {

		//初始化参数获取
		characterEncoding = config.getInitParameter("characterEncoding");

		//启用此filter
		enabled = "true".equalsIgnoreCase(characterEncoding.trim())
				|| "1".equalsIgnoreCase(characterEncoding.trim());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (enabled || characterEncoding != null) {
			request.setCharacterEncoding(characterEncoding);    //设置request的编码格式
			response.setCharacterEncoding(characterEncoding);    //设置response的编码格式
		}

		//执行下一个filter或者Servlet
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		characterEncoding = null;    //清空资源
	}
}

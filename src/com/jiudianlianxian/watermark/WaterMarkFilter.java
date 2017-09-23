package com.jiudianlianxian.watermark;

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
 * @Title: WaterMarkFilter
 * @Description: 给此类一个描述
 * @Company: 济宁九点连线信息技术有限公司
 * @ProjectName: filter
 * @author fupengpeng
 * @date 2017年9月23日 下午4:11:19
 */
public class WaterMarkFilter implements Filter {

	// 水印图片，配置在初始化参数中
	private String waterMarkFile;

	public void init(FilterConfig config) throws ServletException {
		String file = config.getInitParameter("waterMarkFile");
		waterMarkFile = config.getServletContext().getRealPath(file);
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// 自定义的response
		WaterMarkResponseWrapper waterMarkRes = new WaterMarkResponseWrapper(
				response, waterMarkFile);

		chain.doFilter(request, waterMarkRes);

		// 打水印，输出到客户端浏览器
		waterMarkRes.finishResponse();
	}

	public void destroy() {
	}

}

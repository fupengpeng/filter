package com.jiudianlianxian.exception;


/**
 * 
 * @Title: BusinessException
 * @Description: 给此类一个描述
 * @Company: 济宁九点连线信息技术有限公司
 * @ProjectName: filter
 * @author fupengpeng
 * @date 2017年9月23日 上午11:02:30
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = -3040955562136599570L;

	public BusinessException(String msg) {
		super(msg);
	}

}

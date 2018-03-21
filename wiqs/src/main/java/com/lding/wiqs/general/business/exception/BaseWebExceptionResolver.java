package com.lding.wiqs.general.business.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class BaseWebExceptionResolver implements HandlerExceptionResolver {

	/**
	 * 异常拦截器
	 * 支持json方式或者xml方式返回
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ex.printStackTrace();
		
		ModelAndView retMav = new ModelAndView("error/error");
		BaseWebException bex = null;
		if(ex instanceof BaseWebException) {
			bex = (BaseWebException)ex;
		}else{
			bex= new BaseWebException(ex.getMessage());
		}
		
		retMav.addObject("errorMessage", bex.getMessage());
		retMav.addObject("respcode",99);
		retMav.addObject("respmesg",bex.getMessage());

		return retMav;
	}

}

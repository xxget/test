package com.lding.wiqs.general.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.lding.wiqs.utils.SessionUtils;


/**
 * 自定义认证拦截器，包含校验码
 * @author yangling
 *
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter{

	/**
	 * 认证方法，加入校验码
	 * return true 表示校验失败，失败信息通过 shiroLoginFailure 传递给前台。
	 * 成功则传给父类的 onAccessDenied
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String srvValidateCode = (String) req.getSession().getAttribute(SessionUtils.VALIDATE_CODE);
		String inputValidateCode = req.getParameter("validateCode");
		
		if(srvValidateCode!=null && inputValidateCode!=null && !inputValidateCode.equals(srvValidateCode) ) {
			req.setAttribute(SessionUtils.SHIRO_LOGIN_FAILURE, "ValidateCodeExcption");
			return true;
		}
		return super.onAccessDenied(request, response);
	}

}

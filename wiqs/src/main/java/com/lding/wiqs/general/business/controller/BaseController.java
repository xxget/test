package com.lding.wiqs.general.business.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.lding.wiqs.utils.SnowflakeIdWorker;

/**
 * 请求响应基础类
 * @author xxg
 */
public abstract class BaseController {
	protected Logger logger = Logger.getLogger(this.getClass());

	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	
	// ============= 请求响应 ========================
	@ModelAttribute
	public void setReqAndResp(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}
}

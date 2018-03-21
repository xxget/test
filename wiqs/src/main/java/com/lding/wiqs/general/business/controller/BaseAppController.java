package com.lding.wiqs.general.business.controller;

public abstract class BaseAppController extends BaseController {

	
	/* token 超时时间，单位毫秒 ,12 小时 */
	public static long TOKEN_TIMEOUT = 12*3600000;
	
	/* 超时时间，可以更新,默认 12小时 */
	public long tokenTimeout=TOKEN_TIMEOUT;

	public long getTokenTimeout() {
		return tokenTimeout;
	}
	public void setTokenTimeout(long tokenTimeout) {
		this.tokenTimeout = tokenTimeout;
	}
	/**
	 * 创建新token，如果存在，则更新
	 * @param username
	 * @param deviceId
	 * @return token
	 */
	public abstract String createToken(String username,String deviceId);
	/**
	 * 校验token合法性,如果成功，返回用户名，更新时间签
	 * 				失败，返回 null
	 * @param token
	 * @param deviceId
	 * @return username
	 */
	public abstract String checkToken(String token,String deviceId);
	
}

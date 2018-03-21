package com.lding.wiqs.utils;

import java.util.UUID;

public class SessionUtils {
	
	public static String USER = "mluser";
	public static String VALIDATE_CODE = "validateCode";
	public static String SHIRO_LOGIN_FAILURE ="shiroLoginFailure";
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}

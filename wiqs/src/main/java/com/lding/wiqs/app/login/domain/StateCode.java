package com.lding.wiqs.app.login.domain;

public class StateCode {
	/**
     * 200 OK 客户端请求成功
     */
    public static final String OK = "200";
	/**
     * 201 FAIL 客户端请求失败
     */
    public static final String FAIL="201";
    /**
     * 400 Bad Request	客户端请求错误且不能被服务器所理解
     */
    public static final String BAD_REQUEST = "400";

    /**
     * 403 Forbidden	服务器收到请求但拒绝提供服务
     */
    public static final String FORBIDDEN = "403";

    /**
     * 404 Not Found	请求资源不存在
     */
    public static final String NOT_FOUND = "404";

    public static final String UNKNOWN_ACCESS_RESULT = "405";

    /**
     * 420 not login 未登录
     */
    public static final String NOT_LOGIN = "420";

    /**
     * 500 Internal Server Error	服务器发生不可预期的错误
     */
    public static final String INTERNAL_SERVER_ERROR = "500";

    /**
     * 503 Server Unavailable	服务器当前不能处理客户端的请求
     */
    public static final String SERVER_UNAVAILABLE = "503";

    /**
     * 600 Invalid Parameters 参数不合法
     */
    public static final String INVALID_PARAMETERS = "600";

    /**
     * 601 UserName Not Exist 用户名不存在
     */
    public static final String USERNAME_NOT_EXIST = "601";

    /**
     * 602 Password Error 密码错误
     */
    public static final String PASSWORD_ERROR = "602";

    /**
     * 603 UserName Not Exist 用户名已存在
     */
    public static final String USERNAME_ALREADY_EXIST = "603";
    
    /**
     * 604 UserName And Password Not Exist 用户名或密码错误
     */
    public static final String USERNAME_AND_PASSWORD_NOT_EXIST = "604";

    /**
     * 700 Permission denied 权限不足
     */
    public static final String PERMISSION_DENIED = "700";
    
    /**
     * 安全秘钥错误
     */
    public static final String SECURITY_KEY_ERROR= "701";
    
    /**
     * 上传失败
     */
    public static final String UPLOAD_ERROR= "702";
}

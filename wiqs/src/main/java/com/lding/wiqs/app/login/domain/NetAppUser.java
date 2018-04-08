package com.lding.wiqs.app.login.domain;

import java.util.List;

import com.lding.wiqs.modular.appversion.domain.AppVersion;
import com.lding.wiqs.modular.org.domain.Org;
import com.lding.wiqs.modular.permission.domain.Permission;
import com.lding.wiqs.modular.userinfo.domain.UserInfo;

public class NetAppUser {
	/**
	 * 网络访问状态码
	 */
	private String stateCode;
	/**
	 * 用户基本信息
	 */
	private UserInfo userInfo;
	/**
	 * 组织机构信息
	 */
	private Org org;
	/**
	 * app版本信息
	 */
	private AppVersion appVersion;
	/**
	 * 用户的资源权限列表
	 */
	private List<Permission> permissionList;

	/**
	 * <p>
	 * Title: getStateCode
	 * </p>
	 * <p>
	 * Description: 获取网络访问状态码
	 * </p>
	 * 
	 * @return
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * <p>
	 * Title: setStateCode
	 * </p>
	 * <p>
	 * Description:设置网络访问状态码
	 * </p>
	 * 
	 * @param stateCode
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	/**
	 * <p>
	 * Title: getUserInfo
	 * </p>
	 * <p>
	 * Description: 获取用户基本信息
	 * </p>
	 * 
	 * @return
	 */
	public UserInfo getUserInfo() {
		return userInfo;
	}

	/**
	 * <p>
	 * Title: setUserInfo
	 * </p>
	 * <p>
	 * Description: 设置用户基本信息
	 * </p>
	 * 
	 * @param userInfo
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * <p>
	 * Title: getOrg
	 * </p>
	 * <p>
	 * Description: 获取组织机构信息
	 * </p>
	 * 
	 * @return
	 */
	public Org getOrg() {
		return org;
	}

	/**
	 * <p>
	 * Title: setOrg
	 * </p>
	 * <p>
	 * Description: 设置组织机构信息
	 * </p>
	 * 
	 * @param org
	 */
	public void setOrg(Org org) {
		this.org = org;
	}

	/**
	 * <p>Title: getPermissionList</p>  
	 * <p>Description: 获取用户的资源权限列表</p>  
	 * @return
	 */
	public List<Permission> getPermissionList() {
		return permissionList;
	}

	/**
	 * <p>Title: setPermissionList</p>  
	 * <p>Description: 设置用户的资源权限列表</p>  
	 * @param permissionList
	 */
	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	/**
	 * <p>Title: getAppVersion</p>  
	 * <p>Description: 获取app版本信息</p>  
	 * @return
	 */
	public AppVersion getAppVersion() {
		return appVersion;
	}

	/**
	 * <p>Title: setAppVersion</p>  
	 * <p>Description: 设置app版本信息</p>  
	 * @param appVersion
	 */
	public void setAppVersion(AppVersion appVersion) {
		this.appVersion = appVersion;
	}
	
}

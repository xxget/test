package com.lding.wiqs.general.web.vo;

import java.io.Serializable;
import java.util.List;

import com.lding.wiqs.modular.org.domain.Org;
import com.lding.wiqs.modular.permission.domain.Permission;

/**
 * 前台用户类，包含菜单和权限列表
 * 
 * @author yangling
 *
 */
public class UserVo implements Serializable {
	private static final long serialVersionUID = 6641951161257250706L;
	/**
	 * 登陆名
	 */
	String userCode;
	/**
	 * 用户名
	 */
	String userName;
	/**
	 * UUID
	 */
	String userId;
	/**
	 * 菜单
	 */
	List<TreeVo> menus = null;
	/**
	 * 权限
	 */
	List<Permission> permissions = null;
	/**
	 * 机构
	 */
	Org org = null;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<TreeVo> getMenus() {
		return menus;
	}

	public void setMenus(List<TreeVo> menus) {
		this.menus = menus;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

}

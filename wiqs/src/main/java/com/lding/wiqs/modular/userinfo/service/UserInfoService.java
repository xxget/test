package com.lding.wiqs.modular.userinfo.service;

import java.util.List;

import com.lding.wiqs.app.login.domain.NetAppUser;
import com.lding.wiqs.general.business.service.IService;
import com.lding.wiqs.modular.userinfo.domain.UserInfo;
import com.lding.wiqs.modular.userrole.domain.UserRole;

/**
 * 用户信息表
 * 
 * @author xxg
 *
 */
public interface UserInfoService extends IService<UserInfo> {
	/**
	 * 根据用户名查询用户信息
	 */
	UserInfo findUserByUsername(String username);

	public List<UserInfo> readByOrgid(String orgid);

	public List<UserRole> readRoleByUser(UserInfo userInfo);

	int updateUserWithRole(UserInfo userInfo, String roles);
}

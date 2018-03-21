package com.lding.wiqs.modular.role.service;

import java.util.List;

import com.lding.wiqs.general.business.service.IService;
import com.lding.wiqs.modular.permission.domain.Permission;
import com.lding.wiqs.modular.role.domain.Role;

public interface RoleService extends IService<Role> {
	/**
	 * 删除角色，同时删除用户映射和权限映射
	 * @param id
	 * @return
	 */
	int deleteWithMapByKey(String id);
	
	/**
	 * 增加角色，同时增加权限映射
	 * @param role
	 * @param permissionList
	 * @return
	 */
	int insertWithPermissionList(Role role,String permissionList);
	
	/**
	 * 更新角色，同时更新权限映射
	 * @param role
	 * @param permissionList
	 * @return
	 */
	int updateWithPermissionListByKey(Role role,String permissionList);

	/**
	 * 根据角色读取权限列表
	 * @param role
	 * @return
	 */
	public List<Permission> readPermissionByRole(Role role);

	String readPermissionStringByRole(Role role);

}

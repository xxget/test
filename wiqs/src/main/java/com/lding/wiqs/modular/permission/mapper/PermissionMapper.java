package com.lding.wiqs.modular.permission.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lding.wiqs.modular.permission.domain.Permission;

import tk.mybatis.mapper.common.Mapper;

public interface PermissionMapper extends Mapper<Permission> {
	/**
	 * 根据用户id查询菜单列表
	 * @param userid
	 * @return
	 */
	List<Permission> selectMenuListByUserID(@Param("userId") String userId);

	/**
	 * 根据用户id查询权限列表
	 * @param userid
	 * @return
	 */
	List<Permission> selectPermissionListByUserID(@Param("userId") String userId);

	/**
	 * 根据角色id查询权限列表
	 * @param roleid
	 * @return
	 */
	List<Permission> selectPermissionListByRoleID(@Param("roleId") String roleId);
}
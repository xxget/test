package com.lding.wiqs.modular.permission.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lding.wiqs.general.business.service.impl.BaseService;
import com.lding.wiqs.modular.permission.domain.Permission;
import com.lding.wiqs.modular.permission.mapper.PermissionMapper;
import com.lding.wiqs.modular.permission.service.PermissionService;

@Service("PermissionService")
public class PermissionServiceImpl extends BaseService<Permission>  implements PermissionService{

	@Autowired
	private PermissionMapper permissionMapper;
	
	/**
	 * 根据用户id查询菜单列表
	 */
	@Override
	public List<Permission> findMenuListByUserId(String id) {
		return permissionMapper.selectMenuListByUserID(id);
	}

	/**
	 * 根据用户id查询权限列表
	 */
	@Override
	public List<Permission> findPermissionListByUserId(String id) {
		return permissionMapper.selectPermissionListByUserID(id);
	}
}

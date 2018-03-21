package com.lding.wiqs.modular.permission.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lding.wiqs.general.business.service.IService;
import com.lding.wiqs.modular.permission.domain.Permission;
import com.lding.wiqs.modular.permission.mapper.PermissionMapper;

/**
 * 权限资源表
 * @author xxg
 *
 */
public interface PermissionService extends IService<Permission> {
	
	/**
	 * 根据用户id查询菜单列表
	 * @param id
	 * @return
	 */
	List<Permission> findMenuListByUserId(String id);
	
	/**
	 * 分局用户id查询权限列表
	 * @param id
	 * @return
	 */
	List<Permission> findPermissionListByUserId(String id);
	
	
	
	
}

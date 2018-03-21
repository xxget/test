package com.lding.wiqs.modular.role.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lding.wiqs.general.business.controller.BaseController;
import com.lding.wiqs.general.web.vo.ResponseVO;
import com.lding.wiqs.modular.role.domain.Role;
import com.lding.wiqs.modular.role.service.RoleService;
import com.lding.wiqs.utils.SessionUtils;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/manage")
	@RequiresPermissions("base:role:read")
	public String manage() {
		return "role/rolePage";
	}
	
	@RequestMapping("/selectByWhere")
	@ResponseBody
	@RequiresPermissions("base:role:read")
	public List<Role> selectAll(Role role,
			@RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int rows) {
		List<Role> roles = roleService.selectByEqualNotNull(role, page, rows);
		return roles;
	}

	/**
	 * @return
	 */
	@RequestMapping("/readAll")
	@ResponseBody
	public List<Role> readAll() {
		List<Role> roles = roleService.readAllRows();
		for(Role r:roles) {
			if("管理员".equals(r.getRoleName())) {
				roles.remove(r);
				break;
			}
		}
		return roles;
	}
	
	@RequestMapping(value = "/insert" , method = RequestMethod.POST )
	@ResponseBody
	@RequiresPermissions("base:role:insert")
	public ResponseVO insert(Role role,String pms) {
		ResponseVO rv = new ResponseVO(0);
		role.setRoleId(SessionUtils.getUUID());
		Role r = new Role();
		r.setRoleName(role.getRoleName());
		List<Role> roleList = roleService.readByEqualNotNull(r);
		if(roleList.size()>0) {
			rv.setRespcode(2);
			rv.setRespmesg("该资源已经存在！");
			return rv;
		}
		int ret=0;
		ret = roleService.insertWithPermissionList(role, pms);
		if(ret<1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
			return rv;
		}
		return rv;
	}
	
	@RequestMapping(value = "/updateByKey" , method = RequestMethod.POST )
	@ResponseBody
	@RequiresPermissions("base:role:update")
	public ResponseVO updateByKey(Role role,String pms) {
		ResponseVO rv = new ResponseVO(0);
		Role r = roleService.selectByKey(role.getRoleId());
		if(r==null) {
			rv.setRespcode(1);
			rv.setRespmesg("更新信息不存在");
			return rv;
		}
		/* 管理员角色不可以更名 */
		if("管理员".equals(r.getRoleName())) {
			role.setRoleName(r.getRoleName());
		}
		int ret=0;
		ret = roleService.updateWithPermissionListByKey(role,pms);
		if(ret<1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败,可能无需更新数据！");
		}
		rv.setRespmesg("交易成功，更新了"+ret+"条记录!");
		return rv;
	}	

	@RequestMapping("/deleteByKey")
	@ResponseBody
	@RequiresPermissions("base:role:delete")
	public ResponseVO deleteByKey(String roleId) {
		int ret=0;
		ResponseVO rv = new ResponseVO(0);
		Role role = new Role();
		role.setRoleId(roleId);
		Role r = roleService.selectByKey(role.getRoleId());
		/* 管理员角色不可以删除 */
		if(r==null || "管理员".equals(r.getRoleName())) {
			rv.setRespcode(1);
			rv.setRespmesg("角色不可删除！");
			return rv;
		}
		ret = roleService.deleteWithMapByKey(roleId);
		if(ret != 1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
		}
		return rv;
	}
	
	@RequestMapping("/readPermissionArrayByRole")
	@ResponseBody
	public String[] readPermissionArrayByRole(Role role) {
		return roleService.readPermissionStringByRole(role).split(",");
	}

}

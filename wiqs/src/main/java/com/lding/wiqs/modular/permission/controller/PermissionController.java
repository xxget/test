package com.lding.wiqs.modular.permission.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lding.wiqs.general.business.controller.BaseController;
import com.lding.wiqs.general.business.page.PageInfo;
import com.lding.wiqs.general.shiro.utils.TreeVoHelper;
import com.lding.wiqs.general.web.vo.ResponseVO;
import com.lding.wiqs.general.web.vo.TreeVo;
import com.lding.wiqs.modular.permission.domain.Permission;
import com.lding.wiqs.modular.permission.service.PermissionService;
import com.lding.wiqs.utils.SessionUtils;

/**
 * <p>Title: PermissionController</p>  
 * <p>Description: 资源权限管理Controller类</p>  
 * @author xixuguang 
 * @date 2018年3月2日 上午10:15:45
 *
 */
@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {
	
	@Autowired
	private PermissionService permissionService;

	private String pagelist = "permission/perPage";
	
	/**
	 * <p>Title: manage</p>  
	 * <p>Description: 进入资源权限管理页面</p>  
	 * @return
	 */
	@RequestMapping("/manage")
	@RequiresPermissions("base:permission:read")
	public String manage() {
		logger.info("进入资源权限管理页面");
		return pagelist;
	}

	@RequestMapping("/selectByWhere")
	@ResponseBody
	@RequiresPermissions("base:permission:read")
	public PageInfo<Permission> selectByWhere(Permission permission,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int rows) {
		List<Permission> PermissionList = permissionService.selectByEqualNotNull(permission, page, rows);
		return new PageInfo<Permission>(PermissionList);
	}
	
	@RequestMapping("/readAllAsTree")
	@ResponseBody
	@RequiresPermissions("base:role:read")
	public List<TreeVo> readAllAsTree(){
		List<Permission> permissionList = permissionService.readAllRows();
		return TreeVoHelper.transMenuToTree("0", permissionList);
	}
	
	@RequestMapping(value = "/insert" , method = RequestMethod.POST )
	@ResponseBody
	@RequiresPermissions("base:permission:insert")
	public ResponseVO insert(Permission permission) {
		ResponseVO rv = new ResponseVO(0);
		permission.setPerId(SessionUtils.getUUID());
		if(permission.getPerUrl().trim().length()>0) { 
			Permission p = new Permission();
			p.setPerUrl(permission.getPerUrl());
			List<Permission> permissionList = permissionService.readByEqualNotNull(p);
			if(permissionList.size()>0) {
				rv.setRespcode(2);
				rv.setRespmesg("该资源已经存在！");
				return rv;
			}
		}
		if("".equals(permission.getParentid())) permission.setParentid("0");
		int ret=0;
		ret = permissionService.insert(permission);
		if(ret<1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
		}
		return rv;
	}
	
	@RequestMapping(value = "/updateByKey" , method = RequestMethod.POST )
	@ResponseBody
	@RequiresPermissions("base:permission:update")
	public ResponseVO updateByKey(Permission permission) {
		ResponseVO rv = new ResponseVO(0);
		Permission p = permissionService.selectByKey(permission.getPerId());
		if(p==null) {
			rv.setRespcode(1);
			rv.setRespmesg("更新信息不存在");
			return rv;
		}else if( !permission.getParentid().equals(p.getParentid()) ){
			Permission p1 = new Permission();
			p1.setPerUrl(permission.getPerUrl());
			List<Permission> permissionList = permissionService.readByEqualNotNull(p1);
			List<TreeVo> permissionTree = TreeVoHelper.transMenuToTree(p.getPerId(), permissionList) ;
			if(TreeVoHelper.checkTree(permissionTree, permission.getParentid())) {
				rv.setRespcode(2);
				rv.setRespmesg("树逻辑错误！");
				return rv;
			}
		}
		int ret=0;
		ret = permissionService.updateAllByKey(permission);
		if(ret<1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
		}
		return rv;
	}	

	@RequestMapping("/deleteByKey")
	@ResponseBody
	@RequiresPermissions("base:permission:delete")
	public ResponseVO deleteByKey(String id) {
		int ret=0;
		ResponseVO rv = new ResponseVO(0);
		Permission p = new Permission();
		p.setParentid(id);
		List<Permission> permissionList = permissionService.readByEqualNotNull(p);
		if(permissionList.size()>0) {
			rv.setRespcode(2);
			rv.setRespmesg("树逻辑错误！有子节点");
			return rv;
		}
		ret = permissionService.deleteByKey(id);
		if(ret != 1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
		}
		return rv;
	}
}

package com.lding.wiqs.modular.role.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lding.wiqs.general.business.service.impl.BaseService;
import com.lding.wiqs.modular.permission.domain.Permission;
import com.lding.wiqs.modular.permission.mapper.PermissionMapper;
import com.lding.wiqs.modular.role.domain.Role;
import com.lding.wiqs.modular.role.service.RoleService;
import com.lding.wiqs.modular.rolepermission.domain.RolePermission;
import com.lding.wiqs.modular.rolepermission.mapper.RolePermissionMapper;
import com.lding.wiqs.modular.userrole.domain.UserRole;
import com.lding.wiqs.modular.userrole.mapper.UserRoleMapper;
import com.lding.wiqs.utils.SessionUtils;

import tk.mybatis.mapper.entity.Example;

@Service("RoleService")
public class RoleServiceImpl extends BaseService<Role> implements RoleService {
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	Logger log = Logger.getLogger(RoleServiceImpl.class);
	
	@Override
	public int deleteWithMapByKey(String id) {
		Example example = new Example(UserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", id);
		userRoleMapper.deleteByExample(example);
		example = new Example(RolePermission.class);
        criteria = example.createCriteria();
        criteria.andEqualTo("roleId", id);
		rolePermissionMapper.deleteByExample(example);
		return deleteByKey(id);
	}

	@Override
	public int insertWithPermissionList(Role role, String permissionList) {
		int ret = mapper.insert(role);
		List<Permission> pms=convertString2PermissionListWithParrent(permissionList);
		for(Permission pm:pms) {
			RolePermission record = new RolePermission();
			record.setId(SessionUtils.getUUID());
			record.setRoleId(role.getRoleId());
			record.setPerId(pm.getPerId());
			ret += rolePermissionMapper.insert(record);
		}
		return ret;
	}

	@Override
	public int updateWithPermissionListByKey(Role role, String permissionList) {
		Role oldRole = mapper.selectByPrimaryKey(role.getRoleId());
		int ret = 0;
		if(oldRole ==null) return 0;
		if ( !( oldRole.getRoleName().equals(role.getRoleName()) && oldRole.getRoleIsLock().equals(role.getRoleIsLock()) ) ){
			ret += mapper.updateByPrimaryKey(role);
		}
		/* 读出旧权限映射列表 */
		List<Permission> oldPms = readPermissionByRole(role);

		List<Permission> pms = convertString2PermissionListWithParrent(permissionList);
		String oldPermissionList = "";
		/* 删除新列表中没有的映射 */
		for(Permission pm:oldPms) {
			if( searchPermissionByID(pm.getPerId(),pms)==null ) {
				Example example = new Example(RolePermission.class);
		        Example.Criteria criteria = example.createCriteria();
		        criteria.andEqualTo("roleId", role.getRoleId());
		        criteria.andEqualTo("perId",pm.getPerId());
				ret += rolePermissionMapper.deleteByExample(example);
			}else{
				oldPermissionList += pm.getPerId() + ",";
			}
		}
		/* 增加列表中没有的映射 */
		for(Permission pm:pms) {
			if(oldPermissionList.indexOf(pm.getPerId())==-1) {
				RolePermission record = new RolePermission();
				record.setId(SessionUtils.getUUID());
				record.setRoleId(role.getRoleId());
				record.setPerId(pm.getPerId());
				ret += rolePermissionMapper.insert(record);
			}
		}
		return ret;
	}
	
	@Override
	public List<Permission> readPermissionByRole(Role role) {
		return permissionMapper.selectPermissionListByRoleID(role.getRoleId());
	}

	@Override
	public String readPermissionStringByRole(Role role){
		return convertPermissionList2StringWithoutParrent( permissionMapper.selectPermissionListByRoleID(role.getRoleId()));
	}
	
	List<Permission> readPermission() {
		return permissionMapper.selectAll();
	}
	
	String convertPermissionList2StringWithoutParrent(List<Permission> pms) {
		StringBuilder sb_p = new StringBuilder();
		for(Permission pm:pms) {
			sb_p.append(pm.getParentid());
		}
		StringBuilder sb_s = new StringBuilder();
		for(Permission pm:pms) {
			if(sb_p.indexOf(pm.getPerId())==-1) {
				if(sb_s.length()>0) sb_s.append(",");
				sb_s.append(pm.getPerId());
			}
		}
		return sb_s.toString();
	}
	
	List<Permission> convertString2PermissionListWithParrent(String pmstring) {
		List<Permission> allpms=readPermission();
		String[] pmsa = pmstring.split(",");
		List<Permission> pms = new ArrayList<Permission>();
		for(int i=0;i<pmsa.length;i++) {
			Permission p = searchPermissionByID(pmsa[i], allpms);
			if(p==null) {
				break;
			}
			pms.add(p);
			Permission pp = searchPermissionByID(p.getParentid(), allpms);
			if(pp!=null && searchPermissionByID(pp.getPerId(),pms)==null){
				pms.add(pp);
				if(! "0".equals(pp.getParentid())) {
					pp = searchPermissionByID(pp.getParentid(), allpms);
					if(pp!=null && searchPermissionByID(pp.getPerId(),pms)==null){
						pms.add(pp);
					}
				}
			}
		}
		return pms;
	}
	
	Permission searchPermissionByID(String id,List<Permission> pms) {
		for(Permission p:pms) {
			if(id.equals(p.getPerId())) 
				return p;
		}
		return null;
	}
}

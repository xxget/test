package com.lding.wiqs.modular.userinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lding.wiqs.app.login.domain.NetAppUser;
import com.lding.wiqs.general.business.service.impl.BaseService;
import com.lding.wiqs.modular.org.domain.Org;
import com.lding.wiqs.modular.org.service.OrgService;
import com.lding.wiqs.modular.userinfo.domain.UserInfo;
import com.lding.wiqs.modular.userinfo.service.UserInfoService;
import com.lding.wiqs.modular.userrole.domain.UserRole;
import com.lding.wiqs.modular.userrole.mapper.UserRoleMapper;
import com.lding.wiqs.utils.SessionUtils;

import tk.mybatis.mapper.entity.Example;

@Service("UserInfoService")
public class UserInfoServiceImpl extends BaseService<UserInfo> implements UserInfoService {

	@Autowired
	private UserRoleMapper userRoleMapper;
	
	/**
	 * 根据用户名查询用户信息
	 */
	@Override
	public UserInfo findUserByUsername(String userName) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(userName);
		List<UserInfo> users = readByEqualNotNull(userInfo);
		if(users.size()>0) {
			return readByEqualNotNull(userInfo).get(0);	
		}else{
			return null;
		}
	}

	@Override
	public List<UserInfo> readByOrgid(String orgid) {
        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orgid", orgid);
        return readByExample(example);
	}

	@Override
	public List<UserRole> readRoleByUser(UserInfo userInfo) {
        Example example = new Example(UserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userInfo.getUserId());
        return userRoleMapper.selectByExample(example);
	}

	@Override
	public int updateUserWithRole(UserInfo userInfo, String roles) {
		int ret = 0;
		/* 读旧权限列表 */
		List<UserRole> urs = readRoleByUser(userInfo);
		/* 删除没有了的旧权限映射 */
		String oldRoleList = "";
		for(UserRole ur:urs) {
			if(roles.indexOf( ur.getRoleId() )==-1) {
				ret += userRoleMapper.delete(ur);
			}else{
				oldRoleList += ur.getRoleId() + ",";
			}
		}
		/* 增加列表中没有的映射 */
		String role[] = roles.split(",");
		for(int i=0;i<role.length;i++) {
			if( oldRoleList.indexOf(role[i]) ==-1) {
				UserRole nur = new UserRole();
				nur.setId(SessionUtils.getUUID());
				nur.setUserId(userInfo.getUserId());
				nur.setRoleId(role[i]);
				ret += userRoleMapper.insert(nur);
			}
		}
		return ret;
	}
	
}

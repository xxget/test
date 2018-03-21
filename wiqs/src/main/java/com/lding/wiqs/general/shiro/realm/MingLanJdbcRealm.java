package com.lding.wiqs.general.shiro.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.lding.wiqs.general.shiro.utils.TreeVoHelper;
import com.lding.wiqs.general.web.vo.UserVo;
import com.lding.wiqs.modular.org.service.OrgService;
import com.lding.wiqs.modular.permission.domain.Permission;
import com.lding.wiqs.modular.permission.service.PermissionService;
import com.lding.wiqs.modular.userinfo.domain.UserInfo;
import com.lding.wiqs.modular.userinfo.service.UserInfoService;



/**
 * 认证器，读取数据库，MD5+SALT方式加密，用户认证
 * @author yangling
 *
 */
public class MingLanJdbcRealm extends AuthorizingRealm {

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private OrgService orgService;
	
	private String name="minglanRealm";
	
	public String getName() {
		return this.name;
	}
	/**
	 * 获取授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		UserVo uv = (UserVo) principalCollection.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		for(Permission perm:uv.getPermissions()) {
			if( ! "".equals(perm.getPercode()) ) {
				authorizationInfo.addStringPermission(perm.getPercode());
			}
		}
		return authorizationInfo;
	}

	/**
	 * 获取用户凭据，用于认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		String username = (String)token.getPrincipal();
		
		UserInfo userInfo=null;
		try {
			userInfo = userInfoService.findUserByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(userInfo==null) return null;
		
		UserVo uv = new UserVo();
		uv.setUserId(userInfo.getUserId());
		uv.setUserName(userInfo.getRealName());
		uv.setUserCode(userInfo.getUserName());
		uv.setOrg(orgService.selectByKey(userInfo.getOrgId()));
		
		List<Permission> menus=null;
		List<Permission> permissions=null;
		try {
			menus = permissionService.findMenuListByUserId(userInfo.getUserId());
			permissions=permissionService.findPermissionListByUserId(userInfo.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		uv.setMenus(TreeVoHelper.transMenuToTree("0" ,menus));
		uv.setPermissions(permissions);
		
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(uv, userInfo.getPassword(), 
				ByteSource.Util.bytes(userInfo.getSalt()), getName());

		return authenticationInfo;
	}
	
}

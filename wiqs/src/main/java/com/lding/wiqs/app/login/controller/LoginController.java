package com.lding.wiqs.app.login.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lding.wiqs.app.login.domain.NetAppUser;
import com.lding.wiqs.app.login.domain.StateCode;
import com.lding.wiqs.general.business.controller.BaseController;
import com.lding.wiqs.general.business.exception.BaseWebException;
import com.lding.wiqs.modular.org.domain.Org;
import com.lding.wiqs.modular.org.service.OrgService;
import com.lding.wiqs.modular.permission.domain.Permission;
import com.lding.wiqs.modular.permission.service.PermissionService;
import com.lding.wiqs.modular.userinfo.domain.UserInfo;
import com.lding.wiqs.modular.userinfo.service.UserInfoService;

/**
 * <p>Title: LoginController</p>  
 * <p>Description: APP用户登录部分</p>  
 * @author xixuguang 
 * @date 2018年3月5日 下午4:32:45
 */
@Controller
public class LoginController extends BaseController {

	public String loginTests(){
		logger.info("测试用户登录2");
		return "abc";
	}
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private PermissionService permissionService;
	/**
	 * <p>
	 * Title: loginAction
	 * </p>
	 * <p>
	 * Description: app登录
	 * </p>
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws BaseWebException
	 */
	public NetAppUser loginAPP(String username, String password) throws BaseWebException {

		NetAppUser netAppUser = new NetAppUser();
		//判断是否为空
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {// 参数不合法
			netAppUser.setStateCode(StateCode.INVALID_PARAMETERS);
			return netAppUser;
		}
		netAppUser = netAppUserInfo(username);
		//判断用户名是否错误
		if (netAppUser.getUserInfo() == null) {// 用户名不存在提示用户名或密码错误
			netAppUser.setStateCode(StateCode.USERNAME_AND_PASSWORD_NOT_EXIST);
			return netAppUser;
		}
		//判断密码是否错误
		String pwd = new SimpleHash("md5", password, ByteSource.Util.bytes(netAppUser.getUserInfo().getSalt()), 2).toHex();
		if (!netAppUser.getUserInfo().getPassword().equals(pwd)) {// 密码错误
			netAppUser.setStateCode(StateCode.USERNAME_AND_PASSWORD_NOT_EXIST);
			return netAppUser;
		}
		
		netAppUser.setStateCode(StateCode.OK);
		logger.debug("APP login...");
		return netAppUser;
	}
	
	/**
	 * 根据登录名查询APP用户的详细信息及其相关联信息 
	 */
	public NetAppUser netAppUserInfo(String username) {
		NetAppUser netAppUser = new NetAppUser();
		
		UserInfo userInfo = new UserInfo();
		userInfo = userInfoService.findUserByUsername(username);
		netAppUser.setUserInfo(userInfo);
		
		Org org = new Org();
		org = orgService.selectByKey(userInfo.getOrgId());
		netAppUser.setOrg(org);
		
		List<Permission> permissionList = permissionService.findPermissionListByUserId(userInfo.getUserId());
		netAppUser.setPermissionList(permissionList);
		
		return netAppUser;
	}
}

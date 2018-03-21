package com.lding.wiqs.modular.userinfo.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lding.wiqs.general.business.controller.BaseController;
import com.lding.wiqs.general.business.page.PageInfo;
import com.lding.wiqs.general.shiro.utils.PasswordHelper;
import com.lding.wiqs.general.web.vo.ResponseVO;
import com.lding.wiqs.general.web.vo.UserVo;
import com.lding.wiqs.modular.org.domain.Org;
import com.lding.wiqs.modular.org.service.OrgService;
import com.lding.wiqs.modular.userinfo.domain.UserInfo;
import com.lding.wiqs.modular.userinfo.service.UserInfoService;
import com.lding.wiqs.modular.userrole.domain.UserRole;
import com.lding.wiqs.utils.SessionUtils;

@Controller
@RequestMapping("/userinfo")
public class UserInfoController extends BaseController {

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private OrgService orgService;
	
	@RequestMapping("/userpage")
	@RequiresPermissions("base:user:read")
	public String manage() {
		return "user/userPage";
	}

	@RequestMapping("/selectByWhere")
	@ResponseBody
	@RequiresPermissions("base:user:read")
	public PageInfo<UserInfo> selectByWhere(UserInfo userInfo,
			@RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int rows) {
		List<UserInfo> userInfos = userInfoService.selectByEqualNotNull(userInfo, page, rows);
		for(UserInfo u:userInfos) {
			u.setPassword("********");
			u.setSalt("========");
		}
		return new PageInfo<UserInfo>(userInfos);
	}
	
	/**
	 * <p>Title: insert</p>  
	 * <p>Description: 插入一条用户信息</p>  
	 * @param userInfo
	 * @param roles
	 * @return
	 */
	@RequestMapping(value = "/insert" , method = RequestMethod.POST )
	@ResponseBody
	@RequiresPermissions("base:user:insert")
	public ResponseVO insert(String username, String realname, String orgid, String phone ,String roles) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(username);
		
		userInfo.setRealName(realname);
		userInfo.setOrgId(orgid);
		userInfo.setPhone(phone);
		logger.info("插入一条用户信息： " + userInfo);
		ResponseVO rv = new ResponseVO(0);
		userInfo.setUserId(SessionUtils.getUUID());
		Org oldOrg = orgService.selectByKey(userInfo.getOrgId());
		if(oldOrg==null) {
			rv.setRespcode(1);
			rv.setRespmesg("机构信息不存在");
			return rv;
		}
		UserInfo nameu = userInfoService.findUserByUsername(userInfo.getUserName());
		if( nameu != null ) {
			rv.setRespcode(2);
			rv.setRespmesg("登录名已经存在！");
			return rv;
		}
		userInfo.setPassword(userInfo.getUserName().substring(0,6)+SessionUtils.getUUID().substring(0,4));
		rv.setRespmesg("请记录用户密码：["+userInfo.getPassword()+"]");
		PasswordHelper ph = new PasswordHelper("MD5", 2);
		ph.encryptPassword(userInfo);
		int ret = userInfoService.insert(userInfo);
		if(ret<1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
			return rv;
		}
		userInfoService.updateUserWithRole(userInfo,roles);
		return rv;
	}

	@RequestMapping(value = "/updateByKey" , method = RequestMethod.POST )
	@ResponseBody
	@RequiresPermissions("base:user:update")
	public ResponseVO updateByKey(UserInfo userInfo,String roles) {
		ResponseVO rv = new ResponseVO(0);
		UserInfo oldu = userInfoService.selectByKey(userInfo.getUserId());
		if( oldu == null ) {
			rv.setRespcode(1);
			rv.setRespmesg("用户信息不存在");
			return rv;		
		}
		if("manager".equals(oldu.getUserName())) {
			if(! "manager".equals(userInfo.getUserName())) {
				rv.setRespcode(1);
				rv.setRespmesg("超级管理员登录名不可修改");
				return rv;		
			}
		}else{
			UserInfo nameu = userInfoService.findUserByUsername(userInfo.getUserName());
			if( nameu != null && !nameu.getUserId().equals(oldu.getUserId()) ) {
				rv.setRespcode(2);
				rv.setRespmesg("登录名已经存在！");
				return rv;		
			}
		}
		
		Org oldOrg = orgService.selectByKey(userInfo.getOrgId());
		if(oldOrg==null) {
			rv.setRespcode(3);
			rv.setRespmesg("机构信息不存在");
			return rv;
		}
		oldu.setPhone(userInfo.getPhone());
		oldu.setUserName(userInfo.getUserName());
		oldu.setRealName(userInfo.getRealName());
		oldu.setOrgId(userInfo.getOrgId());
		int ret = userInfoService.updateAllByKey(oldu);
		if(ret<1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
			return rv;
		}
		if( !"manager".equals(oldu.getUserName())) {
			ret = userInfoService.updateUserWithRole(userInfo,roles);
		}else{
			ret=0;
		}
		rv.setRespmesg("更新成功，角色更新了["+ret+"]条记录!");
		return rv;
	}
	
	@RequestMapping(value = "/deleteByKey" , method = RequestMethod.POST )
	@ResponseBody
	@RequiresPermissions("base:user:delete")
	public ResponseVO deleteByKey(String id) {
		ResponseVO rv = new ResponseVO(0);
		UserInfo oldu = userInfoService.selectByKey(id);
		if( oldu == null ) {
			rv.setRespcode(1);
			rv.setRespmesg("用户信息不存在");
			return rv;		
		}
		if("admin".equals(oldu.getUserName())) {
			rv.setRespcode(2);
			rv.setRespmesg("超级管理员不可删除!");
			return rv;		
		}
		int ret = userInfoService.deleteByKey(id);
		if(ret<1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
		}
		return rv;
	}

	@RequestMapping("/readRoleByUser")
	@ResponseBody
	public String[] readRoleByUser(UserInfo userInfo) {
		List<UserRole> urs = userInfoService.readRoleByUser(userInfo);
		String[] urArray = new String[urs.size()];
		int i=0;
		for(UserRole ur:urs) {
			urArray[i]=ur.getRoleId();
			i++;
		}
		return urArray;
	}

	@RequestMapping("/updateUserResetPassword")
	@ResponseBody
	@RequiresPermissions("base:user:update")
	public ResponseVO updateUserResetPassword(UserInfo userInfo) {
		ResponseVO rv = new ResponseVO(0);
		UserInfo r = userInfoService.selectByKey(userInfo.getUserId());
		if(r==null) {
			rv.setRespcode(1);
			rv.setRespmesg("用户信息不存在");
			return rv;
		}
		r.setPassword(r.getUserName().substring(0,6)+SessionUtils.getUUID().substring(1,5));
		rv.setRespmesg("请记录用户密码：["+r.getPassword()+"]");
		PasswordHelper ph = new PasswordHelper("MD5", 2);
		ph.encryptPassword(r);
		int ret = userInfoService.updateAllByKey(r);
		if(ret<1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
			return rv;
		}
		return rv;
	}

	@RequestMapping("/updatePassword")
	@ResponseBody
	public ResponseVO updatePassword(String password,String newpassword) {
		ResponseVO rv = new ResponseVO(0);
		UserVo uv = (UserVo) req.getSession().getAttribute(SessionUtils.USER);
		UserInfo r = userInfoService.selectByKey(uv.getUserId());
		if(r==null || password.equals(newpassword)) {
			rv.setRespcode(1);
			rv.setRespmesg("密码更新失败!");
			return rv;
		}
		if( ! Pattern.matches("((?=.*\\d)(?=.*\\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,}$", newpassword) ) {
			rv.setRespcode(2);
			rv.setRespmesg("简单密码,更新失败!");
			return rv;
		}
		PasswordHelper ph = new PasswordHelper("MD5", 2);
		String oldPassword = r.getPassword();
		r.setPassword(password);
		ph.encryptPassword(r);
		if(!oldPassword.equals(r.getPassword())) {
			rv.setRespcode(2);
			rv.setRespmesg("密码校验失败！");
			return rv;
		}
		r.setPassword(newpassword);
		r.setSalt(null);
		rv.setRespmesg("请牢记用户密码");
		ph.encryptPassword(r);
		int ret = userInfoService.updateAllByKey(r);
		if(ret<1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
			return rv;
		}
		return rv;
		
	}
}

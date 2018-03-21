package com.lding.wiqs.app.tel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lding.wiqs.app.tel.domain.TelList;
import com.lding.wiqs.general.business.controller.BaseController;
import com.lding.wiqs.modular.org.controller.OrgController;
import com.lding.wiqs.modular.userinfo.domain.UserInfo;
import com.lding.wiqs.modular.userinfo.service.UserInfoService;

/**
 * <p>Title: TelListController</p>  
 * <p>Description: 通讯录处理</p>  
 * @author xixuguang 
 * @date 2018年3月8日 下午2:27:54
 */
@Controller
public class TelListController extends BaseController{
	
	@Autowired
	private UserInfoService userInfoService;
	/**
	 * 查询所有用户信息生产通讯录
	 */
	public TelList userTelInfo() {
		OrgController orgController = new OrgController();
		TelList telList = new TelList();
		List<UserInfo> userInfoList = userInfoService.readAllRows();
		for(UserInfo u : userInfoList) {
			telList.setUserId(u.getUserId());
			telList.setJobId(orgController.queryOrgName(u.getJobId()));
			telList.setAddress(u.getAddress());
			telList.setIcon(u.getIcon());
			telList.setPhone(u.getPhone());
			telList.setRealName(u.getRealName());
			telList.setOrgName(u.getOrgId());
		}
		return telList;
	}
}

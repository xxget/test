package com.lding.wiqs.modular.appversion.service;

import com.lding.wiqs.general.business.service.IService;
import com.lding.wiqs.modular.appversion.domain.AppVersion;
import com.lding.wiqs.modular.userrole.domain.UserRole;

/**
 * <p>Title: AppVersionService</p>  
 * <p>Description: APP版本类接口</p>  
 * @author xixuguang 
 * @date 2018年3月22日 下午4:27:29
 *
 */
public interface AppVersionService extends IService<AppVersion> {

	/**
	 * <p>Title: queryLatestVersionInfo</p>  
	 * <p>Description: 查询最新的App版本信息</p>  
	 * @return
	 */
//	AppVersion queryLatestVersionInfo();
	
	/**
	 * <p>Title: insertAppVersionInfo</p>  
	 * <p>Description: 插入一条版本信息</p>  
	 * @param appVersion
	 * @return
	 */
	Integer insertAppVersionInfo(AppVersion appVersion);
}

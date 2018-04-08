package com.lding.wiqs.modular.appversion.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lding.wiqs.general.business.service.impl.BaseService;
import com.lding.wiqs.general.web.vo.UserVo;
import com.lding.wiqs.modular.appversion.domain.AppVersion;
import com.lding.wiqs.modular.appversion.service.AppVersionService;
import com.lding.wiqs.utils.SessionUtils;

/**
 * <p>Title: AppVersionServiceImpl</p>  
 * <p>Description: app版本实现类</p>  
 * @author xixuguang 
 * @date 2018年3月22日 下午4:30:51
 *
 */
@Service("AppVersionService")
public class AppVersionServiceImpl extends BaseService<AppVersion> implements AppVersionService {

//	/**
//	 * <p>Title: getLatestVersionInfo</p>  
//	 * <p>Description: 查询最新的App版本信息</p>  
//	 * @return
//	 */
//	@Override
//	public AppVersion queryLatestVersionInfo() {
//		AppVersion appVersion = new AppVersion();
//		//获取状态为最新版本的版本信息
//		appVersion.setVersioinState("1");
//		List <AppVersion> appVersionList = readByExample(appVersion);
//		for(AppVersion a : appVersionList) {
//			appVersion = a;
//		}
//		return appVersion;
//	}
	
	/**
	 * <p>Title: insertAppVersionInfo</p>  
	 * <p>Description: 插入一条版本信息</p>  
	 * @param appVersion
	 */
	@Override
	public Integer insertAppVersionInfo(AppVersion appVersion) {
		AppVersion version = new AppVersion();
		version.setVersioinState("1");
		List <AppVersion> appVersionList = readByEqualNotNull(version);
		//将历史数据改为0
		for(AppVersion a : appVersionList) {
			a.setVersioinState("0");
			updateAllByKey(a);
		}
		//将新增数据改为1
		appVersion.setVersioinState("1");
		//获取UUID为主键
		appVersion.setId(SessionUtils.getUUID());
		//获取时间戳为当前日期
		appVersion.setVersionData(String.valueOf(new Date().getTime() / 1000));
		return insert(appVersion);
	}
	
	

}

package com.lding.wiqs.modular.appversion.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lding.wiqs.general.business.controller.BaseController;
import com.lding.wiqs.general.business.page.PageInfo;
import com.lding.wiqs.general.web.vo.ResponseVO;
import com.lding.wiqs.general.web.vo.UserVo;
import com.lding.wiqs.modular.appversion.domain.AppVersion;
import com.lding.wiqs.modular.appversion.service.AppVersionService;
import com.lding.wiqs.modular.dict.domain.Dict;
import com.lding.wiqs.utils.SessionUtils;

/**
 * <p>
 * Title: AppVersionController
 * </p>
 * <p>
 * Description: app版本信息controller类
 * </p>
 * 
 * @author xixuguang
 * @date 2018年3月22日 下午4:33:06
 *
 */
@Controller
@RequestMapping("/appversion")
public class AppVersionController extends BaseController {

	@Autowired
	private AppVersionService appVersionService;

	/**
	 * <p>
	 * Title: manage
	 * </p>
	 * <p>
	 * Description: 进入页面的方法
	 * </p>
	 * 
	 * @return
	 */
	@RequiresPermissions("base:appversion:read")
	@RequestMapping("/manage")
	public String manage() {
		return "appVersion/appVersionPage";
	}

	/**
	 * <p>
	 * Title: selectByWhere
	 * </p>
	 * <p>
	 * Description: 根据条件分页查询app版本信息
	 * </p>
	 * 
	 * @param appVersion
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/selectByWhere")
	@ResponseBody
	@RequiresPermissions("base:appVersion:read")
	public PageInfo<AppVersion> selectByWhere(AppVersion appVersion,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int rows) {
		List<AppVersion> appVersionList = appVersionService.selectByEqualNotNull(appVersion, page, rows);
		return new PageInfo<AppVersion>(appVersionList);
	}

	/**
	 * <p>
	 * Title: insert
	 * </p>
	 * <p>
	 * Description: 插入一条版本信息数据
	 * </p>
	 * 
	 * @param appVersion
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	@RequiresPermissions("base:appVersion:insert")
	public ResponseVO insert(AppVersion appVersion) {
		int ret = 0;
		ResponseVO rv = new ResponseVO(0);
		AppVersion version = new AppVersion();
		version.setVersionCode(appVersion.getVersionCode());
		List<AppVersion> appVersionList = appVersionService.readByEqualNotNull(version);
		if (appVersionList.size() > 0) {
			rv.setRespcode(2);
			rv.setRespmesg("APP版本记录已经存在！");
			return rv;
		}
		UserVo uv = (UserVo) req.getSession().getAttribute(SessionUtils.USER);
		appVersion.setUserId(uv.getUserId());
		ret = appVersionService.insertAppVersionInfo(appVersion);
		if (ret != 1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
		}
		return rv;
	}

	/**
	 * <p>
	 * Title: updateByKey
	 * </p>
	 * <p>
	 * Description: 根据主键更新一条数据
	 * </p>
	 * 
	 * @param appVersion
	 * @return
	 */
	@RequestMapping("/updateByKey")
	@ResponseBody
	@RequiresPermissions("base:appVersion:update")
	public ResponseVO updateByKey(AppVersion appVersion) {
		int ret = 0;
		ResponseVO rv = new ResponseVO(0);

		AppVersion version = new AppVersion();
		version = appVersionService.selectByKey(appVersion.getId());
		if ("1".equals(version.getVersioinState())) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败，不能更新当前版本数据！");
			return rv;
		} else {
			ret = appVersionService.updateNotNullByKey(appVersion);
			if (ret != 1) {
				rv.setRespcode(8);
				rv.setRespmesg("数据库更新失败");
			}
			return rv;
		}
	}

	/**
	 * <p>
	 * Title: deleteByKey
	 * </p>
	 * <p>
	 * Description: 根据主键删除一条数据
	 * </p>
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteByKey")
	@ResponseBody
	@RequiresPermissions("base:appVersion:delete")
	public ResponseVO deleteByKey(String id) {
		int ret = 0;
		ResponseVO rv = new ResponseVO(0);

		AppVersion appVersion = new AppVersion();
		appVersion = appVersionService.selectByKey(id);
		if ("1".equals(appVersion.getVersioinState())) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败，不能删除当前版本数据！");
			return rv;
		} else {

			ret = appVersionService.deleteByKey(id);
			if (ret != 1) {
				rv.setRespcode(8);
				rv.setRespmesg("数据库更新失败");
			}
			return rv;
		}
	}
}

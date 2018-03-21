package com.lding.wiqs.modular.pointinfo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.lding.wiqs.general.business.controller.BaseController;
import com.lding.wiqs.general.business.page.PageInfo;
import com.lding.wiqs.general.web.vo.ResponseVO;
import com.lding.wiqs.modular.imggroup.domain.ImgGroup;
import com.lding.wiqs.modular.imggroup.service.ImgGroupService;
import com.lding.wiqs.modular.pointinfo.domain.PointAllInfo;
import com.lding.wiqs.modular.pointinfo.domain.PointInfo;
import com.lding.wiqs.modular.pointinfo.service.PointInfoService;
import com.lding.wiqs.utils.DataUtils;
import com.lding.wiqs.utils.SessionUtils;

/**
 * <p>
 * Title: PointInfoController
 * </p>
 * <p>
 * Description: 坐标点设施位置信息controller类
 * </p>
 * 
 * @author xixuguang
 * @date 2018年3月2日 上午9:51:54
 *
 */
@Controller
@RequestMapping("/pointinfo")
public class PointInfoController extends BaseController {

	DataUtils dataUtils = new DataUtils();

	@Autowired
	private PointInfoService pointInfoService;
	
	@Autowired
	private ImgGroupService imgGroupService;

	/**
	 * <p>
	 * Title: manage
	 * </p>
	 * <p>
	 * Description: 进入坐标点设施位置信息页面
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/manage")
	@RequiresPermissions("resource:pointinfo:read")
	public String manage() {
		return "point_info/pointInfoPage";
	}

	/**
	 * 根据条件分页查询坐标点设施位置信息信息
	 * 
	 * @param dict
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/selectByWhere")
	@ResponseBody
	@RequiresPermissions("resource:pointinfo:read")
	public PageInfo<PointInfo> selectByWhere(PointInfo pointInfo,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int rows) {
		List<PointInfo> pointInfoList = pointInfoService.selectByEqualNotNull(pointInfo, page, rows);
		return new PageInfo<PointInfo>(pointInfoList);
	}

	/**
	 * <p>
	 * Title: insert
	 * </p>
	 * <p>
	 * Description: 新插入一条设施点信息
	 * </p>
	 * 
	 * @param pointInfo
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	@RequiresPermissions("resource:pointinfo:insert")
	public ResponseVO insertPointInfo(PointInfo pointInfo) {
		// 取得 session 中的 code
		String exceptionClassName = (String) req.getAttribute(SessionUtils.SHIRO_LOGIN_FAILURE);
		int ret = 0;
		ResponseVO rv = new ResponseVO(0);
		//获取主键uuid
		pointInfo.setPointId(SessionUtils.getUUID());
		//如果是app端提交的会带用户信息，pc端的自动获取
		if (StringUtils.isEmpty(pointInfo.getUserId())) {
			pointInfo.setUserId(exceptionClassName);
		}
		pointInfo.setSaveTime(dataUtils.getTimeStampLong());
		ret = pointInfoService.insert(pointInfo);
		if (ret != 1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
		}
		return rv;
	}
	
	/**
	 * <p>Title: readAllRows</p>  
	 * <p>Description: 获取所有设施点信息</p>  
	 * @param pointInfo
	 * @return
	 */
	@RequestMapping("/readAllPointInfoList")
	@ResponseBody
	//@RequiresPermissions("resource:pointinfo:read")
	public List<PointInfo> readAllPointInfoList() {
		List<PointInfo> pointInfoList = pointInfoService.readAllRows();
		return pointInfoList;
	}
	
	/**
	 * 资源点信息及其图片
	 */
	public String readPointAllInfo() {
		PointAllInfo pointAllInfo = new PointAllInfo();
		List<PointAllInfo> pointAllInfoList =  new ArrayList();
		List<PointInfo> pointInfoList = pointInfoService.readAllRows();
		
		for(PointInfo p : pointInfoList) {
			//添加设施资源点详细信息
			pointAllInfo.setAreaId(p.getAreaId());
			pointAllInfo.setHeadName(p.getHeadName());
			pointAllInfo.setHeadPhone(p.getHeadPhone());
			pointAllInfo.setLat(p.getLat());
			pointAllInfo.setLats(p.getLats());
			pointAllInfo.setLng(p.getLng());
			pointAllInfo.setLngs(p.getLngs());
			pointAllInfo.setNote(p.getNote());
			pointAllInfo.setPointId(p.getPointId());
			pointAllInfo.setPointInfo(p.getPointInfo());
			pointAllInfo.setPointState(p.getPointState());
			pointAllInfo.setPointType(p.getPointType());
			pointAllInfo.setSaveTime(p.getSaveTime());
			pointAllInfo.setUnitName(p.getUnitName());
			pointAllInfo.setUpdateTime(p.getUpdateTime());
			pointAllInfo.setUpdateUserId(p.getUpdateUserId());
			pointAllInfo.setUserId(p.getUserId());
			
			pointAllInfoList.add(pointAllInfo);
			ImgGroup imgGroup = new ImgGroup();
			imgGroup.setPointId(p.getPointId());
			List<ImgGroup> imgGroupList = imgGroupService.readByExample(imgGroup);
			//添加相关图片
			for(ImgGroup i : imgGroupList) {
				pointAllInfo.setImgGroup(i);
				pointAllInfoList.add(pointAllInfo);
			}
		}
		return JSON.toJSONString(pointAllInfoList);
	}
}

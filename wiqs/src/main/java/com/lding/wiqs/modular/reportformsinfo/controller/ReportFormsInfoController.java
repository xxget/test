package com.lding.wiqs.modular.reportformsinfo.controller;

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
import com.lding.wiqs.modular.reportformsinfo.domain.ReportFormsInfo;
import com.lding.wiqs.modular.reportformsinfo.service.ReportFormsInfoService;
import com.lding.wiqs.utils.DataUtils;
import com.lding.wiqs.utils.SessionUtils;

/**
 * <p>Title: ReportFormsInfoController</p>  
 * <p>Description: 报表模块_报表信息表Controller</p>  
 * @author xixuguang 
 * @date 2018年3月29日 下午3:26:34
 *
 */
@Controller
@RequestMapping("/reportFormsInfo")
public class ReportFormsInfoController extends BaseController {
	@Autowired
	private ReportFormsInfoService reportFormsInfoService;

	private String pagelist = "report_forms_info/reportFormsInfoPage";

	DataUtils dataUtils = new DataUtils();

	/**
	 * <p>
	 * Title: manage
	 * </p>
	 * <p>
	 * Description:进入报表信息页面
	 * </p>
	 * 
	 * @return
	 */
	@RequiresPermissions("report:reportformsinfo:read")
	@RequestMapping("/manage")
	public String manage() {
		return pagelist;
	}

	/**
	 * 根据条件分页查询
	 * 
	 * @param dict
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/selectByWhere")
	@ResponseBody
	@RequiresPermissions("report:reportformsinfo:read")
	public PageInfo<ReportFormsInfo> selectByWhere(ReportFormsInfo reportFormsInfo, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int rows) {
		List<ReportFormsInfo> reportFormsInfoList = reportFormsInfoService.selectByEqualNotNull(reportFormsInfo, page, rows);
		return new PageInfo<ReportFormsInfo>(reportFormsInfoList);
	}

	/**
	 * <p>Title: insert</p>  
	 * <p>Description: 插入一条数据</p>  
	 * @param reportFormsInfo
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	@RequiresPermissions("report:reportformsinfo:insert")
	public ResponseVO insert(ReportFormsInfo reportFormsInfo) {
		int ret = 0;
		// 取得 session 中的 code
		String exceptionClassName = (String) req.getAttribute(SessionUtils.SHIRO_LOGIN_FAILURE);
		ResponseVO rv = new ResponseVO(0);
		reportFormsInfo.setRfId(SessionUtils.getUUID());
		reportFormsInfo.setMakeId(exceptionClassName);
		reportFormsInfo.setMakeTime(dataUtils.getTimeStampString());
		ret = reportFormsInfoService.insert(reportFormsInfo);
		if (ret != 1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
		}
		return rv;
	}

	/**
	 * <p>Title: updateByKey</p>  
	 * <p>Description: 更新一条数据</p>  
	 * @param reportFormsInfo
	 * @return
	 */
	@RequestMapping("/updateByKey")
	@ResponseBody
	@RequiresPermissions("report:reportformsinfo:update")
	public ResponseVO updateByKey(ReportFormsInfo reportFormsInfo) {
		int ret = 0;
		ResponseVO rv = new ResponseVO(0);
		ret = reportFormsInfoService.updateNotNullByKey(reportFormsInfo);
		if (ret != 1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
		}
		return rv;
	}

	/**
	 * <p>Title: deleteByKey</p>  
	 * <p>Description: 删除一条数据</p>  
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteByKey")
	@ResponseBody
	@RequiresPermissions("report:reportformsinfo:delete")
	public ResponseVO deleteByKey(String id) {
		int ret = 0;
		ResponseVO rv = new ResponseVO(0);
		ret = reportFormsInfoService.deleteByKey(id);
		if (ret != 1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
		}
		return rv;
	}

}

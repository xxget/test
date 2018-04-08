package com.lding.wiqs.modular.problem.controller;

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
import com.lding.wiqs.modular.problem.domain.Problem;
import com.lding.wiqs.modular.problem.service.ProblemService;
import com.lding.wiqs.utils.DataUtils;
import com.lding.wiqs.utils.SessionUtils;

/**
 * <p>
 * Title: ProblemController
 * </p>
 * <p>
 * Description: 报表模块_报表包含问题表Controller
 * </p>
 * 
 * @author xixuguang
 * @date 2018年4月3日 下午4:00:40
 *
 */
@Controller
@RequestMapping("/problem")
public class ProblemController extends BaseController {
	@Autowired
	private ProblemService problemService;

	private String pagelist = "problem/problemPage";

	DataUtils dataUtils = new DataUtils();

	/**
	 * <p>
	 * Title: manage
	 * </p>
	 * <p>
	 * Description:进入数据字典页面
	 * </p>
	 * 
	 * @return
	 */
	@RequiresPermissions("report:problem:read")
	@RequestMapping("/manage")
	public String manage() {
		return pagelist;
	}

	/**
	 * 根据条件分页查询数据字典信息
	 * 
	 * @param dict
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/selectByWhere")
	@ResponseBody
	@RequiresPermissions("report:problem:read")
	public PageInfo<Problem> selectByWhere(Problem problem,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int rows) {
		List<Problem> problemList = problemService.selectByEqualNotNull(problem, page, rows);
		//List<Problem> problemList = problemService.selectByExample(problem, page, rows);
		return new PageInfo<Problem>(problemList);
	}

	/**
	 * 
	 * @param dict
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	@RequiresPermissions("report:problem:insert")
	public ResponseVO insert(Problem problem) {
		// 取得 session 中的 code
		String exceptionClassName = (String) req.getAttribute(SessionUtils.SHIRO_LOGIN_FAILURE);
		int ret = 0;
		ResponseVO rv = new ResponseVO(0);
		problem.setProblemId(SessionUtils.getUUID());
		problem.setReportTime(dataUtils.getTimeStampString());
		problem.setReportUser(exceptionClassName);
		ret = problemService.insert(problem);
		if (ret != 1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
		}
		return rv;
	}

	/**
	 * 
	 * @param dict
	 * @return
	 */
	@RequestMapping("/updateByKey")
	@ResponseBody
	@RequiresPermissions("report:problem:update")
	public ResponseVO updateByKey(Problem problem) {
		int ret = 0;
		ResponseVO rv = new ResponseVO(0);
		ret = problemService.updateNotNullByKey(problem);
		if (ret != 1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
		}
		return rv;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteByKey")
	@ResponseBody
	@RequiresPermissions("report:problem:delete")
	public ResponseVO deleteByKey(String id) {
		int ret = 0;
		ResponseVO rv = new ResponseVO(0);
		ret = problemService.deleteByKey(id);
		if (ret != 1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
		}
		return rv;
	}
}

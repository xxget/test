package com.lding.wiqs.modular.imggroup.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lding.wiqs.general.business.page.PageInfo;
import com.lding.wiqs.general.web.vo.ResponseVO;
import com.lding.wiqs.modular.dict.domain.Dict;
import com.lding.wiqs.modular.imggroup.domain.ImgGroup;
import com.lding.wiqs.modular.imggroup.service.ImgGroupService;
import com.lding.wiqs.utils.SessionUtils;

/**
 * <p>Title: ImgGroupController</p>  
 * <p>Description: 图片资源组处理controller类</p>  
 * @author xixuguang 
 * @date 2018年3月2日 上午9:41:23
 *
 */
@Controller
@RequestMapping("/imggroup")
public class ImgGroupController {

	@Autowired
	private ImgGroupService imgGroupService;
	
	/**
	 * <p>Title: manage</p>  
	 * <p>Description: 进入图片资源组的页面</p>  
	 * @return
	 */
	@RequestMapping("/manage")
	@RequiresPermissions("resource:imggroup:read")
	public String manage() {
		return "img_group/imgGroupPage";
	}
	
	/**
	 * 根据条件分页查询图片资源组信息
	 * @param dict
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/selectByWhere")
	@ResponseBody
	@RequiresPermissions("resource:imggroup:read")
	public PageInfo<ImgGroup> selectByWhere(ImgGroup imgGroup, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int rows) {
		List<ImgGroup> imgGroupList = imgGroupService.selectByEqualNotNull(imgGroup, page, rows);
		return new PageInfo<ImgGroup>(imgGroupList);
	}
}

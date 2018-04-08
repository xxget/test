package com.lding.wiqs.modular.dict.controller;

import java.util.ArrayList;
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
import com.lding.wiqs.modular.dict.domain.Dict;
import com.lding.wiqs.modular.dict.service.DictService;
import com.lding.wiqs.utils.DataUtils;
import com.lding.wiqs.utils.SessionUtils;

@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {

	@Autowired
	private DictService dictService;

	private String pagelist = "dict/dictPage";

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
	@RequiresPermissions("base:dict:read")
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
	@RequiresPermissions("base:dict:read")
	public PageInfo<Dict> selectByWhere(Dict dict, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int rows) {
		List<Dict> dictList = dictService.selectByEqualNotNull(dict, page, rows);
		return new PageInfo<Dict>(dictList);
	}

	/**
	 * 
	 * @param dict
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	@RequiresPermissions("base:dict:insert")
	public ResponseVO insert(Dict dict) {
		int ret = 0;
		ResponseVO rv = new ResponseVO(0);
		dict.setDictId(SessionUtils.getUUID());
		Dict entity = new Dict();
		entity.setDictType(dict.getDictType());
		entity.setDictDkey(dict.getDictDkey());
		List<Dict> dicts = dictService.readByEqualNotNull(entity);
		if (dicts.size() > 0) {
			rv.setRespcode(2);
			rv.setRespmesg("字典记录已经存在！");
			return rv;
		}
		ret = dictService.insert(dict);
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
	@RequiresPermissions("base:dict:update")
	public ResponseVO updateByKey(Dict dict) {
		int ret = 0;
		ResponseVO rv = new ResponseVO(0);
		ret = dictService.updateNotNullByKey(dict);
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
	@RequiresPermissions("base:dict:delete")
	public ResponseVO deleteByKey(String id) {
		int ret = 0;
		ResponseVO rv = new ResponseVO(0);
		ret = dictService.deleteByKey(id);
		if (ret != 1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
		}
		return rv;
	}

	/**
	 * 根据数据字典类型查询该类型的所有数据字典信息
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping("/readByType")
	@ResponseBody
	public List<Dict> readByType(String dictType) {
		return dictService.readByType(dictType);
	}

	/**
	 * <p>
	 * Title: queryDictValue
	 * </p>
	 * <p>
	 * Description: 根据字典的类型和key查询字典的值
	 * </p>
	 * 
	 * @param dictType
	 *            字典类型
	 * @param dictDkey
	 *            字典值
	 * @return
	 */
	public String queryDictValue(String dictType, String dictDkey) {
		String dictValue = "";
		Dict dict = new Dict();
		dict.setDictType(dictType);
		dict.setDictDkey(dictDkey);
		List<Dict> dictList = new ArrayList();
		dictList = dictService.readByEqualNotNull(dict);
		for (Dict d : dictList) {
			dictValue = d.getDictValue();
		}
		return dictValue;
	}
}

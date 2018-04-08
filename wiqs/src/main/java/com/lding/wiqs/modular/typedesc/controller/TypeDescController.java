package com.lding.wiqs.modular.typedesc.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lding.wiqs.general.business.controller.BaseController;
import com.lding.wiqs.general.web.vo.ResponseVO;
import com.lding.wiqs.general.web.vo.TreeVo;
import com.lding.wiqs.modular.dict.controller.DictController;
import com.lding.wiqs.modular.problem.domain.Problem;
import com.lding.wiqs.modular.problem.service.ProblemService;
import com.lding.wiqs.modular.typedesc.domain.TypeDesc;
import com.lding.wiqs.modular.typedesc.service.TypeDescService;
import com.lding.wiqs.utils.SessionUtils;

/**
 * <p>
 * Title: TypeDescController
 * </p>
 * <p>
 * Description: 报表模块_问题分类描述表Controller
 * </p>
 * 
 * @author xixuguang
 * @date 2018年3月29日 下午3:32:11
 *
 */
@Controller
@RequestMapping("/typedesc")
public class TypeDescController extends BaseController {

	@Autowired
	private TypeDescService typeDescService;

	@Autowired
	private ProblemService problemService;
	
	private String pagelist = "type_desc/typeDesc";

	/**
	 * <p>
	 * Title: manage
	 * </p>
	 * <p>
	 * Description: 进入问题分类描述表页面
	 * </p>
	 * 
	 * @return
	 */
	@RequiresPermissions("report:typedesc:read")
	@RequestMapping("/manage")
	public String manage() {
		return pagelist;
	}

	/**
	 * <p>
	 * Title: readAll
	 * </p>
	 * <p>
	 * Description: 查询问题分类描述表树形数据
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/readAll")
	@ResponseBody
	public List<TreeVo> readAll() {
		return trans2Tree(typeDescService.readAllRows(), "0");
	}

	List<TreeVo> trans2Tree(List<TypeDesc> typeDesclist, String pid) {
		List<TreeVo> treeList = new ArrayList<TreeVo>();
		DictController dictController = new DictController();
		for (TypeDesc typeDesc : typeDesclist) {
			if (pid.equals(typeDesc.getPdPid())) {
				TreeVo treeVo = new TreeVo();
				treeVo.setId(typeDesc.getPdId());
				treeVo.setText(typeDesc.getPdName());
				treeVo.setVal0(typeDesc.getPdTypeId());
				//String pdTypeName = dictController.queryDictValue("pdType", typeDesc.getPdTypeId());
				//typeDesc.setPdTypeName(pdTypeName);
				treeVo.setVal1(typeDesc.getPdTypeName());
				treeVo.setVal2(typeDesc.getPdContant());
				treeVo.setVal3(typeDesc.getPdId());

				treeVo.setChildren(trans2Tree(typeDesclist, typeDesc.getPdId()));
				treeList.add(treeVo);
			}
		}
		if (treeList.size() == 0 && (!"0".equals(pid)))
			return null;
		return treeList;
	}

	/**
	 * <p>
	 * Title: queryPdTypeNameName
	 * </p>
	 * <p>
	 * Description: 根据问题分类描述编号查询问题分类描述名称
	 * </p>
	 * 
	 * @param pdId
	 * @return
	 */
	public String queryPdTypeName(String pdId) {
		TypeDesc typeDesc = new TypeDesc();
		typeDesc = typeDescService.selectByKey(pdId);
		return typeDesc.getPdName();
	}

	/**
	 * 查询该类型下的所以问题描述
	 */
	public List<TypeDesc> queryPdTypeNameName(String pdTypeId) {
		TypeDesc typeDesc = new TypeDesc();
		List<TypeDesc> typeDescList = new ArrayList<>();

		typeDesc.setPdTypeId(pdTypeId);
		typeDescList = typeDescService.readByEqualNotNull(typeDesc);
		return typeDescList;
	}

	/**
	 * <p>
	 * Title: insert
	 * </p>
	 * <p>
	 * Description: 插入一条数据
	 * </p>
	 * 
	 * @param typeDesc
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	@RequiresPermissions("report:typedesc:insert")
	public ResponseVO insert(TypeDesc typeDesc) {
		ResponseVO rv = new ResponseVO();
		typeDesc.setPdId(SessionUtils.getUUID());
		List<TypeDesc> typeDescList = typeDescService.readAllRows();
		for(TypeDesc t:typeDescList) {
			if(typeDesc.getPdPid().equals(t.getPdId())) {
				rv.setRespcode(0);
				break;
			}
		}
		int ret = 0;

		if(rv.getRespcode()==0)
			ret = typeDescService.insert(typeDesc);
		if(ret != 1) {
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
	 * Description: 更新一条数据
	 * </p>
	 * 
	 * @param typeDesc
	 * @return
	 */
	@RequestMapping("/updateByKey")
	@ResponseBody
	@RequiresPermissions("report:typedesc:update")
	public ResponseVO updateByKey(TypeDesc typeDesc) {
		ResponseVO rv = new ResponseVO();
		TypeDesc oldTypeDesc = typeDescService.selectByKey(typeDesc.getPdId());
		if (oldTypeDesc == null) {
			rv.setRespcode(1);
			rv.setRespmesg("机构信息不存在");
		} else if (!typeDesc.getPdPid().equals(oldTypeDesc.getPdPid())) {
			if ("0".equals(oldTypeDesc.getPdPid())) {
				rv.setRespcode(2);
				rv.setRespmesg("机构树逻辑错误！");
			} else {
				List<TreeVo> orgTree = trans2Tree(typeDescService.readAllRows(), oldTypeDesc.getPdId());
				if (checkTree(orgTree, oldTypeDesc.getPdPid())) {
					rv.setRespcode(2);
					rv.setRespmesg("机构树逻辑错误！");
				}
			}
		}
		int ret = 0;
		if (rv.getRespcode() == 9)
			ret = typeDescService.updateAllByKey(typeDesc);
		if (ret == 1)
			rv.setRespcode(0);
		return rv;
	}

	private boolean checkTree(List<TreeVo> tree, String pdId) {
		if (tree == null)
			return Boolean.FALSE;
		for (TreeVo tv : tree) {
			if (pdId.equals(tv.getId()))
				return Boolean.TRUE;
			if (checkTree(tv.getChildren(), pdId))
				return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * <p>
	 * Title: deleteByKey
	 * </p>
	 * <p>
	 * Description: 删除一条数据
	 * </p>
	 * 
	 * @param pdId
	 * @return
	 */
	@RequestMapping("/deleteByKey")
	@ResponseBody
	@RequiresPermissions("report:typedesc:delete")
	public ResponseVO deleteByKey(String id) {
		System.out.println("要删除的信息为： " + id);
		ResponseVO rv = new ResponseVO(0);
		if("".equals(id) || id == null) {
			rv.setRespcode(2);
			rv.setRespmesg("数据传递错误！关键数据未接受到！");
			return rv;
		}
		
		int ret = 0;
		
		List<TypeDesc> typeDescList = typeDescService.readAllRows();
		for (TypeDesc t : typeDescList) {
			if (id.equals(t.getPdPid())) {
				rv.setRespcode(2);
				rv.setRespmesg("树逻辑错误！有子节点");
				return rv;
			}
		}
		// 判断是否有关联数据
		Problem problem = new Problem();
		problem.setProblemId(id);
		List<Problem> problemList = problemService.readByEqualNotNull(problem);
		if (problemList != null && problemList.size() > 0) {
			rv.setRespcode(4);
			rv.setRespmesg("有关联信息，无法删除该数据，如要删除请先删除相关依赖！");
			return rv;
		}
		String pdId = id;
		ret = problemService.deleteByKey(pdId);
		if (ret != 1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
		}
		return rv;
	}

}

package com.lding.wiqs.modular.org.controller;

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
import com.lding.wiqs.modular.org.domain.Org;
import com.lding.wiqs.modular.org.service.OrgService;
import com.lding.wiqs.modular.userinfo.domain.UserInfo;
import com.lding.wiqs.modular.userinfo.service.UserInfoService;
import com.lding.wiqs.utils.SessionUtils;


@Controller
@RequestMapping("/org")
public class OrgController extends BaseController {

	@Autowired
	private OrgService orgService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * 进入机构管理页面
	 * @return
	 */
	@RequiresPermissions("base:org:read")
	@RequestMapping("/manage")
	public String manage() {
		return "org/orgPage";
	}
	
	@RequestMapping("/readAll")
	@ResponseBody
	public List<TreeVo> readAll() {
		return trans2Tree(orgService.readAllRows(),"0");
	}
	
	@RequestMapping("/updateByKey")
	@ResponseBody
	@RequiresPermissions("base:org:update")
	public ResponseVO updateByKey(Org org) {
		ResponseVO rv = new ResponseVO();
		Org oldOrg = orgService.selectByKey(org.getOrgId());
		if(oldOrg==null) {
			rv.setRespcode(1);
			rv.setRespmesg("机构信息不存在");
		}else if( !org.getOrgPid().equals(oldOrg.getOrgPid()) ) {
			if("0".equals(oldOrg.getOrgPid())) {
				rv.setRespcode(2);
				rv.setRespmesg("机构树逻辑错误！");
			}else{
				List<TreeVo> orgTree = trans2Tree(orgService.readAllRows(),oldOrg.getOrgId());
				if(checkTree(orgTree, org.getOrgPid())) {
					rv.setRespcode(2);
					rv.setRespmesg("机构树逻辑错误！");
				}
			}
		}
		int ret=0;
		if(rv.getRespcode()==9)
			ret = orgService.updateAllByKey(org);
		if(ret == 1) rv.setRespcode(0);
		return rv;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	@RequiresPermissions("base:org:insert")
	public ResponseVO insert(Org org) {
		ResponseVO rv = new ResponseVO();
		org.setOrgId(SessionUtils.getUUID());
		List<Org> orgList = orgService.readAllRows();
		for(Org o:orgList) {
			if(org.getOrgPid().equals(o.getOrgId())) {
				rv.setRespcode(0);
				break;
			}
		}
		int ret=0;
		
		if(rv.getRespcode()==0)
			ret = orgService.insert(org);
		if(ret != 1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
		}
		return rv;
	}
	
	@RequestMapping("/deleteByKey")
	@ResponseBody
	@RequiresPermissions("base:org:delete")
	public ResponseVO deleteByKey(String orgId) {
		int ret=0;
		ResponseVO rv = new ResponseVO(0);
		List<Org> orgList = orgService.readAllRows();
		for(Org o:orgList) {
			if(orgId.equals(o.getOrgPid())) {
				rv.setRespcode(2);
				rv.setRespmesg("机构树逻辑错误！有子节点");
				return rv;
			}
		}
		UserInfo userInfo = new UserInfo();
		userInfo.setOrgId(orgId);
		List<UserInfo> users = userInfoService.readByEqualNotNull(userInfo);
		if( users!=null && users.size()>0 ) {
			rv.setRespcode(4);
			rv.setRespmesg("有关联用户，请先清理用户，后删除机构");
			return rv;
		}
		ret = orgService.deleteByKey(orgId);
		if(ret != 1) {
			rv.setRespcode(8);
			rv.setRespmesg("数据库更新失败");
		}
		return rv;
	}
	
	private boolean checkTree(List<TreeVo> tree,String orgId) {
		if(tree==null) return Boolean.FALSE;
		for(TreeVo tv:tree) {
			if(orgId.equals(tv.getId())) return Boolean.TRUE;
			if(checkTree(tv.getChildren(), orgId)) return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	List<TreeVo> trans2Tree(List<Org> orglist,String pid) {
		List<TreeVo> treeList = new ArrayList<TreeVo>();
		for(Org org:orglist) {
			if( pid.equals(org.getOrgPid())) {
				TreeVo treeVo = new TreeVo();
				treeVo.setId(org.getOrgId());
				treeVo.setText(org.getOrgName());
				treeVo.setVal0(org.getOrgNo());
				treeVo.setVal1(org.getOrgType());
				treeVo.setVal2(org.getContant());
				treeVo.setVal3(org.getOrgPid());

				treeVo.setChildren(trans2Tree(orglist,org.getOrgId()));
				treeList.add(treeVo);
			}
		}
		if(treeList.size() == 0 && ( ! "0".equals(pid) ) ) return null; 		
		return treeList;	
	}
	
	/**
	 * <p>Title: queryrgName</p>  
	 * <p>Description: 根据机构id查询机构名称</p>  
	 * @param orgId
	 * @return
	 */
	public String queryOrgName(String orgId) {
		Org org = new Org();
		org = orgService.selectByKey(orgId);
		return org.getOrgName();
	}
}

package com.lding.wiqs.general.shiro.utils;

import java.util.ArrayList;
import java.util.List;

import com.lding.wiqs.general.web.vo.TreeVo;
import com.lding.wiqs.modular.permission.domain.Permission;

public class TreeVoHelper {

	public static List<TreeVo> transMenuToTree(String pid, List<Permission> menus) {
		List<TreeVo> menuTree = new ArrayList<TreeVo>();
		if (menus != null) {
			for (Permission pm : menus) {
				if (pid.equals(pm.getParentid())) {
					TreeVo singleMenu = new TreeVo();
					singleMenu.setText(pm.getPerName());
					singleMenu.setAttributes(pm.getPerUrl());
					singleMenu.setId(pm.getPerId());
					singleMenu.setPid(pm.getParentid());
					singleMenu.setChildren(transMenuToTree(pm.getPerId(), menus));
					menuTree.add(singleMenu);
				}
			}
		}
		if (menuTree.size() == 0 && (!"0".equals(pid))) {
			return null;
		}
		return menuTree;
	}

	public static boolean checkTree(List<TreeVo> tree, String id) {
		if (tree == null) {
			return Boolean.FALSE;
		}
		for (TreeVo tv : tree) {
			if (id.equals(tv.getId())) {
				return Boolean.TRUE;
			}
			if (checkTree(tv.getChildren(), id)) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
}

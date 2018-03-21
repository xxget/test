package com.lding.wiqs.general.web.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 返回前台的树，easyui 的 tree , combotree , datagridtree 可以解析显示
 * @author yangling
 *
 */
public class TreeVo implements Serializable {

	private static final long serialVersionUID = -7893869153366783541L;
	
	public static String CLOSE = "closed";
	public static String OPEN = "open";
	public String id;
	public String pid;
	public String text;
	public String state; //open or close ,是否有子节点（是否可以展开？）
	public String check; //选择框，是否选中
	public Object attributes;
	public List<TreeVo> children;//子节点，可以不用一次性生成树
	public String val0;
	public String val1;
	public String val2;
	public String val3;
	public String val4;
	public String val5;
	public String val6;	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public Object getAttributes() {
		return attributes;
	}
	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}
	public List<TreeVo> getChildren() {
		return children;
	}
	public void setChildren(List<TreeVo> children) {
		this.children = children;
	}
	public String getVal0() {
		return val0;
	}
	public void setVal0(String val0) {
		this.val0 = val0;
	}
	public String getVal1() {
		return val1;
	}
	public void setVal1(String val1) {
		this.val1 = val1;
	}
	public String getVal2() {
		return val2;
	}
	public void setVal2(String val2) {
		this.val2 = val2;
	}
	public String getVal3() {
		return val3;
	}
	public void setVal3(String val3) {
		this.val3 = val3;
	}
	public String getVal4() {
		return val4;
	}
	public void setVal4(String val4) {
		this.val4 = val4;
	}
	public String getVal5() {
		return val5;
	}
	public void setVal5(String val5) {
		this.val5 = val5;
	}
	public String getVal6() {
		return val6;
	}
	public void setVal6(String val6) {
		this.val6 = val6;
	}
	
	public static List<IdTextVo> transTree2Map(List<TreeVo> tree) {
		//List<IdTextVo> list = new ArrayList<>();这里可能有jdk1.8和jdk1.7区别的问题。
		List<IdTextVo> list = new ArrayList<IdTextVo>();
		for(TreeVo tv:tree) {
			IdTextVo itv = new IdTextVo();
			itv.setId(tv.getId());
			itv.setText(tv.getText());
			list.add(itv);
			if(tv.getChildren() != null) {
				List<IdTextVo> subList = transTree2Map(tv.getChildren());
				for(IdTextVo stv:subList) {
					list.add(stv);
				}
			}
		}
		return list;
	}
}

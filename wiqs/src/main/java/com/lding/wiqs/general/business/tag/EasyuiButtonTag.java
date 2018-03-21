package com.lding.wiqs.general.business.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * JSP标签库，自动生成按钮
 * @author yanglingsx
 *
 */
public class EasyuiButtonTag  extends SimpleTagSupport implements DynamicAttributes {

	String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 根据传入的参数  type 自动生成easyui按钮
	 */
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		if(type==null||"all".equals(type)) {
			out.println("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-add\"  plain=\"true\" onclick=\"javascript:openCreateForm();\">增加</a>");
			out.println("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-edit\"   plain=\"true\" onclick=\"javascript:openEditForm();\">修改</a>");
			out.println("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-delete\" plain=\"true\" onclick=\"javascript:deleteObject();\">删除</a>");
		}else if("add".equals(type)){
			out.println("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-add\"  plain=\"true\" onclick=\"javascript:openCreateForm();\">增加</a>");
		}else if("edit".equals(type)){
			out.println("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-edit\"   plain=\"true\" onclick=\"javascript:openEditForm();\">修改</a>");
		}else if("delete".equals(type)){
			out.println("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-delete\" plain=\"true\" onclick=\"javascript:deleteObject();\">删除</a>");
		}
		return;
	}
	

	@Override
	public void setDynamicAttribute(String uri, String name, Object value) throws JspException {
	}

}

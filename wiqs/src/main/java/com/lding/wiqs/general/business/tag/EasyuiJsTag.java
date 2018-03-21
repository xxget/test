package com.lding.wiqs.general.business.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class EasyuiJsTag  extends SimpleTagSupport implements DynamicAttributes {

	public void doTag() throws JspException, IOException {
		String queryUrl="urlBasePath+'/dict/selectByWhere.do'";
		
		JspWriter out = getJspContext().getOut();
		out.println("<script type=\"text/javascript\">");
//--dagatrid 编辑窗体部分-含分页		
		out.println("$('#showData').datagrid({");
		//需动态生成内容-000-url
		out.println("	url	: "+queryUrl+",");
		out.println("	toolbar : '#toolsbar',");
		out.println("	idField:'id',");
		out.println("	columns:[[");
		//需动态生成内容-001-column
		out.println("		{title:'ID',field:'id',checkbox:'true'},");
		out.println("		{title:'字典类型',field:'type'},");
		out.println("		{title:'字典键值',field:'dkey'},");
		out.println("		{title:'字典值',field:'value'},");
		out.println("		{title:'说明',field:'text'},");
		//success-001
		out.println("	]],");
		out.println("	singleSelect:true,");
		out.println("	pagination:true,");
		out.println("	striped:'true',");
		out.println("	loadMsg:'正在加载数据',");
		out.println("});");
		
		out.println("$('#showData').datagrid('getPager').pagination({");
		out.println("    pageSize:10,");
		out.println("    pageList:[10,20,30],");
		out.println("beforePageText:'第',");
		out.println("afterPageText:'页       共{pages}页',");
		out.println("displayMsg:'当前显示{from}-{to}条记录      共{total}条记录',");
		out.println("});");

//-打开新建窗体 editForm
		out.println("function openCreateForm(){");
		//需动态生成内容-002-增加数据的默认值-读取
		out.println("if($('#showData').datagrid('getSelected')){");
		out.println("var selectRow = $('#showData').datagrid('getSelected');$('#updateType').val(selectRow.type);}");
		//--success-002
		out.println("$('#editForm').dialog({");
		//需动态生成内容-003--窗体title
		out.println("title:'增加',");
		//--success-003
		out.println("buttons:[{text:'保存',iconCls:'icon-ok',handler:function() {createObject();}},{text:'取消',iconCls:'icon-cancel',handler:function() {$('#editForm').dialog('close');$('#editf').form('reset');}},],");
		out.println("});");
		out.println("$('#editForm').dialog('open');");
		out.println("}");
//-打开编辑窗体-editForm

		out.println("function openEditForm(){");
		out.println("	if($('#showData').datagrid('getSelected')){");
		out.println("		var selectRow = $('#showData').datagrid('getSelected');");
		out.println("		$('#updateId').val(selectRow.id);");
		//需动态生成内容-004-增加数据的默认值-读取
		out.println("		$('#updateType').val(selectRow.type);");
		out.println("		$('#updateKey').val(selectRow.dkey);");
		out.println("		$('#updateValue').val(selectRow.value);");
		out.println("		$('#updateText').val(selectRow.text);");
		//success-004
		out.println("		$('#editForm').dialog({");
		out.println("			title:'编辑',");
		out.println("			buttons:[{text:'保存',iconCls:'icon-ok',handler:function() {updateObject();}},{text:'取消',iconCls:'icon-cancel',handler:function() {$('#editForm').dialog('close');$('#editf').form('reset');}},],});'");
		out.println("		$('#editForm').dialog('open');");
		out.println("	}else{$.messager.alert({title:'提示',msg:'请先选择要操作的对象',icon:'error',});}}");

//保存新增记录		
		out.println("function createObject() {");
		out.println("	if( ! $('#editf').form('validate') ) {");
		out.println("  	}else {");
		out.println(" 		$.ajax({");
		//-个性化url
		out.println("			url : urlBasePath+'/dict/insert.do',");
		out.println("			headers:{Accept:\"application/json;charset=utf-8\"},");
		out.println("			type : 'post',");
		out.println("			data : {");
		//--个性化提交数据
		out.println("				type : $('#updateType').val(),");
		out.println("				dkey : $('#updateKey').val(),");
		out.println("				value : $('#updateValue').val(),");
		out.println("				text : $('#updateText').val(),");
		//-end
		out.println("			},");
		out.println("			beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},");
		out.println("			success	: function (result) {");
		out.println("				$.messager.progress('close');");
		out.println("				showTips(result.respmesg);");
		out.println("				if (result.respcode==0) {");
		out.println("					$('#showData').datagrid('reload');");
		out.println("					$('#editForm').dialog('close');$('#editf').form('reset');");
		out.println("				}},});//ajax	 ");
		out.println("	}}");

		out.println("function updateObject() {");
		out.println("	if( ! $('#editf').form('validate') ) {");
		out.println("  	}else {$.ajax({");
		//-个性化url
		out.println("			url : urlBasePath+'/dict/insert.do',");
		out.println("			headers:{Accept:\"application/json;charset=utf-8\"},");
		out.println("			type : 'post',");
		out.println("			data : {");
		//--个性化提交数据
		out.println("				id : $('#updateId').val(),");
		out.println("				type : $('#updateType').val(),");
		out.println("				dkey : $('#updateKey').val(),");
		out.println("				value : $('#updateValue').val(),");
		out.println("				text : $('#updateText').val(),");
		out.println("			},");
		out.println("			beforeSend	: function () {$.messager.progress({test:'系统处理中，请等待......',});},");
		out.println("			success	: function (result) {");
		out.println("				$.messager.progress('close');");
		out.println("				showTips(result.respmesg);");
		out.println("				if (result.respcode==0) {");
		out.println("					$('#showData').datagrid('reload');");
		out.println("					$('#editForm').dialog('close');$('#editf').form('reset');");
		out.println("				}},});//ajax");
		out.println("	}}");		
		
		out.println("</script>");
	}
	
	@Override
	public void setDynamicAttribute(String uri, String name, Object value) throws JspException {
	}

}

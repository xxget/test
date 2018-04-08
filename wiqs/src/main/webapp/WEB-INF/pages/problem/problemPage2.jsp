<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/include/baseEnv.jsp"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<%@ include file="/WEB-INF/pages/include/title.jsp"%>
<title>${projname }-${mlenterprise.name }-问题管理</title>
</head>
<body class="easyui-layout">
	<div
		data-options="region:'center',title:'问题管理',iconCls:'icon-edit_user'">
		<table id="showData" class="easyui-datagrid">
		</table>
	</div>
	<div id="toolsbar">
		<div>
			上报人: <input id="selectReportUser" name="reportUser" class="easyui-input" style="width: 80px"> 
			问题类型: <input id="selectProblemType" name="reportUser" class="easyui-input" style="width: 80px"> 
			<!-- <select id="selectProblemType" name="problemType" class="easyui-combobox" panelHeight="auto" style="width: 100px">
				<option value="1">漏气</option>
				<option value="2">隐患</option>
			</select>  -->
			<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="submitButton()">查 询</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="resetButton()">重 置</a>
		</div>
		<shiro:hasPermission name="base:dict:insert">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
				onclick="javascript:openCreateForm();">增加</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="base:dict:update">
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
				plain="true" onclick="javascript:openEditForm()">编辑</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="base:dict:delete">
			<a href="#" class="easyui-linkbutton" iconCls="icon-delete"
				plain="true" onclick="javascript:deleteObject()">删除</a>
		</shiro:hasPermission>
	</div>
	<div id="editForm" class="easyui-dialog" title="修改信息"
		style="width: 550px; height: 300px; padding: 5px 0 0 25px; margin: 0px;"
		data-options="
			iconCls:'icon-org',
			closed:true,
		">
		<form id="editf" method="post">
			<table>
				<tr>
					<th valign="top">* 字典名</th>
					<td><input id="updateType" class="easyui-validatebox"
						data-options="required:true,validType:'length[1,10]',missingMessage:'请输入',invalidMessage:'长度6-10位英文字母'" />
						<p></p> <input id="updateId" type='hidden' /></td>
				</tr>
				<tr>
					<th valign="top">* 键:</th>
					<td><input id="updateKey" class="easyui-validatebox"
						data-options="required:true,validType:'length[1,10]',missingMessage:'请输入',invalidMessage:'长度6-10位英文字母'" />
						<p></p></td>
				</tr>
				<tr>
					<th valign="top">* 值:</th>
					<td><input id="updateValue" class="easyui-validatebox"
						data-options="required:true,validType:'length[1,32]',missingMessage:'请输入',invalidMessage:'长度6-10位英文字母'" />
						<p></p></td>
				</tr>
				<tr>
					<th valign="top">* 说明:</th>
					<td><input id="updateText" class="easyui-validatebox"
						data-options="required:true,validType:'checkCEN[2,30]',missingMessage:'请输入真说明',invalidMessage:'长度1-30位中英文'" />
						<p></p></td>
				</tr>
			</table>
		</form>
	</div>

	<%@ include file="/WEB-INF/pages/include/inc_js.jsp"%>
	<script type="text/javascript">
		var urlBasePath = "${path}";
	</script>
	<script type="text/javascript" src="${webrs }/js/pages/problem/problemPage.js"></script>
</body>
</html>
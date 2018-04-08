<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/include/baseEnv.jsp"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<%@ include file="/WEB-INF/pages/include/title.jsp"%>
<title>${projname }-${mlenterprise.name }-问题分类描述</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',title:'问题分类描述信息',iconCls:'icon-edit_user'">
		<table id="typeDescTree">
		</table>
	</div>
	<div id="struTool">
		<shiro:hasPermission name="base:org:insert"><a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#createForm').dialog('open');">增加</a></shiro:hasPermission> 
		<shiro:hasPermission name="base:org:update"><a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:editOrg()">编辑</a> </shiro:hasPermission>
		<shiro:hasPermission name="base:org:delete"><a href="#" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="javascript:deleteOrg()">删除</a> </shiro:hasPermission>
	</div>
	<div id="createForm" class="easyui-dialog" title="新增问题" style="width:550px;height:300px;padding:5px 0 0 25px;margin:0px;" 
		data-options="
			iconCls:'icon-org',
			buttons:[
				{text:'保存',iconCls:'icon-ok',handler:function() {createTypeDesc();}},
				{text:'取消',iconCls:'icon-cancel',handler:function() {$('#createForm').dialog('close');$('#createf').form('reset');}},
			],
			closed:true,
		">
		<form id="createf" method="post">
			<table>
				<tr>
	              <th valign="top">* 问题描述名称:</th>
	              <td><input id="createPdName" class="easyui-validatebox" data-options="required:true,validType:'checkCEN[2,20]',missingMessage:'请输入机构名称',invalidMessage:'长度2-20位中文'" />
	              	<p>2-8个汉字，请输入机构名称。</p><input id="createId" type='hidden'/></td>			
				</tr>
				<tr>
	              <th valign="top">* 问题类型:</th>
	              <td><select id="createPdTypeId" class="easyui-combobox" style="width:160px;"
	              	data-options="url:'${path}/dict/readByType.do?dictType=pdType',valueField:'dictDkey',textField:'dictValue'," />
	              </td>
	            </tr>
				<tr>
	              <th valign="top">* 上级问题:</th>
	              <td><select id="createPdPid" class="easyui-combotree" style="width:200px;"
	              	data-options="url:'${path}/typedesc/readAll.do'"/></td>
				</tr>
				<tr>
	              <th valign="top">* 备注/联系方式:</th>
	              <td><input id="createPdContant" calss="easyui-validatebox" style="width:200px;"/></td>
				</tr>
	        </table>
	</div>
	
	<div id="editForm" class="easyui-dialog" title="修改机构信息" style="width:550px;height:300px;padding:5px 0 0 25px;margin:0px;" 
		data-options="
			iconCls:'icon-org',
			buttons:[
				{text:'保存',iconCls:'icon-ok',handler:function() {updateOrg();}},
				{text:'取消',iconCls:'icon-cancel',handler:function() { $('#editForm').dialog('close');}},
			],
			closed:true,
		">
		<form id="editf" method="post">
			<table>
				<input id="updatePdId" type="hidden">
				<tr>
	              <th valign="top">* 问题描述名称:</th>
	              <td><input id="updatePdName" class="easyui-validatebox" data-options="required:true,validType:'checkCEN[2,20]',missingMessage:'请输入机构名称',invalidMessage:'长度2-20位中文'" />
	              	<p>2-8个汉字，请输入机构名称。</p><input id="updateId" type='hidden'/></td>			
				</tr>
				<tr>
	              <th valign="top">* 问题类型:</th>
	              <td><select id="updatePdTypeId" class="easyui-combobox" style="width:160px;"/>
	              </td>
	            </tr>
				<tr>
	              <th valign="top">* 上级问题:</th>
	              <td><select id="updatePdPid" class="easyui-combotree" style="width:200px;"/></td>
				</tr>
				<tr>
	              <th valign="top">* 备注/联系方式:</th>
	              <td><input id="updatePdContant" calss="easyui-validatebox" style="width:200px;"/></td>
				</tr>
	        </table>
        </form>
	</div>
<%@ include file="/WEB-INF/pages/include/inc_js.jsp"%>
<script type="text/javascript">
	var urlBasePath = "${path}";
</script>
<script type="text/javascript" src="${webrs }/js/pages/type_desc/typeDesc.js"></script>
</body>
</html>
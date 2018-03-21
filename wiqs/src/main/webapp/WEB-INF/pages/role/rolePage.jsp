<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/include/baseEnv.jsp"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<%@ include file="/WEB-INF/pages/include/title.jsp"%>
<title>${projname }-${mlenterprise.name }-角色管理</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',title:'角色信息',iconCls:'icon-edit_user'">
		<table id="showData">
		</table>
	</div>
	<div id="toolsbar">
		<shiro:hasPermission name="base:role:insert"><a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:openCreateForm();">增加</a></shiro:hasPermission> 
		<shiro:hasPermission name="base:role:update"><a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:openEditForm()">编辑</a> </shiro:hasPermission>
		<shiro:hasPermission name="base:role:delete"><a href="#" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="javascript:deleteObject()">删除</a> </shiro:hasPermission>
	</div>
	<div id="editForm" class="easyui-dialog" title="修改信息" style="width:550px;height:300px;padding:5px 0 0 25px;margin:0px;" 
		data-options="
			iconCls:'icon-org',
			closed:true,
		">
		<form id="editf" method="post">
			<table>
				<tr>
	              <th valign="top">* 角色名称</th>
	              <td><input id="updateName" class="easyui-validatebox" data-options="required:true,validType:'checkCEN[2,15]',missingMessage:'请输入',invalidMessage:'长度2-15位中英文'" />
	              	<p></p><input id="updateId" type='hidden'/></td>			
				</tr>
				<tr>
	              <th valign="top">* 权限列表:</th>
	              <td><select id="updatePermissionList" class="easyui-combotree" style="width:200px;"
	              	data-options="checkbox:true,multiple:true,url:'${path}/permission/readAllAsTree.do'"/></td>
				</tr>
				<tr>
	              <th valign="top">* 锁定:</th>
	              <td><select id="updateLocked" class="easyui-combobox" style="width:160px;"
	              	data-options="url:'${path}/dict/readByType.do?dictType=locktype',valueField:'dictDkey',textField:'dictValue'," /></td>
				</tr>
	        </table>
        </form>
	</div>

<%@ include file="/WEB-INF/pages/include/inc_js.jsp"%>
<script type="text/javascript">
	var urlBasePath = "${path}";
</script>
<script type="text/javascript" src="${webrs }/js/pages/role/rolePage.js"></script>
</body>
</html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/include/baseEnv.jsp"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<%@ include file="/WEB-INF/pages/include/title.jsp"%>
<title>${projname }-${mlenterprise.name }-权限资源管理</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',title:'权限资源信息',iconCls:'icon-edit_user'">
		<table id="showData" style="height: 100%">
		</table>
	</div>
	<div id="toolsbar">
		<shiro:hasPermission name="base:permission:insert"><a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:openCreateForm();">增加</a></shiro:hasPermission> 
		<shiro:hasPermission name="base:permission:update"><a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:openEditForm()">编辑</a> </shiro:hasPermission>
		<shiro:hasPermission name="base:permission:delete"><a href="#" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="javascript:deleteObject()">删除</a> </shiro:hasPermission>
	</div>
	<div id="editForm" class="easyui-dialog" title="修改信息" style="width:550px;height:400px;padding:5px 0 0 25px;margin:0px;" 
		data-options="
			iconCls:'icon-org',
			closed:true,
		">
		<form id="editf" method="post">
			<table>
				<tr>
	              <th valign="top">* 权限/菜单名</th>
	              <td><input id="updateName" class="easyui-validatebox" data-options="required:true,validType:'checkCEN[2,15]',missingMessage:'请输入',invalidMessage:'长度2-15位中英文'" />
	              	<p></p><input id="updateId" type='hidden'/></td>			
				</tr>
				<tr>
	              <th valign="top">* 类型:</th>
	              <td><select id="updateType" class="easyui-combobox" style="width:160px;"
	              	data-options="url:'${path}/dict/readByType.do?dictType=restype',valueField:'dictDkey',textField:'dictValue'," />
	              </td>			
				</tr>
				<tr>
	              <th valign="top">* WEB路径:</th>
	              <td><input id="updateUrl" class="easyui-validatebox" data-options="validType:'length[0,32]',missingMessage:'请输入',invalidMessage:'长度0-32位英文字母符号'" />
	              	<p></p></td>
				</tr>
				<tr>
	              <th valign="top">* 权限描述串:</th>
	              <td><input id="updatePercode" class="easyui-validatebox" data-options="validType:'length[0,32]',missingMessage:'请输入',invalidMessage:'长度0-32位英文字母'" />
	              	<p>Shiro定义的描述符</p></td>
				</tr>
	              <th valign="top">* 上级菜单节点:</th>
	              <td><select id="updateParentid" class="easyui-combotree" style="width:200px;"
	              	data-options="url:'${path}/permission/readAllAsTree.do'"/></td>
				</tr>
	              <th valign="top">* 排序串:</th>
	              <td><input id="updateSortstring" class="easyui-validatebox" data-options="required:true,validType:'length[2,32]',missingMessage:'请输入',invalidMessage:'长度2-32位英文字母'" />
	              	<p></p></td>
				</tr>
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
<script type="text/javascript" src="${webrs }/js/pages/permission/perPage.js"></script>
</body>
</html>
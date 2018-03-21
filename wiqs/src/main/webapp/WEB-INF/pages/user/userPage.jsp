<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/include/baseEnv.jsp"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<%@ include file="/WEB-INF/pages/include/title.jsp"%>
<title>${projname }-${mlenterprise.name }-用户管理</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',title:'用户信息',iconCls:'icon-edit_user'">
		<table id="userData" style="height: 100%">
		</table>
	</div>
	<div id="toolsbar">
		<shiro:hasPermission name="base:user:insert"><a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:openCreateForm();">增加</a></shiro:hasPermission> 
		<shiro:hasPermission name="base:user:update"><a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:openEditForm()">编辑</a> </shiro:hasPermission>
		<shiro:hasPermission name="base:user:delete"><a href="#" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="javascript:deleteObject()">删除</a> </shiro:hasPermission>
	</div>
	<div id="editForm" class="easyui-dialog" title="修改用户信息" style="width:550px;height:300px;padding:5px 0 0 25px;margin:0px;" 
		data-options="
			iconCls:'icon-org',
			closed:true,
		">
		<form id="editf" method="post">
			<table>
				<tr>
	              <th valign="top">* 登陆名:</th>
	              <td><input id="updateUsername" class="easyui-validatebox" data-options="required:true,validType:'length[6,20]',missingMessage:'请输入登陆名',invalidMessage:'长度6-20位英文字母'" />
	              	<p>登陆名。</p><input id="updateId" type='hidden'/></td>			
				</tr>
				<tr>
	              <th valign="top">* 真实姓名:</th>
	              <td><input id="updateRealname" class="easyui-validatebox" data-options="required:true,validType:'checkCEN[2,8]',missingMessage:'请输入真实姓名',invalidMessage:'长度2-20位中文'" />
	              	<p>姓名。</p></td>			
				</tr>
				<tr>
	              <th valign="top">* 所属机构:</th>
	              <td><select id="updateOrgid" class="easyui-combotree" style="width:200px;" data-options="url:'${path}/org/readAll.do'" /></td>
				</tr>
				<tr>
	              <th valign="top">* 联系方式:</th>
	              <td><input id="updatePhone" calss="easyui-validatebox" style="width:200px;"/></td>
				</tr>
				<tr>
	              <th valign="top">* 角色列表:</th>
	              <td><select id="updateRoleList" class="easyui-combobox" style="width:200px;"
	              	data-options="checkbox:true,multiple:true,url:'${path}/role/readAll.do',valueField:'roleId',textField:'roleName',"/></td>
				</tr>
	        </table>
        </form>
	</div>
<%@ include file="/WEB-INF/pages/include/inc_js.jsp"%>
<script type="text/javascript">
	var urlBasePath = "${path}";
</script>
<script type="text/javascript" src="${webrs }/js/pages/userinfo/userPage.js"></script>
</body>
</html>
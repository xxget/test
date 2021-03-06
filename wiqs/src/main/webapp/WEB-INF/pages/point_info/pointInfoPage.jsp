<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/include/baseEnv.jsp"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<%@ include file="/WEB-INF/pages/include/title.jsp"%>
<title>${projname }-${mlenterprise.name }-设置位置点编号</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',title:'位置点信息',iconCls:'icon-edit_user'">
		<table id="showData">
		</table>
	</div>
	<div id="toolsbar">
		<shiro:hasPermission name="resource:pointinfo_test:insert"><a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:openCreateForm();">增加</a></shiro:hasPermission> 
		<shiro:hasPermission name="resource:pointinfo_test:update"><a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:openEditForm()">编辑</a></shiro:hasPermission> 
		<shiro:hasPermission name="resource:pointinfo_test:delete"><a href="#" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="javascript:deleteObject()">删除</a></shiro:hasPermission>
	</div>
	<div id="editForm" class="easyui-dialog" title="修改信息" style="width:550px;height:300px;padding:5px 0 0 25px;margin:0px;" 
		data-options="
			iconCls:'icon-org',
			closed:true,
		">
		<form id="editf" method="post">
			<table>
				<tr>
	              <th valign="top">* 位置点编号</th>
	              <td><input id="updatePointId" class="easyui-validatebox" data-options="required:true,validType:'length[1,10]',missingMessage:'请输入',invalidMessage:'长度6-10位英文字母'" />
	              	<p></p><input id="updateId" type='hidden'/></td>			
				</tr>
				<tr>
	              <th valign="top">* 位置点信息:</th>
	              <td><input id="updatePointInfo" class="easyui-validatebox" data-options="required:true,validType:'length[1,10]',missingMessage:'请输入',invalidMessage:'长度6-10位英文字母'" />
	              	<p></p></td>				
				</tr>
				<tr>
	              <th valign="top">* 经度改:</th>
	              <td><input id="updateLngs" class="easyui-validatebox" data-options="required:true,validType:'length[1,32]',missingMessage:'请输入',invalidMessage:'长度6-10位英文字母'" />
	              	<p></p></td>
				</tr>
				<tr>
	              <th valign="top">* 纬度改:</th>
	              <td><input id="updateLats" class="easyui-validatebox" data-options="required:true,validType:'checkCEN[2,30]',missingMessage:'请输入真说明',invalidMessage:'长度1-30位中英文'" />
	              	<p></p></td>
				</tr>
	        </table>
        </form>
	</div>

<%@ include file="/WEB-INF/pages/include/inc_js.jsp"%>
<script type="text/javascript">
	var urlBasePath = "${path}";
</script>
<script type="text/javascript" src="${webrs }/js/pages/point_info/pointInfoPage.js"></script>
</body>
</html>
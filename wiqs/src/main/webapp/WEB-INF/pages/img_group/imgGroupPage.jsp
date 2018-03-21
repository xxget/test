<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/include/baseEnv.jsp"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<%@ include file="/WEB-INF/pages/include/title.jsp"%>
<title>${projname }-${mlenterprise.name }-图片资源组管理</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',title:'字典信息',iconCls:'icon-edit_user'">
		<table id="showData">
		</table>
	</div>
	<div id="toolsbar">
		<shiro:hasPermission name="resource:imggroup_test:insert"><a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:openCreateForm();">增加</a></shiro:hasPermission> 
		<shiro:hasPermission name="resource:imggroup_test:update"><a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:openEditForm()">编辑</a></shiro:hasPermission> 
		<shiro:hasPermission name="resource:imggroup_test:delete"><a href="#" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="javascript:deleteObject()">删除</a></shiro:hasPermission>
	</div>
	<div id="editForm" class="easyui-dialog" title="修改信息" style="width:550px;height:300px;padding:5px 0 0 25px;margin:0px;" 
		data-options="
			iconCls:'icon-org',
			closed:true,
		">
		<form id="editf" method="post">
			<table>
				<tr>
	              <th valign="top">* 图片标题</th>
	              <td><input id="updateImgTitle" class="easyui-validatebox" data-options="required:true,validType:'length[1,10]',missingMessage:'请输入',invalidMessage:'长度6-10位英文字母'" />
	              	<p></p><input id="updateId" type='hidden'/></td>			
				</tr>
				<tr>
	              <th valign="top">* 图片类型:</th>
	              <td><input id="updateImgType" class="easyui-validatebox" data-options="required:true,validType:'length[1,10]',missingMessage:'请输入',invalidMessage:'长度6-10位英文字母'" />
	              	<p></p></td>				
				</tr>
				<tr>
	              <th valign="top">* 图片地址:</th>
	              <td><input id="updateImgAddress" class="easyui-validatebox" data-options="required:true,validType:'length[1,32]',missingMessage:'请输入',invalidMessage:'长度6-10位英文字母'" />
	              	<p></p></td>
				</tr>
				<tr>
	              <th valign="top">* 图片信息:</th>
	              <td><input id="updateImgInfo" class="easyui-validatebox" data-options="required:true,validType:'checkCEN[2,30]',missingMessage:'请输入真说明',invalidMessage:'长度1-30位中英文'" />
	              	<p></p></td>
				</tr>
	        </table>
        </form>
	</div>

<%@ include file="/WEB-INF/pages/include/inc_js.jsp"%>
<script type="text/javascript">
	var urlBasePath = "${path}";
</script>
<script type="text/javascript" src="${webrs }/js/pages/img_group/imgGroupPage.js"></script>
</body>
</html>
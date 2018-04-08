<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/include/baseEnv.jsp"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<%@ include file="/WEB-INF/pages/include/title.jsp"%>
<title>${projname }-${mlenterprise.name }-APP版本管理</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',title:'APP版本信息',iconCls:'icon-edit_user'">
		<table id="showData">
		</table>
	</div>
	<div id="toolsbar">
		<shiro:hasPermission name="base:appversion:insert"><a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:openCreateForm();">增加</a></shiro:hasPermission> 
		<shiro:hasPermission name="base:appversion:delete"><a href="#" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="javascript:deleteObject()">删除</a></shiro:hasPermission>
	</div>
	<div id="editForm" class="easyui-dialog" title="修改信息" style="width:550px;height:300px;padding:5px 0 0 25px;margin:0px;" 
		data-options="
			iconCls:'icon-org',
			closed:true,
		">
		<form id="editf" method="post">
			<table>
				<tr>
	              <th valign="top">* 版本号</th>
	              <td><input id="updateVersionCode" class="easyui-validatebox" data-options="required:true,validType:'length[1,32]',missingMessage:'请输入',invalidMessage:'长度1-32位英文字母'" />
	              	<p></p><input id="id" type='hidden'/></td>			
				</tr>
				<tr>
	              <th valign="top">版本信息:</th>
	              <td><input id="updateVersionInfo" class="easyui-validatebox" data-options="required:true,validType:'length[1,60]',missingMessage:'请输入',invalidMessage:'长度1-60位英文字母'" />
	              	<p></p></td>				
				</tr>
				<tr>
	              <th valign="top">下载路径:</th>
	              <td><input id="updateAppUrl" class="easyui-validatebox" data-options="required:true,validType:'length[1,300]',missingMessage:'请输入',invalidMessage:'长度1-300位英文字母'" />
	              	<p></p></td>
				</tr>
	        </table>
        </form>
	</div>

<%@ include file="/WEB-INF/pages/include/inc_js.jsp"%>
<script type="text/javascript">
	var urlBasePath = "${path}";
</script>
<script type="text/javascript" src="${webrs}/js/pages/appVersion/appVersionPage.js"></script>
</body>
</html>
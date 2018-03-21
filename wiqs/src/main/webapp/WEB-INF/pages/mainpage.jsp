<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/include/baseEnv.jsp"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<%@ include file="/WEB-INF/pages/include/title.jsp"%>
<title>${projname }-主页</title>
<link rel="stylesheet" type="text/css" href="${webrs }/css/pages/mainpage.css">
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:70px;background:#B3DFDA;padding:10px">
		<div class="logo">
			${projname}
		</div>
		<div class="logout">
			您好：${mluser.userName } | <a href="#" onClick="javascript:$('#passwordform').dialog('open');">改密</a> | <a href="${path }/logout.do">退出</a>
		</div>
	</div>
	<!-- 导航部分开始 -->
	<div data-options="region:'west',split:true,title:'导航',iconCls:'icon-world'" style="width:150px;padding:10px;">
		<ul id="nav"></ul>
	</div>
	<!-- 导航部分结束 -->
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;align:'center';"></div>
	<div data-options="region:'center'">
		<div id="tabs" class="easyui-tabs" fit="true" border="false" data-options="fit:true,plain:true">
			<div title="首页" data-options="iconCls:'icon-home'">欢迎</div>
		</div>
	</div>
	<div id="passwordform" class="easyui-dialog" title="用户密码修改" style="width:550px;height:400px;padding:5px 0 0 25px;margin:0px;" 
		data-options="
			iconCls:'icon-edit_user',
			modal:true,
			closed:true,
			buttons:[
				{text:'修改',iconCls:'icon-ok',handler:function() {savePassword();}},
				{text:'取消',iconCls:'icon-cancel',handler:function() {cancelPass();}},
			]
		">
		<form id="passf" action="">
		<table>
			<tr>
              <th valign="top">* 旧密码:</th>
              <td><input id="oldpassword" size="30" type="password" class="easyui-validatebox"  data-options="required:true,validType:'length[1,40]',missingMessage:'请输入密码',invalidMessage:'要输入旧密码哦！'" /><p/></td>
            </tr>
			<tr>
              <th valign="top">* 新密码:</th>
              <td><input id="password" size="30" type="password" class="easyui-validatebox"  data-options="required:true,validType:'simplepassword',missingMessage:'请输入密码'" />
                <p>请选用安全的密码，不允许使用只包含纯数字，纯字母的密码</p></td>
            </tr>
            <tr>
              <th>* 确认密码:</th>
              <td><input id="password_confirmation" size="30" type="password" class="easyui-validatebox"  data-options="required:true,validType:'equals[\'#password\']',missingMessage:'请输入密码',invalidMessage:'两次密码输入需一致'" /></td>
            </tr>		
        </table>
        </form>
	</div>
<%@ include file="/WEB-INF/pages/include/inc_js.jsp"%>
<script type="text/javascript">
	var urlpath = "${path}";
</script>
<script type="text/javascript" src="${webrs }/js/pages/mainpage.js"></script>
</body>
</html>
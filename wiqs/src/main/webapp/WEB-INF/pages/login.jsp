<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/include/baseEnv.jsp"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<%@ include file="/WEB-INF/pages/include/title.jsp"%>
<title>${projname }-用户登录</title>
</head>
<body class="easyui-layout" align="center">
	<div style="margin-top: 10% ; border-radius: 10px; border: 2px solid #6699cc; " align="center">
		    <h3>${projname }-用户登录</h3>
	    <form  action="${path}/login.do" method="post" style="padding:10px 20px 10px 40px;">
			<p>
				帐&nbsp;&nbsp;&nbsp;&nbsp;号<img alt="" src="${path}/static/easyui/themes/icons/edit_user.png">
				<input type="text" name="userName" placeholder="请输入您的账号" class="easyui-validatebox" data-options="required:true,validType:'length[5,16]',missingMessage:'请输入账号',invalidMessage:'长度5-16位字符！'">
			</p>
			<p>
				密&nbsp;&nbsp;&nbsp;&nbsp;码<img alt="" src="${path}/static/easyui/themes/icons/key.png">
				<input type="password" name="password" placeholder="请输入您的密码" class="easyui-validatebox" data-options="required:true,validType:'length[5,16]',missingMessage:'请输入密码',invalidMessage:'长度5-16位字符！'">
			</p>
			<p>
				验证码<img alt="" src="${path}/static/easyui/themes/icons/lock.png">
				<input type="text" name="validateCode" placeholder="验证码" class="easyui-validatebox" data-options="required:true,validType:'length[4,4]',missingMessage:'请输入验证码',invalidMessage:'长度为4位的验证码！'">
			</p>
			<p>
				<img id="code" alt="点击获取验证码" src="${path}/validatecode.do">
			</p>
			<div style="padding:5px;text-align:center;">
				<input type="submit" class="easyui-linkbutton" value="登&nbsp;&nbsp;&nbsp;陆" style="margin:10px; padding: 10px;">
			</div>
		</form>
	 
	</div>

<%@ include file="/WEB-INF/pages/include/inc_js.jsp"%>
<script type="text/javascript">
	var urlBasePath = "${path}";
	
	$.each(data,function(){
		alert(this.PsId);
	});
	alert(data);
</script>
<script src="${webrs}/js/canvas-nest.js" count="200" zindex="-2" opacity="0.5" color="47,135,193" type="text/javascript"></script>
<script type="text/JavaScript">
	$(function() {
		$("#code").click(function() {
			$(this).attr("src", "${path}/validatecode.do?time=" + new Date());
		});
	})
</script>
</body>
</html>
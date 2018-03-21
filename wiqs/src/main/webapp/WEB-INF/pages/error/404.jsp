<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/include/baseEnv.jsp"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<%@ include file="/WEB-INF/pages/include/title.jsp"%>
<title>${projname }-ERROR500</title>
<link rel="stylesheet" type="text/css" href="${webrs }/css/mainpage.css">
</head>
<body class="easyui-layout">
	<div id="errorWin" class="easyui-window" title="系统错误"
		style="width: 600px; height: 400px;"
		data-options="iconCls:'icon-error',modal:false">
		<P class="errorTitle">系统错误</P>
		<p class="errorMessage">错误信息：${msg }</p>
	</div>
	<%@ include file="/WEB-INF/pages/include/inc_js.jsp"%>


</body>
</html>

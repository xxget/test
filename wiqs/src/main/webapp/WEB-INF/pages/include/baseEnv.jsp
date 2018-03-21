<!-- 页面常量,参数及路径配置 -->
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
	request.setAttribute("projname","管线宝");
	request.setAttribute("webrs", path+"/static");
	request.setAttribute("easyui", path+"/static/easyui");
%>

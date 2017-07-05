<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="com.newdemo.HelloBean" %>
<%
	//request.setCharacterEncoding("gb2312");
%>
<jsp:useBean id="hello" class="com.newdemo.HelloBean" scope="request">
	<jsp:setProperty name="hello" property="*"/>
</jsp:useBean>
<html>
<head>
<title>HelloBean</title>
<meta http-equiv="Content-Type" content="text/html;charset=gb2312">
</head>
<body>
	欢迎
	<jsp:getProperty property="name" name="hello"/>
	<jsp:getProperty property="sex" name="hello"/>
</body>
</html>
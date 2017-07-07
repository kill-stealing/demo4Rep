<%@page import="com.listener.MyBean"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>感知Session绑定的综合案例</title>
</head>
<body>
<%
	System.out.println("当前session对象的ID号为"+session.getId());
	session.setAttribute("myBean", new MyBean());
	//session.removeAttribute("myBean");
%>
</body>
</html>
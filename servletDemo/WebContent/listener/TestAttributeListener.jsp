<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<h4>
这是一个测试对象属性信息监听器的页面
</h4>
<%
	getServletContext().setAttribute("username","it315");
	getServletContext().setAttribute("username","zxx");
	getServletContext().removeAttribute("username");
	session.setAttribute("username", "it315");
	session.setAttribute("username","zxx");
	session.removeAttribute("username");
	request.setAttribute("username","it315");
	request.setAttribute("username","zxx");
	request.removeAttribute("username");
%>
</body>
</html>
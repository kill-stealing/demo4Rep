<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%
	session.invalidate();
	request.getRequestDispatcher("logon.jsp").forward(request, response);
%>
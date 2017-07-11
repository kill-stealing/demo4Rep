<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%
	RequestDispatcher rd=request.getRequestDispatcher("NewFile.html");
	rd.forward(request, response);
	//response.sendRedirect("NewFile.html");
 %>

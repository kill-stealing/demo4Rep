<%@ page language="java" contentType="text/html; charset=gb2312"%>
<% 
	float v1=Float.parseFloat(request.getParameter("value1"));
	float v2=Float.parseFloat(request.getParameter("value2"));
	float result=v1*v2;
%>

�����<%=result %>
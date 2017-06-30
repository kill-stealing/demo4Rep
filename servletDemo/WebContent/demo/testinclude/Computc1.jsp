<%@ page language="java" contentType="text/html; charset=gb2312"%>
<% 
	String value1=request.getParameter("value1");
	String value2=request.getParameter("value2");
%>
<% 
	if(request.getParameter("compute").equals("division")){%>
		<jsp:include page="divide.jsp" flush="true">
			<jsp:param name="v1" value="<%=value1 %>"/>
			<jsp:param name="v2" value="<%=value2 %>"/>
		</jsp:include>
<%		
	}else{%>
	<%@ include file="multiply.jsp" %>
	
<% } %>
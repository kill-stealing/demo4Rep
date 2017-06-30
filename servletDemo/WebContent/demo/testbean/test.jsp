<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="com.jsp.CounterBean" %>

<%-- 
<%! int i=0;
%>
<% CounterBean cb=new CounterBean();%>

<font color="red" size="5" >
<%=cb.getCounter() %>
<%=++i %>
</font>
--%>

<jsp:useBean id="cb" class="com.jsp.CounterBean">
</jsp:useBean>

<jsp:setProperty name="cb" property="count" value="23" />
<jsp:getProperty name="cb" property="count" />
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String token=(String)request.getSession().getAttribute("token"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<form action="DoFormServlet" method="post">
	<input type="hidden" name="token" value="<%=token%>" />
	<input type="text" name="param1" />
	<input type="submit" value="提交" >
</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()
	+request.getContextPath()+"/"; %>
<base href='<%=basePath+"logon.jsp" %>' />
<% 
	//如果当前会话的用户已经登录
	if(session.getAttribute("logonUser")!=null){
	%>
	您已经登录，请先<a href="/servletDemo/filter/logout.jsp">注销</a>后重新登录！<br>
	<%
		return;
	}
%>

<%
	//如果jsp页面的当前执行过程是对表单提交的响应
	if(request.getParameter("submit")!=null){
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		if("zxx".equals(userName)&&"123".equals(password)){
			session.setAttribute("logonUser", userName);
			String viewPage=(String)session.getAttribute("viewPage");
			if(viewPage==null){
				viewPage="/filter/articles.html";
			}
		%>
		<jsp:forward page="<%=viewPage%>" />
		<%
		}else{
			%>
			用户名或密码不正确！<br>
			<%
		}
	}
%>

请先登录：
<form action="" method="post">
	姓名：<input type="text" name="userName" /><br>
	密码：<input type="password" name="password" /><br>
	<input type="submit" value="登录" name="submit" />
</form>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()
	+request.getContextPath()+"/"; %>
<base href='<%=basePath+"logon.jsp" %>' />
<% 
	//�����ǰ�Ự���û��Ѿ���¼
	if(session.getAttribute("logonUser")!=null){
	%>
	���Ѿ���¼������<a href="/servletDemo/filter/logout.jsp">ע��</a>�����µ�¼��<br>
	<%
		return;
	}
%>

<%
	//���jspҳ��ĵ�ǰִ�й����ǶԱ��ύ����Ӧ
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
			�û��������벻��ȷ��<br>
			<%
		}
	}
%>

���ȵ�¼��
<form action="" method="post">
	������<input type="text" name="userName" /><br>
	���룺<input type="password" name="password" /><br>
	<input type="submit" value="��¼" name="submit" />
</form>
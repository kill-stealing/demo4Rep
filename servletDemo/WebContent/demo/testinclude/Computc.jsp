<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>

<form action="Computc1.jsp" method="post">
	<p>ѡ��Ҫ��������
	<input type="radio" name="compute" value="division" checked />����
	<input type="radio" name="compute" value="multiplication" />�˷�
	</p>
	<p>
		������(������):<input type="text" name="value1" />
		����(����):<input type="text" name="value2" />
	</p>
	<p>
		<input type="submit" name="Submit" value="������" />
	</p>
	
</form>

</body>
</html>
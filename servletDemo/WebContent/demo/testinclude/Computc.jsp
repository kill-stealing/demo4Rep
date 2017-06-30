<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>

<form action="Computc1.jsp" method="post">
	<p>选择要做的运算
	<input type="radio" name="compute" value="division" checked />除法
	<input type="radio" name="compute" value="multiplication" />乘法
	</p>
	<p>
		被除数(被乘数):<input type="text" name="value1" />
		除数(乘数):<input type="text" name="value2" />
	</p>
	<p>
		<input type="submit" name="Submit" value="计算结果" />
	</p>
	
</form>

</body>
</html>
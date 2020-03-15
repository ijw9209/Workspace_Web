<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<a href="hello.do?command=hello">hello</a>
	<form action="hello.do" method="post">
		<input type="submit" value="hellopost">
	</form>
	
	<a href="controller.do?command=hello">hello2</a>
	
	<a href="./hello.do">hello3</a>
</body>
</html>
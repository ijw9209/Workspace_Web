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

	<form action="controller.jsp" method="post">
	<input type="hidden" name="command" value="login">
	<table border="1">
		<tr>
			<th>아이디</th>
			<td><input type="text" name="id"></td>
		</tr>	
		<tr>
			<th>비밀번호</th>
			<td><input type="text" name="pw"></td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="submit" value="login">
			<input type="button" value="regist" onclick="location.href='controller.jsp?command=regist'">
		</tr>
	
	
	</table>
	</form>

</body>
</html>
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
	<form action="my.do" method="post">
	<input type="hidden" name="command" value="insertres">
		<table border="1">
			<tr>		
				<th>작성자</th>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>		
				<th>제목</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>		
				<th>내용</th>
				<td><textarea cols="50" rows="10" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="1">
				<input type="submit" value="확인">
				<input type="button" value="취소" onclick="location.href='my.do?command=list'">
			</tr>
		
		
		
		</table>
	</form>

</body>
</html>
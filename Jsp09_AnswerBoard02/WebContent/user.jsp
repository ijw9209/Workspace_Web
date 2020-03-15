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

	<h1>${dto.myname }님의 정보</h1>
	
	<table border="1">
		
		<tr>
			<th>아이디</th>
			<td>${dto.myid }</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${dto.myname }</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${dto.myphone }</td>
		</tr>
		<tr>
			<td colspan="3">
			<input type="button" value="게시글 보기" onclick="location.href='answer.do?command=list'">
			</td>
		</tr>
		
	
	
	</table>

</body>
</html>
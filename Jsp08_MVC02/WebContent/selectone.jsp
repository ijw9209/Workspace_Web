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

	<h1>select one</h1>
	
	<table border="1">
		<tr>
			<th>작성자</th>
			<td>${dto.writer }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${dto.title }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60">${dto.content }</textarea>
		</tr>
		
		<tr>
			<td colspan="3">
			<input type="button" value="수정" onclick="location.href='update?seq=${dto.seq}'">
			<input type="button" value="삭제" onclick="location.href='delete?seq=${dto.seq}'">
			<input type="button" value="목록" onclick="location.href='selectall'">
			</td>
		</tr>
	
	
	
	</table>

</body>
</html>
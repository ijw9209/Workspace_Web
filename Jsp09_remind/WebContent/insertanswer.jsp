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
<form action="answer.do" method="post">
		<input type="hidden" name="command" value="insertanswerres">
		<input type="hidden" name="parentsboardno" value="${dto.boardno }">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="50" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="3">
				<input type="submit" value="작성">
				<input type="button" value="취소" onclick="location.href='answer.do?command=selectone&boardno=${dto.boardno}'">
			</tr>
		</table>
	</form>

</body>
</html>
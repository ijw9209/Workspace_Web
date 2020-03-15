<%@page import="com.my.dto.MyBoardDto"%>
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
<%
	MyBoardDto dto = (MyBoardDto)request.getAttribute("dto");
%>
<body>

	<form action="my.do" method="post">
		<input type="hidden" name="command" value="updateres">
		<input type="hidden" name="myno" value="<%=dto.getMyno()%>">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><%=dto.getMyname() %></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="<%=dto.getMytitle()%>"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="50" name="content"><%=dto.getMycontent() %></textarea>
			</tr>
			
			<tr>
				<td colspan="1">
				<input type="submit" value="수정">
				<input type="button" value="취소" onclick="location.href='my.do?command=selectone&myno=<%=dto.getMyno() %>'">
			</tr>
			
		</table>
	</form>


</body>
</html>
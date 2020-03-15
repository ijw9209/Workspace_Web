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
	
	<table border="1">
		<tr>
			<th>이름</th>
			<td><%=dto.getMyname() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=dto.getMytitle()%></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="50" readonly="readonly"><%=dto.getMycontent() %></textarea></td>
		</tr>
		
		<tr>
			<td colspan="1">
			<input type="button" value="수정" onclick="location.href='my.do?command=update&myno=<%=dto.getMyno()%>'">
			<input type="button" value="삭제" onclick="location.href='my.do?command=delete&myno=<%=dto.getMyno()%>'">
			<input type="button" value="목록" onclick="location.href='my.do?command=list'">
		</tr>
	
	
	
	</table>
</body>
</html>
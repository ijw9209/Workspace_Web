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
<%
	MyBoardDto dto = (MyBoardDto)request.getAttribute("dto");

%>
</head>
<body>

	<h1>수정</h1>
	<form action="my.do" method="get">
		<input type="hidden" name="command" value="updateres">
		<input type="hidden" name="myno" value="<%=dto.getMyno() %>">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><%=dto.getMyname() %></td>
			</tr>		
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="<%=dto.getMytitle() %>"></td>
			</tr>	
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="content"><%=dto.getMycontent() %></textarea></td>
			</tr>	
			<tr>	
				<td colspan="2">
				<input type="submit" value="수정">
				<input type="button" value="취소" onclick="location.href='my.do?command=selectone&myno=<%=dto.getMyno()%>'">
				</td>
			</tr>
		
		</table>
	</form>

</body>
</html>
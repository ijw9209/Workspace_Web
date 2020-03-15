<%@page import="com.login.dto.MyBoardDto"%>
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
	MyBoardDto dto = (MyBoardDto)session.getAttribute("dto");
%>
</head>
<body>

	<form action="controller.jsp" method="post">
		<input type="hidden" name="command" value="updateres">
		<input type="hidden" name="myno" value="<%=dto.getMyno() %>">
		<table border="1">
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="pw" value="<%=dto.getMypw()%>"/></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="addr" value="<%=dto.getMyaddr()%>"/></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="phone" value="<%=dto.getMyphone() %>"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email" value="<%=dto.getMyemail()%>"></td>
			</tr>
			<tr>
				<td colspan="2">			
				<input type="submit" value="수정">
				<input type="button" value="취소" onclick="location.href='controller.jsp?command=userinfo'">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>
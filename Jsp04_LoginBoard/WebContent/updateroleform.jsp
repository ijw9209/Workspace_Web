<%@page import="com.login.dto.MemberDto"%>
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

<%
	MemberDto dto = (MemberDto)request.getAttribute("dto");
%>


	<h1>회원등급 변경</h1>
	
	<form action="logincontroller.jsp" method="post">
		<input type="hidden" name="command" value="updateroleres">
		<input type="hidden" name="myno" value="<%=dto.getMyno() %>">
		<table border="1">
			<tr>
				<th>번호</th>
				<td><%=dto.getMyno() %></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><%=dto.getMyname() %></td>
			</tr>
			<tr>
				<th>등급</th>
				<td>
					<select name="role">
						<option value="USER" <%=dto.getMyrole().equals("USER")?"seleted":"" %>>일반회원</option>
						<option value="ADMIN"><%=dto.getMyrole().equals("ADMIN")?"selectd":"" %>관리자</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정">
					<input type="button" value="취소" onclick="">	
				</td>
			</tr>
		</table>	
	</form>
</body>
</html>
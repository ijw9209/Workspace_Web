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
</head>
<%
	MyBoardDto dto = (MyBoardDto)session.getAttribute("dto");
%>
<body>
<table border="1">
		<tr>
			<th>아이디</th>
			<td><%=dto.getMyid() %></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><%=dto.getMypw() %></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=dto.getMyname() %></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><%=dto.getMyaddr() %></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><%=dto.getMyphone() %></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%=dto.getMyemail() %></td>
		</tr>
		<tr>
			<td colspan="3">
			<input type="button" value="정보수정" onclick="location.href='controller.jsp?command=update'">
			<input type="button" value="탈퇴" onclick="location.href='controller.jsp?myno=<%=dto.getMyno()%>&command=delete'">
			<input type="button" value="유저메인" onclick="location.href='controller.jsp?command=usermain'">
			</td>
		</tr>
	
	</table>

</body>
</html>
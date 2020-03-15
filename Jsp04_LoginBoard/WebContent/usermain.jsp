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
<%
	//sendredirect 했는데도 값을 가져올수있음session이라서
	MemberDto dto = (MemberDto)session.getAttribute("dto");
%>
<body>

	<h1><%=dto.getMyname() %>님, 환영합니다.<%=dto.getMyrole() %></h1>
	<a href="logincontroller.jsp?command=logout">logout</a>
	
	<div>
		<a href="logincontroller.jsp?id=<%=dto.getMyid() %>&command=userinfo">내 정보 보기</a>
	</div>
	
</body>
</html>
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

	<h1>user 로켓단<%=dto.getMyname() %>님 환영합니당</h1>
	
	<a href="controller.jsp?command=logout">logout</a>
	
	<div>
		<a href="controller.jsp?command=userinfo">내정보보기</a>
	</div>
</body>
</html>
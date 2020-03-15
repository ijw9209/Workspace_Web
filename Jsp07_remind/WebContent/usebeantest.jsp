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
	
	<h1>use bean test</h1>

	<jsp:useBean id="sc" class="com.el.dto.Score" scope="session"></jsp:useBean>
	
	<jsp:setProperty property="name" name="sc" value="홍길동"/>
	<jsp:setProperty property="kor" name="sc" value="100"/>
	<jsp:setProperty property="eng" name="sc" value="77"/>
	<jsp:setProperty property="math" name="sc" value="60"/>
	
	<jsp:getProperty property="sum" name="sc"/>
	<jsp:getProperty property="avg" name="sc"/>
	<jsp:getProperty property="grade" name="sc"/>
	
	<button onclick="location.href='res.jsp'"></button>
</body>
</html>
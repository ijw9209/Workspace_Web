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
		${sc.name }
		<jsp:useBean id="sc" scope="session" class="com.el.dto.Score"></jsp:useBean>
		
		<jsp:getProperty property="grade" name="sc"/>
</body>
</html>
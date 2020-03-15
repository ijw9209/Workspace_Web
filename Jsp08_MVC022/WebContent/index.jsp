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
	<a href="selectall">list..</a>
	
	<a href="selectall">목록</a><br/>
	<!-- 루트로 잡으면 톰캣서버 최상위로 잡음 -->
	<a href="/selectall">목록2</a><br/>
	<a href="./selectall">목록3</a><br/>
	<!-- request.getContextPath() 프로젝트의 실제경로를 알려줌 -->
	<a href="<%=request.getContextPath() %>/selectall">목록4</a><br/>
</body>
</html>
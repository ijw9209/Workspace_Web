<%
	//뒤로가기눌렀을때 세션유지되는거 막기
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-control","no-store");
	response.setHeader("Expirse", "0");
	/*
	데이터가 변경되었을 때,이전 내용을 화면에 보여주는 이유
	=> 서버값이 아닌 캐시에 저장된 내용을 출력.
	브라우저가 캐시에 응답결과를 저장하지않도록 설정
	response.setHeader("Pargma", "no-cache");		//http1.0
	response.setHeader("Cache-control","no-store");	//http1.1
	response.setHeader("Expires", "0");				//proxy server
	
	*/
%>
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
	MemberDto dto = (MemberDto)session.getAttribute("dto");
	
	if(dto == null){
		pageContext.forward("index.jsp");
	}
%>
<body>

	<h1><%=dto.getMyname()%>님 환영합니다</h1>
	<a href="logincontroller.jsp?command=logout">logout</a>
	
	<div>
		<a href="logincontroller.jsp?command=userlistall">회원정보 전체 조회</a>
	</div>	<!-- y면 가입 아니면 탈퇴 -->
	<div>
		<a href="logincontroller.jsp?command=enabledylist">회원정보 조회(ENABLED=Y)</a>
	</div>
	
	

</body>
</html>
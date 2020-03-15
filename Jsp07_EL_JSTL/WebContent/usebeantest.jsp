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

	<h1>jsp:usebean을 통한 객체 생성</h1>
	
	<!-- id : JSP페이지에서 자바빈 객체에 접근 할 때 사용하는 이름이다.
	class : 패키지 이름을 포함한 자바빈 클래스의 완전한 이름을 입력
	scope : 자바빈 객체가 저장될 영역을 지정. page, request, session, application 중 하나를 값으로 갖는다. 기본값은 page 
	-->

	<!-- 객체로 만들었다 -->
	<jsp:useBean id="sc" class="com.el.dto.Score" scope="session"></jsp:useBean>
	<!-- java beans : 객체 
		Score id =>sc = new Score();
	-->
	<jsp:setProperty property="name" name="sc" value="홍길동"/>
	<jsp:setProperty property="kor" name="sc" value="100"/>
	<jsp:setProperty property="eng" name="sc" value="50"/>
	<jsp:setProperty property="math" name="sc" value="77"/>
	<!-- set : setter 호출 property="name" value="홍길동" : sc.setName("홍길동") 호출 -->
	
	총점 : <jsp:getProperty property="sum" name="sc"/><br/>
	평균 : <jsp:getProperty property="avg" name="sc"/><br/>
	등급 : <jsp:getProperty property="grade" name="sc"/><br/>
	<!-- get : getter 호출 property="sum" sc.getSum() -->
	
	<button onclick="location.href='res.jsp'">res</button>
</body>
</html>
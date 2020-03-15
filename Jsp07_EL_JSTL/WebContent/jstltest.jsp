<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8"); %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>JSTL TEST</h1>
	<!-- JSP Standard Tag Library -->
	
	<table border="1">
		<tr>
			<th>이름</th>
			<th>국어</th>
			<th>영어</th>
			<th>수학</th>
			<th>총점</th>
			<th>평균</th>
			<th>등급</th>
		</tr>
		<!--위에 taglib prefix="c"  
		jspl 의 for문-->
		<c:forEach items="${list }" var="score">
			<tr>
				<td>
					<c:if test="${score.name eq '이름10'}">
						<!--  c:out 객체 변수의 값을 출력한다.  eq (==)비교하고자 하는 값이 동일한지를 확인할때 사용한다.
						-->
						<c:out value="홍길동"></c:out>
					</c:if>
					<!-- choose안에다 주석처리하면안딤 -->
					<c:choose>
						<c:when test="${score.name eq '이름20'}">
							<c:out value="${score.name }님"></c:out>
						</c:when>
						<c:when test="${score.name eq '이름30'}">
							<c:out value="${score.name }님"></c:out>
						</c:when>
						<c:otherwise>
							<c:out value="누구지?"></c:out>
						</c:otherwise>		
					</c:choose>	
				</td>
				<td>${score.kor }</td>
				<td> 
				<c:choose>
					<c:when test="${(score.eng ge '80')}">
						<c:out value="${score.eng}멋져"></c:out>
					</c:when>
					<c:otherwise>
						<c:out value="${score.eng}"></c:out>
					</c:otherwise>
				</c:choose>
				 </td>
				<td>${score.math }</td>
				<td>${score.sum }</td>
				<td>${score.avg }</td>
				<td>
				<c:choose>
				<c:when test="${(score.grade eq 'A' ||score.grade eq 'B')}">
						<c:out value="PASS"></c:out>
				</c:when>
				<c:otherwise>
						<c:out value="FAIL"></c:out>
				</c:otherwise>
				</c:choose>	
				</td>
			</tr>
		</c:forEach>	
		
		
		<!-- 1.영어점수가 80점 이상이면 점수 멋져 -->
		<!-- 등급이 A이거나 B이면 PASS 아니면 FAIL -->
	</table>
	
	<c:forEach var ="i" begin="1" end="10" step="1">
		${i }<br/>
	</c:forEach>
	
	<table border="1">
	<c:forEach var ="i" begin="1" end="9" step="1">
		<tr>
			<td>${i}단</td>
		<c:forEach var="j" begin="1" end="9" step="1">
			<td>${i}*${j} = ${i * j}</td>
		</c:forEach>
		</tr>
	</c:forEach>
	</table>

</body>
</html>
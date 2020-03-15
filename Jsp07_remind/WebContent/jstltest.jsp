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
			<th>수학</th>
			<th>영어</th>
			<th>총점</th>
			<th>평균</th>
			<th>등급</th>
		</tr>
		
		<c:forEach items="${list }" var="score">
			<tr>
				<td>
				<c:if test="${score.name eq '번호10' }">
					<c:out value="홍길동"></c:out>
				</c:if>
				<c:choose>
					<c:when test="${score.name eq '번호20' }">
						<c:out value="${score.name }님"></c:out>
					</c:when>
					<c:when test="${score.name eq '번호30' }">
						<c:out value="${score.name }님"></c:out>
					</c:when>
					<c:otherwise>
						<c:out value="누구지?"></c:out>
					</c:otherwise>
				</c:choose>
				
				</td>
				<td>${score.kor }</td>
				<td>${score.math }</td>
				<td>
				<c:choose>
				<c:when test="${score.eng >= 80 }">
					<c:out value="${score.eng } 멋져"></c:out>
				</c:when>	
				<c:otherwise>
					<c:out value="${score.eng }"></c:out>
				</c:otherwise>
				</c:choose>
				</td>
				<td>${score.sum }</td>
				<td>${score.avg }</td>
				<td>
				<c:choose>
				<c:when test="${score.grade eq 'A' ||score.grade eq 'B'}">
					<c:out value="Pass"></c:out>
				</c:when>
				<c:otherwise>
					<c:out value="fail"></c:out>
				</c:otherwise>
				</c:choose>
				</td>
			</tr>
		</c:forEach>
	<!-- 1.영어점수가 80점 이상이면 점수 멋져 -->
	<!-- 등급이 A이거나 B이면 PASS 아니면 FAIL -->
	
	
	</table>
	
	<table border="1">
	
	<c:forEach var="i" begin="1" end="9" step="1">
		<tr>
			<td>${i }단</td>
			<c:forEach var="j" begin="1" end="9" step="1">
				<td>${i }*${j }=${i * j}	</td>
				
			</c:forEach>
		</tr>
	</c:forEach>
	
	</table>
	
</body>
</html>
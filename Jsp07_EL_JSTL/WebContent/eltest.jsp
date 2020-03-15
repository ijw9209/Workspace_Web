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
	<!-- \${} 가 찾아줌 scope로  -->
	<h1>${score.name} 님의 성적표</h1>
	
	<table border="1">
		<tr>
			<th>국어</th>
			<td>${score.kor}</td>
		</tr>
		<tr>
			<th>영어</th>
			<td>${score.eng}</td>
		</tr>
		<tr>
			<th>수학</th>
			<td>${score.math}</td>
		</tr>
		<tr>
			<th>총점</th>
			<!--  GET이 붙어있는애들을 찾아줄수있다 -->
			<td>${score.sum}</td>
		</tr>
		<tr>
			<th>평균</th>
			<td>${(score.kor + score.eng + score.math) / 3}</td>
		</tr>
		<tr>
			<th>등급</th>
			<td>${((score.kor + score.eng + score.math) / 30 >= 9)?"A":
			((score.kor + score.eng + score.math) / 30 >= 8)?"B":
			((score.kor + score.eng + score.math) / 30 >= 7)?"C":
			((score.kor + score.eng + score.math) / 30 >= 6)?"D":"F"}</td>
		</tr>
	
	</table>
</body>
</html>
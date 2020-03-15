<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8"); %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table border = "1">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<c:choose>
			<c:when test="${empty list }">
		<tr>
			<td>----------------작성된 글이 없습니다==========</td>
		</tr>
			</c:when>
		<c:otherwise>
			<c:forEach items="${list }" var="dto">
			<tr>
				<td>${dto.boardno }</td>
				<td>
				<c:forEach begin="1" end="${dto.titletab }">
						<c:out value="RE: "></c:out> 
				</c:forEach>
				<a href="answer.do?command=selectone&boardno=${dto.boardno }">${dto.title }</a>
				</td>
				<td>${dto.writer }</td>
				<td>${dto.regdate }</td>
			</tr>
		</c:forEach>
		</c:otherwise>
		</c:choose>
	</table>

</body>
</html>
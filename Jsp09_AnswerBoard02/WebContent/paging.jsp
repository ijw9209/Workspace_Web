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

<h1>글 목록</h1>
	<table border = "1">
		<col width="100">
		<col width="300">
		<col width="100">
		<col width="100">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<c:choose>
			
			<c:when test="${empty list }">
				<tr>
					<td colspan="4">==========작성된글이없음--------------------</td>
				</tr>	
			</c:when>
			<c:otherwise>
				<c:forEach items="${list }" var="dto">
				<tr>
					<td>${dto.rownum}</td>
					<td>
						<c:choose>
							<c:when test="${dto.delflag eq 'Y' }">
								<c:out value="-------------삭제된 글 입니다------------"></c:out>
							</c:when>
							<c:otherwise>
								<c:forEach begin="1" end="${dto.titletab }">
										&nbsp;								
								</c:forEach>
								<a href="answer.do?command=selectone&boardno=${dto.boardno}">${dto.title }</a>
							</c:otherwise>
						</c:choose>
						
					</td>
					<td>${dto.writer }</td>
					<td>${dto.regdate }</td>
				</tr>
			</c:forEach>
			</c:otherwise>
		</c:choose>
			<tr>
				<td colspan="4">
					<input type="button" value="자동글생성" onclick="location.href='answer.do?command=insert'">
				</td>
			</tr>
			<tr>
			<td colspan="5">
			<c:set var="prevPage" value="${absolutePage-blockCount}"></c:set>
			<c:choose>
				<c:when test="${prevPage >0}">
					<a href="answer.do?command=paging&page=${prevPage }">◀</a>
				</c:when>
				<c:otherwise>
					<a href="answer.do?command=paging&page=1">◀</a>
				</c:otherwise>
			</c:choose>
			<c:forEach begin="${absolutePage }" end="${endPage }" var="i">
				<c:choose>
				<c:when test="${i eq page}">
					<a href="answer.do?command=paging&page=${i}"><strong>[${ i }]</strong></a>
				</c:when>
				<c:otherwise>
					<a href="answer.do?command=paging&page=${i}">[${ i }]</a>
				</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:set var="nextPage" value="${absolutePage+blockCount }"></c:set>
			<c:choose>
				<c:when test="${nextPage < totalPage}">
					<a href="answer.do?command=paging&page=${nextPage }">▶</a>
				</c:when>
				<c:otherwise>
					<a href="answer.do?command=paging&page=${totalPage}">▶</a>
				</c:otherwise>
			</c:choose>
			</td>
			</tr>
		
	</table>
</body>
</html>
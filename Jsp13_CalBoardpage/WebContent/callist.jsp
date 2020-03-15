<%@page import="com.cal.dto.CalDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script type="text/javascript">

	function allChk(bool){
		$("input[name=chk]").each(function(){
			$(this).prop("checked",bool);
		});
	}
</script>
</head>

<%
	int year = Integer.parseInt(request.getParameter("year"));
	int month = Integer.parseInt(request.getParameter("month"));
	int date = Integer.parseInt(request.getParameter("date"));
%>
<body>
	
		<h1>일정 목록 보기</h1>
	<form action="cal.do" method="post">
		<input type="hidden" name="command" value="muldel">
		<jsp:useBean id="util" class="com.cal.dao.Util"/>
	<table border="1">
		<col width="50px"/>
		<col width="50px"/>
		<col width="300px"/>
		<col width="200px"/>
		<col width="100px"/>
		<tr>
			<th><input type="checkbox" name="all" onclick="allChk(this.checked);"></th>
			<th>번호</th>
			<th>제목</th>
			<th>일정</th>
			<th>작성일</th>		
		</tr>
		<c:choose>
			<c:when test="${empty list}">
				<tr>
					<td colspan="5">=======일정이 없습니다==================</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list }" var="dto">
					<tr>
						<td><input type="checkbox" name="chk" value="${dto.seq }"></td>
						<td>${dto.seq }</td>
						<td><a href="cal.do?command=detail&seq=${dto.seq }">${dto.title }</a></td>
						<td>
							<jsp:setProperty property="toDates" name="util" value="${dto.mdate }"/>
							<jsp:getProperty property="toDates" name="util"/>
						</td>
						<td><fmt:formatDate value="${dto.regdate }" pattern="yyyy:MM:dd"/>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="5">
				<input type="submit" value="삭제">
				<input type="button" value="돌아가기" onclick="location.href='cal.do?command=calendar'">
			</td>	
		</tr>
		<tr>
		<td colspan="4">
		<c:set var="prevPage" value="${absolutePage - blockCount }"></c:set>
		<c:choose>
			<c:when test="${prevPage > 0}">
				<a href="cal.do?command=callist&page=${prevPage }&year=<%=year%>&month=<%=month%>&date=<%=date%>">◀</a>
			</c:when>
			<c:otherwise>
				<a href="cal.do?command=callist&page=1&year=<%=year%>&month=<%=month%>&date=<%=date%>">◀</a>			
			</c:otherwise>
		</c:choose>
		<c:forEach begin="${absolutePage }" end="${endPage }" var="i">
			<c:choose>
			<c:when test="${i eq page }">
				<a href="cal.do?command=callist&page=${i }&year=<%=year%>&month=<%=month%>&date=<%=date%>"><strong>[${i }]</strong></a>
			</c:when>
			<c:otherwise>
				<a href="cal.do?command=callist&page=${i }&year=<%=year%>&month=<%=month%>&date=<%=date%>">[${i }]</a>			
			</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:set var="nextPage" value="${absolutePage + blockCount }"></c:set>
		<c:choose>
			<c:when test="${nextPage < totalPage }">
				<a href="cal.do?command=callist&page=${nextPage }&year=<%=year%>&month=<%=month%>&date=<%=date%>">▶</a>
			</c:when>
			<c:otherwise>
				<a href="cal.do?command=callist&page=${totalPage }&year=<%=year%>&month=<%=month%>&date=<%=date%>">▶</a>
			</c:otherwise>
		</c:choose>
		</td>
		</tr>
	</table>
	
	</form>
</body>
</html>
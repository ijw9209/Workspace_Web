<%@page import="com.mvc.dto.MVCBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	MVCBoardDto dto = (MVCBoardDto)request.getAttribute("dto");
%>
</head>
<body>
	
	<table border="1">
		<tr>
			<th>작성자</th>
			<td><%=dto.getWriter() %></td>	
		</tr>
		<tr>
			<th>제목</th>
			<td><%=dto.getTitle() %></td>	
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="50"><%=dto.getContent() %></textarea></td>	
		</tr>
		
		<tr>	
			<td colspan="3">
			<input type="button" value="수정" onclick="location.href='mycontroller.jsp?seq=<%=dto.getSeq()%>&command=updateform'"/>
			<input type="button" value="삭제" onclick="location.href='mycontroller.jsp?seq=<%=dto.getSeq()%>&command=deleteform'"/>
			<input type="button" value="목록" onclick="location.href='mycontroller.jsp?command=boardlist'"/>
			</td>
		</tr>
	
	</table>

</body>
</html>
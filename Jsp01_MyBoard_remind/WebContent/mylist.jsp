<%@page import="com.my.dao.MyBoardDao"%>
<%@page import="com.my.dto.MyBoardDto"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">




</style>
</head>
<%
	MyBoardDao dao = new MyBoardDao();
	List<MyBoardDto> list = dao.selectlist();
%>
<body>


	<table border="1">
		<thead>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>제목</th>
			<th>내용</th>
			<th>날짜</th>
		</tr>
		</thead>
		<tbody>
<%
	for(int i =0; i<list.size(); i++){
%>		
			<tr>
				<td><%=list.get(i).getMyno()%></td>
				<td><%=list.get(i).getMyname() %></td>
				<td><a href="myselectone.jsp?myno=<%=list.get(i).getMyno()%>"><%=list.get(i).getMytitle() %></a></td>
				<td><%=list.get(i).getMycontent()%></td>
				<td><%=list.get(i).getMydate() %></td>
			</tr>
<%
	}
%>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="1">
				<input type="button" value="글쓰기" onclick="location.href='myinsert.jsp'">
				</td>
			</tr>
		</tfoot>
		
	</table>
	
</body>
</html>
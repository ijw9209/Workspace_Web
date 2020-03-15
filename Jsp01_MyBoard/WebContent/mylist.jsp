<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.my.dao.MyBoardDao" %>    
<%@ page import="com.my.dto.MyBoardDto" %>
<%@ page import="java.util.List" %>    

<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	MyBoardDao dao = new MyBoardDao();
	List<MyBoardDto> list = dao.selectList();
%>
<body>

	<h1>List page</h1>
	
	<table border ="1">
		<col width="50px"/>
		<col width="100px"/>
		<col width="200px"/>
		<col width="10px"/>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>제목</th>
			<th>날짜</th>
		</tr>
<%
	for(int i=0; i< list.size(); i++){
%>
	<tr>
		<td><%=list.get(i).getMyno() %></td>
		<td><%=list.get(i).getMyname() %></td>
		<td><a href="myselectone.jsp?myno=<%=list.get(i).getMyno()%>"><%=list.get(i).getMytitle() %></a></td>
		<td><%=list.get(i).getMydate() %></td>
	</tr>
<%
	}
%>	
		<tr>
			<td colspan="4">
				<input type="button" onclick="location.href='myinsert.jsp'" value="글쓰기"/>
			</td>
		</tr>	
	</table>

</body>
</html>
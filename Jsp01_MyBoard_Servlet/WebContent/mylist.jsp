<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.my.dto.MyBoardDto" %>
<%@ page import="java.util.List" %>    
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<%
	List<MyBoardDto> list = (List<MyBoardDto>)request.getAttribute("list");
%>
</head>
<body>

	<h1>목록</h1>
	
	<table border = "1">
		<col width="50px">
		<col width="70px">
		<col width="100px">
		<col width="50px">
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
			<td><a href="my.do?command=selectone&myno=<%=list.get(i).getMyno()%>"><%=list.get(i).getMytitle() %></a></td>
			<td><%=list.get(i).getMydate() %></td>
		</tr>
<%
	}
%>	
		<tr>
			<td colspan="2">
				<input type="button" value="글쓰기" onclick="location.href='my.do?command=insert'">
			</td>
		</tr>
	

	</table>

</body>
</html>
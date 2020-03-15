<%@page import="com.login.dto.MemberDto"%>
<%@page import="java.util.List"%>
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
table th {
	text-align: center;
    background-color: lightgray;
}
td {
text-align: center;
}
table{
    width: 80%;
}


</style>
</head>
<%
	List<MemberDto> list = (List<MemberDto>)session.getAttribute("list");
%>
<body>


	<h1>회원정보 전체 조회</h1>
	<table border="1">

		<col width="50">
		<col width="100">
		<col width="100">
		<col width="500">
		<col width="300">
		<col width="150">
		<col width="100">
		<col width="50">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>주소</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>가입여부</th>
			<th>등급</th>
		</tr>

<%
	for(int i =0; i<list.size(); i++){
%>	

		<tr>
			<td><%=list.get(i).getMyno() %></td>
			<td><%=list.get(i).getMyid() %></td>
			<td><%=list.get(i).getMyname() %></td>
			<td><%=list.get(i).getMyaddr() %></td>
			<td><%=list.get(i).getMyphone() %></td>
			<td><%=list.get(i).getMyemail() %></td>
			<td><%= (list.get(i).getMyenabled().equals("Y"))?"가입":"탈퇴"%></td>
			<td><%= (list.get(i).getMyrole().equals("ADMIN"))?"관리자":"일반계정"%></td>
		</tr>
		
		
<%
	}
%>	
		<tr>
			<td colspan="8">
				<input type="button" onclick="location.href='index.jsp'" value="메인"/>
			</td>	
		</tr>
	
	
	</table>



</body>
</html>
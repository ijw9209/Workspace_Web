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
<%
	List<MemberDto> list = (List<MemberDto>)session.getAttribute("list");
%>

<script type="text/javascript">

	function updateRole(myno){
			location.href="logincontroller.jsp?command=updateroleform&myno=" + myno;
		} 
 

</script>
</head>
<body>
<h1>가입된 회원조회</h1>
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
			<th>등급변경</th>
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
			<td><%=(list.get(i).getMyenabled().equals("Y"))?"가입":"탈퇴"%></td>
			<td><%=(list.get(i).getMyrole().equals("ADMIN"))?"관리자":"일반계정"%><input type="button" value="등급변경" onclick="location.href='logincontroller.jsp?command=changerole&role=<%=list.get(i).getMyrole()%>&myno=<%=list.get(i).getMyno()%>'"></td>
			<td><button onclick ="updateRole(<%=list.get(i).getMyno() %>)">변경</button>
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
<%@page import="com.mul.dto.MDBoardDto"%>
<%@page import="java.util.List"%>
<%@page import="com.mul.biz.MDBoardBiz"%>
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
<%
	String text = request.getParameter("search");
%>
<%
MDBoardBiz biz = new MDBoardBiz();
List<MDBoardDto> list = biz.selectList();


%>

<body>

	<%@ include file="./form/header.jsp" %>
	
	<h1>글 목록</h1>
	
	<form action="muldel.jsp" method="post" id="muldelform">
	 
		<table border="1">
			<col width="30px"/>
			<col width="50px"/>
			<col width="100px"/>
			<col width="300px"/>
			<col width="100px"/>
			<tr>
				<th><input type="checkbox" name="all" onclick="allChk(this.checked);"/></th>
				<th>번  호</th>
				<th>작성자</th>
				<th>제  목</th>
				<th>작성일</th>
			</tr>	
<%
	if(list.size() == 0){
%>			
			<tr>
				<td colspan="5">--------작성된 글이 없습니다-------</td>
			</tr>
<%
	}else{
		for(int i = 0; i < list.size(); i++){
	
%>	
			<tr>
				<td><input type="checkbox" name="chk" value="<%=list.get(i).getSeq()%>"/></td>
				<td><%=list.get(i).getSeq()%></td>
				<td><%=list.get(i).getWriter() %></td>
				<td><a href="boardselectone.jsp?seq=<%=list.get(i).getSeq()%>"><%=list.get(i).getTitle() %></a></td>
				<td><%=list.get(i).getRegdate() %></td>
			</tr>
<%
		}
	}	
%>
		<tr>
			<td colspan="5">
				
				
			</td>
		</tr>
		</table>
	</form>
	
	
	<%@ include file="./form/footer.jsp" %>
</body>
</html>
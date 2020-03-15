<%@page import="com.mvc.dto.MVCBoardDto"%>
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
	table{
		width:600px;
		text-align: center;
		
	}
	table th{
		background-color: pink;
		color:gray;
	}
	a{
		text-decoration: none;
		color:blue;
	}
	
</style>
</head>
<body>
<%
	//request 에 담긴 객체는 오브젝트기때문에 명시적으로 형변환을 해줘야함
	List<MVCBoardDto> list = (List<MVCBoardDto>)request.getAttribute("list");
%>


	<h1>글 목록</h1>
	<table border="1" style="border-color: silver;">
		<colgroup>
			<col width="3%" />
			<col width="5%" />
			<col width="5%" />
			<col width="5%" />
		</colgroup>
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
<%
	if(list.size() == 0){
%>	
	<tr>
		<td colspan="4">----------------작성된 글이 없습니다----------</td>
	</tr>

<%
	}else{
		for(MVCBoardDto dto : list){
%>	

		<tr>
			<td><%=dto.getSeq() %></td>
			<td><%=dto.getWriter() %></td>
			<td><a href="mycontroller.jsp?seq=<%=dto.getSeq() %>&command=boardselectone""><%=dto.getTitle() %></a></td>
			<td><%=dto.getRegdate() %></td>
		</tr>
<%
		}
	}
%>

		<tr>
			<td colspan="4">
			<input type="button" value="글쓰기" onclick="location.href='mycontroller.jsp?command=insertform'"/>
			</td>
		</tr>
	</table>
</body>
</html>
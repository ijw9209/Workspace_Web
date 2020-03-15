<%@page import="com.mul.dto.MDBoardDto"%>
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
<%
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	MDBoardDto dto = new MDBoardDto(writer,title,content);
	
	
	MDBoardBiz biz = new MDBoardBiz();
	boolean res = biz.insert(dto);
	
	if(res == true){
%>
<script type="text/javascript">
	alert("insert 성공!");
	location.href="boardlist.jsp";
</script>

<%
	}else{
%>
<script type="text/javascript">
	alert("insert 실패!");
	location.href="boardlist.jsp";
</script>
<%
	}
%>
<body>


</body>
</html>
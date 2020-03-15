<%@page import="com.my.dao.MyBoardDao"%>
<%@page import="com.my.dto.MyBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8"); %>    
<!DOCTYPE html>
<html>
<head>
<% 
	int myno = Integer.parseInt(request.getParameter("myno"));
	MyBoardDao dao = new MyBoardDao();
	int res = dao.Delete(myno);
	
	
	if(res > 0){
		
	
%>
<script type="text/javascript">
	alert("글 삭제 성공!");
	location.href="mylist.jsp";
</script>

<%
	}else{
%>
<script type="text/javascript">
	alert("글 삭제 실패!");
	location.href="mylist.jsp";
</script>
<%
	}
%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
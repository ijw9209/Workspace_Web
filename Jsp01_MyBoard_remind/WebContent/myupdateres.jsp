<%@page import="com.my.dao.MyBoardDao"%>
<%@page import="com.my.dto.MyBoardDto"%>
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
	int myno = Integer.parseInt(request.getParameter("myno"));
	String mytitle = request.getParameter("mytitle");
	String mycontent = request.getParameter("mycontent");
	
	MyBoardDto dto = new MyBoardDto();
	dto.setMyno(myno);
	dto.setMytitle(mytitle);
	dto.setMycontent(mycontent);
	MyBoardDao dao = new MyBoardDao();
	int res = dao.update(dto);
	if(res > 0) {
%>
<script type="text/javascript">
	alert("update 성공!");
	location.href="myselectone.jsp?myno=<%=dto.getMyno()%>"
</script>
<%
	}else{
%>
<script type="text/javascript">
	alert("update 실패!");
	location.href="myselectone.jsp?myno=<%=dto.getMyno()%>"
</script>
<%
	}
%>
</body>
</html>
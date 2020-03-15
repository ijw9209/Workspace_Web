<%@page import="com.mul.biz.MDBoardBiz"%>
<%@page import="com.mul.dto.MDBoardDto"%>
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
	int seq = Integer.parseInt(request.getParameter("seq"));
	MDBoardBiz biz = new MDBoardBiz();
	MDBoardDto dto = new MDBoardDto();
	boolean res = biz.delete(seq);
	if(res == true){
%>
<script type="text/javascript">
	alert("delete 성공!");
	location.href="boardlist.jsp";
</script>
<%
	}else{
%>
<script type="text/javascript">
	alert("delete 실패!");
	location.href="boardselectone.jsp?seq=<%=dto.getSeq()%>";
</script>
<%
	}
%>
<body>


</body>
</html>
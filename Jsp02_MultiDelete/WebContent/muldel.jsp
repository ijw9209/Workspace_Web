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
	String[]seq = request.getParameterValues("chk");
%>

<%
	if(seq ==null || seq.length == 0){
%>

<script type="text/javascript">
		alert("체크를 하나라도 해주십쇼");
		location.href="boardlist.jsp";
</script>
<%
	}else{
		MDBoardBiz biz = new MDBoardBiz();
		if(biz.multidelete(seq)){
%>
<script type="text/javascript">
		alert("체크박스 삭제 성공!");
		location.href="boardlist.jsp";
</script>
<%
		}else{
%>
<script type="text/javascript">
	alert("체크박스 삭제 실패!");
	location.href="boardlist.jsp";
</script>
<%
		}
	}
%>
<body>

</body>
</html>
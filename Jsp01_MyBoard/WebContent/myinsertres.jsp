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
	//값은 리퀘스트에있음 ~~~~~?myname=ㅁ&mytitle=ㅁ&mycontent=ㅁ
	String myname = request.getParameter("myname");
	String mytitle =request.getParameter("mytitle");
	String mycontent = request.getParameter("mycontent");
	
	//dto 에다 담아서 dao에넘겨서 db에 넘김
	MyBoardDto dto = new MyBoardDto();
	dto.setMyname(myname);
	dto.setMytitle(mytitle);
	dto.setMycontent(mycontent);
	
	MyBoardDao dao = new MyBoardDao();
	int res = dao.Insert(dto);
	
	if(res > 0){
		
	
%>	
	<script type="text/javascript">
		alert("글 작성 성공!");
		location.href="mylist.jsp";
	</script>
<%
	}else{
%>
	<script type="text/javascript">
		alert("글 작성 실패!");
		//	./ 생략가능
		location.href="myinsert.jsp"
	</script>
<%
	}
%>	
</body>
</html>
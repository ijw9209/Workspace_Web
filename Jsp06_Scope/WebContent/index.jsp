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
	pageContext.setAttribute("pageId", "page value");
	request.setAttribute("reqId","request value");
	session.setAttribute("sessionId","session value");
	application.setAttribute("appId","application value");


%>
</head>
<body>

	<h1>INDEX</h1>
	
	pageId : <%=pageContext.getAttribute("pageId") %>
	<br/>
	<!-- 2) request : 현재 페이지에서 요청한 다음 페이지까지만 객체 전달
(요청된 페이지까지 객체 유지) 왜 null뜨는지 찾아오는것 숙제 -->
	reqId : <%=request.getAttribute("reqId") %>
	<br/>
	sessionId : <%=session.getAttribute("sessionId") %>
	<br/>
	appId : <%=application.getAttribute("appId") %>
	<br/>
	
	<!-- 이동하기 버튼을 눌렀을경우(클라이언트에서 페이지변경이 이루어짐 )-->
	<!-- 해당 URL로 새로운 요청을 하기 때문에 request , page 영역에 저장한 속성을 가져올수없는것을 볼수있다. -->
	<a href="result.jsp">result</a>
	<!-- 	* 포워딩이 이루어졌을경우 ( 서버에서 페이지변경이 이루어짐 )
	request 영역에 저장한 속성을 가져올수있는것을 볼수있다. 
	반면 page영역 저장된 속성은 페이지를 벗어났기때문에 참조할수없는것을 볼수있다. 
	서버에서 페이지 교체가 이루어지면서-->
	<!--  jsp:forward page="result.jsp -->
	<br/>
	<a href="scope.do">controller</a>
</body>
</html>
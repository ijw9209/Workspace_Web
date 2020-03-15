<%@page import="com.mvc.dto.MVCBoardDto"%>
<%@page import="java.util.List"%>
<%@page import="com.mvc.dao.MVCBoardDao"%>
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
	String command = request.getParameter("command");
	System.out.println("<"+command+">");
	MVCBoardDao dao = new MVCBoardDao();
	if(command != null){
		
		
		if(command.equals("boardlist")){
			List<MVCBoardDto> list = dao.selectList();
			request.setAttribute("list", list);
			pageContext.forward("myboardlist.jsp");
		}else if(command.equals("muldel")){
			System.out.println("!!!!");
			String[] seq = request.getParameterValues("chk");
			for(int i =0; i<seq.length; i++){
			System.out.println(seq[i]);
			}
			if(seq == null || seq.length == 0){
%>
<script type="text/javascript">
	alert("하나라도 체크해주세요!");
	location.href="mycontroller.jsp?command=boardlist";
</script>
<% 				
			}else{
				if(dao.multidelete(seq)){
%>
<script type="text/javascript">
	alert("multi delete 성공!");
	location.href="mycontroller.jsp?command=boardlist";
</script>
<% 					
				}else{
%>
<script type="text/javascript">
	alert("muti delete 실패!!");
	location.href="mycontroller.jsp?command=boardlist";
</script>
<%					
				}
			}
		}else if(command.equals("insertform")){
			response.sendRedirect("insertform.jsp");
		}else if(command.equals("insertres")){
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			MVCBoardDto dto = new MVCBoardDto(writer , title , content);
			
			int res = dao.insert(dto);
			if(res > 0){

%>
	<script type="text/javascript">
		alert("글 작성 성공!");
		location.href="mycontroller.jsp?command=boardlist";
	</script>
	
<%				
			}else{
%>				
<script type="text/javascript">
		alert("글 작성 실패!");
		location.href="mycontroller.jsp?command=insertform";
</script>
<%
			}
		}else if(command.equals("selectone")){
			int seq = Integer.parseInt(request.getParameter("seq"));
			MVCBoardDto dto = dao.selectone(seq);
			request.setAttribute("dto",dto);
			pageContext.forward("selectone.jsp");
		}else if(command.equals("updateform")){
			int seq = Integer.parseInt(request.getParameter("seq"));
			MVCBoardDto dto = dao.selectone(seq);
			request.setAttribute("dto",dto);
			pageContext.forward("updateform.jsp");
		}else if(command.equals("updateres")){
			int seq = Integer.parseInt(request.getParameter("seq"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			MVCBoardDto dto = new MVCBoardDto(seq,title,content);
			int res = dao.update(dto);
			if(res > 0){
%>
<script type="text/javascript">
	alert("update성공!");
	location.href="mycontroller.jsp?seq=<%=seq%>&command=selectone";
</script>	
	
<%				
			}else{
%>
<script type="text/javascript">
	alert("update 실패!");
	location.href="mycontroller.jsp?seq=<%=seq%>&command=selectone";
</script>
<%			
			}
		}else if(command.equals("deleteform")){
			int seq = Integer.parseInt(request.getParameter("seq"));
			int res = dao.delete(seq);
			if(res > 0 ){
%>
<script type="text/javascript">
	alert("delete 성공!");
	location.href="mycontroller.jsp?seq=<%=seq%>&command=selectone";
</script>		
<%
			}else{	
%>
<script type="text/javascript">
	alert("delete 실패!");
	location.href="mycontroller.jsp?seq=<%=seq%>&command=selectone";
</script>	
<%
			}
			}
	}
%>		
<body>
	<h1>잘못왔음</h1>
</body>
</html>
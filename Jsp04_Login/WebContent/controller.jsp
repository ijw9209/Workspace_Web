<%@page import="com.login.dto.MyBoardDto"%>
<%@page import="com.login.biz.MyBoardBiz"%>
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
	System.out.println("< " +command + " > ");
	if(command != null){
		
		MyBoardBiz biz = new MyBoardBiz();
		if(command.equals("login")){
			String myid = request.getParameter("id");
			String mypw = request.getParameter("pw");
			
			MyBoardDto login = biz.login(myid, mypw);
			if(login != null){
				session.setAttribute("dto", login);
				session.setMaxInactiveInterval(3600);
				if(login.getMyrole().equals("ADMIN")){
					response.sendRedirect("adminmain.jsp");
				}else if(login.getMyrole().equals("USER")){
					response.sendRedirect("usermain.jsp");
				}
			}else{
%>
<script type="text/javascript">
	alert("id와pw를 다시 확인 해주세요!");
	location.href='index.jsp';
</script>
<%				
			}	
		}else if(command.equals("logout")){
			session.invalidate();
			response.sendRedirect("index.jsp");
		}else if(command.equals("usermain")){
			response.sendRedirect("usermain.jsp");	
		}else if(command.equals("regist")){
			response.sendRedirect("registform.jsp");		
		}else if(command.equals("userinfo")){
			response.sendRedirect("userinfo.jsp");
		}else if(command.equals("update")){
			response.sendRedirect("updateform.jsp");
		}else if(command.equals("updateres")){
			MyBoardDto dto = (MyBoardDto)session.getAttribute("dto");
			int myno = Integer.parseInt(request.getParameter("myno"));
			String pw = request.getParameter("pw");
			String myaddr = request.getParameter("addr");
			String myphone = request.getParameter("phone");
			String myemail = request.getParameter("email");
			dto.setMyno(myno);
			dto.setMypw(pw);
			dto.setMyaddr(myaddr);
			dto.setMyphone(myphone);
			dto.setMyemail(myemail);
			
			int res = biz.update(dto);
			
			if(res > 0){
				//session.setAttribute("dto", dto);
%>	
<script type="text/javascript">
	alert("수정 성공!");
	location.href="controller.jsp?command=userinfo";
</script>

<%				
			}else{
%>
<script type="text/javascript">
	alert("수정 실패!");
	location.href="controller.jsp?command=update";
</script>
<%				
			
			}
		}else if(command.equals("delete")){
			int myno = Integer.parseInt(request.getParameter("myno"));
			int res = biz.delete(myno);
			if(res > 0) {
				session.invalidate();
%>
<script type="text/javascript">
	alert("탈퇴 성공!");
	location.href="index.jsp";
</script>
<%				
			}else{
%>
<script type="text/javascript">
	alert("탈퇴 실패!");
	location.href="index.jsp";
</script>
<%				
			}
		}else if(command.equals("idChk")){
			String myid = request.getParameter("id");
			int res = biz.idChk(myid);
			response.sendRedirect("idchk.jsp?res="+res);
		}else if(command.equals("registres")){
			System.out.println("여기");
			String myid = request.getParameter("myid");
			String mypw = request.getParameter("pw");
			String myname = request.getParameter("name");
			String myaddr = request.getParameter("addr");
			String myphone = request.getParameter("phone");
			String myemail = request.getParameter("email");
			MyBoardDto dto = new MyBoardDto();
			dto.setMyid(myid);
			dto.setMypw(mypw);
			dto.setMyname(myname);
			dto.setMyaddr(myaddr);
			dto.setMyphone(myphone);
			dto.setMyemail(myemail);
			int res = biz.insert(dto);
			if(res > 0){
%>
<script type="text/javascript">
	alert("회원가입 성공!");
	location.href="index.jsp";
</script>
<%				
			}else{
%>
<script type="text/javascript">
	alert("회원가입 실패!!");
	location.href="index.jsp";
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
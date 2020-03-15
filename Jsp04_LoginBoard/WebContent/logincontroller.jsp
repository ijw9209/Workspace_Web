<%@page import="java.util.List"%>
<%@page import="com.login.dto.MemberDto"%>
<%@page import="com.login.biz.MemberBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<%
		String command = request.getParameter("command");
		System.out.println("[ " + command + " ]");
		MemberBiz biz = new MemberBiz();

		if (command.equals("login")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");

			MemberDto login = biz.login(id, pw);

			if (login.getMyid() != null) {
				//session 서버에 요청하고 응답할때 브라우저에대한 세션아이디가 만들어지고 세션이 만료되기전까지 어떤 페이지에서도 이세션이 살아있음
				session.setAttribute("dto", login);
				//setMaxInactiveInterval() : 초로 되어있음
				session.setMaxInactiveInterval(10 * 60);
				//10분동안 활동이 없으면 session의 'dto 사라짐.
				// 이거안써주면 default : 30분
				// 음수일때 , 무제한

				//관리자와 유저페이지 나누기
					if (login.getMyrole().equals("ADMIN")) {
						response.sendRedirect("adminmain.jsp");
					} else if (login.getMyrole().equals("USER")) {
						response.sendRedirect("usermain.jsp");
					}
			} else {
%>
	<script type="text/javascript">
		alert('login 실패! id와 pw를 다시 확인해 주세요!');
		location.href = "index.jsp";
	</script>
<%
			}

		} else if (command.equals("logout")) {
			//session 정보 삭제
			session.invalidate();
			
			response.sendRedirect("index.jsp");
		} else if(command.equals("userinfo")){
			response.sendRedirect("userinfo.jsp");
			
		}else if(command.equals("usermain")){
			response.sendRedirect("usermain.jsp");	
		}else if(command.equals("update")){
			response.sendRedirect("update.jsp");
		}else if(command.equals("updateres")){
			//(주소,전화번호,이메일,비밀번호
			MemberDto dto = (MemberDto)session.getAttribute("dto");		
			int myno = Integer.parseInt(request.getParameter("myno"));
			String addr = request.getParameter("addr");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String pw = request.getParameter("pw");
			String id = request.getParameter("id");
			
			
			//MemberDto dto = new MemberDto();
			dto.setMyno(myno);
			dto.setMyaddr(addr);
			dto.setMyphone(phone);
			dto.setMyemail(email);
			dto.setMypw(pw);
			int res = biz.update(dto);
			//dto = biz.login(id, pw);
			//session안해주면 업뎃이안됨
			//session.setAttribute("dto", dto);
			if(res > 0){
				
				
%>
	<script type="text/javascript">
		alert('update완료!');
		location.href="logincontroller.jsp?command=userinfo";
	</script>
<%
			}else{
%>				
	<script type="text/javascript">
		alert('update 실패!');
		location.href="logincontroller.jsp?command=userinfo";
	</script>			
<%				
			}
		}else if(command.equals("delete")){
			int myno = Integer.parseInt(request.getParameter("myno"));
			MemberDto dto = new MemberDto();
			int res = biz.delete(myno);
			if(res > 0){
				session.invalidate();
%>
<script type="text/javascript">
	alert("탈퇴 성공!");
	location.href="index.jsp"
</script>

<%				

			
			}else{
%>
<script type="text/javascript">
	alert("탈퇴 실패!");
	location.href="index.jsp"
</script>
<%				
			
			}
		}else if(command.equals("registform")){
			response.sendRedirect("registform.jsp");
		}else if(command.equals("idchk")){
			String myid = request.getParameter("id");
			String res = biz.idChk(myid);
			
			boolean idnotused = true;
			
			if(res != null){
				idnotused = false;
			}
	
			response.sendRedirect("idchk.jsp?idnotused="+idnotused);
		}else if(command.equals("registres")){
			MemberDto dto = new MemberDto();
			String myid = request.getParameter("myid");
			String mypw = request.getParameter("mypw");
			String myname = request.getParameter("myname");
			String myaddr = request.getParameter("myaddr");
			String myphone = request.getParameter("myphone");
			String myemail = request.getParameter("myemail");
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
	alert("회원가입성공!");
	location.href="index.jsp";
</script>
<%				
			}else{
%>
<script type="text/javascript">
	alert("회원가입 실패!");
	location.href="index.jsp";
</script>
<%				
			}
			//관리자화면
		}else if(command.equals("userlistall")){
			List<MemberDto> list = biz.userlistAll();
			session.setAttribute("list", list);
			pageContext.forward("userlistall.jsp");
		}else if(command.equals("enabledylist")){
			List<MemberDto> list = biz.enableylist();
			session.setAttribute("list", list);
			pageContext.forward("enabledylist.jsp");
		}else if(command.equals("changerole")){
			String role = request.getParameter("role");
			int myno = Integer.parseInt(request.getParameter("myno"));
			MemberDto dto = biz.selectOne(myno);
			request.setAttribute("dto",dto);
			response.sendRedirect("updateroleform.jsp");
			int res = biz.changRole(role, myno);
			if(res > 0){
		
%>
<script type="text/javascript">
	alert("role변경 성공!");
	location.href="index.jsp";
</script>


<%				
			}else{
%>
<script type="text/javascript">
	alert("role변경 실패!");
	location.href="index.jsp";
</script>
<%				
			}
			
		}else if(command.equals("updateroleres")){
			int myno = Integer.parseInt(request.getParameter("myno"));
			String role = request.getParameter("role");
			
			int res = biz.updateRole(role, myno);
			if(res > 0){
				%>
				<script type="text/javascript">
					alert("role변경 성공!");
					location.href="logincontroller.jsp?command=enabledylist";
				</script>


				<%				
							}else{
				%>
				<script type="text/javascript">
					alert("role변경 실패!");
					location.href="logincontroller.jsp?command=updateroleform&myno="+myno;
				</script>
				<%					
				
			}
		}
%>
	<h1>잘못왔음</h1>
</body>
</html>
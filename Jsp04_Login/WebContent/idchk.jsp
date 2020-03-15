<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	
	onload=function(){
		var id = opener.document.getElementsByName("myid")[0].value;
		document.getElementsByName("id")[0].value = id;
	}

	
	function idConfirm(res){
		
		if(res > 0){
			alert("아이디생성 불가 중복입니당.");
			opener.document.getElementsByName("myid")[0].focus();
		}else{
			alert("아이디 생성가능!");
			opener.document.getElementsByName("myid")[0].title = "Y";
		}
		self.close();
	}

</script>
</head>
<%
	int res = Integer.parseInt(request.getParameter("res"));
%>
<body>
<table border="1">
		<tr>
			<td><input type="text" name="id"></td>
		</tr>
		<tr>
			<td><%=(res > 0)?"중복이다새끼야":"만들어라" %></td>
		</tr>
		<tr>
			<td>												<!-- ''잡은이유 String값으로 넣어야되니깐 문자열이라고 명시 -->
				<input type="button" value="확인" onclick="idConfirm('<%=res%>')">
			</td>
		</tr>
	
	</table>
</body>
</html>
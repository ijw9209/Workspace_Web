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
<script type="text/javascript">
	
	
	onload=function(){
		var id = opener.document.getElementsByName("id")[0].value;
		document.getElementsByName("id")[0].value = id;
	}

	function idConfirm(res){
		
		if(res > 0){
			alert("아이디생성 불가 중복이랑께");
			opener.document.getElementsByName("id")[0].focus();
		}else{
			alert("아이디 생성가능!");
			opener.document.getElementsByName("id")[0].title = "Y";
		}
		self.close();
	}
</script>
<body>
<%
	int res = Integer.parseInt(request.getParameter("res"));
%>
	<table border="1">
		<tr>
			<td><input type="text" name="id"></td>
		</tr>
		<tr>
			<td><%=(res > 0)? "중복이얌꺼졍" : "중복아님 만들어랏" %></td>
		</tr>
		<tr>
			<td>
				<input type="button" value="확인" onclick="idConfirm('<%=res %>')">
			</td>
		</tr>
	</table>
</body>
</html>
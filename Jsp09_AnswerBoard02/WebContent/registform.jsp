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
	
	function idChk(){
		var id = document.getElementsByName("id")[0];
		if(id.value == null || id.value.trim()==""){
			alert("id를 입력해주세요!");
			document.getElementsByName("id")[0].focus();
		}else{
			open("login.do?command=idChk&id="+id.value,"","width=300px,height=300px");
		}
	}
	function idcomfirmChk(){
		var id = document.getElementsByName("id")[0].title;
		if(id == "n"){
			document.getElementsByName("id")[0].focus();
			alert("id중복검사를 해주십쇼!");
		}else{
			document.getElementsByName("pw")[0].focus();
		}
	}

</script>
<body>
	<form action="login.do" method="post">
		<input type="hidden" name="command" value="registres">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td><input type="text" name="id" title="n">
					<input type="button" value="중복검사" onclick="idChk();">
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="pw" onclick="idcomfirmChk();"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" onclick="idcomfirmChk();"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="phone" onclick="idcomfirmChk();"></td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="submit" value="가입">
					<input type="button" value="취소" onclick="location.href='login.do?command=login'">
				</td>
			</tr>
		
		
		
		</table>
	</form>



</body>
</html>
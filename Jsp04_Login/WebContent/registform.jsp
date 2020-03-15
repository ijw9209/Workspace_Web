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
<script type="text/javascript">

	function idChk(){
		var id = document.getElementsByName("myid")[0];
		if(id.value == null || id.value.trim()==""){
			alert("id를 입력해주세요!");
			document.getElementsByName("myid")[0].focus();
		}else{
			open("controller.jsp?command=idChk&id="+id.value,"","width=300px,height=300px");
		}
	}
	function idcomfirmChk(){
		var id = document.getElementsByName("myid")[0].title;
		if(id =='n'){
			document.getElementsByName("myid")[0].focus();
			alert("id중복검사를 해주십쇼!");
		}else{
			document.getElementsByName("pw")[0].focus();
		}
	}	

</script>
</head>
<body>

	<form action="controller.jsp" method="post">
		<input type="hidden" name="command" value="registres">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td><input type="text" name="myid" title="n" > <input
					type="button" value="중복검사" onclick="idChk();"></td>
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
				<th>주소</th>
				<td><input type="text" name="addr" onclick="idcomfirmChk();"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="phone" onclick="idcomfirmChk();"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email" onclick="idcomfirmChk();"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="가입"> <input
					type="button" value="취소" onclick="location.href='index.jsp'">
				</td>
			</tr>




		</table>
	</form>

</body>
</html>
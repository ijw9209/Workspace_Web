<%@page import="com.my.dto.MyBoardDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script type="text/javascript">
	
	function allChk(bool){
		$("input[name=chk]").each(function(){
			$(this).prop("checked",bool);
		});
	}
	
	$(function(){
		$("input[name=chk]").click(function(){
			if($("input[name=chk]").length==$("input[name=chk]:checked").length){
				$("input[name=all]").prop("checked",true);
			}else{
				$("input[name=all]").prop("checked",false);
			}
		});
		$("#listform").submit(function(){
			if($("input[name=chk]:checked ").length == 0){
				alert("하나라도 체크해주세요!");
				return false;
			}
		});
	});
	

</script>
<title>Insert title here</title>
<%
	List<MyBoardDto> list = (List<MyBoardDto>)request.getAttribute("list");
%>
</head>
<body>
	
	<form action="my.do" method="post" id="listform">
		<input type="hidden" name="command" value="muldel">
		<table border="1">
			<tr>
				<th><input type="checkbox" name="all" onclick="allChk(this.checked);"></th>
				<th>번호</th>
				<th>이름</th>
				<th>제목</th>
				<th>날짜</th>
			</tr>
<%
	if(list == null){
%>		
			<tr>
				<td>---------게시글을 입력해주세요----------</td>
			</tr>
<%
	}else{
		for(MyBoardDto dto : list){
%>		
		<tr>
			<td><input type="checkbox" name="chk" value="<%=dto.getMyno()%>"></td>
			<td><%=dto.getMyno() %></td>		
			<td><%=dto.getMyname() %></td>
			<td><a href="my.do?command=selectone&myno=<%=dto.getMyno()%>"><%=dto.getMytitle() %></a></td>
			<td><%=dto.getMydate() %></td>
		</tr>
<%
		}
	}
%>		
		<tr>
			<td colspan="1">
			<input type="button" value="글작성" onclick="location.href='my.do?command=insert'">
			<input type="submit" value="삭제">
		</tr>
		
		
		</table>
	
	</form>
</body>
</html>
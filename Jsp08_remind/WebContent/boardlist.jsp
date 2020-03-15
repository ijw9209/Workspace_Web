<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8"); %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		$("#form").submit(function(){
			if($("input[name=chk]:checked").length==0){
				alert("하나라도 체크햇주셈");
				return false;
			}
		});
	});
	

</script>
</head>
<body>

	<form action="mvc.do" method="post" id="form">
		<input type="hidden" name="command" value="muldel">
		<table border="1">
			<tr>
				<th><input type="checkbox" name="all" onclick="allChk(this.checked);"></th>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>날짜</th>
			</tr>
		<c:choose>
			<c:when test="${empty list }">
				<tr>
					<td colspan="5">----------------내용없음------------</td>
				</tr>
			</c:when>
		<c:otherwise>
		<c:forEach items="${list }" var="dto">
			<tr>
				<td><input type="checkbox" name="chk" value="${dto.seq }"></td>
				<td>${dto.seq }</td>
				<td>${dto.writer}</td>
				<td><a href="mvc.do?command=selectone&seq=${dto.seq }">${dto.title }</a></td>
				<td>${dto.regdate }</td>
			</tr>
		</c:forEach>
		</c:otherwise>
	</c:choose>	
			<tr>
				<td colspan="5">
					<input type="button" value="글작성" onclick="location.href='mvc.do?command=insert'">
					<input type="submit" value="멀티딜리투">
				</td>
			</tr>
		
		</table>
	</form>
</body>
</html>
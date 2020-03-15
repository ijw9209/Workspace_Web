<%@page import="com.mul.biz.MDBoardBiz"%>
<%@page import="com.mul.dto.MDBoardDto"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script type="text/javascript">

	function allChk(bool){
		$("input[name=chk]").each(function(){
			$(this).prop("checked" , bool);
		});
	}


	$(function(){
		$("#listform").submit(function(){
			if($("input:checked").length == 0){
				alert("하나라도 체크해주세요!");
				return false;
			}
		});
		$("input[name=chk]").click(function(){
			if($("input[name=chk]").length == $("input[name=chk]:checked").length){
				$("input[name=all]").prop("checked",true);
			}else{
				$("input[name=all]").prop("checked",false);
			}
		});
	});



</script>
<style type="text/css">
td{
 	text-align: center;
}
table{
    margin-left: 350px;
}
</style>

</head>
<%
	MDBoardBiz biz = new MDBoardBiz();
	
	List<MDBoardDto> list = biz.selectList();
%>
<body>
	<%@include file="./form/header.jsp" %>
	<form action="muldel.jsp" method="post" id="listform">
		<table border="1" style="width:800px;">
		<colgroup>
			<col width=1%>
			<col width=5%>
			<col width=10%>
			<col width=7%>
		</colgroup>	
			<tr>
				<th><input type="checkbox" name="all" onclick="allChk(this.checked);"></th>
				<th>작성자</th>
				<th>제목</th>
				<th>날짜</th>
			</tr>
<%
	if(list.size() == 0){
%>			
			<tr>
				<td colspan="5">-----------내용이 없습니다--------</td>
			</tr>
			
<%
	}else{
		for(int i = 0; i<list.size(); i++){
%>			
			<tr>
				<td><input type="checkbox" name="chk" value="<%=list.get(i).getSeq()%>"></td>
				<td><%=list.get(i).getWriter() %></td>
				<td><a href=""><%=list.get(i).getTitle() %></a></td>
				<td><%=list.get(i).getRegdate() %></td>
			</tr>
<%
		}
	}
%>			
		<tr>	
			<td colspan="4">
			<input type="submit" value="삭제"/>
			<input type="button" value="글쓰기" onclick="location.href='boardinsert.jsp'">
		</tr>
			
		</table>
	</form>
	<%@include file="./form/footer.jsp" %>
</body>
</html>
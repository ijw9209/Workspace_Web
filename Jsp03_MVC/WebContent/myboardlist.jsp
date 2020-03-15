<%@page import="java.util.List"%>
<%@page import="com.mvc.dto.MVCBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="./js/jquery-3.4.1.js"></script>
<script type="text/javascript">


	function allChk(bool){
		$("input[name=chk]").each(function(){
			$(this).prop("checked",bool);
		});
	}
	$(function(){
		$("#listform").submit(function(){
		if($("#listform input[name=chk]:checked").length == 0){
			alert("하나라도 체크해 주세요!");
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
<title>Insert title here</title>
<%
	List<MVCBoardDto> list = (List<MVCBoardDto>)request.getAttribute("list");
%>
</head>
<body>

	<h1>글 목록</h1>
	<form action="mycontroller.jsp" method="get" id="listform">
	<input type="hidden" name="command" value="muldel">
	<table border="1" style="width:600px; height: 100px; text-align: center;" >
		<colgroup>
			<col width="2%">
			<col width="5%">
			<col width="10%">
			<col width="5%">
		
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
			<td colspan="4">-------------목록이 없습니다</td>
		</tr>
<%
	}else{
		for(int i = 0; i<list.size(); i++){
%>	
		<tr>
			<td><input type="checkbox" name="chk" value="<%=list.get(i).getSeq()%>"></td>
			<td><%=list.get(i).getWriter() %></td>
			<td><a href="mycontroller.jsp?seq=<%=list.get(i).getSeq() %>&command=selectone"><%=list.get(i).getTitle() %></a></td>
			<td><%=list.get(i).getRegdate() %></td>
		</tr>
<%
		}
	}
%>
		<tr>
			<td colspan="2">
			<input type="button" value="글쓰기" onclick="location.href='mycontroller.jsp?command=insertform'">
			<input type="submit" value="삭제"/>			
		</tr>
	</table>
	</form>
</body>
</html>
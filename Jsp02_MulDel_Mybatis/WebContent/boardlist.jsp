<%@page import="com.mul.biz.MDBoardBiz"%>
<%@page import="com.mul.dto.MDBoardDto" %>
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
									<!-- 제이쿼리 가장 마지막 버전 하지만 이렇게쓰면 버전바뀐지도 몰라서 안쓰는게좋음 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	function allChk(bool){
		//전체 선택하기
		$("input[name=chk]").each(function(){
			$(this).prop("checked" , bool);	
		});
	}
	
	$(function(){
		$("#muldelform").submit(function(){
			if($("#muldelform input:checked").length == 0){
				alert("하나 이상 체크해주세요!!");
				return false;
			}
		});
		
		$("input[name=chk]").click(function(){
			if($("input[name=chk]").length==$("input[name=chk]:checked").length){
				$("input[name=all]").prop("checked",true);
			}else{
				$("input[name=all]").prop("checked",false);
			}
		});
	});
	
	
	
</script>
</head>

<%
MDBoardBiz biz = new MDBoardBiz();
List<MDBoardDto> list = null;
String text = request.getParameter("search");
if(text != null && text.trim() != "") {
	list = biz.search(text);
} else {
	list = biz.selectList();
}


%>

<body>

	<%@ include file="./form/header.jsp" %>
	
	<h1>글 목록</h1>
	
	<form action="muldel.jsp" method="post" id="muldelform">
	 
		<table border="1">
			<col width="30px"/>
			<col width="50px"/>
			<col width="100px"/>
			<col width="300px"/>
			<col width="100px"/>
			<tr>
				<th><input type="checkbox" name="all" onclick="allChk(this.checked);"/></th>
				<th>번  호</th>
				<th>작성자</th>
				<th>제  목</th>
				<th>작성일</th>
			</tr>	
<%
	if(list.size() == 0){
%>			
			<tr>
				<td colspan="5">--------작성된 글이 없습니다-------</td>
			</tr>
<%
	}else{
		for(int i = 0; i < list.size(); i++){
	
%>	
			<tr>
				<td><input type="checkbox" name="chk" value="<%=list.get(i).getSeq()%>"/></td>
				<td><%=list.get(i).getSeq()%></td>
				<td><%=list.get(i).getWriter() %></td>
				<td><a href="boardselectone.jsp?seq=<%=list.get(i).getSeq()%>"><%=list.get(i).getTitle() %></a></td>
				<td><%=list.get(i).getRegdate() %></td>
			</tr>
<%
		}
	}	
%>
		<tr>
			<td colspan="5">
				
				<input type="submit" value="삭제"/>
				<input type="button" value="글쓰기" onclick="location.href='boardinsert.jsp'"/>
			</td>
		</tr>
		</table>
	</form>
	
	<form action="boardlist.jsp" method="post">
	<input type="text" name="search" />
	<input type="submit" value="검색" />
	</form>
	
	<%@ include file="./form/footer.jsp" %>

</body>
</html>
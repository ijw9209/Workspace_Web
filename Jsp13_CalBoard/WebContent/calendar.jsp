<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

a{
text-decoration: none;
color: green;
}
#calendar{border-collapse: collapse;
		  border: 1px solid gray;
		  }
#calendar th{
	width: 80px;
	border: 1px solid gray;
}
#calendar td{
	width: 80px;
	height: 80px;
	border:  1px solid gray;
	text-align: left;
	vertical-align: top;
	position: relative;
}
</style>

<script type="text/javascript" src="js/jquery-3.4.1.js" ></script>
<script type="text/javascript">

</script>
</head>
<%
	Calendar cal = Calendar.getInstance();

	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH) + 1;
	
	String paramYear = request.getParameter("year");
	String paramMonth = request.getParameter("month");
	if(paramYear != null){
		year = Integer.parseInt(paramYear);
	}
	if(paramMonth != null){
		month = Integer.parseInt(paramMonth);
	}
	if(month > 12){
		month = 1;
		year++;
	}
	if(month < 1){
		month = 12;
		year--;
	}
	
	Calendar precal = Calendar.getInstance();
	precal.set(year , month-2 , 1);
	int prelastday = precal.getActualMaximum(precal.DATE);
	
	
	// "해당년도 , 해당 월 , 해당월의 1일"에 해당하는 Calendar
		cal.set(year, month-1 , 1);
		
	// 1일의 요일
		int start = cal.get(cal.DAY_OF_WEEK);
		
		int lastday = cal.getActualMaximum(cal.DATE);
	//해당 월의 마지막 일 (28,29,30,31)
		
		
%>
<body>
	
	<table id="calendar" border="1">
		<caption>
			<a href="calendar.jsp?year=<%=year-1%>&month=<%=month%>">◀◀</a>
			<a href="calendar.jsp?year=<%=year%>&month=<%=month-1%>">◁</a>
			<span class="y"><%=year %></span>년
			<span class="m"><%=month %></span>월
			<a href="calendar.jsp?year=<%=year%>&month=<%=month+1%>">▷</a>
			<a href="calendar.jsp?year=<%=year+1%>&month=<%=month%>">▶▶</a>
		</caption>
		<tr id="trtr">
			<th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>
		</tr>
		<tr>
<%
	int cnt=start-1;
	for(int i=1; i<start; i++){
		cnt--;
%>		
		<td><%=prelastday-cnt %></td>
<%
	}
%>		
		
<%
	for(int i = 1 ; i <= lastday; i++){
	
%>		
		
			<td><a href="cal.do?command=callist&year=<%=year%>&month=<%=month%>&date=<%=i%>"><%= i%></a>
				<a href="insertcalboard.jsp?year=<%=year%>&month=<%=month%>&date=<%=i%>&lastday=<%=lastday%>">
				<img alt="일정 추가" src="image/pen.png" style="width: 10px; height: 10px;">
				</a>
				<div></div>
			</td>
<%
		if(start % 7 == 0 ){
%>
		</tr><tr>	
<%				

		}
	start++;
	}
	//마지막 날의 요일 = ??	
	//int last = (start + lastday-1)%7;
	//남은 칸의 갯수= (7-(start + lastday-1)%7)%7;
	
	
	//System.out.println("last" + last);
	int st = cal.get(cal.DAY_OF_WEEK);
	for(int i=0; i<(7-(st + lastday-1)%7)%7; i++){
		
		out.print("<td>"+(i+1)+"</td>");
	
	}
	
%>	
			
		</tr>
		
		
	</table>
	
</body>
</html>
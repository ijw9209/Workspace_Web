package com.calendar.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cal.dao.CalDao;
import com.cal.dao.Util;
import com.cal.dto.CalDto;

/**
 * Servlet implementation class CalServlet
 */
@WebServlet("/cal.do")
public class CalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8 ");
			
			String command = request.getParameter("command");
			System.out.println("< " + command + " >");
			
			CalDao dao = new CalDao();
			if(command.equals("calendar")) {
				response.sendRedirect("calendar.jsp");
			}else if(command.equals("insertcal")) {
				String id = request.getParameter("id");
				
				String year = request.getParameter("year");
				String month = request.getParameter("month");
				String date = request.getParameter("date");
				String hour = request.getParameter("hour");
				String min = request.getParameter("min");
				
				String mdate = year + Util.isTwo(month)+Util.isTwo(date) +Util.isTwo(hour)+Util.isTwo(min);
				
				String title =request.getParameter("title");
				String content = request.getParameter("content");
				
				
				int res = dao.insertCalBoard(new CalDto(0,id,title,content,mdate,null));
				if(res > 0) {
					response.sendRedirect("cal.do?command=calendar");
				}else {
					dispatch(request, response, "error.jsp");
				}
				
			}else if(command.equals("callist")) {
				String year = request.getParameter("year");
				String month = request.getParameter("month");
				String date = request.getParameter("date");
				
				String mdate = year + Util.isTwo(month) + Util.isTwo(date);
				
				
				int page = Integer.parseInt(request.getParameter("page"));	//현재페이지번호
				System.out.println("page>>" + page);
				int listCount = 5;	//한 화면에 뿌릴 데이터개수
				int allCount = dao.pageList("kh", mdate).size();	//전체 게시글 개수
				System.out.println("allCount >>" + allCount);
				int totalPage = (allCount -1) /listCount + 1;
				int blockCount = 5; // 이동을 위한 페이지 표시에 나타나는 숫자의 표시 갯수 ([1] [2] [3])
				int absolutePage = 1; // 페이지를 넘겼을때 시작되는 첫번째 번호
				int endPage = 0; // 페이지 마지막번호
				
				if(page < 1) {
					page = 1;
				}else if(page > totalPage) {
					page = totalPage;
				}
				if(page%5 == 0) {
					absolutePage = ((page/5)*5)-4;
					endPage = (page /5 )*5;
				}else {
					absolutePage = ((page/5)*5)+1;
					endPage = ((page/5)*5)+5;
				}
				
				if(endPage > totalPage) {
					endPage = totalPage;
				}
				
				int start = (page -1)*listCount + 1;
				int end = page * listCount;
				System.out.println("start >> " + start);
				System.out.println("end >> " + end);
				List<CalDto> list = dao.getCalList("kh", mdate , start , end);
				request.setAttribute("list", list);
				
				request.setAttribute("page", page);
				request.setAttribute("blockCount", blockCount);
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("absolutePage", absolutePage);
				request.setAttribute("endPage", endPage);
				
				dispatch(request, response, "callist.jsp");
			}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public void dispatch(HttpServletRequest request, HttpServletResponse response,String url) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	public void alert(HttpServletResponse response , String text, String url) throws IOException {
		
		PrintWriter out = response.getWriter();
		String js = "<script type=\"text/javascript\">\r\n" + 
				"	alert('"+text+"');\r\n" + 
				"	location.href='cal.do?command="+url+"'\r\n" + 
				"</script>";
		out.println(js);
	}
}

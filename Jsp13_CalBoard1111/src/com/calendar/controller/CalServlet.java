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
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				
				String year = request.getParameter("year");
				String month = request.getParameter("month");
				String date = request.getParameter("date");
				String hour = request.getParameter("hour");
				String min = request.getParameter("min");
				
				String mdate = year + Util.isTwo(month) + Util.isTwo(date) + Util.isTwo(hour) + Util.isTwo(min);
				int res = dao.insertCal(new CalDto(0,id,title,content,mdate,null));
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
				List<CalDto> list = dao.callist("kh", mdate);
				request.setAttribute("list", list);
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

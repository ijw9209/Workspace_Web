package com.my.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.my.biz.MyBoardBiz;
import com.my.dao.MyBoardDao;
import com.my.dto.MyBoardDto;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MyBoardBiz biz = new MyBoardBiz();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("< " + command + " >");
		PrintWriter out = response.getWriter();
		//웹에서 로그찍고싶으면 로그4j??이거찾으렴
		if(command.equals("list")) {
			// biz > dao > db에가서 list 리턴 받아오자.
			//request 객체에 담자.
			List<MyBoardDto> list = biz.selectList();
			request.setAttribute("list",list);
			
			dispatch(request, response, "mylist.jsp");
		}else if(command.equals("selectone")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			MyBoardDto dto = biz.selectone(myno);
			request.setAttribute("dto", dto);
			dispatch(request, response,"selectone.jsp");
		}else if(command.equals("updateform")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			MyBoardDto dto = biz.selectone(myno);
			request.setAttribute("dto", dto);
			dispatch(request, response,"updateform.jsp");
		}else if(command.equals("updateres")) {

			int myno = Integer.parseInt(request.getParameter("myno"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			MyBoardDto dto = new MyBoardDto();
			dto.setMyno(myno);
			dto.setMytitle(title);
			dto.setMycontent(content);
			int res = biz.update(dto);
			
			if(res > 0) {
				//alert(response, "selectone&myno="+myno, "update 성공!");
				out.println("<script>");

				out.println("alert('update성공!!');");
				out.println("location.href='my.do?command=selectone&myno="+myno+"'");
				out.println("</script>");
			}else {
				out.println("<script>");

				out.println("alert('update실패!!');");
				out.println("location.href='my.do?command=updateform&myno="+myno+"'");
				out.println("</script>");
			}
		}else if(command.equals("insert")) {
			dispatch(request, response,"insert.jsp");
		}else if(command.equals("insertres")) {
			String name = request.getParameter("name");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			MyBoardDto dto = new MyBoardDto();
			dto.setMyname(name);
			dto.setMytitle(title);
			dto.setMycontent(content);
			
			int res = biz.insert(dto);
			if(res > 0) {
				out.println("<script>");

				out.println("alert('insert성공!!');");
				out.println("location.href='my.do?command=list'");
				out.println("</script>");
				
			}else {
				out.println("<script>");

				out.println("alert('insert실패!!');");
				out.println("location.href='my.do?command=insert'");
				out.println("</script>");
			}
		}else if(command.equals("delete")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			int res = biz.delete(myno);
			
			if(res > 0) {
				out.println("<script>");

				out.println("alert('delete성공!!');");
				out.println("location.href='my.do?command=list'");
				out.println("</script>");
			}else {
				out.println("<script>");

				out.println("alert('delete실패!!');");
				out.println("location.href='my.do?command=selectone&myno="+myno+"'");
				out.println("</script>");
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	public void dispatch(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		
		//requestdispatcher 객체를 받아서 forward해줄수있는 객체 url로
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	//alert ??? 서블릿에서 찍는법 alert찍는 메소드 하나만들어놓고 해보렴
	public void alert(HttpServletResponse response, String url,String text) throws IOException {
		PrintWriter out = response.getWriter();
		String js = "<script type=\"text/javascript\">\r\n" + 
				"		alert('"+text+"');\r\n" + 
				"		location.href='my.do?command="+url+"';\r\n" + 
				"	</script>";
		out.println(js);
	}
}

package com.answer.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.answer.biz.LoginBiz;
import com.answer.dto.LoginDto;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String command = request.getParameter("command");
		System.out.println(" < "+command + " >");
		LoginBiz biz = new LoginBiz();
		HttpSession session = request.getSession();
		if(command.equals("login")) {
			dispatch(request, response, "login.jsp");
		}else if(command.equals("loginres")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			LoginDto login = biz.Login(id, pw);
			if(login != null ) {
				session.setAttribute("dto", login);
				session.setMaxInactiveInterval(3600);
				if(login.getMyrole().equals("ADMIN")) {
					response.sendRedirect("admin.jsp");
				}else if(login.getMyrole().equals("USER")) {
					response.sendRedirect("user.jsp");
				}
			}else {
				alert(response, "id와 pw를 다시 확인해주세요", "login");
			}
		}else if(command.equals("regist")) {
			response.sendRedirect("registform.jsp");
		}else if(command.equals("idChk")) {
			String id = request.getParameter("id");
			int res = biz.idChk(id);
			response.sendRedirect("idchk.jsp?res=" +res);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void dispatch(HttpServletRequest request, HttpServletResponse response,String url) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	public void alert(HttpServletResponse response,String text,String url) throws IOException {
		PrintWriter out = response.getWriter();
		String js = "<script type=\"text/javascript\">\r\n" + 
				"alert('"+text+"');\r\n" + 
				"location.href='login.do?command="+url+"'\r\n" + 
				"</script>";
		out.println(js);
	}
}

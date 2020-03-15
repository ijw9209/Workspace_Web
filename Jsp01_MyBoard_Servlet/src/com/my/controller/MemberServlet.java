package com.my.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.biz.MemberMyBiz;
import com.my.dto.MemberMyDto;
import com.my.dto.MyBoardDto;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String command = request.getParameter("command");
		System.out.println("<" + command +">");
		HttpSession session = request.getSession();
		MemberMyBiz biz = new MemberMyBiz();
		
		MemberMyDto dto = new MemberMyDto();
		session.setAttribute("dto", dto);
		
		if(command != null) {
			
			if(command.equals("login")) {
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				
				MemberMyDto login = biz.login(id, pw);
				
				if(login.getMyid() != null) {
					session.setAttribute("dto", login);
					session.setMaxInactiveInterval(3600);
					
					if (login.getMyrole().equals("ADMIN")) {
						response.sendRedirect("adminmain.jsp");
					} else if (login.getMyrole().equals("USER")) {
						response.sendRedirect("usermain.jsp");
					}
					
				}else {
					System.out.println("login실패!");
				}
			}
			
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void dispatch(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		
		//requestdispatcher 객체를 받아서 forward해줄수있는 객체 url로
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
}

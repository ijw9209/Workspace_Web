package com.el.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.el.dto.Score;

/**
 * Servlet implementation class controller
 */
@WebServlet("/controller")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		
		String command = request.getParameter("command");
		
		if(command.equals("basic")) {
			response.sendRedirect("basic-arithmetic.jsp");
		}else if(command.equals("eltest")) {
			Score sc = new Score();
			sc.setName("홍길동");
			sc.setKor(100);
			sc.setEng(70);
			sc.setMath(60);
			request.setAttribute("score", sc);
			RequestDispatcher dispatch = request.getRequestDispatcher("eltest.jsp");
			dispatch.forward(request, response);
		}else if(command.equals("jstl")) {
			
			List<Score> list = new ArrayList<Score>(); 
			for(int i = 10; i<50; i+=10) {
				Score sc = new Score("번호"+i,50+i,50+i,50+i);
				list.add(sc);
			}
			
			request.setAttribute("list", list);
			RequestDispatcher dispatch = request.getRequestDispatcher("jstltest.jsp");
			dispatch.forward(request, response);
		}else if(command.equals("usebean")) {
			response.sendRedirect("usebeantest.jsp");
		}
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

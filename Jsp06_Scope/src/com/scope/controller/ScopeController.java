package com.scope.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/scope.do")
public class ScopeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScopeController() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String reqId = (String)request.getAttribute("reqId");
		String sessionId = (String)request.getSession().getAttribute("sessionId");
		String appId = (String)getServletContext().getAttribute("appId");
		
		System.out.println("request : "+reqId);
		System.out.println("session : "+sessionId);
		System.out.println("application :" + appId);
		
		PrintWriter out = response.getWriter();
//		String html = "<table border=1>"
//					+"<tr>"
//					+"<th>scope</th><th>value</th>"
//					+"</tr>"
//					+"<tr>"
//					+"<tr>"
//						+"<td>request</td><td>"+reqId+"</td>"
//					+"</tr>"
//					+"<tr>"
//					+"<td>session</td><td>"+sessionId+"</td>"
//					+"</tr>"
//					+"<tr>"
//					+"<td>application</td><td>"+appId+"</td>"
//					+"</tr>"
//					+"</table>";
		
		out.println("<table border='1'");
		out.println("<tr>");
		out.println("<th>scope</th>");
		out.println("<th>value</th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>request</th>");
		out.println("<td>"+reqId+"</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>session</th>");
		out.println("<td>"+sessionId+"</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>application</th>");
		out.println("<td>"+appId+"</td>");
		out.println("</tr>");
		
		out.println("</table>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

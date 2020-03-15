package com.hello.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloController
 */
// /꼭 있어야함 HelloController class를 controller.do라는게들어올때 사용하겟다
@WebServlet("/controller.do") //web.xml 에써잇는것을 <<얘가해준다
//서블릿 만드는 방법
//1.web.xml 에서 서블릿 태그 url전달해주겠다 설정방법
//2. @WebServlet 은 위에꺼랑 다른객체 init-param 사용할수없음
public class HelloController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String initParam;
   
    public HelloController() {
    	System.out.println("servlet "
    			+ "생성자!!!");
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	System.out.println("Servelt init!!");
    	
    	System.out.println("context-param : " + config.getServletContext().getInitParameter("url"));
    	initParam = config.getInitParameter("driver");
    	System.out.println("init-param :" + initParam);
    }
    
    @Override		//HttpServlet꺼 DOGET = get방식 doPost= post
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Get 방식으로 들어옴!!");
    	System.out.println("init-param :" + initParam);
    	
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");
    	String command = request.getParameter("command");
    	System.out.println("command : " + command);
    	
    	
    	PrintWriter out = response.getWriter();
    	out.println("<h1 style='background-color:pink'>Hello Servlet</h1>");
    	out.println("<a href='index.html'>돌아가기</a>");
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Post방식으로 들어옴!!");
    	System.out.println("init-param : " + initParam);
    	
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=UTF-8");
    	String command = request.getParameter("command");
    	System.out.println("command : " + command);

    	PrintWriter out = response.getWriter();
    	out.println("<h1 style='background-color:skyblue'>Hello Servlet(post)</h1>");
    	out.println("<a href='index.html'>돌아가기</a>");
    }
    @Override
    public void destroy() {
    	//웹 서버가 종료될 때 실행되어 메모리를 해제한다.
    	// 서블릿이 제거 되기 전 한번 호출 ,서블릿이 메모리에서 제거하는 메소드
    	System.out.println("destroy!!!!");
    }
}

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

import com.my.biz.MyBoardBiz;
import com.my.dao.MyBoardDao;
import com.my.dto.MyBoardDto;



@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("< " + command + " >");
		MyBoardBiz biz = new MyBoardBiz();
		MyBoardDto dto = new MyBoardDto();
		if(command != null) {
			
			if(command.equals("list")) {
				List<MyBoardDto> list = biz.selectList();
				request.setAttribute("list", list);
				dispatcher(request, response, "mylist.jsp");
			}else if(command.equals("muldel")) {
				String[] myno = request.getParameterValues("chk");
				if(myno == null || myno.length == 0) {
					alert(response, "하나라도 체크해주세요", "list");
				}else {
					if(biz.multidelete(myno)) {
						alert(response, "multi delete 성공!", "list");
					}else {
						alert(response, "multi delete 실패 ㅠ","list");
					}
				}
			}else if(command.equals("insert")) {
				dispatcher(request, response, "insert.jsp");
			}else if(command.equals("insertres")) {
				String name = request.getParameter("name");
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				
				dto.setMyname(name);
				dto.setMytitle(title);
				dto.setMycontent(content);
				int res = biz.insert(dto);
				if(res > 0) {
					alert(response, "insert 성공!", "list");
				}else {
					alert(response, "insert 실패!", "list");
				}
			}else if(command.equals("selectone")) {
				int myno = Integer.parseInt(request.getParameter("myno"));
				dto = biz.selectone(myno);
				request.setAttribute("dto", dto);
				dispatcher(request, response, "selectone.jsp");
			}else if(command.equals("update")) {
				int myno = Integer.parseInt(request.getParameter("myno"));
				dto = biz.selectone(myno);
				request.setAttribute("dto", dto);
				dispatcher(request, response, "update.jsp");
			}else if(command.equals("updateres")) {
				int myno = Integer.parseInt(request.getParameter("myno"));
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				
				dto.setMyno(myno);
				dto.setMytitle(title);
				dto.setMycontent(content);
				int res = biz.update(dto);
				if(res > 0) {
					alert(response, "update성공!", "selectone&myno=" + myno);
				}else {
					alert(response, "update실패!", "update&myno="+myno);
				}
			}else if(command.equals("delete")) {
				int myno = Integer.parseInt(request.getParameter("myno"));
				int res = biz.delete(myno);
				if(res > 0) {
					alert(response, "delete 성공!", "list");
				}else {
					alert(response, "delete 실패", "list");
				}
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void dispatcher(HttpServletRequest request,HttpServletResponse response, String url ) throws ServletException, IOException {
		RequestDispatcher dispacher = request.getRequestDispatcher(url);
		dispacher.forward(request, response);
	}
	public void alert(HttpServletResponse response,String text,String url) throws IOException {
		PrintWriter out = response.getWriter();
		String js = "<script type=\"text/javascript\">\r\n" + 
				"	alert('"+ text +"');\r\n" + 
				"	location.href='my.do?command="+url+"';\r\n" + 
				"	</script>";
		out.println(js);
	}
	
}

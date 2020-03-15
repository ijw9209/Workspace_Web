package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.MVCBoardBiz;
import com.mvc.biz.MVCBoardBizImpl;
import com.mvc.dto.MVCBoardDto;


@WebServlet("/MVCServlet")
public class MVCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String command = request.getParameter("command");
		System.out.println("< " + command + " >");
		MVCBoardBiz biz = new MVCBoardBizImpl();
		MVCBoardDto dto = new MVCBoardDto();
		if(command.equals("list")) {
			List<MVCBoardDto> list = biz.selectList();
			request.setAttribute("list", list);
			dispatch(request, response, "boardlist.jsp");
		}else if(command.equals("insert")) {
			response.sendRedirect("insert.jsp");
		}else if(command.equals("insertres")) {
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			int res = biz.insert(dto);
			if(res > 0) {
				alert(response, "insert 성공!", "list");
			}else {
				alert(response, "insert 실패", "list");
			}
			
		}else if(command.equals("selectone")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			dto = biz.selectone(seq);
			request.setAttribute("dto", dto);
			dispatch(request, response, "selectone.jsp");
		}else if(command.equals("update")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			dto = biz.selectone(seq);
			request.setAttribute("dto", dto);
			dispatch(request, response, "update.jsp");
		}else if(command.equals("updateres")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			dto.setSeq(seq);
			dto.setTitle(title);
			dto.setContent(content);
			int res = biz.update(dto);
			if(res > 0) {
				alert(response, "update 성공!", "selectone&seq="+seq);
			}else {
				alert(response, "update 실패!", "update");
			}
		}else if(command.equals("delete")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			int res = biz.delete(seq);
			
			if(res > 0) {
				alert(response, "delete 성공!", "list");
			}else {
				alert(response, "delete 실패!", "list");
			}
		}else if(command.equals("muldel")) {
			
			String[] seq = request.getParameterValues("chk");
			
			if(seq == null || seq.length == 0) {
				alert(response, "하나라도 체크해주세요", "list");
			}else {
			if(biz.muldel(seq)) {
				alert(response, "muldel 성공!", "list");
			}else {
				alert(response, "muldel 실패!", "list");
			}
			}
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
	public void alert(HttpServletResponse response,String text,String url) throws IOException {
		PrintWriter out = response.getWriter();
		String js = "<script type=\"text/javascript\">\r\n" + 
				"	alert('"+text+"');\r\n" + 
				"	location.href='mvc.do?command="+url+"'\r\n" + 
				"</script>";
		out.println(js);
	}
}

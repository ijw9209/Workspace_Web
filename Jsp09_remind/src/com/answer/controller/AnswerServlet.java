package com.answer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.answer.biz.AnswerBiz;
import com.answer.biz.AnswerBizImpl;
import com.answer.dto.AnswerDto;

@WebServlet("/AnswerServlet")
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String command = request.getParameter("command");
		System.out.println("< " + command + " >");
		AnswerDto dto = new AnswerDto();
		AnswerBiz biz = new AnswerBizImpl();
		if(command.equals("list")) {
			
			List<AnswerDto> list = biz.selectList();
			request.setAttribute("list",list);
			dispatch(request, response, "boardlist.jsp");
		}else if(command.equals("selectone")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			dto = biz.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch(request, response, "selectone.jsp");
		}else if(command.equals("insertanswer")) {
			dispatch(request, response, "insertanswer.jsp");
		}else if(command.equals("insertanswerres")) {
			int boardno = Integer.parseInt(request.getParameter("parentsboardno"));
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			dto.setBoardno(boardno);
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			int res = biz.answerProc(dto);
			if(res > 1) {
				alert(response, "답글달기성공", "list");
			}else {
				alert(response, "답글달기실패", "list");
			}
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
				"	alert('"+text+"');\r\n" + 
				"	location.href='answer.do?command="+url+"';\r\n" + 
				"</script>	";
		out.println(js);
	}

}

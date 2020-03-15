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


@WebServlet(urlPatterns = { "/selectall" , "/insert" , "/insertres" , "/selectone" , "/update" ,"/updateres" ,"/delete"})
public class MVCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
		MVCBoardBiz biz;

	private void getRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		String command = request.getRequestURI();
		System.out.println("[ " + command +" ] ");
		
		biz = new MVCBoardBizImpl();
		
		if(command.endsWith("/selectall")) {
			doSelectAll(request,response);
		}else if(command.endsWith("/insert")) {
			doInsert(request,response);
		}else if(command.endsWith("/insertres")) {
			doInsertRes(request,response);
		}else if(command.endsWith("/selectone")) {
			doselectone(request,response);
		}else if(command.endsWith("/update")) {
			doupdate(request,response);
		}else if(command.endsWith("/updateres")) {
			doupdateres(request,response);
		}else if(command.endsWith("/delete")) {
			dodel(request,response);
		}
	}
	
	private void dodel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		int res = biz.delete(seq);
		if(res > 0) {
			alert(response, "delete 성공!", "selectall");
		}else {
			alert(response, "delete 실패!", "selectone?seq="+seq);
		}
		
	}

	private void doupdateres(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		MVCBoardDto dto = new MVCBoardDto();
		dto.setSeq(seq);
		dto.setTitle(title);
		dto.setContent(content);
		
		int res = biz.update(dto);
		if(res > 0) {
			alert(response, "update 성공!", "selectone?seq="+seq);
		}else {
			alert(response, "update실패!", "update");
		}
	}

	private void doupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		MVCBoardDto dto = new MVCBoardDto();
		dto = biz.selectone(seq);
		request.setAttribute("dto", dto);
		
		dispatch(request, response, "updateform.jsp");
	}

	private void doselectone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		MVCBoardDto dto = new MVCBoardDto();
		dto = biz.selectone(seq);
		request.setAttribute("dto", dto);
		dispatch(request, response, "selectone.jsp");
	}

	private void doInsertRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String writer = request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		MVCBoardDto dto = new MVCBoardDto();
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		
		int res = biz.insert(dto);
		if(res > 0) {
			alert(response, "insert 성공!", "selectall");
		}else {
			alert(response, "insert 실패!", "insert");
		}
	}

	private void doInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatch(request, response, "insertform.jsp");
	}

	private void doSelectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MVCBoardDto> list = biz.selectList();
		request.setAttribute("list", list);
		dispatch(request, response, "boardlist.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		getRequest(request,response);
		
		//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=UTF-8"); 
//		
//		String command = request.getParameter("command");
//		System.out.println("< " + command + " > ");
//		MVCBoardDto dto = new MVCBoardDto();
//		MVCBoardBiz biz = new MVCBoardBizImpl();
//		if(command.equals("list")) {
//			
//			List<MVCBoardDto> list = biz.selectList();
//			request.setAttribute("list", list);
//			dispatch(request, response, "boardlist.jsp");
//		}else if(command.equals("insert")) {
//			dispatch(request, response, "insertform.jsp");
//		}else if(command.equals("insertres")) {
//			String writer = request.getParameter("name");
//			String title = request.getParameter("title");
//			String content = request.getParameter("content");
//			
//			dto.setWriter(writer);
//			dto.setTitle(title);
//			dto.setContent(content);
//			
//			int res = biz.insert(dto);
//			if(res > 0) {
//				alert(response, "insert 성공!", "list");
//			}else {
//				alert(response, "insert 실패!", "insert");
//			}
//		}else if(command.equals("selectone")) {
//			int seq = Integer.parseInt(request.getParameter("seq"));
//			dto = biz.selectone(seq);
//			request.setAttribute("dto", dto);
//			dispatch(request, response, "selectone.jsp");
//		}else if(command.equals("updateform")) {
//			int seq = Integer.parseInt(request.getParameter("seq"));
//			dto = biz.selectone(seq);
//			request.setAttribute("dto", dto);
//			dispatch(request, response, "updateform.jsp");
//		}else if(command.equals("updateres")) {
//			int seq = Integer.parseInt(request.getParameter("seq"));
//			String title = request.getParameter("title");
//			String content = request.getParameter("content");
//			dto.setSeq(seq);
//			dto.setTitle(title);
//			dto.setContent(content);
//			
//			int res = biz.update(dto);
//			if(res > 0) {
//				alert(response, "update 성공!", "selectone&seq="+seq);
//			}else {
//				alert(response, "update실패!", "updateform");
//			}
//			
//		}else if(command.equals("delete")) {
//			int seq = Integer.parseInt(request.getParameter("seq"));
//			int res = biz.delete(seq);
//			if(res > 0) {
//				alert(response, "delete 성공!", "list");
//			}else {
//				alert(response, "delete 실패!", "selectone&seq="+seq);
//			}
//			
//		}else if(command.equals("muldel")) {
//			String[] seq = request.getParameterValues("chk");
//			
//			if(seq == null || seq.length ==0) {
//				alert(response, "삭제할 글을 하나이상 체크해주세요!", "list");
//			}else {
//				if(biz.multidelete(seq)) {
//					alert(response, "체크된 글 삭제 성공!", "list");
//				}else {
//					alert(response, "체크된 글 삭제 실패!", "list");
//				}
//			}
//		}
	}








	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void dispatch(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	public void alert(HttpServletResponse response,String text,String url) throws IOException {
		PrintWriter out = response.getWriter();
		
		String js = "<script type=\"text/javascript\">\r\n" + 
				"		alert('"+text+"');\r\n" + 
				"		location.href='"+url+"';\r\n" + 
				"	</script>";
		
		out.println(js);
	}
}

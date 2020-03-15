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

import com.answer.biz.AnswerBoardBiz;
import com.answer.biz.AnswerBoardBizImpl;
import com.answer.dto.AnswerBoardDto;


@WebServlet("/Answer")
public class Answer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		
		
		String command = request.getParameter("command");
		System.out.println("< " + command + " > ");
		
		AnswerBoardDto dto = new AnswerBoardDto();
		AnswerBoardBiz biz = new AnswerBoardBizImpl();
		
		if(command.equals("list")) {
			List<AnswerBoardDto> list = biz.selectlist();
			request.setAttribute("list", list);
			dispatch(request, response, "boardlist.jsp");
		}else if(command.equals("insert")) {
			dispatch(request, response, "insertform.jsp");
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
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			dto = biz.selectone(boardno);
			request.setAttribute("dto", dto);
			dispatch(request, response, "selectone.jsp");
			
		}else if(command.equals("update")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			dto = biz.selectone(boardno);
			request.setAttribute("dto", dto);
			dispatch(request, response, "updateform.jsp");
		}else if(command.equals("updateres")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			dto.setBoardno(boardno);
			dto.setTitle(title);
			dto.setContent(content);
			
			int res = biz.update(dto);
			if(res > 0) {
				alert(response, "update 성공!", "selectone&boardno="+boardno);
			}else {
				alert(response, "update 실패", "update&boardno="+boardno);
			}
		}else if(command.equals("delete")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			int res = biz.delete(boardno);
			if(res > 0) {
				alert(response, "delete 성공!", "list");
			}else {
				alert(response, "delete 실패", "list");
			}
		}else if(command.equals("insertanswer")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			dto = biz.selectone(boardno);
			request.setAttribute("dto", dto);
			dispatch(request, response, "answerform.jsp");
		}else if(command.equals("answerres")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			dto.setWriter(writer);
			dto.setBoardno(boardno);
			dto.setTitle(title);
			dto.setContent(content);
			
			int res = biz.answerProc(dto);
			if(res > 0) {
				alert(response, "답글 성공!", "list");
			}else {
				alert(response, "답글 실패!", "list");
			}
			
		}else if(command.equals("deleteanswer")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			int res = biz.updatedelflag(boardno);
			if(res > 0) {
				alert(response, "답글 삭제 성공!!", "list");
			}else {
				alert(response, "답글 삭제 실패!", "list");
			}
		}else if(command.equals("paging")) {
			int page = Integer.parseInt(request.getParameter("page")); //현재페이징번호
			int allCount = biz.selectlist().size(); //전체게시글개수
			int listCount = 5; //한 화면에 뿌릴 데이터 개수
			int totalPage = (allCount -1) / listCount + 1; // 전체 페이지 개수
			int blockCount = 5;  // 이동을 위한 페이지 표시에 나타나는 숫자의 표시 갯수( 예 [1] [2] [3])
			int absolutePage = 0;  // 페이지를 넘겼을 때 시작되는 첫번째 게시물의 시작 번호
			int endPage = 0; // 페이지 마지막 번호
			
			if(page < 1) {
				page = 1;
			}else if(page > totalPage) {
				page = totalPage;
			}
			if(page% 5 == 0) {
				absolutePage = ((page/5) *5) -4;
				endPage = (page / 5) * 5;
			}else {
				absolutePage = ((page /5) *5)+1;
				endPage = ((page/5) * 5) + 5;
			}
			
			if(endPage > totalPage) {
				endPage = totalPage;
			}
			
			int start = (page - 1)*listCount +1;
			int end = page * listCount;
			System.out.println("start >> " + start);
			System.out.println("end >> " + end);
			request.setAttribute("list", biz.pagelist(start, end));
			request.setAttribute("page", page);
			request.setAttribute("blockCount", blockCount);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("absolutePage", absolutePage);
			request.setAttribute("endPage", endPage);
			dispatch(request, response, "paging.jsp");
			
			
			
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
				"		alert('"+text+"');\r\n" + 
				"		location.href='answer.do?command="+url+"'\r\n" + 
				"</script>	";
		out.println(js);
	}
}

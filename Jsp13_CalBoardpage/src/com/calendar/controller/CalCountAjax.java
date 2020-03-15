package com.calendar.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cal.dao.CalDao;
import com.sun.org.apache.xml.internal.serialize.Printer;

import net.sf.json.JSONObject;


@WebServlet("/CalCountAjax.do")
public class CalCountAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8 ");
		
		String id = request.getParameter("id");
		String yyyyMMdd = request.getParameter("yyyyMMdd");
		System.out.println("전달된 param : " + id + " , " + yyyyMMdd);
		
		CalDao dao = new CalDao();
		int cnt = dao.getCalCount("kh", yyyyMMdd);
		System.out.println("일정 갯수 : " + cnt);
		
		Map<String , Integer> map = new HashMap<String, Integer>();
		map.put("cnt", cnt);
		
		JSONObject obj = JSONObject.fromObject(map);
		
		PrintWriter out = response.getWriter();
		obj.write(out);
	}

}

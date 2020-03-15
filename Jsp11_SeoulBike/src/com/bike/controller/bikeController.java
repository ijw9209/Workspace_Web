package com.bike.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bike.dao.BikeDao;
import com.bike.dto.BikeDto;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@WebServlet("/bike.do")
public class bikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String command = request.getParameter("command");
		System.out.println("< " + command + " >");
		if(command.equals("first")) {
			response.sendRedirect("bike01.jsp");
		}else if(command.equals("first_db")) {
			String[] tmp = request.getParameterValues("bike");
			BikeDao dao = new BikeDao();
			
			String[] dtoVal = null;
			List<BikeDto> list = new ArrayList<BikeDto>();
			for(int i = 0; i < tmp.length; i++) {
				dtoVal = tmp[i].split("/");
				System.out.println(dtoVal[0] + dtoVal[1] + dtoVal[2] + dtoVal[3] + dtoVal[4] + dtoVal[5] + dtoVal[6] + dtoVal[7]);
				BikeDto dto = new BikeDto();
				dto.setRent_id(dtoVal[0]);
				dto.setAddr_gu(dtoVal[1]);
				dto.setContent_id(Integer.parseInt(dtoVal[2]));
				dto.setContent_nm(dtoVal[3]);
				dto.setNew_addr(dtoVal[4]);
				dto.setCradel_count(Integer.parseInt(dtoVal[5]));
				dto.setLongitude(Float.parseFloat(dtoVal[6]));
				dto.setLatitude(Float.parseFloat(dtoVal[7]));
				list.add(dto);
			}
		   int res = dao.insert(list);
		   if(res >0) {
			   System.out.println("성공");
			   response.sendRedirect("index.jsp");
		   }else {
			   System.out.println("실패!");
			   response.sendRedirect("index.jsp");
		   }
			
			
		}else if(command.equals("second")) {
			response.sendRedirect("bike02.jsp");
		}else if(command.equals("second_db")) {
			String obj = request.getParameter("obj");
			//System.out.println(obj);
			BikeDao dao = new BikeDao();
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(obj);
			List<BikeDto> list = new ArrayList<BikeDto>();
			for(int i = 0; i < element.getAsJsonObject().get("DATA").getAsJsonArray().size(); i++) {
				//System.out.println(element.getAsJsonObject().get("DATA").getAsJsonArray().get(i));
				JsonObject tmp = element.getAsJsonObject().get("DATA").getAsJsonArray().get(i).getAsJsonObject();
				JsonElement rent_id_je = tmp.get("rent_id");
				JsonElement addr_gu_je = tmp.get("addr_gu");
				JsonElement content_id_je = tmp.get("content_id");
				JsonElement content_nm_je = tmp.get("content_nm");
				JsonElement new_addr_je = tmp.get("new_addr");
				JsonElement cradle_count_je = tmp.get("cradle_count");
				JsonElement longitude_je = tmp.get("longitude");
				JsonElement latitude_je = tmp.get("latitude");
				
				String rent_id = rent_id_je.getAsString();
				String addr_gu = addr_gu_je.getAsString();
				int content_id = content_id_je.getAsInt();
				String content_nm = content_nm_je.getAsString();
				String new_addr = new_addr_je.getAsString();
				int cradle_count = cradle_count_je.getAsInt();
				double longitude = longitude_je.getAsDouble();
				double latitude = latitude_je.getAsDouble();
				
				BikeDto dto = new BikeDto();
				dto.setRent_id(rent_id);
				dto.setAddr_gu(addr_gu);
				dto.setContent_id(content_id);
				dto.setContent_nm(content_nm);
				dto.setNew_addr(new_addr);
				dto.setCradel_count(cradle_count);
				dto.setLongitude(longitude);
				dto.setLatitude(latitude);
				list.add(dto);
			}
			int res = dao.insert(list);
			if(res > 0) {
				System.out.println("성공");
			}else {
				System.out.println("실패");
			}
			PrintWriter out = response.getWriter();
			out.println(res);
		}
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

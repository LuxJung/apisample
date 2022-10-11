package org.lib.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



@WebServlet("/llib")
public class llibServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doHandler(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandler(request, response);
	}
	
	protected void doHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset-UTF-8");
		
		String KEY = "4d77734d5a726561313037496e6b5271";
		String TYPE = "json";
	    String SERVICE = "SeoulPublicLibraryInfo";

		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
		urlBuilder.append("/" +  URLEncoder.encode(KEY,"UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
		urlBuilder.append("/" +  URLEncoder.encode(TYPE,"UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
		urlBuilder.append("/" + URLEncoder.encode(SERVICE,"UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
		urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
		urlBuilder.append("/" + URLEncoder.encode("5","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
		// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
		String REQUEST = request.getParameter("list");
		// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
		urlBuilder.append("/" + URLEncoder.encode(REQUEST,"UTF-8")); /* 서비스별 추가 요청인자들*/
		System.out.println(REQUEST);
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/xml");
		System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
		BufferedReader rd;

		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				System.out.println(rd.readLine());
		} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			System.out.println(line);
				sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		sb.toString(); 
		/*
		JSONArray ja = new JSONArray();
		ja.add(sb.toString());
		JSONObject univ = new JSONObject();
		univ.put("univ", ja);
		
		String json;
		json = univ.toJSONString();
		System.out.println(json);*/
		/*String content = sb.toString();
        request.setAttribute("content", content);
        ServletContext context =getServletContext();
        RequestDispatcher dispatcher = 
        		context.getRequestDispatcher("/lliblist.jsp"); //넘길 페이지 주소
        dispatcher.forward(request, response);*/
		/* response.sendRedirect("lliblist.jsp"); */
	}

}

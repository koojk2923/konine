<%@page import="com.BookIng.notice.service.NoticeWriteService"%>
<%@page import="com.BookIng.notice.vo.NoticeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");

String title = request.getParameter("title");
String content = request.getParameter("content");
String startDate = request.getParameter("startDate");
String endDate = request.getParameter("endDate");

NoticeVO vo = new NoticeVO();
vo.setTitle(title);
vo.setContent(content);
vo.setStartDate(startDate);
vo.setEndDate(endDate);

NoticeWriteService service = new NoticeWriteService();
service.service(vo);

response.sendRedirect("list.jsp");

%>
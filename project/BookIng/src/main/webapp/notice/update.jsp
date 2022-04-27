<%@page import="com.BookIng.notice.service.NoticeUpdateService"%>
<%@page import="com.BookIng.notice.vo.NoticeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");


String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);
String title = request.getParameter("title");
String content = request.getParameter("content");
String startDate = request.getParameter("startDate");
String endDate = request.getParameter("endDate");

NoticeVO vo = new NoticeVO();

vo.setNo(no);
vo.setTitle(title);
vo.setContent(content);
vo.setStartDate(startDate);
vo.setEndDate(endDate);

NoticeUpdateService service = new NoticeUpdateService();
int result = service.service(vo);

response.sendRedirect("view.jsp?no=" + no);
%>
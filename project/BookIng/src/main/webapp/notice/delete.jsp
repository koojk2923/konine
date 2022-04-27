<%@page import="com.BookIng.notice.service.NoticeDeleteService"%>
<%@page import="com.BookIng.notice.vo.NoticeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);

NoticeVO vo = new NoticeVO();
vo.setNo(no);

NoticeDeleteService service = new NoticeDeleteService();
service.service(no);


response.sendRedirect("list.jsp");


%>
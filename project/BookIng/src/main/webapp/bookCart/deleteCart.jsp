<%@page import="com.BookIng.member.vo.LoginVO"%>
<%@page import="com.BookIng.bookCart.service.BookCartDeleteCartService"%>
<%@page import="com.BookIng.bookCart.service.BookCartDeleteService"%>
<%@page import="com.BookIng.bookCart.vo.BookCartVO"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
LoginVO loginVO = (LoginVO) session.getAttribute("login");
String id = loginVO.getId();
BookCartVO vo = new BookCartVO();
vo.setId(id);
System.out.println("도서 삭제 - vo: " + vo);

BookCartDeleteCartService service = new BookCartDeleteCartService();
service.service(vo);

response.sendRedirect("/bookBoard/list.jsp");


%>

<%@page import="com.BookIng.bookCart.service.BookCartDeleteService"%>
<%@page import="com.BookIng.bookCart.vo.BookCartVO"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String strCartNo = request.getParameter("cartNo");
long cartNo = Long.parseLong(strCartNo); 
BookCartDeleteService service = new BookCartDeleteService();
BookCartVO vo = new BookCartVO();
vo.setCartNo(cartNo);

service.service(cartNo);

response.sendRedirect("cartList.jsp");
%>

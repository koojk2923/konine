<%@page import="com.BookIng.qna.service.QnaDeleteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
// 여기가 자바 입니다.
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);

// 생성하고 호출 QnaDeleteService
QnaDeleteService service = new QnaDeleteService();
int result = service.service(no);

// 자동으로 리스트로 보낸다.
response.sendRedirect("list.jsp");

%>
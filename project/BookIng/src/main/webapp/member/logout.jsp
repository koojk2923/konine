<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//로그아웃 처리 -> 정해진 장소(session의 데이터를 지운다.)
session.removeAttribute("login");
System.out.println("logout.jsp - 로그아웃 처리가 되었습니다.");

//main이 없어서 공지사항으로 자동 이동
response.sendRedirect("../notice/list.jsp");

%>
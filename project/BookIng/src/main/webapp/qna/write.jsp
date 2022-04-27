<%@page import="com.BookIng.member.vo.LoginVO"%>
<%@page import="com.BookIng.qna.service.QnaWriteService"%>
<%@page import="com.BookIng.qna.vo.QnaVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
// 여기가 자바 부분 입니다.
// 1. 넘어오는 데이터 받기
// 데이터 수집
// gerParamater(name) : 문자열 name 과 같은 이름을 가진 인자 값을 가지고 온다.
String title = request.getParameter("title");
String content = request.getParameter("content");

// 아이디는 session에서 꺼낸다.
String id = ((LoginVO)session.getAttribute("login")).getId();

// Controller - Service - DAO : QnaVO 객체를 만들어서 전달한다.
QnaVO vo = new QnaVO();
vo.setTitle(title);
vo.setContent(content);
vo.setId(id);

System.out.println("질문 등록 - " + vo);

// DB에 등록 처리
QnaWriteService service = new QnaWriteService();
service.service(vo);

// 문의게시판 리스트로 자동 이동시킨다.
response.sendRedirect("list.jsp");
%>
<%@page import="com.BookIng.member.vo.MemberVO"%>
<%@page import="com.BookIng.member.service.MemberDeleteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//한글 처리
request.setCharacterEncoding("utf-8");
//데이터 수집
String id = request.getParameter("id");
String pw = request.getParameter("pw");
String tel = request.getParameter("tel");

//MemberVo에 담기
MemberVO vo = new MemberVO();
vo.setId(id);
vo.setPw(pw);
vo.setTel(tel);
System.out.println("회원 삭제 - vo: " + vo);

//생성하고 호출 MemberDeleteService
MemberDeleteService service = new MemberDeleteService();
int result = service.service(vo);

//자동으로 list로
if(result ==1) response.sendRedirect("list.jsp");
else out.println("삭제 오류 - 입력하신 정보를 확인해 주세요.");

%>
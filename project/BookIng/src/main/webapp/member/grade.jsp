<%@page import="com.BookIng.member.service.MemberGradeService"%>
<%@page import="com.BookIng.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String strGNo = request.getParameter("gradeNo");
int gradeNo = Integer.parseInt(strGNo);

MemberVO vo = new MemberVO();
vo.setId(id);
vo.setGradeNo(gradeNo);

System.out.println("회원정보 수정 : " + vo);
//MemberUpdateService -> MemberDAO.Update
MemberGradeService service = new MemberGradeService();
int result = service.service(vo);

//수정이 성공하면 회원 정보 보기로 자동이동 시킨다.
if(result == 1) {
	response.sendRedirect("view.jsp?id=" + id);
	return;
} else {
	out.println("아이디를 확인하십시오.");
}

%>

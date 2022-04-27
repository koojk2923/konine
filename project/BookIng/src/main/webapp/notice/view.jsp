<%@page import="com.BookIng.member.vo.LoginVO"%>
<%@page import="com.BookIng.notice.vo.NoticeVO"%>
<%@page import="com.BookIng.notice.service.NoticeViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);

NoticeViewService service = new NoticeViewService();
NoticeVO vo = service.service(no);
request.setAttribute("vo", vo);
vo.setContent(vo.getContent().replace("\n", "<br>"));

System.out.println(vo);
LoginVO loginVO = (LoginVO) session.getAttribute("login");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트</title>
<style type="text/css">
th {
	background: #ff9999;
	color: white;
}
h1.h1 {
  text-align: center;
  background: #ffcccc;
  color: #0000ff;
}
</style>
<link href='https://fonts.googleapis.com/css?family=Angkor' rel='stylesheet'>
<style>
body {
    font-family: 'Angkor';
}
</style>
</head>
<body>
<div class="container">
		<h1 class="h1">☆★☆이벤트 & 공지사항☆★☆</h1>
<table class="table">
   <tbody>
		<tr>
			<th>번호</th>
			<td>${vo.no }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${vo.title }</td>
		</tr>
		<tr>
			<th>시작일~종료일</th>
			<td>${vo.startDate } ~ ${vo.endDate }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${vo.content }</td>
		</tr>
<tr>
	<td colspan="2">
		<c:if test="${!empty login && login.gradeNo == 9}">
				<a href="updateForm.jsp?no=${vo.no }" class="btn btn-default">수정</a>
				<a href="delete.jsp?no=${vo.no }"class="btn btn-default"> 삭제</a>
			</c:if>			
				<a href="list.jsp?page=${param.page }&perPageNum=${param.perPageNum }" class="btn btn-default"> 리스트</a>
</td>
</tr>
    </tbody>
	</table>
</div>
</body>
</html>
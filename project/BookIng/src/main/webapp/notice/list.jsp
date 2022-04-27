<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Map"%>
<%@page import="com.BookIng.notice.service.NoticeViewService"%>
<%@page import="com.BookIng.member.vo.LoginVO"%>
<%@page import="com.BookIng.notice.vo.NoticeVO"%>
<%@page import="com.BookIng.notice.service.NoticeListService"%>
<%@page import="com.webjjang.util.PageObject"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>
<%
// 페이지 정보 받기
PageObject pageObject = PageObject.getInstance(request);
// 데이터 수집
// now - 현재 공지, old - 지난 공지, res - 예약 공지, all - 전체공지 -> pt(point)
// pt = pageObhect.period
String pt = request.getParameter("pt");
if (pt == null)
	pt = "now"; // 기본값 세팅 : 현재공지

pageObject.setPeriod(pt);

NoticeListService service = new NoticeListService();
List<NoticeVO> list = service.service(pageObject);
// 데이터 확인하기
System.out.println(list);

// 클릭한 버튼의 버튼의 스티일 문자열 변수
String style1 = "background: #000066; color: white;";
String style2 = "background: #3333ff; color: white;";
String style3 = "background: #3366ff; color: white;";
String style4 = "background: #6699ff; color: white;";

// sesssion에서 로그인 정보를 받아낸다.
LoginVO loginVO = (LoginVO) session.getAttribute("login");

request.setAttribute("list", list);
request.setAttribute("pt", pt);
request.setAttribute("style1", style1);
request.setAttribute("style2", style2);
request.setAttribute("style3", style3);
request.setAttribute("style4", style4);
request.setAttribute("pageObject", pageObject);

SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
Calendar cal = Calendar.getInstance();
cal.add(Calendar.DAY_OF_MONTH,-2); // 2일간 보이도록 하기위해서.
String nowday = format.format(cal.getTime());

request.setAttribute("nowday", nowday);
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

.dataRow:hover {
	cursor: grabbing;
	background: #99ffcc;
}

h1.h1 {
	text-align: center;
	background: #ffcccc;
	color: #0000ff;
}

#updateDate {
	display: none;
}
</style>
</head>
<body>
	<div class="container">
		<h1 class="h1">☆★☆이벤트 & 공지사항☆★☆</h1>
		<a href="list.jsp?pt=now" style='${(pt=="now")?style1:""}'
			class="btn btn-default">현재</a> <a href="list.jsp?pt=old"
			style='${(pt=="old")?style2:""}' class="btn btn-default">지난</a> <a
			href="list.jsp?pt=res" style='${(pt=="res")?style3:""}'
			class="btn btn-default">예약</a> <a href="list.jsp?pt=all"
			style='${(pt=="all")?style4:""}' class="btn btn-default">전체</a>
		<table class="table">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>시작일</th>
				<th>종료일</th>
				<!-- 				<th>등록일</th> -->
			</tr>
			<c:if test="${empty list }">
				<tr>
					<td colspan="5">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:if>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="vo">
					<tr
						onclick="document.location='view.jsp?no=${vo.no}&page=${pageObject.page }&perPageNum=${pageObject.perPageNum }'"
						class="dataRow">
						<td>${vo.no}</td>
						<td id="new">${vo.title}&nbsp;&nbsp;
						<c:if test="${vo.updateDate >= nowday }">
								<img class="upload"
									src="<c:url value='/upload/notice/new.jpg' />"
									style="width: 10px; height: 10px;">
							</c:if>
							</td>
						<td>${vo.startDate}</td>
						<td>${vo.endDate}</td>
						<td id="updateDate">${vo.updateDate}</td>
					</tr>
				</c:forEach>
			</c:if>
			<tr>
				<td colspan="5"><pageNav:pageNav listURI="list.jsp"
						pageObject="${pageObject }" query="&pt=${pt }"></pageNav:pageNav></td>
			</tr>
			<c:if test="${!empty login && login.gradeNo == 9}">
				<tr>
					<td colspan="5" style="text-align: center;"><a
						href="writeForm.jsp" class="btn btn-default">공지등록</a></td>
				</tr>
			</c:if>
		</table>
	</div>
</body>
</html>
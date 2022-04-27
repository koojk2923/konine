 <%@page import="com.webjjang.util.PageObject"%>
<%@page import="com.BookIng.qna.vo.QnaVO"%>
<%@page import="com.BookIng.qna.service.QnaViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%--  JSTL : 적용 --%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
// 여기가 자바 부분 입니다.

// 넘어오는 페이지 정보를 받아서 페이지 객체 생성
PageObject pageObject = PageObject.getInstance(request);

// 넘어오는 데이터 수집
String strNo  = request.getParameter("no");
long no = Long.parseLong(strNo);
String strInc = request.getParameter("inc");
int inc = Integer.parseInt(strInc);
System.out.println("1.view.jsp - no : " + no + " ,ing : " + inc);


// 데이터예 따른 DB 가져오기
QnaViewService service = new QnaViewService();
QnaVO vo = service.service(no,inc);

vo.setContent(vo.getContent().replace("/n", "<br>"));
System.out.println("2.view.jsp - vo : " + vo);

// el 객체는 getter를 사용해서 데이터를 꺼낸다.
request.setAttribute("vo", vo);
request.setAttribute("pageObject", pageObject);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 답변 보기</title>
<style type="text/css">
h2 { /* 문의답변보기 : 공간 */
	margin-bottom: 50px;
}
body table {
 font-family: "Trebuchet MS", Tahoma, sans-serif;
 font-size: large;
}
.fontWeight{
	font-weight: bold;
}
</style>
<script type="text/javascript">
$(function(){
	// 삭제 버튼의 이벤트 처리
	$("#deleteBtn").click(function(){
		// JS에서 true(확인) false(취소)를 선택하게 하는 confirm()
		if(!confirm("정말 삭제 하시 겠습니까?")) return false;
	});
});
</script>
</head>
<body>
<div class="container">
<h2 style="text-align:center;"><b>문의 답변 보기</b></h2>
<table class="table">
	<tr>
		<td class="fontWeight">번호</td>
		<td>${vo.no }</td>
	</tr>
	<tr>
		<td class="fontWeight">제목</td>
		<td>${vo.title }</td>
	</tr>
	<tr>
		<td class="fontWeight">내용</td>
		<td>${vo.content }</td>
	</tr>
	<tr>
		<td class="fontWeight">작성자</td>
		<td>${vo.name }</td>
	</tr>
	<tr>
		<td class="fontWeight">작성일</td>
		<td>${vo.writeDate }</td>
	</tr>
	<tr>
		<td class="fontWeight">조회수</td>
		<td>${vo.hit }</td>
	</tr>
	<tr>
		<td colspan="2">
			<!-- 로그인 처리가 되어 있으면 나타나는 버튼 -->
			<c:if test="${!empty login }"> 
			<!-- 내 질문에 대한 답변할 수 없다. -->
 			<c:if test="${login.id != vo.id }"> 
				<a href="answerForm.jsp?no=${vo.no }&key=${pageObject.key }&word=${pageObject.word }" class="btn btn-default">
					답변하기
				</a>
			</c:if>
			<!-- 내가 작성한 글이거나 관리자인 경우에 보여준다. -->
			<!-- 로그인 아이디와 vo 아이디 정보가 일치하거나 관리자일 경우만 수정 삭제가 가능하게끔 한다. -->	
				<c:if test="${login.id == vo.id || login.gradeNo == 9 }"> 
					<a href="updateForm.jsp?no=${vo.no }&page=${param.page }&perPageNum=${parma.perPageNum }&key=${pageObject.key }&word=${pageObject.word }" 
						class="btn btn-default">수정</a>
					<a href="delete.jsp?no=${vo.no }" class="btn btn-default" id="deleteBtn">삭제</a>
 				</c:if>					
			</c:if>
			<a href="list.jsp?page=${param.page }&perPageNum=${parma.perPageNum }&key=${pageObject.key }&word=${pageObject.word }" 
				class="btn btn-default">리스트</a>
	</tr>
</table> 
</div>
</body>
</html>
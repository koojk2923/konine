<%@page import="org.eclipse.jdt.internal.compiler.ast.PrefixExpression"%>
<%@page import="com.webjjang.util.PageObject"%>
<%@page import="com.BookIng.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="com.BookIng.member.service.MemberListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%//여기가 자바의 처리 부분이다.
//DB 클래스 확인
Class.forName("com.BookIng.member.util.db.DB");

//페이지 정보 받기
PageObject pageObject = PageObject.getInstance(request);
//데이터 가져오기 - 생성하고 호출 한다.
//[Controller (JSP)] - Service - DAO
MemberListService service = new MemberListService();
List<MemberVO> list = service.service(pageObject);
System.out.println(list);

//el 객체나 JSTL을 사용하기 위해서 request 객체에 담기 
request.setAttribute("list", list);
request.setAttribute("pageObject", pageObject);

//th, td {
//	border: 1px solid #888;
//	padding: 5px;
//}	
%>    
    
<!DOCTYPE html>
<html>
<!-- 페이지 정보 control + shift + c 눌러서 주석 달기 -->
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>

<style type="text/css">
	
.dataRow:hover { /* class 선택 "."이 붙인다. */ 
	cursor: pointer;
	background: #eee;}
h2.h2 {
  text-align: center;
  background: #ffcccc;
  color: #0000ff;
/* 	width: 18%;   */

}


</style>
<!--
<script type="text/javascript">
//alert("자바 스크립트 경고창")
//</script>-->
</head>
<!-- 데이터 표시 부분 -->
<body>
<div class="container"> 
	<h2 class="h2">회원 리스트</h2>
	<table class="table">
		<tr>
			<th>아이디</th>
			<th>사진</th>
			<th>이름</th>
			<th>생년월일</th>
			<th>연락처</th>
			<th>등급번호</th>	
			<th>등급명</th>	
			<th>최근접속날짜</th>	
		</tr>
		<c:forEach items="${list }" var="vo">
		<!--데이터 있는 만큼 반복 처리해서 줄을 만들어 낸다. -->
		<!-- no: 보여줄 글번호, inc - 조회수 증가 여부. 1: 증가, 0:미증가 -->
			<tr onclick="document.location='view.jsp?id=${vo.id}&page=${pageObject.page }&perPageNum=${pageObject.perPageNum }'" class="dataRow">
				<td>${vo.id }</td>
				<!-- 자바 ->  <a.href;"view.jsp?id=test">id</a> -->
				<td><img src= "${vo.photo }" style ="width: 40px; height: 50px;" class="img-rounded"></td>
				<td> ${vo.name }</td>
				<td> ${vo.birth } </td>
				<td> ${vo.tel } </td>
				<td> ${vo.gradeNo } </td>
				<td>${vo.gradeName}&nbsp;&nbsp;<a href="/member/gradeForm.jsp?id=${vo.id }" class="btn btn-default">등급수정</a></td>
				<td> ${vo.conDate } </td>
			</tr>
		</c:forEach>			
	
		<tr>
			<td colspan="8" > 
				<pageNav:pageNav listURI="list.jsp" pageObject="${pageObject }" />
			</td>		
	 	
	 	</tr>
		<tr>
			<td colspan="8" style="text-align: center;"> 
				<a href= "writeForm.jsp" class= "btn btn-default">회원등록</a>
	 		</td>
	 	</tr>
	</table>
</div>
</body>
</html>
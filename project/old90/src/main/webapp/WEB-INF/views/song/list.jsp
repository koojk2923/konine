<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 22.03.28 : JSTL 추가 --> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 22.03.28 : fmt(날짜 정보를 담고 있는 객체를 포맷팅하여 출력할때 사용) -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 22.03.28 : PageNav 사용 -->
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인기차트</title>
</head>
<body>
<div class="container">
<h2 class="text-center">인기 차트</h2>
<table class="table">
	<tr>
		<td>순위</td>
		<td>앨범이미지</td>
		<td>앨범</td>
		<td>음원명</td>
		<td>조회수</td>
	</tr> 
	<c:forEach items="${songList }" var="vo">
		<tr onclick="document.location='/record/view.do?no=${vo.no}&inc=0'" class="dataRow">
			<td >${vo.rnum }</td>
			<td><img src="${vo.albumImage }" alt="Lights" style="width:60px; height:60px;" ></td>
			<td>${vo.albumName }</td>
			<td>${vo.songTitle }</td>
			<td>${vo.hit }</td>
		</tr>
	</c:forEach>	
	<tr>
		<td colspan="5" class="text-center">
			<pageObject:pageNav listURI="list.do" pageObject="${pageObject }"/>
		</td>
	</tr>
</table>
</div>
</body>
</html>
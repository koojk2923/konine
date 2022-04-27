<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 22.03.22 : 날짜 정보를 담고 있는 객체를 포맷팅하여 출력할 때 사용하는 태그 -->    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!-- 22.03.22 : JSTL 추가 -->    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!-- 22.03.22 : pageObject 추가-->    
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원리스트</title>
<style type="text/css">
	.dataRow:hover {
		background: #eee;
		cursor: pointer;
	}
</style>
<script type="text/javascript">
$(function(){
	$(".dataRow").click(function(){
		var id = $(this).find(".id").text();
		location = "view.do?id=" + id + "&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}"
	});
});
</script>
</head>
<body>
<div class="container">
<h2>회원 리스트</h2>
	<table class="table">
		<tr>
			<td>닉네임</td>
			<td>아이디</td>
			<td>이름</td>
			<td>생년월일</td>
			<td>성별</td>
			<td>연락처</td>
			<td>이메일</td>
			<td>상태</td>
			<td>등급번호</td>
			<td>등급명</td>
		</tr>
		<c:forEach items="${list }" var="vo">
			<tr class="dataRow">
				<td>${vo.nickName }</td>
				<td class="id">${vo.id }</td>
				<td>${vo.name }</td>
				<td><fmt:formatDate value="${vo.birth }" pattern="yyyy-MM-dd"/> </td>
				<td>${vo.gender }</td>
				<td>${vo.tel }</td>
				<td>${vo.email }</td>
				<td>${vo.status }</td>
				<td>${vo.gradeNo }</td>
				<td>${vo.gradeName }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="9" class="text-center">
				<pageObject:pageNav listURI="list.do" pageObject="${pageObject }"/>
			</td>
		</tr>
	</table>
</div>
</body>
</html>
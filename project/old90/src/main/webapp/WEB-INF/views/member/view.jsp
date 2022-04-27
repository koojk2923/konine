<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 22.03.24 : JSTL 추가 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 22.03.23 : fmt(날짜 정보를 담고 있는 객체를 포맷팅하여 출력할때 사용) -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title }</title>
</head>
<body>
<div class="container">
	<h2>${title }</h2>
<table class="table">
		<tr>
		<td>닉네임</td>
		<td>${vo.nickName }</td>
	</tr>
	<tr>
		<td>아이디</td>
		<td>${vo.id }</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${vo.name }</td>
	</tr>
	<tr>
		<td>성별</td>
		<td>${vo.gender }</td>
	</tr>
	<tr>
		<td>생년월일</td>
		<td><fmt:formatDate value="${vo.birth }" pattern="yyyy-MM-dd"/></td>
	</tr>	
	<tr>
		<th>연락처</th>
		<td>${vo.tel }</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td>${vo.email }</td>
	</tr>
	<tr>
		<th>가입일</th>
		<td><fmt:formatDate value="${vo.regDate }" pattern="yyyy-MM-dd"/></td>
	</tr>	
	<tr>
		<td colspan="2">
		<!-- 설명-1 : 로그인을 하고 회원관리누르면 리스트에서 뷰로 넘어올때 id를 가지고온다. 
		그러면 id를 가지고 오기때 정보수정 을 보여주지 않는다.-->
		<!-- 설명-2 : 메인에서 닉네임(등급명)을 클릭해야 바로 뷰페이지로 이동하고 
				아이디를 못가져오기 때문에 정보수정을 보여준다. (default_decorator 확인필수!) 
				넘어오는 값음 param으로 받는다. -->
		<c:if test="${empty param.id }">  
			<a href="update.do?id=${vo.id }&page=${pageObject.page }&perPageNum=${pageObject.perPageNum}" class="btn btn-default">정보수정</a>
		</c:if>
		<c:if test="${!empty param.id && login.gradeNo == 9 }">
			<!-- 관리자 메뉴 : 관리자 & 넘어오는 아이디가 있다. & 넘어오는 아이디가 관리자가 아니다. -->
			<form action="changeStatus.do" method="post">
			<input type="hidden" name="id" value="${vo.id }">
			<input type="hidden" name="page" value="${pageObject.page }">
			<input type="hidden" name="perPageNum" value="${pageObject.perPageNum }">
				<div class="input-group">
					<select name="status" class="form-control">
						<option ${(vo.status == "정상")?"selected": ""}>정상</option>
						<option ${(vo.status == "탈퇴")?"selected": ""}>탈퇴</option>
						<option ${(vo.status == "강퇴")?"selected": ""}>강퇴</option>
						<option ${(vo.status == "휴면")?"selected": ""}>휴면</option>
					</select>
					<div class="input-group-btn">
						<button class="btn btn-default">변경</button>
					</div>
				</div>
			</form>
			<!-- 등급변경 -->
			<!-- 관리자 메뉴 : 관리자 & 넘어오는 아이디가 있다. & 넘어오는 아이디가 관리자가 아니다. -->
			<form action="changeGradeNo.do" method="post">
			<input type="hidden" name="id" value="${vo.id }">
			<input type="hidden" name="page" value="${pageObject.page }">
			<input type="hidden" name="perPageNum" value="${pageObject.perPageNum }">
				<div class="input-group">
					<select name="gradeNo" class="form-control">
						<option ${(vo.gradeNo == 1)?"selected": ""} value="1">일반</option>
						<option ${(vo.gradeNo == 9)?"selected": ""} value="9">관리자</option>
					</select>
					<div class="input-group-btn">
						<button class="btn btn-default">변경</button>
					</div>
				</div>
			</form>							
		</c:if>
		</td>
	</tr>
</table>
	<div class="row">
		<div class="col-md-12">
		</div>
	</div>
</div>
</body>
</html>
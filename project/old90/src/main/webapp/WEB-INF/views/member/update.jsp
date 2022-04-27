<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!-- 22.03.24 : JSTL 추가 -->   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!-- 22.03.20 : fmt(날짜 정보를 담고 있는 객체를 포맷팅하여 출력할때 사용) -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 22.03.23 : PageNav 사용 -->
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보수정</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 
</head>
<body>
<div class="container" style="width: 30%; float:none; margin:0 auto">
<h2 class="text-center">정보 수정</h2>
<div class="well">
	<form action="update.do" method="post" class="form-horizontal">
			<!-- 숨겨서 보내기 --> 
		<input type="hidden" name="page" value="${param.page }">
		<input type="hidden" name="perPageNum" value="${param.perPageNum }">
		<div class="form-group">
			<label for="id" class="control-label col-sm-4">아이디</label> 
			<div class="col-sm-5">
				<input type="text" id="id" name="id" class="form-control" required="required" readonly="readonly"
			 	pattern="[A-Za-z0-9]{4,20}" placeholder="아이디 입력" autocomplete="off" value="${vo.id }">
			</div>	
		</div>
		
		<div class="form-group">
			<label for="nickName" class="control-label col-sm-4">닉네임</label> 
			<div class="col-sm-5">
				<input type="text" id="nickName" name="nickName" class="form-control" required="required" 
				 pattern="[A-Za-z0-9]{4,20}" placeholder="닉네임 입력" autocomplete="off" value="${vo.nickName }">
			 </div>
		</div>
		
		<div class="form-group">
			<label for="pw" class="control-label col-sm-4">새 비밀번호</label> 
			<div class="col-sm-5">
				<input type="password" id="pw" name="pw" class="form-control" required="required" 
				 pattern=".{4,20}" placeholder="새 비밀번호 입력" autocomplete="off">
			 </div>
		</div>
		
		<div class="form-group">
			<label for="pw2" class="control-label col-sm-4">새 비밀번호 확인</label> 
			<div class="col-sm-5">
				<input type="password" id="pw2" name="pw2" class="form-control" required="required" 
				 pattern=".{4,20}" placeholder="새 비밀번호 확인 입력" autocomplete="off">
			 </div>
		</div>
		
		<div class="form-group">
			<label for="name" class="control-label col-sm-4">이름</label> 
			<div class="col-sm-5">
				<input type="text" id="name" name="name" class="form-control" required="required" 
				 pattern="[가-힣]{2,10}" placeholder="이름 입력" autocomplete="off" value="${vo.name }">
			 </div>
		</div>
		
		<div class="form-group">
			<label for="gender" class="control-label col-sm-4">성별</label> 
			<div class="col-sm-5">
				<label class="radio-inline">
					<input type="radio" id="gender" name="gender" value="남자" ${(vo.gender =="남자")?"checked":"" }>
					남자</label>
				<label class="radio-inline">
					<input type="radio" name="gender" value="여자" ${(vo.gender =="여자")?"checked":"" }>여자
				</label>
			 </div>
		</div>
		
		<div class="form-group">
			<label for="birth" class="control-label col-sm-4">생년월일</label> 
			<div class="col-sm-5">
				<input id="birth" name="birth" class="form-control datepicker" required="required" 
				placeholder="yyyy-mm-dd" autocomplete="off" 
				value="<fmt:formatDate value="${vo.birth }" pattern="yyyy-MM-dd"/>">
			 </div>
		</div>
		
		<div class="form-group">
			<label for="tel" class="control-label col-sm-4">연락처</label> 
			<div class="col-sm-5">
				<input id="tel" name="tel" class="form-control" required="required" 
				placeholder="연락처 입력" autocomplete="off" value="${vo.tel }">
			 </div>
		</div>
		
		<div class="form-group">
			<label for="email" class="control-label col-sm-4">이메일</label> 
			<div class="col-sm-5">
				<input id="email" name="email" class="form-control" required="required" 
				placeholder="이메일 입력" autocomplete="off" value="${vo.email }">
			 </div>
		</div>
		<div class="col-md-offest-3 text-center">
			<button type="submit" class="btn btn-default">수정</button>
			<button type="reset" class="btn btn-default">새로입력</button>
			<a href="delete.do?id=${vo.id }&perPageNum=${param.perPageNum}" class="btn btn-default" id="deleteBtn">회원탈퇴</a>
		</div>	
	</form>
</div>
</div>
</body>
</html>
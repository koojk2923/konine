 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 폼</title>
</head>
<body>
<div class="container">
<div class="well" style="width: 50%; float:none; margin:0 auto">
<h2 class="text-center">로그인 폼</h2>
	<form action="login.do" method="post" class="form-horizontal">
		<div class="from-group row" style="float: none; margin:100 auto;">
			<div class="col-xs-6" style="float: none; margin:0 auto;">
				<input type="text" name="id" placeholder="아이디" 
				class="form-control input-lg">
			</div>
		</div>
		<div class="from-group row" style="float: none; margin:100 auto;">
			<div class="col-xs-6" style="float: none; margin:0 auto;" >
				<input type="password" name="pw" placeholder="비밀번호" 
				class="form-control input-lg">
			</div>
		</div>
		<div class="from-group row text-center"  style="float: none; margin:100 auto; margin-top: 10px">
			<button class="btn btn-default col-xs-6" style="float: none; margin:0 auto;">로그인</button>
		</div>
	</form>
</div>
</div>
</body>
</html>

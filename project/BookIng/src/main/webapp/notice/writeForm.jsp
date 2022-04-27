<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 쓰기</title>
<script type="text/javascript" src="/js/formUtil.js"></script>
<script type="text/javascript">
$(function(){
	$("#writeForm").submit(function(){
		// alert("데이터 검사");
		
		// 필수 입력 데이터 검사
		if(emptyCheck("#title","제목")) return false;
		if(emptyCheck("#content","내용")) return false;
		
	});
});

</script>
</head>
<body>
<div class="container">
	<h2>공지 등록</h2>
	<form action="write.jsp" method="post" id= "writeForm" class="form-horizontal">
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">제목</label>
			<div class="col-sm-10">
				<input type="text" name="title" maxlength="100" placeholder="제목 입력. 4자이상 100자이내" id="title"  class="form-control" />
		</div>
		</div>
		<div class="form-group">
			<label for="content" class="control-label col-sm-2">내용</label>
			<div class="col-sm-10">
			<!-- placeholder="내용 입력. 2000자이내" -->
			<textarea rows="7" cols="80" name="content" placeholder="내용 입력. 2000자이내"  id="content" class="form-control" ></textarea>
		</div>
		</div>
		<div class="form-group">
			<label for="startDate" class="control-label col-sm-2">시작일</label>
			<div class="col-sm-10">
				<!-- 정규표현식 - \d : 숫자.[0-9]-->
				<!-- pattern="\d{4}-\d{2}-\d{2}"  -->
				<input name="startDate" maxlength="10"
				placeholder="yyyy-mm-dd" pattern="\d{4}-\d{2}-\d{2}" title="날짜형식 : yyyy-mm-dd" id="startDate" class="form-control" />
		</div>
		</div>
		<div class="form-group">
			<label for="endDate" class="control-label col-sm-2">종료일</label>
			<div class="col-sm-10">
				<input name="endDate" maxlength="10"
				placeholder="yyyy-mm-dd" pattern="\d{4}-\d{2}-\d{2}"  title="날짜형식 : yyyy-mm-dd" id="endDate" class="form-control" />
		</div>
		</div>
		<div class="text-center">
				<button type="submit" class="btn btn-default">등록</button>
				<button type="reset"  class="btn btn-default">다시입력</button>
				<button type="button" onclick="history.back()" class="btn btn-default">취소</button>
		</div>

</form>
</div>
</body>
</html>
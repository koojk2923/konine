<%@page import="com.BookIng.bookBoard.service.BookBoardViewService"%>
<%@page import="com.BookIng.bookBoard.service.CoverChangeService"%>
<%@page import="com.BookIng.bookBoard.vo.BookBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 등록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- /js/formUtil.js의 경로를 지정해준다. -->
<script type="text/javascript" src="/js/formUtil.js?v=1"></script>
<script type="text/javascript">
$(function() {
	$("#writeForm").submit(function(){
		// 필수입력 항목 검사
		// 데이터 넘어갈때 유효성 검사
		// 제목 -> 제목이 비어있으면 경고->포커스->이동무시 
		if(emptyCheck("#title", "제목")) return false;
		if(emptyCheck("#writer", "작가")) return false;
		if(emptyCheck("#genre", "장르")) return false;
		if(emptyCheck("#publisher", "출판사")) return false;
		if(emptyCheck("#pubDate", "출간일")) return false;
		if(!regTest(reg_date, "#pubDate", "yyyy-MM-dd 형식의 날짜 데이터")) return false;
		if(emptyCheck("#price", "가격")) return false;
		if(!regTest(reg_num, "#price", "숫자")) return false;
		if(emptyCheck("#summary", "줄거리")) return false;
	});
});
</script>
<style type="text/css"> 
/* 이미지 스타일과 사이즈를 설정한다 */
#viewImg {
	width: 400px;
	 box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
</style>
</head>
<body>
<!-- bootstrap을 이용해 보기좋게 만들어준다. -->
<div class="container">
<h2>도서 등록 폼</h2>
<!-- 책 표지의 입력을 위해 type="file" 을 설정한다 -->
<form action="write.jsp" method="post" enctype="multipart/form-data" class="form-horizontal" id="writeForm">
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">책표지</label>
			<div class="col-sm-4">
			<input name="cover" type="file" class="form-control" />
			</div>
		</div>
 		<!--  write에 입력할 데이터를 input으로 받아온다. -->
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">제목</label>
			<div class="col-sm-10">
			<!-- input 데이터 입력, type : 입력형태, name : 전달이름, maxlength : 최대입력  -->
				<input type="text" name="title" maxlength="100" id="title" class="form-control" />
			</div>
		</div>
		<!--  write에 입력할 데이터를 input으로 받아온다. -->
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">작가</label>
			<div class="col-sm-10">
			<!-- input 데이터 입력, type : 입력형태, name : 전달이름, maxlength : 최대입력  -->
				<input type="text" name="writer" maxlength="100" id="writer" class="form-control" />
			</div>
		</div>
		<!--  write에 입력할 데이터를 input으로 받아온다. -->
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">장르</label>
			<div class="col-sm-10">
			<!-- input 데이터 입력, type : 입력형태, name : 전달이름, maxlength : 최대입력  -->
				<input type="text" name="genre" maxlength="100" id="genre" class="form-control" />
			</div>
		</div>
		<!--  write에 입력할 데이터를 input으로 받아온다. -->
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">출판사</label>
			<div class="col-sm-10">
			<!-- input 데이터 입력, type : 입력형태, name : 전달이름, maxlength : 최대입력  -->
				<input type="text" name="publisher" maxlength="100" id="publisher" class="form-control" />
			</div>
		</div>
		<!--  write에 입력할 데이터를 input으로 받아온다. -->
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">출간일</label>
			<div class="col-sm-10">
			<!-- input 데이터 입력, type : 입력형태, name : 전달이름, maxlength : 최대입력  -->
 			<!-- 날짜 형식의 데이터만 입력 받게끔 placeholder로 표시해줬지만 유효성 검사로 한번 더 확인하는 처리를 했다 -->
				<input type="text" name="pubDate" maxlength="10" placeholder="ex) 1900-01-01" id="pubDate" class="form-control"/>
			</div>
		</div>
			<!--  write에 입력할 데이터를 input으로 받아온다. -->
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">가격</label>
			<div class="col-sm-10">
			<!-- input 데이터 입력, type : 입력형태, name : 전달이름, maxlength : 최대입력  -->
				<input type="text" name="price" maxlength="1000" id="price" class="form-control" />
			</div>
		</div>
			<!--  write에 입력할 데이터를 input으로 받아온다. -->
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">줄거리</label>
			<div class="col-sm-10">
			<!-- textarea 데이터 입력, type : 입력형태, name : 전달이름, maxlength : 최대입력  -->
				<textarea rows="7" cols="80" name="summary" id="summary" class="form-control" /></textarea>
			</div>
		</div>
	<div class="text-right">
				<!-- button이 form tag안에 있으면 데이터를 전달하는 동작을 하게됨 -->
				<button type="submit" class="btn btn-default">등록</button>
				<button type="reset" class="btn btn-default">다시 입력</button>
				<button type="button" onclick="history.back()" class="btn btn-default">취소</button>
			</div>
</form>
</div>
</body>
</html>
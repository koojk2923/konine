<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음반 등록</title>
</head>
<body>
<div class="container">
<h2 class="text-center">음반 등록</h2>
<div class="well">
	<form class="form-horizontal col-md-offset-3" action="write.do" method="post" enctype="multipart/form-data">
		<!-- 숨겨서 보내기 -->
		<input type="hidden" name="perPageNum" value="${param.perPageNum }">
		<div class="form-group">
			<label class="control-label col-sm-2" for="albumName">앨범명</label> 
			<div class="col-sm-5">
				<input type="text" class="form-control" id="albumName" name="albumName">
			</div>		
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="artistName">아티스트명</label> 
			<div class="col-sm-5">
				<input type="text" class="form-control" id="artistName" name="artistName">
			</div>		
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="songTitle">음원명</label> 
			<div class="col-sm-5">
				<input type="text" class="form-control" id="songTitle" name="songTitle">
			</div>		
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="genres">장르</label> 
			<div class="col-sm-5">
				<input type="text" class="form-control" id="genres" name="genres">
			</div>		
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="introduce">소개글</label> 
			<div class="col-sm-5">
				<textarea  class="form-control" rows="5" cols="5" id="introduce" name="introduce"></textarea>
			</div>		
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="relDate">발매일</label> 
			<div class="col-sm-5">
				<input type="text" class="form-control" id="relDate" name="relDate" placeholder="yyyy-MM-dd">
			</div>		
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="albumImageFile">앨범 이미지 파일</label> 
			<div class="col-sm-5">
				<input type="file" class="form-control" id="albumImageFile" name="albumImageFile">
			</div>		
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="songFile">음원 파일</label> 
			<div class="col-sm-5">
				<input type="file" class="form-control" id="songFile" name="songFile">
			</div>		
		</div>
		<div class="col-md-offset-3">
			<button class="btn btn-default">등록</button>
			<button type="reset" class="btn btn-default">다시입력</button>
			<button type="button" onclick="history.back()" class="btn btn-default">취소</button>
		</div>
	</form>
</div>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음반 정보 수정</title>
<style type="text/css">
	.img-thumnail{
		width: 500px;
		margin-top: 10px;
		margin-bottom: 15px;
	}
	.audio-thumnail {
		margin-top: 15px;
		margin-bottom: 15px;
	}
	

</style>
</head>
<body>
<div class="container">
<h2 class="text-center">음반 정보 수정</h2>
<div class="well row">
<div class="col-md-6">
<form class="form-horizontal" action="update.do" method="post">
		<!-- 숨겨서 보내기 -->
		<input type="hidden" name="page" value="${pageObject.page }">
		<input type="hidden" name="perPageNum" value="${pageObject.perPageNum }">
		<!-- end -->
		<div class="form-group">
			<label class="control-label col-sm-2" for="no">번호</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="no" name="no" value="${vo.no }" 
				readonly="readonly" onclick="alert('번호는 수정할 수 없습니다.')">
			</div>		
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="albumName">앨범명</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="albumName" name="albumName" value="${vo.albumName }">
			</div>		
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="artistName">아티스트명</label> 
			<div class="col-sm-8">
				<input type="text" class="form-control" id="artistName" name="artistName" value="${vo.artistName }">
			</div>		
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="songTitle">음원명</label> 
			<div class="col-sm-8">
				<input type="text" class="form-control" id="songTitle" name="songTitle" value="${vo.songTitle }">
			</div>		
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="genres">장르</label> 
			<div class="col-sm-8">
				<input type="text" class="form-control" id="genres" name="genres" value="${vo.genres }">
			</div>		
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="introduce">소개글</label> 
			<div class="col-sm-8">
				<textarea  class="form-control" rows="5" cols="5" id="introduce" name="introduce">${vo.introduce }</textarea>
			</div>		
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="relDate">발매일</label> 
			<div class="col-sm-8">
				<input type="text" class="form-control" id="relDate" name="relDate" 
				value="<fmt:formatDate value="${vo.relDate }" pattern="yyyy-MM-dd"/>">
			</div>		
		</div>	
		<div class="col-md-12 text-center">
			<button type="submit" class="btn btn-default">등록</button>
			<button type="reset" class="btn btn-default">다시입력</button>
			<button type="button" onclick="history.back()" class="btn btn-default">취소</button>
		</div>	
	</form>	
</div><!-- end of span6 -->

	
	<div class="row">
	<div class="col-md-6">
		<div class="form-group">
			<div class="col-sm-7">
			<strong>앨범 이미지 파일</strong>
			<img src="${vo.albumImage }" class="img-thumnail" style="width:300px; height:200px;">	
			<a href="${vo.albumImage}" class="btn btn-danger" download>다운로드</a>
			<!-- 이미지 바꾸기 : form 태그가 있는 모달창을 사용해서 post방식으로 넘겨 보자. a 태그는 get 방식 전달은 하므로 이미지 전달이 안된다. -->
			<!-- Trigger the modal with a button -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">이미지 바꾸기</button>
			</div>
		</div>
	</div>	
	
	
	<div class="row">
	<div class="col-md-6">
		<div class="form-group">
			<div class="col-sm-7">
			<audio src="${vo.song }" controls="controls" class="audio-thumnail"></audio>
			<!-- 이미지 바꾸기 : form 태그가 있는 모달창을 사용해서 post방식으로 넘겨 보자. a 태그는 get 방식 전달은 하므로 이미지 전달이 안된다. -->
			<!-- Trigger the modal with a button -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal2">음원 바꾸기</button>
			</div>
		</div>
	</div>
	
</div>	
</div>	

<!-- Modal : 음반 이미지 부분 -->
<div id="myModal" class="modal fade" role="dialog">
	 <div class="modal-dialog">
	  <form action="changeImage.do" method="post" enctype="multipart/form-data">
	  	<!-- 숨겨서 넘겨 줘야할 데이터 - 페이지, 한페이지당 갯수, 삭제해야할 원래 파일 정보 -->
		<input type="hidden" name="page" value="${pageObject.page }">
		<input type="hidden" name="perPageNum" value="${pageObject.perPageNum }">
		<input type="hidden" name="deleteImageFileName" value="${vo.albumImage }">
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">바꾸기</h4>
	      </div>
	      <div class="modal-body">
	      <p>
	        <div class="form-group">
				<label for="no">번호</label> 
				<input type="text" class="form-control" onclick="alert('번호는 변경할 수 없습니다.');"
				id="no" name="no" readonly="readonly" value="${vo.no }">
			</div>
	        <div class="form-group">
				<label for="albumImageFile">이미지 파일</label> 
				<input type="file" class="form-control" id="albumImageFile" name="albumImageFile" required="required">
			</div>
	      </div>
	      <div class="modal-footer">
	        <!-- form 태그 안에 button은 기본타입이므로 type="button" 이라고 사용하지 않는다. -->
	        <!-- 주의사항 만약 type="button" 지정해서 사용하면 화면이 넘어가지 않는다.   -->
	        <button class="btn btn-default">바꾸기</button>
	        <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
	      </div>
	    </div>
	  </form> 
</div>
</div>
	
<!-- Modal : 음반 이미지 부분 -->
<div id="myModal2" class="modal fade" role="dialog">
	<div class="modal-dialog">
	  <form action="changeSong.do" method="post" enctype="multipart/form-data">
	  	<!-- 숨겨서 넘겨 줘야할 데이터 - 페이지, 한페이지당 갯수, 삭제해야할 원래 파일 정보 -->
		<input type="hidden" name="page" value="${pageObject.page }">
		<input type="hidden" name="perPageNum" value="${pageObject.perPageNum }">
		<input type="hidden" name="deleteSongFileName" value="${vo.song }">
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">사진바꾸기</h4>
	      </div>
	      <div class="modal-body">
	      <p>
	        <div class="form-group">
				<label for="no">번호</label> 
				<input type="text" class="form-control" onclick="alert('번호는 변경할 수 없습니다.');"
				id="no" name="no" readonly="readonly" value="${vo.no }">
			</div>
	        <div class="form-group">
				<label for="songFile">이미지 파일</label> 
				<input type="file" class="form-control" id="songFile" name="songFile" required="required">
			</div>
	      </div>
	      <div class="modal-footer">
	        <!-- form 태그 안에 button은 기본타입이므로 type="button" 이라고 사용하지 않는다. -->
	        <!-- 주의사항 만약 type="button" 지정해서 사용하면 화면이 넘어가지 않는다.   -->
	        <button class="btn btn-default">바꾸기</button>
	        <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
	      </div>
	    </div>
	  </form> 
	</div>		
</div>



</div>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 22.03.21 : JSTL 추가 -->   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- 22.03.21 : fmt(날짜 정보를 담고 있는 객체를 포맷팅하여 출력할때 사용) -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음반 보기</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
  
<style type="text/css">
 #viewImage { /* 이미지 크기 조정 */
/* 	width: 500px; */
/* 	margin-right:  50px; */
	width: 350px;
	height: 2;
	content: 300px;
	
}

.table, table2{ /* 테이블 전체 가운데 정렬 */
	text-align: center;
}
.textLeft{
	text-align: left;
}
</style>

</head>
<body>
<div class="container">
<h2>음반보기</h2>
<table class="table">
	<tr>
		<!-- alt : 이미지가 안보이실 문구내용 작성 --> 
		<!-- image : 앨범이미지 -->
		<td rowspan="7" class="text-left"><img alt="${vo.artistName }" src="${vo.albumImage }" id="viewImage"/></td>	
	</tr>
	<tr>
		<th>조회수</th>
		<td>${vo.hit }</td>
	</tr>
	<tr>
		<th>앨범명</th>
		<td>${vo.albumName }</td>
	</tr>
	<tr>
		<th>아티스트</th>
		<td>${vo.artistName }</td>
	</tr>
	<tr>
		<th>발매일</th>
		<td><fmt:formatDate value="${vo.relDate }" pattern="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<th>장르</th>
		<td>${vo.genres }</td>
	</tr>
	<tr>
		<th>소개글</th>
		<td style="word-wrap: break-word;">${vo.introduce }</td>
	</tr>
</table>

	<h3>TARACK</h3>
	<table class="table">
	<tr>
		<th class="col-md-2">번호</th>
		<th class="col-md-6">곡명</th>
		<th class="col-md-4">Play</th>
	</tr>
	<tr class="textLeft">
		<td>1</td>
		<td>${vo.songTitle }</td>
		<td>
		
		<audio controls="controls" > <!-- 오디오 태그 -->
			<source src="${vo.song }" type="audio/mp3"> <!-- src : vo에 있는 오디오파일을 가져온다. -->
		</audio>
		</td>
	</table>
		<div class="row">
			<div class="col-md-12">
	<c:if test="${!empty login.id && login.gradeNo == 9 }">
				<a href="update.do?no=${vo.no }&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}" class="btn btn-default">음원수정</a>
				<a href="delete.do?no=${vo.no }&perPageNum=${pageObject.perPageNum }&deleteImageFileName=${vo.albumImage}&deleteSongFileName=${vo.song}" 
				class="btn btn-default" id="deleteBtn">삭제</a>
		</c:if>			
				<a href="list.do?page=${pageObject.page}&perPageNum=${pageObject.perPageNum}" class="btn btn-default">리스트</a>
			</div>
		</div>
	
</div>
</body>
</html>
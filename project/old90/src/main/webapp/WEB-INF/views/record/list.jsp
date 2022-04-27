<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 22.03.20 : JSTL 추가 --> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 22.03.20 : fmt(날짜 정보를 담고 있는 객체를 포맷팅하여 출력할때 사용) -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 22.03.20 : PageNav 사용 -->
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음반 차트</title>

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 
<style type="text/css">
.dataRow:hover{
/* 	background: #eee; */
	cursor: pointer;
}

</style>

<script type="text/javascript">
$(function(){
	$(".dataRow").click(function(){
		var no = $(this).find(".no").text();
		location = "view.do?no=" + no + "&inc=1&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}";		
	});
});
</script>

<script type="text/javascript">
$(function(){s
	var isMsg = true;

	<c:if test="${!empty msg}">
		if(isMsg){
			// 0.5초 기다렸다가 경고창 띄우기 : js setTimeout
			setTimeout(
				// 실행해야 할 함수
				function(){
				alert("${msg}");
			},
			200 // 0.2초 : 1000 -> 1초 : 기다려야 할 시간
			);
			isMsg =  false;
		} 
	</c:if>
});
</script>
</head>
<body>
<div class="container">
<h2 class="text-center">음반 차트</h2>
<!-- 검색기능 -->
<div class="row">
	<div class="col-md-8">
		<form class="form-inline searchDiv" style="padding-bottom: 10px;">
		<input type="hidden" name="perPageNum" value="${pageObject.perPageNum }">
			<div class="input-group">
				<select name="key" class="form-control">
					<option value="a" ${(pageObject.key == "a")? "selected":"" }>앨범명</option>
					<option value="n" ${(pageObject.key == "n")? "selected":"" }>아티스트명</option>
					<option value="s" ${(pageObject.key == "s")? "selected":"" }>노래명</option>
					<option value="ans" ${(pageObject.key == "nas")? "selected":"" }>전체</option>
				</select>
			</div>
		<div class="input-group">
		   <input type="text" class="form-control" placeholder="Search" name="word" value="${pageObject.word }">
		    <div class="input-group-btn">
		      <button class="btn btn-default" type="submit">
		        <i class="glyphicon glyphicon-search"></i>
		      </button>
		    </div>
		</div>
	</form>
</div><!-- 검색기능 끝 -->
</div>
<div class="row"> 
  <c:forEach items="${list }" var="vo" varStatus="vs">
	  <div class="col-md-4 dataRow" >
	    <div class="thumbnail">
	        <img src="${vo.albumImage }" alt="Lights" style="width:100%">
	        <div class="caption">
	          <p><span class="no">${vo.no }</span>.${vo.albumName }</p>
	          <p><span>아티스트</span>:${vo.artistName }</p>
	          <p><span><fmt:formatDate value="${vo.relDate }" pattern="yyyy-MM-dd"/></span></p>
	          <p><span>${vo.genres }</span></p>
	        </div>
	    </div>
	  </div>
	  		<!-- itemms="리스트가 받아올 배열이름" || var="for문 내부에서 사용할 변수" || varStatus="상태용 변수" -->
	  		<!-- 줄바꿈을 하기 위한 코드 : EL 객체의 삼항 연사자 - 조건?True:False -->
	  	  	<c:if test="${vs.count % 3 == 0 && vs.count != list.size()}">
			<!-- 한 줄을 마감하고 새로운 줄을 시작한다. -->
			${"</div>" } 
			${"<div class='row'>"}
		</c:if>
  </c:forEach>
  
</div>
	<c:if test="${!empty login.id && login.gradeNo == 9 }">
		<div class="text-center">
			<a href="write.do?perPageNum=${pageObject.perPageNum }" class="btn btn-default">앨범등록</a>
		</div>
	</c:if>
<div class="text-center">
	<pageObject:pageNav listURI="list.do" pageObject="${pageObject }" 
	query="&key=${pageObject.key }&word=${pageObject.word }"/>
</div>
</div>
</body>
</html>
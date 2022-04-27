<%@page import="com.BookIng.bookBoard.vo.BookBoardVO"%>
<%@page import="com.BookIng.bookBoard.service.BookBoardViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// bookNo 데이터를 받아 스트링 데이터로 선언한다.
String strBookNo = request.getParameter("bookNo");
//받아온 스트링 데이터를 parseLong으로 캐스팅 해서 bookNo에 넣는다
long bookNo = Long.parseLong(strBookNo); 
// DB에서 데이터를 가져온다. 
BookBoardViewService Service = new BookBoardViewService();
BookBoardVO vo = Service.service(bookNo);

System.out.println("updateForm.jsp- vo : " + vo);
//el 객체는 getter를 사용해서 데이터를 꺼낸다.
request.setAttribute("vo", vo);
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 정보 수정</title>
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
	width: 300px;
	 box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
/* 이미지를 바꿀때 숨겨주게끔 처리해주는 선언 */
#changeImageDiv {
	display: none; 
}
</style>
</head>
<body>
<!-- bootstrap을 이용해 보기좋게 만들어준다. -->
<div class="container">
<h2>도서 정보 수정 폼</h2>
<br>
<!--  updateForm에서 이미지를 바꾸기 위한 처리문 -->
	<div class="form-group">
			<label for="title" class="control-label col-sm-2">표지</label>
			<div class="col-sm-10">
<!-- 			이미지 파일의 주소를 불러온다 -->
		<img alt="<%= vo.getTitle() %>" src="<%= vo.getCover() %>" id="viewImg">
		<hr>
<!-- 		바꾸기 버튼은 평소 숨겨두다가 바꾸기를 누르면 나오게 처리한다 -->
		<button onclick="$('#changeImageDiv').show()" class="btn btn-default">바꾸기</button>
		<hr>
		<div id="changeImageDiv">
<!-- 		submit을 하면 changeCover.jsp가 동작하도록 처리한다 -->
		<form action="changeCover.jsp" method="post" enctype="multipart/form-data">
		<!-- type="hidden" : 사용자는 볼수 없고 데이터는 넘어간다. -->
		<input type="hidden" name="bookNo" value="<%= vo.getBookNo() %>">
<!-- 		changeCover.jsp에서 수정 전 파일을 삭제하기 위해 oldCover를 선언해준다  -->
		<input type="hidden" name="oldCover" value="<%= vo.getCover() %>">
		<input type="file" name="cover" required="required" class="btn btn-default">
		<br>
		<button class="btn btn-default">바꾸기</button>
		<button type="button" onclick="$('#changeImageDiv').hide()" class="btn btn-default">취소</button>
		</form>
		<br>
		</div>
		</div>
		</div>
<form action="update.jsp" method="post" class="form-horizontal" id="updateForm">
<!-- 사용자에게 테이터를 입력하도록 한다. : form, input, select, textarea tag  -->
<!-- 	이미 입력된 데이터를 수정하기 위해 원래의 value를 불러오고 그것을 수정할 데이터를 다시 입력받는다. -->
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">등록번호</label>
			<div class="col-sm-10">
			<!-- input 데이터 입력, type : 입력형태, name : 전달이름, maxlength : 최대입력 -->
				<input type="text" name="bookNo" value="${vo.bookNo}" readonly="readonly" 
			style="background: #eee" class="form-control" />
			</div>
			</div>
			<!-- 	이미 입력된 데이터를 수정하기 위해 원래의 value를 불러오고 그것을 수정할 데이터를 다시 입력받는다. -->
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">제목</label>
			<div class="col-sm-10">
			<!-- input 데이터 입력, type : 입력형태, name : 전달이름, maxlength : 최대입력 -->
				<input type="text" name="title" value="${vo.title}" maxlength="100" id="title" class="form-control" />
			</div>
		</div>
		<!-- 	이미 입력된 데이터를 수정하기 위해 원래의 value를 불러오고 그것을 수정할 데이터를 다시 입력받는다. -->
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">작가</label>
			<div class="col-sm-10">
			<!-- input 데이터 입력, type : 입력형태, name : 전달이름, maxlength : 최대입력 -->
				<input type="text" name="writer" value="${vo.writer}" maxlength="100" id="writer" class="form-control" />
			</div>
		</div>
		<!-- 	이미 입력된 데이터를 수정하기 위해 원래의 value를 불러오고 그것을 수정할 데이터를 다시 입력받는다. -->
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">장르</label>
			<div class="col-sm-10">
			<!-- input 데이터 입력, type : 입력형태, name : 전달이름, maxlength : 최대입력 -->
				<input type="text" name="genre" value="${vo.genre}" maxlength="100" id="genre" class="form-control" />
			</div>
		</div>
		<!-- 	이미 입력된 데이터를 수정하기 위해 원래의 value를 불러오고 그것을 수정할 데이터를 다시 입력받는다. -->
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">출판사</label>
			<div class="col-sm-10">
			<!-- input 데이터 입력, type : 입력형태, name : 전달이름, maxlength : 최대입력 -->
				<input type="text" name="publisher" value="${vo.publisher}" maxlength="100" id="publisher" class="form-control" />
			</div>
		</div>
		<!-- 	이미 입력된 데이터를 수정하기 위해 원래의 value를 불러오고 그것을 수정할 데이터를 다시 입력받는다. -->
		<!-- 	날짜 형식의 데이터만 입력 받게끔 유효성 검사로 확인하는 처리를 했다 -->
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">출간일</label>
			<div class="col-sm-10">
			<!-- input 데이터 입력, type : 입력형태, name : 전달이름, maxlength : 최대입력 -->
				<input type="text" name="pubDate" value="${vo.pubDate}" maxlength="10" id="pubDate" class="form-control"/>
			</div>
		</div>
		<!-- 	이미 입력된 데이터를 수정하기 위해 원래의 value를 불러오고 그것을 수정할 데이터를 다시 입력받는다. -->
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">가격</label>
			<div class="col-sm-10">
			<!-- input 데이터 입력, type : 입력형태, name : 전달이름, maxlength : 최대입력 -->
				<input type="text" name="price" value="${vo.price}" maxlength="1000" id="price" class="form-control" />
			</div>
		</div>
		<!-- 	이미 입력된 데이터를 수정하기 위해 원래의 value를 불러오고 그것을 수정할 데이터를 다시 입력받는다. -->
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">줄거리</label>
			<div class="col-sm-10">
			<!-- input 데이터 입력, type : 입력형태, name : 전달이름, maxlength : 최대입력 -->
				<textarea rows="7" cols="80" name="summary" id="summary" class="form-control">${vo.summary}</textarea>
			</div>
		</div>
		<!--  돌아가기, 수정완료와 취소를 위한 버튼을 만든다. -->
	<div class="text-right">
				<!-- button이 form tag안에 있으면 데이터를 전달하는 동작을 하게됨 -->
				<button type="submit" class="btn btn-default">수정</button>
				<button type="reset" class="btn btn-default">다시 입력</button>
				<button type="button" onclick="history.back()" class="btn btn-default">취소</button>
			</div>
</form>
</div>
</body>
</html>
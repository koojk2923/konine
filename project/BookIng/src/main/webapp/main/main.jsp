<%@page import="com.BookIng.qna.vo.QnaVO"%>
<%@page import="com.BookIng.qna.service.QnaListService"%>
<%@page import="com.BookIng.notice.vo.NoticeVO"%>
<%@page import="com.BookIng.notice.service.NoticeListService"%>
<%@page import="com.BookIng.bookBoard.vo.BookBoardVO"%>
<%@page import="java.util.List"%>
<%@page import="com.webjjang.util.PageObject"%>
<%@page import="com.BookIng.bookBoard.service.BookBoardListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// pageObject 생성
PageObject pageObject = new PageObject();
// 데이터 가져오기 - 이미지리스트 (3개), 공지사항 리스트(5개), 게시판 리스트(5개)
// 이미지 데이터 가져오기 
BookBoardListService bookBoardListService = new BookBoardListService();
// 데이터의 갯수를 3개로 정한다.
pageObject.setPerPageNum(6);
List<BookBoardVO> bookBoardList = bookBoardListService.service(pageObject);

// 공지사항 데이터 가져오기
NoticeListService noticeListService = new NoticeListService();
pageObject.setPerPageNum(5);
List<NoticeVO> noticeList = noticeListService.service(pageObject);

// 게시판 데이터 가져오기
QnaListService qnaListService = new QnaListService();
pageObject.setPerPageNum(5);
List<QnaVO> qnaList = qnaListService.service(pageObject);

//EL 객체나 JSTL 객체로 사용하기 위해서 기본 저장 객체(request)에 담는다.
//EL 라이브러리는 톰캣에 포함. JSTL을 별도로 다운 받아서 /WEB_INF/lib 에 넣어 둬야 한다.
request.setAttribute("bookBoardList", bookBoardList); // el 객체는 getter를 사용해서 데이터를 꺼낸다.
request.setAttribute("noticeList", noticeList); 
request.setAttribute("qnaList", qnaList); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<style type="text/css">
th {
	background: black;
	color: white;
}


#shadow2 {
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.05), 0 6px 10px 0 rgba(0, 0, 0, 0.05);
}

.bookBoardDataRow:hover {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.2);
	cursor: pointer;
		
	opacity: 1;
    transition: all 0.2s;
}
.noticeDataRow:hover {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.2);
	cursor: pointer;
		
	opacity: 1;
    transition: all 0.2s;
}
.qnaDataRow:hover {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	cursor: pointer;
		
	opacity: 1;
    transition: all 0.2s;
}
.well {
  background-color: #5BC8DA;
  border: 1px solid #e3e3e3;
  border-radius: 4px;
  -webkit-box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.05), 0 6px 10px 0 rgba(0, 0, 0, 0.05);
          box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.05), 0 6px 10px 0 rgba(0, 0, 0, 0.05);
}
.panel-default > .panel-heading {
  background-color: #FFBF80 !important;
}  
#font2 { 
    font-weight: bold;
	text-shadow: 0px 0 1px #000, 0 3px 1px #000, 1px 0 1px #000, 0 -1px 1px #000;  
 }

</style>
<script type="text/javascript">
$(function() {
	$(".noticeDataRow, .qnaDataRow").click(function() {
		var no = $(this).data("no"); 
		var url = "/";
		if($(this).hasClass("noticeDataRow")) url = url + "notice";
		if($(this).hasClass("qnaDataRow")) url = url + "qna";
		url += "/view.jsp?no=" + no + "&inc=1";
		location = url;
	});
});
</script>
</head>
<body>
	<div class="container" >
		<div class="well" style="color:white; text-align:center;" id="font">
			<h2><b><span style="color:#EEB51C" id="font2">
			Book-Ing</span></b>
			<b><span style="color:white;" id="font2">서점
			</span></b>에 오신걸 환영합니다!</h2>
		</div>
		<div class="row">
			<div class="col-md-12">
				<jsp:include page="/include/bookBoardList.jsp"/>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<jsp:include page="/include/noticeList.jsp"/>
			</div>
			<div class="col-md-6">
				<jsp:include page="/include/qnaList.jsp"/>
			</div>
		</div>
	</div>
</body>
</html>
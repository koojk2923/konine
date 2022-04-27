<%@page import="com.webjjang.util.PageObject"%>
<%@page import="com.BookIng.bookBoard.vo.BookBoardVO"%>
<%@page import="com.BookIng.bookBoard.service.BookBoardListService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%
// 페이지 처리를 위해 request를 받아 PageObject의 getInstance로 저장한다.
PageObject pageObject = PageObject.getInstance(request);

pageObject.setPerPageNum(12);

// list에 데이터 수집은 없다.
// DB에서 데이터를 가져온다. 
BookBoardListService Service = new BookBoardListService();
List <BookBoardVO> list = Service.service(pageObject);
System.out.println("/bookBoard/list : " + list);

//el 객체는 getter를 사용해서 데이터를 꺼낸다.
request.setAttribute("list", list); 
request.setAttribute("pageObject", pageObject); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 목록</title>
<style type="text/css">
/* 리스트 데이터를 각각의 네모안에 보기좋게 넣어준다 */
#card .thumbnail{
    display: inline-flex;
}
th{
	background: black;
	color: white;
}
/* 마우스를 올려둘때 그림자효과를 발동한다 */
.dataRow:hover {
 	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 10px 20px 0 rgba(0, 0, 0, 0.2);
	cursor: pointer;
	
	opacity: 1;
    transition: all 0.2s;
 } 
 #cover {
	width: 130px; 
	height: 160px;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.2);
}

#title {
	font-size: 20px;
	border-left: none;
	border-right: none;
	border-bottom: none;
	border-top: none;
}
#hidden {
	display: none;
}
.navbar-form {
	padding-left: 1px;
}
</style>
<script type="text/javascript">
// $(function() {
// 	//  글보기로 페이지 이동 시키는 처리문 작성
// 	// 1. 리스트의 한줄을 선택, 2. 이벤트 함수를 붙인다., 3. 이벤트 함수에 동작할 처리 함수를 파라메터로 넣어준다.
// 	// JQuery
// 	$(".dataRow").click(function(){
// 		// var no -> javaScript 변수 선언
// 		// $(this).find(".no").text() -> jQuery
// 		var bookNo = $(this).find(".bookNo").text();
// 		// javaScript
// 		location = "view.jsp?bookNo=" + bookNo + "&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}"
// 			 	+ "&key=${pageObject.key}&word=${pageObject.word}"; 
// 	});			 
// });
</script>
</head>
<body>
<!-- bootstrap을 이용해 보기좋게 만들어준다. -->
<div class="container">
 <h2>도서 목록</h2>
 <form action="list.jsp" class="navbar-form navbar-right">
			<!-- 페이지 정보를 다시 그대로 보내준다. -->
			<!-- value="1" : 검색을 했으면 검색한 정보에 대한 데이터를 1로 넘어간다. -->
			<input name="page" type="hidden" value="1">
			<input name="perPageNum" type="hidden" value="${pageObject.perPageNum }">
			<div class="input-group">	
    			<div class="form-group navbar-left"> <!-- 왼쪽으로 크기 조정 -->
    			<!-- 검색 종류 선택 (pulldown 메뉴 - select) : key -->
				<select name="key" class="form-control">
					<option value="t" ${(pageObject.key == "t")? "selected":"" }>제목</option>
					<option value="w" ${(pageObject.key == "w")? "selected":"" }>작가</option>
					<option value="g" ${(pageObject.key == "g")? "selected":"" }>장르</option>			
					<option value="p" ${(pageObject.key == "p")? "selected":"" }>출판사</option>			
				</select>
    			<!-- pageObject의 word 값과 맞춰주라. 
					  jsp에서 검색 후 텍스트를 유지시켜줘야 하기 때문에 value값을 주다.-->
    			<input id="word" type="text" class="form-control" name="word" placeholder="Search"
    					value="${param.word }">
    			</div>
    			 <div class="input-group-btn">
      				<button class="btn btn-default" type="submit">
        				<i class="glyphicon glyphicon-search"></i>
      				</button>
    				</div>
  				 </div>
  			</form>
  			 </div>
  			 <br>
  <div class="container">
 <div class="row">
<!--  리스트에 데이터가 존재하지 않을때 출력할 화면 -->
  	<c:if test="${empty list }">
<tr>
	<td colspan="5">데이터가 존재하지 않습니다.</td><br><br>
	<td><a href="list.jsp" class="btn btn-default">목록으로</a></td>
</tr>
</c:if>
<!-- 줄넘김을 위한 함수 -->
<%	int i = 0;
	for(BookBoardVO vo : list) { %>
 <div id="card" class="col-md-4" >
    <div class="thumbnail dataRow" onclick="document.location='view.jsp?bookNo=<%= vo.getBookNo() %>&page=${pageObject.page }&perPageNum=${pageObject.perPageNum }'" >
        <div class="col-md-5">
<!--         받아온 BookNo를 화면에 출력한다 -->
           <div id="hidden"><p>[<%= vo.getBookNo() %>]</p></div>
           <p>&nbsp;</p>
<!--             책표지를 화면에 출력한다 -->
            <img src="<%= vo.getCover() %>" alt="thumbnail" id="cover"  >
        </div>
        <div class="col-md-7">
          <br>
          <!-- 받아온 list 데이터를 화면에 출력한다 -->
          <ul>
            <li><p id="title"><%= vo.getTitle() %></p></li>
            <li><p><%= vo.getWriter() %></p></li>
            <li><p><%= vo.getGenre() %></p></li>
            <li><p><%= vo.getPublisher() %></p></li>
            <li><p><%= vo.getPubDate() %></p></li>
            <li><p><fmt:formatNumber value="<%= vo.getPrice() %>" pattern="#,###" />원</p></li>
          </ul>
        </div>
	</div>
</div>
  
	<% i++;
	// 이미지 3개를 출력하면 새로운 줄을 만든다. 만약 출력된 이미지가 총 데이터의 갯수와 같다면 그만 만든다.
	if(i % 3 == 0 && i != list.size() ){%>
<!-- 	 한 줄을 마감하고 새로운 줄을 시작한다.  -->
	</div>
	<div class="row">
	
	<% }
	 } %>
	 </div>
</div>
<!-- 	도서 등록 페이지로 넘어간다. -->
		<c:if test="${!empty login && login.gradeNo == 9}">
	<div class="text-center">
			<a href="writeForm.jsp" class="btn btn-default">도서 등록</a>
			</div>
			</c:if>
<!-- 	페이지를 이동하는 버튼을 생성한다. -->
<!-- query적용 : 검색후 페이지를 넘겨도 URL 안에 검색 내용 유지 -->
	<div class="text-center">
			<pageNav:pageNav listURI="list.jsp" pageObject="${pageObject }"
			query="&key=${pageObject.key }&word=${pageObject.word }"/>
	</div>	
</body>
</html>

	
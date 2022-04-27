<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
  <style type="text/css">
  	.dataRow:hover {
		background: #eee;
		cursor: pointer;
	}
  </style>
  <script type="text/javascript">
  	$(function(){
  		$(".dataRow").click(function(){
  			var no = $(this).find(".no").text();
  			location = "view.do?no=" + no + "&inc=1&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}";
  		});
  		// perPageNum 데이터의 변경 이벤트 처리 -> jQuery 에 대한 이벤트
  		$("#perPageNumSelect").change(function(){
  			//alert("값 변경");
  			$("#perPageNumForm").submit();
  		});
  		
  	});
  </script>
</head>
<body>
<div class="container">
<h2>게시판 리스트</h2>
<div class="row" style="margin-bottom: 5px;">
	<!-- 검색에 대한 div -->
	<div class="col-md-8">
		<form  class="form-inline">
		<input type="hidden" name="perPageNum" value="${pageObject.perPageNum }">
			<div class="input-group">
			  	<select name="key" class="form-control">
			  		<option value="t" ${(pageObject.key =="t")?"selected":"" }>제목</option>
			  		<option value="c" ${(pageObject.key =="c")?"selected":"" }>내용</option>
			  		<option value="w" ${(pageObject.key =="w")?"selected":"" }>작성자</option>
			  		<option value="tc" ${(pageObject.key =="tc")?"selected":"" }>제목/내용</option>
			  		<option value="tw" ${(pageObject.key =="tw")?"selected":"" }>제목/작성자</option>
			  		<option value="cw" ${(pageObject.key =="cw")?"selected":"" }>내용/작성자</option>
			  		<option value="tcw" ${(pageObject.key =="tcw")?"selected":"" }>전체</option>
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
	</div>
	<!-- 한페이지 당 보여주는 데이터 갯수 -->
	<div class="col-md-4 text-right">
		<form action="list.do" class="form-inline" id="perPageNumForm">
			<input type="hidden" name="page" value="1">
			<input type="hidden" name="key" value="${pageObject.key }">
			<input type="hidden" name="word" value="${pageObject.word }">
			<div class="form-group">
				<label> 1페이지 당 개수
				<select name="perPageNum" class="form-control" id="perPageNumSelect">
					<option ${(pageObject.perPageNum == 5)?"selected":"" }>5</option>
					<option ${(pageObject.perPageNum == 10)?"selected":"" }>10</option>
					<option ${(pageObject.perPageNum == 15)?"selected":"" }>15</option>
					<option ${(pageObject.perPageNum == 20)?"selected":"" }>20</option>
				</select>
				</label>
			</div>
		</form>
	</div>
</div>
<table class="table">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	<c:forEach items="${list }" var="vo">
		<tr class="dataRow">
			<td class="no">${vo.no }</td>
			<td>${vo.title }</td>
			<td>${vo.writer }</td>
			<td><fmt:formatDate value="${vo.writeDate }" pattern="yyyy-MM-dd"/></td>
			<td>${vo.hit }</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="5">
			<pageNav:pageNav listURI="list.do" pageObject="${pageObject }" 
			 query="&key=${pageObject.key }&word=${pageObject.word }"/>
		</td>
	</tr>
	<tr>
	<c:if test="${!empty login }">
		<td colspan="5">
			<a href="write.do?perPageNum=${pageObject.perPageNum }" class="btn btn-default">쓰기</a>
		</td>
	</c:if>	
	</tr>
</table>
</div>
</body>
</html>
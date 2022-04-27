<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="panel panel-default" id="shadow2">
	<div class="panel-heading" style="color:white;">
		<h3><b class="glyphicon glyphicon-book" id="font2"></b>
		<b><span style="font-size: 25px" id="font2">도서목록</span></b>
		<a href="/bookBoard/list.jsp" class="btn btn-info" style="float: right;">더보기&nbsp;>></a></h3>
	</div>
	<div class="panel-body">
		<div class="row" >
			<c:forEach items="${bookBoardList }" var="vo" >
				<div class="col-md-2">
				  <div class="thumbnail bookBoardDataRow" onclick="location='/bookBoard/view.jsp?bookNo=${vo.bookNo}'">
						<img src="${vo.cover}" alt="cover Lists" style="width: 100%; height:200px" id="shadow2">
						<div class="caption">
						<hr>
							<p style="line-height: 10px;"><b><span style="font-size: 15px">${vo.title}</span></b></p>
							<p style="line-height: 5px;"><i><span style="font-size:7px">${vo.writer}</span></i></p>
							<p style="line-height: 5px;"><span style="font-size:7px">${vo.pubDate}</span></p>
							<p style="line-height: 10px;"><b><span style="font-size:12px">
							<fmt:formatNumber value="${vo.price}" pattern="#,###" />원</span></b></p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div><br>
	</div>
</div>